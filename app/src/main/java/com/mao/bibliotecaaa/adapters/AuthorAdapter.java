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
import com.mao.bibliotecaaa.activities.Author.EditAuthorActivity;
import com.mao.bibliotecaaa.db.DbAuthors;
import com.mao.bibliotecaaa.models.Author;

import java.util.ArrayList;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.ViewHolder> {
  ArrayList<Author> authors;

  public AuthorAdapter(ArrayList<Author> authors) {
    this.authors = authors;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    // Instancia de componentes de vista
    TextView txtAuthorNames, txtAuthorLastnames, txtAuthorNationality;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      // Se asigna componente con recurso
      txtAuthorNames = itemView.findViewById(R.id.txtAuthorNames);
      txtAuthorLastnames = itemView.findViewById(R.id.txtAuthorLastnames);
      txtAuthorNationality = itemView.findViewById(R.id.txtAuthorNationality);
    }

    void upData(Author author) {
      // Se añaden los datos a los componentes de vista
      txtAuthorNames.setText(author.getNames());
      txtAuthorLastnames.setText(author.getLastnames());
      txtAuthorNationality.setText(author.getNationality());
    }
  }

  @NonNull
  @Override
  public AuthorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.author_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(
      @NonNull AuthorAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.upData(authors.get(position));

    // Metodos de OnClick
    holder.itemView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Author author = authors.get(position);
            Intent intent = new Intent(holder.itemView.getContext(), EditAuthorActivity.class);
            intent.putExtra("author", author);
            holder.itemView.getContext().startActivity(intent);
          }
        });

    holder.itemView.setOnLongClickListener(
        new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View v) {
            Author author = authors.get(position);
            alert(holder.itemView.getContext(), author);
            return false;
          }
        });
  }

  public void alert(Context context, Author author) {
    AlertDialog.Builder alert = new AlertDialog.Builder(context);
    alert.setTitle("¿Eliminar autor?");
    alert.setMessage("¿Seguro que desea eliminar al autor " + author.getNames() + "?");
    alert.setPositiveButton(
        "Eliminar",
        new DialogInterface.OnClickListener() {
          @SuppressLint("NotifyDataSetChanged")
          @Override
          public void onClick(DialogInterface dialog, int which) {
            authors.remove(author);
            DbAuthors dbAuthors = new DbAuthors(context);
            dbAuthors.delete(author);

            notifyDataSetChanged();
          }
        });
    alert.setNegativeButton("Cancelar", null);
    alert.create();
    alert.show();
  }

  @Override
  public int getItemCount() {
    return authors.size();
  }
}
