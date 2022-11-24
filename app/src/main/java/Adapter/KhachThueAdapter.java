package Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;

import DAO.KhachThueDAO;
import Model.KhachThue;
import Model.ObjectKhachThue;
import longvtph16016.poly.appquanlyphongtro.R;
import longvtph16016.poly.appquanlyphongtro.interfaceDeleteClickdistioner;

public class KhachThueAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ObjectKhachThue> list;
    private interfaceDeleteClickdistioner interfaceDeleteClickdistioner;

    public KhachThueAdapter(Context context, interfaceDeleteClickdistioner interfaceDeleteClickdistioner) {
        this.context = context;
        this.interfaceDeleteClickdistioner = interfaceDeleteClickdistioner;
    }

    public void setData(ArrayList<ObjectKhachThue> arrayList){
        this.list= arrayList;
        notifyDataSetChanged();// có tác dụng refresh lại data
    }

    public final class MyViewHolder {
        //khai báo các thành phần view có trong layoutitem
        TextView tv_KhachThue,giaphong,giadien,gianuoc,giawifi,trangthai;

    }

    @Override
    public int getCount() {
        if(list!=null)
            return list.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder;
        if (view == null) {
            myViewHolder = new MyViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_khachthue, null);
            myViewHolder.tv_KhachThue = view.findViewById(R.id.txt_item_khachThue);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        LinearLayout ln_item_kt = view.findViewById(R.id.ln_menu_khachthue);
        myViewHolder.tv_KhachThue.setText("Khách Thuê: "+ list.get(i).getSoPhong());
        ln_item_kt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_bottom_khachthue);

                LinearLayout editLayout = dialog.findViewById(R.id.edt_update_kt);
                LinearLayout delete_layout = dialog.findViewById(R.id.edt_delete_kt);

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);

                delete_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        interfaceDeleteClickdistioner.OnClickDelete(i);
                        dialog.dismiss();
                    }
                });
            }
        });

        return view;
    }
}
