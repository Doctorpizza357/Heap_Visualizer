import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private JLabel timeTakenLabel;
    private JLabel numberOfNodesLabel;
    private JLabel heapHeightLabel;
    private JLabel heapCapacityLabel;
    private JLabel heapTypeLabel;
    private JButton addButton;
    private JButton removeButton;
    private int[] heap;
    private long timeTaken;
    private int numberOfNodes;
    private HeapVisualizer visualizer;

    public ControlPanel(int[] heap, HeapVisualizer visualizer) {
        this.heap = heap;
        this.visualizer = visualizer;
        this.numberOfNodes = heap.length;
        this.timeTaken = 0;

        setLayout(new BorderLayout());

        // Create statistics panel
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(5, 1));
        timeTakenLabel = new JLabel("Time Taken: " + timeTaken + " ms");
        numberOfNodesLabel = new JLabel("Number of Nodes: " + numberOfNodes);
        heapHeightLabel = new JLabel("Heap Height: " + calculateHeapHeight());
        heapCapacityLabel = new JLabel("Heap Capacity: " + heap.length);
        heapTypeLabel = new JLabel("Heap Type: " + (visualizer.isMaxHeap ? "Max Heap" : "Min Heap"));
        statsPanel.add(timeTakenLabel);
        statsPanel.add(numberOfNodesLabel);
        statsPanel.add(heapHeightLabel);
        statsPanel.add(heapCapacityLabel);
        statsPanel.add(heapTypeLabel);

        // Create buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        addButton = new JButton("Add Node");
        removeButton = new JButton("Remove Node");
        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);

        // Add action listeners to buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNode();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeNode();
            }
        });

        // Add panels to the main panel
        add(statsPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void addNode() {
        SwingUtilities.invokeLater(() -> {
            String input = JOptionPane.showInputDialog(this, "Enter an integer to add:");
            if (input != null) {
                try {
                    int newNode = Integer.parseInt(input);
                    int[] newHeap = new int[heap.length + 1];
                    System.arraycopy(heap, 0, newHeap, 0, heap.length);
                    newHeap[heap.length] = newNode;
                    heap = newHeap;
                    numberOfNodes++;
                    numberOfNodesLabel.setText("Number of Nodes: " + numberOfNodes);
                    heapHeightLabel.setText("Heap Height: " + calculateHeapHeight());
                    heapCapacityLabel.setText("Heap Capacity: " + heap.length);
                    visualizer.updateHeap(heap);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter an integer.");
                }
            }
        });
    }

    private void removeNode() {
        if (numberOfNodes > 0) {
            int[] newHeap = new int[heap.length - 1];
            System.arraycopy(heap, 0, newHeap, 0, heap.length - 1);
            heap = newHeap;
            numberOfNodes--;
            numberOfNodesLabel.setText("Number of Nodes: " + numberOfNodes);
            heapHeightLabel.setText("Heap Height: " + calculateHeapHeight());
            heapCapacityLabel.setText("Heap Capacity: " + heap.length);
            visualizer.updateHeap(heap);
        } else {
            JOptionPane.showMessageDialog(this, "No nodes to remove.");
        }
    }

    private int calculateHeapHeight() {
        return (int) Math.ceil(Math.log(numberOfNodes + 1) / Math.log(2)) - 1;
    }

    public void updateTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
        timeTakenLabel.setText("Time Taken: " + timeTaken + " ms");
    }
}