package com.mao.bibliotecaaa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mao.bibliotecaaa.R;
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
  public void onBindViewHolder(@NonNull EditorialAdapter.ViewHolder holder, int position) {
    holder.upData(editorials.get(position));
  }

  @Override
  public int getItemCount() {
    return editorials.size();
  }
}
