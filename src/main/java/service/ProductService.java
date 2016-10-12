package service;

import model.Product;
import model.UserAccount;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Ivo Raykov on 29.9.2016 г..
 */
public interface ProductService {

    public void createProduct(Product product);
    public void updateProduct(Product product);
    public List<Product> listProduct();
    public Product getProductByCode(String code);
    public void removeProduct(String code);
    public List<Product> findProductsForSale();
    public void buyProduct(String code, int userId);
    public List<Product> listProductsForUser(int userId);
    public UserAccount getUserByProductCode(String code);

    void downloadList(HttpServletResponse response) throws IOException;
}
