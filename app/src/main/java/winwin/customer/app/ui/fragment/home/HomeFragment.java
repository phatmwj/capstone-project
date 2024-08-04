package winwin.customer.app.ui.fragment.home;

import android.util.Log;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import winwin.customer.app.BR;
import winwin.customer.app.R;
import winwin.customer.app.data.model.api.response.Banner;
import winwin.customer.app.data.model.api.response.Category;
import winwin.customer.app.data.model.api.response.Food;
import winwin.customer.app.databinding.FragmentHomeBinding;
import winwin.customer.app.di.component.FragmentComponent;
import winwin.customer.app.ui.base.fragment.BaseFragment;
import winwin.customer.app.ui.fragment.home.adapter.BannerAdapter;
import winwin.customer.app.ui.fragment.home.adapter.CategoryAdapter;
import winwin.customer.app.ui.fragment.home.adapter.FoodAdapter;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeFragmentViewModel> {

    BannerAdapter bannerAdapter;
    CategoryAdapter categoryAdapter;
    FoodAdapter foodAdapter;

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void performDataBinding() {
        loadCategory();
        loadBanner();
        loadFood();
    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    private void loadBanner(){
        //
        List<Banner> banners = new ArrayList<>();
        banners.add(new Banner(R.drawable.banner));
        banners.add(new Banner(R.drawable.banner));
        banners.add(new Banner(R.drawable.banner));

        RecyclerView.LayoutManager layoutBanner = new LinearLayoutManager(getActivity().getApplicationContext()
                ,LinearLayoutManager.HORIZONTAL, false);
        binding.rcBanner.setLayoutManager(layoutBanner);
        binding.rcBanner.setItemAnimator(new DefaultItemAnimator());
        bannerAdapter = new BannerAdapter(banners);
        binding.rcBanner.setAdapter(bannerAdapter);
        bannerAdapter.setOnItemClickListener(banner -> {
            Log.d("Click", "performDataBinding: ");
        });
    }

    private void loadCategory(){
        //
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Ô tô", null));
        categories.add(new Category("Xe máy", null));
        categories.add(new Category("Giao hàng", null));
        categories.add(new Category("Đi chợ", null));

        binding.setLifecycleOwner(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext()
                ,LinearLayoutManager.HORIZONTAL, false);
        binding.rcCategory.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(categories);
        binding.rcCategory.setAdapter(categoryAdapter);
        viewModel.categoryListLiveData.setValue(categories);

        categoryAdapter.setOnItemClickListener(category -> {

        });
    }

    private void loadFood(){
        //
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Trà sữa Bubble", R.drawable.product));
        foods.add(new Food("Combo Burger", R.drawable.product));
        foods.add(new Food("Trà sữa Bubble", R.drawable.product));
        foods.add(new Food("Combo Burger", R.drawable.product));

        RecyclerView.LayoutManager layoutSg = new LinearLayoutManager(getActivity().getApplicationContext()
                ,LinearLayoutManager.HORIZONTAL, false);
        binding.rcSg.setLayoutManager(layoutSg);
        binding.rcSg.setItemAnimator(new DefaultItemAnimator());
        foodAdapter = new FoodAdapter(foods);
        binding.rcSg.setAdapter(foodAdapter);

        //
        RecyclerView.LayoutManager layoutPb = new LinearLayoutManager(getActivity().getApplicationContext()
                ,LinearLayoutManager.HORIZONTAL, false);
        binding.rcPb.setLayoutManager(layoutPb);
        binding.rcPb.setItemAnimator(new DefaultItemAnimator());
        binding.rcPb.setAdapter(foodAdapter);

        foodAdapter.setOnItemClickListener(food -> {

        });
    }
}
