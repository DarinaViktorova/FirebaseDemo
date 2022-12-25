package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button logout;
    private EditText edit;
    private Button add;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.logout);
        edit = findViewById(R.id.edit);
        add = findViewById(R.id.add);
        listView = findViewById(R.id.listView);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Logged out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, StartActivity.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_name = edit.getText().toString();
                if (txt_name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "No name entered!", Toast.LENGTH_SHORT);
                } else {
                    FirebaseDatabase.getInstance().getReference().child("WorkersList").push().setValue(txt_name);
                }
            }
        });

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("WorkersList");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
               for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Information info = snapshot.getValue(Information.class);
//                    String txt = info.getName() + " : " + info.getEmail();
//                    list.add(txt);
                   list.add(snapshot.getValue().toString());
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}