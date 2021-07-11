package com.example.thietkegiaodien.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.thietkegiaodien.MainActivity;
import com.example.thietkegiaodien.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginTabFragment extends Fragment {

    EditText inputEditTextEmail, inputEditTextPass;
    TextInputLayout inputLayoutEmail, inputLayoutPass;
    TextView forgetPass;
    FirebaseAuth mAuth;
    Button login;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mroot = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);
        inputEditTextEmail = mroot.findViewById(R.id.EditTextInputEmail);
        inputEditTextPass = mroot.findViewById(R.id.EditTextInputPass);
        inputLayoutEmail = mroot.findViewById(R.id.inputLayoutEmail);
        inputLayoutPass = mroot.findViewById(R.id.inputLayoutPass);
        forgetPass= mroot.findViewById(R.id.forget_pass);
        login = mroot.findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEditTextEmail.getText().toString().trim();
                String passworld = inputEditTextPass.getText().toString().trim();

                if (email.isEmpty()) {
                    inputEditTextEmail.setError("Email is required!");
                    inputEditTextEmail.requestFocus();
                    return;

                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    inputEditTextEmail.setError("Please enter a valid email!");
                    inputEditTextEmail.requestFocus();
                    return;
                }
                if (passworld.isEmpty()) {
                    inputEditTextPass.setError("Passworld is required!");
                    inputEditTextPass.requestFocus();
                    return;

                }
                if (passworld.length() < 6) {
                    inputEditTextPass.setError("Min passworld length should be 6  characters!");
                    inputEditTextPass.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, passworld).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //check email successfull
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user.isEmailVerified()) {
                                //redirect to user profile
                                startActivity(new Intent(getActivity(), MainActivity.class));
                            } else {
                                //send email
                                user.sendEmailVerification();
                                Toast.makeText(getActivity(), "Check your email to verify your account!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Failed to Login! Please check your credentials ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ForgotPassWord.class));
            }
        });
        return mroot;
    }
}
