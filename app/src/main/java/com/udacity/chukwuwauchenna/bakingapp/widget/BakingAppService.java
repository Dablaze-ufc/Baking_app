package com.udacity.chukwuwauchenna.bakingapp.widget;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Objects;

import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.INTENT_FROM_ACTIVITY_INGREDIENTS_LIST;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.WIDGET_INTENT_KEY;

public class BakingAppService extends IntentService {

    public BakingAppService() {
        super("BakingService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            ArrayList<String> fromActivityIngredientList = Objects.requireNonNull(intent.getExtras()).getStringArrayList(INTENT_FROM_ACTIVITY_INGREDIENTS_LIST);
            handleActionBakingWidgetUpdate(fromActivityIngredientList);
        }
    }

    private void handleActionBakingWidgetUpdate(ArrayList<String> fromActivityIngredientList) {
        Intent intent = new Intent(WIDGET_INTENT_KEY);
        intent.setAction(WIDGET_INTENT_KEY);
        intent.putExtra(INTENT_FROM_ACTIVITY_INGREDIENTS_LIST, fromActivityIngredientList);
        sendBroadcast(intent);
    }
}
