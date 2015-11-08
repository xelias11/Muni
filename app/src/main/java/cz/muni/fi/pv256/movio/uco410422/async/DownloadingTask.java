package cz.muni.fi.pv256.movio.uco410422.async;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;


import com.google.gson.*;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.security.auth.callback.Callback;

import cz.muni.fi.pv256.movio.uco410422.Constans;
import cz.muni.fi.pv256.movio.uco410422.GsonParse;
import cz.muni.fi.pv256.movio.uco410422.fragments.ListFilmFragment;
import cz.muni.fi.pv256.movio.uco410422.models.Film;

/**
 * Created by Vladimir on 07.11.2015.
 */
public class DownloadingTask extends AsyncTask<String, String, List<Film>> {

	private final OkHttpClient client = new OkHttpClient();
	private ListFilmFragment mListFilmFragment;

	public DownloadingTask(ListFilmFragment listFilmFragment) {
		mListFilmFragment = listFilmFragment;
	}

	@Override
	protected void onPreExecute() { // hl. vlakno

		//mStartButton.setEnabled(false);
		//mCancelButton.setEnabled(true);
	}



	@Override
	protected List<Film> doInBackground(String... params) { // 2. vlakno
		Response response = null;
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(Constans.BASE_URL + Constans.POPULAR_URL + Constans.API_KEY)
				.addHeader("Accept", "application/json")
				.build();

		try {
			response = client.newCall(request).execute();
			Log.d("Filmy", response.body().string());
			List<Film> films = new ArrayList<>();
			films = parse(response.body().string());



			return films;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onProgressUpdate(String... values) { // hl. vlakno; progres je zpracovavan v davkach

	}

	@Override
	protected void onPostExecute(List<Film> films) {
		mListFilmFragment.updateAdapter(films);
	}

	protected List<Film> parse(String response){
		Gson mGson = new Gson();
		JsonParser jsonParser = new JsonParser();
		GsonParse results = mGson.fromJson(response, GsonParse.class);
		List<Film> films = new ArrayList<Film>();
		films = results.films;

		for (Film film : films){
			Log.d("JSON", film.getmTitle());
		}

		return films;
	}


}
