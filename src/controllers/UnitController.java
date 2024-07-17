/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import exceptions.EntityAlreadyExistException;
import exceptions.ValidationException;
import java.sql.SQLException;
import model.Unit;

/**
 *
 * @author vidur
 */
public class UnitController {

    final java.sql.Connection conn;

    public UnitController() {
        this.conn = database.Database.getConnection();
    }

    public Unit getByName(String name) throws SQLException {
        if (name != null) {
            String q = "SELECT * FROM `units` WHERE `units`.`name` = ?";
            java.sql.PreparedStatement stmt = conn.prepareStatement(q);
            stmt.setString(1, name);
            java.sql.ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return Unit.fromResultSet(resultSet);
            } else {
                return null;
            }

        } else {
            return null;
        }
    }

    public void Add(Unit unit) throws ValidationException, SQLException,EntityAlreadyExistException {
        if (unit.isValid()) {
            if (getByName(unit.getName()) == null) {
                String q = "INSERT INTO `units` (`name`) VALUES (?);";
                java.sql.PreparedStatement stmt = conn.prepareStatement(q);
                stmt.setString(1, unit.getName());
                stmt.execute();
            }else{
                throw new EntityAlreadyExistException("Unit Name already exists!");
            }

        }
    }
}
