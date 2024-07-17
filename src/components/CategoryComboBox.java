/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import controllers.CategoryController;
import gui.AddNewUnitFrame;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import model.Category;
import model.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import raven.toast.Notifications;

/**
 *
 * @author vidur
 */
public class CategoryComboBox extends javax.swing.JComboBox<Unit> {

    final CategoryController controller;
    private static final Logger logger = LoggerFactory.getLogger(CategoryComboBox.class);
    List<Category> categories;
    
    public CategoryComboBox() {
        controller = new CategoryController();
        loadData();
    }

    public void loadData() {
        try {
            categories = controller.getAll();
            DefaultComboBoxModel model = (DefaultComboBoxModel) this.getModel();
            model.removeAllElements();
            for(Category c : categories){
                model.addElement(c);
            }
        } catch (java.sql.SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, ex.getMessage());
            logger.info(ex.getMessage());
        }
    }

}
