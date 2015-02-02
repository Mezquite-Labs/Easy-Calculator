package com.mezquitelabs.easycalculator;

import android.app.Application;


public class CalculatorActivityApplication extends Application {

    private static CalculatorActivityApplication mCurrentApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mCurrentApplication = this;
    }

    public static CalculatorActivityApplication getCurrentApplication() {
        return mCurrentApplication;
    }

    public static String retrieveString(int resourceId) {
        return getCurrentApplication().getResources().getString(resourceId);
    }
}
