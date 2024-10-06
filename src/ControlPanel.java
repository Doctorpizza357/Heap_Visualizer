import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ControlPanel extends JPanel {
    private final JLabel timeTakenLabel;
    private final JLabel numberOfNodesLabel;
    private final JLabel heapHeightLabel;
    private final JLabel heapCapacityLabel;
    private final JLabel rootNodeLabel;
    private final JLabel minValueLabel;
    private final JLabel maxValueLabel;
    private final JLabel avgValueLabel;
    private final JLabel sumValueLabel;
    private final JLabel arrayLabel;
    private int[] heap;
    private long timeTaken;
    private int numberOfNodes;
    private final HeapVisualizer visualizer;

    public ControlPanel(int[] heap, HeapVisualizer visualizer) {
        this.heap = heap;
        this.visualizer = visualizer;
        this.numberOfNodes = heap.length;
        this.timeTaken = 0;

        setLayout(new BorderLayout());

        // Create statistics panel
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(10, 1));
        timeTakenLabel = new JLabel("Time Taken: " + timeTaken + " ms");
        numberOfNodesLabel = new JLabel("Number of Nodes: " + numberOfNodes);
        heapHeightLabel = new JLabel("Heap Height: " + calculateHeapHeight());
        heapCapacityLabel = new JLabel("Heap Capacity: " + heap.length);
        JLabel heapTypeLabel = new JLabel("Heap Type: " + (visualizer.isMaxHeap ? "Max Heap" : "Min Heap"));
        rootNodeLabel = new JLabel("Root Node: " + (heap.length > 0 ? heap[0] : "N/A"));
        minValueLabel = new JLabel("Minimum Value: " + (heap.length > 0 ? Arrays.stream(heap).min().getAsInt() : "N/A"));
        maxValueLabel = new JLabel("Maximum Value: " + (heap.length > 0 ? Arrays.stream(heap).max().getAsInt() : "N/A"));
        avgValueLabel = new JLabel("Average Value: " + (heap.length > 0 ? Arrays.stream(heap).average().getAsDouble() : "N/A"));
        sumValueLabel = new JLabel("Sum of Values: " + (heap.length > 0 ? Arrays.stream(heap).sum() : "N/A"));
        arrayLabel = new JLabel("Array: " + Arrays.toString(heap));
        statsPanel.add(timeTakenLabel);
        statsPanel.add(numberOfNodesLabel);
        statsPanel.add(heapHeightLabel);
        statsPanel.add(heapCapacityLabel);
        statsPanel.add(heapTypeLabel);
        statsPanel.add(rootNodeLabel);
        statsPanel.add(minValueLabel);
        statsPanel.add(maxValueLabel);
        statsPanel.add(avgValueLabel);
        statsPanel.add(sumValueLabel);

        // Create array panel
        JPanel arrayPanel = new JPanel();
        arrayPanel.setLayout(new GridLayout(1, 1));
        arrayPanel.add(arrayLabel);
        // Create buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        JButton addButton = new JButton("Add Node");
        JButton removeButton = new JButton("Remove Node");
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
        add(arrayPanel, BorderLayout.CENTER);
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
                    updateLabels();
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
            updateLabels();
            visualizer.updateHeap(heap);
        } else {
            JOptionPane.showMessageDialog(this, "No nodes to remove.");
        }
    }

    private void updateLabels() {
        numberOfNodesLabel.setText("Number of Nodes: " + numberOfNodes);
        heapHeightLabel.setText("Heap Height: " + calculateHeapHeight());
        heapCapacityLabel.setText("Heap Capacity: " + heap.length);
        rootNodeLabel.setText("Root Node: " + (heap.length > 0 ? heap[0] : "N/A"));
        minValueLabel.setText("Minimum Value: " + (heap.length > 0 ? Arrays.stream(heap).min().getAsInt() : "N/A"));
        maxValueLabel.setText("Maximum Value: " + (heap.length > 0 ? Arrays.stream(heap).max().getAsInt() : "N/A"));
        avgValueLabel.setText("Average Value: " + (heap.length > 0 ? Arrays.stream(heap).average().getAsDouble() : "N/A"));
        sumValueLabel.setText("Sum of Values: " + (heap.length > 0 ? Arrays.stream(heap).sum() : "N/A"));
        arrayLabel.setText("Array: " + Arrays.toString(heap));
    }

    private int calculateHeapHeight() {
        return (int) Math.ceil(Math.log(numberOfNodes + 1) / Math.log(2)) - 1;
    }

    public void updateTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
        timeTakenLabel.setText("Time Taken: " + timeTaken + " ms");
    }
}