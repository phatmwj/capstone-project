package winwin.customer.app.ui.dialog;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import lombok.Getter;
import lombok.Setter;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import timber.log.Timber;
import winwin.customer.app.databinding.DialogUploadFileBinding;
import winwin.customer.app.utils.RealPathUtil;

public class UploadFileDialog extends DialogFragment {
    @Getter
    @Setter
    private UploadFileListener listener;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    dismiss();
                    Intent data = result.getData();
                    if (data != null) {
                        // Handle the result data here
                        Uri resultUri = data.getData();
                        if(resultUri == null) return;
                        final InputStream imageStream;
                        try {
                            imageStream = requireActivity().getContentResolver().openInputStream(resultUri);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        listener.getFileSuccess(BitmapFactory.decodeStream(imageStream));
                    }
                }
            });

    private ActivityResultLauncher<String[]> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                Boolean cameraGranted = result.getOrDefault(Manifest.permission.CAMERA, false);
                Boolean storageGranted = result.getOrDefault(Manifest.permission.READ_EXTERNAL_STORAGE, false);
                if (cameraGranted != null && cameraGranted) {
                    openCamera();
                } else if (storageGranted != null && storageGranted) {
                    openGallery();
                } else {
                    Toast.makeText(requireActivity(), "Quyền truy cập bị từ chối!", Toast.LENGTH_SHORT).show();
                }
            });

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        DialogUploadFileBinding binding = DialogUploadFileBinding.inflate(requireActivity().getLayoutInflater());

        dialog.setContentView(binding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding.setDialogFile(this);
        binding.executePendingBindings();

        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    private void openGallery() {
        ImagePicker.with(requireActivity())
                .galleryOnly()
                .cropSquare()
                .createIntent(intent -> {
                    activityResultLauncher.launch(intent);
                    return null;
                });
//                .start();
//        dismiss();
    }

    private void openCamera() {
        ImagePicker.with(requireActivity())
                .cameraOnly()
                .cropSquare()
                .createIntent(intent -> {
                    activityResultLauncher.launch(intent);
                    return null;
                });
//                .start();
//        dismiss();
    }

    public void checkCameraPermission(){
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(new String[]{Manifest.permission.CAMERA});
        } else {
            openCamera();
        }
    }

    public void checkStoragePermission(){
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch( new String[]{Manifest.permission.READ_EXTERNAL_STORAGE});
        } else {
            openGallery();
        }
    }

    public interface UploadFileListener{
        void permissionDenied();
        void getFileSuccess(Bitmap bitmap);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (listener == null) {
            try {
                // Instantiate the mListener so we can send events to the host
                listener = (UploadFileListener) context;
            } catch (ClassCastException e) {
                // The activity doesn't implement the interface, throw exception
                throw new ClassCastException(context.toString()
                        + " must implement InputKeyListener");
            }
        }
    }

    public Uri getUriFromBitmap(Context context, Bitmap bitmap) {
        Uri uri = null;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
        uri = Uri.parse(path);
        return uri;
    }

    public RequestBody getRequestBody(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageByteArray = byteArrayOutputStream.toByteArray();

        // Create a request body for the image
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageByteArray);

        // Create a multipart request builder
        MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        requestBodyBuilder.addFormDataPart("file", "image.jpg", requestFile);
        requestBodyBuilder.addFormDataPart("type", "AVATAR");

        return requestBodyBuilder.build();
    }

    private MultipartBody.Part uriToMultipartBodyPart(Uri uri, String partName) {
        try {
            File file = new File(RealPathUtil.getRealPath(requireActivity(), uri));
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
        } catch (Exception e) {
            Timber.d(e);
            return null;
        }
    }

}
