package edu.psu.sweng888.practiceiii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import java.util.List;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView pRecyclerView;
    private LinearLayoutManager layoutManager;
    private ProductDbHelper dbHelper;
    private ProductAdapter productAdapter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Assigning layout views
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        pRecyclerView = findViewById(R.id.recycler_view);

        button = findViewById(R.id.button);

        // Create instance of database helper to manipulate db
        dbHelper = new ProductDbHelper(this);

        List<Product> productList;
        ArrayList<Product> selected;

        // Populate database if empty
        if(dbHelper.isDatabaseEmpty()){
            dbHelper.populateProductsDatabase();
        }

        // Fill database with product info
        productList = dbHelper.getAllProducts();

        // Configure product adapter and assign to RecyclerView
        productAdapter = new ProductAdapter(productList);
        pRecyclerView.setAdapter(productAdapter);

        layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        pRecyclerView.setLayoutManager(layoutManager);



            button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){

            // Creating intent that passes selected items to the ResultsActivity class

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) productAdapter.selected);
            startActivity(intent);
            }
        }
        );
    }
}