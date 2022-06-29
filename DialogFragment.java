package android.bignerdranch.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.FileProvider;

import java.io.File;
import java.util.UUID;



public class DialogFragment extends androidx.fragment.app.DialogFragment {
    private ImageView mZoomedView;
    private Crime mCrime;           // a crime object reference.
    private File mZoomedFile;
    private static final String ARG_CRIME_ID = "crime_id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_image, container, false);
        UUID crimeId = (UUID)getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
        mZoomedView = (ImageView)v.findViewById(R.id.crime_zoomed);
        mZoomedFile = CrimeLab.get(getActivity()).getPhotoFile(mCrime);
        Bitmap bm = PictureUtils.getZoomedBitmap(
                mZoomedFile.getPath(), getActivity());
        mZoomedView.setImageBitmap(bm);
        return v;
    }
}
