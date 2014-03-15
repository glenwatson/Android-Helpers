package com.glen_watson;

import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

public class App {
	
	/*
	 * Override this with your app-specific tag
	 */
	protected static final String TAG = App.class.getPackage().toString();
	
	/**
	 * Returns if a debugger is connected to the device
	 * Don't use this for application logic!
	 */
	protected static final boolean isDebugging = android.os.Debug.isDebuggerConnected() || android.os.Debug.waitingForDebugger();
	
	/**
	 * Creates a short toast
	 * @param c
	 * @param msg
	 */
	public static void toast(Context c, String msg) {
		shortToast(c, msg);
	}
	
	/**
	 * @see android.widget.Toast#makeText(Context, CharSequence, int)
	 * @param c
	 * @param msg
	 */
	public static void shortToast(Context c, Object obj) {
		Toast.makeText(c, obj+"", Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * @see android.widget.Toast#makeText(Context, CharSequence, int)
	 * @param c
	 * @param msg
	 */
	public static void longToast(Context c, Object obj) {
		Toast.makeText(c, obj+"", Toast.LENGTH_LONG).show();
	}
	
	/**
	 * @see android.util.Log#d(String, String)
	 */
	public static void d(Object obj) {
		Log.d(TAG, obj+"");
	}
	
	/**
	 * @param obj - the object to log
	 * @param condition - only log if this is true
	 * @see android.util.Log#d(String, String)
	 */
	public static void d(Object obj, boolean condition) {
		if(condition)
			Log.d(TAG, obj+"");
	}
	
	/**
	 * @see android.util.Log#i(String, String)
	 */
	public static void i(Object obj) {
		Log.i(TAG, obj+"");
	}
	
	/**
	 * @see android.util.Log#v(String, String)
	 */
	public static void v(Object obj) {
		Log.v(TAG, obj+"");
	}
	
	/**
	 * @see android.util.Log#v(String, String, Throwable)
	 */
	public static void v(Object obj, Throwable t) {
		Log.v(TAG, obj+"", t);
	}
	
	/**
	 * @see android.util.Log#w(String, String)
	 */
	public static void w(Object obj) {
		Log.w(TAG, obj+"");
	}
	
	/**
	 * @see android.util.Log#w(String, String, Throwable)
	 */
	public static void w(Object obj, Throwable t) {
		Log.w(TAG, obj+"", t);
	}
	
	/**
	 * @see android.util.Log#e(String, String)
	 */
	public static void e(Object obj) {
		Log.e(TAG, obj+"");
	}
	
	/**
	 * @see android.util.Log#e(String, String, Throwable)
	 */
	public static void e(Object obj, Throwable t) {
		Log.e(TAG, obj+"", t);
	}
	
	/**
	 * Gets the approximate location of the device
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
		/**
		 * @param action - a android.view.MotionEvent
		 * @return - the String representation of action
		 * @see android.view.MotionEvent
		 */
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
