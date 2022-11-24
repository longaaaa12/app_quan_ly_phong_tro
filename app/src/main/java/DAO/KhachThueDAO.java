package DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Database.DbHelper;
import Model.*;

public class KhachThueDAO {
    private SQLiteDatabase db;

    public KhachThueDAO(Context mContext) {
        DbHelper dbHelper = new DbHelper(mContext);
        db= dbHelper.getWritableDatabase();
    }

    //insert
    public long insertKhachThue(ObjectKhachThue khachThue){
        ContentValues values = new ContentValues();
        values.put("SoPhong",khachThue.getSoPhong());
        values.put("HoTen",khachThue.getHoTen());
        values.put("SoDienThoai",khachThue.getSoDienThoai());
        values.put("Cccd",khachThue.getCccd());
        return db.insert("KhachThue",null,values);

    }

    //delete by object
    public int deleteKhachThue(ObjectKhachThue obj){
        String Id = String.valueOf(obj.getIdKhachThue());
        return db.delete("KhachThue","IdKhachThue=?",new String[]{Id});
    }

    //update
    public int updateKhachThue(KhachThue obj){
        ContentValues values = new ContentValues();
        values.put("IdKhachThue",obj.getIdKhachThue());
        values.put("HoTen",obj.getHoTen());
        values.put("SoDienThoai",obj.getSoDienThoai());
        values.put("Cccd",obj.getCccd());
        return db.update("KhachThue",values,"IdKhachThue=?",new String[]{});
    }
    //getAll
    public List<ObjectKhachThue> getAll(){
        String sql="SELECT * FROM KhachThue";
        return getData(sql);
    }
    //get user by id
    public ObjectKhachThue getPhongById(String Id){
        String sql="SELECT * FROM KhachThue WHERE IdKhachThue=?";
        List<ObjectKhachThue> list = getData(sql,Id);
        if(list!=null){
            return list.get(0);
        }
        return null;
    }
    @SuppressLint("Range")
    public List<ObjectKhachThue>getData(String sql, String...SelectArgs){
        List<ObjectKhachThue> list= new ArrayList<>();
        Cursor cursor= db.rawQuery(sql,SelectArgs);
        while (cursor.moveToNext()){
            ObjectKhachThue user = new ObjectKhachThue();
            user.setIdKhachThue(cursor.getInt(cursor.getColumnIndex("IdKhachThue")));
            user.setSoPhong(cursor.getInt(cursor.getColumnIndex("SoPhong")));
            user.setHoTen(String.valueOf(cursor.getInt(cursor.getColumnIndex("HoTen"))));
            user.setSoDienThoai(cursor.getInt(cursor.getColumnIndex("SoDienThoai")));
            user.setCccd(cursor.getInt(cursor.getColumnIndex("Cccd")));
            list.add(user);
        }
        if(list!=null||list.size()!=0){
            return list;
        }
        return null;
    }
}
