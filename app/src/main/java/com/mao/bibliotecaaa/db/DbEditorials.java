package com.mao.bibliotecaaa.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.mao.bibliotecaaa.models.Editorial;

import java.util.ArrayList;

public class DbEditorials extends DbHelper {
  DbHelper helper;

  public DbEditorials(@Nullable Context context) {
    super(context);
    helper = new DbHelper(context);
  }

  public long create(Editorial editorial) {
    SQLiteDatabase db = helper.getWritableDatabase();

    ContentValues data = new ContentValues();
    data.put("name", editorial.getName());
    data.put("nationality", editorial.getNationality());

    return db.insert(DB_TABLE_EDITORIALS, null, data);
  }

  public Editorial getEditorial(int id) {
    SQLiteDatabase db = helper.getReadableDatabase();
    Editorial editorial = null;
    Cursor cursor =
        db.rawQuery(
            "SELECT * FROM " + DB_TABLE_EDITORIALS + " WHERE id = ?",
            new String[] {String.valueOf(id)});

    if (cursor.moveToFirst()) {
      editorial = new Editorial();
      editorial.setId(cursor.getInt(0));
      editorial.setName(cursor.getString(1));
      editorial.setNationality(cursor.getString(2));
    }

    return editorial;
  }

  public ArrayList<Editorial> getEditorials() {
    SQLiteDatabase db = helper.getReadableDatabase();
    ArrayList<Editorial> editorials = new ArrayList<>();
    Editorial editorial;

    Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE_EDITORIALS, null);
    if (cursor.moveToFirst()) {
      do {
        editorial = new Editorial();
        editorial.setId(cursor.getInt(0));
        editorial.setName(cursor.getString(1));
        editorial.setNationality(cursor.getString(2));
        editorials.add(editorial);
      } while (cursor.moveToNext());

      return editorials;
    } else {
      return null;
    }
  }

  public int edit(Editorial editorial) {
    SQLiteDatabase db = helper.getWritableDatabase();

    ContentValues data = new ContentValues();
    data.put("name", editorial.getName());
    data.put("nationality", editorial.getNationality());

    return db.update(
        DB_TABLE_EDITORIALS, data, "id = ?", new String[] {String.valueOf(editorial.getId())});
  }

  public int delete(Editorial editorial) {
    SQLiteDatabase db = helper.getWritableDatabase();

    return db.delete(
        DB_TABLE_EDITORIALS, "id = ?", new String[] {String.valueOf(editorial.getId())});
  }
}
