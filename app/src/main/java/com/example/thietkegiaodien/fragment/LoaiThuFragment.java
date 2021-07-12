package com.example.thietkegiaodien.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thietkegiaodien.MovableFloatingActionButton;
import com.example.thietkegiaodien.R;
import com.example.thietkegiaodien.adapter.LoaiChiAdapter;
import com.example.thietkegiaodien.adapter.LoaiThuAdapter;
import com.example.thietkegiaodien.model.LoaiChi;
import com.example.thietkegiaodien.model.LoaiThu;
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


public class LoaiThuFragment extends Fragment {
    private LoaiThuAdapter loaiThuAdapter;
    private LoaiThu loaiThuModel;
    private List<LoaiThu> loaiThuList;
    private ListView listViewLoai;
    private MovableFloatingActionButton fabLoai;
    private DatabaseReference databaseReference;
    private AlertDialog alertDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_thu_loaithu, container, false);

        listViewLoai = view.findViewById(R.id.listViewLoaiThu);
        fabLoai = view.findViewById(R.id.fabThu);
        loaiThuList = new ArrayList<>();


        databaseReference = FirebaseDatabase.getInstance().getReference().child("LoaiThu");
        // get data to fire ->> app
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loaiThuList.clear();
                for (DataSnapshot LoaiThuDatasnaps : snapshot.getChildren()) {
                    LoaiThu loaiThu = LoaiThuDatasnaps.getValue(LoaiThu.class);
                    loaiThuList.add(loaiThu);
                }
                loaiThuAdapter = new LoaiThuAdapter(loaiThuList, getActivity());
                listViewLoai.setAdapter(loaiThuAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //insert
        fabLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.item_insert_loaithu);
                //anhsa trong item dia log
                EditText editTextTitle = dialog.findViewById(R.id.item_text_loaiThuTitle);
                TextView textDate = dialog.findViewById(R.id.item_text_loaiThuDate);
                Button chonDate  = dialog.findViewById(R.id.btn_ChonDateThu);
                Button Insert = dialog.findViewById(R.id.btn_insert_LoaiThu);

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
                        if (title.isEmpty()){
                            Toast.makeText(getActivity(), "Please enter Title", Toast.LENGTH_SHORT).show();
                            editTextTitle.requestFocus();
                            return;
                        }else if (date.isEmpty()){
                            Toast.makeText(getActivity(), "Please click pick date", Toast.LENGTH_SHORT).show();
                            textDate.requestFocus();
                            return;
                        }else {
                        LoaiThu loaiThu = new LoaiThu(id, title,date);
                        databaseReference.child(loaiThu.getIdLoaiThu()).setValue(loaiThu);
                        Toast.makeText(getActivity(), "Insert " + editTextTitle.getText().toString() + " successful", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });

        listViewLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loaiThuModel = (LoaiThu) loaiThuAdapter.getItem(position);
                String _id_ = loaiThuModel.getIdLoaiThu();

                Dialog dialogLoaiThu = new Dialog(getActivity(),R.style.dialogAnimation);
                dialogLoaiThu.setContentView(R.layout.dialog_loaithu);
                EditText edt_LoaiThu = dialogLoaiThu.findViewById(R.id.edittextDiaLogLoaiThu);
                TextView textDate1 = dialogLoaiThu.findViewById(R.id.editTextDateDialogThu);
                Button btn_Chon  =dialogLoaiThu.findViewById(R.id.chonDateDialogThu);
                Button btn_Delete = dialogLoaiThu.findViewById(R.id.deleteLoaiThu);
                Button btn_Update = dialogLoaiThu.findViewById(R.id.updateLoaiThu);

                edt_LoaiThu.setText(loaiThuModel.getTitleLoaiThu());
                textDate1.setText(loaiThuModel.getDateLoaiThu());

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
                        String  Title = edt_LoaiThu.getText().toString();
                        String date = textDate1.getText().toString();
                        upDateData(_id_,Title,date);
                        Toast.makeText(getActivity(),"Update "+ edt_LoaiThu.getText().toString()+" successful", Toast.LENGTH_SHORT).show();
                        dialogLoaiThu.dismiss();
                    }
                });

                btn_Delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (alertDialog == null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            View view = LayoutInflater.from(getActivity()).inflate(
                                    R.layout.dialog_thongbao_delete, null);
                            builder.setView(view);
                            alertDialog = builder.create();
                            if (alertDialog.getWindow() != null) {
                                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
                            }

                            TextView textCancel = view.findViewById(R.id.textCancel);
                            TextView textDelete = view.findViewById(R.id.textDelete);
                            textDelete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    deleteLoaiThu(_id_);
                                    dialogLoaiThu.dismiss();
                                    alertDialog.dismiss();

                                }
                            });
                            textCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                }
                            });

                        }
                        alertDialog.show();

                    }
                });

                dialogLoaiThu.show();
            }
        });


        return view;
    }

    private void deleteLoaiThu(String idThu) {
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("LoaiThu").child(idThu);
        Task<Void> task = DbRef.removeValue();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                showThongBao();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getActivity(), "Error, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void upDateData(String _id, String _title, String _date) {
        DatabaseReference Dbref = FirebaseDatabase.getInstance().getReference("LoaiThu").child(_id);
        LoaiThu loaiThu = new LoaiThu(_id,_title,_date);
        Dbref.setValue(loaiThu);
    }
    private void  showThongBao(){
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_thongbao_deletesuccess);
        Button btnOk = (Button)dialog.findViewById(R.id.textOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
