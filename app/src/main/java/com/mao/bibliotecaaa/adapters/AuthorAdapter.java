package com.mao.bibliotecaaa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mao.bibliotecaaa.R;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.author_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorAdapter.ViewHolder holder, int position) {
        holder.upData(authors.get(position));

        // Metodos de OnClick
    }

    @Override
    public int getItemCount() {
        return authors.size();
    }
}
