package commaciejprogramuje.facebook.dict;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

import commaciejprogramuje.facebook.dict.R;

/**
 * Implementation of App Widget functionality.
 */
public class DictWidget extends AppWidgetProvider {
    public static final String LETTER_KEY = "letter";
    String number2 = "start";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.dict_widget);
            remoteViews.setTextViewText(R.id.appwidget_text, number2);

            remoteViews.setOnClickPendingIntent(R.id.buttonA, getMyPendingIntent(context, appWidgetIds, "A", 1));
            remoteViews.setOnClickPendingIntent(R.id.buttonB, getMyPendingIntent(context, appWidgetIds, "B", 2));

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    private PendingIntent getMyPendingIntent(Context context, int[] appWidgetIds, String letter, int numOfLetter) {
        Intent intentA = new Intent(context, DictWidget.class);
        intentA.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intentA.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        intentA.putExtra(LETTER_KEY, letter);

        return PendingIntent.getBroadcast(context, numOfLetter, intentA, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle b = intent.getExtras();
        try {
            number2 = b.getString(LETTER_KEY);
        } catch (Exception e) {
            Toast.makeText(context, "problem", Toast.LENGTH_SHORT).show();
        }

        super.onReceive(context, intent);
    }

}

