package com.example.hanna.testapplication.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.hanna.testapplication.R;
import com.example.hanna.testapplication.model.Area;
import com.example.hanna.testapplication.model.GetClimbingAreas;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.InputStream;


public class MainActivity extends ActionBarActivity implements OnMapReadyCallback {
    private GetClimbingAreas climbingareas;
    public GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                //openSearch();
                return true;
            case R.id.action_advanced_search:
                //Open advanced search
                //composeMessage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap m) {
        climbingareas = new GetClimbingAreas();
        this.map = m;
        new LoadClimbingAreas().execute(climbingareas);
    }

    public void showAreaMarkers()
    {
        LatLng utah = new LatLng(39.37, -112.02);
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(utah, 7));
        Area a = climbingareas.getArea_arr().get(0);
        String[] ll = a.getLatlon();

        LatLng a1 = new LatLng(Double.parseDouble(ll[0]), Double.parseDouble(ll[1]));

        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(a1));
    }

    //public
    private class LoadClimbingAreas extends AsyncTask<GetClimbingAreas, Void, GetClimbingAreas> {

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(GetClimbingAreas result) {
            //getArea_arr
            showAreaMarkers();
        }

        @Override
        protected GetClimbingAreas doInBackground(GetClimbingAreas... a) {
            GetClimbingAreas _getclimbingarea = a[0];
            try {
                InputStream json=getAssets().open("small.json");
                _getclimbingarea.parseData(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return _getclimbingarea;
        }
    }

    @Override
    public boolean onSearchRequested() {
        //pauseSomeStuff();
        Log.d("app", "SEARCH uUUUUUUUUUUUUUUUU" );
        return super.onSearchRequested();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
