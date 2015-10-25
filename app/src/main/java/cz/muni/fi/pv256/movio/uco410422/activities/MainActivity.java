package cz.muni.fi.pv256.movio.uco410422.activities;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TableLayout;

import java.util.ArrayList;

import cz.muni.fi.pv256.movio.uco410422.R;
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

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("Films", mFilms);


        if(getResources().getBoolean(R.bool.dual_pane)){
            ListFilmFragment listFilmFragment = new ListFilmFragment();
            fragmentTransaction.replace(R.id.fragmentListLayout, listFilmFragment);
            fragmentTransaction.addToBackStack(null);

            DetailFilmFragment detailFilmFragment = new DetailFilmFragment();
            //detailFilmFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.fragmentDetailLayout, detailFilmFragment);
            fragmentTransaction.commit();
        }
        else{

            ListFilmFragment listFilmFragment = new ListFilmFragment();
            listFilmFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragmentListLayout, listFilmFragment);
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


}
