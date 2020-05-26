package com.example.itemsaleinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper {

    public static final String DATABASE_NAME="SalesInfo";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="Sales";
    public static final String ITEM_NAME="Item_Name";
    public static final String YEAR="Year";
    public static final String SALES_PRICE="SalesPrice";
    public static final String DATE="Date";

    public static final String TABLE_NAME1="SalesYear";
    public static final String TOTAL_SALE_PRICE_YEAR_WISE="TotalSalesPriceYearWise";

    private static final String DB_CREATE="create table "+TABLE_NAME+"("+ITEM_NAME+" TEXT not null, "+
            YEAR+" INTEGER not null, "+SALES_PRICE+" REAL not null check ("+SALES_PRICE+">0),"+DATE+" TEXT not null);";

    private static final String DB_CREATE1="create table "+TABLE_NAME1+"("+ITEM_NAME+" TEXT not null, "+
            YEAR+" INTEGER not null, "+TOTAL_SALE_PRICE_YEAR_WISE+" REAL not null check ("+TOTAL_SALE_PRICE_YEAR_WISE+">0));";

    private final Context context;
    private SQLiteDatabase sqLiteDatabase;
    private MyDBAdapter myDBAdapter;
    private DBHelper dbHelper;
    private Cursor c;
    private double total=0;
    public DBHelper open()
    {
        sqLiteDatabase=myDBAdapter.getWritableDatabase();
        return this;
    }
    public void close()
    {
        sqLiteDatabase.close();
    }

    public long insertdata(String item,int year,double price,String date)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(ITEM_NAME,item);contentValues.put(YEAR,year);contentValues.put(SALES_PRICE,price);contentValues.put(DATE,date);
        ContentValues contentValues1=new ContentValues();
        contentValues1.put(ITEM_NAME,item);contentValues1.put(YEAR,year);
        int k=0;
        try {
            c = sqLiteDatabase.query(TABLE_NAME1,new String[]{TOTAL_SALE_PRICE_YEAR_WISE},ITEM_NAME+"=? and "+YEAR+"=?",
                    new String[]{item,String.valueOf(year)},null,null,null);
            if (c.moveToFirst())
            {
                total=c.getDouble(c.getColumnIndex("TotalSalesPriceYearWise"));
                price=total+price;
                k=1;
                c.close();
            }
        }
        catch (Exception e) { c.close(); }
        contentValues1.put(TOTAL_SALE_PRICE_YEAR_WISE,price);
        if(k==0)
        {
            sqLiteDatabase.insert(TABLE_NAME1,null,contentValues1);
        }
        else
        {
            sqLiteDatabase.update(TABLE_NAME1,contentValues1,ITEM_NAME+"=? and "+YEAR+"=?",new String[]{item,String.valueOf(year)});
        }
        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public DBHelper(Context context) {
        this.context = context;
        myDBAdapter=new MyDBAdapter(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public Cursor getAllEntries(String sql) {
        return sqLiteDatabase.rawQuery(sql,null);
    }

    public static class MyDBAdapter extends SQLiteOpenHelper{
        public MyDBAdapter(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
            db.execSQL(DB_CREATE1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists "+TABLE_NAME);
            db.execSQL("drop table if exists "+TABLE_NAME1);
            onCreate(db);

        }
    }
}
