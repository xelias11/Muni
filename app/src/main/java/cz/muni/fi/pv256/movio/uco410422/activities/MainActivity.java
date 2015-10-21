package cz.muni.fi.pv256.movio.uco410422.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import cz.muni.fi.pv256.movio.uco410422.Network.Connections;
import cz.muni.fi.pv256.movio.uco410422.R;
import cz.muni.fi.pv256.movio.uco410422.adapters.FilmAdapter;
import cz.muni.fi.pv256.movio.uco410422.fragments.DetailFilmFragment;
import cz.muni.fi.pv256.movio.uco410422.fragments.ListFilmFragment;
import cz.muni.fi.pv256.movio.uco410422.models.Film;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Film> mFilms;
    private GridView mGridView;
    private boolean tablet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFilms = new ArrayList<Film>();

        init();

    }


    private void init(){

        FragmentTransaction fragmentTransaction = null;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if(isTablet(this)){
            ListFilmFragment listFilmFragment = new ListFilmFragment();
            fragmentTransaction.replace(R.id.fragmentLayout, listFilmFragment);
            fragmentTransaction.addToBackStack(null);


            DetailFilmFragment detailFilmFragment = new DetailFilmFragment();
            fragmentTransaction.add(R.id.fragmentLayoutTablet, detailFilmFragment);
            fragmentTransaction.commit();
        }
        else{
            FrameLayout phoneLayout = (FrameLayout) findViewById(R.id.fragmentLayout);
            phoneLayout.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

            ListFilmFragment listFilmFragment = new ListFilmFragment();
            fragmentTransaction.replace(R.id.fragmentLayout, listFilmFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }






}
