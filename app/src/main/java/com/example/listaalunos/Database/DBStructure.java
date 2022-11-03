package com.example.listaalunos.Database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class DBStructure<T> extends DBHelper
{
        private List<T> TList;
        private String TName = getClass().getGenericSuperclass().getClass().getName();
        private Field[] TFields = getClass().getGenericSuperclass().getClass().getFields();

        private Context context;

        public DBStructure(Context context) {
                super(context);
                this.context = context;
                getAll();
        }

        // Get All
        public List<T> getAll() {
                Cursor data = select(TName, TFields);
                for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
                        Class classType = DBStructure.class.getGenericSuperclass().getClass();
                        Class<T> model;
                        model = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                        for (int i = 0; i < model.getFields().length; i++) {
                                model.getFields()[i] = data.getExtras().getParcelable(data.getColumnName(i));
                        }
                        TList.add((T) model);
                }
                return TList;
        }


        // Select where
        public List<T> where(Predicate<T> lambda) {
                List<T> returnList = new ArrayList<>();

                for (T model : TList) {
                        if (lambda.test(model)) {
                                returnList.add(model);
                        }
                }
                return returnList;
        }
}
