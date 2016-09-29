package dao;

import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO{


    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public void createProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(product);
        logger.info("Product saved successfully, Product Details=" + product);
    }

    @Override
    public void updateProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(product);
        logger.info("Product updated successfully, Product Details=" + product);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> listProduct() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Product> productList = session.createQuery("from Product").list();
        for (Product prduct : productList) {
            logger.info("Product List::" + prduct);
        }
        return productList;
    }

    @Override
    public Product getProductByCode(String code) {
        Session session = this.sessionFactory.getCurrentSession();
        Product product = (Product) session.load(Product.class, code);
        logger.info("Product loaded successfully, Product details=" + product);
        return product;
    }

    @Override
    public void removeProduct(String code) {
        Session session = this.sessionFactory.getCurrentSession();
        Product product = (Product) session.load(Product.class, code);
        if (null != product) {
            session.delete(product);
        }
        logger.info("Product deleted successfully, Product details=" + product);
    }
}
