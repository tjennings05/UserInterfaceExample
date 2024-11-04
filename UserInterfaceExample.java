import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class UserInterfaceExample {
    private JFrame frame;           // Main window
    private JTextArea textBox;      // Text area to display content

    public UserInterfaceExample() {
        // Create the main window
        frame = new JFrame("User Interface Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit when closed
        frame.setSize(400, 300);   // Set dimensions
        frame.setLayout(new BorderLayout());  // Use BorderLayout as layout manager

        // Create menu bar and menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File"); // Create a "File" menu

        // Create menu items
        JMenuItem showDateTimeItem = new JMenuItem("Show Date and Time");
        JMenuItem saveToFileItem = new JMenuItem("Save to File");
        JMenuItem changeBackgroundColorItem = new JMenuItem("Change Background Color");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Attach action listeners to menu items
        showDateTimeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDateTime();  // Show date and time in text box
            }
        });

        saveToFileItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveToFile();  // Save text box content to "log.txt" file
            }
        });

        changeBackgroundColorItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeBackgroundColor();  // Change frame's background color
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the program
            }
        });

        // Add menu items to the "File" menu
        fileMenu.add(showDateTimeItem);
        fileMenu.add(saveToFileItem);
        fileMenu.add(changeBackgroundColorItem);
        fileMenu.add(exitItem);

        // Add the "File" menu to the menu bar
        menuBar.add(fileMenu);

        // Set the menu bar in the frame
        frame.setJMenuBar(menuBar);

        // Create a text area and add it to the center of the frame
        textBox = new JTextArea();
        frame.add(new JScrollPane(textBox), BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to display current date and time in the text box
    private void showDateTime() {
        String dateTime = new java.util.Date().toString();
        textBox.append(dateTime + "\n");
    }

    // Method to save text box content to "log.txt" file
    private void saveToFile() {
        String content = textBox.getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to change the background color of the frame
    private void changeBackgroundColor() {
        float hue = (float) Math.random();  // Generate a random hue value
        Color color = Color.getHSBColor(hue, 1.0f, 1.0f);  // Create a color with the generated hue
        frame.getContentPane().setBackground(color);  // Set the frame's background color
    }

    // Main method to start the program
    public static void main(String[] args) {
        // Schedule the creation of the UI in the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserInterfaceExample(); // Create an instance of the UI
            }
        });
    }
}