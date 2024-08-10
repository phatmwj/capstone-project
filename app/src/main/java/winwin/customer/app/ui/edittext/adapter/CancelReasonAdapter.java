package winwin.customer.app.ui.edittext.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import winwin.customer.app.data.model.api.response.CancelReason;
import winwin.customer.app.databinding.ItemCancelReasonBinding;

public class CancelReasonAdapter extends ArrayAdapter<CancelReason>{
    private List<CancelReason> cancelReasonList;
    public CancelReasonAdapter(@NonNull Context context, int resource, @NonNull List<CancelReason> objects) {
        super(context, resource, objects);
        this.cancelReasonList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        ItemCancelReasonBinding binding;
        if (convertView == null){
            binding = ItemCancelReasonBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent, false);
            convertView = binding.getRoot();
        }else{
            binding = (ItemCancelReasonBinding) convertView.getTag();
        }

        convertView.setTag(binding);
        binding.setIvm(this.getItem(position));
        return binding.getRoot();
    }

}
