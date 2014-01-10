package watson.glen;

import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

public abstract class App {
	
	static final String TAG = "watson.glen";
	
	static final boolean debug = false;
	
	public static void toast(Context c, String msg) {
		shortToast(c, msg);
	}
	
	public static void shortToast(Context c, String msg) {
		Toast.makeText(c, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static void longToast(Context c, String msg) {
		Toast.makeText(c, msg, Toast.LENGTH_LONG).show();
	}
	
	public static void d(String msg) {
		Log.d(TAG, msg);
	}
	
	public static void d(String msg, boolean condition) {
		if(condition)
			Log.d(TAG, msg);
	}
	
	public static void i(String msg) {
		Log.i(TAG, msg);
	}
	
	public static void v(String msg) {
		Log.v(TAG, msg);
	}
	
	public static void v(String msg, Throwable t) {
		Log.v(TAG, msg, t);
	}
	
	public static void w(String msg) {
		Log.w(TAG, msg);
	}
	
	public static void w(String msg, Throwable t) {
		Log.w(TAG, msg, t);
	}
	
	public static void e(String msg) {
		Log.e(TAG, msg);
	}
	
	public static void e(String msg, Throwable t) {
		Log.e(TAG, msg, t);
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
