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
            String number = String.format("%03d", (new Random().nextInt(900) + 100));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.dict_widget);
            remoteViews.setTextViewText(R.id.appwidget_text, number2);

            Intent intentA = new Intent(context, DictWidget.class);
            intentA.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intentA.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            intentA.putExtra(LETTER_KEY, "A");

            PendingIntent pendingIntentA = PendingIntent.getBroadcast(context, 0, intentA, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.buttonA, pendingIntentA);



            Intent intentB = new Intent(context, DictWidget.class);
            intentB.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intentB.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            intentB.putExtra(LETTER_KEY, "B");

            PendingIntent pendingIntentB = PendingIntent.getBroadcast(context, 1, intentB, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.buttonB, pendingIntentB);


            //remoteViews.setOnClickPendingIntent(R.id.buttonA, getPendingSelfIntent(context, MyOnClickA));
            //remoteViews.setOnClickPendingIntent(R.id.buttonB, getPendingSelfIntent(context, MyOnClickB));

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
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

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}

