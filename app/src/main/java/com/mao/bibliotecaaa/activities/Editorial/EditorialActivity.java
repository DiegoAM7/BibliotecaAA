package com.mao.bibliotecaaa.activities.Editorial;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mao.bibliotecaaa.MainActivity;
import com.mao.bibliotecaaa.R;
import com.mao.bibliotecaaa.activities.Bookcase.BookcaseActivity;
import com.mao.bibliotecaaa.activities.Bookcase.CreateBookcaseActivity;
import com.mao.bibliotecaaa.adapters.EditorialAdapter;
import com.mao.bibliotecaaa.db.DbEditorials;
import com.mao.bibliotecaaa.models.Editorial;

import java.util.ArrayList;

public class EditorialActivity extends AppCompatActivity {
  Button btnCreateEditorial;
  RecyclerView recyclerEditorial;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_editorial);

    OnBackPressedCallback back =
            new OnBackPressedCallback(true) {
              @Override
              public void handleOnBackPressed() {
                startActivity(new Intent(EditorialActivity.this, MainActivity.class));
                finish();
              }
            };
    this.getOnBackPressedDispatcher().addCallback(this, back);

    btnCreateEditorial = findViewById(R.id.btnCreateEditorial);
    recyclerEditorial = findViewById(R.id.recyclerEditorial);

    upData();

    btnCreateEditorial.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(EditorialActivity.this, CreateEditorialActivity.class));
        finish();
      }
    });
  }

  public void upData() {
    recyclerEditorial.setLayoutManager(new LinearLayoutManager(this));
    DbEditorials editorials = new DbEditorials(this);
    ArrayList<Editorial> editorialList = editorials.getEditorials();

    if (editorialList != null) {
      EditorialAdapter adapter = new EditorialAdapter(editorials.getEditorials());
      recyclerEditorial.setAdapter(adapter);
    }
  }
}
