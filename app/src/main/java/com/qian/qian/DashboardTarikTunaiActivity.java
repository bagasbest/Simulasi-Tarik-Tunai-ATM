package com.qian.qian;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.qian.qian.databinding.ActivityDashboardTarikTunaiBinding;

public class DashboardTarikTunaiActivity extends AppCompatActivity {

    private ActivityDashboardTarikTunaiBinding binding;
    private long nominal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardTarikTunaiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /// logout
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        /// Fungsi fungsi di bawah ini merupakan tombol dari 100.000 hingga 1.000.000
        /// dimana user dapat menekan salah satu tombol untuk menarik uang
        /// setiap tombol memiliki angka berbeda
        binding.num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nominal = 100000;
                showConfirmBalance();
            }
        });
        binding.num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nominal = 200000;
                showConfirmBalance();
            }
        });
        binding.num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nominal = 300000;
                showConfirmBalance();
            }
        });
        binding.num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nominal = 400000;
                showConfirmBalance();

            }
        });
        binding.num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nominal = 500000;
                showConfirmBalance();
            }
        });
        binding.num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nominal = 600000;
                showConfirmBalance();

            }
        });
        binding.num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nominal = 700000;
                showConfirmBalance();

            }
        });
        binding.num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nominal = 800000;
                showConfirmBalance();

            }
        });
        binding.num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nominal = 900000;
                showConfirmBalance();

            }
        });
        binding.num10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nominal = 1000000;
                showConfirmBalance();

            }
        });

    }

    private void logout() {
        new AlertDialog.Builder(this)
                .setTitle("Konformasi Logout")
                .setMessage("Apakah anda yakin ingin logout sekarang ?")
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setPositiveButton("YA", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    Intent intent = new Intent(DashboardTarikTunaiActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("TIDAK", null)
                .show();
    }


    /// fungsi ini untuk menandai, tombol mana yang ditekan pengguna, yang memuat nominal tarik tunai
    /// intent ke Tarik Tunai Confirmation Activity
    private void showConfirmBalance() {
        Intent intent = new Intent(DashboardTarikTunaiActivity.this, TarikTunaiConfirmationActivity.class);
        intent.putExtra(TarikTunaiConfirmationActivity.EXTRA_NOMINAL, nominal);
        startActivity(intent);
    }

    /// HAPUSKAN ACTIVITY KETIKA SUDAH TIDAK DIGUNAKAN, AGAR MENGURANGI RISIKO MEMORY LEAKS
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}