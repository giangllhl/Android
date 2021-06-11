package com.ptit.example.projectfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ptit.example.projectfinal.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SQLiteSongHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "song.db";
    private static final int DATABSE_VERSION = 1;
    private final String ID = "id";
    private final String IMAGE = "image";
    private final String TITLE = "title";
    private final String SINGER = "singer";
    private final String FAVORITE = "favorite";

    public SQLiteSongHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE song (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                IMAGE + " TEXT,"+
                TITLE + " TEXT," +
                SINGER + " TEXT," +
                FAVORITE + " TEXT)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public List<Song> getAll() {
        List<Song> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("song", null, null, null, null, null, null);
        while ((cursor != null) && cursor.moveToNext()) {
            String id = cursor.getString(0);
            String image = cursor.getString(1);
            String title = cursor.getString(2);
            String singer = cursor.getString(3);
            String favorite = cursor.getString(4);

            Song o = new Song(id, image, title, singer, favorite);
            list.add(o);
        }
        return list;
    }

    public List<Song> getByFav() {
        List<Song> list = new ArrayList<>();
        String whereClause = "favorite like ?";
        String[] whereArgs = {"1"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("song", null, whereClause, whereArgs, null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String img = cursor.getString(1);
            String title = cursor.getString(2);
            String singer = cursor.getString(3);
            String favorite = cursor.getString(4);
            list.add(new Song(id, img, title, singer, favorite));
        }
        return list;
    }
    //add a new song
    public long addSong(Song song) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(IMAGE, song.getImage());
        contentValues.put(TITLE, song.getTitle());
        contentValues.put(SINGER, song.getSinger());
        contentValues.put(FAVORITE, song.getFavorite());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("song", null, contentValues);
    }

    //update
    public int updateSong(Song song) {
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(song.getId())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("image", song.getImage());
        contentValues.put("title", song.getTitle());
        contentValues.put("singer", song.getSinger());
        contentValues.put("favorite", song.getFavorite());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("song", contentValues, whereClause, whereArgs);
    }

    //delete
    public int deleteSong(String id) {
        String whereClause = "id = ?";

        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("song", whereClause, whereArgs);
    }

    //search
    public List<Song> searchByTitle(String key) {
        List<Song> list = new ArrayList<>();
        String whereClause = "title like ?";
        String[] whereArgs = {"%" + key + "%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("song", null, whereClause, whereArgs, null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String img = cursor.getString(1);
            String title = cursor.getString(2);
            String singer = cursor.getString(3);
            String favorite = cursor.getString(4);
            list.add(new Song(id, img, title, singer, favorite));
        }
        return list;
    }

    public List<Song> searchBySinger(String key) {
        List<Song> list = new ArrayList<>();
        String whereClause = "singer like ?";
        String[] whereArgs = {"%" + key + "%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("song", null, whereClause, whereArgs, null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String img = cursor.getString(1);
            String title = cursor.getString(2);
            String singer = cursor.getString(3);
            String favorite = cursor.getString(4);
            list.add(new Song(id, img, title, singer, favorite));
        }
        return list;
    }


}
