package com.mao.bibliotecaaa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mao.bibliotecaaa.R;
import com.mao.bibliotecaaa.models.Book;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
  ArrayList<Book> books;

  public BookAdapter(ArrayList<Book> books) {
    this.books = books;
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
      txtBookBookcase.setText(
          book.getBookcase().getLetter()
              + book.getBookcase().getNumber()
              + " - "
              + book.getBookcase().getColor());
      txtBookTitle.setText(book.getTitle());
      txtBookDescription.setText(book.getDescription());
      txtBookAuthor.setText(book.getAuthor().getNames() + " " + book.getAuthor().getLastnames());
      txtBookPublishDate.setText(book.getPublicationDate());
      txtBookEditorial.setText(book.getEditorial().getName());
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
  public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
    holder.upData(books.get(position));
  }

  @Override
  public int getItemCount() {
    return books.size();
  }
}
