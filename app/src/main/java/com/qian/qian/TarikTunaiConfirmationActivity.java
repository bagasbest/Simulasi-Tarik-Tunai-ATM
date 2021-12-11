package com.qian.qian;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qian.qian.databinding.ActivityTarikTunaiConfirmationBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TarikTunaiConfirmationActivity extends AppCompatActivity {

    public static final String EXTRA_NOMINAL = "nominal";
    private ActivityTarikTunaiConfirmationBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTarikTunaiConfirmationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /// nominal yang telah di tekan di halaman sebelumnya akan di munculkan di sini
        NumberFormat formatter = new DecimalFormat("#,###");
        binding.nominal.setText("Rp" + formatter.format(getIntent().getLongExtra(EXTRA_NOMINAL, 0)));
        binding.total.setText("Rp" + formatter.format(getIntent().getLongExtra(EXTRA_NOMINAL, 0)));


        /// kembali ke halaman sebelumnya
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        /// klik konfirmasi, dan inputkan pin
        /// tampil popup input pin
        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputPin();
            }
        });
    }

    private void inputPin() {
        Dialog dialog;
        Button btnSubmit;
        EditText etPin;
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_pin);
        dialog.setCanceledOnTouchOutside(false);
        btnSubmit = dialog.findViewById(R.id.submit);
        etPin = dialog.findViewById(R.id.pin);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputPin = etPin.getText().toString().trim();

                /// validasi pin oleh sistem, apakah kosong atau tidak
                if(inputPin.isEmpty()) {
                    Toast.makeText(TarikTunaiConfirmationActivity.this, "PIN tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                } else if(!inputPin.equals("123456")) {
                    Toast.makeText(TarikTunaiConfirmationActivity.this, "PIN salah", Toast.LENGTH_SHORT).show();
                    return;
                }

                /// jika pin benar maka akan lanjut ke halaman result tarik tunai
                /// intent ke halaman tarik tunai result
                Intent intent = new Intent(TarikTunaiConfirmationActivity.this, TarikTunaiResult.class);
                intent.putExtra(TarikTunaiResult.EXTRA_NOMINAL, getIntent().getLongExtra(EXTRA_NOMINAL, 0));
                dialog.dismiss();
                startActivity(intent);
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}