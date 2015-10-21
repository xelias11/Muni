package cz.muni.fi.pv256.movio.uco410422.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import java.util.List;

import cz.muni.fi.pv256.movio.uco410422.R;
import cz.muni.fi.pv256.movio.uco410422.activities.MainActivity;
import cz.muni.fi.pv256.movio.uco410422.fragments.DetailFilmFragment;
import cz.muni.fi.pv256.movio.uco410422.models.Film;

/**
 * Created by Vladimir on 16.10.2015.
 */
public class FilmAdapter extends BaseAdapter {

	FragmentManager fragmentManager;
	private List<Film> mData;
	private Context mContext;


	public FilmAdapter(final List<Film> mData, final Context mContext, FragmentManager fragmentManager) {
		this.mData = mData;
		this.mContext = mContext;
		this.fragmentManager = fragmentManager;
	}

	@Override
	public int getCount() {
		return mData.size();
	}



	@Override
	public Object getItem(final int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(final int position) {
		return mData.get(position).getmReleaseDate();
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
		holder.cover.setImageResource(mContext.getResources().getIdentifier(mData.get(position).getmCoverPath(), null, mContext.getPackageName()));
		holder.cover.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				DetailFilmFragment detailFilmFrag = new DetailFilmFragment();
				FragmentTransaction fragmentTransaction = null;
				fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.fragmentLayout, detailFilmFrag);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
			}
		});
		return convertView;
	}

}
