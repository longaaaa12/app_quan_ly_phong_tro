package DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Database.DbHelper;
import Model.HoaDon;
import Model.Phong;

public class HoaDonDao {
    private SQLiteDatabase db;

    public HoaDonDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert

    public long insertHoaDon(HoaDon hoaDon){
        ContentValues values = new ContentValues();
        values.put("Ngay",hoaDon.getNgay());
        values.put("SoDien",hoaDon.getSoDien());
        values.put("SoNuoc",hoaDon.getSoNuoc());
        values.put("Tong",hoaDon.getTong());
        values.put("ChiPhiKhac",hoaDon.getChiPhiKhac());
        values.put("GhiChu",hoaDon.getGhiChu());
        return db.insert("HoaDon",null,values);
    }

    //update
    public int updateHoaDon(HoaDon HoaDon){
        ContentValues values = new ContentValues();
        values.put("Ngay",HoaDon.getNgay());
        values.put("SoDien",HoaDon.getSoDien());
        values.put("SoNuoc",HoaDon.getSoNuoc());
        values.put("Tong",HoaDon.getTong());
        values.put("ChiPhiKhac",HoaDon.getChiPhiKhac());
        values.put("GhiChu",HoaDon.getGhiChu());
        String Id = String.valueOf(HoaDon.getIdPhong());
        return db.update("HoaDon",values,"IdHoaDon=?",new String[]{Id});
    }

    //delete by object
    public int deletePhong(HoaDon obj){
        String Id = String.valueOf(obj.getIdPhong());
        return db.delete("HoaDon","IdHoaDon=?",new String[]{Id});
    }

    //get user by id
    public HoaDon getHoaDonById(String IdHoaDon){
        String sql="SELECT * FROM Phong WHERE IdHoaDon=?";
        List<HoaDon> list = getData(sql,IdHoaDon);
        if(list != null){
            return list.get(0);
        }
        return null;
    }

    @SuppressLint("Range")
    public List<HoaDon>getData(String sql, String...SelectArgs){
        List<HoaDon> list= new ArrayList<>();
        Cursor cursor= db.rawQuery(sql,SelectArgs);
        while (cursor.moveToNext()){
            HoaDon hoaDon= new HoaDon();
            hoaDon.setIdPhong(cursor.getInt(cursor.getColumnIndex("IdPhong")));
            hoaDon.setNgay(String.valueOf(cursor.getInt(cursor.getColumnIndex("Ngay"))));
            hoaDon.setSoDien(cursor.getInt(cursor.getColumnIndex("SoDien")));
            hoaDon.setSoNuoc(cursor.getInt(cursor.getColumnIndex("SoNuoc")));
            hoaDon.setTong(cursor.getInt(cursor.getColumnIndex("Tong")));
            hoaDon.setGhiChu(String.valueOf(cursor.getInt(cursor.getColumnIndex("GhiChu"))));
            hoaDon.setChiPhiKhac(String.valueOf(cursor.getInt(cursor.getColumnIndex("ChiPhiKhac"))));
            list.add(hoaDon);
        }
        if(list!=null||list.size()!=0){
            return list;
        }
        return null;
    }
}
