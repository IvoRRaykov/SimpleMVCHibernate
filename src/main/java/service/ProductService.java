package service;

import model.Product;
import model.UserAccount;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * Created by Ivo Raykov on 29.9.2016 г..
 */
public interface ProductService {

    public void createProduct(Product product);
    public UserAccount updateProduct(Product product);
    public List<Product> listProduct();
    public Product getProductByCode(String code);
    public void removeProduct(String code);
    public List<Product> findProductsForSale();
    public UserAccount buyProduct(String code, UserAccount user);
}
