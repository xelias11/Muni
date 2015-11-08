package cz.muni.fi.pv256.movio.uco410422;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio.uco410422.models.Film;

/**
 * Created by Vladimir on 08.11.2015.
 */
public class GsonParse {
	@SerializedName("results")
	public List<Film> films = new ArrayList<Film>();
}
