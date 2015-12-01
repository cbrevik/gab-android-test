package com.idehub.GoogleAnalyticsBridge;

import android.content.Context;
import android.app.Application;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cbrevik on 22/11/15.
 */
public class GoogleAnalyticsBridge extends ReactContextBaseJavaModule{

    public GoogleAnalyticsBridge(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "GoogleAnalyticsBridge";
    }

    /**
     * Return the advertising info
     * @return
     */
    private Info getAdvertisementInfo(){
      try{
        return AdvertisingIdClient.getAdvertisingIdInfo((Context)getReactApplicationContext());
      }catch(Exception e){
        return null;
      }
    }

    /**
     * Export to Javascript the variable language containing the current language
     * @return
     */
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }

    /**
     * Export a method callable from javascript that returns the current language
     * @param callback
     */
    @ReactMethod
    public void getAdvertisingId(Callback callback){
        Info info = getAdvertisementInfo();
        String id = null;
        if (info != null)
        {
          id = info.getId();
          System.out.println("The advertising id is " + id);
        }
        else
        {
          System.out.println("Unable to retrieve advertising id. Is Play Services installed?");
        }

        callback.invoke(null, id);
    }

    /**
     * Export a method callable from javascript that returns the current language
     * @param callback
     */
    @ReactMethod
    public void getAdvertisingTrackingEnabled(Callback callback){
        Info info = getAdvertisementInfo();
        boolean limited = false;
        if (info != null)
        {
          limited = info.isLimitAdTrackingEnabled();
          System.out.println("Limited tracking is enabled " + limited);
        }
        else
        {
          System.out.println("Unable to retrieve limited tracking value. Is Play Services installed?");
        }

        callback.invoke(null, limited);
    }
}
