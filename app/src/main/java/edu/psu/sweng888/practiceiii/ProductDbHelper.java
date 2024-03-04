package edu.psu.sweng888.practiceiii;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class ProductDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PRODUCTS = "products";
    private static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_SELLER = "seller";
    public static final String KEY_PRICE = "price";

    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createProductsTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2){
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCTS);
        }
    }

    // SQL query to create the products table
    private String createProductsTable() {
       String QUERY_CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + " (" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT," +
                KEY_DESCRIPTION + " TEXT," +
                KEY_SELLER + " TEXT," +
                KEY_PRICE + " REAL)";
       return QUERY_CREATE_PRODUCTS_TABLE;
    }

    public void addProduct(Product product){
        // Initiate writable instance of db
        SQLiteDatabase database = this.getWritableDatabase();
        // Create content values to write to db
        ContentValues values = new ContentValues();
        // Populate db
        values.put(KEY_ID, product.getID());
        values.put(KEY_NAME, product.getName());
        values.put(KEY_DESCRIPTION, product.getDescription());
        values.put(KEY_SELLER, product.getSeller());
        values.put(KEY_PRICE, product.getPrice());
        // Insert the values on the TABLE_PROODUCTS
        database.insert(TABLE_PRODUCTS, null, values);
        // Close db connection
        database.close();
    }

    public List<Product> getAllProducts(){
        List<Product> productList = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TABLE_PRODUCTS;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        // Cursor to navigate through each record in db
        if (cursor!= null && cursor.moveToFirst()){
            do {
                Product product = new Product(
                        cursor.getInt(0), // ID
                        cursor.getString(1), // NAME
                        cursor.getString(2), // DESCRIPTION
                        cursor.getString(3), // SELLER
                        cursor.getFloat(4) // PRICE
                );
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return productList;
    }

    // Check if db is empty
    public boolean isDatabaseEmpty() {
        boolean isEmpty = true;
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM " + TABLE_PRODUCTS, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            if (count > 0) {
                isEmpty = false;
            }
            cursor.close();
        }
        return isEmpty;
    }

    // populate db with product info
    public void populateProductsDatabase(){

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();

        values = new ContentValues();
        values.put(KEY_ID, 3283);
        values.put(KEY_NAME, "Honeycrisp Apple");
        values.put(KEY_DESCRIPTION, "Fresh and crisp apples");
        values.put(KEY_SELLER, "Johnny Appleseed");
        values.put(KEY_PRICE, 1.99);
        database.insert(TABLE_PRODUCTS,null,values);

        values = new ContentValues();
        values.put(KEY_ID, 4011);
        values.put(KEY_NAME, "Banana");
        values.put(KEY_DESCRIPTION, "Ripe and ready-to-eat bananas");
        values.put(KEY_SELLER, "Chiquita");
        values.put(KEY_PRICE, 0.29);
        database.insert(TABLE_PRODUCTS,null,values);

        values = new ContentValues();
        values.put(KEY_ID, 4094);
        values.put(KEY_NAME, "Carrots");
        values.put(KEY_DESCRIPTION, "Sweet and crunchy carrots");
        values.put(KEY_SELLER, "Buggs Bunny");
        values.put(KEY_PRICE, 3.29);
        database.insert(TABLE_PRODUCTS,null,values);

        values = new ContentValues();
        values.put(KEY_ID, 4061);
        values.put(KEY_NAME, "Iceberg Lettuce");
        values.put(KEY_DESCRIPTION, "Crisp and leafy lettuce");
        values.put(KEY_SELLER, "Fresh Farms");
        values.put(KEY_PRICE, 1.29);
        database.insert(TABLE_PRODUCTS,null,values);

        values = new ContentValues();
        values.put(KEY_ID, 3061);
        values.put(KEY_NAME, "Beefsteak Tomato");
        values.put(KEY_DESCRIPTION, "Juicy and ripe tomatoes");
        values.put(KEY_SELLER, "TN Farms");
        values.put(KEY_PRICE, 0.99);
        database.insert(TABLE_PRODUCTS,null,values);

        values = new ContentValues();
        values.put(KEY_ID, 4452);
        values.put(KEY_NAME, "Mandarins");
        values.put(KEY_DESCRIPTION, "Small, sweet snacks");
        values.put(KEY_SELLER, "Halo");
        values.put(KEY_PRICE, 4.59);
        database.insert(TABLE_PRODUCTS,null,values);

        values = new ContentValues();
        values.put(KEY_ID, 4313);
        values.put(KEY_NAME, "Mango");
        values.put(KEY_DESCRIPTION, "Juicy mangos, rpe and ready-to-eat");
        values.put(KEY_SELLER, "Kent");
        values.put(KEY_PRICE, 0.99);
        database.insert(TABLE_PRODUCTS,null,values);

        values = new ContentValues();
        values.put(KEY_ID, 4889);
        values.put(KEY_NAME, "Cilantro");
        values.put(KEY_DESCRIPTION, "Fresh cilantro to top every dish");
        values.put(KEY_SELLER, "Dole");
        values.put(KEY_PRICE, 0.99);
        database.insert(TABLE_PRODUCTS,null,values);

        values = new ContentValues();
        values.put(KEY_ID, 4609);
        values.put(KEY_NAME, "Elephant Garlic");
        values.put(KEY_DESCRIPTION, "Large, easy to peel cloves");
        values.put(KEY_SELLER, "GalicRUs");
        values.put(KEY_PRICE, 1.29);
        database.insert(TABLE_PRODUCTS,null,values);

        values = new ContentValues();
        values.put(KEY_ID, 3047);
        values.put(KEY_NAME, "Medjool Dates");
        values.put(KEY_DESCRIPTION, "Pitted and sweet");
        values.put(KEY_SELLER, "Oasis");
        values.put(KEY_PRICE, 5.49);
        database.insert(TABLE_PRODUCTS,null,values);

        database.close();
    }
}

