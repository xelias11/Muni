package cz.muni.fi.pv256.movio.uco410422.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import cz.muni.fi.pv256.movio.uco410422.UpdaterAuthenticator;

/**
 * Created by Vladimir on 27.11.2015.
 */
public class UpdaterAuthenticatorService extends Service {
	// Instance field that stores the authenticator object
	private UpdaterAuthenticator mAuthenticator;

	@Override
	public void onCreate() {
		// Create a new authenticator object
		mAuthenticator = new UpdaterAuthenticator(this);
	}

	/*
	 * When the system binds to this Service to make the RPC call
	 * return the authenticator's IBinder.
	 */
	@Override
	public IBinder onBind(Intent intent) {
		return mAuthenticator.getIBinder();
	}
}
