package winwin.customer.app.ui.profile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;

import winwin.customer.app.BR;
import winwin.customer.app.R;
import winwin.customer.app.databinding.ActivityEditProfileBinding;
import winwin.customer.app.di.component.ActivityComponent;
import winwin.customer.app.ui.base.activity.BaseActivity;
import winwin.customer.app.ui.dialog.UploadFileDialog;

public class EditProfileActivity extends BaseActivity<ActivityEditProfileBinding, EditProfileViewModel> implements UploadFileDialog.UploadFileListener {
    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_profile;
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
        setTheme(R.style.WhiteAppTheme);
        super.onCreate(savedInstanceState);

    }
    public void getImage(){
        UploadFileDialog uploadFileDialog = new UploadFileDialog();
        uploadFileDialog.setListener(this);
        uploadFileDialog.show(getSupportFragmentManager(),"uploadFileFragment");
    }

    @Override
    public void permissionDenied() {

    }

    @Override
    public void getFileSuccess(Bitmap bitmap) {
        viewBinding.imageAvatar.setImageBitmap(bitmap);
    }
}
