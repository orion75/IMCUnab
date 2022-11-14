package com.example.imcunab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.imcunab.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth firebaseAuth;
    private String email = "", password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.progressbar.setVisibility(View.GONE);
        firebaseAuth = FirebaseAuth.getInstance();
        binding.registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrarItent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(registrarItent);
            }
        });

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { ValidarDatos(); }
        });
    }

    private void ValidarDatos() {
        email = binding.emailEditText.getText().toString().trim();
        password = binding.passwordEditText.getText().toString().trim();

        //validar datos
        if (TextUtils.isEmpty(email)) {
            binding.emailEditText.setError("Email: es requerido.");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEditText.setError("Email: no es valido.");
        } else if (TextUtils.isEmpty(password)) {
            binding.passwordEditText.setError("password: es requerido.");
        } else if (password.length() < 6) {
            binding.passwordEditText.setError("password: mínimo 6 caracteres.");
        } else {
            firebaseLogin();
        }
    }

    private void firebaseLogin() {
        binding.progressbar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        binding.progressbar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "Conectado\n" + user.getEmail(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        binding.progressbar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}