package controller;

import model.Product;
import model.UserAccount;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ProductService;
import service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static util.Constants.*;

@Controller
public class ProductController {

    /*

        AWAITING SPRING SECURITY FULL INTEGRATION

     */
    private ProductService productService;
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = PRODUCT_SERVICE)
    public void setProductService(ProductService ps) {
        this.productService = ps;
    }

    @Autowired(required = true)
    @Qualifier(value = USER_SERVICE)
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    @RequestMapping(value = {"user/manageProducts", "admin/manageProducts"}, method = RequestMethod.GET)
    public String manageProducts(Model model, HttpSession session) {

        model.addAttribute(PRODUCT_TO_UPRADE_ATTRIBUTE, new Product());
        model.addAttribute(PRODUCT_TO_CREATE_ATTRIBUTE, new Product());

        this.fillList(session,model);

        return "manageProducts";
    }

    @RequestMapping(value = {"/product/create"}, method = RequestMethod.POST)
    public String doCreateProduct(@Valid @ModelAttribute(PRODUCT_TO_CREATE_ATTRIBUTE) Product product, BindingResult result, Model model, HttpSession session) {

        if(result.hasErrors()){
            fillList(session, model);
            return "manageProducts";
        }

        Object loggedUserIdObj =  session.getAttribute(LOGGED_USER_ID_ATTRIBUTE);

        if (loggedUserIdObj == null) {
            model.addAttribute(ERROR_STRING_ATTRIBUTE, "You are not logged user");
            fillList(session, model);
            return "manageProducts";
        }

        UserAccount user = this.userService.getUserById((int)loggedUserIdObj);
        product.setUserAccount(user);

        this.productService.createProduct(product);

        return "redirect:/user/manageProducts";
    }

    @RequestMapping(value = {"/product/update/{code}"}, method = RequestMethod.GET)
    public String updateProduct(@PathVariable("code") String code, Model model, HttpSession session) {

        model.addAttribute(PRODUCT_TO_UPRADE_ATTRIBUTE, this.productService.getProductByCode(code));
        model.addAttribute(PRODUCT_TO_CREATE_ATTRIBUTE, new Product());

        this.fillList(session, model);

        return "manageProducts";
    }


    @RequestMapping(value = "/product/doUpdate", method = RequestMethod.POST)
    public String doUpdateProduct(@Valid @ModelAttribute(PRODUCT_TO_UPRADE_ATTRIBUTE) Product product, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {

            model.addAttribute(PRODUCT_TO_CREATE_ATTRIBUTE, new Product());
            this.fillList(session, model);
            return "manageProducts";
        }

        this.productService.updateProduct(product);


        return "redirect:/user/manageProducts";
    }

    @RequestMapping(value = {"/product/remove/{code}"}, method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("code") String code, Model model) {

        this.productService.removeProduct(code);

        return "redirect:/user/manageProducts";
    }

    @RequestMapping(value = {"/marketplace"}, method = RequestMethod.GET)
    public String marketplace(Model model, HttpSession session) {

        Object loggedUserIdObj =  session.getAttribute("loggedUserId");

        if (loggedUserIdObj == null) {
            model.addAttribute(LOGGED_USER_MONEY_ATTRIBUTE, (int)9999999);
            model.addAttribute(LOGGED_USER_NAME_ATTRIBUTE, "admin");
            model.addAttribute(PRODUCT_LIST_ATTRIBUTE, this.productService.findProductsForSale());

            return "marketplace";
        }

        UserAccount userAccount = this.userService.getUserById((int)loggedUserIdObj);

        model.addAttribute(PRODUCT_LIST_ATTRIBUTE, userAccount.getMoney());
        model.addAttribute(LOGGED_USER_NAME_ATTRIBUTE, userAccount.getUserName());
        model.addAttribute(PRODUCT_LIST_ATTRIBUTE, this.productService.findProductsForSale());

        return "marketplace";
    }

    @RequestMapping(value = {"/buyProduct/{code}"}, method = RequestMethod.GET)
    public String productTransaction(@PathVariable("code") String code, HttpSession session, Model model) {

        Object loggedUserIdObj =  session.getAttribute(LOGGED_USER_ID_ATTRIBUTE);

        if (loggedUserIdObj == null) {
            model.addAttribute(PRODUCT_TO_CREATE_ATTRIBUTE, "You are not logged user");
            model.addAttribute(PRODUCT_LIST_ATTRIBUTE, this.productService.findProductsForSale());
            return "marketplace";
        }

        this.productService.buyProduct(code, (int)loggedUserIdObj);

        return "redirect:/user/manageProducts";
    }

    @RequestMapping(value = {"/downloadList"}, method = RequestMethod.GET)
    public void downloadList(HttpServletResponse response){

        try {
            this.productService.downloadList(response);
        } catch (IOException e) {
            //??
        }
    }

    private void fillList(HttpSession session, Model model){
        Object loggedUserIdObj =  session.getAttribute("loggedUserId");

        if (loggedUserIdObj == null) {
            model.addAttribute("listProducts", this.productService.listProduct());
        } else {
            List<Product> productList = this.productService.listProductsForUser((int)loggedUserIdObj);
            model.addAttribute("listProducts", productList);
        }
    }


}
