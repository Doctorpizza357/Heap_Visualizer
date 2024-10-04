# Heap Visualizer

## Overview
This project is a Java application that visualizes a heap data structure using a graphical user interface (GUI). The application supports both max heap and min heap visualizations.

## Classes and Methods

### `Main.java`
- **Main Method**: The entry point of the application. It reads user input to determine the type of heap (max or min) and initializes the `HeapVisualizer` with the appropriate heap type.

### `HeapVisualizer.java`
- **Constructor**: Initializes the `HeapVisualizer` with the given heap array and sets up the JFrame properties.
- **heapifyMax()**: Converts the array into a max heap.
- **heapifyMin()**: Converts the array into a min heap.

#### Inner Class: `HeapPanel`
- **paintComponent(Graphics g)**: Overrides the `paintComponent` method to draw the heap on the panel.
- **drawHeap(Graphics g, int index, int x, int y, int xOffset)**: Recursively draws the heap nodes and their connections.

## Usage
1. **Run the Application**: Execute the `Main` class.
2. **Input Heap Type**: Enter 'max' for a max heap or 'min' for a min heap.
3. **Visualization**: The heap will be visualized in a new window.

## Example
```java
// Example usage in Main.java
public static void main(String[] args) {
    int[] array = {4, 10, 3, 5, 1};
    HeapVisualizer visualizer = new HeapVisualizer(array);
    visualizer.heapifyMax(); // or visualizer.heapifyMin();
    visualizer.setVisible(true);
}
```

This will display the heap structure in a graphical window.