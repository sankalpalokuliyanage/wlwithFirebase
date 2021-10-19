package com.example.wedlock.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.wedlock.R;
import com.example.wedlock.adapters.viewAllAdapter;
import com.example.wedlock.models.viewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    viewAllAdapter viewAllAdapter;
    List<viewAllModel> viewAllModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        firestore = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.view_all_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new viewAllAdapter(this, viewAllModelList);

        recyclerView.setAdapter(viewAllAdapter);


        //getting banquet halls
        if (type != null && type.equalsIgnoreCase("banquet_halls")) {
            firestore.collection("All_Items").whereEqualTo("type", "banquet_halls").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        viewAllModel viewAllModel = documentSnapshot.toObject(viewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }

                }
            });
        }

        //getting poruwa
        if (type != null && type.equalsIgnoreCase("poruwa")) {
            firestore.collection("All_Items").whereEqualTo("type", "poruwa").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        viewAllModel viewAllModel = documentSnapshot.toObject(viewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }

                }
            });
        }
    }
}