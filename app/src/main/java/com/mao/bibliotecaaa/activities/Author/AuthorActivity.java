package com.mao.bibliotecaaa.activities.Author;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

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

    btnCreateAuthor = findViewById(R.id.btnCreateAuthor);
    recyclerAuthor = findViewById(R.id.recyclerAuthor);

    upData();
  }

  public void upData() {
    recyclerAuthor.setLayoutManager(new LinearLayoutManager(this));
    DbAuthors authors = new DbAuthors(this);
    AuthorAdapter adapter = new AuthorAdapter(authors.getAuthors());
    if (adapter != null) {
      recyclerAuthor.setAdapter(adapter);
    }
  }
}
