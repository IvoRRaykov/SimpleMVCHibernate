package service;

import model.Song;
import repository.ProductDAO;
import repository.SongDAO;
import repository.UserDAO;
import model.Product;
import model.UserAccount;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import static util.Constants.*;

/**
 * Created by Ivo Raykov on 29.9.2016 г..
 */

@Service
public class ProductServiceImpl implements ProductService {


    private ProductDAO productDAO;
    private UserDAO userDAO;
    private SongDAO songDAO;

    public void setSongDAO(SongDAO songDAO) {this.songDAO = songDAO;}

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

        /*for(Song song: product.getSongs() ){
            //song.setProduct(product);
            this.songDAO.createSong(song);
        }*/
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        UserAccount user = this.productDAO.findUser(product.getCode());
        product.setUserAccount(user);
        this.productDAO.updateProduct(product);
    }

    @Override
    @Transactional
    public List<Product> listProducts() {

        List<Product> products = this.productDAO.findProducts();
        this.convertPathToPictureForList(products);
        this.fillSongs(products);
        return products;
    }

    @Override
    @Transactional
    public Product getProduct(String code) {
        Product p = this.productDAO.findProduct(code);

        Set<Song> songs = new TreeSet<>();
        songs.addAll((this.songDAO.findSongs(code)));
        p.setSongs(songs);

        try {
            p.setPictureBase64String(getPicture(p.getPictureFilePath()));
        } catch (IOException e) {
            p.setPictureBase64String(null);
        }
        return p;
    }

    @Override
    @Transactional
    public void removeProduct(String code) {
        this.productDAO.removeProduct(code);
    }

    @Override
    @Transactional
    public List<Product> getProductsForSale() {
        List<Product> products = this.productDAO.findProductsForSale();
        this.convertPathToPictureForList(products);
        this.fillSongs(products);
        return products;
    }

    @Override
    @Transactional
    public List<Product> getProductsForSale(String genre) {
        List<Product> products = this.productDAO.findProductsForSale(genre);
        this.convertPathToPictureForList(products);
        this.fillSongs(products);
        return products;
    }

    @Override
    @Transactional
    public List<String> getGenres() {
        return this.productDAO.findGenres();
    }

    @Override
    @Transactional
    public void buyProduct(String code, int userId) {
        Product p = this.productDAO.findProduct(code);

        UserAccount previousOwner = this.productDAO.findUser(p.getCode());
        UserAccount futureOwner = this.userDAO.findUser(userId);

        previousOwner.setMoney(previousOwner.getMoney() + p.getPrice());
        futureOwner.setMoney(futureOwner.getMoney() - p.getPrice());

        p.setUserAccount(futureOwner);
        p.setForSale(false);
    }

    @Override
    @Transactional
    public UserAccount getUser(String code) {
        return this.productDAO.findUser(code);
    }

    @Override
    @Transactional
    public List<Product> listProductsForUser(int userId) {
        List<Product> products = this.productDAO.findProducts(userId);
        this.convertPathToPictureForList(products);
        this.fillSongs(products);
        return products;
    }

    @Override
    @Transactional
    public void downloadList(HttpServletResponse response) throws IOException {

        List<Product> productsForSale = this.getProductsForSale();

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + FILE_FOR_SAVE_NAME + ".xls");

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(SHEET_NAME);

        HSSFRow rowhead = sheet.createRow((short) 0);
        rowhead.createCell(0).setCellValue(TABLE_COLUMN_CODE);
        rowhead.createCell(1).setCellValue(TABLE_COLUMN_NAME);
        rowhead.createCell(2).setCellValue(TABLE_COLUMN_PRICE);
        rowhead.createCell(3).setCellValue(TABLE_COLUMN_OWNER);

        short rowNumber = 1;
        for (Product p : productsForSale) {

            String ownerName = this.getUser(p.getCode()).getUserName();

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


            File dir = new File(SAVE_TO_DIRECTORY);

            File serverFile = new File(dir.getAbsolutePath()
                    + File.separator + name + FORMAT_JPG);
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            return dir.getAbsolutePath()
                    + File.separator + name + FORMAT_JPG;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getPicture(String uri) throws IOException {
        byte[] imageInByte = new byte[]{};
        try {

            BufferedImage originalImage =
                    ImageIO.read(new File(uri));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return new String(Base64.encodeBase64(imageInByte));
    }

    @Override
    @Transactional
    public void attachSongs(List<String> songs, String code) {
        Product p = this.productDAO.findProduct(code);
        Set<Song> songsSet = new TreeSet<>();
        for(String songName : songs){
            Song song = new Song();
            song.setSongName(songName);
            song.setProduct(p);
            this.songDAO.createSong(song);

            songsSet.add(song);
        }
        p.setSongs(songsSet);
    }

    private void convertPathToPictureForList(List<Product> list) {
        for (Product product : list) {
            try {
                if (product.getPictureFilePath() != null) {
                    product.setPictureBase64String(this.getPicture(product.getPictureFilePath()));
                }
            } catch (IOException e) {
                product.setPictureBase64String(null);
                // e.printStackTrace();
            }
        }
    }

    private void fillSongs(List<Product> list) {
        for (Product product : list) {
            Set<Song> songs = new TreeSet<>();
            songs.addAll(this.songDAO.findSongs(product.getCode()));
            product.setSongs(songs);
        }
    }


}
