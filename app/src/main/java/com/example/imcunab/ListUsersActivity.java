package com.example.imcunab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.imcunab.Adapter.PesoUsuario;
import com.example.imcunab.Adapter.PesoUsuarioAdapter;
import com.example.imcunab.Adapter.Usuario;
import com.example.imcunab.Adapter.UsuarioAdapter;
import com.example.imcunab.databinding.ActivityListRegistroBinding;
import com.example.imcunab.databinding.ActivityListUsersBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ListUsersActivity extends AppCompatActivity {

    private ActivityListUsersBinding binding;
    private RecyclerView recyclerUsuarios;
    private UsuarioAdapter usuarioAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Usuario> usuarioList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerUsuarios = binding.recyclerUsuarios;
        recyclerUsuarios.setHasFixedSize(true);
        getAllUserDocuments();

    }
    private void getAllUserDocuments () {

        db.collection("Usuarios").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                usuarioList = queryDocumentSnapshots.toObjects(Usuario.class);
                usuarioAdapter = new UsuarioAdapter(usuarioList);
                recyclerUsuarios.setLayoutManager(new LinearLayoutManager(ListUsersActivity.this));
                recyclerUsuarios.setAdapter(usuarioAdapter);
            }
        });
    }
}