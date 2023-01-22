package com.example.karori.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.karori.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password_Fragment extends Fragment {
    private TextInputEditText editTextEmail;
    private FirebaseAuth mAuth;

    public Forgot_Password_Fragment() {
        // Required empty public constructor
    }


    public static Forgot_Password_Fragment newInstance() {
        Forgot_Password_Fragment fragment = new Forgot_Password_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot__password_, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Button backButton = view.findViewById(R.id.backbuttonreset);
        final Button resetButton = view.findViewById(R.id.ResetPsw_Button);
        editTextEmail = view.findViewById(R.id.mailedittext);

        backButton.setOnClickListener( v->
                Navigation.findNavController(view).navigate(R.id.action_forgot_Password_Fragment_to_loginFragment));

        resetButton.setOnClickListener( v -> {
            resetPassword();
        }
        );
    }

    private void resetPassword() {
        String email = editTextEmail.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Insert a valid email");
            editTextEmail.requestFocus();
            return;
        }

        // TODO: controllare se effettivamente quell'utente e registrato
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(), "mail send. check your email", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(), "try again! Something wrong happened", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}