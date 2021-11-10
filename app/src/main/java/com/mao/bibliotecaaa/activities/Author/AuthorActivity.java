package com.mao.bibliotecaaa.activities.Author;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.IntRange;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mao.bibliotecaaa.MainActivity;
import com.mao.bibliotecaaa.R;
import com.mao.bibliotecaaa.adapters.AuthorAdapter;
import com.mao.bibliotecaaa.db.DbAuthors;

public class AuthorActivity extends AppCompatActivity {
  Button btnCreateAuthor;
  RecyclerView recyclerAuthor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_author);

    OnBackPressedCallback back = new OnBackPressedCallback(true) {
      @Override
      public void handleOnBackPressed() {
        startActivity(new Intent(AuthorActivity.this, MainActivity.class));
      }
    };
    this.getOnBackPressedDispatcher().addCallback(this, back);

    btnCreateAuthor = findViewById(R.id.btnCreateAuthor);
    recyclerAuthor = findViewById(R.id.recyclerAuthor);

    upData();

    btnCreateAuthor.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(AuthorActivity.this, CreateAuthorActivity.class));
        finish();
      }
    });
  }

  public void upData() {
    recyclerAuthor.setLayoutManager(new LinearLayoutManager(this));
    DbAuthors authors = new DbAuthors(this);
    AuthorAdapter adapter = new AuthorAdapter(authors.getAuthors());
    recyclerAuthor.setAdapter(adapter);
  }
}
