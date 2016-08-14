package com.example.cooljweather.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cooljweather.db.CooljWeatherOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by zhangbingquan on 2016/8/14.
 */

public class CooljweatherDB {

    /**
     * 数据库名
     */
    public static final String DB_NAME = "cooljweather";

    /**
     * 数据库版本
     */
    public static final int VERSION = 1;


    private static CooljweatherDB cooljweatherDB;

    private SQLiteDatabase db;

    /**
     * 构造函数私有化
     *
     * @param context
     */
    private CooljweatherDB(Context context) {


        CooljWeatherOpenHelper dbHelper = new CooljWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();

    }


    /**
     * 获取cooljWeatherDBs实例
     *
     * @param context
     * @return
     */
    public synchronized static CooljweatherDB getInstence(Context context) {

        if (cooljweatherDB == null) {
            cooljweatherDB = new CooljweatherDB(context);
        }
        return cooljweatherDB;
    }

    /**
     * 将Province实例存储到数据库
     *
     * @param province
     */
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("province_name", province.getProvinceName());
            contentValues.put("province_code", province.getProvinceCode());
            db.insert("province", null, contentValues);


        }
    }

    /**
     * 从数据库获取全国省份信息
     *
     * @return
     */
    public List<Province> loadProvince() {

        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);


            } while (
                    cursor.moveToNext()
                    );
        }
        return list;
    }

    /**
     * 将city实例存储到数据库中
     *
     * @param city
     */
    public void saveCity(City city) {
        if (city != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("city_name", city.getCityName());
            contentValues.put("city_code", city.getCityCode());
            contentValues.put("province_id", city.getProvinceId());
            db.insert("City", null, contentValues);
        }
    }


    /**
     * 从数据库获取某省下所有城市信息
     *
     * @param provinceId
     * @return
     */

    public List<City> loadCitise(int provinceId) {

        List<City> list = new ArrayList<City>();

        Cursor cursor = db.query("City", null, "province_id=?", new String[]{String.valueOf(provinceId)}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 将ounty实例存储到数据库
     *
     * @param county
     */
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("county_name", county.getCountyName());
            contentValues.put("county_code", county.getCountyCode());
            contentValues.put("city_id", county.getCityId());
            db.insert("County", null, null);
        }
    }


    public List<County> loadCounties(int cityId) {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County", null, "cityId=?", new String[]{String.valueOf(cityId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);

                list.add(county);
            } while (cursor.moveToNext());
        }
        return list;
    }


}



