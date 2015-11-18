package cz.muni.fi.pv256.movio.uco410422.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladimir on 17.11.2015.
 */
public class Cast {
	@SerializedName("profile_path")
	private String mImage;
	@SerializedName("name")
	private String mName;

	public Cast(final String mImage, final String mName) {
		this.mImage = mImage;
		this.mName = mName;
	}

	public String getmImage() {
		return mImage;
	}

	public void setmImage(final String mImage) {
		this.mImage = mImage;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(final String mName) {
		this.mName = mName;
	}
}
