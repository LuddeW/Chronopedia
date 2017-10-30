package Data;

import org.json.JSONObject;

/**
 * Created by Ludwig on 2017-10-30.
 */

public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature(){
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
    }
}
