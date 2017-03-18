package com.example.lks.aroundweather.gson;

/**
 * Created by lks on 2017/3/16.
 */

public class AQI {
    public AQICity city;

    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
