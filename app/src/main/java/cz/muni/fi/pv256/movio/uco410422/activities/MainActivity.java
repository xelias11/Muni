package cz.muni.fi.pv256.movio.uco410422.activities;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;

import javax.xml.validation.Validator;

import cz.muni.fi.pv256.movio.uco410422.BuildConfig;
import cz.muni.fi.pv256.movio.uco410422.Muni;
import cz.muni.fi.pv256.movio.uco410422.Network.Responses;
import cz.muni.fi.pv256.movio.uco410422.R;
import cz.muni.fi.pv256.movio.uco410422.adapters.UpdaterSyncAdapter;
import cz.muni.fi.pv256.movio.uco410422.databases.DatabaseFilms;
import cz.muni.fi.pv256.movio.uco410422.fragments.DetailFilmFragment;
import cz.muni.fi.pv256.movio.uco410422.fragments.InitFragment;
import cz.muni.fi.pv256.movio.uco410422.fragments.ListFilmFragment;
import cz.muni.fi.pv256.movio.uco410422.models.Film;
import cz.muni.fi.pv256.movio.uco410422.services.DownloadService;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Film> mFilms;
    private GridView mGridView;
    private boolean tablet = false;
    private DetailFilmFragment detailFilmFragment;
    private DatabaseFilms dbFilms;
    private Bundle bundle;
    private int initScreen = 0;
    private Muni muni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFilms = new ArrayList<Film>();
        dbFilms = new DatabaseFilms(this);

        if (BuildConfig.logging){
            Log.i("Logging", "PAID VERSION");
        }
        bundle = savedInstanceState;
        UpdaterSyncAdapter.initializeSyncAdapter(this);

    }


    private void init(ArrayList<Film> films){

        FragmentTransaction fragmentTransaction = null;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("Films", films);

        bundle.putInt("Position", muni.getInstance().getFilmSelected());


        if(getResources().getBoolean(R.bool.dual_pane)){
            ListFilmFragment listFilmFragment = new ListFilmFragment();
            listFilmFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragmentListLayout, listFilmFragment);
            fragmentTransaction.addToBackStack(null);

            if (initScreen == 0){
                InitFragment initFragment = new InitFragment();
                fragmentTransaction.add(R.id.fragmentDetailLayout, initFragment, "detail");
                fragmentTransaction.commit();
                initScreen = 1;
            }
            else {
                DetailFilmFragment detailFilmFragment = new DetailFilmFragment();
                detailFilmFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.fragmentDetailLayout, detailFilmFragment, "detail");
                fragmentTransaction.commit();}
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
        downloadIntent.putExtra("film_id", -1);
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
        if (id == R.id.menu_switch) {
            if (item.getTitle().equals("Favorites")){
                mFilms = (ArrayList<Film>) dbFilms.getAllFilms();
                muni.getInstance().setFilmSelected(-1);
                initScreen = 0;
                init(mFilms);
                item.setTitle("Discover");
            }
            else {
                item.setTitle("Favorites");
                downloadData();
            }

            return true;
        }

        if (id == R.id.menu_refresh) {
            downloadData();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        if (bundle == null){
            downloadData();
        }
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
