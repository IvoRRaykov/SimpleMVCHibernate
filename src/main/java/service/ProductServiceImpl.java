package service;

import dao.ProductDAO;
import dao.UserDAO;
import model.Product;
import model.UserAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

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
    public UserAccount updateProduct(Product product) {

        this.productDAO.updateProduct(product);
        return this.userDAO.getUserById(product.getUserAccount().getId());
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

    @Override
    @Transactional
    public List<Product> findProductsForSale() {
        return this.productDAO.findProductsForSale();
    }

    @Override
    @Transactional
    public UserAccount buyProduct(String code, UserAccount currentOwner) {
        Product p = this.productDAO.getProductByCode(code);
        UserAccount previousOwner = p.getUserAccount();

        previousOwner.setMoney(previousOwner.getMoney() + p.getPrice());
        userDAO.updateUser(previousOwner);

        currentOwner.setMoney(currentOwner.getMoney() - p.getPrice());
        userDAO.updateUser(currentOwner);
        currentOwner.getProducts().add(p);

        p.setUserAccount(currentOwner);
        p.setForSale(false);
        productDAO.updateProduct(p);
        return currentOwner;
    }
}
