package com.mao.bibliotecaaa.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mao.bibliotecaaa.R;
import com.mao.bibliotecaaa.activities.Book.EditBookActivity;
import com.mao.bibliotecaaa.db.DbBooks;
import com.mao.bibliotecaaa.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
  ArrayList<Book> books;
  ArrayList<Book> booksOriginal;

  public BookAdapter(ArrayList<Book> books) {
    this.books = books;
    this.booksOriginal = new ArrayList<>();
    this.booksOriginal.addAll(this.books);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtBookBookcase,
        txtBookTitle,
        txtBookDescription,
        txtBookAuthor,
        txtBookPublishDate,
        txtBookEditorial,
        txtBookPages,
        txtBookReplicas;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      txtBookBookcase = itemView.findViewById(R.id.txtBookBookcase);
      txtBookTitle = itemView.findViewById(R.id.txtBookTitle);
      txtBookDescription = itemView.findViewById(R.id.txtBookDescription);
      txtBookAuthor = itemView.findViewById(R.id.txtBookAuthor);
      txtBookPublishDate = itemView.findViewById(R.id.txtBookPublishDate);
      txtBookEditorial = itemView.findViewById(R.id.txtBookEditorial);
      txtBookPages = itemView.findViewById(R.id.txtBookPages);
      txtBookReplicas = itemView.findViewById(R.id.txtBookReplicas);
    }

    void upData(Book book) {
      txtBookBookcase.setText(book.getBookcase().toString());
      txtBookTitle.setText(book.getTitle());
      txtBookDescription.setText(book.getDescription());
      txtBookAuthor.setText(book.getAuthor().toString());
      txtBookPublishDate.setText(book.getPublicationDate());
      txtBookEditorial.setText(book.getEditorial().toString());
      txtBookPages.setText(String.valueOf(book.getPages()));
      txtBookReplicas.setText(String.valueOf(book.getReplicas()));
    }
  }

  @NonNull
  @Override
  public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(
      @NonNull BookAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.upData(books.get(position));

    holder.itemView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Book book = books.get(position);
            Intent intent = new Intent(holder.itemView.getContext(), EditBookActivity.class);
            intent.putExtra("book", book);
            holder.itemView.getContext().startActivity(intent);
          }
        });

    holder.itemView.setOnLongClickListener(
        new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View v) {
            Book book = books.get(position);
            alert(holder.itemView.getContext(), book);
            return false;
          }
        });
  }

  public void alert(Context context, Book book) {
    AlertDialog.Builder alert = new AlertDialog.Builder(context);
    alert.setTitle("¿Eliminar autor?");
    alert.setMessage("¿Seguro que desea eliminar el libro " + book.getTitle() + "?");
    alert.setPositiveButton(
        "Eliminar",
        new DialogInterface.OnClickListener() {
          @SuppressLint("NotifyDataSetChanged")
          @Override
          public void onClick(DialogInterface dialog, int which) {
            books.remove(book);
            DbBooks dbBooks = new DbBooks(context);
            dbBooks.delete(book);

            notifyDataSetChanged();
          }
        });
    alert.setNegativeButton("Cancelar", null);
    alert.create();
    alert.show();
  }

  @SuppressLint("NotifyDataSetChanged")
  public void filter(String search) {
    int length = search.length();

    books.clear();
    books.addAll(booksOriginal);

    if (length != 0) {
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        List<Book> bookListTitle =
            books.stream().filter(i -> i.getTitle().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
        List<Book> bookListAuthorNames =
                books.stream().filter(i -> i.getAuthor().getNames().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
        List<Book> bookListAuthorLastnames =
                books.stream().filter(i -> i.getAuthor().getLastnames().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
        List<Book> bookListEditorial =
                books.stream().filter(i -> i.getEditorial().getName().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
        books.clear();
        books.addAll(bookListTitle);
        books.addAll(bookListAuthorNames);
        books.addAll(bookListAuthorLastnames);
        books.addAll(bookListEditorial);
      } else {
        for(Book book: booksOriginal) {
          if (book.getTitle().toLowerCase().contains(search.toLowerCase())) {
            books.add(book);
          }
        }
      }
    }

    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return books.size();
  }
}
