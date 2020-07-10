package com.udacity.chukwuwauchenna.bakingapp.database.local;

import android.os.Handler;
import android.os.Looper;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chukwuwaUchenna
 */
public class IngredientExecutors {

    private final static Object LOCK = new Object();
    private static IngredientExecutors sInstance;
    private final ExecutorService diskIO;

    public IngredientExecutors(ExecutorService diskIO) {
        this.diskIO = diskIO;
    }

    public static IngredientExecutors getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new IngredientExecutors(Executors.newSingleThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor diskIO() {
        return diskIO;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NotNull Runnable runnable) {
            mainThreadHandler.post(runnable);
        }
    }
}
