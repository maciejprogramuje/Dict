package commaciejprogramuje.facebook.dict;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class DictWidget extends AppWidgetProvider {
    public static final String TEXT_KEY = "textKey";
    public static final String TRANSLATOR_KEY = "translator_key";
    String myText = "";
    Translator translator;

    @SuppressWarnings("deprecation")
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        translator = new Translator(context); // ????????????????????
        Log.w("UWAGA", "translator ok");

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.dict_widget);
            remoteViews.setTextViewText(R.id.appwidget_text, myText);

            remoteViews.setOnClickPendingIntent(R.id.buttonQ, getMyPendingIntent(context, appWidgetIds, ("Q"), 1, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonW, getMyPendingIntent(context, appWidgetIds, ("W"), 2, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonE, getMyPendingIntent(context, appWidgetIds, ("E"), 3, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonR, getMyPendingIntent(context, appWidgetIds, ("R"), 4, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonT, getMyPendingIntent(context, appWidgetIds, ("T"), 5, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonY, getMyPendingIntent(context, appWidgetIds, ("Y"), 6, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonU, getMyPendingIntent(context, appWidgetIds, ("U"), 7, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonI, getMyPendingIntent(context, appWidgetIds, ("I"), 8, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonO, getMyPendingIntent(context, appWidgetIds, ("O"), 9, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonP, getMyPendingIntent(context, appWidgetIds, ("P"), 10, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonA, getMyPendingIntent(context, appWidgetIds, ("A"), 11, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonS, getMyPendingIntent(context, appWidgetIds, ("S"), 12, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonD, getMyPendingIntent(context, appWidgetIds, ("D"), 13, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonF, getMyPendingIntent(context, appWidgetIds, ("F"), 14, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonG, getMyPendingIntent(context, appWidgetIds, ("G"), 15, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonH, getMyPendingIntent(context, appWidgetIds, ("H"), 16, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonJ, getMyPendingIntent(context, appWidgetIds, ("J"), 17, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonK, getMyPendingIntent(context, appWidgetIds, ("K"), 18, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonL, getMyPendingIntent(context, appWidgetIds, ("L"), 19, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonZ, getMyPendingIntent(context, appWidgetIds, ("Z"), 20, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonX, getMyPendingIntent(context, appWidgetIds, ("X"), 21, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonC, getMyPendingIntent(context, appWidgetIds, ("C"), 22, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonV, getMyPendingIntent(context, appWidgetIds, ("V"), 23, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonB, getMyPendingIntent(context, appWidgetIds, ("B"), 24, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonN, getMyPendingIntent(context, appWidgetIds, ("N"), 25, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonM, getMyPendingIntent(context, appWidgetIds, ("M"), 26, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonClear, getMyPendingIntent(context, appWidgetIds, ("clear"), 27, translator, remoteViews));
            remoteViews.setOnClickPendingIntent(R.id.buttonBack, getMyPendingIntent(context, appWidgetIds, ("back"), 28, translator, remoteViews));

            if (myText != null && translator != null) {
                remoteViews.setTextViewText(R.id.founded_text, translator.findKey(myText).toString());
            } else if (myText == null) {
                remoteViews.setTextViewText(R.id.founded_text, "");
            }

            remoteViews.setRemoteAdapter(R.id.widgetCollectionList, new Intent(context, DictWidget.class));
            //remoteViews.setEmptyView(R.id.widgetCollectionList, R.id.empty_text);

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    private PendingIntent getMyPendingIntent(Context context, int[] appWidgetIds, String keyStone, int requestCode, Translator translatorCopy, RemoteViews remoteViews) {
        Intent intent = new Intent(context, DictWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

        String temp;
        if (keyStone.equals("back")) {
            if (myText == null) {
                temp = null;
            } else if (myText.length() == 1) {
                temp = null;
            } else {
                temp = myText.substring(0, myText.length() - 1);
            }
        } else if (keyStone.equals("clear")) {
            temp = null;
        } else {
            if (myText == null) {
                temp = keyStone;
            } else {
                temp = (myText + keyStone);
            }
        }

        intent.putExtra(TEXT_KEY, temp);


        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));



        return PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
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
        myText = intent.getStringExtra(TEXT_KEY);

        if ((translator = intent.getParcelableExtra(TRANSLATOR_KEY)) == null) {
            translator = new Translator(context);
        } else {
            translator = intent.getParcelableExtra(TRANSLATOR_KEY);
        }

        super.onReceive(context, intent);
    }


}

