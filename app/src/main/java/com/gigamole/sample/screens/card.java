package com.gigamole.sample.screens;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigamole.sample.R;
import com.gigamole.sample.adapters.HorizontalPagerAdapter;
import com.gigamole.sample.utils.Utils;
import com.gigamole.sample.data.Entry;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;

public class card extends AppCompatActivity {

    private Entry  entry;
    private String textContent;
    private byte [] image ;
    private String dateContent;
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBar;
    private EditText text;
    private int day;
    private int month;
    private int year;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        appBar = (AppBarLayout) findViewById(R.id.app_bar);

        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        dateContent = dateFormat.format(new Date());

     //   dateContent = DateFormat.getDateInstance().format( new Date());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textContent = ((EditText)findViewById(R.id.textTitle)).getText().toString();
                finish();

                if (image == null){
                    ImageView I = (ImageView) findViewById(R.id.img);
                    BitmapDrawable drawable = (BitmapDrawable) I.getDrawable();

                    Bitmap bitmap = drawable.getBitmap();


                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    image = baos.toByteArray();

                }

                if(!textContent.matches("")) {
                    entry = new Entry(textContent,dateContent,image);
                    HorizontalPagerAdapter.getInstance(getApplicationContext()).entryArrayList.add(entry);
                    HorizontalPagerAdapter.getInstance(getApplicationContext()).notifyDataSetChanged();
                }

            }
        });
        ImageView img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                final int ACTIVITY_SELECT_IMAGE = 1234;
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
            }
        });

        AppBarLayout.OnOffsetChangedListener mListener = new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(collapsingToolbar.getHeight() + verticalOffset < 2 * ViewCompat.getMinimumHeight(collapsingToolbar)) {
                        text = (EditText) findViewById(R.id.textTitle);
                        text.setVisibility(View.INVISIBLE);
                        collapsingToolbar.setTitle(text.getText().toString());
                } else {
                       text = (EditText) findViewById(R.id.textTitle);
                       text.setVisibility(View.VISIBLE);
                       collapsingToolbar.setTitle("");
                }
            }
        };

        appBar.addOnOffsetChangedListener(mListener);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap;
        switch(requestCode) {
            case 1234:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();

                    try {
                        ContentResolver cr = getBaseContext().getContentResolver();
                        InputStream inputStream = cr.openInputStream(selectedImage);
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        image = baos.toByteArray();
                        ImageView img = (ImageView) findViewById(R.id.img);
                        img.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }

    };

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == R.id.Date) {
            return new DatePickerDialog(this, myDateListener, 2017, 7, 1);
        }
        return null;
    }

    // when set button is clicked
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            TextView dateText = (TextView) findViewById(R.id.dateText);
            // set selected date into textview
            dateText.setText(new StringBuilder().append(day)
                    .append("/").append(month + 1).append("/").append(year)
                    .append(" "));
            dateContent = dateText.getText().toString();
        }
    };

    public void setDate(View view) {
         showDialog(R.id.Date);
    }


}



