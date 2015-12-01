package com.idehub.GoogleAnalyticsBridge;

import android.content.Context;
import android.app.Application;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;
import java.util.Map;

public class GoogleAnalyticsBridge extends ReactContextBaseJavaModule{

    public GoogleAnalyticsBridge(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "GoogleAnalyticsBridge";
    }

    HashMap<string, Tracker> mTrackers = new HashMap<string, Tracker>();

    synchronized Tracker getTracker(string trackerId) {
       if (!mTrackers.containsKey(trackerId)) {
           GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
           Tracker t = analytics.newTracker(trackerId);
           t.setAnonymizeIp(true);
           mTrackers.put(trackerId, t);
       }
       return mTrackers.get(trackerId);
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }

    @ReactMethod
    public void getAdvertisingId(string trackerId, string eventCategory, string eventAction, Callback callback){
        Tracker tracker = getTracker(trackerId);
        bool tracked = false;
        if (tracker != null)
        {
          tracker.send(new HitBuilders.EventBuilder()
                .setCategory(getString(categoryId))
                .setAction(getString(actionId))
                .build());
          tracked = true;
        }

        callback.invoke(tracked);
    }
}
