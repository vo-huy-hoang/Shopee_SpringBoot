package org.example.shopee.controller;

import org.example.shopee.dto.CustomerProductDTO;
import org.example.shopee.dto.ProductCreateDTO;
import org.example.shopee.mapper.CustomerProductMapper;
import org.example.shopee.model.Comment;
import org.example.shopee.model.CustomerProduct;
import org.example.shopee.model.Product;
import org.example.shopee.service.ICommentService;
import org.example.shopee.service.ICustomerProductService;
import org.example.shopee.service.ICustomerService;
import org.example.shopee.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/carts")
public class CartController {
    @Autowired
    @Qualifier("productService")
    private IProductService productService;
    @Autowired
    @Qualifier("commentService")
    private ICommentService commentService;
    @Autowired
    @Qualifier("customerProductService")
    private ICustomerProductService customerProductService;
    @Autowired
    @Qualifier("customerService")
    private ICustomerService customerService;
    @Autowired
//    @Qualifier("customerProductMapper")
    private CustomerProductMapper customerProductMapper;
    @GetMapping("/buyProduct")
    public String showBuyProduct(Model model, Integer id) {
        Product product = productService.findById(id);
        List<Comment> commentList = commentService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("commentList", commentList);
        model.addAttribute("comment", new Comment());
        return "/shoppe/showProduct";
    }
    @GetMapping("")
    public String listCart(Model model) {
        List<CustomerProduct> customerProductList = customerProductService.findAll();
        model.addAttribute("customerProductList", customerProductList);
//        List<Product> productList = productService.findAll();
//        model.addAttribute("productList", productList);
        return "/shoppe/cart";
    }

    @GetMapping("/delete")
    public String deleteCart(CustomerProductDTO customerProductDTO, RedirectAttributes redirectAttributes) {
        customerProductService.delete(customerProductDTO);
        redirectAttributes.addFlashAttribute("message", "Xoá thành công");
        return "redirect:/carts";
    }
    @PostMapping("/buyProduct")
    public String buy(CustomerProductDTO customerProductDTO, RedirectAttributes redirectAttributes) {
        CustomerProduct customerProduct = new CustomerProduct();
        customerProduct.setProduct(productService.findById(Integer.parseInt(customerProductDTO.getProductId())));
        customerProduct.setCustomer(customerService.findById(1));
        customerProduct.setQuantity(Integer.parseInt(customerProductDTO.getQuantity()));
        if (customerProductService.existProductIdAndCutomerId(customerProductDTO)) {
            customerProductService.duplicateProduct(customerProductDTO);
        } else {
            customerProductService.save(customerProduct);
        }
        redirectAttributes.addFlashAttribute("message", "Thêm vào giỏ hàng thành công");
        return "redirect:/carts";
    }
    @PostMapping("/commentProduct")
    public String createComment(Comment comment, String content, Integer productId, RedirectAttributes redirectAttributes) {
        comment.setContent(content);
        commentService.save(comment);
        return "redirect:/carts/buyProduct?id=" + productId;
    }

}
