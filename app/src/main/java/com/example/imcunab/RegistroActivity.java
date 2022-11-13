package com.example.imcunab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.imcunab.databinding.ActivityRegistroBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    private ActivityRegistroBinding binding;
    private String email = "", password = "", validpassword = "";
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        binding.registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidarDatos();
            }
        });
    }

    private void ValidarDatos() {
        email = binding.emailEditText.getText().toString().trim();
        password = binding.passwordEditText.getText().toString().trim();
        validpassword = binding.validPasswordEditText.getText().toString().trim();

        //validar datos
        if (TextUtils.isEmpty(email)) {
            binding.emailEditText.setError("Email: es requerido.");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEditText.setError("Email: no es valido.");
        } else if (TextUtils.isEmpty(password)) {
            binding.passwordEditText.setError("password: es requerido.");
        } else if (password.length() < 6) {
            binding.passwordEditText.setError("password: mínimo 6 caracteres.");
        } else  if (!password.equals(validpassword)) {
            binding.validPasswordEditText.setError("password: no son iguales.");
        } else {
            firebaseRegistro();
        }
    }

    private void firebaseRegistro() {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistroActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}