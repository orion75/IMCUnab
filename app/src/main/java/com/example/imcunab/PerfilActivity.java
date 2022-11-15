package com.example.imcunab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.imcunab.databinding.ActivityPerfilBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PerfilActivity extends AppCompatActivity {

    private ActivityPerfilBinding binding;
    private FirebaseUser usuario = null;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    final Calendar myCalendar= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        usuario = FirebaseAuth.getInstance().getCurrentUser();
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        binding.fechaNacimientoEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(PerfilActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        binding.aceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { ValidarDatos(); }
        });
        if (usuario != null)
            enlazarDatos();
        binding.progressbar.setVisibility(View.GONE);
    }

    private void updateLabel() {
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        binding.fechaNacimientoEditText.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void enlazarDatos() {
        db.collection("Usuarios").document(usuario.getEmail())
           .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.getData() != null) {
                            binding.nombresEditText.setText(documentSnapshot.get("Nombres").toString());
                            binding.apellidosEditText.setText(documentSnapshot.get("Apellidos").toString());
                            binding.fechaNacimientoEditText.setText(documentSnapshot.get("FechaNacimiento").toString());
                        }
                        binding.emailEditText.setText(usuario.getEmail());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PerfilActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void ValidarDatos() {
        binding.progressbar.setVisibility(View.VISIBLE);
        //validar datos
        if (TextUtils.isEmpty(binding.nombresEditText.getText().toString().trim())) {
            binding.nombresEditText.setError("Nombres: es requerido.");
        } else if (TextUtils.isEmpty(binding.apellidosEditText.getText().toString().trim())) {
            binding.apellidosEditText.setError("Apellidos: no es valido.");
        } else if (TextUtils.isEmpty(binding.fechaNacimientoEditText.getText().toString().trim())) {
            binding.fechaNacimientoEditText.setError("Fecha nacimiento: es requerido.");
        } else {
            firebaseRegistro();
        }
        binding.progressbar.setVisibility(View.GONE);
        //Todo
    }

    private  void  firebaseRegistro() {
        Map<String, Object> perfil = new HashMap<>();
        perfil.put("Nombres", binding.nombresEditText.getText().toString().trim());
        perfil.put("Apellidos",  binding.apellidosEditText.getText().toString().trim());
        perfil.put("FechaNacimiento", binding.fechaNacimientoEditText.getText().toString().trim());
        db.collection("Usuarios").document(usuario.getEmail())
                .set(perfil).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PerfilActivity.this, "datos guardados", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(PerfilActivity.this, ListRegistroActivity.class);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PerfilActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}