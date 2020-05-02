package com.mop.learnprogrammingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class Registr extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private final int RC_SIGN_IN = 0;

    private boolean FLAG_IS_REGISTRATION = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);

        if(!hasConnection(getApplicationContext()))
            Toast.makeText(getApplicationContext(),
                    "Нет соединения с интернетом!", Toast.LENGTH_LONG).show();

        EditText editTextLogin = findViewById(R.id.editTextEmailAuth);
        EditText editTextPass = findViewById(R.id.editTextPasswordAuth);

        findViewById(R.id.btn_sign_in_google).setOnClickListener(view -> signIn());

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btnLogin).setOnClickListener(view -> {
            if(FLAG_IS_REGISTRATION) {
                mAuth.createUserWithEmailAndPassword(
                        editTextLogin.getText().toString(), editTextPass.getText().toString())
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),
                                        "Registration successfully!",
                                        Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                if(editTextPass.getText().toString().length() < 6)
                                    Toast.makeText(getApplicationContext(),
                                            "Minimum of 6 characters for the password!",
                                            Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(getApplicationContext(),
                                            "Registration failed!",
                                            Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            else {
                mAuth.signInWithEmailAndPassword(
                        ((EditText) findViewById(R.id.editTextEmailAuth)).getText().toString(),
                        ((EditText) findViewById(R.id.editTextPasswordAuth)).getText().toString())
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),
                                        "Authentication successfully!",
                                        Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Authentication failed!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        });

        findViewById(R.id.textViewLinkToRegisterScreen).setOnClickListener(view -> {
            FLAG_IS_REGISTRATION = !FLAG_IS_REGISTRATION;

            if(FLAG_IS_REGISTRATION) {
                ((TextView) findViewById(R.id.textViewAuth)).setText("REGISTRATION");
                ((TextView) view).setText(getString(R.string.btn_link_to_login));
            }
            else {
                ((TextView) findViewById(R.id.textViewAuth)).setText("AUTH");
                ((TextView) view).setText(getString(R.string.btn_link_to_register));
            }
        });
    }

    private static boolean hasConnection(final Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;

        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) return true;

        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) return true;

        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) return true;

        return false;
    }

    private void signIn() { startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN); }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(Registr.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        // else Toast.makeText(getApplicationContext(), "Error Auth!", Toast.LENGTH_SHORT).show();
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            AuthCredential credential = GoogleAuthProvider.getCredential(
                    Objects.requireNonNull(account).getIdToken(), null);

            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else updateUI(null);
                    });
        }
        catch (ApiException ignored) { }
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
}