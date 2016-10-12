package service;

import dao.ProductDAO;
import dao.UserDAO;
import model.Product;
import model.UserAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ivo Raykov on 29.9.2016 г..
 */

@Service
public class ProductServiceImpl implements ProductService {


    private ProductDAO productDAO;
    private UserDAO userDAO;


    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;

    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;

    }

    @Override
    @Transactional
    public void createProduct(Product product) {
        this.productDAO.createProduct(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {this.productDAO.updateProduct(product);}

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

    @Override
    @Transactional
    public List<Product> findProductsForSale() {
        return this.productDAO.findProductsForSale();
    }

    //to make logic with id not user
    @Override
    @Transactional
    public void buyProduct(String code, int userId) {
        Product p = this.productDAO.getProductByCode(code);

        UserAccount previousOwner = p.getUserAccount();
        UserAccount futureOwner = this.userDAO.getUserById(userId);

        previousOwner.setMoney(previousOwner.getMoney() + p.getPrice());
        userDAO.updateUser(previousOwner);

        futureOwner.setMoney(futureOwner.getMoney() - p.getPrice());
        userDAO.updateUser(futureOwner);

        p.setUserAccount(futureOwner);
        p.setForSale(false);
        productDAO.updateProduct(p);
    }

    @Override
    @Transactional
    public UserAccount getUserByProductCode(String code) {
        return this.productDAO.getUserByProductCode(code);
    }

    @Override
    @Transactional
    public List<Product> listProductsForUser(int userId) {
        return this.productDAO.listProductsForUser(userId);
    }


}
