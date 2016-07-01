package com.tcs.sqlitedatabaseexample;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by 1256010 on 6/30/2016.
 */
public class RetrieveAsyncTask extends AsyncTask<String,Void,Cursor> {
    Context mContext;
    DatabaseHelper mHelper;
    Cursor mcursor;


    public RetrieveAsyncTask(Context mContext, DatabaseHelper mHelper) {
        this.mContext = mContext;
        this.mHelper = mHelper;
        //this.mcursor = mcursor;
    }

    @Override
    protected Cursor doInBackground(String... params) {
        mcursor = mHelper.readData();

        return mcursor;
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);
        Booking.mList = new ArrayList<DetailsRowHolder>();
        String mMobile = null;
        String mDate = null;


        while (mcursor.moveToNext()) {
            mMobile = mcursor.getString(mcursor.getColumnIndex(DataTable.MOBILE));
            mDate = mcursor.getString(mcursor.getColumnIndex(DataTable.DATE));
            DetailsRowHolder detailsRowHolder = new DetailsRowHolder(mMobile,mDate);
            Booking.mList.add(detailsRowHolder);
        }

    }
}
