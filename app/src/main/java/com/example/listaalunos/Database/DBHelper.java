package com.example.listaalunos.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.listaalunos.Models.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase _dbContext;

    private List<DBStructure> dbStructures = new ArrayList<>();

    public DBStructure<User> Users;

    public DBHelper(@Nullable Context context)
    {
        super(context, DBConfig._dbName, DBConfig._factory, DBConfig._version);
        Users = new DBStructure<>(context);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        _dbContext = sqLiteDatabase;

        for(DBStructure table : dbStructures) {
            String tableName = table.getClass().getGenericSuperclass().getClass().getName();
            Field[] fields = table.getClass().getGenericSuperclass().getClass().getFields();
            buildTable(tableName, fields);
        }
    }

    private void buildTable(String table, Field[] fieldsList)
    {
        List<Field> fields = Arrays.asList(fieldsList);

        String query = "CREATE TABLE ";
        query += table + "(";

        for(Field field : fields)
        {
            if(fields.indexOf(field) == fields.size() - 1) {
                query += field.getName() + " " + field.getType() + ");";
                break;
            }
            if(!field.getName().toLowerCase().equals("id")) {
                query += field.getName() + " " + field.getType() + " PRIMARY KEY AUTOINCREMENT, ";
                continue;
            }
            query += field.getName() + " " + field.getType() + ", ";
        }
        Log.d("Raiiaa", query);
        _dbContext.execSQL(query);
    }

    public boolean insert(String table, ContentValues contentValues)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long response = db.insert(table, null, contentValues);
        return response > 0;
    }

    public boolean update(String table, ContentValues cv, String where, String[] args)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int response = db.update(table, cv, where, args);
        return response > 0;
    }

    public boolean delete(String table, String where, String[] args) {
        SQLiteDatabase db = this.getWritableDatabase();
        int response = db.delete(table, where, args);
        return response > 0;
    }

    public Cursor select(String table, Field[] fields) {
        SQLiteDatabase db = this.getReadableDatabase();

        StringBuilder query = new StringBuilder("SELECT ");

        int index = 0;
        for(Field field : fields) {
            String fieldName = field.getName();
            index++;
            if(index == fields.length -1) {
                query.append(fieldName).append(" FROM ");
                break;
            }
            query.append(fieldName + ",");
        }

        query.append(table + ";");
        return db.rawQuery(query.toString(), null);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
}
