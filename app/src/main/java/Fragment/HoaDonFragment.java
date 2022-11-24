package Fragment;

import static android.R.layout.simple_spinner_dropdown_item;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import Adapter.HoaDonAdapter;
import Adapter.PhongAdapter;
import DAO.HoaDonDao;
import DAO.PhongDAO;
import Model.HoaDon;
import Model.Phong;
import longvtph16016.poly.appquanlyphongtro.R;
import longvtph16016.poly.appquanlyphongtro.interfaceDeleteClickdistioner;


public class HoaDonFragment extends Fragment implements interfaceDeleteClickdistioner{

    EditText  Ed_NgayTao_HDon, Ed_NhapSoDien_HDon, Ed_NhapSoNuoc_HDon, Ed_ChiPhiKhac_HDon,Ed_TongTien_HDon,Ed_GhiChu_HDon;
    Spinner  Ed_ChonSoPhong;
    Button Btn_huy_HDon, Btn_them_HDon;
    FloatingActionButton fab;
    ListView rcv_hoadon;
    ImageView image_ngay;

    HoaDonAdapter HoaDonAdapter;
    private HoaDonDao HoaDonDao;
    private ArrayList<HoaDon> list = new ArrayList<>();
    Context context;
    PhongDAO phongDAO;
    Phong khachHangGet = new Phong();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab_addHoaDon);
        rcv_hoadon = view.findViewById(R.id.rec_HoaDon);
        context = this.getActivity();
        HoaDonDao = new HoaDonDao(context);
        list = (ArrayList<HoaDon>) HoaDonDao.getAll();
        HoaDonAdapter = new HoaDonAdapter(context, this::OnClickDelete);
        HoaDonAdapter.setData(list);
        rcv_hoadon.setAdapter(HoaDonAdapter);


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
        View view = inflater.inflate(R.layout.dialog_tao_hoa_don, null);

        Ed_ChonSoPhong = view.findViewById(R.id.ed_ChonSoPhong);
        Ed_NgayTao_HDon = view.findViewById(R.id.ed_NgayTao_HDon);
        Ed_NhapSoDien_HDon = view.findViewById(R.id.ed_NhapSoDien_HDon);
        Ed_NhapSoNuoc_HDon = view.findViewById(R.id.ed_NhapSoNuoc_HDon);
        Ed_ChiPhiKhac_HDon = view.findViewById(R.id.ed_ChiPhiKhac_HDon);
        Ed_TongTien_HDon = view.findViewById(R.id.ed_TongTien_HDon);
        Ed_GhiChu_HDon = view.findViewById(R.id.ed_GhiChu_HDon);
        image_ngay= view.findViewById(R.id.image_NgayTao_HDon);
        Btn_them_HDon= view.findViewById(R.id.btn_them_HDon);
        Btn_huy_HDon= view.findViewById(R.id.btn_huy_HDon);

        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        ArrayList<String> listSpiner = new ArrayList<>();
        ArrayList<Phong> khachHangs = phongDAO.getALLKH();
        if (khachHangs.size() <= 0) {
            Toast.makeText(context, "Vui Lòng Thêm khách hàng", Toast.LENGTH_SHORT).show();
        }
        if (khachHangs.size() > 0) {
            khachHangGet = khachHangs.get(1);
        }

        for (int i = 0; i < khachHangs.size(); i++) {
            listSpiner.add(String.valueOf(khachHangs.get(i).getSoPhong()));
        }
        ArrayAdapter<String> ad = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listSpiner);
        Ed_ChonSoPhong.setAdapter(ad);
        Ed_ChonSoPhong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                khachHangGet = khachHangs.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //---------------- an voa anh chon ngay
        Calendar calendar = Calendar.getInstance();//Lay time
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);
        image_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        Ed_NgayTao_HDon.setText(d + "/" + (m + 1) + "/" + y);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        Btn_them_HDon.setOnClickListener(new View.OnClickListener() {
            HoaDon HoaDon;

            @Override
            public void onClick(View view) {
                boolean check = true;

                //---------- check so phong

                int sohoadon = 0;

//                //-------------------check ngay
                String ngays = Ed_NgayTao_HDon.getText().toString();
                if (ngays.length() == 0) {
                    Toast.makeText(context, "Ngày không được để trống", Toast.LENGTH_SHORT).show();
                    check = false;
                }


                //-------------------check gia dien
                String giadienS = Ed_NhapSoDien_HDon.getText().toString();
                int giadien = 0;

                if (giadienS.length() == 0) {
                    Toast.makeText(context, "Giá điện không được để trống", Toast.LENGTH_SHORT).show();
                    check = false;
                } else {
                    try {
                        giadien = Integer.parseInt(giadienS);
                        if (giadien < 0) {
                            Toast.makeText(context, "Giá điện phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } catch (Exception e) {
                        Toast.makeText(context, "Giá điện phải là số nguyên", Toast.LENGTH_SHORT).show();
                        check = false;
                    }

                }

                //-------------------check gia nuoc
                String gianuocS = Ed_NhapSoNuoc_HDon.getText().toString();
                int gianuoc = 0;

                if (gianuocS.length() == 0) {
                    Toast.makeText(context, "Giá nước không được để trống", Toast.LENGTH_SHORT).show();
                    check = false;
                } else {
                    try {
                        gianuoc = Integer.parseInt(gianuocS);
                        if (gianuoc < 0) {
                            Toast.makeText(context, "Giá nước phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } catch (Exception e) {
                        Toast.makeText(context, "Giá nước phải là số nguyên", Toast.LENGTH_SHORT).show();
                        check = false;
                    }

                }

                //-------------------check chi phi khac
                String chiphikhacs = Ed_ChiPhiKhac_HDon.getText().toString();
                int chiphikhac = 0;

                if (chiphikhacs.length() == 0) {
                    Toast.makeText(context, "Chi phí khác không được để trống", Toast.LENGTH_SHORT).show();
                    check = false;
                } else {
                    try {
                        chiphikhac = Integer.parseInt(chiphikhacs);
                        if (gianuoc < 0) {
                            Toast.makeText(context, "Chi Phí khác phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } catch (Exception e) {
                        Toast.makeText(context, "Chi phí khác phải là số nguyên", Toast.LENGTH_SHORT).show();
                        check = false;
                    }

                }

                //Tổng tiền
                String tongtiens = Ed_TongTien_HDon.getText().toString();
                int tongtien = 0;

                if (tongtiens.length() == 0) {
                    Toast.makeText(context, "Tổng tiền không được để trống", Toast.LENGTH_SHORT).show();
                    check = false;
                } else {
                    try {
                        tongtien = Integer.parseInt(tongtiens);
                        if (tongtien < 0) {
                            Toast.makeText(context, "Tổng tiền phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } catch (Exception e) {
                        Toast.makeText(context, "Tổng tiền phải là số nguyên", Toast.LENGTH_SHORT).show();
                        check = false;
                    }

                }
                //ghi chu
                String ghichus = Ed_TongTien_HDon.getText().toString();


                if (check == true) {

                    if (HoaDonDao.insertHoaDon(sohoadon,ngays,giadien,gianuoc,chiphikhac,tongtien,ghichus)) {
                        Toast.makeText(context, "thêm mới thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        list.clear();
                        list.addAll(HoaDonDao.getAll());
                       HoaDonAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, "thêm mới k thành công", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });


        Btn_huy_HDon.setOnClickListener(new View.OnClickListener() {
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
        return inflater.inflate(R.layout.fragment_hoa_don, container, false);
    }


    @Override
    public void OnClickDelete(int index) {
        deletedialog(index);
    }

    public void deletedialog(int index) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle("bạn có chắc chắn muốn xóa không?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (HoaDonDao.deleteHoaDon(list.get(index)) > 0) {
                    list.remove(index);
                    HoaDonAdapter.setData(list);
                    Toast.makeText(context, "xóa thành công",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "xóa không thành công",
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


//------------------------------------

}
