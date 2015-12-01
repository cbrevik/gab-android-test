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

    HashMap<String, Tracker> mTrackers = new HashMap<String, Tracker>();

    synchronized Tracker getTracker(String trackerId) {
       if (!mTrackers.containsKey(trackerId)) {
           GoogleAnalytics analytics = GoogleAnalytics.getInstance(getReactApplicationContext());
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
    public void trackEvent(String trackerId, String eventCategory, String eventAction, Callback callback){
        Tracker tracker = getTracker(trackerId);
        Boolean tracked = false;
        if (tracker != null)
        {
          tracker.send(new HitBuilders.EventBuilder()
                .setCategory(eventCategory)
                .setAction(eventAction)
                .build());
          tracked = true;
        }

        callback.invoke(tracked);
    }
}
