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
import com.google.firebase.auth.UserProfileChangeRequest;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Executors;

public class RegistrationActivity extends AppCompatActivity {

    TextView loginTv;

    Button signUpBtn;

    private FirebaseAuth mAuth;

    EditText usernameSUTv;

    EditText emailSUTv;

    EditText passwordSUTv;

    EditText confirmPasswordSUTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();

        loginTv = findViewById(R.id.loginTv);

        signUpBtn = findViewById(R.id.signUpBtn);

        usernameSUTv = findViewById(R.id.usernameSUTv);

        emailSUTv = findViewById(R.id.emailSUTv);

        passwordSUTv = findViewById(R.id.passwordSUTv);

        confirmPasswordSUTv = findViewById(R.id.confirmPasswordSUTv);

        // Disable sign-up button temporarily while checking environment
        signUpBtn.setEnabled(false);

        if (BuildConfig.DEBUG) {
            detectAndSetEmulator();
        } else {
            // Automatically cloud if not in debug mode
            signUpBtn.setEnabled(true);
            Toast.makeText(RegistrationActivity.this, "Connected to Live Cloud", Toast.LENGTH_SHORT).show();
        }

        String fullText = "Already have an account? Log In here.";
        SpannableString spannableString = new SpannableString(fullText);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        int startIndex = fullText.indexOf("Log In");
        int endIndex = startIndex + "Log In".length();

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        loginTv.setText(spannableString);
        loginTv.setMovementMethod(LinkMovementMethod.getInstance());

        signUpBtn.setOnClickListener(v -> {
            String email = emailSUTv.getText().toString().trim();
            String username = usernameSUTv.getText().toString().trim();
            String password = passwordSUTv.getText().toString().trim();
            String confirm_password = confirmPasswordSUTv.getText().toString().trim();

            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirm_password.isEmpty()) {
                Toast.makeText(RegistrationActivity.this, "Please enter complete credentials", Toast.LENGTH_SHORT).show();
                return;
            }


            if (!password.equals(confirm_password)) {
                Toast.makeText(RegistrationActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(username)
                                            .build();

                                    user.updateProfile(profileUpdates)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> updateTask) {
                                                    if (updateTask.isSuccessful()) {
                                                        Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                                        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        Toast.makeText(RegistrationActivity.this, "Failed to save username: " + updateTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                            } else {
                                Toast.makeText(RegistrationActivity.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
            });
        });

    }

    private void detectAndSetEmulator() {
        String host = BuildConfig.EMULATOR_HOST;

        Executors.newSingleThreadExecutor().execute(() -> {
            boolean emulatorRunning = false;
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(host, 9099), 1000);
                emulatorRunning = true;
            } catch (Exception e) {
                // Connection failed; emulator is offline
            }

            final boolean finalEmulatorRunning = emulatorRunning;

            runOnUiThread(() -> {
                if (finalEmulatorRunning) {
                    try {
                        mAuth.useEmulator(host, 9099);
                        Toast.makeText(RegistrationActivity.this, "Connected to Local Emulator", Toast.LENGTH_SHORT).show();
                    } catch (IllegalStateException e) {
                        // useEmulator was already called previously in MainActivity
                        Toast.makeText(RegistrationActivity.this, "Connected to Local Emulator", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Ping failed, falling back to live cloud
                    Toast.makeText(RegistrationActivity.this, "Connected to Live Cloud", Toast.LENGTH_SHORT).show();
                }

                signUpBtn.setEnabled(true);
            });
        });
    }
}