package cz.muni.fi.pv256.movio.uco410422.Network;

import java.util.List;

import cz.muni.fi.pv256.movio.uco410422.Constans;

import cz.muni.fi.pv256.movio.uco410422.models.Film;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Vladimir on 13.11.2015.
 */
public interface Api {
	@GET(Constans.POPULAR_URL + Constans.API_KEY)
	void getFilms(Callback<Responses.LoadFilmsResponse> response);

	@GET("/movie/{id}/credits" + Constans.API_KEY)
	void getCast(@Path("id") int id, Callback<Responses.LoadCastResponse> response);
}
