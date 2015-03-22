package com.example.hanna.testapplication;

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
        this.setAllData(buf.toString());
        Log.d("app", "CCCCCCCCCCCCCCCCCC" );

        return buf.toString();
    }

    public void setAllData(String data){
        JSONObject jObj = null;

        try {
            jObj = new JSONObject(data);
            JSONObject region = jObj.getJSONObject("county_region_latlon");
            this.setRegions(region);

            /*JSONObject area_list = jObj.getJSONObject("area_list");
            this.setAreas(area_list);

            JSONObject section_list = jObj.getJSONObject("section_list");
            this.setSections(area_list);

            JSONObject route_list = jObj.getJSONObject("route_list");
            this.setRoutes(area_list);*/

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setRegions(JSONObject regions){
        Iterator<String> region_ids = regions.keys();

        ArrayList<Region> region_arr = new ArrayList<Region>();

        while( region_ids.hasNext() ) {
            String key = region_ids.next();
            try {
                //Object region = regions.get(key);
                JSONObject jobj = regions.getJSONObject(key);

                Region r = new Region();
                r.setGuid(jobj.getString("guid"));
                r.setName(jobj.getString("name"));
            } catch (JSONException e) {
                // Something went wrong!
            }
        }
    }

    public void setArea(JSONObject areas){
        Iterator<String> area_ids = areas.keys();

        while( area_ids.hasNext() ) {
            String key = area_ids.next();
            try {
                //Object area = areas.get(key);
                //Object region = regions.get(key);
                JSONObject jobj = areas.getJSONObject(key);

                Region r = new Region();
                r.setGuid(jobj.getString("guid"));
                r.setName(jobj.getString("name"));
            } catch (JSONException e) {
                // Something went wrong!
            }
        }
    }
    public void setSectionList(JSONObject sectionlist){
        Iterator<String> sections = sectionlist.keys();

        while( sections.hasNext() ) {
            String key = sections.next();
            try {
                Object section = sectionlist.get(key);
            } catch (JSONException e) {
                // Something went wrong!
            }
        }
    }

    public void setRouteList(JSONObject routelist){
        Iterator<String> routes = routelist.keys();

        while( routes.hasNext() ) {
            String key = routes.next();
            try {
                Object route = routelist.get(key);
            } catch (JSONException e) {
                // Something went wrong!
            }
        }
    }
}
