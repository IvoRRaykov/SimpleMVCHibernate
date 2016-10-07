package controller;

import model.Product;
import model.UserAccount;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

        UserAccount user = (UserAccount) session.getAttribute("loggedUser");

        if (user == null) {
            model.addAttribute("listProducts", this.productService.listProduct());
        } else {
            model.addAttribute("listProducts", user.getProducts());
        }
        model.addAttribute("product", new Product());
        model.addAttribute("productToCreate", new Product());

        return "manageProducts";
    }

    //zashto ne stava v service
    @RequestMapping(value = "/product/update/{userId}", method = RequestMethod.POST)
    public String updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, @PathVariable("userId") int userId, Model model, HttpSession session) {
        model.addAttribute("productToCreate", new Product());

        if (result.hasErrors()) {
            fillList(session, model);

            return "manageProducts";

        }

        product.setUserAccount(this.userService.getUserById(userId));

        session.setAttribute("loggedUser", this.productService.updateProduct(product));

        return "redirect:/user/manageProducts";
    }

    @RequestMapping("/removeProduct/{code}")
    public String deleteProduct(@PathVariable("code") String code, Model model) {
        model.addAttribute("productToCreate", new Product());

        this.productService.removeProduct(code);
        return "redirect:/user/manageProducts";
    }

    @RequestMapping("/editProduct/{code}")
    public String editProduct(@PathVariable("code") String code, Model model, HttpSession session) {

        model.addAttribute("product", this.productService.getProductByCode(code));
        model.addAttribute("productToCreate", new Product());

        fillList(session, model);

        return "manageProducts";
    }


    @RequestMapping(value = {"/product/create"}, method = RequestMethod.POST)
    public String createProduct(@Valid @ModelAttribute("productToCreate") Product product, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            fillList(session, model);
            return "manageProducts";
        }
        UserAccount user = (UserAccount) session.getAttribute("loggedUser");
        product.setUserAccount(user);

        this.productService.createProduct(product);

        model.addAttribute("listProducts", user.getProducts().add(product));
        return "redirect:/user/manageProducts";
    }

    @RequestMapping(value = {"/marketplace"}, method = RequestMethod.GET)
    public String marketplace(Model model) {

        model.addAttribute("productList", this.productService.findProductsForSale());

        return "marketplace";
    }

    @RequestMapping(value = {"/buyProduct/{code}"}, method = RequestMethod.GET)
    public String buyProduct(@PathVariable("code") String code, HttpSession session) {


        session.setAttribute("loggedUser", this.productService.buyProduct(code, (UserAccount) session.getAttribute("loggedUser")));
        return "redirect:/user/manageProducts";
    }

    private void fillList(HttpSession session, Model model){
        UserAccount user = (UserAccount) session.getAttribute("loggedUser");
        if (user == null) {
            model.addAttribute("listProducts", this.productService.listProduct());
        } else {
            model.addAttribute("listProducts", user.getProducts());
        }
    }
}
