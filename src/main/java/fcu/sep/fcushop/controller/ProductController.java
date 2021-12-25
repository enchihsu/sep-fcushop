
package fcu.sep.fcushop.controller;

import fcu.sep.fcushop.model.Product;
import fcu.sep.fcushop.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/products/{keyword}")
  public List<Product> getProducts(@PathVariable("keyword") String keyword) {
    return productManager.getProducts(keyword);
  }

  @RequestMapping(value="/add_product", method = RequestMethod.GET)
  @ResponseBody
  public String addProduct(
      @RequestParam("book_name") String book_name,
      @RequestParam("img_url") String img_url,
      @RequestParam("price") Integer price,
      @RequestParam("description") String description
  )
  {
    return productManager.AddProduct(book_name, img_url, price, description);
  }

  @RequestMapping(value="/update_product", method = RequestMethod.GET)
  @ResponseBody
  public String updateProduct(
      @RequestParam("book_name") String book_name,
      @RequestParam("price") Integer price
  )
  {
    return productManager.UpdateProduct(book_name, price);
  }

}
