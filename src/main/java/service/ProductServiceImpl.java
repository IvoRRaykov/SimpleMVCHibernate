package service;

import dao.ProductDAO;
import dao.UserDAO;
import model.Product;
import model.UserAccount;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static util.Constants.FILE_FOR_SAVE_NAME;

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
    public void updateProduct(Product product) {
        UserAccount user = this.productDAO.getUserByProductCode(product.getCode());
        product.setUserAccount(user);
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

    @Override
    @Transactional
    public List<Product> findProductsForSale() {
        return this.productDAO.findProductsForSale();
    }

    @Override
    @Transactional
    public void buyProduct(String code, int userId) {
        Product p = this.productDAO.getProductByCode(code);

        UserAccount previousOwner = this.productDAO.getUserByProductCode(p.getCode());
        UserAccount futureOwner = this.userDAO.getUserById(userId);

        previousOwner.setMoney(previousOwner.getMoney() + p.getPrice());
        futureOwner.setMoney(futureOwner.getMoney() - p.getPrice());

        p.setUserAccount(futureOwner);
        p.setForSale(false);
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

    @Override
    @Transactional
    public void downloadList(HttpServletResponse response) throws IOException {

        List<Product> productsForSale = this.findProductsForSale();


        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + FILE_FOR_SAVE_NAME + ".xls");

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("FirstSheet");

        HSSFRow rowhead = sheet.createRow((short) 0);
        rowhead.createCell(0).setCellValue("Code.");
        rowhead.createCell(1).setCellValue("Name");
        rowhead.createCell(2).setCellValue("Price");
        rowhead.createCell(3).setCellValue("Owner");

        short rowNumber = 1;
        for (Product p : productsForSale) {

            String ownerName = this.getUserByProductCode(p.getCode()).getUserName();

            HSSFRow row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(p.getCode());
            row.createCell(1).setCellValue(p.getName());
            row.createCell(2).setCellValue(p.getPrice());
            row.createCell(3).setCellValue(ownerName);


        }

        workbook.write(response.getOutputStream());
        workbook.close();

    }

    @Override
    public String uploadPicture(String name, MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();


            File dir = new File("tmpFiles");

            File serverFile = new File(dir.getAbsolutePath()
                    + File.separator + name + ".jpg");
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            return dir.getAbsolutePath()
                    + File.separator + name + ".jpg";
        } catch (Exception e) {
            return null;
        }
    }


}
