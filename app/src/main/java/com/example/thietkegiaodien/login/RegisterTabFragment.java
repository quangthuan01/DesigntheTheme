package com.example.thietkegiaodien.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.thietkegiaodien.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterTabFragment extends Fragment {
    //initalaze variable

    TextInputLayout tilName, tilEmail;

    EditText Name, Email, PassWorld, Phone;
    Button Register;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mroot = (ViewGroup) inflater.inflate(R.layout.register_tab_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();

        Name = mroot.findViewById(R.id.EditTextInputName_signup);
        Email = mroot.findViewById(R.id.EditTextInputEmail_signup);
        PassWorld = mroot.findViewById(R.id.EditTextInputPass_signup);
        Phone = mroot.findViewById(R.id.EditTextInputConfimPass_signup);

        tilName = mroot.findViewById(R.id.inputLayoutName_sinup);
        tilEmail = mroot.findViewById(R.id.inputLayoutEmail_sinup);

        Register = mroot.findViewById(R.id.register);

//
//        Name.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                //check condition
//                if (!s.toString().isEmpty() && !s.toString().matches("[a-zA-Z]+")){
//                    //when value is not equal to empty and contian numeric value
//                    //show err0r
//                    tilName.setError("Allow only character");
//
//                }else {
//                    //when value is equal to character only
//                    //hide error
//                    tilName.setError(null);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        tilEmail.setEndIconOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //clear value
//                Email.getText().clear();
//
//            }
//        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String passworld = PassWorld.getText().toString().trim();
                String phone = Phone.getText().toString().trim();

                if (name.isEmpty()) {
                    Name.setError("Full name is required!");
                    Name.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    Email.setError("Email is required!");
                    Email.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Email.setError("Please provide valid email!");
                    Email.requestFocus();
                    return;
                }

                if (passworld.isEmpty()) {
                    PassWorld.setError("PassWorld is required!");
                    PassWorld.requestFocus();
                    return;
                }
                if (passworld.length() < 6) {
                    PassWorld.setError("Min passworld length should be 6  characters!");
                    PassWorld.requestFocus();
                    return;
                }

                if (phone.isEmpty()) {
                    Phone.setError("Phone  is required!");
                    Phone.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, passworld).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(name, email, phone);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //redirect to login layout
                                        Toast.makeText(getActivity(), "Register Successfull", Toast.LENGTH_SHORT).show();
                                        resetFromdata();
                                    } else {
                                        Toast.makeText(getActivity(), "Register Failed! Try again!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getActivity(), "Register Failed! Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        return mroot;
    }

    private void resetFromdata() {
        Name.setText("");
        Name.requestFocus();
        Email.setText("");
        PassWorld.setText("");
        Phone.setText("");

    }
}
