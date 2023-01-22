package com.example.karori.menuFragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.example.karori.R;
import com.github.drjacky.imagepicker.ImagePicker;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentSettings extends Fragment {
    private static final String KEY_INDEX1="imageSave";
    Uri uri;
    private Button button;
    Uri savedUri;

    CircleImageView profile;
    CircleImageView changeImage;

    ActivityResultLauncher<Intent> launcher=
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(ActivityResult result)->{
                if(result.getResultCode()==RESULT_OK){
                    uri=result.getData().getData();
                    profile.setImageURI(uri);
                    // Use the uri to load the image
                }else if(result.getResultCode()== ImagePicker.RESULT_ERROR){
                    // Use ImagePicker.Companion.getError(result.getData()) to show an error
                }
            });

    public FragmentSettings() {
        // Required empty public constructor
    }


    public static FragmentSettings newInstance(String param1, String param2) {
        FragmentSettings fragment = new FragmentSettings();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            Log.d("FragmentSettings", "creazione fragment1");
            savedUri = savedInstanceState.getParcelable(KEY_INDEX1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_settings, container, false);
        changeImage=(CircleImageView) view.findViewById(R.id.changeImage);
        profile=(CircleImageView)view.findViewById(R.id.celebrityImage);
        if(savedUri!=null){
            Log.d("FragmentSettings", "ciao");
            profile.setImageURI(savedUri);
        }
        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch(ImagePicker.with(getActivity())
                        .crop()
                        .maxResultSize(1080,1080,true)
                        .galleryOnly()
                        .createIntent());

            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    //Salvo l'immagine in un bundle. Però come faccio dall'uri a salvarlo nel database?
    //devo salvare l'uri o proprio l'immagine a cui è associata l'uri.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelable(KEY_INDEX1, uri);
    }
}
