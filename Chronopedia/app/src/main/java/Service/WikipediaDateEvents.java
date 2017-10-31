package Service;

import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Ludwig on 2017-10-31.
 */

public class WikipediaDateEvents extends AsyncTask<Void, Void, Void> {
    String event;
    TextView wikiTextView;

    public WikipediaDateEvents(TextView wikiTextView){
        this.wikiTextView = wikiTextView;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/" + getMonth()+ "_" + getDate()).get();
            ArrayList<String> events = new ArrayList<>();
            Elements lists = doc.select("li");
            for (Element list : lists ){
                events.add(list.text());
            }
            event = events.get(randomInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        wikiTextView.setText(event);
    }

    private int randomInt(){
        int randomNum = ThreadLocalRandom.current().nextInt(20, 45 + 1);
        return randomNum;
    }

    private String getMonth(){
        Calendar mCalendar = Calendar.getInstance();
        String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        return month;
    }

    private String getDate(){
        Date d = new Date();
        String dateWithoutTime = d.toString().substring(8, 10);
        return dateWithoutTime;
    }
}
