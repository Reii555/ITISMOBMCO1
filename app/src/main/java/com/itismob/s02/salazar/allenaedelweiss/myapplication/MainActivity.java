package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    TextView registrationTv;

    Button loginBtn;

    EditText emailTv;

    EditText passwordTv;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();

        emailTv = findViewById(R.id.emailTv);
        passwordTv = findViewById(R.id.passwordTv);
        loginBtn = findViewById(R.id.loginBtn);

        // Disable login button temporarily while checking environment
        loginBtn.setEnabled(false);

        if (BuildConfig.DEBUG) {
            detectAndSetEmulator();
        } else {
            loginBtn.setEnabled(true);
            Toast.makeText(MainActivity.this, "Connected to Live Cloud", Toast.LENGTH_SHORT).show();
            checkCurrentUser();
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTv.getText().toString().trim();
                String password = passwordTv.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity.this, "Login Failed: " + (task.getException() != null ? task.getException().getMessage() : ""), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        registrationTv = findViewById(R.id.registrationTv);

        String fullText = "Don't have an account? Sign up here.";
        SpannableString spannableString = new SpannableString(fullText);


        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        };

        int startIndex = fullText.indexOf("Sign up");
        int endIndex = startIndex + "Sign up".length();

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        registrationTv.setText(spannableString);
        registrationTv.setMovementMethod(LinkMovementMethod.getInstance());

    }

    private void detectAndSetEmulator() {
        String host = BuildConfig.EMULATOR_HOST;

        // Network operations must running on a background thread
        Executors.newSingleThreadExecutor().execute(() -> {
            boolean emulatorRunning = false;
            try (Socket socket = new Socket()) {
                // Try connecting to the Auth emulator port (9099) with a 1-second timeout
                socket.connect(new InetSocketAddress(host, 9099), 1000);
                emulatorRunning = true;
            } catch (Exception e) {
                // Connection failed; emulator is offline
            }

            final boolean finalEmulatorRunning = emulatorRunning;

            // Return to main thread to update UI and Firebase state
            runOnUiThread(() -> {
                if (finalEmulatorRunning) {
                    try {
                        mAuth.useEmulator(host, 9099);
                        Toast.makeText(MainActivity.this, "Connected to Local Emulator", Toast.LENGTH_SHORT).show();
                    } catch (IllegalStateException e) {
                        // useEmulator was already called previously
                    }
                } else {
                    // Ping failed, falling back to live cloud
                    Toast.makeText(MainActivity.this, "Connected to Live Cloud", Toast.LENGTH_SHORT).show();
                }

                loginBtn.setEnabled(true);
                checkCurrentUser(); // Check for active session after environment is configured
            });
        });
    }
    private void checkCurrentUser() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

}