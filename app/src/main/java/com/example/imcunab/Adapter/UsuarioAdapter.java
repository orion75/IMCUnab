package com.example.imcunab.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imcunab.R;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder> {
    List<Usuario> usuarioList;

    public UsuarioAdapter(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_usuarios, parent, false);
        return new UsuarioAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Usuario item = usuarioList.get(position);

        holder.txtNombres.setText(item.getNombres() + " " + item.getApellidos());
        holder.txtFechaNac.setText("Nacio el: " + item.getFechaNacimiento());
    }

    @Override
    public int getItemCount() { return usuarioList.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView txtNombres;
        private TextView txtFechaNac;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.tu_imageLabel);
            txtNombres = itemView.findViewById(R.id.tu_nombresLabel);
            txtFechaNac = itemView.findViewById(R.id.tu_emailLabel);
        }
    }
}
