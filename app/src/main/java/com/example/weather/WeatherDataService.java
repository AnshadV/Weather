package com.example.weather;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    public static final String QUERY_FOR_GEO_BY_ZIP = "https://api.openweathermap.org/geo/1.0/zip?zip=";
    public static final String QUERY_FOR_FORECAST = "https://api.openweathermap.org/data/2.5/forecast?q=";

    Context context;
    String cityName;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String cityName);
    }

    public void geoByZip(String zipcode, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_GEO_BY_ZIP + zipcode + "&appid=9fdc025749075b14a010ae87218f707c";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                cityName = "";
                try {
                    cityName = response.getString("name");
                } catch (JSONException e) {

                }
                //Toast.makeText(context, cityName, Toast.LENGTH_SHORT).show();
                volleyResponseListener.onResponse(cityName);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something wrong");

            }
        });


        MySingleton.getInstance(context).addToRequestQueue(request);

        //return cityName;
    }

    public interface ForecastByIDResponse {
        void onError(String message);

        void onResponse(List<WeatherReportModel> weatherReportModels);
    }

    public void getCityForecastByID(String cityName, ForecastByIDResponse forecastByIDResponse) {
        String url = QUERY_FOR_FORECAST + cityName + "&appid=9fdc025749075b14a010ae87218f707c";
        List<WeatherReportModel> weatherReportModels = new ArrayList<>();

        //get the json object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(context, response.toString(),Toast.LENGTH_SHORT).show();

                try {
                    JSONArray objlist =response.getJSONArray("list");


                    for(int i=0; i< objlist.length(); i++) {
                        WeatherReportModel one_day = new WeatherReportModel();
                        JSONObject obj = objlist.getJSONObject(i);
                        one_day.setDate(obj.getString("dt_txt"));
                        JSONObject first_day_from_api = (JSONObject) objlist.get(i);


                        JSONObject main = first_day_from_api.getJSONObject("main");
                        one_day.setTemp(main.getLong("temp"));
                        one_day.setTemp_min(main.getLong("temp_min"));
                        one_day.setTemp_max(main.getLong("temp_max"));
                        one_day.setPressure((int) main.getLong("pressure"));
                        one_day.setHumidity((int) main.getLong("humidity"));


                        JSONArray weather = first_day_from_api.getJSONArray("weather");
                        JSONObject weather_obj = weather.getJSONObject(0);
                        one_day.setWeather_desc(weather_obj.getString("description"));
                        one_day.setWeather_main(weather_obj.getString("main"));


                        weatherReportModels.add(one_day);
                    }
                    forecastByIDResponse.onResponse(weatherReportModels);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

                //get the property called list which is an array

                //get each item in the array and assign it to a ne weather

        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
