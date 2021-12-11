package com.qian.qian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qian.qian.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /// tampilkan logo
        Glide.with(this)
                .load(R.drawable.logo)
                .into(binding.logo);


        //// intent ke halaman dashboard tarik tunai
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.username.getText().toString().trim();
                String password = binding.password.getText().toString().trim();

                /// cek jika username dan password login apakah sesuai atau tidak
                if(username.equals("user") && password.equals("user12345")) {
                    startActivity(new Intent(LoginActivity.this, DashboardTarikTunaiActivity.class));
                    finish();
                } else if(username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Maaf, username atau kata sandi harus diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Maaf, username atau kata sandi anda salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}