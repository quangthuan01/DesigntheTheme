package com.example.thietkegiaodien.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.thietkegiaodien.MovableFloatingActionButton;
import com.example.thietkegiaodien.R;
import com.example.thietkegiaodien.adapter.CategoryAdapter;
import com.example.thietkegiaodien.adapter.LoaiChiAdapter;
import com.example.thietkegiaodien.model.Category;
import com.example.thietkegiaodien.model.LoaiChi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class LoaiChiFragment extends Fragment {
    private List<LoaiChi> loaiChiList;
    private LoaiChi loaiChiModel;
    private LoaiChiAdapter loaiChiAdapter;
    private ListView listLoaiChi;
    private MovableFloatingActionButton fabLoaiChi;
    private DatabaseReference DbRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mview = (ViewGroup) inflater.inflate(R.layout.fragment_chi_loaichi,container,false);

        listLoaiChi = mview.findViewById(R.id.listViewLoaiChi);
        fabLoaiChi = mview.findViewById(R.id.fab_LoaiChi);

        loaiChiList = new ArrayList<>();
        DbRef = FirebaseDatabase.getInstance().getReference().child("LoaiChi");
        // get data to fire ->> app
        DbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loaiChiList.clear();
                for (DataSnapshot LoaiChiDatasnaps : snapshot.getChildren()) {
                    LoaiChi loaiChi = LoaiChiDatasnaps.getValue(LoaiChi.class);
                    loaiChiList.add(loaiChi);
                }
                loaiChiAdapter = new LoaiChiAdapter(loaiChiList,getActivity());
                listLoaiChi.setAdapter(loaiChiAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //insert
        fabLoaiChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.item_insert_loaichi);
                //anhsa trong item dia log
                final EditText editTextTitle = dialog.findViewById(R.id.item_text_loaichiTitle);
                final TextView textDate = dialog.findViewById(R.id.item_text_loaichiDate);
                Button chonDate  = dialog.findViewById(R.id.btn_ChonDate);
                Button Insert = dialog.findViewById(R.id.btn_insert_LoaiChi);

                chonDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
                        int thang = calendar.get(Calendar.MONTH);
                        int nam = calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                        textDate.setText(dayOfMonth + "-" + month + "-" + year);
                                    }
                                }, nam, thang, ngay);
                        datePickerDialog.show();

                    }
                });

                Insert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = UUID.randomUUID().toString();
                        String title = editTextTitle.getText().toString();
                        String date = textDate.getText().toString();
                        LoaiChi loaiChi_Model = new LoaiChi(id, title,date);
                        DbRef.child(loaiChi_Model.getIdChi()).setValue(loaiChi_Model);
                        Toast.makeText(getActivity(), "Insert " + editTextTitle.getText().toString() + " successful", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        listLoaiChi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loaiChiModel = (LoaiChi) loaiChiAdapter.getItem(position);
                String _id_ = loaiChiModel.getIdChi();

                Dialog dialogLoaiChi = new Dialog(getActivity(),R.style.dialogAnimation);
                dialogLoaiChi.setContentView(R.layout.dialog_loaichi);
                EditText edt_LoaiChi = dialogLoaiChi.findViewById(R.id.edittextDiaLogLoaiChi);
                TextView textDate1 = dialogLoaiChi.findViewById(R.id.editTextDateDialog);
                Button btn_Chon  =dialogLoaiChi.findViewById(R.id.chonDateDialog);
                Button btn_Delete = dialogLoaiChi.findViewById(R.id.deleteLoaiChi);
                Button btn_Update = dialogLoaiChi.findViewById(R.id.updateLoaiChi);

                edt_LoaiChi.setText(loaiChiModel.getTitleChi());
                textDate1.setText(loaiChiModel.getDate());

                btn_Chon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
                        int thang = calendar.get(Calendar.MONTH);
                        int nam = calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                        textDate1.setText(dayOfMonth + "-" + month + "-" + year);
                                    }
                                }, nam, thang, ngay);
                        datePickerDialog.show();

                    }
                });

                btn_Update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String  Title = edt_LoaiChi.getText().toString();
                        String date = textDate1.getText().toString();
                        upDateData(_id_,Title,date);
                        Toast.makeText(getActivity(),"Update "+ edt_LoaiChi.getText().toString()+" successful", Toast.LENGTH_SHORT).show();
                        dialogLoaiChi.dismiss();
                    }
                });

                btn_Delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteLoaiChi(_id_);
                        dialogLoaiChi.dismiss();
                    }
                });


                dialogLoaiChi.show();
            }
        });



        return mview;
    }
    private void deleteLoaiChi(String idChi) {
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("LoaiChi").child(idChi);
        Task<Void> task = DbRef.removeValue();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getActivity(), "Delete successful", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getActivity(), "Error, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void upDateData(String _id, String _title, String _date) {
        DatabaseReference Dbref = FirebaseDatabase.getInstance().getReference("LoaiChi").child(_id);
        LoaiChi loaiChi = new LoaiChi(_id,_title,_date);
        Dbref.setValue(loaiChi);
    }

}
