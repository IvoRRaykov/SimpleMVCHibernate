package dao;

import model.Product;
import model.UserAccount;

import java.util.List;
import java.util.Set;


public interface ProductDAO {

    public void createProduct(Product product);
    public void updateProduct(Product product);
    public List<Product> listProduct();
    public Product getProductByCode(String code);
    public void removeProduct(String code);
    public List<Product> findProductsForSale();
    public List<Product> listProductsForUser(int userId);
    public UserAccount getUserByProductCode(String code);

    }
