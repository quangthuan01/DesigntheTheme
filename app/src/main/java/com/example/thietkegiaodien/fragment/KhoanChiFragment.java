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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.thietkegiaodien.R;
import com.example.thietkegiaodien.adapter.KhoanChiAdapter;
import com.example.thietkegiaodien.adapter.LoaiChiAdapter;
import com.example.thietkegiaodien.adapter.SpinnerKhoanChiAdapter;
import com.example.thietkegiaodien.model.KhoanChi;
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

public class KhoanChiFragment extends Fragment {
    private KhoanChiAdapter khoanChiAdapter;
    private SpinnerKhoanChiAdapter spinnerKhoanChiAdapter;
    private KhoanChi khoanChiModel;
    private LoaiChi loaiChiModel;
    private List<KhoanChi> khoanChiList;
    private List<LoaiChi> loaiChiList;
    private DatabaseReference DbRef;
    private Spinner spinner;
    private EditText editTextMoney;
    private TextView textViewDate;
    private Button btn_ChonDate, btn_Insert;
    private ListView listView;
    private AlertDialog alertDialog;
    private String select;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mview = (ViewGroup) inflater.inflate(R.layout.fragment_chi_khoanchi, container, false);

        spinner = (Spinner) mview.findViewById(R.id.spinnerKhoanChi);
        editTextMoney = (EditText) mview.findViewById(R.id.editTextSoTienKhoanChi);
        textViewDate = (TextView) mview.findViewById(R.id.textDateKhoanChi);
        btn_ChonDate = (Button) mview.findViewById(R.id.btn_chonngayKhoanChi);
        btn_Insert = (Button) mview.findViewById(R.id.btn_ThemKhoanChi);
        listView = (ListView) mview.findViewById(R.id.listViewKhoanChi);

        loaiChiList = new ArrayList<>();
        khoanChiList = new ArrayList<>();
        SpinnerKhoanChi();
//        spinnerKhoanChiAdapter = new SpinnerKhoanChiAdapter(loaiChiList, getActivity());
//        spinner.setAdapter(spinnerKhoanChiAdapter);
        DbRef = FirebaseDatabase.getInstance().getReference().child("KhoanChi");
        // get data to fire ->> app
        DbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                khoanChiList.clear();
                for (DataSnapshot KhoanChiDatasnaps : snapshot.getChildren()) {
                    KhoanChi khoanChi = KhoanChiDatasnaps.getValue(KhoanChi.class);
                    khoanChiList.add(khoanChi);
                }
                khoanChiAdapter = new KhoanChiAdapter(khoanChiList, getActivity());
                listView.setAdapter(khoanChiAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select = ((LoaiChi) spinnerKhoanChiAdapter.getItem(position)).getTitleChi();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_ChonDate.setOnClickListener(new View.OnClickListener() {
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
                                textViewDate.setText(dayOfMonth + "-" + month + "-" + year);
                            }
                        }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });

        btn_Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = UUID.randomUUID().toString();
                String title = select;
                String money = editTextMoney.getText().toString();
                String date = textViewDate.getText().toString();
                if (editTextMoney.toString().isEmpty()) {
                    editTextMoney.requestFocus();
                    return;
                } else if (textViewDate.toString().isEmpty()) {
                    textViewDate.requestFocus();
                    return;
                } else {
                    KhoanChi khoanChi = new KhoanChi(id, title, money, date);
                    DbRef.child(khoanChi.getIdKhoanChi()).setValue(khoanChi);
                    Toast.makeText(getActivity(), "Insert " + editTextMoney.getText().toString() + " successful", Toast.LENGTH_SHORT).show();


                    editTextMoney.setText("");
                    textViewDate.setText("");
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = new Dialog(getActivity());
                khoanChiModel = (KhoanChi) khoanChiAdapter.getItem(position);
                String _idKhoanChi = khoanChiModel.getIdKhoanChi();
                dialog.setContentView(R.layout.dialog_khoanchi);
                TextView TitleKhoanChi = dialog.findViewById(R.id.textKhoanChiDiaLog);
                TextView DateKhoanChi = dialog.findViewById(R.id.textDateKhoanChiDiaLog);
                EditText Money = dialog.findViewById(R.id.editTextSoTienChiDiaLog);
                Button btnChonNgay = dialog.findViewById(R.id.ChonDateKhoanChiDiaLog);
                Button btnUpdate = dialog.findViewById(R.id.btn_Update_KhoanChiDiaLog);
                Button btnDelete = dialog.findViewById(R.id.btn_Delete_KhoanChiDiaLog);

                TitleKhoanChi.setText(khoanChiModel.getTheLoaiKhoanChi());
                DateKhoanChi.setText(khoanChiModel.getDateKhoanChi());
                Money.setText(khoanChiModel.getMoney());


                btnChonNgay.setOnClickListener(new View.OnClickListener() {
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
                                        DateKhoanChi.setText(dayOfMonth + "-" + month + "-" + year);
                                    }
                                }, nam, thang, ngay);
                        datePickerDialog.show();
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String money = Money.getText().toString();
                        String date = DateKhoanChi.getText().toString();
                        String title = TitleKhoanChi.getText().toString();
                        upDateData(_idKhoanChi, title, money, date);
                        Toast.makeText(getActivity(), "Update " + Money.getText().toString() + "$ successful", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                btnDelete.setOnClickListener(new View.OnClickListener() {
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
                                    deleteLoaiChi(_idKhoanChi);
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
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        return mview;
    }

    private void deleteLoaiChi(String idKhoanChi) {
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("KhoanChi").child(idKhoanChi);
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

    private void upDateData(String idKhoanChi, String title, String money, String date) {
        DatabaseReference Dbref = FirebaseDatabase.getInstance().getReference("KhoanChi").child(idKhoanChi);
        KhoanChi khoanChi = new KhoanChi(idKhoanChi, title, money, date);
        Dbref.setValue(khoanChi);
    }

    private void SpinnerKhoanChi() {
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("LoaiChi");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loaiChiList.clear();
                for (DataSnapshot LoaiChiDatasnaps : snapshot.getChildren()) {
                    LoaiChi loaiChi = LoaiChiDatasnaps.getValue(LoaiChi.class);
                    loaiChiList.add(loaiChi);
                }
                spinnerKhoanChiAdapter = new SpinnerKhoanChiAdapter(loaiChiList, getActivity());
                spinner.setAdapter(spinnerKhoanChiAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
