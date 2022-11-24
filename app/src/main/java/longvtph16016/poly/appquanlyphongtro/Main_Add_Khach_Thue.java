package longvtph16016.poly.appquanlyphongtro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.KhachThueAdapter;
import Adapter.PhongAdapter;
import DAO.KhachThueDAO;
import Model.KhachThue;
import Model.ObjectKhachThue;
import Model.Phong;

public class Main_Add_Khach_Thue extends AppCompatActivity {
    Context context;
    KhachThueDAO khachThueDAO;
    KhachThueAdapter khachThueAdapter;
    ArrayList<ObjectKhachThue> list = new ArrayList<>();
    EditText edsophong,edtenKT,edSdt,edCccd;
    Button btnCancel,btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_khach_thue);

         edsophong = findViewById(R.id.tv_SoPhong_ThemKhachThue);
         edtenKT = findViewById(R.id.edTenKT);
         edSdt = findViewById(R.id.edSdt);
         edCccd = findViewById(R.id.edCccd);
         btnCancel = findViewById(R.id.btnCancel);
         btnSave = findViewById(R.id.btnSave);

         context = this.getApplicationContext();
         khachThueDAO = new KhachThueDAO(context);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    ObjectKhachThue khach = new ObjectKhachThue();
                    khach.setSoPhong(Integer.parseInt(edsophong.getText().toString()));
                    khach.setHoTen(edtenKT.getText().toString());
                    khach.setSoDienThoai(Integer.parseInt(edSdt.getText().toString()));
                    khach.setCccd(Integer.parseInt(edCccd.getText().toString()));
                    if (khachThueDAO.insertKhachThue(khach)>0){
                        Toast.makeText(context, "thêm mới thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(khachThueDAO.getAll());
                    }else {
                        Toast.makeText(context, "thêm mới k thành công", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}