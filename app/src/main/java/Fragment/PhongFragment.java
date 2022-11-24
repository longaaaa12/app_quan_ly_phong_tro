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

            @Override
            public void onClick(View view) {
                boolean check = true;

                //---------- check so phong
                String sop=edt_themsophong.getText().toString();
                int soPhong=0;

                if(sop.length()==0){
                    Toast.makeText(context,"Số phòng không được để trống",Toast.LENGTH_SHORT).show();
                    check=false;
                }
                else {
                    try {
                        soPhong = Integer.parseInt(sop);
                        if(soPhong<0){
                            Toast.makeText(context,"Số phòng phải lớn hơn 0",Toast.LENGTH_SHORT).show();
                            check=false;
                        }
                    }catch (Exception e){
                        Toast.makeText(context,"Số phòng phải là số nguyên",Toast.LENGTH_SHORT).show();
                        check=false;
                    }

                }

                try {
                    for (int i = 0;i<phongDAO.getAll().size();i++)
                    {
                        Phong phong_ = phongDAO.getAll().get(i);
                        if (soPhong == phong_.getSoPhong())
                        {
                            Toast.makeText(context, "Phòng "+soPhong+" đã có", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                }catch (Exception e){

                }

                //-------------------check gia phong
                String giaphongS=edt_themgiaphong.getText().toString();
                int giaphong=0;

                if(giaphongS.length()==0){
                    Toast.makeText(context,"Giá phòng không được để trống",Toast.LENGTH_SHORT).show();
                    check=false;
                }
                else {
                    try {
                        giaphong = Integer.parseInt(giaphongS);
                        if(giaphong<0){
                            Toast.makeText(context,"Giá phòng phải lớn hơn 0",Toast.LENGTH_SHORT).show();
                            check=false;
                        }
                    }catch (Exception e){
                        Toast.makeText(context,"Giá phòng phải là số nguyên",Toast.LENGTH_SHORT).show();
                        check=false;
                    }

                }

                //-------------------check gia dien
                String giadienS=edt_themgiadien.getText().toString();
                int giadien=0;

                if(giadienS.length()==0){
                    Toast.makeText(context,"Giá điện không được để trống",Toast.LENGTH_SHORT).show();
                    check=false;
                }
                else {
                    try {
                        giadien = Integer.parseInt(giadienS);
                        if(giadien<0){
                            Toast.makeText(context,"Giá điện phải lớn hơn 0",Toast.LENGTH_SHORT).show();
                            check=false;
                        }
                    }catch (Exception e){
                        Toast.makeText(context,"Giá điện phải là số nguyên",Toast.LENGTH_SHORT).show();
                        check=false;
                    }

                }

                //-------------------check gia dien
                String gianuocS=edt_themgianuoc.getText().toString();
                int gianuoc=0;

                if(gianuocS.length()==0){
                    Toast.makeText(context,"Giá nước không được để trống",Toast.LENGTH_SHORT).show();
                    check=false;
                }
                else {
                    try {
                        gianuoc = Integer.parseInt(gianuocS);
                        if(gianuoc<0){
                            Toast.makeText(context,"Giá nước phải lớn hơn 0",Toast.LENGTH_SHORT).show();
                            check=false;
                        }
                    }catch (Exception e){
                        Toast.makeText(context,"Giá nước phải là số nguyên",Toast.LENGTH_SHORT).show();
                        check=false;
                    }

                }

                //-------------------check gia dien
                String giawifiS=edt_themgianuoc.getText().toString();
                int giawifi=0;

                if(giawifiS.length()==0){
                    Toast.makeText(context,"Giá wifi không được để trống",Toast.LENGTH_SHORT).show();
                    check=false;
                }
                else {
                    try {
                        giawifi = Integer.parseInt(giawifiS);
                        if(gianuoc<0){
                            Toast.makeText(context,"Giá wifi phải lớn hơn 0",Toast.LENGTH_SHORT).show();
                            check=false;
                        }
                    }catch (Exception e){
                        Toast.makeText(context,"Giá wifi phải là số nguyên",Toast.LENGTH_SHORT).show();
                        check=false;
                    }

                }

                //--------
                String trangthai= "chưa thuê";
                edt_themgianuoc.setText(trangthai);

                if (check == true) {

                    if (phongDAO.insertPhong(soPhong,giaphong,giadien,gianuoc,giawifi,trangthai)){
                        Toast.makeText(context, "thêm mới thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        list.clear();
                        list.addAll(phongDAO.getAll());
                        phongAdapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(context, "thêm mới k thành công", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn_huyphong.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(context,"xóa thành công",
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