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

@Controller
public class ProductController {

    private ProductService productService;
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "productService")
    public void setProductService(ProductService ps) {
        this.productService = ps;
    }

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"user/manageProducts", "admin/manageProducts"}, method = RequestMethod.GET)
    public String manageProducts(Model model, HttpSession session) {

        model.addAttribute("productToUpdate", new Product());
        model.addAttribute("productToCreate", new Product());

        this.fillList(session,model);

        return "manageProducts";
    }

    @RequestMapping(value = {"/product/create"}, method = RequestMethod.POST)
    public String doCreateProduct(@Valid @ModelAttribute("productToCreate") Product product, BindingResult result, Model model, HttpSession session) {

        if(result.hasErrors()){
            fillList(session, model);
            return "manageProducts";
        }

        Object loggedUserIdObj =  session.getAttribute("loggedUserId");

        if (loggedUserIdObj == null) {
            model.addAttribute("errorString", "You are not logged user");
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

        model.addAttribute("productToUpdate", this.productService.getProductByCode(code));
        model.addAttribute("productToCreate", new Product());

        this.fillList(session, model);

        return "manageProducts";
    }


    @RequestMapping(value = "/product/doUpdate", method = RequestMethod.POST)
    public String doUpdateProduct(@Valid @ModelAttribute("productToUpdate") Product product, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {

            model.addAttribute("productToCreate", new Product());
            this.fillList(session, model);
            return "manageProducts";
        }

        product.setUserAccount( this.productService.getUserByProductCode(product.getCode()) );
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
            model.addAttribute("loggedUserMoney", (int)9999999);
            model.addAttribute("loggedUserName", "admin");
            model.addAttribute("productList", this.productService.findProductsForSale());

            return "marketplace";
        }
        UserAccount userAccount = this.userService.getUserById((int)loggedUserIdObj);
        model.addAttribute("loggedUserMoney", userAccount.getMoney());
        model.addAttribute("loggedUserName", userAccount.getUserName());
        model.addAttribute("productList", this.productService.findProductsForSale());

        return "marketplace";
    }

    @RequestMapping(value = {"/buyProduct/{code}"}, method = RequestMethod.GET)
    public String productTransaction(@PathVariable("code") String code, HttpSession session, Model model) {

        Object loggedUserIdObj =  session.getAttribute("loggedUserId");

        if (loggedUserIdObj == null) {
            model.addAttribute("errorString", "You are not logged user");
            model.addAttribute("productList", this.productService.findProductsForSale());
            return "marketplace";
        }

        this.productService.buyProduct(code, (int)loggedUserIdObj);

        return "redirect:/user/manageProducts";
    }

    @RequestMapping(value = {"/downloadList"}, method = RequestMethod.GET)
    public void downloadList(Model model, HttpServletResponse response){

        try {
            this.productService.downloadList(response);
        } catch (IOException e) {
            e.printStackTrace();
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
