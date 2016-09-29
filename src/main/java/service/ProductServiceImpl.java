package service;

import dao.ProductDAO;
import model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ivo Raykov on 29.9.2016 г..
 */

@Service
public class ProductServiceImpl implements ProductService {


    private ProductDAO productDAO;

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    @Override
    @Transactional
    public void createProduct(Product product) {
        this.productDAO.createProduct(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        this.productDAO.updateProduct(product);
    }

    @Override
    @Transactional
    public List<Product> listProduct() {
        return this.productDAO.listProduct();
    }

    @Override
    @Transactional
    public Product getProductByCode(String code) {
        return this.productDAO.getProductByCode(code);
    }

    @Override
    @Transactional
    public void removeProduct(String code) {
        this.productDAO.removeProduct(code);
    }
}
