/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import exceptions.EntityAlreadyExistException;
import exceptions.ValidationException;
import java.sql.SQLException;
import model.Category;
import model.Unit;

/**
 *
 * @author vidur
 */
public class CategoryController {

    final java.sql.Connection conn;

    public CategoryController() {
        this.conn = database.Database.getConnection();
    }

    public Category getByName(String name) throws SQLException {
        if (name != null) {
            String q = "SELECT * FROM `catrgories` WHERE `catrgories`.`name` = ?";
            java.sql.PreparedStatement stmt = conn.prepareStatement(q);
            stmt.setString(1, name);
            java.sql.ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return Category.fromResultSet(resultSet);
            } else {
                return null;
            }

        } else {
            return null;
        }
    }

    public void Add(Category cat) throws ValidationException, SQLException,EntityAlreadyExistException {
        if (cat.isValid()) {
            if (getByName(cat.getName()) == null) {
                String q = "INSERT INTO `catrgories` (`name`) VALUES (?);";
                java.sql.PreparedStatement stmt = conn.prepareStatement(q);
                stmt.setString(1, cat.getName());
                stmt.execute();
            }else{
                throw new EntityAlreadyExistException("catrgory Name already exists!");
            }

        }
    }
}
