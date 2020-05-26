package com.example.itemsaleinformation;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    List<SalesData> salesdetail = new ArrayList<>();

    public class SalesData {
        String itemname;
        int yeardisplay;
        double yearlyprofit;
    }

    public MyAdapter(Context context) {
        this.context = context;
        DBHelper db1= new DBHelper(context);
        db1.open();
        String sql="select * from SalesYear";
        Cursor c= db1.getAllEntries(sql);
        c.moveToFirst();
        for (int i =0; i<c.getCount();i++)
        {
            SalesData sd = new SalesData();
            sd.itemname = c.getString(c.getColumnIndex("Item_Name"));
            sd.yeardisplay = c.getInt(c.getColumnIndex("Year"));
            sd.yearlyprofit = c.getDouble(c.getColumnIndex("TotalSalesPriceYearWise"));
            salesdetail.add(sd);
            c.moveToNext();
        }
        c.close();
        db1.close();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.recyclerview_display_support,parent,
                false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.item.setText(salesdetail.get(position).itemname);
        String yeardetail= String.valueOf(salesdetail.get(position).yeardisplay);
        holder.year.setText(yeardetail);
        String totalsales= String.valueOf(salesdetail.get(position).yearlyprofit);
        holder.total.setText(totalsales);
    }

    @Override
    public int getItemCount() {
        return salesdetail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView item,year,total;
        RelativeLayout recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item=itemView.findViewById(R.id.item_name);
            year=itemView.findViewById(R.id.year_name);
            total=itemView.findViewById(R.id.price_name);
            recyclerView=itemView.findViewById(R.id.mylayout);
        }
    }
}
