package controller;

import model.Product;
import model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ProductService;

import javax.servlet.http.HttpSession;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired(required = true)
    @Qualifier(value = "productService")
    public void setProductService(ProductService ps) {
        this.productService = ps;
    }


    @RequestMapping(value = {"user/manageProducts", "admin/manageProducts"}, method = RequestMethod.GET)
    public String listProducts(Model model, HttpSession session) {

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
    @RequestMapping(value = "/product/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("product") Product product, HttpSession session, Model model) {
        model.addAttribute("productToCreate", new Product());

        UserAccount owner = this.productService.getProductByCode(product.getCode()).getUserAccount();
        product.setUserAccount(owner);

        this.productService.updateProduct(product,session);

        owner = this.productService.getProductByCode(product.getCode()).getUserAccount();
        session.setAttribute("loggedUser", owner);

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


        UserAccount user = (UserAccount) session.getAttribute("loggedUser");
        if (user == null) {
            model.addAttribute("listProducts", this.productService.listProduct());
        } else {
            model.addAttribute("listProducts", user.getProducts());
        }
        return "manageProducts";
    }


    @RequestMapping(value = {"/product/create"}, method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("productToCreate") Product product,Model model,  HttpSession session){
        UserAccount user = (UserAccount) session.getAttribute("loggedUser");
        product.setUserAccount(user);

        this.productService.createProduct(product);

        model.addAttribute("listProducts", user.getProducts().add(product));
        return "redirect:/user/manageProducts";
    }

}
