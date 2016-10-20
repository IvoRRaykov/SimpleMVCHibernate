package controller;

import model.Product;
import model.UserAccount;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ProductService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static util.Constants.*;

@Controller
public class ProductController {

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


    @RequestMapping(value = {"product/manage"}, method = RequestMethod.GET)
    public String manageProducts(Model model, HttpSession session, HttpServletRequest request) {

        model.addAttribute(PRODUCT_TO_UPRADE_ATTRIBUTE, new Product());
        model.addAttribute(PRODUCT_TO_CREATE_ATTRIBUTE, new Product());

        this.fillList(request, session, model);

        return "manageProducts";
    }

    @RequestMapping(value = {"/product/create"}, method = RequestMethod.POST)
    public String doCreateProduct(@Valid @ModelAttribute(PRODUCT_TO_CREATE_ATTRIBUTE) Product product, BindingResult result, Model model, HttpSession session, HttpServletRequest request) {

        if (result.hasErrors()) {
            fillList(request, session, model);
            return "manageProducts";
        }

        Object loggedUserIdObj = session.getAttribute(LOGGED_USER_ID_ATTRIBUTE);

        UserAccount user = this.userService.getUserById((int) loggedUserIdObj);
        product.setUserAccount(user);

        this.productService.createProduct(product);

        return "redirect:/product/manage";
    }

    @RequestMapping(value = {"/product/update/{code}"}, method = RequestMethod.GET)
    public String updateProduct(@PathVariable("code") String code, Model model, HttpSession session, HttpServletRequest request) {

        model.addAttribute(PRODUCT_TO_UPRADE_ATTRIBUTE, this.productService.getProductByCode(code));
        model.addAttribute(PRODUCT_TO_CREATE_ATTRIBUTE, new Product());

        this.fillList(request, session, model);

        return "manageProducts";
    }


    @RequestMapping(value = "/product/doUpdate", method = RequestMethod.POST)
    public String doUpdateProduct(@Valid @ModelAttribute(PRODUCT_TO_UPRADE_ATTRIBUTE) Product product, BindingResult result, Model model, HttpSession session, HttpServletRequest request) {

        if (result.hasErrors()) {

            model.addAttribute(PRODUCT_TO_CREATE_ATTRIBUTE, new Product());
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

        Object loggedUserIdObj = session.getAttribute("loggedUserId");
        UserAccount userAccount = this.userService.getUserById((int) loggedUserIdObj);

        model.addAttribute(LOGGED_USER_MONEY_ATTRIBUTE, userAccount.getMoney());
        model.addAttribute(LOGGED_USER_NAME_ATTRIBUTE, userAccount.getUserName());
        model.addAttribute(PRODUCT_LIST_ATTRIBUTE, this.productService.findProductsForSale());

        return "marketplace";
    }

    @RequestMapping(value = {"/product/buy/{code}"}, method = RequestMethod.GET)
    public String productTransaction(@PathVariable("code") String code, HttpSession session) {

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

    private void fillList(HttpServletRequest request, HttpSession session, Model model) {

        SecurityContextHolderAwareRequestWrapper wrapper = new SecurityContextHolderAwareRequestWrapper(request, EMPTY);
        List<Product> productList = null;

        if (wrapper.isUserInRole(USER_ROLE)) {
            Object loggedUserIdObj = session.getAttribute(LOGGED_USER_ID_ATTRIBUTE);
            productList = this.productService.listProductsForUser((int) loggedUserIdObj);
        }
        else if(wrapper.isUserInRole(ADMIN_ROLE)){
            productList = this.productService.listProduct();
        }

        model.addAttribute("listProducts", productList);
    }
}



