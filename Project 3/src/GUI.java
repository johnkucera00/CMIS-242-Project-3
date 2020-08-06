/*
* File: GUI.java
* Author: John Kucera
* Date: April 23, 2019
* Purpose: This java program defines a GUI that will hold the functions of
* the Sequence class for user use. Upon exiting, it writes values of n, efficiency
* values for each of them (recursive and iterative), and can be opened with Excel
* for a graph of the data.
*/

// import of necessary java classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class GUI extends JFrame {
    // Components
    public int number;
    private static JButton computeBtn = new JButton("Compute");
    private static JTextField inputTxt = new JTextField("");
    private static JTextField resultTxt = new JTextField();
    private static JTextField effTxt = new JTextField("");
    private static JLabel enterLbl = new JLabel("Enter n:");
    private static JLabel resultLbl = new JLabel("Result:");
    private static JLabel effLbl = new JLabel("Efficiency:");
    private static JRadioButton iterRad = new JRadioButton("Iterative");
    private static JRadioButton recRad = new JRadioButton("Recursive");
    private static JOptionPane frame = new JOptionPane();
    private ButtonGroup methodBtns = new ButtonGroup();
    
    // Clear input box
    public void clearInput() {
        inputTxt.setText("");
    }
    
    // Compute listener
    class ComputeBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int input = Integer.parseInt(inputTxt.getText());
                if (iterRad.isSelected()) {
                resultTxt.setText(""+Sequence.computeIterative(input));
                effTxt.setText(""+Sequence.getEfficiency());
                } 
                else if (recRad.isSelected()) {
                resultTxt.setText(""+Sequence.computeRecursive(input));
                effTxt.setText(""+Sequence.getEfficiency());
                } 
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid integer.");
                clearInput();
            }
        }
    } // end of compute listener
    
    // Window closing listener
    class ClosingListener extends WindowAdapter {
        ClosingListener() {
            addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    FileWriter file = new FileWriter("EfficiencyData");
                    file.append("n,");
                    file.append("Iterative,");
                    file.append("Recursive");
                    file.append(System.getProperty("line.separator"));

                    for (number = 0; number <= 10; number++) {
                        file.append(Integer.toString(number));
                        file.append(",");
                        Sequence.computeIterative(number);
                        file.append(String.valueOf(Integer.toString(Sequence.getEfficiency())));
                        file.append(",");
                        Sequence.computeRecursive(number);
                        file.append(String.valueOf(Integer.toString(Sequence.getEfficiency())));
                        file.append(System.getProperty("line.separator"));
                    }
                    file.flush();
                    file.close();
                }
                catch (IOException ex) {
                    System.out.println("IO Exception" + ex.getMessage());
                }
            }
            });
        } // end of full listener method
    } // End of window listener
    
    // GUI Creation
    public GUI() {
        // Frame Creation
        super("John's Sequence GUI"); // Titling Frame
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.HORIZONTAL;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Buttons
        layout.gridx = 1; // Iterative Radio
        layout.gridy = 0;
        add(iterRad, layout);
        
        layout.gridx = 1; // Recursive Radio
        layout.gridy = 1;
        add(recRad, layout);
        
        layout.gridx = 1; // Compute Button
        layout.gridy = 3;
        add(computeBtn, layout);
        computeBtn.addActionListener(new ComputeBtnListener());
        
        methodBtns.add(iterRad);
        iterRad.setText("Iterative");
        methodBtns.add(recRad);
        recRad.setText("Recursive");
        iterRad.setSelected(true);
        
        // Text Fields
        resultTxt.setEditable(false);
        effTxt.setEditable(false);
        
        layout.gridx = 1; // Input Text Field
        layout.gridy = 2;
        add(inputTxt, layout);
        
        layout.gridx = 1; // Result Text Field
        layout.gridy = 4;
        add(resultTxt, layout);
        resultTxt.setBackground(Color.WHITE);
        
        layout.gridx = 1; // Efficiency Text Field
        layout.gridy = 5;
        add(effTxt, layout);
        effTxt.setBackground(Color.WHITE);
        
        layout.gridx = 0; // Enter n: Label
        layout.gridy = 2;
        add(enterLbl, layout);
        
        layout.gridx = 0; // Result Label
        layout.gridy = 4;
        add(resultLbl, layout);
        
        layout.gridx = 0; // Efficiency Label
        layout.gridy = 5;
        add(effLbl, layout);
        
        // Writing after closing window
        new ClosingListener();
    } // end of GUI method
    
    // Main method
    public static void main(String[] args) {
        GUI gui = new GUI();
    } // end of main method
} // end of class