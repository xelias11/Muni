package cz.muni.fi.pv256.movio.uco410422;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;


import cz.muni.fi.pv256.movio.uco410422.Network.Api;

import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;
import retrofit.client.OkClient;


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



		//api = new RestAdapter.Builder()
		//		.setEndpoint(Constans.BASE_URL)
		//		.setClient(new OkClient())
		//		.setLogLevel(RestAdapter.LogLevel.FULL)
		//		.setLog(new RestAdapter.Log() {
		//			@Override
		//			public void log(String msg) {
		//				Log.d("RETROFIT", msg);
		//			}
		//		})
		//		.build()
		//		.create(Api.class);





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
