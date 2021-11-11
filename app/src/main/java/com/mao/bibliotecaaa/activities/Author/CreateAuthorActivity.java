package com.mao.bibliotecaaa.activities.Author;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.textfield.TextInputEditText;
import com.mao.bibliotecaaa.R;
import com.mao.bibliotecaaa.db.DbAuthors;
import com.mao.bibliotecaaa.models.Author;

public class CreateAuthorActivity extends AppCompatActivity {
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
            startActivity(new Intent(CreateAuthorActivity.this, AuthorActivity.class));
            finish();
          }
        };
    this.getOnBackPressedDispatcher().addCallback(this, back);

    etxtNames = findViewById(R.id.etxtAuthorNames);
    etxtLastnames = findViewById(R.id.etxtAuthorLastnames);
    etxtNationality = findViewById(R.id.etxtAuthorNationality);
    btnSave = findViewById(R.id.btnCreateAuthorSave);

    validation = new AwesomeValidation(ValidationStyle.BASIC);

    validation.addValidation(this, R.id.etxtAuthorNames, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(
        this, R.id.etxtAuthorLastnames, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(
        this, R.id.etxtAuthorNationality, RegexTemplate.NOT_EMPTY, R.string.empty);

    btnSave.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            validation.clear();
            if (validation.validate()) {
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
          }
        });
  }
}
