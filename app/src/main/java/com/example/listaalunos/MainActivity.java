package com.example.listaalunos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.listaalunos.Database.DBHelper;
import com.example.listaalunos.Database.DBStructure;
import com.example.listaalunos.Models.User;

import java.io.Serializable;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = new User();
        user.setId(1);
        user.setName("Raiiaa");
        user.setEmail("email");
        user.setPassword("pass");

        DBHelper _dbContext = new DBHelper(getApplicationContext());
        _dbContext.Users.insert(user);
    }

    public void saveData() {

    }

    public void gotoList() {

    }
}