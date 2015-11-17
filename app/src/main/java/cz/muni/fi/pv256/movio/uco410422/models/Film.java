package cz.muni.fi.pv256.movio.uco410422.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladimir on 16.10.2015.
 */
public class Film
		implements Parcelable {


	public int getId() {
		return id;
	}

	private int id;
	@SerializedName("release_date")
	private String mReleaseDate;
	@SerializedName("poster_path")
	private String mCoverPath;
	@SerializedName("title")
	private String mTitle;
	@SerializedName("overview")
	private String mOverview;

	public String getmBackground() {
		return mBackground;
	}

	public String getmOverview() {
		return mOverview;
	}

	@SerializedName("backdrop_path")
	private String mBackground;

	public Film(final int id, final String title, final String cover, final String background, final String overview, final String releaseDate){
		this.id = id;
		mTitle = title;
		mCoverPath = cover;
		mBackground = background;
		mOverview = overview;
		mReleaseDate = releaseDate;
	}

	public Film(final String mReleaseDate, final String mCoverPath, final String mTitle) {
		this.mReleaseDate = mReleaseDate;
		this.mCoverPath = mCoverPath;
		this.mTitle = mTitle;
	}

	public Film(){

	}

	public String getmReleaseDate() {
		return mReleaseDate.substring(0,4);
	}

	public void setmReleaseDate(final String mReleaseDate) {
		this.mReleaseDate = mReleaseDate;
	}

	public String getmCoverPath() {
		return mCoverPath;
	}

	public void setmCoverPath(final String mCoverPath) {
		this.mCoverPath = mCoverPath;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(final String mTitle) {
		this.mTitle = mTitle;
	}


	@Override
	public int describeContents() { return 0; }

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.mReleaseDate);
		dest.writeString(this.mCoverPath);
		dest.writeString(this.mTitle);
		dest.writeString(this.mOverview);
		dest.writeString(this.mBackground);
	}

	protected Film(Parcel in) {
		this.id = in.readInt();
		this.mReleaseDate = in.readString();
		this.mCoverPath = in.readString();
		this.mTitle = in.readString();
		this.mOverview = in.readString();
		this.mBackground = in.readString();
	}

	public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {
		public Film createFromParcel(Parcel source) {return new Film(source);}

		public Film[] newArray(int size) {return new Film[size];}
	};
}
