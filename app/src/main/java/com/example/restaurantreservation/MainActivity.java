package com.example.restaurantreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference myRef;
    Button loginButton;
    static List<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToDB();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                startActivity(intent);
            }
        });

        myRef = FirebaseDatabase.getInstance().getReference("restaurants");
        //pushButton = findViewById(R.id.push);

        restaurantList = new ArrayList<>();

//        pushButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                writeToDB();
//
//                Toast.makeText(MainActivity.this, "Pushed", Toast.LENGTH_SHORT).show();
//                printData();
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                restaurantList.clear();

                for (DataSnapshot restaurantSnapShot : dataSnapshot.getChildren()) {
                    Restaurant restaurant = restaurantSnapShot.getValue(Restaurant.class);

                    restaurantList.add(restaurant);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Nothing", "Failed to read value.", error.toException());
            }
        });
    }

    public void writeToDB() {

        String id = myRef.push().getKey();

        Restaurant restaurant = new Restaurant("Test", id, "No Des");
        myRef.child(id).setValue(restaurant);
    }

    void printData() {
        for (int i = 0; i < restaurantList.size() + 1; i++) {
            System.out.println(restaurantList.get(i).toString());
        }
    }



}
