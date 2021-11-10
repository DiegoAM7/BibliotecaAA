package com.mao.bibliotecaaa.activities.Editorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.mao.bibliotecaaa.R;
import com.mao.bibliotecaaa.adapters.EditorialAdapter;
import com.mao.bibliotecaaa.db.DbEditorials;

public class EditorialActivity extends AppCompatActivity {
    Button btnCreateEditorial;
    RecyclerView recyclerEditorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editorial);

        btnCreateEditorial = findViewById(R.id.btnCreateEditorial);
        recyclerEditorial = findViewById(R.id.recyclerEditorial);

        upData();
    }

    public void upData() {
        recyclerEditorial.setLayoutManager(new LinearLayoutManager(this));
        DbEditorials editorials = new DbEditorials(this);
        EditorialAdapter adapter = new EditorialAdapter(editorials.getEditorials());
        recyclerEditorial.setAdapter(adapter);
    }
}