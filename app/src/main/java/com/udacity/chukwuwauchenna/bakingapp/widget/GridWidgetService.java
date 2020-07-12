package com.udacity.chukwuwauchenna.bakingapp.widget;

import android.content.Context;
import android.content.Intent;

import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.chukwuwauchenna.bakingapp.R;
import com.udacity.chukwuwauchenna.bakingapp.database.Repository;

import java.util.ArrayList;
import java.util.List;

public class GridWidgetService extends RemoteViewsService {
    List<String> remoteIngredientList = new ArrayList<>();
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewsFactory(this.getApplicationContext(), intent);

    }

    class GridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
        private Context mContext;
        private Repository mRepo;

        GridRemoteViewsFactory(Context context, Intent intent) {
            mContext = context;
        }
        @Override
        public void onCreate() {
            mRepo = new Repository(mContext);
            remoteIngredientList = mRepo.getIngredients().getIngredients();
        }

        @Override
        public void onDataSetChanged() {
            mRepo = new Repository(mContext);
            remoteIngredientList = mRepo.getIngredients().getIngredients();

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return remoteIngredientList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.baking_app_widget);
            remoteViews.setTextViewText(R.id.widget_text_app, remoteIngredientList.get(position));
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
