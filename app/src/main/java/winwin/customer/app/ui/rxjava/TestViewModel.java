package winwin.customer.app.ui.rxjava;

import io.reactivex.rxjava3.core.Observable;
import timber.log.Timber;
import winwin.customer.app.MVVMApplication;
import winwin.customer.app.data.Repository;
import winwin.customer.app.data.model.api.ApiModelUtils;
import winwin.customer.app.data.model.api.ResponseListObj;
import winwin.customer.app.data.model.api.ResponseWrapper;
import winwin.customer.app.data.model.api.response.KeyGroupResponse;
import winwin.customer.app.data.model.api.response.KeyResponse;
import winwin.customer.app.ui.base.activity.BaseActivity;
import winwin.customer.app.ui.base.activity.BaseViewModel;

public class TestViewModel extends BaseViewModel {
    public TestViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public Observable<ResponseWrapper<ResponseListObj<KeyResponse>>> getKey(){
        return repository.getApiService().getKeyInformation(null, null, null, null,1)
                .doOnNext(response -> {
                    Timber.d(ApiModelUtils.GSON.toJson(response));
                });
    }

    public Observable<ResponseWrapper<ResponseListObj<KeyGroupResponse>>> getGroup(){
        return repository.getApiService().getKeyGroupList(null, null)
                .doOnNext(response -> {
                    Timber.d(ApiModelUtils.GSON.toJson(response));
                });
    }
}
