package winwin.customer.app.ui.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class ObservableTester {

    public static void Test() throws InterruptedException {

        //Create the observable
        Single<String> testSingle = Single.just("Hello World");

        Disposable disposable = testSingle.delay(2, TimeUnit.SECONDS, Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<String>(){

                    @Override
                    public void onSuccess(String s) {
                        Timber.d(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e);
                    }
                });

        Thread.sleep(3000);

        disposable.dispose();

    }
}
