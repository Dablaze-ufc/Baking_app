package com.udacity.chukwuwauchenna.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.udacity.chukwuwauchenna.bakingapp.R;
import com.udacity.chukwuwauchenna.bakingapp.model.Ingredient;
import com.udacity.chukwuwauchenna.bakingapp.ui.details.DetailsActivity;
import com.udacity.chukwuwauchenna.bakingapp.ui.main.MainActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.ID_PREF;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.INGREDIENT_PREF;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.NAME_PREF;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.WIDGET_PREF;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidget extends AppWidgetProvider {

    public static List<Ingredient> ingredients = new ArrayList<>();
    private static String text;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(WIDGET_PREF, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type itemType = new TypeToken<List<Ingredient>>() {
        }.getType();
        String ingredientsString = sharedPreferences.getString(INGREDIENT_PREF,null);
        ingredients = gson.fromJson(ingredientsString, itemType);

        text = sharedPreferences.getString(NAME_PREF, "no recipe");
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);
        views.setTextViewText(R.id.widget_text_app, text);

        //open mainActivity when title is clicked
        Intent clickIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, clickIntent, 0);
        views.setOnClickPendingIntent(R.id.widget_text_app, pendingIntent);
        //set adapter
        Intent intent = new Intent(context, BakingWidgetService.class);
        views.setRemoteAdapter(R.id.widget_list_view, intent);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list_view);
    }

    public static void updateWidget(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, BakingAppWidget.class));
        //Now update all widgets
        for (int appWidgetId : appWidgetIds) {
            BakingAppWidget.updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appwidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);
            Intent appIntent =  new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 , appIntent, 0);
            views.setOnClickPendingIntent(R.id.widget_text_app, pendingIntent);
            appWidgetManager.updateAppWidget(appwidgetId, views);
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
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        SharedPreferences sharedPreferences = context.getSharedPreferences(WIDGET_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(NAME_PREF);
        editor.remove(ID_PREF);
        editor.apply();
    }
}

