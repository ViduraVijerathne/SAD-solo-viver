/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import controllers.UnitController;
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
public class UnitComboBox extends javax.swing.JComboBox<Unit> {

    List<Unit> units;
    UnitController controller;
    private static final Logger logger = LoggerFactory.getLogger(UnitComboBox.class);

    public UnitComboBox() {
        controller = new UnitController();
        loadData();
    }

    public Unit getSelectedUnit() {
        Unit u = (Unit) this.getSelectedItem();

        return u;
    }

    public void loadData() {
        try {
            units = controller.getAll();
            DefaultComboBoxModel model = (DefaultComboBoxModel) this.getModel();
            model.removeAllElements();
            for (Unit c : units) {
                model.addElement(c);
            }
        } catch (java.sql.SQLException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, ex.getMessage());
            logger.info(ex.getMessage());
        }
    }

    public void setSelectUnit(Unit u) {
        System.out.println(u.getName());
        for (Unit unit : units) {
            if(unit.getId() == u.getId()){
                this.setSelectedItem(unit);
                break;
            }

        }
    }

}
