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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mao.bibliotecaaa.R;
import com.mao.bibliotecaaa.activities.Bookcase.EditBookcaseActivity;
import com.mao.bibliotecaaa.db.DbBookcases;
import com.mao.bibliotecaaa.db.DbBooks;
import com.mao.bibliotecaaa.models.Book;
import com.mao.bibliotecaaa.models.Bookcase;

import java.util.ArrayList;

public class BookcaseAdapter extends RecyclerView.Adapter<BookcaseAdapter.ViewHolder> {
  ArrayList<Bookcase> bookcases;

  public BookcaseAdapter(ArrayList<Bookcase> bookcases) {
    this.bookcases = bookcases;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtBookcaseLetter, txtBookcaseNumber, txtBookcaseColor;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      txtBookcaseLetter = itemView.findViewById(R.id.txtBookcaseLetter);
      txtBookcaseNumber = itemView.findViewById(R.id.txtBookcaseNumber);
      txtBookcaseColor = itemView.findViewById(R.id.txtBookcaseColor);
    }

    void upData(Bookcase bookcase) {
      txtBookcaseLetter.setText(bookcase.getLetter());
      txtBookcaseNumber.setText(String.valueOf(bookcase.getNumber()));
      txtBookcaseColor.setText(bookcase.getColor());
    }
  }

  @NonNull
  @Override
  public BookcaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.bookcase_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(
      @NonNull BookcaseAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.upData(bookcases.get(position));

    holder.itemView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Bookcase bookcase = bookcases.get(position);
            Intent intent = new Intent(holder.itemView.getContext(), EditBookcaseActivity.class);
            intent.putExtra("bookcase", bookcase);
            holder.itemView.getContext().startActivity(intent);
          }
        });

    holder.itemView.setOnLongClickListener(
        new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View v) {
            Bookcase bookcase = bookcases.get(position);
            alert(holder.itemView.getContext(), bookcase);
            return false;
          }
        });
  }

  public boolean inUse(Context context, Bookcase bookcase){
    // Comprobar que no este en uso
    DbBooks books = new DbBooks(context);
    ArrayList<Book> booksList = books.getBooks();

    for (Book book : booksList) {
      if (book.getBookcase().getId() == bookcase.getId()) {
        return true;
      }
    }

    return false;
  }

  public void alert(Context context, Bookcase bookcase) {
    AlertDialog.Builder alert = new AlertDialog.Builder(context);
    alert.setTitle("¿Eliminar autor?");
    alert.setMessage(
        "¿Seguro que desea eliminar la estanteria "
            + bookcase.getLetter()
            + bookcase.getNumber()
            + " de color "
            + bookcase.getColor()
            + "?");
    alert.setPositiveButton(
        "Eliminar",
        new DialogInterface.OnClickListener() {
          @SuppressLint("NotifyDataSetChanged")
          @Override
          public void onClick(DialogInterface dialog, int which) {
            boolean used = inUse(context, bookcase);

            if (!used) {
              bookcases.remove(bookcase);
              DbBookcases dbBookcases = new DbBookcases(context);
              dbBookcases.delete(bookcase);

              notifyDataSetChanged();
            }else{
              Toast.makeText(context, "No es posible eliminar la estanteria, ya que, se encuentra en uso", Toast.LENGTH_LONG).show();
            }
          }
        });
    alert.setNegativeButton("Cancelar", null);
    alert.create();
    alert.show();
  }

  @Override
  public int getItemCount() {
    return bookcases.size();
  }
}
