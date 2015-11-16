package cz.muni.fi.pv256.movio.uco410422.Network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cz.muni.fi.pv256.movio.uco410422.models.Film;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

/**
 * Created by Vladimir on 13.11.2015.
 */
public class Responses {

	private Responses() {
	}

	public static class LoadFilmsResponse {
		@SerializedName("results")
		public List<Film> films;


		public LoadFilmsResponse(final List<Film> films) {
			this.films = films;
		}
	}
}
