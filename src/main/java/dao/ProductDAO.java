package dao;

import model.Product;
import model.UserAccount;

import java.util.List;


public interface ProductDAO {

    void createProduct(Product product);

    void updateProduct(Product product);

    void removeProduct(String code);

    Product getProduct(String code);

    UserAccount getUser(String code);

    List<Product> findProductsForSale();

    List<Product> findProducts(int userId);

    List<Product> findProducts();

}
