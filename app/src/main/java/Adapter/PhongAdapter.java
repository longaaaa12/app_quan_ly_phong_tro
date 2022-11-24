package Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import DAO.KhachThueDAO;
import Model.ObjectKhachThue;
import Model.Phong;
import longvtph16016.poly.appquanlyphongtro.R;
import longvtph16016.poly.appquanlyphongtro.interfaceDeleteClickdistioner;

public class PhongAdapter extends BaseAdapter  {
    Context context;
    private ArrayList<Phong> list;
    ArrayList<ObjectKhachThue> listKhachThue = new ArrayList<>();
    private interfaceDeleteClickdistioner interfaceDeleteClickdistioner;
    KhachThueDAO khachThueDAO;
    KhachThueAdapter khachThueAdapter;


    public PhongAdapter(Context context, interfaceDeleteClickdistioner interfaceDeleteClickdistioner) {
        this.context = context;
        this.interfaceDeleteClickdistioner = interfaceDeleteClickdistioner;
    }

    public void setData(ArrayList<Phong> arrayList){
        this.list= arrayList;
        notifyDataSetChanged();// có tác dụng refresh lại data
    }

    public final class MyViewHolder {
        //khai báo các thành phần view có trong layoutitem
        private TextView tv_sophong,giaphong,giadien,gianuoc,giawifi,trangthai;

    }
    public int getCount() {
        if(list!=null)
            return list.size();
        return 0;
    }
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder = null;
        if (view == null) {
            myViewHolder = new MyViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_phong, null);
            myViewHolder.tv_sophong = view.findViewById(R.id.tv_ssophong);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        LinearLayout ln_item_dv = view.findViewById(R.id.ln_menu_phong);
        myViewHolder.tv_sophong.setText("Phòng"+": "+list.get(i).getSoPhong());

        ln_item_dv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_bottom_phong);

                LinearLayout editLayout = dialog.findViewById(R.id.edt_update_dv);
                LinearLayout delete_layout = dialog.findViewById(R.id.edt_delete_dv);
                LinearLayout ThemKhachThue = dialog.findViewById(R.id.edt_ThemKhachThue);

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);

                ThemKhachThue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                        View view = inflater.inflate(R.layout.themkhachthue_dialog, null);
                        TextView edsophong = view.findViewById(R.id.tv_SoPhong_ThemKhachThu);
                        EditText edtenKT = view.findViewById(R.id.edTenK);
                        EditText edSdt = view.findViewById(R.id.edSd);
                        EditText edCccd = view.findViewById(R.id.edCcc);
                        Button btnCancel = view.findViewById(R.id.btnCance);
                        Button btnSave = view.findViewById(R.id.btnSav);

                        edsophong.setText(""+list.get(i).getSoPhong());

                        builder.setView(view);
                        Dialog dialog = builder.create();
                        dialog.show();

                        btnSave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                khachThueDAO = new KhachThueDAO(context);
                                ObjectKhachThue khach = new ObjectKhachThue();
                                khach.setSoPhong(Integer.parseInt(edsophong.getText().toString()));
                                khach.setHoTen(edtenKT.getText().toString());
                                khach.setSoDienThoai(Integer.parseInt(edSdt.getText().toString()));
                                khach.setCccd(Integer.parseInt(edCccd.getText().toString()));
                                if (khachThueDAO.insertKhachThue(khach)>0){
                                    Toast.makeText(context, "thêm mới thành công", Toast.LENGTH_SHORT).show();
                                    listKhachThue.clear();
                                    listKhachThue.addAll(khachThueDAO.getAll());
                                    dialog.dismiss();

                                }else {
                                    Toast.makeText(context, "thêm mới k thành công", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                });

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
