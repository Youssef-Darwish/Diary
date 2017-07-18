package com.gigamole.sample.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gigamole.sample.data.DiaryContract.DiaryEntry;

import java.util.ArrayList;
import java.util.List;


public class DiaryDataSource {

    private SQLiteDatabase database;
    private DiaryDbHelper dbHelper;

    private String[] allColumns = {DiaryEntry._ID, DiaryEntry.COLUMN_ENTRY_TITLE,
            DiaryEntry.COLUMN_ENTRY_DESCRIPTION, DiaryEntry.COLUMMN_ENTRY_DATE
            , DiaryEntry.COLUMN_ENTRY_IMAGE};

    public DiaryDataSource(Context context) {
        dbHelper = new DiaryDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();

    }

    public void close() {
        database.close();
    }

    public void addEntry(Entry newEntry) {
        ContentValues values = new ContentValues();
        values.put(DiaryEntry.COLUMN_ENTRY_TITLE, newEntry.getEntryTitle());
        values.put(DiaryEntry.COLUMN_ENTRY_DESCRIPTION, newEntry.getEntryDescription());
        values.put(DiaryEntry.COLUMMN_ENTRY_DATE, newEntry.getDate());
        values.put(DiaryEntry.COLUMN_ENTRY_IMAGE, newEntry.getEntryImage());

        database.insert(DiaryEntry.TABLE_NAME, null, values);
        Log.d("Db operations", "entry inserted");
    }

    public void deleteEntry(Entry EntryToBeDeleted) {
        String title = EntryToBeDeleted.getEntryTitle();
        database.delete(DiaryEntry.TABLE_NAME
                , DiaryEntry.COLUMN_ENTRY_TITLE + " = " + title, null);

    }

    public void updateEntry(Entry oldEntry, Entry newEntry) {

        ContentValues values = new ContentValues();
        values.put(DiaryEntry.COLUMN_ENTRY_TITLE, newEntry.getEntryTitle());
        values.put(DiaryEntry.COLUMN_ENTRY_DESCRIPTION, newEntry.getEntryDescription());
        values.put(DiaryEntry.COLUMMN_ENTRY_DATE, newEntry.getDate());
        values.put(DiaryEntry.COLUMN_ENTRY_IMAGE, newEntry.getEntryImage());

        database.update(DiaryEntry.TABLE_NAME,
                values, DiaryEntry.COLUMN_ENTRY_TITLE + " = " +
                        oldEntry.getEntryTitle(), null);


    }

    // add function to return all entries
    // what else ??
    // select operations?

    public ArrayList<Entry> getAllEntries() {
        ArrayList<Entry> entries = new ArrayList<Entry>();
        Cursor cursor = database.query(DiaryEntry.TABLE_NAME, allColumns, null, null, null, null, null);


        cursor.moveToFirst();
        Log.d("cursor data",cursor.getString(1));
        if (cursor.getCount() >1) {
            Log.d("debug Cursor", String.valueOf(cursor.getCount()));
            Log.d("debug Cursor", "can be created");
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                Entry temp = new Entry();
                temp.setEntryTitle(cursor.getString(1));
                temp.setEntryDescription(cursor.getString(2));
                temp.setDate(cursor.getString(3));
                temp.setEntryImage(cursor.getBlob(4));

                entries.add(temp);
                cursor.moveToNext();
            }
        }


        return entries;
    }

    private Entry cursorToEntry(Cursor cursor) {

        Entry entryToReturn = new Entry();
        entryToReturn.setEntryTitle(cursor.getString(0));
        entryToReturn.setEntryDescription(cursor.getString(1));
        entryToReturn.setDate(cursor.getString(2));
        entryToReturn.setEntryImage(cursor.getBlob(3));

        return entryToReturn;
    }
}

