package cz.muni.fi.pv256.movio.uco410422.models;

/**
 * Created by Vladimir on 16.10.2015.
 */
public class Film {

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
}
