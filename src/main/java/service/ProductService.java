package service;

import model.Product;

import java.util.List;

/**
 * Created by Ivo Raykov on 29.9.2016 г..
 */
public interface ProductService {

    public void createProduct(Product product);
    public void updateProduct(Product product);
    public List<Product> listProduct();
    public Product getProductByCode(String code);
    public void removeProduct(String code);

}
