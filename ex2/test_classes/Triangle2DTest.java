package ex2.test_classes;

import ex2.geo.Point_2D;
import ex2.geo.Triangle_2D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Triangle2DTest {

    private static Stream<Arguments> pointProvider() {
        return Stream.of(
                arguments(new Point_2D(0, 0), new Point_2D(1, 0), new Point_2D(0, 1))
        );
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void testGetAllPoints(Point_2D p1, Point_2D p2, Point_2D p3) {
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

        Point_2D[] points = triangle.getAllPoints();

        assertArrayEquals(new Point_2D[]{p1, p2, p3}, points, "Failed to retrieve all points of the triangle.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void testContains(Point_2D p1, Point_2D p2, Point_2D p3) {
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

        Point_2D insidePoint = new Point_2D((p1.x() + p2.x() + p3.x()) / 3, (p1.y() + p2.y() + p3.y()) / 3);
        Point_2D outsidePoint = new Point_2D(10, 10);

        assertTrue(triangle.contains(insidePoint), "The triangle does not contain a point inside it.");
        assertFalse(triangle.contains(outsidePoint), "The triangle contains a point outside it.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void testArea(Point_2D p1, Point_2D p2, Point_2D p3) {
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);
        double expectedArea = Math.abs((p1.x() - p3.x()) *
                (p2.y() - p3.y()) - (p1.y() - p3.y()) * (p2.x() - p3.x()));

        double actualArea = triangle.area();

        assertEquals(expectedArea, actualArea, 0.01, "Failed to calculate the area of the triangle.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void testPerimeter(Point_2D p1, Point_2D p2, Point_2D p3) {
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);
        double side1 = p1.distance(p2);
        double side2 = p2.distance(p3);
        double side3 = p3.distance(p1);
        double expectedPerimeter = side1 + side2 + side3;

        double actualPerimeter = triangle.perimeter();

        assertEquals(expectedPerimeter, actualPerimeter, 0.01, "Failed to calculate the perimeter of the triangle.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void testTranslate(Point_2D p1, Point_2D p2, Point_2D p3) {
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);
        Point_2D translationVector = new Point_2D(1, 1); // Move the triangle 1 right and 1 up
        Point_2D[] expectedPoints = {
                new Point_2D(p1.x() + translationVector.x(), p1.y() + translationVector.y()),
                new Point_2D(p2.x() + translationVector.x(), p2.y() + translationVector.y()),
                new Point_2D(p3.x() + translationVector.x(), p3.y() + translationVector.y())
        };
        triangle.translate(translationVector);

        Point_2D[] translatedPoints = triangle.getAllPoints();
        assertArrayEquals(expectedPoints, translatedPoints, "The translated points do not match the expected positions.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void testCopy(Point_2D p1, Point_2D p2, Point_2D p3) {
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

        Triangle_2D copiedTriangle = (Triangle_2D) triangle.copy();
        Point_2D[] originalPoints = triangle.getAllPoints();
        Point_2D[] copiedPoints = copiedTriangle.getAllPoints();

        assertNotSame(triangle, copiedTriangle, "The copied triangle should not be the same instance as the original.");
        assertArrayEquals(originalPoints, copiedPoints, "The points of the copied triangle do not match the original.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void testScale(Point_2D p1, Point_2D p2, Point_2D p3) {
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

        Point_2D center = new Point_2D((p1.x() + p2.x() + p3.x()) / 3, (p1.y() + p2.y() + p3.y()) / 3); // Center of scaling
        double ratio = 2;

        Point_2D[] expectedPoints = new Point_2D[]{
                new Point_2D(2 * (p1.x() - center.x()) + center.x(), 2 * (p1.y() - center.y()) + center.y()),
                new Point_2D(2 * (p2.x() - center.x()) + center.x(), 2 * (p2.y() - center.y()) + center.y()),
                new Point_2D(2 * (p3.x() - center.x()) + center.x(), 2 * (p3.y() - center.y()) + center.y())
        };

        triangle.scale(center, ratio);

        Point_2D[] scaledPoints = triangle.getAllPoints();
        assertArrayEquals(expectedPoints, scaledPoints, "The scaled points do not match the expected positions.");
    }

    @Test
    void testRotate() {
        // Define the original triangle vertices
        Point_2D vertex1 = new Point_2D(0, 0);
        Point_2D vertex2 = new Point_2D(1, 0);
        Point_2D vertex3 = new Point_2D(0, 1);

        // Create the triangle object
        Triangle_2D triangle = new Triangle_2D(vertex1, vertex2, vertex3);

        // Define the center point for rotation
        Point_2D rotationCenter = new Point_2D(0.5, 0.5);

        // Define the rotation angle
        double angleDegrees = 90;

        // Expected vertices after a 90-degree rotation around the center (0.5, 0.5)
        Point_2D expectedVertex1 = new Point_2D(1.0, 0.0); // Original vertex (0,0) rotated
        Point_2D expectedVertex2 = new Point_2D(1.0, 1.0); // Original vertex (1,0) rotated
        Point_2D expectedVertex3 = new Point_2D(-1.1102230246251565E-16, 0.0); // Original vertex (0,1) rotated

        // Rotate the triangle
        triangle.rotate(rotationCenter, angleDegrees);

        // Assert
        Point_2D[] rotatedVertices = triangle.getAllPoints();
        // Adjust expected values based on your rotation logic and origin
        assertArrayEquals(new Point_2D[]{expectedVertex1, expectedVertex2, expectedVertex3}, rotatedVertices,
                "The vertices after rotation do not match the expected positions.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void testToString(Point_2D p1, Point_2D p2, Point_2D p3) {
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

        // Expected string representation of the triangle
        String expected = p1.x() + "," + p1.y() + ", " + p2.x() + "," + p2.y() + ", " + p3.x() + "," + p3.y();

        String result = triangle.toString();

        assertEquals(expected, result, "The string representation of the triangle does not match the expected format.");
    }
}
