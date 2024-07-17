/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import exceptions.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author vidur
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {

    private int id;
    private String name;
    private String barcode;
    private double refillStockLimit;
    private Unit unit;
    private Category category;

    public boolean isValid() throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name cannot be null or empty.");
        }
        if (barcode != null && barcode.length() > 0 && barcode.length() != 12) {
            throw new ValidationException("Barcode  length must to 12.");
        }
        if (refillStockLimit < 0) {
            throw new ValidationException("Refill stock limit cannot be negative.");
        }if (refillStockLimit < 1) {
            throw new ValidationException("Refill stock limit  must to be greater than 1.");
        }
        if (unit == null || !unit.isValid()) {
            throw new ValidationException("Unit is invalid.");
        }
        if (category == null || !category.isValid()) {
            throw new ValidationException("Category is invalid.");
        }
        if (name.length() > 40) {
            throw new ValidationException("Category name is too long.");
        }
        if (name.length() < 2) {
            throw new ValidationException("Category name is too shrot.");
        }
        return true;
    }

    public static Product fromResultSet(java.sql.ResultSet resultSet) throws java.sql.SQLException {
        try {
            int id = resultSet.getInt("products.id");
            String name = resultSet.getString("products.name");
            String barcode = resultSet.getString("products.barcode");
            double refillStockLimit = resultSet.getDouble("products.refil_stock_limit");
            Unit unit = Unit.fromResultSet(resultSet);
            Category category = Category.fromResultSet(resultSet);
            return new Product(id, name, barcode, refillStockLimit, unit, category);
        } catch (java.sql.SQLException e) {
            throw new java.sql.SQLException("Error while creating Product from ResultSet", e);
        }
    }

}
