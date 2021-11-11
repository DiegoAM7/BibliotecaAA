package com.mao.bibliotecaaa.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.mao.bibliotecaaa.models.Author;

import java.util.ArrayList;

public class DbAuthors extends DbHelper {
  DbHelper helper;

  public DbAuthors(@Nullable Context context) {
    super(context);
    helper = new DbHelper(context);
  }

  public long create(Author author) {
    SQLiteDatabase db = helper.getWritableDatabase();

    ContentValues data = new ContentValues();
    data.put("names", author.getNames());
    data.put("lastnames", author.getLastnames());
    data.put("nationality", author.getNationality());

    return db.insert(DB_TABLE_AUTHORS, null, data);
  }

  public Author getAuthor(int id) {
    SQLiteDatabase db = helper.getReadableDatabase();
    Author author = null;
    Cursor cursor =
        db.rawQuery(
            "SELECT * FROM " + DB_TABLE_AUTHORS + " WHERE id = ?",
            new String[] {String.valueOf(id)});

    if (cursor.moveToFirst()) {
      author = new Author();
      author.setId(cursor.getInt(0));
      author.setNames(cursor.getString(1));
      author.setLastnames(cursor.getString(2));
      author.setNationality(cursor.getString(3));
    }

    return author;
  }

  public ArrayList<Author> getAuthors() {
    SQLiteDatabase db = helper.getReadableDatabase();
    ArrayList<Author> authors = new ArrayList<>();
    Author author;

    Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE_AUTHORS, null);
    if (cursor.moveToFirst()) {
      do {
        author = new Author();
        author.setId(cursor.getInt(0));
        author.setNames(cursor.getString(1));
        author.setLastnames(cursor.getString(2));
        author.setNationality(cursor.getString(3));
        authors.add(author);
      } while (cursor.moveToNext());
    } else {
      authors = null;
    }

    return authors;
  }

  public int edit(Author author) {
    SQLiteDatabase db = helper.getWritableDatabase();

    ContentValues data = new ContentValues();
    data.put("names", author.getNames());
    data.put("lastnames", author.getLastnames());
    data.put("nationality", author.getNationality());

    return db.update(
        DB_TABLE_AUTHORS, data, "id = ?", new String[] {String.valueOf(author.getId())});
  }

  public int delete(Author author) {
    SQLiteDatabase db = helper.getWritableDatabase();

    return db.delete(DB_TABLE_AUTHORS, "id = ?", new String[] {String.valueOf(author.getId())});
  }
}
