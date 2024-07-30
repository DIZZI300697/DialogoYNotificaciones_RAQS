package com.example.dialogoynotificaciones_raqs;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "basic_notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBasicDialog = findViewById(R.id.btnBasicDialog);
        Button btnCustomDialog = findViewById(R.id.btnCustomDialog);
        Button btnBasicNotification = findViewById(R.id.btnBasicNotification);
        Button btnBasicToast = findViewById(R.id.btnBasicToast);
        Button btnCustomToast = findViewById(R.id.btnCustomToast);

        btnBasicDialog.setOnClickListener(v -> showBasicDialog());
        btnCustomDialog.setOnClickListener(v -> showCustomDialog());
        btnBasicNotification.setOnClickListener(v -> showBasicNotification());
        btnBasicToast.setOnClickListener(v -> showBasicToast());
        btnCustomToast.setOnClickListener(v -> showCustomToast());
    }

    private void showBasicDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Dialogo Básico")
                .setMessage("¿Quieres aceptar o cancelar el dialogo?")
                .setPositiveButton("Aceptar", (dialog, which) ->
                        Toast.makeText(MainActivity.this, "Has aceptado", Toast.LENGTH_SHORT).show())
                .setNegativeButton("Cancelar", (dialog, which) ->
                        Toast.makeText(MainActivity.this, "Has cancelado", Toast.LENGTH_SHORT).show())
                .show();
    }

    private void showCustomDialog() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        EditText etName = new EditText(this);
        etName.setHint("Nombre");
        layout.addView(etName);

        EditText etUsername = new EditText(this);
        etUsername.setHint("Usuario");
        layout.addView(etUsername);

        EditText etPhone = new EditText(this);
        etPhone.setHint("Telefono");
        layout.addView(etPhone);

        EditText etEmail = new EditText(this);
        etEmail.setHint("Email");
        layout.addView(etEmail);

        EditText etPassword = new EditText(this);
        etPassword.setHint("Contraseña");
        layout.addView(etPassword);

        new AlertDialog.Builder(this)
                .setTitle("Registro")
                .setView(layout)
                .setPositiveButton("Guardar", (dialog, which) ->
                        Toast.makeText(MainActivity.this, "El usuario " + etUsername.getText().toString() + " ha sido guardado", Toast.LENGTH_SHORT).show())
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void showBasicNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Basic Notification", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Notificación Básica")
                .setContentText("Esta es una notificación básica")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(1, builder.build());
    }

    private void showBasicToast() {
        Toast.makeText(this, "Este es un toast básico", Toast.LENGTH_SHORT).show();
    }

    private void showCustomToast() {
        Toast toast = new Toast(getApplicationContext());
        LinearLayout toastLayout = new LinearLayout(this);
        toastLayout.setOrientation(LinearLayout.HORIZONTAL);
        toastLayout.setPadding(16, 16, 16, 16);

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_toast_image);

        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                100,
                100
        );
        imageView.setLayoutParams(imageParams);
        toastLayout.addView(imageView);

        TextView textView = new TextView(this);
        textView.setText("Ejemplo de toast personalizado");
        textView.setTextColor(getResources().getColor(android.R.color.white));
        textView.setTextSize(16);
        textView.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        textParams.setMargins(16, 0, 0, 0);
        textView.setLayoutParams(textParams);
        toastLayout.addView(textView);

        toastLayout.setBackgroundResource(R.drawable.toast_background);

        toast.setView(toastLayout);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
