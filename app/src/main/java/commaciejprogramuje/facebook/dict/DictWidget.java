package commaciejprogramuje.facebook.dict;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

import commaciejprogramuje.facebook.dict.R;

/**
 * Implementation of App Widget functionality.
 */
public class DictWidget extends AppWidgetProvider {
    String number2 = "start";
    private static final String MyOnClickA = "myOnClickA";
    private static final String MyOnClickB = "myOnClickB";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            String number = String.valueOf(new Random().nextInt(100));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.dict_widget);
            remoteViews.setTextViewText(R.id.appwidget_text, number);

            Intent intent = new Intent(context, DictWidget.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            remoteViews.setOnClickPendingIntent(R.id.buttonA, getPendingSelfIntent(context, MyOnClickA));
            remoteViews.setOnClickPendingIntent(R.id.buttonB, getPendingSelfIntent(context, MyOnClickB));

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
        super.onReceive(context, intent);
        if (MyOnClickA.equals(intent.getAction())){
            number2 = "a";
        } else if (MyOnClickB.equals(intent.getAction())) {
            number2 = "b";
        }
        Toast.makeText(context, number2, Toast.LENGTH_SHORT).show();
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}

