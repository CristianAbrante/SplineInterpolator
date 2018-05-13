/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

/**
 * <h2>SettingsPanel</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		13/05/2018
 * @version 1.0.0
 */
public class SettingsPanel extends JPanel implements ActionListener {
  
  public final static String GENERATE = "Generate";
  public final static String CLEAR = "Clear";
  private final Color BACKGROUND = new Color(196, 210, 227);
  
  private JLabel      pointsTitle = new JLabel("Number of points");
  private JTextField  pointsCounter = new JTextField("0"); 
  private JButton     generatePoints = new JButton(GENERATE);
  private JButton     clearPoints = new JButton(CLEAR);
  private JButton     information = new JButton("Information");
  
  /**
   * Constructor of SettingsPanel
   */
  public SettingsPanel(Controller controller) {
    super();
    setBackground(BACKGROUND);
    add(pointsTitle);
    pointsCounter.setColumns(10);
    add(pointsCounter);
    generatePoints.addActionListener(controller);
    add(generatePoints);
    clearPoints.addActionListener(controller);
    add(clearPoints);
    information.addActionListener(this);
    add(information);
  }

  public int getNumberOfPoints() throws NumberFormatException {
    return Integer.parseInt(pointsCounter.getText());
  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    String info = "Subject: Programación de Aplicaciones Interativas\n"
                + "Course: 2017-2018 \n"
                + "Assignment: 14 \n"
                + "Author: Cristian Abrante Dorta \n"
                + "Description: Spline Interpolator";
    JOptionPane.showMessageDialog(new JFrame("Information"), info);
  } 
}
