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
import com.mao.bibliotecaaa.activities.Editorial.EditEditorialActivity;
import com.mao.bibliotecaaa.db.DbBooks;
import com.mao.bibliotecaaa.db.DbEditorials;
import com.mao.bibliotecaaa.models.Author;
import com.mao.bibliotecaaa.models.Book;
import com.mao.bibliotecaaa.models.Editorial;

import java.util.ArrayList;

public class EditorialAdapter extends RecyclerView.Adapter<EditorialAdapter.ViewHolder> {
  ArrayList<Editorial> editorials;

  public EditorialAdapter(ArrayList<Editorial> editorials) {
    this.editorials = editorials;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtEditorialName, txtEditorialNationality;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      txtEditorialName = itemView.findViewById(R.id.txtEditorialName);
      txtEditorialNationality = itemView.findViewById(R.id.txtEditorialNationality);
    }

    void upData(Editorial editorial) {
      txtEditorialName.setText(editorial.getName());
      txtEditorialNationality.setText(editorial.getNationality());
    }
  }

  @NonNull
  @Override
  public EditorialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.editorial_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull EditorialAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.upData(editorials.get(position));

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Editorial editorial = editorials.get(position);
        Intent intent = new Intent(holder.itemView.getContext(), EditEditorialActivity.class);
        intent.putExtra("editorial", editorial);
        holder.itemView.getContext().startActivity(intent);
      }
    });

    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        Editorial editorial = editorials.get(position);
        alert(holder.itemView.getContext(), editorial);
        return false;
      }
    });
  }

  public boolean inUse(Context context, Editorial editorial){
    // Comprobar que no este en uso
    DbBooks books = new DbBooks(context);
    ArrayList<Book> booksList = books.getBooks();

    for (Book book : booksList) {
      if (book.getAuthor().getId() == editorial.getId()) {
        return true;
      }
    }

    return false;
  }

  public void alert(Context context, Editorial editorial) {
    AlertDialog.Builder alert = new AlertDialog.Builder(context);
    alert.setTitle("¿Eliminar autor?");
    alert.setMessage("¿Seguro que desea eliminar la editorial " + editorial.getName() + "?");
    alert.setPositiveButton(
        "Eliminar",
        new DialogInterface.OnClickListener() {
          @SuppressLint("NotifyDataSetChanged")
          @Override
          public void onClick(DialogInterface dialog, int which) {
            boolean used = inUse(context, editorial);

            if (!used) {
              editorials.remove(editorial);
              DbEditorials dbEditorials = new DbEditorials(context);
              dbEditorials.delete(editorial);

              notifyDataSetChanged();
            } else {
              Toast.makeText(context, "No es posible eliminar la editorial, ya que, se encuentra en uso", Toast.LENGTH_LONG).show();
            }
          }
        });
    alert.setNegativeButton("Cancelar", null);
    alert.create();
    alert.show();
  }

  @Override
  public int getItemCount() {
    return editorials.size();
  }
}
