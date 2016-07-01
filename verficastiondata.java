package com.tcs.sqlitedatabaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 1256010 on 6/28/2016.
 */
public class VerficationTask extends AsyncTask<String,Void,Cursor> {
    Context context;
    String mobile,password,date;
    DatabaseHelper databaseHelper;
    Cursor res;
    boolean flag = false;
    static String strDate;

    public VerficationTask(Context context, String mobile,String password,String date,  DatabaseHelper databaseHelper) {
        this.context = context;
        this.mobile = mobile;
        this.databaseHelper = databaseHelper;
        this.password=password;
        this.date=date;
    }

    @Override
    protected Cursor doInBackground(String... params) {
        res = databaseHelper.verification();
        return res;


    }

    @Override
    protected void onPostExecute(Cursor res) {
        super.onPostExecute(res);
        if (res != null) {
            res.moveToFirst();
            while (res.moveToNext()) {
                if (mobile.equals(res.getString(1))) {
                    Toast.makeText(context, "Already Exists", Toast.LENGTH_SHORT).show();
                    flag = true;
                    break;
                }
            }
        }
        if (!flag) {
            if (!mobile.equals("") && !password.equals("") && isValidPassword(password)) {

                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              strDate = sdf.format(c.getTime());

                ContentValues cv = new ContentValues();
                cv.put(DataTable.MOBILE, mobile);
                cv.put(DataTable.PASSWORD, password);
                cv.put(DataTable.DATE,strDate);

                long id = new DatabaseHelper(context).insert(cv);
                if (id > 0) {
                    Toast.makeText(context, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(context, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
            }

        }
        flag =false;
    }

    public boolean isValidPassword(String password) {
        String PASSWORD_PATTERN =
                "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }
}
