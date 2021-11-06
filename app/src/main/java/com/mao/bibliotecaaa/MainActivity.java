package com.mao.bibliotecaaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mao.bibliotecaaa.activities.Author.AuthorActivity;
import com.mao.bibliotecaaa.activities.Book.BookActivity;
import com.mao.bibliotecaaa.activities.Bookcase.BookcaseActivity;
import com.mao.bibliotecaaa.activities.Editorial.EditorialActivity;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
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
}
