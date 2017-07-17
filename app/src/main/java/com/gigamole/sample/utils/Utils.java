package com.gigamole.sample.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigamole.sample.R;
import com.gigamole.sample.data.Entry;

/**
 * Created by GIGAMOLE on 8/18/16.
 */
public class Utils {

    public static void setupItem(final View view, final Entry entry) {
        final TextView txt = (TextView) view.findViewById(R.id.txt_item);
        txt.setText(entry.getEntryTitle());

        final ImageView img = (ImageView) view.findViewById(R.id.img_item);

        Bitmap bmp = BitmapFactory.decodeByteArray(entry.getEntryImage(),
                0, entry.getEntryImage().length);
            img.setImageBitmap(bmp);

        final TextView text = (TextView) view.findViewById(R.id.txt_date);
        text.setText(entry.getDate());

    }

    public static class LibraryObject {

        private String mTitle;
        private Bitmap mImage;
        private String mDate;

        public LibraryObject(final String title, Bitmap Image, String Date) {
            mTitle = title;
            mImage = Image;
            mDate = Date;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(final String title) {
            mTitle = title;
        }

        public Bitmap getImage() {
            return mImage;
        }

        public void setImage(final Bitmap image) {
            mImage = image;
        }

        public String getDate() {
            return mDate;
        }

        public void setDate(final String date) {
            mDate = date;
        }
    }
}
