package cz.muni.fi.pv256.movio.uco410422.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import cz.muni.fi.pv256.movio.uco410422.adapters.UpdaterSyncAdapter;

/**
 * Created by Vladimir on 27.11.2015.
 */
public class UpdaterSyncService extends Service {

	private static final Object LOCK = new Object();
	private static UpdaterSyncAdapter sUpdaterSyncAdapter = null;

	@Override
	public void onCreate() {
		synchronized (LOCK) {
			if (sUpdaterSyncAdapter == null) {
				sUpdaterSyncAdapter = new UpdaterSyncAdapter(getApplicationContext(), true);
			}
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return sUpdaterSyncAdapter.getSyncAdapterBinder();
	}
}
