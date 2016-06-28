package com.steeginaldo.proxernews;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final NewsData[] news = new NewsData[15];
    private final TextView[] titel = new TextView[15];
    private final TextView[] zsm = new TextView[15];
    private final TextView[] date = new TextView[15];
    private SwipeRefreshLayout swipeContainer;
    private final ImageButton[] toPage = new ImageButton[15];
    private String link;


    @Override
    public void onClick(View v) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news[v.getId()].getUrl()));
        startActivity(browserIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        for (int i = 0; i < toPage.length; i++) {
            toPage[i] = new ImageButton(this);
            toPage[i].setOnClickListener(this);
            toPage[i].setId(i);
        }


        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new recieveData().execute("http://proxer.me/notifications?format=json&s=news&p=1");
            }
        });

        new recieveData().execute("http://proxer.me/notifications?format=json&s=news&p=1");
        LinearLayout myLayout = (LinearLayout) findViewById(R.id.all);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.END;
        LinearLayout.LayoutParams rp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rp.gravity = Gravity.START;
        for (int l = 0; l < titel.length; l++) {
            titel[l] = new TextView(this);
            titel[l].setTextSize(18);
            titel[l].setLayoutParams(rp);
            titel[l].setId(l);
            titel[l].setTextColor(Color.argb(255, 163, 13, 13));
            zsm[l] = new TextView(this);
            zsm[l].setTextSize(15);
            zsm[l].setLayoutParams(rp);
            zsm[l].setId(l);
            zsm[l].setTextColor(Color.BLACK);
            date[l] = new TextView(this);
            date[l].setTextSize(10);
            date[l].setLayoutParams(lp);
            date[l].setId(l);
            date[l].setTextColor(Color.BLACK);
            date[l].setPadding(0, 0, 0, 25);
            toPage[l].setLayoutParams(lp);
            toPage[l].setImageResource(R.drawable.ic_action_name);
            toPage[l].setBackgroundColor(1);
            toPage[l].setPadding(0, 0, 0, 75);
            myLayout.addView(titel[l]);
            myLayout.addView(zsm[l]);
            myLayout.addView(date[l]);
            myLayout.addView(toPage[l]);

        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(this, About.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private class recieveData extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... urls) {
            JSONReciever proxer = new JSONReciever();
            HTMLReciever proxerHTML;
            proxerHTML = new HTMLReciever();
            proxerHTML.recieveData(urls[0]);
            for (int i = 0; i < news.length; i++) {
                news[i] = new NewsData();
                news[i].setTitel(proxer.recieveTitel(proxerHTML.getData(), i));
                news[i].setZsm(proxer.recieveDescription(proxerHTML.getData(), i));
                news[i].setDatum(proxer.recieveDate(proxerHTML.getData(), i));
                news[i].setUrl(proxer.recieveLink(proxerHTML.getData(), i));
            }


            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            for (int i = 0; i < news.length; i++) {
                titel[i].setText(news[i].getTitel());
                zsm[i].setText(news[i].getZsm());
                date[i].setText(news[i].getDatum());
                swipeContainer.setRefreshing(false);
            }

        }
    }


}


