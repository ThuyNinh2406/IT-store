package com.example.it_store;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.it_store.model.Cart;
import com.example.it_store.model.Product;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProductDetail extends AppCompatActivity {
    TextView product_name,product_price,product_description,tv_Display;
    ImageView product_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    Button increment, decrement;

    String productID="";
    FirebaseDatabase database;
    DatabaseReference products;
    private String productCategory;
    Product product;
    int soLuong = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        database = FirebaseDatabase.getInstance();
        products = database.getReference();
        increment = findViewById(R.id.increment);
        decrement = findViewById(R.id.decrement);
        tv_Display = findViewById(R.id.display);
//        btnCart = findViewById(R.id.btnCart);
//        product_description = findViewById(R.id.product_description);
//        product_name = findViewById(R.id.product_name);
//        product_price = findViewById(R.id.product_price);
//        product_image = findViewById(R.id.img_product);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong+=1;
//                int newPrice = Integer.parseInt(product.getGia()) * soLuong;
//                product_price.setText(newPrice + "");
                tv_Display.setText(String.valueOf(soLuong));
                decrement.setEnabled(true);

            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong-=1;
                if (soLuong==0){
                    decrement.setEnabled(false);
                }
                else{
                    decrement.setEnabled(true);

                }
              ///  int newPrice = Integer.parseInt(product.getGia()) * soLuong;
              //  product_price.setText(newPrice + "");
                tv_Display.setText(String.valueOf(soLuong));

            }
        });

//
//        if (getIntent() != null){
//            productID = getIntent().getStringExtra("ProductId");
//            productCategory = getIntent().getStringExtra("ProductCategory");
//        }
//        if (!productID.isEmpty()){
//            getDetailProduct(productID);
//        }

    }
    private void getDetailProduct(final String productId){
        products.child("Category/" + productCategory + "/danh_sach/" + productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                product = snapshot.getValue(Product.class);

                Glide.with(getBaseContext()).load(product.getImage())
                        .into(product_image);
//             collapsingToolbarLayout.setTitle(product.getName());

                product_price.setText(product.getGia());
                product_name.setText(product.getName());
                product_description.setText(product.getMota());
                product.setId(productId);
                btnCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cart.addToCart(product.getId(), productCategory, product.getName(), soLuong, Integer.parseInt(product.getGia()));
                        Log.d("category", "onClick: " + productCategory);
                        Toast.makeText(ProductDetail.this, "Added to cart", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}