package repository;

import model.Product;
import model.UserAccount;

import java.util.List;


public interface ProductDAO {

    void createProduct(Product product);

    void updateProduct(Product product);

    void removeProduct(String code);

    Product findProduct(String code);

    UserAccount findUser(String code);

    List<Product> findProductsForSale();

    List<Product> findProductsForSale(String genre);

    List<String> findGenres();

    List<Product> findProducts(int userId);

    List<Product> findProducts();

}
