package com.mao.bibliotecaaa.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
  protected static final String DB_NAME = "biblioteca";
  protected static final int DB_VERSION = 1;

  protected Context context;

  protected static final String DB_TABLE_BOOKS = "books";
  protected static final String DB_TABLE_AUTHORS = "authors";
  protected static final String DB_TABLE_EDITORIALS = "editorials";
  protected static final String DB_TABLE_BOOKCASES = "bookcases";

  public DbHelper(@Nullable Context context) {
    super(context, DB_NAME, null, DB_VERSION);
    this.context = context;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    // Autores
    db.execSQL(
        "CREATE TABLE "
            + DB_TABLE_AUTHORS
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "names TEXT NOT NULL,"
            + "lastnames TEXT NOT NULL,"
            + "nationality TEXT NOT NULL)");

    // Editoriales
    db.execSQL(
        "CREATE TABLE "
            + DB_TABLE_EDITORIALS
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL,"
            + "nationality TEXT NOT NULL)");

    // Estanterias
    db.execSQL(
        "CREATE TABLE "
            + DB_TABLE_BOOKCASES
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "letter TEXT NOT NULL,"
            + "number INTEGER NOT NULL,"
            + "color TEXT NOT NULL)");

    // Libros
    db.execSQL(
        "CREATE TABLE "
            + DB_TABLE_BOOKS
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "title TEXT NOT NULL,"
            + "description TEXT NOT NULL,"
            + "publication_date TEXT NOT NULL,"
            + "replicas INTEGER NOT NULL,"
            + "pages INTEGER NOT NULL, "
            + "author_id INTEGER NOT NULL,"
            + "editorial_id INTEGER NOT NULL,"
            + "bookcase_id INTEGER NOT NULL,"
            + "FOREIGN KEY (author_id) REFERENCES authors(id),"
            + "FOREIGN KEY (editorial_id) REFERENCES editorials(id),"
            + "FOREIGN KEY (bookcase_id) REFERENCES bookcases(id) )");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_AUTHORS);
    db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_EDITORIALS);
    db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_BOOKCASES);
    db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_BOOKS);
    onCreate(db);
  }
}
