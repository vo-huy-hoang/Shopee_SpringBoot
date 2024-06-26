package org.example.shopee.controller;

import org.example.shopee.dto.ProductCreateDTO;
import org.example.shopee.dto.ProductEditDTO;
import org.example.shopee.dto.ProductSearchDTO;
import org.example.shopee.mapper.ProductMapper;
import org.example.shopee.model.Product;
import org.example.shopee.repository.IProductRepository;
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
@RequestMapping("/shoppes")
public class ShoppeController {
    @Autowired
    @Qualifier("productService")
    private IProductService productService;
    @Autowired
//    @Qualifier("productMapper")
    private ProductMapper productMapper;
    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("productCreateDTO", new ProductCreateDTO());
        return "shoppe/create";
    }
    @GetMapping("/edit")
    public String showEdit(Model model, Integer id) {
        Product product = productService.findById(id);
        ProductEditDTO productEditDTO = productMapper.toProductEditDTOFromProduct(product);
        model.addAttribute("productEditDTO", productEditDTO);
        return "shoppe/edit";
    }
    @GetMapping("")
    public String showScreen(Model model, ProductSearchDTO productSearchDTO) {
        List<Product> productList = productService.search(productSearchDTO);
        model.addAttribute("productSearchDTO", productSearchDTO);
        model.addAttribute("productList", productList);
        return "shoppe/screen";
    }
    @GetMapping("/viewProduct")
    public String viewProduct(Model model, Integer id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "shoppe/viewProduct";
    }
    @GetMapping("/deleteProduct")
    public String deleteProduct(Integer id, RedirectAttributes redirectAttributes) {
        productService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Xoá thành công");
        return "redirect:/shoppes";
    }
    @PostMapping("/create")
    public String createProduct(Model model, @Validated @ModelAttribute("productCreateDTO") ProductCreateDTO productCreateDTO, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        new ProductCreateDTO().validate(productCreateDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("productCreateDTO", productCreateDTO);
            return "shoppe/create";
        }
        Product product = productMapper.toProductFromProductCreateDTO(productCreateDTO);
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
        return "redirect:/shoppes";
    }
    @PostMapping("/edit")
    public String editProduct(Model model, @Validated @ModelAttribute("productEditDTO") ProductEditDTO productEditDTO, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        new ProductEditDTO().validate(productEditDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("productCreateDTO", productEditDTO);
            return "shoppe/edit";
        }
        Product product = productMapper.toProductFromProductEditDTO(productEditDTO);
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Chỉnh sửa thành công");
        return "redirect:/shoppes";
    }
}
