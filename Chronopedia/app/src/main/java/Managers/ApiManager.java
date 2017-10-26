package Managers;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ludwig.chronopedia.R;

/**
 * Created by Ludwig on 2017-10-24.
 */

public class ApiManager {
    // hämtar apier från Wiki och Darksky
    String returnWeatherString = "Sun";
    String returnWikiString = "2003 – Concorde makes its last commercial flight." ;
    //RequestQueue queue = Volley.newRequestQueue(//Some kind of context here);
    String url ="http://www.google.com";

    public String returnWeather(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        returnWeatherString = "Response is: "+ response.substring(0,500);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                returnWeatherString = "That didn't work!";
            }
        });
        return(returnWeatherString);
    }

    public String returnWikiQuote(){
        return (returnWikiString);
    }

}
