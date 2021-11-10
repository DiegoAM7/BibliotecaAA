package com.mao.bibliotecaaa.activities.Book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.mao.bibliotecaaa.R;
import com.mao.bibliotecaaa.adapters.BookAdapter;
import com.mao.bibliotecaaa.adapters.EditorialAdapter;
import com.mao.bibliotecaaa.db.DbBooks;
import com.mao.bibliotecaaa.db.DbEditorials;

public class BookActivity extends AppCompatActivity {
    Button btnCreateBook;
    RecyclerView recyclerBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        btnCreateBook = findViewById(R.id.btnCreateBook);
        recyclerBook = findViewById(R.id.recyclerBook);

        upData();
    }

    public void upData() {
        recyclerBook.setLayoutManager(new LinearLayoutManager(this));
        DbBooks books = new DbBooks(this);
        BookAdapter adapter = new BookAdapter(books.getBooks());
        recyclerBook.setAdapter(adapter);
    }
}
