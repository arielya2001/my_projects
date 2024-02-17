package ex2.test_classes;

import ex2.geo.Point_2D;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.ParameterizedTest;

class Point2DTest {
    // Stream methods that provides values for each test:

    static Stream<Point_2D> pointProvider() {
        return Stream.of(
                new Point_2D(3.5, 0),
                new Point_2D(0, -2.5),
                new Point_2D(1.0, 2.0),
                new Point_2D(1, 1)
        );
    }
/////////////////////////////////////// Test functions: ///////////////////////////////////////////////////////////////

    @ParameterizedTest
    @MethodSource("pointProvider")
    void x(Point_2D point) {
        double expectedX = point.x();
        assertEquals(expectedX, point.x(), 0.001, "The X-coordinate should match the expected value.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void y(Point_2D point) {
        double expectedY = point.y();
        assertEquals(expectedY, point.y(), 0.001, "The Y-coordinate should match the expected value.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void ix(Point_2D point) {
        int expectedIx = (int) point.x();
        assertEquals(expectedIx, point.ix(), "The integer part of the X-coordinate should match the expected value.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void iy(Point_2D point) {
        int expectedIy = (int) point.y();
        assertEquals(expectedIy, point.iy(), "The integer part of the Y-coordinate should match the expected value.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void set_x(Point_2D point) {
        double newX = 3.0;
        point.set_x(newX);
        assertEquals(newX, point.x(), 0.001, "The X-coordinate should be updated to the new value.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void set_y(Point_2D point) {
        double newY = 4.0;
        point.set_y(newY);
        assertEquals(newY, point.y(), 0.001, "The Y-coordinate should be updated to the new value.");
    }

    @Test
    void add() {
        Point_2D point1 = new Point_2D(1.0, 2.0);
        Point_2D point2 = new Point_2D(3.0, 4.0);
        Point_2D result = point1.add(point2);
        Point_2D expected = new Point_2D(4.0, 6.0);
        assertEquals(expected.x(), result.x(), 0.001, "The X-coordinate of the result should match the expected value.");
        assertEquals(expected.y(), result.y(), 0.001, "The Y-coordinate of the result should match the expected value.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void ToString(Point_2D point) {
        String expected = point.x() + "," + point.y();
        assertEquals(expected, point.toString(), "The string representation should match the expected value.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void distanceToOrigin(Point_2D point) {
        double expectedDistance = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        assertEquals(expectedDistance, point.distance(), 0.001, "The distance to the origin should match the expected value.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void distance(Point_2D point) {
        Point_2D origin = new Point_2D(0, 0);
        double expectedDistance = Math.sqrt((point.x() - origin.x()) * (point.x() - origin.x()) +
                (point.y() - origin.y()) * (point.y() - origin.y()));
        assertEquals(expectedDistance, point.distance(origin), 0.001, "The distance between points should match the expected value.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void testEquals(Point_2D point) {
        Point_2D samePoint = new Point_2D(point.x(), point.y());
        Point_2D differentPoint = new Point_2D(point.x() + 1, point.y() + 1);

        assertTrue(point.equals(samePoint), "Points with the same coordinates should be considered equal.");
        assertFalse(point.equals(differentPoint), "Points with different coordinates should not be considered equal.");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void close2equals(Point_2D point) {
        Point_2D closePoint = new Point_2D(point.x() + 0.01, point.y() + 0.01);
        Point_2D farPoint = new Point_2D(point.x() + 1, point.y() + 1);

        assertTrue(point.close2equals(closePoint, 0.05), "Points within the epsilon range should be considered equal.");
        assertFalse(point.close2equals(farPoint, 0.05), "Points outside the epsilon range should not be considered equal.");
    }

    @Test
    void vector() {
        Point_2D point1 = new Point_2D(3, 4);
        Point_2D target = new Point_2D(7, 9);
        Point_2D expectedVector = new Point_2D(4, 5);

        Point_2D actualVector = point1.vector(target);
        assertEquals(expectedVector.x(), actualVector.x(), 0.001, "The X-component of the vector should match the expected value.");
        assertEquals(expectedVector.y(), actualVector.y(), 0.001, "The Y-component of the vector should match the expected value.");
    }

    @Test
    void move() {
        Point_2D point = new Point_2D(5, 5);
        Point_2D vec = new Point_2D(3, -2);
        point.move(vec);
        assertEquals(new Point_2D(8, 3).x(), point.x(), 0.001, "After moving, the X-coordinate should be updated correctly.");
        assertEquals(new Point_2D(8, 3).y(), point.y(), 0.001, "After moving, the Y-coordinate should be updated correctly.");
    }

    @Test
    void scale() {
        Point_2D point = new Point_2D(4, 4);
        Point_2D cen = new Point_2D(2, 2);
        double ratio = 0.5;
        point.scale(cen, ratio);
        assertEquals(new Point_2D(3, 3), point, "After scaling, the coordinates of the point should be updated correctly.");
    }

    @Test
    void rotate() {
        Point_2D point = new Point_2D(1, 2);
        Point_2D cen = new Point_2D(0, 0);
        double angleDegrees = 180;
        point.rotate(cen, angleDegrees);
        // After a 180-degree rotation, the point is expected to be (-1, -2)
        assertEquals(-1, point.x(), 0.001, "After a 180-degree rotation, the X-coordinate should be -1.");
        assertEquals(-2, point.y(), 0.001, "After a 180-degree rotation, the Y-coordinate should be -2.");
    }
}
