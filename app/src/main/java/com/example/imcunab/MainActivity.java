package com.example.imcunab;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.imcunab.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Fibase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();
        //Buttons
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
        //
        binding.AdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginAdminIntent = new Intent(MainActivity.this, LoginAdminActivity.class);
                startActivity(loginAdminIntent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        firebaseAuth.signOut();
        super.onDestroy();
    }

    private void checkUser() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            //startActivity(new Intent(this, AddRegistroActivity.class));
            //startActivity(new Intent(this, PerfilActivity.class));
        }
    }
}