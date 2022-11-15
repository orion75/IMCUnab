package com.example.imcunab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.imcunab.databinding.ActivityLoginAdminBinding;
import com.example.imcunab.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginAdminActivity extends AppCompatActivity {
    private ActivityLoginAdminBinding binding;


    private String email = "", password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
        } else if (TextUtils.isEmpty(password)) {
            binding.passwordEditText.setError("password: es requerido.");
        } else if (password.length() < 6) {
            binding.passwordEditText.setError("password: mínimo 6 caracteres.");
        } else {
            ValidLogin();
        }
    }

    private void ValidLogin() {
        String user = binding.emailEditText.getText().toString().trim();
        String pwd = binding.passwordEditText.getText().toString().trim();
        if (user.equals("Administrador") && password.equals("Aa_123")) {
            Intent i = new Intent(LoginAdminActivity.this, ListUsersActivity.class);
            finish();
            overridePendingTransition(0, 0);
            startActivity(i);
            overridePendingTransition(0, 0);
        } else
            Toast.makeText(LoginAdminActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
    }
}