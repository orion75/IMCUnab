package com.example.imcunab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.imcunab.Adapter.PesoUsuario;
import com.example.imcunab.Adapter.PesoUsuarioAdapter;
import com.example.imcunab.databinding.ActivityListRegistroBinding;
import com.example.imcunab.databinding.ActivityRegistroBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Map;

public class ListRegistroActivity extends AppCompatActivity {

    private ActivityListRegistroBinding binding;
    private RecyclerView recyclerPesoUsuarios;
    private PesoUsuarioAdapter pesoUsuarioAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<PesoUsuario> pesosUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        recyclerPesoUsuarios = binding.recyclerPesoUsuarios;
        recyclerPesoUsuarios.setHasFixedSize(true);

        db.collection("Usuarios").document(usuario.getEmail())
            .collection("Pesos").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        pesosUsuario =  queryDocumentSnapshots.toObjects(PesoUsuario.class);
                        pesoUsuarioAdapter = new PesoUsuarioAdapter(pesosUsuario/*, ListRegistroActivity.this*/);
                        recyclerPesoUsuarios.setLayoutManager(new LinearLayoutManager(ListRegistroActivity.this));
                        recyclerPesoUsuarios.setAdapter(pesoUsuarioAdapter);
                    }
                });
        binding.agregarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListRegistroActivity.this, AddRegistroActivity.class);
                finish();
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);

            }
        });
    }
}