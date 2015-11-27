package cz.muni.fi.pv256.movio.uco410422;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import cz.muni.fi.pv256.movio.uco410422.Network.Api;



/**
 * Created by Vladimir on 21.09.2015.
 */
public class Muni extends Application {



	public Api getApi() {
		return api;
	}

	private Api api;


	@Override
	public void onCreate() {
		super.onCreate();

		if (BuildConfig.DEBUG) {
			initStrictMode();
		}


	}

	private void initStrictMode() {
		StrictMode.ThreadPolicy.Builder tpb = new StrictMode.ThreadPolicy.Builder()
				.detectAll()
				.penaltyLog();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			tpb.penaltyFlashScreen();
		}
		StrictMode.setThreadPolicy(tpb.build());

		StrictMode.VmPolicy.Builder vmpb = new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects()
				.penaltyLog();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			vmpb.detectLeakedClosableObjects();
		}
		StrictMode.setVmPolicy(vmpb.build());
	}

}
