package cz.muni.fi.pv256.movio.uco410422.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cz.muni.fi.pv256.movio.uco410422.R;
import cz.muni.fi.pv256.movio.uco410422.models.Film;

/**
 * Created by Vladimir on 21.10.2015.
 */
public class DetailFilmFragment extends Fragment {

	private View v;

	ImageView background;
	ImageView cover;
	TextView name;
	TextView overview;
	TextView release;
	TextView director;
	FloatingActionButton fbFav;
	private ArrayList<Film> mFilms;
	private int position;

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_film_detail,container,false);
		mFilms = new ArrayList<>();
		mFilms = getArguments().getParcelableArrayList("Films");
		position = getArguments().getInt("Position");
		setRetainInstance(true);
		return v;
	}

	@Override
	public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		background = (ImageView) view.findViewById(R.id.ivFilmBackground);
		cover = (ImageView) view.findViewById(R.id.ivFilmCover);
		name = (TextView) view.findViewById(R.id.tvFilmName);
		overview = (TextView) view.findViewById(R.id.tvFilmOverview);
		release = (TextView) view.findViewById(R.id.tvFilmYear);
		director = (TextView) view.findViewById(R.id.tvFilmDirector);
		fbFav = (FloatingActionButton) view.findViewById(R.id.fbAddToFav);

		ImageView cast = (ImageView) view.findViewById(R.id.ivCast);
		insertData();

	}

	private void insertData(){
		Context mContext = getActivity();
		if (position != -1){
			Glide.with(mContext).load("https://image.tmdb.org/t/p/original" + mFilms.get(position).getmBackground()).into(background);
			Glide.with(mContext).load("https://image.tmdb.org/t/p/w396" + mFilms.get(position).getmCoverPath()).into(cover);
			name.setText(mFilms.get(position).getmTitle());
			overview.setText(mFilms.get(position).getmOverview());
			release.setText(mFilms.get(position).getmReleaseDate());

		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putCharSequence("text", "hovno");
	}
}
