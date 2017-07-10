package com.gigamole.sample.screens;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.gigamole.sample.R;
import com.gigamole.sample.adapters.MainPagerAdapter;
@TargetApi(Build.VERSION_CODES.M)
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

    public void openAddActivity(View view){
        Intent intent = new Intent(this,card.class);
        startActivity(intent);

}







}
