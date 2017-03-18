package com.example.lks.aroundweather.util;

import android.text.TextUtils;

import com.example.lks.aroundweather.db.City;
import com.example.lks.aroundweather.db.County;
import com.example.lks.aroundweather.db.Province;
import com.example.lks.aroundweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lks on 2017/3/11.
 */

public class Utility {
    /*解析和处理服务器返回的省级数据*/
    public static boolean handleProvinceRespond(String respond){
        if(!TextUtils.isEmpty(respond)){
            try {
                JSONArray allProvince=new JSONArray(respond);
                for (int i = 0; i < allProvince.length(); i++) {
                    JSONObject provinceObject=allProvince.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();

                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    return false;
    }


    public static boolean handleCityRespond(String respond,int provinceId){
        if(!TextUtils.isEmpty(respond)){
            try {
                JSONArray allCity=new JSONArray(respond);
                for (int i = 0; i < allCity.length(); i++) {
                    JSONObject cityObject=allCity.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();

                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }



    public static boolean handleCountyRespond(String respond,int cityId){
        if(!TextUtils.isEmpty(respond)){
            try {
                JSONArray allCounty=new JSONArray(respond);
                for (int i = 0; i < allCounty.length(); i++) {
                    JSONObject countyObject=allCounty.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();

                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }



    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;


    }
}
