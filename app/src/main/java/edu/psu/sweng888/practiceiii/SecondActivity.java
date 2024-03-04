package edu.psu.sweng888.practiceiii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    //Instantiating and assigning variables
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private SelectedProductAdapter selectedProductAdapter;
    private Button emailButton;
    private ArrayList<Product> productList;
    boolean successfulEmail = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Setting up layout
        setContentView(R.layout.activity_second);
        recyclerView=findViewById(R.id.recyclerView);
        emailButton = findViewById(R.id.emailButton);

        // Gets list of selected items sent by the main activity
        productList = getIntent().getParcelableArrayListExtra("data");

        // Attach the adapter to selected items
        selectedProductAdapter=new SelectedProductAdapter(productList);
        recyclerView.setAdapter(selectedProductAdapter);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        //Setting a click listener to the email button
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If at least one item is selected, send email then remove selected items from list
                if(selectedProductAdapter.getItemCount()>0){
                    sendEmail();
                    selectedProductAdapter.removeAll(productList);
                    successfulEmail=true;

                }
                // Otherwise tell the user to select at least one item.
                else{
                    Toast.makeText(SecondActivity.this,"Select at least one item",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    // Notify user email was sent successfully
    @Override
    protected void onResume() {
        super.onResume();

        if(successfulEmail){
            Toast.makeText(SecondActivity.this,"Email sent successfully",Toast.LENGTH_SHORT).show();
        }
    }

    // Naviagate to and populate email with the data from the selected items.
    public void sendEmail() {
        String[] TO = {"someone@gmail.com"}; // Email address to send the email
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Selected Grocery Items"); // subject
        emailIntent.putExtra(Intent.EXTRA_TEXT, parseProducts(productList)); // body

        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent,"Choose email client:"));
    }

    // Parse the class of each selected item
    public String parseProducts(ArrayList<Product> products){
        String productDetails = "";
        for(Product p:products){
            productDetails+=p.getName()+": "+p.getDescription()+"\n";
        }
        return productDetails;
    }
}