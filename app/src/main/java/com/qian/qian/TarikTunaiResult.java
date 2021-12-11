package com.qian.qian;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.qian.qian.databinding.ActivityTarikTunaiResultBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TarikTunaiResult extends AppCompatActivity {

    public static final String EXTRA_NOMINAL = "nominal";
    private ActivityTarikTunaiResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTarikTunaiResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /// kembali ke halaman sebelumnya (konfirmasi tarik tunai)
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        /// batalkan tarik tunai dan menuju dashoard
        binding.cancelWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelWithdraw();
            }
        });


        /// Konfirmasi tarik tunai, akan memunculkan popup berhasil menarik tunai
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSuccessDialog();
            }
        });


        /// copy code tarik tunai
        binding.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TarikTunaiResult.this, "Menyalin kode ke clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /// fungsi untuk batalkan tarik tunai
    private void cancelWithdraw() {
        new AlertDialog.Builder(this)
                .setTitle("Konformasi Batalkan Tarik Tunai")
                .setMessage("Apakah anda yakin ingin membatalkan tarik tunai ini ?")
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setPositiveButton("YA", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    Intent intent = new Intent(TarikTunaiResult.this, DashboardTarikTunaiActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("TIDAK", null)
                .show();
    }

    /// munculkan dialog ketika sukses tarik tunai
    private void showSuccessDialog() {
        NumberFormat formatter = new DecimalFormat("#,###");
        new AlertDialog.Builder(this)
                .setTitle("Berhasil melakukan tarik tunai")
                .setMessage("Silahkan ambil uang anda sebesar Rp. " + formatter.format(getIntent().getLongExtra(EXTRA_NOMINAL, 0)))
                .setIcon(R.drawable.ic_baseline_check_circle_outline_24)
                .setPositiveButton("OKE", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    Intent intent = new Intent(TarikTunaiResult.this, DashboardTarikTunaiActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    dialogInterface.dismiss();
                    startActivity(intent);
                    finish();
                })
                .show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}