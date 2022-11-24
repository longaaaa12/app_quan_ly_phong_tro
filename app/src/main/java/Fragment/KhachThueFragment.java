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
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Adapter.KhachThueAdapter;
import Adapter.PhongAdapter;
import DAO.KhachThueDAO;
import DAO.PhongDAO;
import Model.KhachThue;
import Model.ObjectKhachThue;
import Model.Phong;
import longvtph16016.poly.appquanlyphongtro.R;
import longvtph16016.poly.appquanlyphongtro.interfaceDeleteClickdistioner;

public class KhachThueFragment extends Fragment  implements interfaceDeleteClickdistioner {
    FloatingActionButton fab;
    ListView rcv_khachThue;
    KhachThueAdapter khachThueAdapter;
    KhachThueDAO khachThueDAO;
    Context context;

    private ArrayList<ObjectKhachThue> list = new ArrayList<>();


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab_addKhachTHue);
        rcv_khachThue = view.findViewById(R.id.rec_KhachThue);
        context = this.getActivity();

        khachThueDAO = new KhachThueDAO(context);
        list = (ArrayList<ObjectKhachThue>) khachThueDAO.getAll();
        khachThueAdapter = new KhachThueAdapter(context,this::OnClickDelete);
        khachThueAdapter.setData(list);
        rcv_khachThue.setAdapter(khachThueAdapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_khach_thue, container, false);
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
                if(khachThueDAO.deleteKhachThue(list.get(index))>0){
                    list.remove(index);
                    khachThueAdapter.setData(list);
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