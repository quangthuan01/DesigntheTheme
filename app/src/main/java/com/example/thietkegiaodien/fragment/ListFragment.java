package com.example.thietkegiaodien.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thietkegiaodien.MovableFloatingActionButton;
import com.example.thietkegiaodien.R;
import com.example.thietkegiaodien.adapter.CategoryAdapter;
import com.example.thietkegiaodien.model.Category;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListFragment extends Fragment {

    private MovableFloatingActionButton fab;
    private ListView listView;
    private List<Category> categoryList;
    private DatabaseReference phoneDbRef;
    private CategoryAdapter categoryAdapter;
    private Category categoryModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mview = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        fab = mview.findViewById(R.id.fab_move);
        listView = mview.findViewById(R.id.listView);
        categoryList = new ArrayList<>();
        //tao bang Categorys tren firebase
        phoneDbRef = FirebaseDatabase.getInstance().getReference().child("Categorys");

        // get data to fire ->> app
        phoneDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categoryList.clear();
                for (DataSnapshot catagorysDatasnaps : snapshot.getChildren()) {
                    Category categoryModel = catagorysDatasnaps.getValue(Category.class);
                    categoryList.add(categoryModel);
                }
                categoryAdapter = new CategoryAdapter(getActivity(), categoryList);
                listView.setAdapter(categoryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //item long click listview ->> detail categorys
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               categoryModel  =(Category) categoryAdapter.getItem(position);
                String _id = categoryModel.getId();
                Dialog dialog = new Dialog(getActivity(), R.style.DialogforAnimation);
                dialog.setContentView(R.layout.item_update_list);
                EditText updateNamePhone = dialog.findViewById(R.id.item_update_editTextNamePhone);
                EditText updateOperatingsystem = dialog.findViewById(R.id.item_update_editTextOperatingsystem);
                EditText updatePrice = dialog.findViewById(R.id.item_update_editTextPrice);
                Spinner spn_updateBrand = dialog.findViewById(R.id.item_update_spinner_theloai);
                Button btnDelete = dialog.findViewById(R.id.item_update_btn_delete);
                Button btnUpdate = dialog.findViewById(R.id.item_update_btn_insert);

                updateNamePhone.setText(categoryModel.getPhoneName());
                updateOperatingsystem.setText(categoryModel.getPhoneOperatingsystem());
                updatePrice.setText(categoryModel.getPhonePrice());
                spn_updateBrand.getSelectedItem();
//                categoryModel.setPhoneBrand(spn_updateBrand.getSelectedItem().toString());

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //update database
                        String newName = updateNamePhone.getText().toString();
                        String newOperatingsystem = updateOperatingsystem.getText().toString();
                        String newPrice = updatePrice.getText().toString();
                        String newSpn = spn_updateBrand.getSelectedItem().toString();
                        upDateData(_id, newName, newOperatingsystem, newPrice, newSpn);
                        Toast.makeText(getActivity(), "Update " + updateNamePhone.getText().toString() + " successful", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deletePhone(_id);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        //insert
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.item_listview);
                //anhsa trong item dia log
                final EditText editTextNamePhone = dialog.findViewById(R.id.editTextNamePhone);
                final EditText editTextOperatingsystem = dialog.findViewById(R.id.editTextOperatingsystem);
                final EditText editTextPrice = dialog.findViewById(R.id.editTextPrice);
                final TextView textLoai = dialog.findViewById(R.id.text_theloai);
                Button btnInsert = dialog.findViewById(R.id.btn_insert);
                btnInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = UUID.randomUUID().toString();
                        String phoneName = editTextNamePhone.getText().toString();
                        String phoneOperatingsystem = editTextOperatingsystem.getText().toString();
                        String phonePrice = editTextPrice.getText().toString();
                        String category = textLoai.getText().toString();
                        Category categoryModel = new Category(id, phoneName, phoneOperatingsystem, phonePrice, category);
                        phoneDbRef.child(categoryModel.getId()).setValue(categoryModel);
                        Toast.makeText(getActivity(), "Insert " + editTextNamePhone.getText().toString() + " successful", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        return mview;
    }

    private void deletePhone(String id) {
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("Categorys").child(id);
        Task<Void> task = DbRef.removeValue();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                showToas("Delete Phone Successful");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                showToas("Error, Please try again!");
            }
        });
    }

    private void showToas(String mess) {
        Toast.makeText(getActivity(), mess, Toast.LENGTH_SHORT).show();
    }

    private void upDateData(String id, String name, String operatingsystem, String price, String brand) {
        DatabaseReference Dbref = FirebaseDatabase.getInstance().getReference("Categorys").child(id);
        Category categoryModel = new Category(id, name, operatingsystem, price, brand);
        Dbref.setValue(categoryModel);
    }


}
