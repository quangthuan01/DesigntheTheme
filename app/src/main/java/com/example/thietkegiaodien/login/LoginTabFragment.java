package com.example.thietkegiaodien.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginTabFragment extends Fragment {

    EditText inputEditTextEmail, inputEditTextPass;
    TextInputLayout inputLayoutEmail, inputLayoutPass;
    FirebaseAuth mAuth;
    Button login;
    float y = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mroot = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);
        inputEditTextEmail = mroot.findViewById(R.id.EditTextInputEmail);
        inputEditTextPass = mroot.findViewById(R.id.EditTextInputPass);
        inputLayoutEmail = mroot.findViewById(R.id.inputLayoutEmail);
        inputLayoutPass = mroot.findViewById(R.id.inputLayoutPass);
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
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
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
                            //redirect to user profile
                            startActivity(new Intent(getActivity(), MainActivity.class));

                        } else {
                            Toast.makeText(getActivity(), "Failed to Login! Please check your credentials ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


//        inputLayoutEmail.setTranslationX(900);
//        inputEditTextEmail.setTranslationX(900);
//        inputLayoutPass.setTranslationX(900);
//        inputEditTextPass.setTranslationX(900);
//        login.setTranslationX(900);
//
//        inputLayoutEmail.setAlpha(y);
//        inputEditTextEmail.setAlpha(y);
//        inputLayoutPass.setAlpha(y);
//        inputEditTextPass.setAlpha(y);
//        login.setAlpha(y);
//
//        inputLayoutEmail.animate().translationY(0).alpha(1).setDuration(1100).setStartDelay(300).start();
//        inputEditTextEmail.animate().translationY(0).alpha(1).setDuration(1100).setStartDelay(500).start();
//        inputLayoutPass.animate().translationY(0).alpha(1).setDuration(1100).setStartDelay(700).start();
//        inputEditTextPass.animate().translationY(0).alpha(1).setDuration(1100).setStartDelay(900).start();
//        login.animate().translationY(0).alpha(1).setDuration(1100).setStartDelay(1100).start();

        return mroot;
    }
}
