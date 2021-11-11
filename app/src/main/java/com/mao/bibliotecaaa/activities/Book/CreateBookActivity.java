package com.mao.bibliotecaaa.activities.Book;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mao.bibliotecaaa.R;
import com.mao.bibliotecaaa.db.DbAuthors;
import com.mao.bibliotecaaa.db.DbBookcases;
import com.mao.bibliotecaaa.db.DbBooks;
import com.mao.bibliotecaaa.db.DbEditorials;
import com.mao.bibliotecaaa.models.Author;
import com.mao.bibliotecaaa.models.Book;
import com.mao.bibliotecaaa.models.Bookcase;
import com.mao.bibliotecaaa.models.Editorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateBookActivity extends AppCompatActivity {
  TextInputEditText etxtTitle, etxtDescription, etxtPublicationDate, etxtReplicas, etxtPages;
  Spinner spAuthor, spEditorial, spBookcase;
  Button btnSave;

  AwesomeValidation validation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_book);

    OnBackPressedCallback back =
        new OnBackPressedCallback(true) {
          @Override
          public void handleOnBackPressed() {
            startActivity(new Intent(CreateBookActivity.this, BookActivity.class));
            finish();
          }
        };
    this.getOnBackPressedDispatcher().addCallback(this, back);

    etxtTitle = findViewById(R.id.etxtBookTitle);
    etxtDescription = findViewById(R.id.etxtBookDescription);
    etxtPublicationDate = findViewById(R.id.etxtBookPublicationDate);
    etxtReplicas = findViewById(R.id.etxtBookReplicas);
    etxtPages = findViewById(R.id.etxtBookPages);
    spAuthor = findViewById(R.id.spBookAuthors);
    spEditorial = findViewById(R.id.spBookEditorial);
    spBookcase = findViewById(R.id.spBookBookcase);
    btnSave = findViewById(R.id.btnCreateBookSave);

    validation = new AwesomeValidation(ValidationStyle.BASIC);

    validation.addValidation(this, R.id.etxtBookTitle, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(
        this, R.id.etxtBookDescription, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(
        this, R.id.etxtBookPublicationDate, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(this, R.id.etxtBookReplicas, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(this, R.id.etxtBookPages, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(this, R.id.spBookAuthors, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(this, R.id.spBookEditorial, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(this, R.id.spBookBookcase, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(this, R.id.etxtBookReplicas, "[0-9]+", R.string.number);
    validation.addValidation(this, R.id.etxtBookPages, "[0-9]+", R.string.number);
    validation.addValidation(
        this,
        R.id.etxtBookPublicationDate,
        "^(?:3[01]|[12][0-9]|0?[1-9])([\\-/.])(0?[1-9]|1[1-2])\\1\\d{4}$",
        R.string.date);

    spinnerData(getApplicationContext());

    btnSave.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            validation.clear();
            if (validation.validate()) {
              String title = etxtTitle.getText().toString();
              String description = etxtDescription.getText().toString();
              String publicationDate = etxtPublicationDate.getText().toString();
              int replicas = Integer.parseInt(etxtReplicas.getText().toString());
              int pages = Integer.parseInt(etxtPages.getText().toString());
              Author author = (Author) spAuthor.getSelectedItem();
              Bookcase bookcase = (Bookcase) spBookcase.getSelectedItem();
              Editorial editorial = (Editorial) spEditorial.getSelectedItem();

              Book book =
                  new Book(
                      title,
                      description,
                      publicationDate,
                      replicas,
                      pages,
                      author,
                      bookcase,
                      editorial);

              DbBooks dbBooks = new DbBooks(getApplicationContext());
              long res = dbBooks.create(book);

              if (res > 0) {
                Toast.makeText(getApplicationContext(), "Libro creado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CreateBookActivity.this, BookActivity.class));
                finish();
              } else {
                Toast.makeText(
                        getApplicationContext(), "No se pudo crear el libro", Toast.LENGTH_SHORT)
                    .show();
              }
            }
          }
        });
  }

  public void spinnerData(Context context) {
    ArrayList<Author> authorList = new DbAuthors(context).getAuthors();
    ArrayAdapter<Author> authorAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, authorList);
    spAuthor.setAdapter(authorAdapter);

    ArrayList<Bookcase> bookcaseList = new DbBookcases(context).getBookcases();
    ArrayAdapter<Bookcase> bookcaseAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bookcaseList);
    spBookcase.setAdapter(bookcaseAdapter);

    ArrayList<Editorial> editorialList = new DbEditorials(context).getEditorials();
    ArrayAdapter<Editorial> editorialAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, editorialList);
    spEditorial.setAdapter(editorialAdapter);
  }
}
