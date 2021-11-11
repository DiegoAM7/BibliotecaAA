package com.mao.bibliotecaaa.activities.Editorial;

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
import com.mao.bibliotecaaa.db.DbEditorials;
import com.mao.bibliotecaaa.models.Editorial;

public class EditEditorialActivity extends AppCompatActivity {
  TextInputEditText etxtName, etxtNationality;
  Button btnSave;

  AwesomeValidation validation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_editorial);

    OnBackPressedCallback back =
        new OnBackPressedCallback(true) {
          @Override
          public void handleOnBackPressed() {
            startActivity(new Intent(EditEditorialActivity.this, EditorialActivity.class));
            finish();
          }
        };
    this.getOnBackPressedDispatcher().addCallback(this, back);

    etxtName = findViewById(R.id.etxtEditorialName);
    etxtNationality = findViewById(R.id.etxtEditorialNationality);
    btnSave = findViewById(R.id.btnCreateEditorialSave);

    btnSave.setText(R.string.edit);

    validation = new AwesomeValidation(ValidationStyle.BASIC);

    validation.addValidation(this, R.id.etxtEditorialName, RegexTemplate.NOT_EMPTY, R.string.empty);
    validation.addValidation(
        this, R.id.etxtEditorialNationality, RegexTemplate.NOT_EMPTY, R.string.empty);

    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();
    Editorial editorial = (Editorial) bundle.get("editorial");

    etxtName.setText(editorial.getName());
    etxtNationality.setText(editorial.getNationality());

    btnSave.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            int id = editorial.getId();
            String name = etxtName.getText().toString();
            String nationality = etxtNationality.getText().toString();

            Editorial editorialEdit = new Editorial(id, name, nationality);
            DbEditorials dbEditorials = new DbEditorials(getApplicationContext());
            dbEditorials.edit(editorialEdit);

            startActivity(new Intent(EditEditorialActivity.this, EditorialActivity.class));
            finish();
          }
        });
  }
}
