package com.mao.bibliotecaaa.activities.Book;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mao.bibliotecaaa.MainActivity;
import com.mao.bibliotecaaa.R;
import com.mao.bibliotecaaa.activities.Author.AuthorActivity;
import com.mao.bibliotecaaa.adapters.BookAdapter;
import com.mao.bibliotecaaa.adapters.EditorialAdapter;
import com.mao.bibliotecaaa.db.DbBooks;
import com.mao.bibliotecaaa.db.DbEditorials;
import com.mao.bibliotecaaa.models.Book;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
  Button btnCreateBook;
  RecyclerView recyclerBook;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_book);

    OnBackPressedCallback back =
        new OnBackPressedCallback(true) {
          @Override
          public void handleOnBackPressed() {
            startActivity(new Intent(BookActivity.this, MainActivity.class));
            finish();
          }
        };
    this.getOnBackPressedDispatcher().addCallback(this, back);

    btnCreateBook = findViewById(R.id.btnCreateBook);
    recyclerBook = findViewById(R.id.recyclerBook);

    upData();

    btnCreateBook.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            startActivity(new Intent(BookActivity.this, CreateBookActivity.class));
            finish();
          }
        });
  }

  public void upData() {
    recyclerBook.setLayoutManager(new LinearLayoutManager(this));
    DbBooks books = new DbBooks(this);
    ArrayList<Book> bookList = books.getBooks();

    if (bookList != null) {
      BookAdapter adapter = new BookAdapter(bookList);
      recyclerBook.setAdapter(adapter);
    }
  }
}
