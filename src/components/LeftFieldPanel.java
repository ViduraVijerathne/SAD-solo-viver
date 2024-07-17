/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author vidur
 */
public class LeftFieldPanel  extends JPanel {

    public LeftFieldPanel() {
     setLayout(new MigLayout("fillx,wrap,insets 30 40 50 40, width 320", "[fill]", "[]10[][]10[][]10[][]10[]10[]10[]10[]10"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;");
    }
    
    
}
