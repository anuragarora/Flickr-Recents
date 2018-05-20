package com.anurag.flickr.module;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Application module facilitates access to app wide services and objects by any class.
 */
public class ApplicationModule {
    private static Application sApplication;

    public static void setApplication(Application application) {
        sApplication = application;
    }

    public static Context applicationContext() {
        return sApplication;
    }

    public static Resources resources() {
        return sApplication.getResources();
    }

    public static LocalBroadcastManager getBroadcastManager() {
        return LocalBroadcastManager.getInstance(applicationContext());
    }
}
