package com.mao.bibliotecaaa.activities.Author;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.textfield.TextInputEditText;
import com.mao.bibliotecaaa.R;
import com.mao.bibliotecaaa.db.DbAuthors;
import com.mao.bibliotecaaa.models.Author;

public class EditAuthorActivity extends AppCompatActivity {
  TextInputEditText etxtNames, etxtLastnames, etxtNationality;
  Button btnSave;

  AwesomeValidation validation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_author);

    OnBackPressedCallback back =
        new OnBackPressedCallback(true) {
          @Override
          public void handleOnBackPressed() {
            startActivity(new Intent(EditAuthorActivity.this, AuthorActivity.class));
            finish();
          }
        };
    this.getOnBackPressedDispatcher().addCallback(this, back);

    etxtNames = findViewById(R.id.etxtAuthorNames);
    etxtLastnames = findViewById(R.id.etxtAuthorLastnames);
    etxtNationality = findViewById(R.id.etxtAuthorNationality);
    btnSave = findViewById(R.id.btnCreateAuthorSave);

    btnSave.setText(R.string.edit);

    validation = new AwesomeValidation(ValidationStyle.BASIC);

    validation.addValidation(this, R.id.etxtAuthorNames, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(
        this, R.id.etxtAuthorLastnames, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(
        this, R.id.etxtAuthorNationality, RegexTemplate.NOT_EMPTY, R.string.empty);

    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();
    Author author = (Author) bundle.get("author");

    etxtNames.setText(author.getNames());
    etxtLastnames.setText(author.getLastnames());
    etxtNationality.setText(author.getNationality());

    btnSave.setOnClickListener(
        new View.OnClickListener() {
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
