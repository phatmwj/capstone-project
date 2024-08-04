package winwin.customer.app.ui.profile;

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

public class EditProfileActivity extends BaseActivity<ActivityEditProfileBinding, EditProfileViewModel> {
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

        viewBinding.edtEPFullName.addTextChangedListener(new GenericTextWatcher());
        viewBinding.edtEPPw.addTextChangedListener(new GenericTextWatcher());
        viewBinding.edtEPCPw.addTextChangedListener(new GenericTextWatcher());

    }

    public class GenericTextWatcher implements TextWatcher {
        public GenericTextWatcher() {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(!viewBinding.edtEPFullName.getText().toString().isEmpty() && !viewBinding.edtEPPw.getText().toString().isEmpty()
                    && !viewBinding.edtEPCPw.getText().toString().isEmpty()
                    && viewBinding.edtEPCPw.getText().toString().equals(viewBinding.edtEPPw.getText().toString())){
                viewBinding.btnEPUpdate.setEnabled(true);
            }else{
                viewBinding.btnEPUpdate.setEnabled(false);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // TODO: Implement as needed
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // TODO: Implement as needed1
        }

    }
}
