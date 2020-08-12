//package com.example.popularmovies;
//
//import android.net.Uri;
//import android.os.AsyncTask;
//import com.example.popularmovies.RetrofitApi.MovieAPI;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.Scanner;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class MovieUtils {
//  public static final String BASE_URL = "https://api.themoviedb.org/3/discover/movie";
//  public static String API_KEY_PARAMETER = "api_key";
//  public static String apiKey = "1c0e9c5248204984a257ab3dd013c1a1";
//  public static String SORT_PARAMETER = "sort_by";
//  public static Retrofit retrofit;
//
//  public static Retrofit getRetroFitInstance() {
//    if (retrofit == null) {
//      retrofit = new Retrofit.Builder()
//          .baseUrl(BASE_URL)
//          .addConverterFactory(GsonConverterFactory.create())
//          .build();
//    }
//
//    return retrofit;
//  }
//
//  //public static URL buildUrl(String sortBy) {
//  //  Uri buildUri = Uri.parse(BASE_URL).buildUpon()
//  //      .appendQueryParameter(SORT_PARAMETER, sortBy)
//  //      .appendQueryParameter(API_KEY_PARAMETER, apiKey)
//  //      .build();
//  //  URL url = null;
//  //  try {
//  //    url = new URL(buildUri.toString());
//  //  } catch (MalformedURLException e) {
//  //    e.printStackTrace();
//  //  }
//  //  return url;
//  //}
//  //
//  //public static String getResponseFromHttpUrl(URL url) throws IOException {
//  //  HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//  //  try {
//  //    InputStream in = httpURLConnection.getInputStream();
//  //    Scanner scanner = new Scanner(in);
//  //    scanner.useDelimiter("\\A");
//  //    boolean hasInput = scanner.hasNext();
//  //    if (hasInput) {
//  //      return scanner.next();
//  //    } else {
//  //      return null;
//  //    }
//  //  }finally{
//  //    httpURLConnection.disconnect();
//  //  }
//  //}
//
//}
