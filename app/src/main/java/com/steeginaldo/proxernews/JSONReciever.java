package com.steeginaldo.proxernews;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by Janik on 27.06.2016.
 */
class JSONReciever {


    private int nid;
    private String subject;
    private String description;

    public int getNid() {
        return nid;
    }

    public String getDescription() {
        return description;
    }

    public String getSubject() {
        return subject;
    }


    public String recieveTitel(StringBuffer data, int obj) {

        String titel = null;


        try {
            String finalJSON = data.toString();

            JSONObject parentObject = new JSONObject(finalJSON);
            JSONArray parentArray = parentObject.getJSONArray("notifications");

            JSONObject finalObject = parentArray.getJSONObject(obj);

            titel = finalObject.getString("subject");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (titel);
    }

    public String recieveDescription(StringBuffer data, int obj) {

        String titel = null;


        try {
            String finalJSON = data.toString();

            JSONObject parentObject = new JSONObject(finalJSON);
            JSONArray parentArray = parentObject.getJSONArray("notifications");

            JSONObject finalObject = parentArray.getJSONObject(obj);

            titel = finalObject.getString("description");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (titel);
    }

    public String recieveLink(StringBuffer data, int obj) {

        String thread = null;
        String catID = null;


        try {
            String finalJSON = data.toString();

            JSONObject parentObject = new JSONObject(finalJSON);
            JSONArray parentArray = parentObject.getJSONArray("notifications");

            JSONObject finalObject = parentArray.getJSONObject(obj);

            thread = finalObject.getString("thread");
            catID = finalObject.getString("catid");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ("http://proxer.me/forum/" + catID + "/" + thread);
    }


    public String recieveDate(StringBuffer data, int obj) {

        String titel;
        String date = null;

        String finalJSON = data.toString();

        try {
            JSONObject parentObject = new JSONObject(finalJSON);
            JSONArray parentArray = parentObject.getJSONArray("notifications");

            JSONObject finalObject = parentArray.getJSONObject(obj);

            titel = finalObject.getString("time");

            Long timestamp = Long.parseLong(titel);
            Calendar mydate = Calendar.getInstance();
            mydate.setTimeInMillis(timestamp * 1000);
            date = String.valueOf(mydate.get(Calendar.DAY_OF_MONTH)) + "." + String.valueOf(mydate.get(Calendar.MONTH)) + "." + String.valueOf(mydate.get(Calendar.YEAR)) + "  " + String.valueOf(mydate.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(mydate.get(Calendar.MINUTE)) + "0";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (date);

    }
}

