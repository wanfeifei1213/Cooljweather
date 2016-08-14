package com.example.cooljweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhangbingquan on 2016/8/14.
 */

public class CooljWeatherOpenHelper extends SQLiteOpenHelper {


    public static final String CREAT_PROVINCE = "creat table Province(id integer primary key autocrement,province_name text,province_code text)";

    public static final String CREAT_CITY = "creat table City(id integer primary key autocrement,city_name text,city_code text,province_id integer)";

    public static final String CREAT_COUNTY = "creat table County(id integer primary key autocrement,county_name text,county_code text,city_id integer)";


    public CooljWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREAT_PROVINCE); //建表
        db.execSQL(CREAT_CITY);
        db.execSQL(CREAT_COUNTY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
