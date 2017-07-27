package com.example.user.showdown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

//TODO
//ENTER INPUT CODE
//CHANGE CONDITIONS TO START SECOND SCREEN
//SET UP DATABASE FOR SECOND SCREEN

public class MainActivity extends AppCompatActivity {

    int randint = (int) (Math.random() * 1000000000); //one in a billion chance of getting same variable name

    ArrayList <String> codes = new ArrayList<>();
    DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef = myRootRef.child("condition" + randint);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView codeTextView = (TextView) findViewById(R.id.your_random_code);
        codeTextView.setText("Your Random Code: " + randint);

        conditionRef.setValue("0");

    }

    @Override
    protected void onStart() {
        super.onStart();

        myRootRef.getDatabase().getReference().removeValue();

    }

    public void connect(View view) {
        EditText edit = (EditText) findViewById(R.id.edit_text);
        conditionRef.setValue(String.valueOf(edit.getText()));

        FirebaseDatabase.getInstance().getReference().child("users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if (snapshot.getValue().equals("" + randint)) {
                                //both devices start next screen
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }
}
