package com.mao.bibliotecaaa.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.mao.bibliotecaaa.models.Bookcase;

import java.util.ArrayList;

public class DbBookcases extends DbHelper {
  DbHelper helper;

  public DbBookcases(@Nullable Context context) {
    super(context);
    helper = new DbHelper(context);
  }

  public long create(Bookcase bookcase) {
    SQLiteDatabase db = helper.getWritableDatabase();

    ContentValues data = new ContentValues();
    data.put("letter", bookcase.getLetter());
    data.put("number", bookcase.getNumber());
    data.put("color", bookcase.getColor());

    return db.insert(DB_TABLE_BOOKCASES, null, data);
  }

  public Bookcase getBookcase(int id) {
    SQLiteDatabase db = helper.getReadableDatabase();
    Bookcase bookcase = null;
    Cursor cursor =
        db.rawQuery(
            "SELECT * FROM " + DB_TABLE_BOOKCASES + " WHERE id = ?",
            new String[] {String.valueOf(id)});

    if (cursor.moveToFirst()) {
      bookcase = new Bookcase();
      bookcase.setId(cursor.getInt(0));
      bookcase.setLetter(cursor.getString(1));
      bookcase.setNumber(cursor.getInt(2));
      bookcase.setColor(cursor.getString(3));
    }

    return bookcase;
  }

  public ArrayList<Bookcase> getBookcases() {
    SQLiteDatabase db = helper.getReadableDatabase();
    ArrayList<Bookcase> bookcases = new ArrayList<>();
    Bookcase bookcase;

    Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE_BOOKCASES, null);
    if (cursor.moveToFirst()) {
      do {
        bookcase = new Bookcase();
        bookcase.setId(cursor.getInt(0));
        bookcase.setLetter(cursor.getString(1));
        bookcase.setNumber(cursor.getInt(2));
        bookcase.setColor(cursor.getString(3));
        bookcases.add(bookcase);
      } while (cursor.moveToNext());

      return bookcases;
    } else {
      return null;
    }
  }

  public int edit(Bookcase bookcase) {
    SQLiteDatabase db = helper.getWritableDatabase();

    ContentValues data = new ContentValues();
    data.put("letter", bookcase.getLetter());
    data.put("number", bookcase.getNumber());
    data.put("color", bookcase.getColor());

    return db.update(
        DB_TABLE_BOOKCASES, data, "id = ?", new String[] {String.valueOf(bookcase.getId())});
  }

  public int delete(Bookcase bookcase) {
    SQLiteDatabase db = helper.getWritableDatabase();

    return db.delete(DB_TABLE_BOOKCASES, "id = ?", new String[] {String.valueOf(bookcase.getId())});
  }
}
