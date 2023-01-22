package com.example.karori.ui;

import android.annotation.SuppressLint;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karori.R;
import com.example.karori.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpFragment extends Fragment {
    private FirebaseAuth mAuth;
    private EditText editTextEmail;
    private EditText editTextPsw;
    private EditText editTextCnfPsw;
    private EditText editTextName;


    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            // reload();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Button register = view.findViewById(R.id.RegisterUserButton);
        final Button back = view.findViewById(R.id.backbuttonsignup);

        editTextEmail = view.findViewById(R.id.Mail_input_SU);
        editTextPsw= view.findViewById(R.id.Psw_input_su);
        editTextCnfPsw= view.findViewById(R.id.ConfirmPsw_Input_SU);
        editTextName= view.findViewById(R.id.Name_input_SU);

        assert back != null;
        back.setOnClickListener(v->
                Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment2));


        register.setOnClickListener(v -> {
            registerUser();

        });



    }

    @SuppressLint("SuspiciousIndentation")
    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPsw.getText().toString().trim();
        String cnfpassword = editTextCnfPsw.getText().toString().trim();
        String name = editTextName.getText().toString().trim();

        if(name.isEmpty()){
            editTextName.setError("A name is required!");
            editTextName.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("An email is required!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Insert a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPsw.setError("A password is required!");
            editTextPsw.requestFocus();
            return;
        }

        if(cnfpassword.isEmpty()){
            editTextCnfPsw.setError("Repeat the password!");
            editTextCnfPsw.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPsw.setError("The password is too short, enter a password that is at least 6 characters long ");
            editTextPsw.requestFocus();
            return;
        }

        if(password != null && cnfpassword != null){
            int i = password.compareTo(cnfpassword);

                if(i!=0) {
                    editTextPsw.setError("Password and confirmed password are different!");
                    editTextCnfPsw.requestFocus();
                    editTextPsw.requestFocus();

                    return;
                }
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if( task.isSuccessful()){
                                                Toast.makeText(getContext(), "User has been registered successfully", Toast.LENGTH_LONG).show();
                                            }
                                            else{
                                                Toast.makeText(getContext(), "Registration failed! try again.", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                        } else{
                            Toast.makeText(getContext(), "Registration failed! try again.", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}