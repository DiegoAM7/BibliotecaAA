package com.mao.bibliotecaaa.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.mao.bibliotecaaa.models.Author;
import com.mao.bibliotecaaa.models.Book;
import com.mao.bibliotecaaa.models.Bookcase;
import com.mao.bibliotecaaa.models.Editorial;

import java.util.ArrayList;

public class DbBooks extends DbHelper {
  DbHelper helper;

  public DbBooks(@Nullable Context context) {
    super(context);
    helper = new DbHelper(context);
  }

  public long create(Book book) {
    SQLiteDatabase db = helper.getWritableDatabase();

    ContentValues data = new ContentValues();
    data.put("title", book.getTitle());
    data.put("description", book.getDescription());
    data.put("publication_date", book.getPublicationDate());
    data.put("replicas", book.getReplicas());
    data.put("pages", book.getPages());
    data.put("author_id", book.getAuthor().getId());
    data.put("editorial_id", book.getEditorial().getId());
    data.put("bookcase_id", book.getBookcase().getId());

    return db.insert(DB_TABLE_BOOKS, null, data);
  }

  public Book getBook(int id) {
    SQLiteDatabase db = helper.getReadableDatabase();
    Book book = null;
    Cursor cursor =
        db.rawQuery(
            "SELECT * FROM " + DB_TABLE_BOOKS + " WHERE id = ?", new String[] {String.valueOf(id)});

    if (cursor.moveToFirst()) {
      book = new Book();
      book.setId(cursor.getInt(0));
      book.setTitle(cursor.getString(1));
      book.setDescription(cursor.getString(2));
      book.setPublicationDate(cursor.getString(3));
      book.setReplicas(cursor.getInt(4));
      book.setPages(cursor.getInt(5));

      DbAuthors authorDb = new DbAuthors(context);
      Author author = authorDb.getAuthor(cursor.getInt(6));
      book.setAuthor(author);

      DbEditorials editorialDb = new DbEditorials(context);
      Editorial editorial = editorialDb.getEditorial(cursor.getInt(7));
      book.setEditorial(editorial);

      DbBookcases bookcaseDb = new DbBookcases(context);
      Bookcase bookcase = bookcaseDb.getBookcase(cursor.getInt(8));
      book.setBookcase(bookcase);
    }

    return book;
  }

  public ArrayList<Book> getBooks() {
    SQLiteDatabase db = helper.getReadableDatabase();
    ArrayList<Book> books = new ArrayList<>();
    Book book;

    Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE_BOOKS, null);
    if (cursor.moveToFirst()) {
      do {
        book = new Book();
        book.setId(cursor.getInt(0));
        book.setTitle(cursor.getString(1));
        book.setDescription(cursor.getString(2));
        book.setPublicationDate(cursor.getString(3));
        book.setReplicas(cursor.getInt(4));
        book.setPages(cursor.getInt(5));

        DbAuthors authorDb = new DbAuthors(context);
        Author author = authorDb.getAuthor(cursor.getInt(6));
        book.setAuthor(author);

        DbEditorials editorialDb = new DbEditorials(context);
        Editorial editorial = editorialDb.getEditorial(cursor.getInt(7));
        book.setEditorial(editorial);

        DbBookcases bookcaseDb = new DbBookcases(context);
        Bookcase bookcase = bookcaseDb.getBookcase(cursor.getInt(8));
        book.setBookcase(bookcase);

        books.add(book);
      } while (cursor.moveToNext());
    } else {
      books = null;
    }

    return books;
  }

  public int edit(Book book) {
    SQLiteDatabase db = helper.getWritableDatabase();

    ContentValues data = new ContentValues();
    data.put("title", book.getTitle());
    data.put("description", book.getDescription());
    data.put("publication_date", book.getPublicationDate());
    data.put("replicas", book.getReplicas());
    data.put("pages", book.getPages());
    data.put("author_id", book.getAuthor().getId());
    data.put("editorial_id", book.getEditorial().getId());
    data.put("bookcase_id", book.getBookcase().getId());

    return db.update(DB_TABLE_BOOKS, data, "id = ?", new String[] {String.valueOf(book.getId())});
  }

  public int delete(Book book) {
    SQLiteDatabase db = helper.getWritableDatabase();

    return db.delete(DB_TABLE_BOOKS, "id = ?", new String[] {String.valueOf(book.getId())});
  }
}
