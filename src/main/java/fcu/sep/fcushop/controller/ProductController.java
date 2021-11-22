
package fcu.sep.fcushop.controller;

import fcu.sep.fcushop.model.Product;
import fcu.sep.fcushop.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 *this is a class for get product information.
 */

@RestController

public class ProductController {
  @Autowired
  ProductService productManager;

  @GetMapping("/products")
  public List<Product> getProducts() {
    return productManager.getProducts();
  }

  @GetMapping("/keyword")
  public List<Product> getProducts(@RequestParam String keyword) {
    return productManager.getProducts(keyword);
  }
}
