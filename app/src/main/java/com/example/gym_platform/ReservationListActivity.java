package com.example.gym_platform;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ReservationListActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference ReservationRef = db.collection("Reservation");
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser currentUser = mAuth.getCurrentUser();
    private ReservationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservationlist);
    Log.d("ReservationListActivity","진입");
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Log.d("ReservationListActivity",currentUser.getUid());
        Query query = ReservationRef.whereEqualTo("userUID", currentUser.getUid());
        Log.d("ReservationListActivity", String.valueOf(query));
        FirestoreRecyclerOptions<Reservation> options = new FirestoreRecyclerOptions.Builder<Reservation>().setQuery(query, Reservation.class).build();
        adapter = new ReservationAdapter(options);
        Log.d("ReservationListActivity", "진입2");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Log.d("ReservationListActivity", "진입3");
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        Log.d("ReservationListActivity", "시작");
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
        Log.d("ReservationListActivity", "종료");
    }
}
