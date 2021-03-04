package com.example.advancedandriod_1.FIREBASE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.advancedandriod_1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthentication extends AppCompatActivity {
EditText ed1,ed2;
    private FirebaseAuth mAuth;
// interface     FirebaseAuth.AuthStateListener mlistener;

    String email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_authentication);
        ed1=findViewById(R.id.ed1);
        ed2=findViewById(R.id.ed2);
        mAuth = FirebaseAuth.getInstance();
        email=ed1.getText().toString();
        pass=ed2.getText().toString();
    if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
        Toast.makeText(getApplicationContext(),"PLEASE FILL ALL DETAILS",Toast.LENGTH_LONG).show();
    }

    }

    public void login(View view) {

        if(ed1.getText().toString().isEmpty() || ed2.getText().toString().isEmpty()) {
            return;

        }
        else {
            mAuth.signInWithEmailAndPassword(ed1.getText().toString(), ed2.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getApplicationContext(), "LOGIN SUCCESS.",
                                        Toast.LENGTH_SHORT).show();
                                //  updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //  Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "LOGIN failed.",
                                        Toast.LENGTH_SHORT).show();
                                //  updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }

    public void signup(View view) {

        if(ed1.getText().toString().isEmpty() || ed2.getText().toString().isEmpty()) {
          return;

        }
        else{
            mAuth.createUserWithEmailAndPassword(ed1.getText().toString(),ed2.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                // Log.d(TAG, "createUserWithEmail:success");
                                //  FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getApplicationContext(), "Authentication Success.",
                                        Toast.LENGTH_SHORT).show();
                                //  updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                ////   Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //   updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
      //  FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);
    }
    @Override
    public void onStop(){
        super.onStop();
        mAuth.signOut();
    }

}
