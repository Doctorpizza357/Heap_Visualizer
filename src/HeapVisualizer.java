import javax.swing.*;
import java.awt.*;

public class HeapVisualizer extends JFrame {
    private int[] heap;
    private HeapPanel heapPanel;
    boolean isMaxHeap;

    public HeapVisualizer(int[] heap, boolean isMaxHeap) {
        this.heap = heap;
        this.isMaxHeap = isMaxHeap;
        if (isMaxHeap) {
            heapifyMax();
        } else {
            heapifyMin();
        }
        setTitle("Heap Visualizer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        heapPanel = new HeapPanel();
        add(heapPanel);
        ControlPanel controlPanel = new ControlPanel(heap, this);
        add(controlPanel, BorderLayout.SOUTH);
    }

    public void updateHeap(int[] newHeap) {
        this.heap = newHeap;
        if (isMaxHeap) {
            heapifyMax();
        } else {
            heapifyMin();
        }
        heapPanel.repaint();
    }

    private class HeapPanel extends JPanel {
        private static final int NODE_RADIUS = 20;
        private static final int HORIZONTAL_GAP = 50;
        private static final int VERTICAL_GAP = 50;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (heap.length > 0) {
                drawHeap(g, 0, getWidth() / 2, 30, getWidth() / 4);
            }
        }

        private void drawHeap(Graphics g, int index, int x, int y, int xOffset) {
            if (index >= heap.length) {
                return;
            }

            g.setColor(Color.BLUE);
            g.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(heap[index]), x - 5, y + 5);

            int leftIndex = 2 * index + 1;
            int rightIndex = 2 * index + 2;

            if (leftIndex < heap.length) {
                int leftX = x - xOffset;
                int leftY = y + VERTICAL_GAP;
                g.setColor(Color.BLACK);
                g.drawLine(x, y + NODE_RADIUS, leftX, leftY - NODE_RADIUS);
                drawHeap(g, leftIndex, leftX, leftY, xOffset / 2);
            }
            if (rightIndex < heap.length) {
                int rightX = x + xOffset;
                int rightY = y + VERTICAL_GAP;
                g.setColor(Color.BLACK);
                g.drawLine(x, y + NODE_RADIUS, rightX, rightY - NODE_RADIUS);
                drawHeap(g, rightIndex, rightX, rightY, xOffset / 2);
            }
        }
    }

    public void heapifyMax() {
        int n = heap.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(n, i);
        }
    }

    private void maxHeapify(int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && heap[left] > heap[largest]) {
            largest = left;
        }

        if (right < n && heap[right] > heap[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;

            maxHeapify(n, largest);
        }
    }

    public void heapifyMin() {
        int n = heap.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            minHeapify(n, i);
        }
    }

    private void minHeapify(int n, int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && heap[left] < heap[smallest]) {
            smallest = left;
        }

        if (right < n && heap[right] < heap[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            int temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;

            minHeapify(n, smallest);
        }
    }
}