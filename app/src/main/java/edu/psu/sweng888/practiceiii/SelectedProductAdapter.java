package edu.psu.sweng888.practiceiii;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class SelectedProductAdapter extends RecyclerView.Adapter<SelectedProductAdapter.ViewHolder> {
    //list containing the products selected by the user
    private ArrayList<Product> selectedProducts;

    public SelectedProductAdapter(ArrayList<Product> selectedProducts) {this.selectedProducts=selectedProducts;}

    @NonNull
    @Override
    public SelectedProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    // Binding data with product attributes
    @Override
    public void onBindViewHolder(@NonNull SelectedProductAdapter.ViewHolder holder, int position) {
        Product product = selectedProducts.get(position);

        holder.productIDTextView.setText(String.valueOf(product.getID()));
        holder.productNameTextView.setText(product.getName());
        holder.productDescriptionTextView.setText(product.getDescription());
        holder.productSellerTextView.setText(product.getSeller());
        holder.productPriceTextView.setText(String.valueOf(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return selectedProducts.size();
    }

    // viewholder class assigning variables to layout
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productIDTextView;
        public TextView productNameTextView;
        public TextView productDescriptionTextView;
        public TextView productSellerTextView;
        public TextView productPriceTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            productIDTextView = itemView.findViewById(R.id.productIdTextView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productDescriptionTextView = itemView.findViewById(R.id.productDescriptionTextView);
            productSellerTextView = itemView.findViewById(R.id.productSellerTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);

        }
    }

    // Remove all items from the arraylist once email is sent
    public void removeAll(ArrayList<Product> product){
        product.clear();
        notifyDataSetChanged();
    }
}
