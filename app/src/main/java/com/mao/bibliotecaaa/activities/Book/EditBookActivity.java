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

public class EditBookActivity extends AppCompatActivity {
  TextInputEditText etxtTitle, etxtDescription, etxtPublicationDate, etxtReplicas, etxtPages;
  Spinner spAuthor, spEditorial, spBookcase;
  Button btnSave;
  ArrayList<Author> authorList;
  ArrayList<Bookcase> bookcaseList;
  ArrayList<Editorial> editorialList;

  AwesomeValidation validation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_book);

    OnBackPressedCallback back =
        new OnBackPressedCallback(true) {
          @Override
          public void handleOnBackPressed() {
            startActivity(new Intent(EditBookActivity.this, BookActivity.class));
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

    btnSave.setText(R.string.edit);

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

    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();
    Book book = (Book) bundle.get("book");

    etxtTitle.setText(book.getTitle());
    etxtDescription.setText(book.getDescription());
    etxtPublicationDate.setText(book.getPublicationDate());
    etxtReplicas.setText(String.valueOf(book.getReplicas()));
    etxtPages.setText(String.valueOf(book.getPages()));

    int indexAuthor = getIndexAuthor(book);
    int indexEditorial = getIndexEditorial(book);
    int indexBookcase = getIndexBookcase(book);

    spAuthor.setSelection(indexAuthor);
    spEditorial.setSelection(indexEditorial);
    spBookcase.setSelection(indexBookcase);

    btnSave.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            int id = book.getId();
            String title = etxtTitle.getText().toString();
            String description = etxtDescription.getText().toString();
            String publicationDate = etxtPublicationDate.getText().toString();
            int replicas = Integer.parseInt(etxtReplicas.getText().toString());
            int pages = Integer.parseInt(etxtPages.getText().toString());
            Author author = (Author) spAuthor.getSelectedItem();
            Bookcase bookcase = (Bookcase) spBookcase.getSelectedItem();
            Editorial editorial = (Editorial) spEditorial.getSelectedItem();

            Book bookEdit =
                new Book(
                    id,
                    title,
                    description,
                    publicationDate,
                    replicas,
                    pages,
                    author,
                    bookcase,
                    editorial);
            DbBooks dbBooks = new DbBooks(getApplicationContext());
            dbBooks.edit(bookEdit);

            startActivity(new Intent(EditBookActivity.this, BookActivity.class));
            finish();
          }
        });
  }

  public int getIndexAuthor(Book book) {
    int index = 0;

    for (Author author : authorList) {
      if (author.toString().equals(book.getAuthor().toString())) {
        return index;
      }

      index += 1;
    }

    return -1;
  }

  public int getIndexEditorial(Book book) {
    int index = 0;

    for (Editorial editorial : editorialList) {
      if (editorial.toString().equals(book.getEditorial().toString())) {
        return index;
      }

      index += 1;
    }

    return -1;
  }

  public int getIndexBookcase(Book book) {
    int index = 0;

    for (Bookcase bookcase : bookcaseList) {
      if (bookcase.toString().equals(book.getBookcase().toString())) {
        return index;
      }

      index += 1;
    }

    return -1;
  }

  public void spinnerData(Context context) {
    authorList = new DbAuthors(context).getAuthors();
    ArrayAdapter<Author> authorAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, authorList);
    spAuthor.setAdapter(authorAdapter);

    bookcaseList = new DbBookcases(context).getBookcases();
    ArrayAdapter<Bookcase> bookcaseAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bookcaseList);
    spBookcase.setAdapter(bookcaseAdapter);

    editorialList = new DbEditorials(context).getEditorials();
    ArrayAdapter<Editorial> editorialAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, editorialList);
    spEditorial.setAdapter(editorialAdapter);
  }
}
