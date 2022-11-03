package com.example.listaalunos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.listaalunos.Database.DBHelper;
import com.example.listaalunos.Database.DBStructure;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper _dbContext = new DBHelper(getApplicationContext());
    }

    public void saveData() {

    }

    public void gotoList() {

    }
}