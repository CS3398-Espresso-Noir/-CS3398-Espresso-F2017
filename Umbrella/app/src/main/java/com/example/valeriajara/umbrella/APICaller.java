package com.example.valeriajara.umbrella;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class APICaller{

    private static String rainfallSM;
    private static String rainfallAustin;
    private static String rainfallSA;
    private static String rainfallHouston;

    // makes API call and returns the daily inches of rainfall in format #.##
    public String getSanMarcos() {
        sanMarcos();
        return rainfallSM;
    }
    public String getAustin() {
        austin();
        return rainfallAustin;
    }
    public String getHouston() {
        houston();
        return rainfallHouston;
    }
    public String getSanAntonio() {
        sanAntonio();
        return rainfallSA;
    }

    public static void sanMarcos(){
      Thread thread = new Thread(new Runnable() {
      String text = "";
        @Override
        public void run() {
          try  {
            try {
              Document doc = Jsoup.connect("https://api.wunderground.com/api/d59e8a61df5f52ab/conditions/q/TX/San_Marcos.json").ignoreContentType(true).get();
              text = doc.body().text();
              String[] split = text.split("precip_today_in");
              String temp = split[1];
              rainfallSM = temp.substring(3, 8);
              Log.d("Rainfall (inches): ", rainfallSM); //uncomment this and logcat will show the string (for debugging)
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

    public static void sanAntonio(){
        Thread thread = new Thread(new Runnable() {
            String text = "";
            @Override
            public void run() {
                try  {
                    try {
                        Document doc = Jsoup.connect("https://api.wunderground.com/api/d59e8a61df5f52ab/conditions/q/TX/San_Antonio.json").ignoreContentType(true).get();
                        text = doc.body().text();
                        String[] split = text.split("precip_today_in");
                        String temp = split[1];
                        rainfallSA = temp.substring(3, 8);
                        Log.d("Rainfall (inches): ", rainfallSA); //uncomment this and logcat will show the string (for debugging)
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

    public static void houston(){
        Thread thread = new Thread(new Runnable() {
            String text = "";
            @Override
            public void run() {
                try  {
                    try {
                        Document doc = Jsoup.connect("https://api.wunderground.com/api/d59e8a61df5f52ab/conditions/q/TX/Houston.json").ignoreContentType(true).get();
                        text = doc.body().text();
                        String[] split = text.split("precip_today_in");
                        String temp = split[1];
                        rainfallHouston = temp.substring(3, 8);
                        Log.d("Rainfall (inches): ", rainfallHouston); //uncomment this and logcat will show the string (for debugging)
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

    public static void austin(){
        Thread thread = new Thread(new Runnable() {
            String text = "";
            @Override
            public void run() {
                try  {
                    try {
                        Document doc = Jsoup.connect("https://api.wunderground.com/api/d59e8a61df5f52ab/conditions/q/TX/Austin.json").ignoreContentType(true).get();
                        text = doc.body().text();
                        String[] split = text.split("precip_today_in");
                        String temp = split[1];
                        rainfallAustin = temp.substring(3, 8);
                        Log.d("Rainfall (inches): ", rainfallAustin); //uncomment this and logcat will show the string (for debugging)
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
