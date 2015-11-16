package cz.muni.fi.pv256.movio.uco410422.adapters;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.support.v4.app.FragmentManager;

import com.bumptech.glide.Glide;

import java.util.List;

import cz.muni.fi.pv256.movio.uco410422.R;
import cz.muni.fi.pv256.movio.uco410422.fragments.DetailFilmFragment;
import cz.muni.fi.pv256.movio.uco410422.models.Film;

/**
 * Created by Vladimir on 16.10.2015.
 */
public class FilmAdapter extends BaseAdapter {

	private List<Film> mData;
	private Context mContext;


	public FilmAdapter(final List<Film> mData, final Context mContext) {
		this.mData = mData;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	public void setFilms(List<Film> films){
		mData = films;
	}

	@Override
	public Object getItem(final int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(final int position) {
		return mData.get(position).getId();
	}

	private static class ViewHolder {
		ImageView cover;
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.film_item, parent, false);
			ViewHolder holder = new ViewHolder();
			holder.cover = (ImageView) convertView.findViewById(R.id.ivFilmCover);
			convertView.setTag(holder);
		}

		ViewHolder holder = (ViewHolder) convertView.getTag();
		Glide.with(mContext).load("https://image.tmdb.org/t/p/w396" + mData.get(position).getmCoverPath()).into(holder.cover);


		return convertView;
	}

}
