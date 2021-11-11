package com.mao.bibliotecaaa.activities.Bookcase;

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
import com.mao.bibliotecaaa.adapters.BookcaseAdapter;
import com.mao.bibliotecaaa.db.DbBookcases;
import com.mao.bibliotecaaa.models.Bookcase;

import java.util.ArrayList;

public class BookcaseActivity extends AppCompatActivity {
  Button btnCreateBookcase;
  RecyclerView recyclerBookcase;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bookcase);

    OnBackPressedCallback back =
        new OnBackPressedCallback(true) {
          @Override
          public void handleOnBackPressed() {
            startActivity(new Intent(BookcaseActivity.this, MainActivity.class));
            finish();
          }
        };
    this.getOnBackPressedDispatcher().addCallback(this, back);

    btnCreateBookcase = findViewById(R.id.btnCreateBookcase);
    recyclerBookcase = findViewById(R.id.recyclerBookcase);

    upData();

    btnCreateBookcase.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            startActivity(new Intent(BookcaseActivity.this, CreateBookcaseActivity.class));
            finish();
          }
        });
  }

  public void upData() {
    recyclerBookcase.setLayoutManager(new LinearLayoutManager(this));
    DbBookcases bookcases = new DbBookcases(this);
    ArrayList<Bookcase> bookcasesList = bookcases.getBookcases();

    if (bookcasesList != null) {
      BookcaseAdapter adapter = new BookcaseAdapter(bookcases.getBookcases());
      recyclerBookcase.setAdapter(adapter);
    }
  }
}
