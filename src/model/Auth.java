/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import database.Database;
import exceptions.AuthenticationException;
import exceptions.ValidationException;
import java.sql.SQLException;

/**
 *
 * @author vidur
 */
public class Auth {

    private int id;
    private String usename;
    private String password;
    private AuthType authType;
    private boolean isAuthenticated = false;

    public Auth(String username, String password) {
        this.usename = username;
        this.password = password;
    }

    public String getUsername() {
        return this.usename;
    }
    
    public int getID(){
        return id;
    }

    public boolean isAdmin() {
        if (!this.isAuthenticated) {
            return false;
        }
        if (authType == null) {
            return false;
        }

        return authType == AuthType.ADMIN;
    }

    public boolean isCashier() {
        if (!this.isAuthenticated) {
            return false;
        }
        if (authType == null) {
            return false;
        }

        return authType == AuthType.CASHIER;
    }

    public boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    public boolean isValid() throws ValidationException {
        if (usename.length() > 40) {
            throw new ValidationException("invalid username");
        }
        if (usename.length() < 2) {
            throw new ValidationException("invalid username");
        }
        if (password.length() > 40) {
            throw new ValidationException("invalid password");
        }
        if (password.length() < 2) {
            throw new ValidationException("invalid password");
        }
        return true;
    }

    public boolean authenticate() throws AuthenticationException, ValidationException, SQLException {
        if (isValid()) {
            java.sql.Connection conn = Database.getConnection();
            String query = "SELECT * FROM auths WHERE auths.username = ? AND auths.password = ? ";

            java.sql.PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.usename);
            stmt.setString(2, this.password);
            java.sql.ResultSet result = stmt.executeQuery();

            if (result.next()) {
                this.authType = AuthType.valueOf(result.getString("type"));
                this.id = result.getInt("id");
                return true;
            } else {
                throw new AuthenticationException("Invalid username or password");
            }

        } else {
            return false;
        }
    }

}
