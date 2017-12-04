package com.example.valeriajara.umbrella;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Objects;

public class APICaller{

    private static String rainfall;
    private static String temperatureF;
    private static int condition;
    private static String city;

    public APICaller(String city){
        this.city = city;
        this.condition = 0;
    }

    public String getRainfall() { //CALLAPI MUST NOW BE CALLED BEFORE GETTERS FOR VALIDITY
        return rainfall;
    }

    public String getTemperatureF() { //CALLAPI MUST NOW BE CALLED BEFORE GETTERS FOR VALIDITY
        return temperatureF;
    }

    public int getCondition() { //CALLAPI MUST NOW BE CALLED BEFORE GETTERS FOR VALIDITY
        return condition;
    }

    public static void callAPI() {
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
                        Log.d("Rainfall (inches): ", rainfall);

                        split = text.split("temp_f");
                        temp = split[1];

                        temperatureF = "Temperature: " + temp.substring(2, 8);
                        if (Objects.equals(temp.substring(8,9), ","))
                            temperatureF = "Temperature: " + temp.substring(2, 7);
                        Log.d("Temperature (F): ", temperatureF);

                        split = text.split("\"icon\"");
                        temp = split[1];

                        String temp2 = temp.substring(2,7);
                        if (Objects.equals(temp2,"clear")) {
                            condition = 1;
                            Log.d("condition: ", ""+condition);
                            return;
                        }
                        temp2 = temp.substring(2,8);
                        if (Objects.equals(temp2, "cloudy")) {
                            condition = 2;
                            Log.d("condition: ", ""+condition);
                            return;
                        }
                        temp2 = temp.substring(2,10);
                        if (Objects.equals(temp2, "flurries")) {
                            condition = 3;
                            Log.d("condition: ", ""+condition);
                            return;
                        }
                        temp2 = temp.substring(2,5);
                        if (Objects.equals(temp2, "fog")) {
                            condition = 4;
                            Log.d("condition: ", ""+condition);
                            return;
                        }
                        temp2 = temp.substring(2,6);
                        if (Objects.equals(temp2, "hazy")) {
                            condition = 5;
                            Log.d("condition: ", ""+condition);
                            return;
                        }
                        temp2 = temp.substring(2,14);
                        if (Objects.equals(temp2, "mostlycloudy")) {
                            condition = 6;
                            Log.d("condition: ", ""+condition);
                            return;
                        }
                        temp2 = temp.substring(2,13);
                        if (Objects.equals(temp2, "mostlysunny")) {
                            condition = 7;
                            Log.d("condition: ", ""+condition);
                            return;
                        }
                        temp2 = temp.substring(2,14);
                        if (Objects.equals(temp2, "partlycloudy")) {
                            condition = 8;
                            Log.d("condition: ", ""+condition);
                            return;
                        }
                        temp2 = temp.substring(2,13);
                        if (Objects.equals(temp2, "partlysunny")) {
                            condition = 9;
                            Log.d("condition: ", ""+condition);
                            return;
                        }
                        temp2 = temp.substring(2,7);
                        if (Objects.equals(temp2, "sleet")) {
                            condition = 10;
                            Log.d("condition: ", ""+condition);
                            return;
                        }
                        temp2 = temp.substring(2,6);
                        if (Objects.equals(temp2, "rain")) {
                            condition = 11;
                            Log.d("condition: ", ""+condition);
                            return;
                        }
                        temp2 = temp.substring(2,6);
                        if (Objects.equals(temp2, "snow")) {
                            condition = 12;
                            Log.d("condition: ", ""+condition);
                            return;
                        }
                        temp2 = temp.substring(2,7);
                        if (Objects.equals(temp2, "sunny")) {
                            condition = 13;
                            Log.d("condition: ", ""+condition);
                            return;
                        }
                        temp2 = temp.substring(2,9);
                        if (Objects.equals(temp2, "tstorms")) {
                            condition = 14;
                            Log.d("condition: ", ""+condition);
                            return;
                        }
                        temp2 = temp.substring(2,9);
                        if (Objects.equals(temp2, "unknown")) {
                            condition = 15;
                            Log.d("condition: ", ""+condition);
                            return;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
