package Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Adapter.PhongAdapter;
import DAO.PhongDAO;
import Model.Phong;
import longvtph16016.poly.appquanlyphongtro.R;
import longvtph16016.poly.appquanlyphongtro.interfaceDeleteClickdistioner;


public class PhongFragment extends Fragment implements interfaceDeleteClickdistioner {

    EditText edt_themsophong,edt_themgiaphong,edt_themgiadien,edt_themgianuoc,edt_themgiawifi,edt_trangthai;
    Button btn_themphong,btn_huyphong;
    FloatingActionButton fab;
    ListView rcv_phong;

    PhongAdapter phongAdapter;
    private PhongDAO phongDAO;
    private ArrayList<Phong> list = new ArrayList<>();
    Context context;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab_addPhong);
        rcv_phong = view.findViewById(R.id.rec_Phong);

        context = this.getActivity();

        phongDAO = new PhongDAO(context);
        list = (ArrayList<Phong>) phongDAO.getAll();
        phongAdapter = new PhongAdapter(context,this::OnClickDelete);
        phongAdapter.setData(list);
        rcv_phong.setAdapter(phongAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getContext());
            }
        });
    }



    private void openDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogthemphong, null);

        edt_themsophong = view.findViewById(R.id.edt_themsophong);
        edt_themgiaphong = view.findViewById(R.id.edt_themgiaphong);
        edt_themgiadien = view.findViewById(R.id.edt_themgiadien);
        edt_themgianuoc = view.findViewById(R.id.edt_themgianuoc);
        edt_themgiawifi = view.findViewById(R.id.edt_themgiawifi);

        btn_themphong = view.findViewById(R.id.btn_them_phong);
        btn_huyphong = view.findViewById(R.id.btn_huy_phong);

        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        btn_themphong.setOnClickListener(new View.OnClickListener() {
            Phong phong;

            @Override
            public void onClick(View view) {

                    phong = new Phong();
                    phong.setSoPhong(Integer.parseInt(edt_themsophong.getText().toString()));
                    phong.setGiaPhong(Integer.parseInt(edt_themgiaphong.getText().toString()));
                    phong.setGiaDien(Integer.parseInt(edt_themgiadien.getText().toString()));
                    phong.setGiaNuoc(Integer.parseInt(edt_themgianuoc.getText().toString()));
                    phong.setGiaWifi(Integer.parseInt(edt_themgiawifi.getText().toString()));
                    phong.setTrangThai("chua thue");
                    phongDAO = new PhongDAO(context);
                    phongDAO.insertPhong(phong);
                    Toast.makeText(context,"thêm mới thành công",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    list.clear();
                    list.addAll(phongDAO.getAll());
                    phongAdapter.notifyDataSetChanged();
            }
        });
        btn_huyphong
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phong, container, false);
    }


    @Override
    public void OnClickDelete(int index) {
        deletedialog(index);
    }
    public void deletedialog(int index){
        androidx.appcompat.app.AlertDialog.Builder builder= new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle("bạn có chắc chắn muốn xóa không?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(phongDAO.deletePhong(list.get(index))>0){
                    list.remove(index);
                    phongAdapter.setData(list);
                    Toast.makeText(context,"xoa thành công",
                            Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(context,"xóa không thành công",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

}