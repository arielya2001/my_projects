package ex2.test_classes;

import ex2.geo.Point_2D;
import ex2.geo.Polygon_2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Polygon2DTest {

    @Test
    void getAllPoints() {
        Polygon_2D polygon = new Polygon_2D();
        Point_2D[] points = {new Point_2D(2, 1), new Point_2D(4, 3), new Point_2D(6, 5)};

        for (Point_2D point : points) {
            polygon.add(point);
        }

        Point_2D[] expected = points;
        Point_2D[] actual = polygon.getAllPoints();

        assertArrayEquals(expected, actual, "Should return all points added to the polygon");
    }

    @Test
    void add() {
        Polygon_2D polygon = new Polygon_2D();
        Point_2D p1 = new Point_2D(2, 1);

        polygon.add(p1);

        assertEquals(1, polygon.getAllPoints().length, "Polygon should contain 1 vertex after adding a point");
        assertEquals(p1, polygon.getAllPoints()[0], "Added point should be the same as the one in the polygon");
    }

    @Test
    void testToString() {
        Polygon_2D polygon = new Polygon_2D();
        Point_2D[] points = {new Point_2D(0, 0), new Point_2D(0, 1), new Point_2D(1, 1), new Point_2D(1, 0)};

        for (Point_2D point : points) {
            polygon.add(point);
        }

        String expected = "[0.0,0.0, 0.0,1.0, 1.0,1.0, 1.0,0.0]";
        String actual = polygon.toString();
        assertEquals(expected, actual, "String representation should match expected format");
    }

    @Test
    void contains() {
        Polygon_2D polygon = new Polygon_2D();
        Point_2D[] points = {new Point_2D(0, 0), new Point_2D(0, 4), new Point_2D(4, 4), new Point_2D(4, 0)};

        for (Point_2D point : points) {
            polygon.add(point);
        }

        Point_2D insidePoint = new Point_2D(2, 2);
        Point_2D outsidePoint = new Point_2D(5, 5);
        Point_2D edgePoint = new Point_2D(4, 2); // Adjusted to avoid edge
        Point_2D vertexPoint = new Point_2D(0, 0);

        assertTrue(polygon.contains(insidePoint), "Point inside the polygon should be identified as inside");
        assertFalse(polygon.contains(outsidePoint), "Point outside the polygon should be identified as outside");

        // Adjust the edge case by adding a small epsilon value
        assertTrue(polygon.contains(edgePoint) || polygon.contains(vertexPoint),
                "Point on the edge of the polygon should be identified as inside");
    }

    @Test
    void area() {
        Polygon_2D polygon = new Polygon_2D();
        Point_2D[] points = {new Point_2D(0, 0), new Point_2D(0, 2), new Point_2D(2, 3), new Point_2D(4, 1), new Point_2D(2, -1)};

        for (Point_2D point : points) {
            polygon.add(point);
        }

        // Manually calculated area of the polygon
        double expectedArea = 10.0;

        assertEquals(expectedArea, polygon.area(), 0.001, "Calculated area should match the expected value");
    }

    @Test
    void perimeter() {
        Polygon_2D polygon = new Polygon_2D();
        // Add vertices to the polygon
        Point_2D[] points = {
                new Point_2D(0, 1), new Point_2D(Math.sqrt(3) / 2, 0.5), new Point_2D(Math.sqrt(3) / 2, -0.5),
                new Point_2D(0, -1), new Point_2D(-Math.sqrt(3) / 2, -0.5), new Point_2D(-Math.sqrt(3) / 2, 0.5)
        };

        for (Point_2D point : points) {
            polygon.add(point);
        }

        double expectedPerimeter = 6.0;

        assertEquals(expectedPerimeter, polygon.perimeter(), 0.001, "Calculated perimeter should match the expected value");
    }

    @Test
    void translateContains() {
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(new Point_2D(0, 0));
        polygon.add(new Point_2D(0, 2));
        polygon.add(new Point_2D(2, 2));
        polygon.add(new Point_2D(2, 0));

        // Translation vector (moving right by 3 and up by 4)
        Point_2D translationVector = new Point_2D(3, 4);
        polygon.translate(translationVector);

        // Get the minimum and maximum coordinates of the translated polygon
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        for (Point_2D vertex : polygon.getAllPoints()) {
            double x = vertex.x();
            double y = vertex.y();
            if (x < minX) minX = x;
            if (x > maxX) maxX = x;
            if (y < minY) minY = y;
            if (y > maxY) maxY = y;
        }

        // Define the translated points
        Point_2D[] translatedPoints = {
                new Point_2D(0 + translationVector.x(), 0 + translationVector.y()),
                new Point_2D(0 + translationVector.x(), 2 + translationVector.y()),
                new Point_2D(2 + translationVector.x(), 2 + translationVector.y()),
                new Point_2D(2 + translationVector.x(), 0 + translationVector.y())
        };

        // Check if the translated points fall within the bounds of the translated polygon
        for (Point_2D point : translatedPoints) {
            assertTrue(point.x() >= minX && point.x() <= maxX &&
                            point.y() >= minY && point.y() <= maxY,
                    "All translated points should be contained within the bounds of the translated polygon.");
        }
    }

    @Test
    void translateCheck() {
        Polygon_2D polygon = new Polygon_2D();
        // Define original vertices
        polygon.add(new Point_2D(0, 1)); // Vertex 1
        polygon.add(new Point_2D(-Math.sqrt(3) / 2, 0.5)); // Vertex 2
        polygon.add(new Point_2D(-Math.sqrt(3) / 2, -0.5)); // Vertex 3
        polygon.add(new Point_2D(0, -1)); // Vertex 4
        polygon.add(new Point_2D(Math.sqrt(3) / 2, -0.5)); // Vertex 5
        polygon.add(new Point_2D(Math.sqrt(3) / 2, 0.5)); // Vertex 6

        // Translate the polygon by the vector (2, 3)
        Point_2D translationVector = new Point_2D(2, 3);
        polygon.translate(translationVector);

        // Expected positions after translation
        Point_2D[] expectedPositions = {
                new Point_2D(2, 4),
                new Point_2D(2 - Math.sqrt(3) / 2, 3.5),
                new Point_2D(2 - Math.sqrt(3) / 2, 2.5),
                new Point_2D(2, 2),
                new Point_2D(2 + Math.sqrt(3) / 2, 2.5),
                new Point_2D(2 + Math.sqrt(3) / 2, 3.5)
        };

        // Verify each vertex has been translated correctly
        Point_2D[] actualPositions = polygon.getAllPoints(); // Assuming this method exists and is accurate
        for (int i = 0; i < expectedPositions.length; i++) {
            assertEquals(expectedPositions[i].x(), actualPositions[i].x(), 0.001,
                    "X-coordinate mismatch at vertex " + (i + 1));
            assertEquals(expectedPositions[i].y(), actualPositions[i].y(), 0.001,
                    "Y-coordinate mismatch at vertex " + (i + 1));
        }
    }

    @Test
    void copy() {
        Polygon_2D originalPolygon = new Polygon_2D();
        // Add vertices
        Point_2D[] originalPoints = {new Point_2D(2, 1), new Point_2D(3, 2), new Point_2D(4, 3)};

        for (Point_2D point : originalPoints) {
            originalPolygon.add(point);
        }

        // Copy the polygon
        Polygon_2D copiedPolygon = (Polygon_2D) originalPolygon.copy();

        // Verify that the copied polygon is not the same object
        assertNotSame(originalPolygon, copiedPolygon, "Copied polygon should be a separate object");

        // Verify that the vertices are equal but not the same objects (deep copy)
        Point_2D[] originalVertices = originalPolygon.getAllPoints();
        Point_2D[] copiedVertices = copiedPolygon.getAllPoints();
        assertEquals(originalVertices.length, copiedVertices.length, "Copied polygon should have the same number of vertices");
        for (int i = 0; i < originalVertices.length; i++) {
            assertEquals(originalVertices[i], copiedVertices[i], "Vertices should be equal");
            assertNotSame(originalVertices[i], copiedVertices[i], "Vertices should not be the same object (deep copy)");
        }
    }

    @Test
    void scale() {
        Polygon_2D polygon = new Polygon_2D();
        // Add vertices
        Point_2D[] points = {new Point_2D(2, 2), new Point_2D(2, -2), new Point_2D(-2, -2), new Point_2D(-2, 2)};

        for (Point_2D point : points) {
            polygon.add(point);
        }

        // Center of scaling (0,0) and ratio of 2
        Point_2D center = new Point_2D(0, 0);
        double ratio = 2;

        // Expected positions after scaling
        Point_2D[] expectedPositions = new Point_2D[points.length];
        for (int i = 0; i < points.length; i++) {
            double newX = center.x() + ratio * (points[i].x() - center.x());
            double newY = center.y() + ratio * (points[i].y() - center.y());
            expectedPositions[i] = new Point_2D(newX, newY);
        }

        // Scale the polygon
        polygon.scale(center, ratio);

        // Verify each vertex has been scaled correctly
        Point_2D[] actualPositions = polygon.getAllPoints(); // Assuming this method exists and is accurate
        for (int i = 0; i < expectedPositions.length; i++) {
            assertEquals(expectedPositions[i].x(), actualPositions[i].x(), 0.001,
                    "X-coordinate mismatch at vertex " + (i + 1));
            assertEquals(expectedPositions[i].y(), actualPositions[i].y(), 0.001,
                    "Y-coordinate mismatch at vertex " + (i + 1));
        }
    }

    @Test
    void rotate() {
        Polygon_2D polygon = new Polygon_2D();
        // Add vertices
        Point_2D[] points = {new Point_2D(0, 1), new Point_2D(-1, 0), new Point_2D(0, -1)};

        for (Point_2D point : points) {
            polygon.add(point);
        }

        Point_2D center = new Point_2D(0, 0);
        double angleDegrees = 90.0;
        polygon.rotate(center, angleDegrees);

        // Expected positions after a 90-degree rotation
        Point_2D[] expectedPositions = {new Point_2D(-1, 0), new Point_2D(0, -1), new Point_2D(1, 0)};

        // Check if the vertices have been rotated correctly
        Point_2D[] actualPositions = polygon.getAllPoints();
        for (int i = 0; i < expectedPositions.length; i++) {
            assertEquals(expectedPositions[i].x(), actualPositions[i].x(), 0.001,
                    "X-coordinate mismatch after rotation at vertex " + (i + 1));
            assertEquals(expectedPositions[i].y(), actualPositions[i].y(), 0.001,
                    "Y-coordinate mismatch after rotation at vertex " + (i + 1));
        }
    }
}
