package cz.muni.fi.pv256.movio.uco410422.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vladimir on 16.10.2015.
 */
public class Film
		implements Parcelable {

	private long mReleaseDate;
	private String mCoverPath;
	private String mTitle;

	public Film(final long mReleaseDate, final String mCoverPath, final String mTitle) {
		this.mReleaseDate = mReleaseDate;
		this.mCoverPath = mCoverPath;
		this.mTitle = mTitle;
	}

	public long getmReleaseDate() {
		return mReleaseDate;
	}

	public void setmReleaseDate(final long mReleaseDate) {
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
		dest.writeLong(this.mReleaseDate);
		dest.writeString(this.mCoverPath);
		dest.writeString(this.mTitle);
	}

	protected Film(Parcel in) {
		this.mReleaseDate = in.readLong();
		this.mCoverPath = in.readString();
		this.mTitle = in.readString();
	}

	public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {
		public Film createFromParcel(Parcel source) {return new Film(source);}

		public Film[] newArray(int size) {return new Film[size];}
	};
}
