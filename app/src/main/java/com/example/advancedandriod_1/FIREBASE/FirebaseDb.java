package com.example.advancedandriod_1.FIREBASE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.advancedandriod_1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Iterator;

public class FirebaseDb extends AppCompatActivity {
StorageReference sr;
    EditText ed1,ed2,ed3;
    Button read,update,insert,delete;
    SQLiteDatabase sq;
    ContentValues cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_db);

        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        ed3=(EditText)findViewById(R.id.ed3);
        read=findViewById(R.id.read);
        update=findViewById(R.id.update);
        insert=findViewById(R.id.insert);
        delete=findViewById(R.id.delete);
        sr= FirebaseStorage.getInstance().getReference();
    }

    public void delete(View view) {

    }


    public void update(View view) {

    }


    public void read(View view) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Employee");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> childemps=  dataSnapshot.getChildren();
              //  Iterator<DataSnapshot> it=(Iterator<DataSnapshot>)childemps.iterator();
              for(DataSnapshot d:childemps){
                  Iterable<DataSnapshot> indchild=d.getChildren();
                  String msg="";
                  for(DataSnapshot id:indchild){
                      msg=msg+id.getValue()+"\n";
                  }
                  Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void insert(View view){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Employee"); // no need to create employee in database manually.this line will create.
        DatabaseReference childref=myRef.child(ed3.getText().toString());
        childref.child("email").setValue(ed1.getText().toString());
        childref.child("password").setValue(ed2.getText().toString());
        childref.child("name").setValue(ed3.getText().toString());

    }
}

