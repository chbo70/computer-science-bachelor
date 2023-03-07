package at.ac.uibk.pm.g06.csaz9837.training;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<Products> catalog;


    public Shop() {
        new ArrayList<>();
    }

    public void addProduct(Seller seller1, Products productToAdd){
        catalog.add(productToAdd);
    }

    //returns true if product is in catalog else false
    public String searchProductByName(Products productToSearch){
        if (catalog.contains(productToSearch)){
            return productToSearch.toString();
        }
          return "Not available!";
    }

    public void register(){

    }

    public static void main(String[] args) {
        Customer cus1 = new Customer();
        System.out.println(cus1.toString());
    }



}
