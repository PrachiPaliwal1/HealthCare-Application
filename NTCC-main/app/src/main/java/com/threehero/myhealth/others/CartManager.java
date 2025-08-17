
package com.threehero.myhealth.others;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<Medicine_list> cartItems;
    private List<LabTests_list> CartLabItems;

    private CartManager() {

        cartItems = new ArrayList<>();
        CartLabItems = new ArrayList<>();
    }


    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(Medicine_list item) {
        cartItems.add(item);
    }
    public void addToCart(LabTests_list item) { CartLabItems.add(item); }

    public void removeFromCart(Medicine_list item) {
        cartItems.remove(item);
    }
    public void removeFromCart(LabTests_list item) {
        CartLabItems.remove(item);
    }

    public List<Medicine_list> getCartItems() {
        return new ArrayList<>(cartItems);
    }
    public List<LabTests_list> getCartLabItems() {
        return new ArrayList<>(CartLabItems);
    }

    public void loadCartItems(Context context) {
        cartItems = Sharedprefmed.getCartItems();
    }
    public void loadCartLabItems(Context context) {
        CartLabItems = Sharedprefmed.getCartLabItems();
    }

    public void saveCartItems() {
        Sharedprefmed.saveCartItems(cartItems);
    }
}
