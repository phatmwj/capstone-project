package winwin.customer.app.ui.fragment.home;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import winwin.customer.app.MVVMApplication;
import winwin.customer.app.data.Repository;
import winwin.customer.app.data.model.api.response.Category;
import winwin.customer.app.ui.base.fragment.BaseFragmentViewModel;

public class HomeFragmentViewModel extends BaseFragmentViewModel {

    public MutableLiveData<List<Category>> categoryListLiveData;
    public HomeFragmentViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        categoryListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Category>> getCategoryListLiveData() {
        return categoryListLiveData;
    }
    public void openNotification(){

    }
    public void showAllCategory(){

    }
    public void showAllPropose(){

    }
    public void showAllPopularFood(){

    }
}
