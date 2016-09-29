package controller;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ProductService;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired(required = true)
    @Qualifier(value = "productService")
    public void setProductService(ProductService ps){this.productService = ps; }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String listProducts(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("listProducts", this.productService.listProduct());
        return "product";
    }

    @RequestMapping(value= "/product/add", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") Product product){

        if(product.getCode() == null){
            //new user, add it
            this.productService.createProduct(product);
        }else{
            //existing user, call update
            this.productService.updateProduct(product);
        }
        return "redirect:/products";
    }

    @RequestMapping("/removeProduct/{code}")
    public String removeUser(@PathVariable("code") String code){

        this.productService.removeProduct(code);
        return "redirect:/products";
    }

    @RequestMapping("/editProduct/{code}")
    public String editUser(@PathVariable("code") String code, Model model){
        model.addAttribute("product", this.productService.getProductByCode(code));
        model.addAttribute("listProducts", this.productService.listProduct());
        return "product";
    }

}
