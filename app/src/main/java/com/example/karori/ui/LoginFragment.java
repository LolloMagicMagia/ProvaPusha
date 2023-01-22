package com.example.karori.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPsw;
    private static final String TAG = LoginFragment.class.getSimpleName();
    private static final boolean USE_NAVIGATION_COMPONENT = true;
    private FirebaseAuth mAuth;
    Button provaSummaryActivity;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        //per provare a vedere se cambia page
        provaSummaryActivity=view.findViewById(R.id.provaSummaryActivity);
        provaSummaryActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myInt=new Intent(getContext(), SummaryActivity.class);
                startActivity(myInt);
            }
        });

        return view;

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Button buttonLogin = (Button) view.findViewById(R.id.buttonLogin);
        final Button buttonGoogleLogin = (Button) view.findViewById(R.id.button_google_login);
        final Button buttonFrgPsw =(Button)  view.findViewById(R.id.buttonForgotPsw);
        final Button buttonSignUp = (Button) view.findViewById(R.id.sign_up_button);

        editTextEmail = view.findViewById(R.id.usernameEditText);
        editTextPsw = view.findViewById(R.id.PswEditText);

        buttonSignUp.setOnClickListener( v->
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment));

        buttonFrgPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "pulsante schiacciato");
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_forgot_Password_Fragment);
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                userLogin();
                }
        });

    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPsw.getText().toString().trim();

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

        if(password.isEmpty()){
            editTextPsw.setError("A password is required!");
            editTextPsw.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPsw.setError("The password is too short, enter a password that is at least 6 characters long ");
            editTextPsw.requestFocus();
            return;
        }
        // TODO : verificare se la mail è presente nel database;


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // TODO: fare il collegamento con la main activity.
                }else{
                    Toast.makeText(getContext(), "Login failed! try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}