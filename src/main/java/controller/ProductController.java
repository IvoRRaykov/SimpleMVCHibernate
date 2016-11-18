package controller;

import model.Product;
import model.UserAccount;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.ProductService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static util.Constants.*;

@Controller
public class ProductController {
//VERY VERY WRONG
    private ProductService productService;
    private UserService userService;

    @Autowired()
    @Qualifier(value = PRODUCT_SERVICE)
    public void setProductService(ProductService ps) {
        this.productService = ps;
    }

    @Autowired()
    @Qualifier(value = USER_SERVICE)
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = {"product/manage"}, method = RequestMethod.GET)
    public String manageProducts(@RequestParam(value = "message", required = false) String message, Model model, HttpSession session, HttpServletRequest request) {

        model.addAttribute(PRODUCT_TO_UPDATE_ATTRIBUTE, new Product());
        model.addAttribute(PRODUCT_TO_CREATE_ATTRIBUTE, new Product());

        if (!isInUserRole(request)) {
            model.addAttribute(USERS_NAMES_LIST_ATTRIBUTE, this.userService.listUsersNames());
        }

        this.fillList(request, session, model);

        if (message != null) {
            model.addAttribute(MESSAGE_ATTRIBUTE, "Songs attached successfully!");
        }

        return "manageProducts";
    }

    @RequestMapping(value = {"/product/create"}, method = RequestMethod.POST)
    public String doCreateProduct(@Valid @ModelAttribute(PRODUCT_TO_CREATE_ATTRIBUTE) Product product, BindingResult result,
                                  Model model, HttpSession session, HttpServletRequest request) {

        if (result.hasErrors()) {
            fillList(request, session, model);
            return "manageProducts";
        }

        UserAccount user = null;

        if (isInUserRole(request)) {
            Object loggedUserIdObj = session.getAttribute(LOGGED_USER_ID_ATTRIBUTE);
            user = this.userService.getUser((int) loggedUserIdObj);
        } else {
            String userName = request.getParameter(USERS_NAMES_LIST_ATTRIBUTE);
            user = this.userService.getUser(userName);
        }

        product.setUserAccount(user);
        this.productService.createProduct(product);


        model.addAttribute(PRODUCT_TO_UPDATE_ATTRIBUTE, new Product());
        model.addAttribute(PRODUCT_TO_CREATE_ATTRIBUTE, new Product());

        if (!isInUserRole(request)) {
            model.addAttribute(USERS_NAMES_LIST_ATTRIBUTE, this.userService.listUsersNames());
        }

        this.fillList(request, session, model);

        model.addAttribute(PRODUCT_TO_CREATE_CODE_ATTRIBUTE, product.getCode());

        return "manageProducts";
    }


    //TODO: very very wrong....
    @RequestMapping(value = "/uploadPicture/{createOrUpload}", method = RequestMethod.POST)
    public String uploadPicture(@RequestParam("name") String name,
                                @RequestParam("file") MultipartFile file,
                                @RequestParam(value = "code", required = false) String code,
                                @PathVariable("createOrUpload") String createOrUpload,
                                Model model, HttpServletRequest request, HttpSession session) {

        if (!file.isEmpty()) {
            String pictureFilePath = this.productService.uploadPicture(name, file);

            if (pictureFilePath != null) {

                Product productToCreate = new Product();
                Product productToUpdate = new Product();
                try {
                    if (createOrUpload.equals(PRODUCT_CREATE_URL_MARKER)) {
                        productToCreate.setPictureFilePath(pictureFilePath);
                        productToCreate.setPictureBase64String(this.productService.getPicture(pictureFilePath));

                    } else if (createOrUpload.equals(PRODUCT_UPDATE_URL_MARKER)) {
                        productToUpdate = this.productService.getProduct(code);
                        productToUpdate.setPictureFilePath(pictureFilePath);
                        productToUpdate.setPictureBase64String(this.productService.getPicture(pictureFilePath));

                    }
                } catch (IOException e) {
                    return "_errorUpload";
                }
                model.addAttribute(PRODUCT_TO_UPDATE_ATTRIBUTE, productToUpdate);
                model.addAttribute(PRODUCT_TO_CREATE_ATTRIBUTE, productToCreate);

                this.fillList(request, session, model);

                if (!isInUserRole(request)) {
                    model.addAttribute(USERS_NAMES_LIST_ATTRIBUTE, this.userService.listUsersNames());
                }

                return "manageProducts";
            } else {

                return "_errorUpload";
            }
        }

        return "_errorUpload";
    }


    @RequestMapping(value = {"/product/update/{code}"}, method = RequestMethod.GET)
    public String updateProduct(@PathVariable("code") String code,
                                Model model, HttpSession session, HttpServletRequest request) {

        model.addAttribute(PRODUCT_TO_UPDATE_ATTRIBUTE, this.productService.getProduct(code));
        model.addAttribute(PRODUCT_TO_CREATE_ATTRIBUTE, new Product());


        if (!isInUserRole(request)) {
            model.addAttribute(USERS_NAMES_LIST_ATTRIBUTE, this.userService.listUsersNames());
        }

        this.fillList(request, session, model);

        return "manageProducts";
    }


    @RequestMapping(value = "/product/doUpdate", method = RequestMethod.POST)
    public String doUpdateProduct(@Valid @ModelAttribute(PRODUCT_TO_UPDATE_ATTRIBUTE) Product product, BindingResult result,
                                  Model model, HttpSession session, HttpServletRequest request) {

        if (result.hasErrors()) {

            model.addAttribute(PRODUCT_TO_CREATE_ATTRIBUTE, new Product());
            try {
                product.setPictureBase64String(this.productService.getPicture(product.getPictureFilePath()));
            } catch (IOException e) {
                return "_errorUpload";
            }
            this.fillList(request, session, model);
            return "manageProducts";
        }

        this.productService.updateProduct(product);

        return "redirect:/product/manage";
    }

    @RequestMapping(value = {"/product/remove/{code}"}, method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("code") String code) {

        this.productService.removeProduct(code);

        return "redirect:/product/manage";
    }

    @RequestMapping(value = {"/product/marketplace"}, method = RequestMethod.GET)
    public String marketplace(Model model, HttpSession session) {

        Object loggedUserIdObj = session.getAttribute(LOGGED_USER_ID_ATTRIBUTE);
        UserAccount userAccount = this.userService.getUser((int) loggedUserIdObj);

        model.addAttribute(LOGGED_USER_MONEY_ATTRIBUTE, userAccount.getMoney());
        model.addAttribute(LOGGED_USER_NAME_ATTRIBUTE, userAccount.getUserName());
        model.addAttribute(GENRES_LIST_ATTRIBUTE, this.productService.getGenres());
        model.addAttribute(PRODUCT_LIST_ATTRIBUTE, this.productService.getProductsForSale());


        return "marketplace";
    }

    @RequestMapping(value = {"/product/marketplace/{genre}"}, method = RequestMethod.GET)
    public String marketplace(@PathVariable(value = "genre") String genre, Model model, HttpSession session) {

        Object loggedUserIdObj = session.getAttribute(LOGGED_USER_ID_ATTRIBUTE);
        UserAccount userAccount = this.userService.getUser((int) loggedUserIdObj);

        model.addAttribute(LOGGED_USER_MONEY_ATTRIBUTE, userAccount.getMoney());
        model.addAttribute(LOGGED_USER_NAME_ATTRIBUTE, userAccount.getUserName());
        model.addAttribute(GENRES_LIST_ATTRIBUTE, this.productService.getGenres());
        model.addAttribute(PRODUCT_LIST_ATTRIBUTE, this.productService.getProductsForSale(genre));

        return "marketplace";
    }


    @RequestMapping(value = {"/product/buy/{code}"}, method = RequestMethod.GET)
    public String productTransaction(@PathVariable("code") String code,
                                     HttpSession session) {

        Object loggedUserIdObj = session.getAttribute(LOGGED_USER_ID_ATTRIBUTE);

        this.productService.buyProduct(code, (int) loggedUserIdObj);

        return "redirect:/product/manage";
    }

    @RequestMapping(value = {"/product/downloadList"}, method = RequestMethod.GET)
    public void downloadList(HttpServletResponse response) {

        try {
            this.productService.downloadList(response);
        } catch (IOException e) {
            //??
        }
    }

    @RequestMapping(value = {"/product/doAttachSongs"}, method = RequestMethod.POST)
    public String doAttachSongs(@RequestParam(value = "myArray[]", required = false) String[] myArray,
                              @RequestParam(value = "product_code", required = false) String code) {

        if (myArray != null) {
            List<String> songs = Arrays.asList(myArray);
            this.productService.attachSongs(songs, code);
        }

        return "redirect:product/manage";
    }

    @RequestMapping(value = {"/product/attachSongs/{code}"}, method = RequestMethod.GET)
    public String attachSongs(@PathVariable(value = "code") String code,
                              Model model, HttpServletRequest request, HttpSession session){

        model.addAttribute(PRODUCT_TO_UPDATE_ATTRIBUTE, new Product());
        model.addAttribute(PRODUCT_TO_CREATE_ATTRIBUTE, new Product());

        if (!isInUserRole(request)) {
            model.addAttribute(USERS_NAMES_LIST_ATTRIBUTE, this.userService.listUsersNames());
        }

        this.fillList(request, session, model);

        model.addAttribute(PRODUCT_TO_CREATE_CODE_ATTRIBUTE, code);
        model.addAttribute(ATTACH_SONGS_ATTRIBUTE, "yes");

        return "manageProducts";
    }


    private void fillList(HttpServletRequest request, HttpSession session, Model model) {

        List<Product> productList = null;

        if (isInUserRole(request)) {
            Object loggedUserIdObj = session.getAttribute(LOGGED_USER_ID_ATTRIBUTE);
            productList = this.productService.listProductsForUser((int) loggedUserIdObj);
        } else {
            productList = this.productService.listProducts();
        }

        model.addAttribute(PRODUCT_LIST_ATTRIBUTE, productList);
    }

    private boolean isInUserRole(HttpServletRequest request) {

        SecurityContextHolderAwareRequestWrapper wrapper = new SecurityContextHolderAwareRequestWrapper(request, EMPTY);

        return wrapper.isUserInRole(USER_ROLE);
    }
}



