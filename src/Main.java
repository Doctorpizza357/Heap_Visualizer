import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter heap type (min/max): ");
        String heapType = scanner.nextLine().trim().toLowerCase();

        int[] array = {10, 5, 3, 1, 4, 2, 7, 6, 9, 8, 0, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        if (heapType.equals("max")) {
            HeapVisualizer visualizer = new HeapVisualizer(array);
            visualizer.heapifyMax();
            System.out.println("Max Heap: " + Arrays.toString(array)); // Display the max heap
            visualizer.setVisible(true);
        } else if (heapType.equals("min")) {
            HeapVisualizer visualizer = new HeapVisualizer(array);
            visualizer.heapifyMin();
            System.out.println("Min Heap: " + Arrays.toString(array)); // Display the min heap
            visualizer.setVisible(true);
        } else {
            System.out.println("Invalid heap type entered. Please enter 'max' or 'min'.");
        }
    }
}