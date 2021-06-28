package com.example.thietkegiaodien;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thietkegiaodien.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class EmailFragment extends Fragment {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private TextView txtName,txtEmail,txtPhone,txtWellcom;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mview = (ViewGroup) inflater.inflate(R.layout.fragment_email,container,false);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID  = user.getUid();

        txtName = mview.findViewById(R.id.txt_name);
        txtEmail = mview.findViewById(R.id.txt_email);
        txtPhone = mview.findViewById(R.id.txt_phone);
        txtWellcom = mview.findViewById(R.id.wellcom);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile !=null ){
                    String  name = userProfile.name;
                    String  email = userProfile.email;
                    String  phone = userProfile.phone;

                    txtWellcom.setText("Wellcome, " + name + "!");
                    txtName.setText(name);
                    txtEmail.setText(email);
                    txtPhone.setText(phone);

                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(getActivity(), "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        });


        return mview;
    }

}