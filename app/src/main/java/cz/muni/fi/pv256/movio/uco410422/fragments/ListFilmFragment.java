package cz.muni.fi.pv256.movio.uco410422.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cz.muni.fi.pv256.movio.uco410422.Network.Connections;
import cz.muni.fi.pv256.movio.uco410422.R;
import cz.muni.fi.pv256.movio.uco410422.adapters.FilmAdapter;
import cz.muni.fi.pv256.movio.uco410422.models.Film;

/**
 * Created by Vladimir on 21.10.2015.
 */
public class ListFilmFragment extends Fragment {

	private View v;
	private ArrayList<Film> mFilms;

	private GridView mGridView;

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_film_list,container,false);
		mFilms = new ArrayList<Film>();

		return v;
	}

	@Override
	public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		insertData();
		mGridView = (GridView) v.findViewById(R.id.gridView);
		init();

		FilmAdapter filmAdapter = new FilmAdapter(mFilms, getActivity(), getActivity().getSupportFragmentManager());

		mGridView.setAdapter(filmAdapter);
	}

	private void init(){
		mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				Toast.makeText(getActivity(), mFilms.get(position).getmTitle(), Toast.LENGTH_SHORT).show();
				return true;
			}
		});

		ViewStub empty = (ViewStub) v.findViewById(R.id.empty);
		mGridView.setEmptyView(empty);
	}


	private void insertData(){
		Random random = new Random();
		Film mFilm = new Film(random.nextLong(), "drawable/jurassic_world_cover", "Jurassic World");
		Film mFilm1 = new Film(random.nextLong(), "drawable/mad_max_cover", "Mad Max");
		Film mFilm2 = new Film(random.nextLong(), "drawable/martian_cover", "The Martian");
		Film mFilm3 = new Film(random.nextLong(), "drawable/avengers_cover", "The Avengers");
		mFilms.add(mFilm);
		mFilms.add(mFilm1);
		mFilms.add(mFilm2);
		mFilms.add(mFilm3);

		if (!Connections.isOnline(getActivity()) && mFilms.size() == 0){
			//LayoutInflater inflater = LayoutInflater.from(this);
			//View v = inflater.inflate(R.layout.empty_view, null);
			//TextView emptyText = (TextView) v.findViewById(R.id.emptyViewText);
			//emptyText.setText("Ziadne pripojenie");

		}

	}
}