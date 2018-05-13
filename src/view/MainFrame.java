/**
 * 
 */
package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * <h2>MainFrame</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		13/05/2018
 * @version 1.0.0
 */
public class MainFrame {
  
  private JFrame        frame;
  private SettingsPanel settingsPanel;
  private VisualPanel   visualPanel;
  private int width;
  private int height;
  
  // CONSTRUCTORS
  
  /**
   * Constructor of MainFrame
   *
   * @param settingsPanel
   * @param visualPanel
   */
  public MainFrame(String title, SettingsPanel settingsPanel, VisualPanel visualPanel) {
    super();
    setSettingsPanel(settingsPanel);
    setVisualPanel(visualPanel);
    setFrame(new JFrame(title));
    setWidth();
    setHeight();
    initializeFrame();
  }
  
  // PUBLIC METHODS
  
  /**
   * @return the visualPanel
   */
  public VisualPanel getVisualPanel() {
    return visualPanel;
  }
  
  /**
   * @return the settingsPanel
   */
  public SettingsPanel getSettingsPanel() {
    return settingsPanel;
  }
  
  /**
   * @return the width
   */
  public int getWidth() {
    return width;
  }
  
  /**
   * @return the height
   */
  public int getHeight() {
    return height;
  }
  
  public void repaint() {
    getVisualPanel().repaint();
  }
  
  // PRIVATE METHODS
  
  /**
   * @return the frame
   */
  private JFrame getFrame() {
    return frame;
  }
  
  /**
   * @param frame the frame to set
   */
  private void setFrame(JFrame frame) {
    if (frame != null) {
      this.frame = frame;
    } else {
      throw new NullPointerException("frame can't be null");
    }
  }
 
  /**
   * @param settingsPanel the settingsPanel to set
   */
  private void setSettingsPanel(SettingsPanel settingsPanel) {
    if (settingsPanel != null) {
      this.settingsPanel = settingsPanel;      
    } else {
      throw new NullPointerException("settings panel can't be null");
    }
  }
  
  /**
   * @param visualPanel the visualPanel to set
   */
  private void setVisualPanel(VisualPanel visualPanel) {
    if (visualPanel != null) {
      this.visualPanel = visualPanel;      
    } else {
      throw new NullPointerException("visual panel can't be null");
    }
  }
  
  /**
   * @param width the width to set
   */
  private void setWidth() {
    this.width = getVisualPanel().getWidth();
  }

  /**
   * @param height the height to set
   */
  private void setHeight() {
    height = getVisualPanel().getHeight() 
           + getSettingsPanel().getHeight() + 50;
  }
  
  private void initializeFrame() {
    getFrame().setLayout(new BorderLayout());
    getFrame().add(getVisualPanel(), BorderLayout.CENTER);
    getFrame().add(getSettingsPanel(), BorderLayout.SOUTH);
    getFrame().setSize(getWidth(), getHeight());
    getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    getFrame().setResizable(false);
    getFrame().setVisible(true);
  }
}
