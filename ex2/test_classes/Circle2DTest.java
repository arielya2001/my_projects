package ex2.test_classes;

import ex2.geo.Circle_2D;
import ex2.geo.Point_2D;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.ParameterizedTest;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


class Circle2DTest {

    // Stream methods that provides values for each test:

    static Stream<Double> radiusProvider() {
        List<Double> values = new ArrayList<>();
        for (double i = 0; i < 100; i++) {
            values.add(i);
        }
        return values.stream();
    }


    static Stream<Point_2D> centerProvider() {
        List<Point_2D> points = new ArrayList<>();
        for (double i = 0; i <= 100; i++) {
            points.add(new Point_2D(i, i + 1));
        }
        return points.stream();
    }

    static Stream<Arguments> toStringProvider() {
        List<Arguments> argumentsList = new ArrayList<>();
        for (double i = 0; i <= 100; i++) {
            Point_2D point = new Point_2D(i, i + 1);
            double radius = i + 2;
            String expected = String.format("%.1f,%.1f, %.1f", point.x(), point.y(), radius); //The %.1f format ensures that each number is rounded to one decimal place
            argumentsList.add(arguments(point, radius, expected));
        }
        return argumentsList.stream();
    }

    static Stream<Arguments> containsProvider() {
        List<Arguments> argumentsList = new ArrayList<>();

        Point_2D center = new Point_2D(1, 1);
        double radius = 5;

        for (double x = -5; x <= 5; x += 1) {
            for (double y = -5; y <= 5; y += 1) {
                Point_2D testPoint = new Point_2D(x, y);
                boolean expectedResult = center.distance(testPoint) <= radius;
                argumentsList.add(arguments(center, radius, testPoint, expectedResult));
            }
        }

        return argumentsList.stream();
    }

    static Stream<Double> radiusProviderForArea() {
        List<Double> radiusValues = new ArrayList<>();

        // Generating 100 radius values
        for (double radius = 1.0; radius <= 100.0; radius += 1.0) {
            radiusValues.add(radius);
        }

        return radiusValues.stream();
    }

    static Stream<Double> radiusProviderForPerimeter() {
        List<Double> radiusValues = new ArrayList<>();

        // Generating 100 radius values
        for (double radius = 1.0; radius <= 100.0; radius += 1.0) {
            radiusValues.add(radius);
        }

        return radiusValues.stream();
    }

    static Stream<Arguments> translateProvider() {
        List<Arguments> argumentsList = new ArrayList<>();

        // Generating 100 sets of arguments
        for (int i = 0; i < 100; i++) {
            Point_2D center = new Point_2D(i, i);
            Point_2D vector = new Point_2D(i + 1, i + 2);
            Point_2D expected = new Point_2D(center.x() + vector.x(), center.y() + vector.y());
            argumentsList.add(arguments(center, vector, expected));
        }

        return argumentsList.stream();
    }
    static Stream<Arguments> rotateProvider() {
        List<Arguments> argumentsList = new ArrayList<>();

        Point_2D center = new Point_2D(1, 1);
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);
        Point_2D rotationCenter = new Point_2D(2, 2);
        double angleDegrees = 90;
        Point_2D expectedCenterAfterRotation = new Point_2D(3.0, 1.0);

        argumentsList.add(arguments(circle, rotationCenter, angleDegrees, expectedCenterAfterRotation));

        return argumentsList.stream();
    }



    static Stream<Arguments> scaleProvider() {
        List<Arguments> argumentsList = new ArrayList<>();

        // Generating 100 sets of arguments
        for (int i = 0; i < 100; i++) {
            Point_2D center = new Point_2D(i, i);
            double initialRadius = 5.0;
            Point_2D scaleCenter = new Point_2D(i + 1, i + 1);
            double ratio = i / 10.0;
            double expectedRadius = initialRadius * ratio;
            argumentsList.add(arguments(center, initialRadius, scaleCenter, ratio, expectedRadius));
        }

        return argumentsList.stream();
    }
        static Stream<Arguments> copyProvider() {
            return Stream.of(
                    Arguments.of(new Point_2D(0, 0), 5.0),  // Center at (0,0), radius 5
                    Arguments.of(new Point_2D(-3, 2), 8.0), // Center at (-3,2), radius 8
                    Arguments.of(new Point_2D(4, -1), 3.0) // Center at (4,-1), radius 3
            );
        }

