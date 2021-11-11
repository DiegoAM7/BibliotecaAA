package com.mao.bibliotecaaa.activities.Bookcase;

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
import com.mao.bibliotecaaa.db.DbBookcases;
import com.mao.bibliotecaaa.models.Bookcase;

public class CreateBookcaseActivity extends AppCompatActivity {
  TextInputEditText etxtLetter, etxtNumber, etxtColor;
  Button btnSave;

  AwesomeValidation validation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_bookcase);

    OnBackPressedCallback back =
        new OnBackPressedCallback(true) {
          @Override
          public void handleOnBackPressed() {
            startActivity(new Intent(CreateBookcaseActivity.this, BookcaseActivity.class));
            finish();
          }
        };
    this.getOnBackPressedDispatcher().addCallback(this, back);

    etxtLetter = findViewById(R.id.etxtBookcaseLetter);
    etxtNumber = findViewById(R.id.etxtBookcaseNumber);
    etxtColor = findViewById(R.id.etxtBookcaseColor);
    btnSave = findViewById(R.id.btnCreateBookcaseSave);

    validation = new AwesomeValidation(ValidationStyle.BASIC);

    validation.addValidation(
        this, R.id.etxtBookcaseLetter, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(
        this, R.id.etxtBookcaseNumber, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(this, R.id.etxtBookcaseColor, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(this, R.id.etxtBookcaseNumber, "[0-9]+", R.string.number);

    btnSave.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            String letter = etxtLetter.getText().toString();
            int number = Integer.parseInt(etxtNumber.getText().toString());
            String color = etxtColor.getText().toString();

            Bookcase bookcase = new Bookcase(letter, number, color);
            DbBookcases dbBookcases = new DbBookcases(getApplicationContext());
            long res = dbBookcases.create(bookcase);

            if (res > 0) {
              Toast.makeText(getApplicationContext(), "Estantería creada", Toast.LENGTH_SHORT)
                  .show();
              startActivity(new Intent(CreateBookcaseActivity.this, BookcaseActivity.class));
              finish();
            } else {
              Toast.makeText(
                      getApplicationContext(), "No se pudo crear la estantería", Toast.LENGTH_SHORT)
                  .show();
            }
          }
        });
  }
}
