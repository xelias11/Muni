package cz.muni.fi.pv256.movio.uco410422.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio.uco410422.models.Film;

/**
 * Created by Vladimir on 17.11.2015.
 */
public class DatabaseFilms extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "films_db";

	private static final String TABLE_FILM = "film",
		FILM_ID = "id",
		FILM_NAME = "name",
		FILM_COVER = "cover",
		FILM_BACKGROUND = "background",
		FILM_OVERVIEW = "overview",
		FILM_RELEASE_DATE = "release_date";


	public DatabaseFilms(final Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(final SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_FILM + " (" + FILM_ID + " INTEGER PRIMARY KEY NOT NULL, " + FILM_NAME + " TEXT, " + FILM_COVER
				+ " TEXT, " + FILM_BACKGROUND + " TEXT, " + FILM_OVERVIEW + " TEXT, " + FILM_RELEASE_DATE + " TEXT);");
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILM);
		onCreate(db);
	}

	public void saveFilm(Film film){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(FILM_ID, film.getId());
		values.put(FILM_NAME, film.getmTitle());
		values.put(FILM_COVER, film.getmCoverPath());
		values.put(FILM_BACKGROUND, film.getmBackground());
		values.put(FILM_OVERVIEW, film.getmOverview());
		values.put(FILM_RELEASE_DATE, film.getmReleaseDate());

		db.insert(TABLE_FILM, null, values);
		db.close();
	}

	public int deleteFilm(int id){
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM film WHERE id="+id,null);

		if (cursor != null){
			cursor.moveToFirst();
		}
		if (cursor.getCount() <= 0){
			return -1;
		}

		db.delete(TABLE_FILM, FILM_ID + "=" + id, null);

		db.close();
		cursor.close();
		return 0;
	}

	public List<Film> getAllFilms(){
		List<Film> films = new ArrayList<>();

		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FILM, null);

		if (cursor.moveToFirst()) {
			do {
				films.add(new Film(cursor.getInt(0), cursor.getString(1),cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
			}
			while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return films;
	}

	public void updateFilm(Film film){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(FILM_ID, film.getId());
		values.put(FILM_NAME, film.getmTitle());
		values.put(FILM_COVER, film.getmCoverPath());
		values.put(FILM_BACKGROUND, film.getmBackground());
		values.put(FILM_OVERVIEW, film.getmOverview());
		values.put(FILM_RELEASE_DATE, film.getmReleaseDate());

		db.update(TABLE_FILM, values, FILM_ID + "=" + film.getId(), null);
		db.close();
	}

	public boolean isFavorite(int id){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM film WHERE id=" + id, null);
		if (cursor.getCount() <= 0){
			cursor.close();
			db.close();
			return false;
		}
		cursor.close();
		db.close();
		return true;
	}
}
