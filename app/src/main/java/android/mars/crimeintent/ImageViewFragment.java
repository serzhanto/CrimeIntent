package android.mars.crimeintent;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.io.File;


/**
 * Created by Mars on 20.07.2016.
 */
public class ImageViewFragment extends DialogFragment {
    private File mPhotoFile;
    private ImageView mImageView;
    private static final String ARG_IMAGE = "img";

    public static ImageViewFragment newInstance(File file) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_IMAGE, file);
        ImageViewFragment fragment = new ImageViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mPhotoFile = (File) getArguments().getSerializable(ARG_IMAGE);
        final View v = LayoutInflater.from(getActivity()).inflate(R.layout.image_view, null);
        mImageView = (ImageView) v.findViewById(R.id.imageView);
        ViewTreeObserver vto = v.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), v.getWidth(), v.getHeight());
                mImageView.setImageBitmap(bitmap);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Photo View")
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
