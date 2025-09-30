import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoApp {
    public static void main(String[] args) {
        // Always build GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new ToDoApp().createAndShowGUI();
        });
    }

    private DefaultListModel<String> taskListModel;  // holds the tasks
    private JList<String> taskList;                  // shows the tasks
    private JTextField taskField;                    // input field

    private void createAndShowGUI() {
        // Main frame
        JFrame frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Panel at the top for input + add button
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        taskField = new JTextField();
        JButton addButton = new JButton("Add");
        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Task list in the center
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Panel at the bottom for delete button
        JPanel bottomPanel = new JPanel();
        JButton deleteButton = new JButton("Delete Selected");
        bottomPanel.add(deleteButton);

        // Add everything to the frame
        frame.setLayout(new BorderLayout(10, 10));
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Listener for adding tasks
        ActionListener addTaskAction = e -> {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskField.setText(""); // clear after adding
            }
        };
        addButton.addActionListener(addTaskAction);
        taskField.addActionListener(addTaskAction); // pressing Enter works too

        // Listener for deleting tasks
        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            }
        });

        // Show window in center of screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
