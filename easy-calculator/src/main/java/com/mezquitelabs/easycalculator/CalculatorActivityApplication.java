package com.mezquitelabs.easycalculator;

import android.app.Application;
import android.content.Context;


public class CalculatorActivityApplication extends Application {

    private static CalculatorActivityApplication mCurrentApplication;

    public static CalculatorActivityApplication getCurrentApplication() {
        return mCurrentApplication;
    }

    public static String retrieveString(Context context, int resourceId) {
        return context.getResources().getString(resourceId);
    }

    public static String retrieveString(int resourceId) {
        return retrieveString(getCurrentApplication(), resourceId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mCurrentApplication = this;
    }
}
