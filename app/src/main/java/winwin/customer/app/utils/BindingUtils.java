package winwin.customer.app.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import winwin.customer.app.R;
import winwin.customer.app.data.model.api.response.Category;
import winwin.customer.app.ui.fragment.home.adapter.CategoryAdapter;

public final class BindingUtils {
    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .error(R.drawable.car)
                .placeholder(R.drawable.car)
                .into(view);
    }
}
