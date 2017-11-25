package com.example.valeriajara.umbrella;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class APICaller{

    private static String rainfall;
    private static String city;

    public APICaller(String city){
        this.city = city;
    }

    // makes API call and returns the daily inches of rainfall in format #.##
    public String getWeather() {
        callAPI();
        return rainfall;
    }


    public static void callAPI(){
      Thread thread = new Thread(new Runnable() {
      String text = "";
        @Override
        public void run() {
          try  {
            try {
              Document doc = Jsoup.connect("https://api.wunderground.com/api/d59e8a61df5f52ab/conditions/q/TX/" + city + ".json").ignoreContentType(true).get();
              text = doc.body().text();
              String[] split = text.split("precip_today_in");
              String temp = split[1];
              rainfall = "Rainfall: " + temp.substring(3, 8);
              Log.d("Rainfall (inches): ", rainfall); //uncomment this and logcat will show the string (for debugging)
                } catch (IOException e) {
                   e.printStackTrace();
                  }
                } catch (Exception e) {
                    e.printStackTrace();
                  }
        }
      });
      thread.start();
    }

}
