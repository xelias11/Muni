package cz.muni.fi.pv256.movio.uco410422.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio.uco410422.BuildConfig;
import cz.muni.fi.pv256.movio.uco410422.Network.Responses;
import cz.muni.fi.pv256.movio.uco410422.R;
import cz.muni.fi.pv256.movio.uco410422.Versions;
import cz.muni.fi.pv256.movio.uco410422.fragments.DetailFilmFragment;
import cz.muni.fi.pv256.movio.uco410422.fragments.ListFilmFragment;
import cz.muni.fi.pv256.movio.uco410422.models.Film;
import cz.muni.fi.pv256.movio.uco410422.services.DownloadService;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Film> mFilms;
    private GridView mGridView;
    private boolean tablet = false;
    private DetailFilmFragment detailFilmFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFilms = new ArrayList<Film>();


        if (BuildConfig.logging){
            Log.i("Logging", "PAID VERSION");
        }
        EventBus.getDefault().register(this);
        if (savedInstanceState == null){
            downloadData();
        }

    }


    private void init(ArrayList<Film> films){

        FragmentTransaction fragmentTransaction = null;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("Films", films);
        bundle.putInt("Position", -1);


        if(getResources().getBoolean(R.bool.dual_pane)){
            ListFilmFragment listFilmFragment = new ListFilmFragment();
            listFilmFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragmentListLayout, listFilmFragment);
            fragmentTransaction.addToBackStack(null);

            DetailFilmFragment detailFilmFragment = new DetailFilmFragment();
            detailFilmFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.fragmentDetailLayout, detailFilmFragment, "detail");
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

    private void downloadData(){
        Intent downloadIntent = new Intent(this, DownloadService.class);
        startService(downloadIntent);
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

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(final Responses.LoadFilmsResponse response){
        init((ArrayList<Film>)response.films);
    }




}
