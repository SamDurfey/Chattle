package com.epicodus.chattle.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.chattle.Constants;
import com.epicodus.chattle.R;
import com.epicodus.chattle.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private static final String TAG = SignUpActivity.class.getSimpleName();
    @Bind(R.id.userNameEntry) EditText mUserNameEntry;
    @Bind(R.id.emailEntry) EditText mEmailEntry;
    @Bind(R.id.passwordEntry) EditText mPasswordEntry;
    @Bind(R.id.confirmPasswordEntry) EditText mConfirmPasswordEntry;
    @Bind(R.id.signUpButton) Button mSignUpButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    protected void onCreate(Bundle savedInstanceState) {

        ref = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_USER);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
        mSignUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == mSignUpButton) {
            createNewUser();
            Log.d(TAG, "onClick: ");
        }
    }

    private void createNewUser() {
        final String name = mUserNameEntry.getText().toString().trim();
        final String email = mEmailEntry.getText().toString().trim();
        final String password = mPasswordEntry.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEntry.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid = task.getResult().getUser().getUid();
                            User newUser = new User(name, email, password, uid);
                            ref.child(uid).setValue(newUser);
                            Log.d(TAG, "Authentication Successful!");
                        } else {
                            Toast.makeText(SignUpActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
