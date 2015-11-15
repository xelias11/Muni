package cz.muni.fi.pv256.movio.uco410422.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import cz.muni.fi.pv256.movio.uco410422.Constans;
import cz.muni.fi.pv256.movio.uco410422.Muni;
import cz.muni.fi.pv256.movio.uco410422.Network.Api;
import cz.muni.fi.pv256.movio.uco410422.Network.Responses;
import cz.muni.fi.pv256.movio.uco410422.models.Film;
import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

import retrofit.client.OkClient;
import retrofit.client.Response;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class DownloadService
		extends IntentService {
	//private Api api;

	public DownloadService() {
		super("DownloadService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String url = intent.getStringExtra("url");
		Log.d("url", url);

		Muni muni = (Muni) getApplicationContext();
		RestAdapter adapter = new RestAdapter.Builder()
				.setEndpoint(Constans.BASE_URL)
				.setClient(new OkClient())
				.setLogLevel(RestAdapter.LogLevel.FULL)
				.setLog(new RestAdapter.Log() {
					@Override
					public void log(String msg) {
						Log.d("RETROFIT", msg);
					}
				})
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(final RequestFacade request) {
						request.addHeader("Accept", "application/json");
					}
				})
				.build();

		Api api = adapter.create(Api.class);
		api.getFilms(new Callback<List<Film>>() {
			@Override
			public void success(final List<Film> films, final Response response) {
				Log.d("IDE TO", films.get(0).getmTitle());
				EventBus.getDefault().post(new Responses.LoadFilmsResponse(films));
			}

			@Override
			public void failure(final RetrofitError error) {
				Log.d("NEJDE TO", "bla");
			}
		});

	}





}
