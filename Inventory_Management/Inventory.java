
package Model;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 *
 * @author JBallejos
 */
public class Inventory {
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static int productIDCount = 0;
    private static int partIDCount = 0;
    
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static void deleteProduct(Product selectedProduct) {
        allProducts.remove(selectedProduct);
    }

    public static void deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
    }

    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }
    
    
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }
    

    public static int getCountPartId() {
        partIDCount += 1;
        return partIDCount;
    }
    
    
    
    public static int getCountProductId() {
        productIDCount += 1;
        return productIDCount;
    }
    
   
    

    public static ObservableList lookupProduct(String productName) {
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        
        if(productName.length() == 0) {
            foundProducts = allProducts;
        } else {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getName().toUpperCase().contains(productName.toUpperCase())) {
                    foundProducts.add(allProducts.get(i));
                }
            }    
        }
        
        return foundProducts;
    }
    public static Product lookupProduct(int productId) {
        for (Product searchedProduct : allProducts) {
            if (searchedProduct.getId() == productId) {
                return searchedProduct;
            }
        }
        
        return null;
    }
    public static ObservableList lookupPart(String partName) {
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        
        if(partName.length() == 0) {
            foundParts = allParts;
        } else {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getName().toUpperCase().contains(partName.toUpperCase())) {
                    foundParts.add(allParts.get(i));
                }
            }    
        }
        
        return foundParts;
    }
    public static Part lookupPart(int partId) {
        for (Part searchedPart : allParts) {
            if (searchedPart.getId() == partId) {
                return searchedPart;
            }
        }

        return null;
    }
    
        
    public static void addProduct(Product product) {
        allProducts.add(product);
    }
     public static boolean associatedParts(Product product) {
        return product.getProductPartsCount() == 0;
    }

    
    public static void addPart(Part part) {
        allParts.add(part);
    }

    
}