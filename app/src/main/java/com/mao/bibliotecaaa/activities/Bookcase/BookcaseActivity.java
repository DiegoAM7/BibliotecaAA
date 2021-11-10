package com.mao.bibliotecaaa.activities.Bookcase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.mao.bibliotecaaa.R;
import com.mao.bibliotecaaa.adapters.BookcaseAdapter;
import com.mao.bibliotecaaa.db.DbBookcases;

public class BookcaseActivity extends AppCompatActivity {
    Button btnCreateBookcase;
    RecyclerView recyclerBookcase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookcase);

        btnCreateBookcase = findViewById(R.id.btnCreateBookcase);
        recyclerBookcase = findViewById(R.id.recyclerBookcase);

        upData();
    }

    public void upData() {
        recyclerBookcase.setLayoutManager(new LinearLayoutManager(this));
        DbBookcases bookcases = new DbBookcases(this);
        BookcaseAdapter adapter = new BookcaseAdapter(bookcases.getBookcases());
        recyclerBookcase.setAdapter(adapter);
    }
}