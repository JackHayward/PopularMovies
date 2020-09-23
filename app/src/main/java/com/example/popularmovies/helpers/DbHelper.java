package com.example.popularmovies.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.popularmovies.models.Favourite;
import com.example.popularmovies.models.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Result;

public class DbHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "favourite.db";
  private static final int DATABASE_VERSION = 1;

  SQLiteOpenHelper databaseHelper;
  SQLiteDatabase db;

  public DbHelper(Context context){
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  public void open(){
    db = databaseHelper.getWritableDatabase();
  }

  public void close(){
    databaseHelper.close();
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {

    final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + Favourite.FavouriteEntry.TABLE_NAME + " (" +
        Favourite.FavouriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
        Favourite.FavouriteEntry.COLUMN_MOVIEID + " INTEGER, " +
        Favourite.FavouriteEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
        Favourite.FavouriteEntry.COLUMN_USERRATING + " REAL NOT NULL, " +
        Favourite.FavouriteEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
        Favourite.FavouriteEntry.COLUMN_PLOT_SYNOPSIS + " TEXT NOT NULL" +
        "); ";

    sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Favourite.FavouriteEntry.TABLE_NAME);
    onCreate(sqLiteDatabase);

  }

  public void addFavorite(Movie.ResultsBean movie){
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(Favourite.FavouriteEntry.COLUMN_MOVIEID, movie.getId());
    values.put(Favourite.FavouriteEntry.COLUMN_TITLE, movie.getOriginal_title());
    values.put(Favourite.FavouriteEntry.COLUMN_USERRATING, movie.getVote_average());
    values.put(Favourite.FavouriteEntry.COLUMN_POSTER_PATH, movie.getPoster_path());
    values.put(Favourite.FavouriteEntry.COLUMN_PLOT_SYNOPSIS, movie.getOverview());

    db.insert(Favourite.FavouriteEntry.TABLE_NAME, null, values);
    db.close();
  }

  public void deleteFavorite(int id){
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(Favourite.FavouriteEntry.TABLE_NAME, Favourite.FavouriteEntry.COLUMN_MOVIEID+ "=" + id, null);
  }

  public List<Movie.ResultsBean> getAllFavorite(){
    String[] columns = {
        Favourite.FavouriteEntry._ID,
        Favourite.FavouriteEntry.COLUMN_MOVIEID,
        Favourite.FavouriteEntry.COLUMN_TITLE,
        Favourite.FavouriteEntry.COLUMN_USERRATING,
        Favourite.FavouriteEntry.COLUMN_POSTER_PATH,
        Favourite.FavouriteEntry.COLUMN_PLOT_SYNOPSIS

    };
    String sortOrder =
        Favourite.FavouriteEntry._ID + " ASC";
    List<Movie.ResultsBean> favouriteList = new ArrayList<>();

    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.query(Favourite.FavouriteEntry.TABLE_NAME,
        columns,
        null,
        null,
        null,
        null,
        sortOrder);

    if (cursor.moveToFirst()){
      do {
        Movie.ResultsBean movie = new Movie.ResultsBean();
        movie.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Favourite.FavouriteEntry.COLUMN_MOVIEID))));
        movie.setOriginal_title(cursor.getString(cursor.getColumnIndex(Favourite.FavouriteEntry.COLUMN_TITLE)));
        movie.setVote_average(Double.parseDouble(cursor.getString(cursor.getColumnIndex(Favourite.FavouriteEntry.COLUMN_USERRATING))));
        movie.setPoster_path(cursor.getString(cursor.getColumnIndex(Favourite.FavouriteEntry.COLUMN_POSTER_PATH)));
        movie.setOverview(cursor.getString(cursor.getColumnIndex(Favourite.FavouriteEntry.COLUMN_PLOT_SYNOPSIS)));

        favouriteList.add(movie);

      }while(cursor.moveToNext());
    }
    cursor.close();
    db.close();

    return favouriteList;
  }
}
