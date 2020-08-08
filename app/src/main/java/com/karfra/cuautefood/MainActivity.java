package com.karfra.cuautefood;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    public static final int REQUEST_CODE = 54654;

    List<AuthUI.IdpConfig> provider = Arrays.asList(
            new AuthUI.IdpConfig.GoogleBuilder().build()
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser usuario = firebaseAuth.getCurrentUser();

                if (usuario != null) {
                    Toast.makeText(MainActivity.this, "Iniciaste sesión", Toast.LENGTH_SHORT);
                }else{
                    startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(provider)
                    .setIsSmartLockEnabled(false)
                    .build(), REQUEST_CODE);
                }
            }
        };
    } //Fin del onCreate


}