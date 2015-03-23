package com.example.hanna.testapplication.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by hanna on 3/5/15.
 */
public class GetClimbingAreas {
    private ArrayList<Region> region_arr;
    private ArrayList<Area> area_arr;
    private ArrayList<Section> section_arr;
    private ArrayList<Route> route_arr;

    public GetClimbingAreas() {}

    public String parseData(InputStream s){
        StringBuilder buf=new StringBuilder();
        String str;
        try {
            BufferedReader in= new BufferedReader(new InputStreamReader(s, "UTF-8"));
            while ((str=in.readLine()) != null) {
                buf.append(str);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        region_arr = new ArrayList<Region>();
        area_arr = new ArrayList<Area>();
        section_arr = new ArrayList<Section>();

        this.setAllData(buf.toString());

        Log.d("app", "zzzzzzzzzzzzz "+region_arr.toString()  );
        return buf.toString();
    }

    public void setAllData(String data){
        JSONObject jObj = null;

        try {
            jObj = new JSONObject(data);
            JSONObject region = jObj.getJSONObject("county_region_latlon");
            this.setRegions(region);

            JSONObject area_list = jObj.getJSONObject("area_list");
            this.setAreas(area_list);

            JSONObject section_list = jObj.getJSONObject("section_list");
            this.setSections(area_list);

            /*JSONObject route_list = jObj.getJSONObject("route_list");
            this.setRoutes(area_list);*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setRegions(JSONObject regions){
        Iterator<String> region_ids = regions.keys();



        while( region_ids.hasNext() ) {
            String key = region_ids.next();
            try {
                JSONObject jobj = regions.getJSONObject(key);

                Region r = new Region();
                r.setGuid(key);
                r.setName(jobj.getString("name"));
                region_arr.add(r);
            } catch (JSONException e) {
                // Something went wrong!
                Log.d("app", "EEEEEEEEEEEEEEEEEE  "+e.getMessage());
            }
        }
    }

    public void setAreas(JSONObject areas){
        Iterator<String> area_ids = areas.keys();

        while( area_ids.hasNext() ) {
            String key = area_ids.next();
            try {
                JSONObject jobj = areas.getJSONObject(key);

                Area a = new Area();
                a.setGuid(key);
                a.setName(jobj.getString("name"));
                a.setLatlon(jobj.getString("latlon"));
                area_arr.add(a);
            } catch (JSONException e) {
                // Something went wrong!
                Log.d("app", "iiiiiiiiiiiiiiiiii  "+e.getMessage());
            }
        }
    }
    public void setSections(JSONObject sections){
        Iterator<String> section_ids = sections.keys();

        while( section_ids.hasNext() ) {
            String key = section_ids.next();
            try {
                JSONObject jobj = sections.getJSONObject(key);
                Section s = new Section();
                s.setGuid(jobj.getString("guid"));
                s.setName(jobj.getString("name"));
                section_arr.add(s);
            } catch (JSONException e) {
                // Something went wrong!
            }
        }
    }

    public void setRoutes(JSONObject routes){
        Iterator<String> route_ids = routes.keys();

        while( route_ids.hasNext() ) {
            String key = route_ids.next();
            try {
                JSONObject jobj = routes.getJSONObject(key);
            } catch (JSONException e) {
                // Something went wrong!
            }
        }
    }

    public ArrayList<Area> getArea_arr() {
        return area_arr;
    }

    public void setArea_arr(ArrayList<Area> area_arr) {
        this.area_arr = area_arr;
    }
}
