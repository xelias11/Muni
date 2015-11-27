package cz.muni.fi.pv256.movio.uco410422.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio.uco410422.Network.Connections;
import cz.muni.fi.pv256.movio.uco410422.R;
import cz.muni.fi.pv256.movio.uco410422.adapters.FilmAdapter;
import cz.muni.fi.pv256.movio.uco410422.databases.DatabaseFilms;
import cz.muni.fi.pv256.movio.uco410422.models.Film;

/**
 * Created by Vladimir on 21.10.2015.
 */
public class ListFilmFragment extends Fragment {

	private View v;
	private ArrayList<Film> mFilms;
	private GridView mGridView;
	private FilmAdapter filmAdapter;
	private DatabaseFilms dbFilms;
	private static boolean shown = false;

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_film_list,container,false);
		mFilms = new ArrayList<Film>();
		//Bundle bundle = getArguments();
		mFilms = getArguments().getParcelableArrayList("Films");
		dbFilms = new DatabaseFilms(getActivity());
		return v;
	}

	@Override
	public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);


		mGridView = (GridView) v.findViewById(R.id.gridView);
		filmAdapter = new FilmAdapter(mFilms, getActivity());
		mGridView.setAdapter(filmAdapter);

		if (!Connections.isOnline(getActivity())){
			ViewStub empty = (ViewStub) v.findViewById(R.id.empty);
			empty.setLayoutResource(R.layout.no_connection_view);
			empty.inflate();

		}

		init();
		updateData();
	}

	private void init(){
		mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				Toast.makeText(getActivity(), mFilms.get(position).getmTitle(), Toast.LENGTH_SHORT).show();
				return true;
			}
		});

		mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				DetailFilmFragment detailFilmFrag = new DetailFilmFragment();
				FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
				Bundle bundle = new Bundle();
				bundle.putInt("Position", position);
				bundle.putParcelableArrayList("Films", mFilms);
				detailFilmFrag.setArguments(bundle);
				if (getActivity().getResources().getBoolean(R.bool.dual_pane)) {
					fragmentTransaction.replace(R.id.fragmentDetailLayout, detailFilmFrag);
				} else {
					fragmentTransaction.replace(R.id.fragmentListLayout, detailFilmFrag);
				}

				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
			}
		});

		ViewStub empty = (ViewStub) v.findViewById(R.id.empty);
		mGridView.setEmptyView(empty);
	}


	private void updateData(){
		List<Film> savedFilms = new ArrayList<>();
		savedFilms = dbFilms.getAllFilms();
		int dbFilmsSize = savedFilms.size();
		int downloadedFilmsSize = mFilms.size();
		for (int i = 0; i < dbFilmsSize; i++){
			for (int j = 0; j < downloadedFilmsSize; j++){
				if (savedFilms.get(i).getId() == mFilms.get(j).getId()){
					dbFilms.updateFilm(mFilms.get(j));
				}
			}
		}
	}



	public void updateAdapter(List<Film> films) {
		filmAdapter.setFilms(films);//TODO adapter je null
		filmAdapter.notifyDataSetChanged();
	}
}
