package fcu.sep.fcushop.service;

import fcu.sep.fcushop.database.Sql2oDbHandler;
import fcu.sep.fcushop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;

import java.util.List;

@Service
public class ProductService {

  @Autowired
  private Sql2oDbHandler sql2oDbHandler;

  public ProductService() {

  }

  public List<Product> getProducts() {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select ID id, NAME name, IMAGE_URL imageUrl, PRICE price, DESCRIPTION description"
          + " from PRODUCT";

      return connection.createQuery(query).executeAndFetch(Product.class);
    }
  }
  public List<Product> getProducts(String keyword) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select ID id, NAME name, IMAGE_URL imageUrl, PRICE price, DESCRIPTION description"
          + " from PRODUCT where name like :keyword";

      return connection.createQuery(query)
        .addParameter("keyword","%"+keyword+"%")
        .executeAndFetch(Product.class);
    }
  }

  public String AddProduct(String book_name, String img_url, int price, String description)
  {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "insert into fcu_shop.product (NAME, IMAGE_URL, PRICE, DESCRIPTION) "
          + "VALUES(:book_name, :img_url, :price, :description)";

      System.out.println(query);
      connection.createQuery(query)
          .addParameter("book_name", book_name)
          .addParameter("img_url", img_url)
          .addParameter("price", price)
          .addParameter("description", description)
          .executeUpdate();
      return "Success";
    }
  }

  public String UpdateProduct(String book_name, int price)
  {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "Update fcu_shop.product "
          + "SET PRICE= :price WHERE NAME = :book_name";

      System.out.println(query);
      connection.createQuery(query)
          .addParameter("book_name", book_name)
          .addParameter("price", price)
          .executeUpdate();
      return "Success";

    }
  }

}
