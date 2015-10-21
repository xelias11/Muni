package cz.muni.fi.pv256.movio.uco410422.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cz.muni.fi.pv256.movio.uco410422.R;

/**
 * Created by Vladimir on 21.10.2015.
 */
public class DetailFilmFragment extends Fragment {

	private View v;

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_film_detail,container,false);

		return v;
	}


}
