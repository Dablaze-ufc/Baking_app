package com.udacity.chukwuwauchenna.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.udacity.chukwuwauchenna.bakingapp.R;
import com.udacity.chukwuwauchenna.bakingapp.model.Ingredient;

import java.util.List;

public class BakingWidgetService extends RemoteViewsService {
    private List<Ingredient> ingredients;
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingRemoteViewsFactory(getApplicationContext());

    }

    private class BakingRemoteViewsFactory implements RemoteViewsFactory {
        private Context mContext;

        BakingRemoteViewsFactory(Context context) {
            mContext = context;
        }
        @Override
        public void onCreate() {
            ingredients = BakingAppWidget.ingredients;
        }

        @Override
        public void onDataSetChanged() {
            ingredients = BakingAppWidget.ingredients;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (ingredients == null) return 0;
            return ingredients.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);
            Ingredient ingredient = ingredients.get(position);

            String measure = String.valueOf(ingredient.getQuantity());
            String widget_ingredients = ingredient.getIngredient();
            remoteViews.setTextViewText(R.id.widget_list_item_text, widget_ingredients  + "   " + measure);
            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
