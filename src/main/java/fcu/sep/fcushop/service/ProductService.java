package fcu.sep.fcushop.service;

import fcu.sep.fcushop.database.Sql2oDbHandler;
import fcu.sep.fcushop.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;

/**
 * productservice.
 */

@Service
public class ProductService {

  @Autowired
  private Sql2oDbHandler sql2oDbHandler;

  public ProductService() {

  }

  /**
   * productservice.
   */

  public List<Product> getProducts() {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select ID id, NAME name, IMAGE_URL imageUrl, "
          + "PRICE price, DESCRIPTION description from PRODUCT";

      return connection.createQuery(query).executeAndFetch(Product.class);
    }
  }

  /**
   * productservice.
   */

  public List<Product> getProducts(String keyword) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select ID id, NAME name, IMAGE_URL imageUrl, PRICE price,"
          + " DESCRIPTION description from PRODUCT where name like :keyword";

      return connection.createQuery(query)
        .addParameter("keyword", "%" + keyword + "%")
        .executeAndFetch(Product.class);
    }
  }

  /**
   * productservice.
   */

  public String addProduct(String bookname, String imgurl, int price, String description) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "insert into fcu_shop.product (NAME, IMAGE_URL, PRICE, DESCRIPTION) "
          + "VALUES(:bookname, :imgurl, :price, :description)";

      System.out.println(query);
      connection.createQuery(query)
          .addParameter("bookname", bookname)
          .addParameter("imgurl", imgurl)
          .addParameter("price", price)
          .addParameter("description", description)
          .executeUpdate();
      return "Success";
    }
  }

  /**
   * productservice.
   */

  public String updateProduct(String name, int price) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "Update fcu_shop.product "
          + "SET PRICE= :price WHERE NAME = :name";

      System.out.println(query);
      connection.createQuery(query)
          .addParameter("name", name)
          .addParameter("price", price)
          .executeUpdate();
      return "Success";

    }
  }

}
