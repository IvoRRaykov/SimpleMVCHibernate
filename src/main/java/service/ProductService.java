package service;

import model.Product;
import model.UserAccount;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ivo Raykov on 29.9.2016 г..
 */
public interface ProductService {

    void createProduct(Product product);

    void updateProduct(Product product);

    Product getProduct(String code);

    void removeProduct(String code);

    List<Product> getProductsForSale();

    List<Product> getProductsForSale(String genre);

    List<String> getGenres();

    void buyProduct(String code, int userId);

    List<Product> listProductsForUser(int userId);

    List<Product> listProducts();

    UserAccount getUser(String code);

    void downloadList(HttpServletResponse response) throws IOException;

    String uploadPicture(String name, MultipartFile file);

    String getPicture(String uri) throws IOException;

    void attachSongs(List<String> songs, String code);
}
