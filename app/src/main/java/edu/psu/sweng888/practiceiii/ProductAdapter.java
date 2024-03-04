package edu.psu.sweng888.practiceiii;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    // Initiate list of products
    private List<Product> productList;

    // bool to check if item is selected
    boolean isSelected = false;

    // Array list of selected items
    ArrayList<Product> selected = new ArrayList<>();

    // Formatter to set price to two decimal places
    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    // Create new viewholder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,
                parent, false);
        return new ViewHolder(view);
    }

    // Bind data to viewholder
    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.productIDTextView.setText(String.valueOf(product.getID()));
        holder.productNameTextView.setText(product.getName());
        holder.productDescriptionTextView.setText(product.getDescription());
        holder.productSellerTextView.setText(product.getSeller());
        holder.productPriceTextView.setText(decimalFormat.format(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

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

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // on long click, the selected item will change background green and be added to selected items list
                    isSelected = true;
                    if(selected.contains(productList.get(getAdapterPosition()))){
                        itemView.setBackgroundColor(Color.TRANSPARENT);
                        selected.remove(productList.get(getAdapterPosition()));
                    }else{
                        itemView.setBackgroundColor(Color.BLUE);
                        selected.add(productList.get(getAdapterPosition()));
                    }
                    if (selected.size()==0)
                        isSelected=false;
                    return true;
                }
            });

            // onClickListener if item is de-selected
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // if item is in selected mode, it will de select it, otherwise it will select it.
                    if (isSelected){
                        if (selected.contains(productList.get(getAdapterPosition()))){
                            itemView.setBackgroundColor(Color.TRANSPARENT);
                            selected.remove(productList.get(getAdapterPosition()));
                        }
                        else{
                            itemView.setBackgroundColor(Color.BLUE);
                            selected.add(productList.get(getAdapterPosition()));
                        }
                        if(selected.size()==0){
                            isSelected=false;
                        }
                    }
                    else{

                    }
                }
            });
        }
    }
}
