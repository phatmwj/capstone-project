package winwin.customer.app.rabbitmq;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.Recoverable;
import com.rabbitmq.client.RecoveryListener;
import com.rabbitmq.client.ShutdownSignalException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class MQttService implements Runnable {
    public static final String TAG = "MQttService";

    private boolean quitDistribuleMqtt = false;
    private ScheduledExecutorService executor;

    private Connection conn;
    private Channel channel;
    RecoveryListener recoveryListener;
    private final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Double.class, (JsonSerializer<Double>) (src, typeOfSrc, context) -> {
                if (src == src.longValue()) {
                    return new JsonPrimitive(src.longValue());
                }
                return new JsonPrimitive(src);
            })
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .create();

    private final IBinder binder = new LocalBinder();


    private void startMqtt(){
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setRequestedHeartbeat(30);
            factory.setUsername("");
            factory.setPassword("");
            factory.setVirtualHost("/");
            factory.setAutomaticRecoveryEnabled(true);
            factory.setNetworkRecoveryInterval(3000);
            factory.setTopologyRecoveryEnabled(false);
            //factory.setHost(Constants.MQTT_HOST);
            //factory.setPort(Constants.MQTT_PORT);
            createConnection(factory);
            channel = conn.createChannel();

            recoveryListener = new RecoveryListener() {
                @Override
                public void handleRecovery(Recoverable recoverable) {
                    Timber.d("handleRecovery");

                }

                @Override
                public void handleRecoveryStarted(Recoverable recoverable) {
                    Timber.d("handleRecoveryStarted");
                }
            };
            ((Recoverable) conn).addRecoveryListener(recoveryListener);
            Timber.d("=============> connect to mqtt success");
        }catch (Exception e){
            Timber.d(e);
            Timber.d("MQTT ket noi that bai host: "+factory.getHost() + ":" + factory.getPort());
            asyncWaitAndReconnect();
        }
    }

    private void retryConnectBlock(ConnectionFactory factory) throws IOException, TimeoutException {
        conn.close();
        createConnection(factory);
        ((Recoverable) conn).addRecoveryListener(recoveryListener);
        channel = conn.createChannel();
        consumeMessage();
    }

    private void createConnection(ConnectionFactory factory) throws IOException, TimeoutException {
        conn = factory.newConnection(new Address[]{new Address("HOST", 1111)});
        conn.addShutdownListener(cause -> {
            // reconnect only on unexpected errors
            if (!cause.isInitiatedByApplication()) {
                Timber.d("Lost connection to " + factory.getHost() + ":" + factory.getPort());

                try {
                    channel.close();
                    conn.close();
                } catch (IOException e) {
                    Timber.d(e);
                    throw new RuntimeException(e);
                } catch (TimeoutException e) {
                    Timber.d(e);
                    throw new RuntimeException(e);
                }
                channel = null;
                conn = null;
                asyncWaitAndReconnect();
            } else {
                Timber.d("shutdown rabbitMQ");
            }
        });
    }

    private void consumeMessage() throws IOException {
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("** ====> mqtt msg: "+message);
                try {
                    long deliveryTag = envelope.getDeliveryTag();
                    channel.basicAck(deliveryTag, false);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void handleRecoverOk(String consumerTag) {
                super.handleRecoverOk(consumerTag);
                Timber.d("handleRecoverOk");
            }
            @Override
            public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
                super.handleShutdownSignal(consumerTag, sig);
                Timber.d("handleShutdownSignal");
            }
        };
        channel.basicConsume("QueueName", false, consumer);
    }

//    private void closeRealtimeDB() {
//        disposable.add(Completable.create(emitter -> {
//                    try {
//                        if (channel != null) {
//                            channel.close();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    try {
//                        if (conn != null) {
//                            conn.close();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    emitter.onComplete();
//                })
//                .subscribeOn(Schedulers.io())
//                .subscribe(() -> {},Timber::d));
//
//    }

    private void asyncWaitAndReconnect() {
//        closeRealtimeDB();
        executor.schedule(() -> {
            Timber.d("asyncWaitAndReconnect");
        }, 15, TimeUnit.SECONDS);
    }

    @Override
    public void run() {

        synchronized (this) {
            try {

                //Delete cmd before 1 day
                int numberOfDayKeep = 1;
                Long beforeTime = System.currentTimeMillis() - (numberOfDayKeep * (86400 * 1000));//1 ngay

                while (!quitDistribuleMqtt) {
                }
            } catch (Exception e) {
                // thread stop, restart
                new Thread(this).start();
            }
        }
    }
    public class LocalBinder extends Binder {
        public MQttService getService() {
            // Return this instance of LocalService so clients can call public methods.
            return MQttService.this;
        }
    }
    public Boolean isOpen(){
        return conn != null && conn.isOpen();
    }
}
