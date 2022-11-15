package com.example.imcunab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.imcunab.databinding.ActivityAddRegistroBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddRegistroActivity extends AppCompatActivity {

    private ActivityAddRegistroBinding binding;
    private FirebaseUser usuario = null;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.progressbar.setVisibility(View.GONE);
        usuario = FirebaseAuth.getInstance().getCurrentUser();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:MM:ss", Locale.US);
        binding.fechaEditText.setText(dateFormat.format(Calendar.getInstance().getTime().getTime()));

        binding.calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { ValidarDatos(); }
        });
        binding.cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddRegistroActivity.this, ListRegistroActivity.class);
                finish();
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });
        if (usuario != null)
            enlazarDatos();
    }

    private void enlazarDatos() {
        db.collection("Usuarios").document(usuario.getEmail())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.getData() != null) {
                            String nombres = documentSnapshot.get("Nombres").toString();
                            String apellidos = documentSnapshot.get("Apellidos").toString();
                            binding.usuarioLabel.setText(nombres + " " + apellidos);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddRegistroActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void ValidarDatos() {
        binding.progressbar.setVisibility(View.VISIBLE);
        //validar datos
        if (TextUtils.isEmpty(binding.estaturaEditText.getText().toString().trim())) {
            binding.estaturaEditText.setError("Estatura: es requerido.");
        } else if (TextUtils.isEmpty(binding.pesoEditText.getText().toString().trim())) {
            binding.pesoEditText.setError("Apellidos: no es valido.");
        }  else {
            firebaseRegistro();
        }
        binding.progressbar.setVisibility(View.GONE);
    }

    private void firebaseRegistro() {
        Double estatura = Double.parseDouble(binding.estaturaEditText.getText().toString().trim()) / 100;
        estatura = Math.pow(estatura, 2);
        Double imc = Double.parseDouble(binding.pesoEditText.getText().toString().trim()) / estatura;
        binding.imcLabel.setText(String.format("TU INDICE DE MASA CORPORAL ES\n%.2f", imc));
        String result = "";
        if (imc < 18.5f)
            result = "Se encuentra dentro del rango de peso insuficiente.";
        else if  (imc >= 18.5f  && imc < 25f)
            result = "Se encuentra dentro del rango de peso normal o saludable.";
        else if  (imc >= 25f  && imc < 30f)
            result = "Se encuentra dentro del rango de sobrepeso.";
        else
            result = "Se encuentra dentro del rango de obesidad.";
        binding.resultLabel.setText(result);
        binding.calcularButton.setEnabled(false);


        String id = db.collection("Usuarios").document(usuario.getEmail()).collection("Pesos").document().getId();
        DocumentReference df = db.collection("Usuarios").document(usuario.getEmail()).collection("Pesos").document(id);
        Map<String, Object> pesoInfo = new HashMap<>();
        pesoInfo.put("Id", id);
        pesoInfo.put("Fecha", binding.fechaEditText.getText().toString().trim());
        pesoInfo.put("Altura", binding.estaturaEditText.getText().toString().trim());
        pesoInfo.put("Peso", binding.pesoEditText.getText().toString().trim());
        pesoInfo.put("Imc", String.format("%.2f", imc));
        df.set(pesoInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(AddRegistroActivity.this, "Datos guardados", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddRegistroActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}