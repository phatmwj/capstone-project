package winwin.customer.app.ui.base.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;

public abstract class BaseDialogFragment<E extends ViewDataBinding> extends DialogFragment {

    protected E binding;

    protected abstract int getLayoutId();

    protected abstract void onInitView(View root);

    protected abstract void subscribeUi();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutId(), container, false);
        binding = DataBindingUtil.bind(root);
        onInitView(root);

        Dialog dialog = getDialog();
        if (dialog != null) {
            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            if (dismissWhenTouchOutside()) {
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            }
        }
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subscribeUi();
    }

    /**
     * return true -> do not dismiss when click outside
     * return false -> dismiss when click outside
     * **/
    protected boolean dismissWhenTouchOutside() {
        return false;
    }

}
