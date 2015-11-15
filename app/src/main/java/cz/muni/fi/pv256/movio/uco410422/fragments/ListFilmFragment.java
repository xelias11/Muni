package cz.muni.fi.pv256.movio.uco410422.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;
import cz.muni.fi.pv256.movio.uco410422.Constans;
import cz.muni.fi.pv256.movio.uco410422.Network.Connections;
import cz.muni.fi.pv256.movio.uco410422.Network.Responses;
import cz.muni.fi.pv256.movio.uco410422.R;
import cz.muni.fi.pv256.movio.uco410422.adapters.FilmAdapter;
import cz.muni.fi.pv256.movio.uco410422.models.Film;
import cz.muni.fi.pv256.movio.uco410422.services.DownloadService;
import de.greenrobot.event.EventBus;

/**
 * Created by Vladimir on 21.10.2015.
 */
public class ListFilmFragment extends Fragment {

	private View v;
	private ArrayList<Film> mFilms;
	private GridView mGridView;
	private FilmAdapter filmAdapter;

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_film_list,container,false);
		mFilms = new ArrayList<Film>();
		EventBus.getDefault().register(this);
		return v;
	}

	@Override
	public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		insertData();
		mGridView = (GridView) v.findViewById(R.id.gridView);
		init();

		FilmAdapter filmAdapter = new FilmAdapter(mFilms, getActivity());

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

		mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				DetailFilmFragment detailFilmFrag = new DetailFilmFragment();
				FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

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


	private void insertData(){
		//Random random = new Random();
		//Film mFilm = new Film(random.nextLong(), "drawable/jurassic_world_cover", "Jurassic World");
		//Film mFilm1 = new Film(random.nextLong(), "drawable/mad_max_cover", "Mad Max");
		//Film mFilm2 = new Film(random.nextLong(), "drawable/martian_cover", "The Martian");
		//Film mFilm3 = new Film(random.nextLong(), "drawable/avengers_cover", "The Avengers");
		//mFilms.add(mFilm);
		//mFilms.add(mFilm1);
		//mFilms.add(mFilm2);
		//mFilms.add(mFilm3);

		Intent downloadIntent = new Intent(getActivity(), DownloadService.class);
		downloadIntent.putExtra("url", Constans.BASE_URL + Constans.POPULAR_URL + Constans.API_KEY);
		getActivity().startService(downloadIntent);
		//Request request = new Request.Builder()
		//		.url(Constans.BASE_URL + Constans.POPULAR_URL + Constans.API_KEY)
		//		.addHeader("Accept", "application/json")
		//		.build();


		if (!Connections.isOnline(getActivity())){
			ViewStub empty = (ViewStub) v.findViewById(R.id.empty);
			empty.setLayoutResource(R.layout.no_connection_view);
			empty.inflate();

		}

	}

	public void onEvent(final Responses.LoadFilmsResponse response){

		Log.d("Vypis", response.films.get(0).getmTitle());
		updateAdapter(response.films);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	public void updateAdapter(List<Film> films) {
		filmAdapter.setFilms(films);
		filmAdapter.notifyDataSetChanged();
	}
}
