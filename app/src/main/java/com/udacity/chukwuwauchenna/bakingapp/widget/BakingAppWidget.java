package com.udacity.chukwuwauchenna.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.udacity.chukwuwauchenna.bakingapp.R;
import com.udacity.chukwuwauchenna.bakingapp.ui.details.DetailsActivity;
import com.udacity.chukwuwauchenna.bakingapp.ui.main.MainActivity;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidget extends AppWidgetProvider {
    static ArrayList<String> ingredientsList = new ArrayList<>();



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appwidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_grid_view);
            Intent intent = new Intent(context, GridWidgetService.class);
            Intent appIntent =  new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 , appIntent, 0);
            views.setRemoteAdapter(R.id.widget_listView, intent);
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
}

