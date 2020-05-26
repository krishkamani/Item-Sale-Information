package com.example.itemsaleinformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SalesInfoActivity extends AppCompatActivity {

    private RecyclerView myrecylerview;
    private Button previous_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_info);

        myrecylerview=findViewById(R.id.myrecycler);
        previous_button=findViewById(R.id.previous_button);

        MyAdapter recycleadapter = new MyAdapter(this);
        myrecylerview.setAdapter(recycleadapter);
        myrecylerview.setLayoutManager(
                new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this,
                        DividerItemDecoration.VERTICAL);
        myrecylerview.addItemDecoration(dividerItemDecoration);



        previous_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent previousintent=new Intent(SalesInfoActivity.this,MainActivity.class);
                startActivity(previousintent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    finishAffinity();
                }
            }
        });
    }
}
