package com.example.ludwig.chronopedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Freddie on 2017-10-29.
 */

public class AlarmAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public AlarmAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_alarm_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.tvAlarm);
        textView.setText(values[position]);

        return rowView;
    }
}