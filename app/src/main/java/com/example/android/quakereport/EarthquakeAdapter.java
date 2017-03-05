package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nexus on 28.02.2017.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private String offset = "";
    private String city = "";
    private final String DIVIDE = "of";

    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context     The current context. Used to inflate the layout file.
     * @param earthquakes A List of AndroidFlavor objects to display in a list
     */
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Earthquake currentEarthquakeInfo = getItem(position);

        DecimalFormat formatter = new DecimalFormat("0.0");


        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView powerTextView = (TextView) convertView.findViewById(R.id.magnitude);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        powerTextView.setText(String.valueOf(formatter.format(currentEarthquakeInfo.getPower())));

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView cityTextView = (TextView) convertView.findViewById(R.id.city);
        TextView offsetTextView = (TextView) convertView.findViewById(R.id.offset);

        if (currentEarthquakeInfo.getCity().indexOf(DIVIDE) != -1) {
            offset = currentEarthquakeInfo.getCity().substring(0, currentEarthquakeInfo.getCity().indexOf(DIVIDE) + 3);
            city = currentEarthquakeInfo.getCity().substring(currentEarthquakeInfo.getCity().indexOf(DIVIDE) + 3,
                    currentEarthquakeInfo.getCity().length());
        } else {
            city = currentEarthquakeInfo.getCity();
            offset = "Near the";
        }

        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView

        cityTextView.setText(city);
        offsetTextView.setText(offset);

        //String reportDate = df.format(currentEarthquakeInfo.getDate());
        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView dateDayMonthTextView = (TextView) convertView.findViewById(R.id.date_month_day);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView

        long timeInMillis = currentEarthquakeInfo.getDate();
        Date dateObject = new Date(timeInMillis);

        SimpleDateFormat dateMonthFormatter = new SimpleDateFormat("MMM DD, yyyy");
        SimpleDateFormat dateYearFormatter = new SimpleDateFormat("h:m a");

        String monthDayToDisplay = dateMonthFormatter.format(dateObject);
        String yearToDisplay = dateYearFormatter.format(dateObject);

        dateDayMonthTextView.setText(monthDayToDisplay);

        TextView dateYearTextView = (TextView) convertView.findViewById(R.id.date_year);
        dateYearTextView.setText(yearToDisplay);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) powerTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquakeInfo.getPower());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return convertView;
    }

    private int getMagnitudeColor(double power){
        int magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude1);
        int magnitude5Color = ContextCompat.getColor(getContext(), R.color.magnitude5);
        int magnitude9Color = ContextCompat.getColor(getContext(), R.color.magnitude9);
        int magni = (int) Math.floor(power);
        switch (magni){
            case 6:
                return magnitude1Color;
            case 7:
                return magnitude5Color;
            default:
                return magnitude9Color;
        }

    }
}
