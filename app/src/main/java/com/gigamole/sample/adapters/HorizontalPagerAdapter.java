package com.gigamole.sample.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigamole.sample.R;
import com.gigamole.sample.data.DiaryDataSource;
import com.gigamole.sample.utils.Utils;
import com.gigamole.sample.data.Entry;
import java.util.ArrayList;

import static com.gigamole.sample.utils.Utils.setupItem;

/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class HorizontalPagerAdapter extends PagerAdapter {

    public final DiaryDataSource mDataSource;
    
    private static HorizontalPagerAdapter pagerAdapter = null;
    public final ArrayList<Entry> entryArrayList;


    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private HorizontalPagerAdapter(final Context context) {
        mDataSource = new DiaryDataSource(context);
        mDataSource.open();


        entryArrayList = mDataSource.getAllEntries();
        Log.d("values in adapter",String.valueOf(entryArrayList.size()));
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);

    }

    public static HorizontalPagerAdapter getInstance(final Context context) {
        if(pagerAdapter == null) {
            pagerAdapter= new HorizontalPagerAdapter(context);
        }
        return pagerAdapter;
    }

    @Override
    public int getCount() {
        return entryArrayList.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;

            view = mLayoutInflater.inflate(R.layout.item, container, false);
            setupItem(view, entryArrayList.get(position));


        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
