package winwin.customer.app.ui.base.activity;

public interface BaseCallback {
    void doError(Throwable error);

    void doSuccess();

    void doFail();
}
