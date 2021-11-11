package com.mao.bibliotecaaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SearchView;

import com.mao.bibliotecaaa.activities.Author.AuthorActivity;
import com.mao.bibliotecaaa.activities.Book.BookActivity;
import com.mao.bibliotecaaa.activities.Bookcase.BookcaseActivity;
import com.mao.bibliotecaaa.activities.Editorial.EditorialActivity;
import com.mao.bibliotecaaa.adapters.BookAdapter;
import com.mao.bibliotecaaa.db.DbBooks;
import com.mao.bibliotecaaa.models.Book;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
  SearchView etxtSearch;
  RecyclerView recyclerFiltered;
  ArrayList<Book> bookList;
  BookAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    etxtSearch = findViewById(R.id.etxtSearch);
    recyclerFiltered = findViewById(R.id.recyclerFiltered);

    recyclerFiltered.setLayoutManager(new LinearLayoutManager(this));
    DbBooks dbBooks = new DbBooks(this);
    bookList = new ArrayList<>();

    adapter = new BookAdapter(dbBooks.getBooks());

    recyclerFiltered.setAdapter(adapter);

    etxtSearch.setOnQueryTextListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    Context act = MainActivity.this;

    if (item.getItemId() == R.id.itemMenuHome) {
      Intent intentHome = new Intent(act, MainActivity.class);
      startActivity(intentHome);
      return true;
    }

    if (item.getItemId() == R.id.itemMenuBook) {
      Intent intentBook = new Intent(act, BookActivity.class);
      startActivity(intentBook);
      return true;
    }

    if (item.getItemId() == R.id.itemMenuAuthor) {
      Intent intentAuthor = new Intent(act, AuthorActivity.class);
      startActivity(intentAuthor);
      return true;
    }

    if (item.getItemId() == R.id.itemMenuEditorial) {
      Intent intentEditorial = new Intent(act, EditorialActivity.class);
      startActivity(intentEditorial);
      return true;
    }

    if (item.getItemId() == R.id.itemMenuBookcase) {
      Intent intentBookcase = new Intent(act, BookcaseActivity.class);
      startActivity(intentBookcase);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public boolean onQueryTextSubmit(String query) {
    return false;
  }

  @Override
  public boolean onQueryTextChange(String newText) {
    adapter.filter(newText);
    return false;
  }
}
