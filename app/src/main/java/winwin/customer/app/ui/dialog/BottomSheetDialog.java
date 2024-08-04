package winwin.customer.app.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import winwin.customer.app.databinding.LayoutBottemSheetBinding;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    public BottomSheetDialog() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutBottemSheetBinding binding = LayoutBottemSheetBinding.inflate(inflater);
        return binding.getRoot();
    }
}
