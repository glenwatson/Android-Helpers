package com.glen_watson;

import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

public class App {
	
	protected static final String TAG = "com.glen_watson";
	
	protected static final boolean debug = false;
	
	public static void toast(Context c, String msg) {
		shortToast(c, msg);
	}
	
	public static void shortToast(Context c, String msg) {
		Toast.makeText(c, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static void longToast(Context c, String msg) {
		Toast.makeText(c, msg, Toast.LENGTH_LONG).show();
	}
	
	public static void d(Object obj) {
		Log.d(TAG, obj+"");
	}
	
	public static void d(Object obj, boolean condition) {
		if(condition)
			Log.d(TAG, obj+"");
	}
	
	public static void i(Object obj) {
		Log.i(TAG, obj+"");
	}
	
	public static void v(Object obj) {
		Log.v(TAG, obj+"");
	}
	
	public static void v(Object obj, Throwable t) {
		Log.v(TAG, obj+"", t);
	}
	
	public static void w(Object obj) {
		Log.w(TAG, obj+"");
	}
	
	public static void w(Object obj, Throwable t) {
		Log.w(TAG, obj+"", t);
	}
	
	public static void e(Object obj) {
		Log.e(TAG, obj+"");
	}
	
	public static void e(Object obj, Throwable t) {
		Log.e(TAG, obj+"", t);
	}
	
	/**
	 * @param context
	 * @return - null, if no location was found; else returns the most accurate location
	 */
	public static Location getLocation(Context context) {
		//get the location service
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		
		//get all location providers
		List<String> providers = locationManager.getProviders(true);
		
		Location location = null;
		//find the most reliable
		for(int i = providers.size() - 1; i >= 0; i--) {
			Location providerLocation = locationManager.getLastKnownLocation(providers.get(i));
			if(providerLocation != null) {
				if(location == null) { //if no location has been found yet,
					location = providerLocation; //use this location
				} else { //if a location has already been found
					if(location.getAccuracy() < providerLocation.getAccuracy()) { //only update it if it's more accurate
						location = providerLocation;
					}
				}
			}
		}
		return location;
	}
	
	public static class Debug {
		public static String actionToString(int action) {
			switch(action) {
				case MotionEvent.ACTION_DOWN:
					return "ACTION_DOWN";
				case MotionEvent.ACTION_UP:
					return "ACTION_UP";
				case MotionEvent.ACTION_MOVE:
					return "ACTION_MOVE";
				default:
					return "UNKNOWN ACTION";
			}
		}
	}

}
