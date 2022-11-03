package com.example.listaalunos.Controllers.Abstract;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.listaalunos.Database.DBHelper;

public class Controller extends DBHelper
{

    public Controller(@Nullable Context context)
    {
        super(context);
    }
}
