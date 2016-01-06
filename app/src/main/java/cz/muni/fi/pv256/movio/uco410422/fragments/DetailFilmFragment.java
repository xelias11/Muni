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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cz.muni.fi.pv256.movio.uco410422.Network.Responses;
import cz.muni.fi.pv256.movio.uco410422.R;
import cz.muni.fi.pv256.movio.uco410422.databases.DatabaseFilms;
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
	private static boolean isFavorite = false;
	private DatabaseFilms dbFilms;

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_film_detail, container, false);
		mFilms = new ArrayList<>();
		mFilms = getArguments().getParcelableArrayList("Films");
		position = getArguments().getInt("Position");
		setRetainInstance(true);
		dbFilms = new DatabaseFilms(getActivity());

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


		insertData();
		setFbButton();

	}

	private void insertData(){
		Context mContext = getActivity();
		if (position != -1){
			Picasso.with(mContext).load("https://image.tmdb.org/t/p/original" + mFilms.get(position).getmBackground()).into(background);
			Picasso.with(mContext).load("https://image.tmdb.org/t/p/w396" + mFilms.get(position).getmCoverPath()).into(cover);
			name.setText(mFilms.get(position).getmTitle());
			overview.setText(mFilms.get(position).getmOverview());
			release.setText(mFilms.get(position).getmReleaseDate());

		}
	}

	private void setFbButton(){
		if (position == -1){

		}
		else {
			if (dbFilms.isFavorite(mFilms.get(position).getId())){
				fbFav.setImageResource(R.drawable.ic_star_full);
				isFavorite = true;
			}
			else {
				fbFav.setImageResource(R.drawable.ic_star_empty);
				isFavorite = false;
			}
			fbFav.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(final View v) {
					if (isFavorite){
						fbFav.setImageResource(R.drawable.ic_star_empty);
						Toast.makeText(getActivity(), "Odobrané z obľúbených", Toast.LENGTH_SHORT).show();
						dbFilms.deleteFilm(mFilms.get(position).getId());
						isFavorite = false;
					}
					else {
						fbFav.setImageResource(R.drawable.ic_star_full);
						Toast.makeText(getActivity(), "Pridané do obľúbených", Toast.LENGTH_SHORT).show();
						dbFilms.saveFilm(mFilms.get(position));
						isFavorite = true;
					}
				}
			});
		}

	}

	public void onEvent(final Responses.LoadCastResponse response){
		Log.d("Tag", response.cast.get(0).getmName());
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putCharSequence("Fragment", "DetailFIlmFragment");
	}

	@Override
	public void onStart() {
		super.onStart();
//		EventBus.getDefault().register(getActivity());

		//TODO: stahovanie hercov k danemu filmu, nefunguje s aktualnym klucom
		//Intent downloadIntent = new Intent(getActivity(), DownloadService.class);
		//downloadIntent.putExtra("film_id", mFilms.get(position).getId());
		//getActivity().startService(downloadIntent);
	}

	@Override
	public void onStop() {
		super.onStop();
	//	EventBus.getDefault().unregister(getActivity());
	}
}
