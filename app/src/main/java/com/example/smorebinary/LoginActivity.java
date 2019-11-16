package com.example.smorebinary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText login_username;
    private EditText login_password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        login_username = findViewById(R.id.user_field);
        login_password = findViewById(R.id.password_field);

    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            finish();
//            startActivity(new Intent(this, DashboardActivity.class));
        }
    }

    public void login_user(View view) {

        String email = login_username.getText().toString().trim();
        String password = login_password.getText().toString().trim();

        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Log.d("username", user.getEmail());
                                Intent i2 = new Intent(LoginActivity.this, MainActivity.class);
                                login_username.setText("User");
                                login_password.setText("Pass");
                                startActivity(i2);
                                Log.w("UserSTATUS", "Authenticated");
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("UserSTATUS", "Unauthenticated");
                            }

                            // ...
                        }
                    });
        }
    }

    public void register_user(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
