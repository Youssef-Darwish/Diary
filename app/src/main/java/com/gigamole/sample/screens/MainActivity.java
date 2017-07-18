package com.gigamole.sample.screens;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gigamole.sample.adapters.HorizontalPagerAdapter;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.gigamole.sample.R;
import com.gigamole.sample.adapters.MainPagerAdapter;
import com.gigamole.sample.data.DiaryDataSource;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_main);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);

        //   final NavigationTabStrip navigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts);
        // navigationTabStrip.setTitles("HOW WE WORK", "WE WORK WITH");
        // navigationTabStrip.setViewPager(viewPager);
    }

    public void openAddActivity(View view) {
        Intent intent = new Intent(this, card.class);
        startActivity(intent);

    }

    public void tryActivity(View view) {

        Toast.makeText(this, "blabla", Toast.LENGTH_LONG).show();
        Log.d("editting", "entered");
        String title = (String) view.getTag();
        if (title != null) {
            Log.d("title", title);
            Cursor cur = HorizontalPagerAdapter.getInstance(this).mDataSource.selectEntry(title);
            Log.d("entry selected", String.valueOf(cur.getCount()));

        }


    }

}
