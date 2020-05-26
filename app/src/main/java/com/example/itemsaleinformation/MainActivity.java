package com.example.itemsaleinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView input_date;
    private EditText input_item_name,input_year,input_price;
    private Button submit_data_button,sales_next_button;
    private ImageButton select_date_button;

    private DBHelper dbHelper;
    private DatePickerDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper=new DBHelper(this);
        intialize();
        select_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                dialog= new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        input_date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },year, month, day);
                dialog.show();
            }
        });

        submit_data_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item=input_item_name.getText().toString();
                String year=input_year.getText().toString();
                String price=input_price.getText().toString();
                String date=input_date.getText().toString();

                if(TextUtils.isEmpty(item))
                {
                    Toast.makeText(MainActivity.this,"Please enter item name.",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(year))
                {
                    Toast.makeText(MainActivity.this,"Please enter year.",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(price))
                {
                    Toast.makeText(MainActivity.this,"Please enter price.",Toast.LENGTH_SHORT).show();
                }else if(date.equals("Please select date"))
                {
                    Toast.makeText(MainActivity.this,"Please select date.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    double checkprice= Double.parseDouble(price);
                    if(checkprice<=0.0)
                    {
                        Toast.makeText(MainActivity.this,"Please enter valid Input. Sales price should be grater than 0.",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        int datelength = date.length();
                        String checkyear = date.substring(datelength - 4);
                        if (year.equals(checkyear)) {
                            dbHelper.open();
                            try {
                                long checkinsert=dbHelper.insertdata(item,Integer.parseInt(year),Double.parseDouble(price),date);
                                Toast.makeText(MainActivity.this, "Data is inserted", Toast.LENGTH_SHORT).show();
                                input_item_name.setText("");input_price.setText("");input_year.setText("");
                                input_date.setText("Please select date");
                            }catch (Exception e)
                            {
                                Toast.makeText(MainActivity.this, "Please enter again. Data is not inserted", Toast.LENGTH_SHORT).show();
                            }

                            dbHelper.close();

                        } else {
                            Toast.makeText(MainActivity.this, "Please enter valid input. Date and year is not matched!!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }
        });

        sales_next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SalesInfoActivity.class);
                startActivity(intent);

            }
        });
    }

    private void intialize() {
        input_item_name=findViewById(R.id.sales_item_name);
        input_year=findViewById(R.id.sales_year);
        input_price=findViewById(R.id.sales_price);
        input_date=findViewById(R.id.sales_date);
        submit_data_button=findViewById(R.id.Submit_button);
        sales_next_button=findViewById(R.id.next_button);
        select_date_button=findViewById(R.id.sales_date_selection);
    }
}