/////////////////////////////////////// Test functions: ///////////////////////////////////////////////////////////////


    @ParameterizedTest
    @MethodSource("radiusProvider")
    void getRadius(double radius) {
        Point_2D center = new Point_2D(1, 1);
        Circle_2D circle = new Circle_2D(center, radius);
        assertEquals(radius, circle.getRadius(), "The radius should match the input value.");
    }

    @ParameterizedTest
    @MethodSource("centerProvider")
    void getCenter(Point_2D center) {
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);
        assertEquals(center, circle.getCenter(), "The center should match!!");
    }

    @ParameterizedTest
    @MethodSource("toStringProvider")
    void ToString(Point_2D center, double radius, String expected) {
        Circle_2D circle = new Circle_2D(center, radius);
        String actual = circle.toString();
        assertEquals(expected, actual, "The info should match!!");
    }

    @ParameterizedTest
    @MethodSource("containsProvider")
    void contains(Point_2D center, double radius, Point_2D testPoint, boolean expected) {
        Circle_2D circle = new Circle_2D(center, radius);
        assertEquals(expected, circle.contains(testPoint),
                expected ? "The point should be inside the circle." : "The point should be outside the circle.");
    }

    @ParameterizedTest
    @MethodSource("radiusProviderForArea")
    void area(double radius) {
        Circle_2D circle = new Circle_2D(new Point_2D(1, 1), radius);
        double expected = Math.PI * Math.pow(radius, 2);
        assertEquals(expected, circle.area(), "The area should match.");
    }

    @ParameterizedTest
    @MethodSource("radiusProviderForPerimeter")
    void perimeter(double radius) {
        Circle_2D circle = new Circle_2D(new Point_2D(1, 1), radius);
        double expected = 2 * Math.PI * radius;
        assertEquals(expected, circle.perimeter(), "The perimeter should match.");
    }

    @ParameterizedTest
    @MethodSource("translateProvider")
    void translate(Point_2D center, Point_2D vector, Point_2D expected) {
        Circle_2D circle = new Circle_2D(center, 5);
        circle.translate(vector);
        assertEquals(expected, circle.getCenter(), "After translation, the center should match expected.");
    }

    @ParameterizedTest
    @MethodSource("copyProvider")
    void copy() {
        Point_2D Center = new Point_2D(1, 1);
        double radius = 5;
        Circle_2D circle = new Circle_2D(Center, radius);
        Circle_2D copiedCircle = (Circle_2D) circle.copy();

        assertEquals(circle.getCenter().x(), copiedCircle.getCenter().x(), "Copied circle " +
                "X coordinate mismatch");
        assertEquals(circle.getCenter().y(), copiedCircle.getCenter().y(), "Copied circle " +
                "Y coordinate mismatch");
        assertEquals(circle.getRadius(), copiedCircle.getRadius(), "Copied circle radius mismatch");
        assertNotSame(circle, copiedCircle, "The copied circle should be a different object");
    }

    @ParameterizedTest
    @MethodSource("scaleProvider")
    void scale(Point_2D center, double initialRadius, Point_2D scaleCenter, double ratio, double expectedRadius) {
        Circle_2D circle = new Circle_2D(center, initialRadius);
        circle.scale(scaleCenter, ratio);
        assertEquals(expectedRadius, circle.getRadius(), "After scaling, the radius should match expected.");
    }

    @ParameterizedTest
    @MethodSource("rotateProvider")
    void rotate(Circle_2D circle, Point_2D rotationCenter, double angleDegrees, Point_2D expectedCenterAfterRotation) {
        circle.rotate(rotationCenter, angleDegrees);

        assertEquals(expectedCenterAfterRotation.x(), circle.getCenter().x(), "Circle center X coordinate " +
                "after rotation is incorrect");
        assertEquals(expectedCenterAfterRotation.y(), circle.getCenter().y(), "Circle center Y coordinate " +
                "after rotation is incorrect");
    }

}