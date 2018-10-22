package com.example.funny.chat.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import io.reactivex.annotations.Nullable;

public class DataFun {
    Context context;
    DataSave DB ;
    SQLiteDatabase database;
    public DataFun(Context cont)
    {
       context = cont;
       DB = new DataSave(context);
    }

    public String InsertDB(@Nullable String image, String Name,@Nullable String Family,@Nullable String Patronymic, String e_mail,String Answer)
    {
        String backfeed = "Записи сделана";
        database = DB.getWritableDatabase();
        ContentValues content = new ContentValues();
        try {


            if (image != null)
                content.put(DB.PROFIMAGE, image);
            else
                content.putNull(DB.PROFIMAGE);
            content.put(DB.NAME, Name);
            if (Family != null)
                content.put(DB.FAMILY, Family);
            else
                content.putNull(DB.FAMILY);
            if (Patronymic !=null)
                content.put(DB.PATRONYMIC, Patronymic);
            else
                content.putNull(DB.PATRONYMIC);

            content.put(DB.E_MAIL, e_mail);
            content.put(DB.ANSWER, Answer);

            database.insert(DB.TABLE_Udata, null, content);


            database.insert(DB.TABLE_Udata, null, content);
        }
        catch (SQLException e)
        {
            backfeed = "Запись не сделана";
            e.printStackTrace();

        }
        return backfeed;
    }

}
