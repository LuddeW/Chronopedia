package Data;

import android.content.ContentValues;

import org.json.JSONObject;

/**
 * Created by Ludwig on 2017-10-30.
 */

public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition(){
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
    }
}
