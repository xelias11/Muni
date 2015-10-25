package cz.muni.fi.pv256.movio.uco410422.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Vladimir on 16.10.2015.
 */
public class Connections {

	public static boolean isOnline(final Context context) {
		//check network connections
		ConnectivityManager connMan = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = connMan.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}
}
