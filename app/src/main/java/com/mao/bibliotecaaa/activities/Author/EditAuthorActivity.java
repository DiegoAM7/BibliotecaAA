package com.mao.bibliotecaaa.activities.Author;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mao.bibliotecaaa.R;
import com.mao.bibliotecaaa.db.DbAuthors;
import com.mao.bibliotecaaa.models.Author;

public class EditAuthorActivity extends AppCompatActivity {
  EditText etxtNames, etxtLastnames, etxtNationality;
  Button btnSave;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_author);

    etxtNames = findViewById(R.id.etxtCreateAuthorNames);
    etxtLastnames = findViewById(R.id.etxtCreateAuthorLastnames);
    etxtNationality = findViewById(R.id.etxtCreateAuthorNationality);
    btnSave = findViewById(R.id.btnCreateAuthorSave);

    btnSave.setText("Editar");

    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();
    Author author = (Author) bundle.get("author");

    etxtNames.setText(author.getNames());
    etxtLastnames.setText(author.getLastnames());
    etxtNationality.setText(author.getNationality());

    btnSave.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int id = author.getId();
        String names = etxtNames.getText().toString();
        String lastnames = etxtLastnames.getText().toString();
        String nationality = etxtNationality.getText().toString();

        Author authorEdit = new Author(id, names, lastnames, nationality);
        DbAuthors dbAuthors = new DbAuthors(getApplicationContext());
        dbAuthors.edit(authorEdit);

        startActivity(new Intent(EditAuthorActivity.this, AuthorActivity.class));
        finish();
      }
    });
  }
}
