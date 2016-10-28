package repository;

import model.Product;
import model.UserAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {


    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public void createProduct(Product product) {

        Session session = this.sessionFactory.getCurrentSession();

        session.persist(product);

        logger.info("Product saved successfully, Product Details=" + product.toString());
    }

    @Override
    public void updateProduct(Product product) {

        Session session = this.sessionFactory.getCurrentSession();

        session.update(product);

        logger.info("Product updated successfully, Product Details=" + product.toString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> findProducts() {

        Session session = this.sessionFactory.getCurrentSession();

        List<Product> productList = session.createQuery("from Product").list();

        logger.info("Product list obtained successfully");

        for (Product product : productList) {

            logger.info("   product = " + product.toString());

        }

        return productList;
    }

    @Override
    public Product findProduct(String code) {

        Session session = this.sessionFactory.getCurrentSession();

        Product product = (Product) session.load(Product.class, code);

        logger.info("Product loaded successfully by code = " + code + ", Product details=" + product.toString());

        return product;
    }

    @Override
    public void removeProduct(String code) {

        Session session = this.sessionFactory.getCurrentSession();

        Product product = (Product) session.load(Product.class, code);
        if (null != product) {

            session.delete(product);
        }

        logger.info("Product deleted successfully by code = " + code + ", product details = " + product.toString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> findProductsForSale() {

        Session session = this.sessionFactory.getCurrentSession();

        List<Product> productList = session.createQuery("from Product where forSale = true").list();

        logger.info("Products for sale List");

        for (Product product : productList) {

            logger.info("   product = " + product.toString());
        }

        return productList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> findProductsForSale(String genre) {

        Session session = this.sessionFactory.getCurrentSession();

        List<Product> productList = session.createQuery("from Product where forSale = true and genre = :genre")
                .setString("genre", genre)
                .list();

        logger.info("Products for sale for genre : " + genre + " List");

        for (Product product : productList) {

            logger.info("   product = " + product.toString());
        }

        return productList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> findGenres() {
        Session session = this.sessionFactory.getCurrentSession();

        List<String> genreList = session.createQuery("select distinct genre from Product")
                .list();

        logger.info("Genres List :");

        for (String genre : genreList) {

            logger.info("   genre = " + genre);
        }

        return genreList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> findProducts(int userId) {

        Session session = this.sessionFactory.getCurrentSession();

        List<Product> productList = session.createQuery("from Product where user_id = :userId")
                .setInteger("userId", userId)
                .list();

        logger.info("Product List for userId " + userId);

        for (Product product : productList) {
            logger.info("  product = " + product.toString());
        }

        return productList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserAccount findUser(String code) {

        Session session = sessionFactory.getCurrentSession();

        List<UserAccount> userIds = session.createQuery("select userAccount from Product where code = :code")
                .setString("code", code)
                .list();


        logger.info("User Id = " + userIds.get(0) + " obtained successfully by , product code =" + code);

        return userIds.get(0);
    }
}
