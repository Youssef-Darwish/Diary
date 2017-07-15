package com.example.android.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.myapplication.data.DiaryContract.DiaryEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youssef on 08/07/17.
 */

public class DiaryDataSource {

    private SQLiteDatabase database;
    private DiaryDbHelper dbHelper;

    private String [] allColumns = {DiaryEntry._ID,DiaryEntry.COLUMN_ENTRY_TITLE,
            DiaryEntry.COLUMN_ENTRY_DESCRIPTION,DiaryEntry.COLUMMN_ENTRY_DATE
            ,DiaryEntry.COLUMN_ENTRY_IMAGE};
    public DiaryDataSource (Context context) {
        dbHelper = new DiaryDbHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();

    }
    public void close(){
        database.close();
    }

    public void addEntry(Entry newEntry){
        ContentValues values = new ContentValues();
        values.put(DiaryEntry.COLUMN_ENTRY_TITLE,newEntry.getEntryTitle());
        values.put(DiaryEntry.COLUMN_ENTRY_DESCRIPTION,newEntry.getEntryDescription() );
        values.put(DiaryEntry.COLUMMN_ENTRY_DATE,newEntry.getDate());
        values.put(DiaryEntry.COLUMN_ENTRY_IMAGE,newEntry.getEntryImage());

        database.insert(DiaryEntry.TABLE_NAME,null,values);
    }

    public void deleteEntry(Entry EntryToBeDeleted){
        String title = EntryToBeDeleted.getEntryTitle();
        database.delete(DiaryEntry.TABLE_NAME
                ,DiaryEntry.COLUMN_ENTRY_TITLE + " = " + title,null);

    }

    public void updateEntry(Entry oldEntry,Entry newEntry){

        ContentValues values = new ContentValues();
        values.put(DiaryEntry.COLUMN_ENTRY_TITLE,newEntry.getEntryTitle());
        values.put(DiaryEntry.COLUMN_ENTRY_DESCRIPTION,newEntry.getEntryDescription() );
        values.put(DiaryEntry.COLUMMN_ENTRY_DATE,newEntry.getDate());
        values.put(DiaryEntry.COLUMN_ENTRY_IMAGE,newEntry.getEntryImage());

        database.update(DiaryEntry.TABLE_NAME,
                values,DiaryEntry.COLUMN_ENTRY_TITLE + " = " +
                        oldEntry.getEntryTitle(),null);


    }

    // add function to return all entries
    // what else ??
    // select operations?

    public List<Entry> getAllEntries(){
        List <Entry> entries = new ArrayList<Entry>();
        Cursor cursor = database.query(DiaryEntry.TABLE_NAME,allColumns,null,null,null,null,null);
        while (!cursor.isAfterLast()){
            Entry temp = cursorToEntry(cursor);
            entries.add(temp);
            cursor.moveToNext();
        }

        return entries;
    }

    private Entry cursorToEntry(Cursor cursor){

        Entry entryToReturn  = new Entry();
        entryToReturn.setEntryTitle(cursor.getString(1));
        entryToReturn.setEntryDescription(cursor.getString(2));
        entryToReturn.setDate(cursor.getString(3));
        entryToReturn.setEntryImage(cursor.getBlob(4));

        return entryToReturn;
    }
}
