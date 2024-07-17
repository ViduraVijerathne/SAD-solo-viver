/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import exceptions.EntityAlreadyExistException;
import exceptions.EntityNotFoundException;
import exceptions.ValidationException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author vidur
 */
public class ProductController {

    final java.sql.Connection conn;

    public ProductController() {
        conn = database.Database.getConnection();
    }

    public Product getProductByName(String n) throws java.sql.SQLException {
        if (n == null || n.length() == 0) {
            return null;
        }
        String q = "SELECT * FROM products INNER JOIN units ON units.id = products.units_id  INNER JOIN catrgories ON  catrgories.id = products.catrgories_id WHERE products.`name` = ?";
        java.sql.PreparedStatement stmt = conn.prepareStatement(q);
        stmt.setString(1, n);
        java.sql.ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            return Product.fromResultSet(resultSet);
        } else {
            return null;
        }

    }

    public void add(Product p) throws ValidationException, java.sql.SQLException, EntityAlreadyExistException {
        if (p.isValid()) {
            if (getProductByName(p.getName()) == null) {
                String q = "INSERT INTO `products` (`barcode`, `name`, `refil_stock_limit`, `units_id`, `catrgories_id`) VALUES (?,?, ?, ?, ?);";
                java.sql.PreparedStatement stmt = conn.prepareStatement(q);
                stmt.setString(1, p.getBarcode());
                stmt.setString(2, p.getName());
                stmt.setDouble(3, p.getRefillStockLimit());
                stmt.setInt(4, p.getUnit().getId());
                stmt.setInt(5, p.getCategory().getId());
                stmt.execute();
            } else {
                throw new EntityAlreadyExistException("product name already exist");
            }

        }

    }

    public List<Product> getAll() throws java.sql.SQLException {
        List<Product> p = new ArrayList<>();
        String q = "SELECT * FROM products INNER JOIN units ON units.id = products.units_id  INNER JOIN catrgories ON  catrgories.id = products.catrgories_id ";
        java.sql.PreparedStatement stmt = conn.prepareStatement(q);
        java.sql.ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            p.add(Product.fromResultSet(resultSet));
        }

        return p;
    }

    public void update(Product p) throws ValidationException, java.sql.SQLException, EntityNotFoundException {
        if (p.isValid()) {
            String q = "UPDATE `products` SET `barcode`=?, `name`=?, `refil_stock_limit`=?, `units_id`=? , `catrgories_id`=? WHERE  `id`=?";
            java.sql.PreparedStatement stmt = conn.prepareStatement(q);
            stmt.setString(1, p.getBarcode());
            stmt.setString(2, p.getName());
            stmt.setDouble(3, p.getRefillStockLimit());
            stmt.setInt(4, p.getUnit().getId());
            stmt.setInt(5, p.getCategory().getId());
            stmt.setInt(6, p.getId());
            stmt.execute();
        }

    }

}
