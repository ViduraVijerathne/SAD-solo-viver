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
@Getter
@Setter
public class Category {
    private int id;
    private String name;
    
    
    public boolean isValid()throws ValidationException{
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name cannot be null or empty.");
        }
        return true;
    }
    
    
    public static Category fromResultSet(java.sql.ResultSet resultSet)throws java.sql.SQLException{
        try {
            int id = resultSet.getInt("catrgories.id");
            String name = resultSet.getString("catrgories.name");
            return new Category(id, name);
        } catch (java.sql.SQLException e) {
            throw new java.sql.SQLException("Error while creating Category from ResultSet", e);
        }
    }
    
    @Override
    public String toString(){
        return name;
    }
    
}
