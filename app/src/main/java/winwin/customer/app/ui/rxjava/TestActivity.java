package winwin.customer.app.ui.rxjava;

import android.os.Bundle;

import androidx.annotation.Nullable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;
import winwin.customer.app.BR;
import winwin.customer.app.R;
import winwin.customer.app.data.model.api.ApiModelUtils;
import winwin.customer.app.data.model.api.ResponseListObj;
import winwin.customer.app.data.model.api.ResponseWrapper;
import winwin.customer.app.data.model.api.response.KeyGroupResponse;
import winwin.customer.app.data.model.api.response.KeyResponse;
import winwin.customer.app.data.remote.ApiService;
import winwin.customer.app.databinding.ActivityTestBinding;
import winwin.customer.app.di.component.ActivityComponent;
import winwin.customer.app.ui.base.activity.BaseActivity;

public class TestActivity extends BaseActivity<ActivityTestBinding, TestViewModel> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testObservableMerge();
    }

    public void testObservableMerge(){
        viewModel.showLoading();
        Observable<ResponseWrapper<ResponseListObj<KeyResponse>>> a = viewModel.getKey();
        Observable<ResponseWrapper<ResponseListObj<KeyGroupResponse>>> b = viewModel.getGroup();
        viewModel.compositeDisposable.add(Observable.merge(a,b)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            Timber.e(ApiModelUtils.GSON.toJson(response));
                            viewModel.hideLoading();
                        }, throwable -> {
                            viewModel.hideLoading();
                            viewModel.showErrorMessage(String.valueOf(R.string.newtwork_error));
                        }
                )
        );
    }


    public void test2(){
        viewModel.compositeDisposable.add(viewModel.getGroup()
                .flatMap(new Function<ResponseWrapper<ResponseListObj<KeyGroupResponse>>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(ResponseWrapper<ResponseListObj<KeyGroupResponse>> responseListObjResponseWrapper) throws Throwable {
                        return null;
                    }
                })
                .toList()
                .subscribe()

        );
    }

    public void test3(){
        viewModel.compositeDisposable.add(viewModel.getGroup()
                .flatMapIterable(new Function<ResponseWrapper<ResponseListObj<KeyGroupResponse>>, Iterable<?>>() {
                    @Override
                    public Iterable<?> apply(ResponseWrapper<ResponseListObj<KeyGroupResponse>> responseListObjResponseWrapper) throws Throwable {
                        return null;
                    }
                })
                .toList()
                .subscribe()

        );
    }



}
