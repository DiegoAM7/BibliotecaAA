package com.mao.bibliotecaaa.activities.Author;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mao.bibliotecaaa.R;
import com.mao.bibliotecaaa.db.DbAuthors;
import com.mao.bibliotecaaa.models.Author;

public class CreateAuthorActivity extends AppCompatActivity {
  EditText etxtNames, etxtLastnames, etxtNationality;
  Button btnSave;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_author);

    OnBackPressedCallback back =
        new OnBackPressedCallback(true) {
          @Override
          public void handleOnBackPressed() {
            startActivity(new Intent(CreateAuthorActivity.this, AuthorActivity.class));
            finish();
          }
        };
      this.getOnBackPressedDispatcher().addCallback(this, back);

    etxtNames = findViewById(R.id.etxtCreateAuthorNames);
    etxtLastnames = findViewById(R.id.etxtCreateAuthorLastnames);
    etxtNationality = findViewById(R.id.etxtCreateAuthorNationality);
    btnSave = findViewById(R.id.btnCreateAuthorSave);

    btnSave.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            String names = etxtNames.getText().toString();
            String lastnames = etxtLastnames.getText().toString();
            String nationality = etxtNationality.getText().toString();

            Author author = new Author(names, lastnames, nationality);
            DbAuthors dbAuthors = new DbAuthors(getApplicationContext());
            long res = dbAuthors.create(author);

            if (res > 0) {
              Toast.makeText(getApplicationContext(), "Autor creado", Toast.LENGTH_SHORT).show();
              startActivity(new Intent(CreateAuthorActivity.this, AuthorActivity.class));
              finish();
            } else {
              Toast.makeText(
                      getApplicationContext(), "No se pudo crear al autor", Toast.LENGTH_SHORT)
                  .show();
            }
          }
        });
  }
}
