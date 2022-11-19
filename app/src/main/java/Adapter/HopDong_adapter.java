package Adapter;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;

import DAO.HopDongDAO;
import Model.HopDong;
import Model.Phong;
import longvtph16016.poly.appquanlyphongtro.R;
import longvtph16016.poly.appquanlyphongtro.interfaceDeleteClickdistioner;

public class HopDong_adapter extends BaseAdapter {
    private Context context;
    private List<HopDong> list;


    public HopDong_adapter(Context context, List<HopDong> list  ) {
        this.context = context;
        this.list = list;

    }

    public void setData(List<HopDong> list){
        this.list= list;
        notifyDataSetChanged();// có tác dụng refresh lại data
    }
    public final class MyViewHolder {
        //khai báo các thành phần view có trong layoutitem
        private TextView tv_HopDong,giaphong,giadien,gianuoc,giawifi,trangthai;

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
        HopDong_adapter.MyViewHolder myViewHolder = null;
        if (view == null) {
            myViewHolder = new MyViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_hop_dong, null);
            myViewHolder.tv_HopDong = view.findViewById(R.id.txt_item_hopDong);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        CardView ln_item_dv = view.findViewById(R.id.cardview_hopDong);
        myViewHolder.tv_HopDong.setText("Hợp Đồng"+": "+list.get(i).getIdPhong());
        ln_item_dv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottom_layout_hdong);
                LinearLayout editLayout = dialog.findViewById(R.id.edit_layout_dh);
                LinearLayout detailLayout = dialog.findViewById(R.id.view_layout_hd);
                LinearLayout delete_layout = dialog.findViewById(R.id.delete_layout_hp);
                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);

                detailLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        detail();
                    }
                });
                delete_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        androidx.appcompat.app.AlertDialog.Builder builder= new androidx.appcompat.app.AlertDialog.Builder(context);
                        builder.setTitle("bạn có chắc chắn muốn xóa không?");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                if(phongDAO.deletePhong(list.get(index))>0){
//                                    list.remove(index);
//                                    phongAdapter.setData(list);
//                                    Toast.makeText(context,"xoa thành công",
//                                            Toast.LENGTH_LONG).show();
//                                }else {
//                                    Toast.makeText(context,"xóa không thành công",
//                                            Toast.LENGTH_LONG).show();
//                                }
                                dialogInterface.dismiss();

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
                });
            }
        });


        return view;
    }
    public void detail() {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialog.setContentView(R.layout.detail_hop_dong);
        dialog.show();
    }
}
