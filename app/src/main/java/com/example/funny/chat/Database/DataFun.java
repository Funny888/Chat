package com.example.funny.chat.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

import io.reactivex.annotations.Nullable;

public class DataFun {
    Context context;
    DataSave DB;
    SQLiteDatabase database;
    public static final String TAG = "DataFun";

    public DataFun(Context cont) {
        context = cont;
        DB = new DataSave(context);
        database = DB.getWritableDatabase();
    }

    public String InsertTableUser(@Nullable String image, String Name, @Nullable String Family, @Nullable String Patronymic, String e_mail, String Answer) {
        String feedback = "Запись не нужна";

        if (ShowTableU(DB.TABLE_Udata).isEmpty()) {
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
                if (Patronymic != null)
                    content.put(DB.PATRONYMIC, Patronymic);
                else
                    content.putNull(DB.PATRONYMIC);

                content.put(DB.E_MAIL, e_mail);
                content.put(DB.ANSWER, Answer);

                database.insert(DB.TABLE_Udata, null, content);
                feedback = "Записи сделана";
            } catch (SQLException e) {
                feedback = "Запись не сделана";
                e.printStackTrace();

            }
        }
        return feedback;
    }


    public String InsertTableGroup(String ChatName,@Nullable String ProfImage, Integer _id,String FromUser,String ToUser , String DateMsg, String LastChatMsg, String Answer) {

        String feedback = "Не записано";

            try {
                ContentValues content = new ContentValues();
                if (ProfImage != null)
                    content.put(DB.PROFIMAGE, ProfImage);
                else
                    content.putNull(DB.PROFIMAGE);
                content.put(DB._ID_GRP, _id);
                content.put(DB.TEXT_MSG, LastChatMsg);
                content.put(DB.CHAT_NAME, ChatName);
                content.put(DB.DATE_MSG, DateMsg);
                content.put(DB.FROM_USER, FromUser);
                content.put(DB.TO_USER, ToUser);
                content.put(DB.ANSWER, Answer);
                database.insert(DB.TABLE_Gdata, null, content);

                feedback = "Записано";
            } catch (SQLiteException e) {
                e.printStackTrace();
            }


        return feedback;

    }


    public String InsertTableMessage(Integer _idMsg,Integer _idGrp, @Nullable String ProfImage, String DateMsg,String TextMsg, Integer FromUser, Integer ToUser, String Answer) {
        String feedback = "Запись сделана";

        try {
            ContentValues content = new ContentValues();
            if (ProfImage != null)
                content.put(DB.PROFIMAGE, ProfImage);
            else
                content.putNull(DB.PROFIMAGE);
            content.put(DB._ID_MSG,_idMsg);
            content.put(DB._ID_GRP, _idGrp);
            content.put(DB.DATE_MSG, DateMsg);
            content.put(DB.TEXT_MSG,TextMsg);
            content.put(DB.FROM_USER, FromUser);
            content.put(DB.TO_USER, ToUser);
            content.put(DB.ANSWER, Answer);
            database.insert(DB.TABLE_Mdata,null,content);
            feedback = "Запись сделана";
        } catch (SQLiteException e) {
            e.printStackTrace();
        }


        return feedback;
    }


    public ArrayList<String[]> ShowTableU(String NameTable) {
        Cursor cur = database.query(NameTable, null, null, null, null, null, null);
        ArrayList<String[]> arr = new ArrayList<>();
        switch (NameTable) {
            case DataSave.TABLE_Udata: {
                while (cur.moveToNext()) {
                    Log.i(TAG, DB.TABLE_Udata + ": " +
                            cur.getString(cur.getColumnIndex(DB._ID)) + " " +
                            cur.getString(cur.getColumnIndex(DB.PROFIMAGE)) + " " +
                            cur.getString(cur.getColumnIndex(DB.NAME)) + " " +
                            cur.getString(cur.getColumnIndex(DB.FAMILY)) + " " +
                            cur.getString(cur.getColumnIndex(DB.PATRONYMIC)) + " " +
                            cur.getString(cur.getColumnIndex(DB.E_MAIL)) + " " +
                            cur.getString(cur.getColumnIndex(DB.ANSWER)));


                    arr.add(new String[]{
                            cur.getString(cur.getColumnIndex(DB._ID)),
                            cur.getString(cur.getColumnIndex(DB.PROFIMAGE)),
                            cur.getString(cur.getColumnIndex(DB.NAME)),
                            cur.getString(cur.getColumnIndex(DB.FAMILY)),
                            cur.getString(cur.getColumnIndex(DB.PATRONYMIC)),
                            cur.getString(cur.getColumnIndex(DB.E_MAIL)),
                            cur.getString(cur.getColumnIndex(DB.ANSWER))
                            }
                    );

                }
                break;
            }

            case DataSave.TABLE_Gdata: {
                while (cur.moveToNext())

                {
                    Log.i(TAG, DB.TABLE_Gdata + ": " +
                            cur.getString(cur.getColumnIndex(DB.CHAT_NAME)) + " " +
                            cur.getString(cur.getColumnIndex(DB.PROFIMAGE)) + " " +
                            cur.getString(cur.getColumnIndex(DB._ID_GRP)) + " " +
                            cur.getString(cur.getColumnIndex(DB.FROM_USER)) + " " +
                            cur.getString(cur.getColumnIndex(DB.TO_USER)) + " " +
                            cur.getString(cur.getColumnIndex(DB.DATE_MSG)) + " " +
                            cur.getString(cur.getColumnIndex(DB.TEXT_MSG)) + " " +
                            cur.getString(cur.getColumnIndex(DB.ANSWER)));

                    arr.add(new String[]{
                            cur.getString(cur.getColumnIndex(DB.CHAT_NAME)),
                            cur.getString(cur.getColumnIndex(DB.PROFIMAGE)),
                            cur.getString(cur.getColumnIndex(DB._ID_GRP)),
                            cur.getString(cur.getColumnIndex(DB.FROM_USER)),
                            cur.getString(cur.getColumnIndex(DB.TO_USER)),
                            cur.getString(cur.getColumnIndex(DB.DATE_MSG)),
                            cur.getString(cur.getColumnIndex(DB.TEXT_MSG)),
                            cur.getString(cur.getColumnIndex(DB.ANSWER))
                    });

                }
                break;
            }

            case DataSave.TABLE_Mdata:
            {

                while (cur.moveToNext())
                {
                    Log.i(TAG, DB.TABLE_Mdata + ": " +
                            cur.getString(cur.getColumnIndex(DB.PROFIMAGE)) + " " +
                            cur.getString(cur.getColumnIndex(DB._ID_MSG)) + " " +
                            cur.getString(cur.getColumnIndex(DB._ID_GRP)) + " " +
                            cur.getString(cur.getColumnIndex(DB.FROM_USER)) + " " +
                            cur.getString(cur.getColumnIndex(DB.TO_USER)) + " " +
                            cur.getString(cur.getColumnIndex(DB.DATE_MSG)) + " " +
                            cur.getString(cur.getColumnIndex(DB.TEXT_MSG)) + " " +
                            cur.getString(cur.getColumnIndex(DB.ANSWER)));

                    arr.add(new String[]{
                            cur.getString(cur.getColumnIndex(DB.PROFIMAGE)),
                            cur.getString(cur.getColumnIndex(DB._ID_MSG)),
                            cur.getString(cur.getColumnIndex(DB._ID_GRP)),
                            cur.getString(cur.getColumnIndex(DB.FROM_USER)),
                            cur.getString(cur.getColumnIndex(DB.TO_USER)),
                            cur.getString(cur.getColumnIndex(DB.DATE_MSG)),
                            cur.getString(cur.getColumnIndex(DB.TEXT_MSG)),
                            cur.getString(cur.getColumnIndex(DB.ANSWER))
                    });
                }
                break;
            }

        }
        return arr;
    }

    public boolean VerefUpdate(Integer _idGrp,@Nullable String ProfImage, String LastChatMsg, String DateMsg)
    {
        boolean veref = false;
        Cursor cur = database.query(DB.TABLE_Gdata,null,null,null,null,null,null);
        while (cur.moveToNext())
        {
            Integer id = cur.getInt(cur.getColumnIndex(DB._ID_GRP));
            String  Image = cur.getString(cur.getColumnIndex(DB.PROFIMAGE));
            String  lastMsg = cur.getString(cur.getColumnIndex(DB.TEXT_MSG));
            String  Date = cur.getString(cur.getColumnIndex(DB.DATE_MSG));

            if (id.equals(_idGrp))
            {
                ContentValues content = new ContentValues();
                if (Image != ProfImage || lastMsg != LastChatMsg || Date != DateMsg)
                {
                    if (Image != null)
                    content.put(DB.PROFIMAGE,Image);
                    else
                        content.putNull(DB.PROFIMAGE);
                    content.put(DB.TEXT_MSG,LastChatMsg);
                    content.put(DB.DATE_MSG,DateMsg);

                   int a = database.update(DataSave.TABLE_Gdata,content,"_idGrp = ?",new String[]{String.valueOf(id)});
                    Log.i(TAG, "VerefUpdate: " + String.valueOf(a));
                }
            }
        }

        return veref;
    }

    public boolean VerefUpdate(String Name, @Nullable String Family, @Nullable String Patronymic, String e_mail)
    {
        boolean veref = false;

        Cursor cur = database.query(DB.TABLE_Udata,null,null,null,null,null,null);

        return veref;
    }

    public String DeleteTableM(Integer idMsg)
    {
        String answer = "Не удалено";

        if(database.delete(DB.TABLE_Mdata,DB._ID_MSG + " = ?",new String[]{String.valueOf(idMsg)}) == 1);
        answer = "Удалено";

        return answer;
    }

    public String DeleteTableG(Integer idGrp)

    {
        String Answer = "Чат удален";

            if (database.delete(DB.TABLE_Gdata,DB._ID_GRP + " = ?", new String[]{String.valueOf(idGrp)}) == 1)
                Answer = "Чат не удален";

        return Answer;
    }

}
