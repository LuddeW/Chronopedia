package Managers;

import android.widget.TextView;

import com.example.ludwig.chronopedia.R;

/**
 * Created by Ludwig on 2017-10-24.
 */

public class ApiManager {
    // hämtar apier från Wiki och Darksky
    String returnWeatherString = "Sun";
    String returnWikiString = "2003 – Concorde makes its last commercial flight." ;
    public String returnWeather(){

        return(returnWeatherString);
    }

    public String returnWikiQuote(){
        return (returnWikiString);
    }

}
