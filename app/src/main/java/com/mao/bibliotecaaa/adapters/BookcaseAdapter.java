package com.mao.bibliotecaaa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mao.bibliotecaaa.R;
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
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookcase_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull BookcaseAdapter.ViewHolder holder, int position) {
    holder.upData(bookcases.get(position));
  }

  @Override
  public int getItemCount() {
    return bookcases.size();
  }
}
