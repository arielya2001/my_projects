package ex2.test_classes;

import ex2.geo.Point_2D;
import ex2.geo.Rect_2D;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

class Rect2DTest {
    // Stream methods that provides values for each test:


    static Stream<Rect_2D> rectProvider() {
        Point_2D p1 = new Point_2D(1, 3);
        Point_2D p2 = new Point_2D(5, 1);
        Rect_2D rect = new Rect_2D(p1, p2);
        return Stream.of(rect);
    }
/////////////////////////////////////// Test functions: ///////////////////////////////////////////////////////////////


    @ParameterizedTest
    @MethodSource("rectProvider")
    void getP1(Rect_2D rect) {
        Point_2D expected = new Point_2D(1, 3);
        assertEquals(expected, rect.getP1(), "getP1() method failed to return the correct point.");
    }

    @ParameterizedTest
    @MethodSource("rectProvider")
    void getP2(Rect_2D rect) {
        Point_2D expected = new Point_2D(5, 1);
        assertEquals(expected, rect.getP2(), "getP2() method failed to return the correct point.");
    }

    @ParameterizedTest
    @MethodSource("rectProvider")
    void getP3(Rect_2D rect) {
        Point_2D expected = new Point_2D(5, 3);
        assertEquals(expected, rect.getP3(), "getP3() method failed to return the correct point.");
    }

    @ParameterizedTest
    @MethodSource("rectProvider")
    void getP4(Rect_2D rect) {
        Point_2D expected = new Point_2D(1, 1);
        assertEquals(expected, rect.getP4(), "getP4() method failed to return the correct point.");
    }

    @ParameterizedTest
    @MethodSource("rectProvider")
    void contains(Rect_2D rect) {
        Point_2D insidePoint = new Point_2D(3, 2);
        Point_2D outSide = new Point_2D(6, 4);

        assertTrue(rect.contains(insidePoint), "contains() method failed " +
                "for a point inside the rectangle.");
        assertFalse(rect.contains(outSide), "contains() method failed " +
                "for a point outside the rectangle.");
    }

    @ParameterizedTest
    @MethodSource("rectProvider")
    void area(Rect_2D rect) {
        assertEquals(8, rect.area(), "area() method failed to calculate the correct area.");
    }

    @ParameterizedTest
    @MethodSource("rectProvider")
    void perimeter(Rect_2D rect) {
        // Calculate the width and height of the rectangle
        double width = Math.abs(rect.getP2().x() - rect.getP1().x());
        double height = Math.abs(rect.getP2().y() - rect.getP1().y());

        // Calculate the expected perimeter based on the width and height
        double expectedPerimeter = 2 * (width + height);
        double actualPerimeter = rect.perimeter();

        assertEquals(expectedPerimeter, actualPerimeter, "perimeter() method failed to calculate the correct perimeter.");
    }

    @ParameterizedTest
    @MethodSource("rectProvider")
    void translate(Rect_2D rect) {
        Point_2D translationVector = new Point_2D(2, 2); // Move the rectangle 2 right and 2 up
        Point_2D expectedP1 = new Point_2D(3, 5);
        Point_2D expectedP2 = new Point_2D(7, 3);

        rect.translate(translationVector);

        assertEquals(expectedP1, rect.getP1(), "translate() method failed for point P1.");
        assertEquals(expectedP2, rect.getP2(), "translate() method failed for point P2.");
    }

    @ParameterizedTest
    @MethodSource("rectProvider")
    void copy(Rect_2D rect) {
        Rect_2D copiedRect = (Rect_2D) rect.copy();

        assertNotSame(rect, copiedRect, "copy() method returned the same object reference.");
        assertEquals(rect.getP1(), copiedRect.getP1(), "Copied rectangle's P1 point does not match.");
        assertEquals(rect.getP2(), copiedRect.getP2(), "Copied rectangle's P2 point does not match.");
        assertEquals(rect.getP3(), copiedRect.getP3(), "Copied rectangle's P3 point does not match.");
        assertEquals(rect.getP4(), copiedRect.getP4(), "Copied rectangle's P4 point does not match.");
    }

    @ParameterizedTest
    @MethodSource("rectProvider")
    void scale(Rect_2D rect) {
        Point_2D center = new Point_2D(3, 2); // Center point for scaling
        double scaleFactor = 2;

        rect.scale(center, scaleFactor);

        assertEquals(new Point_2D(-1, 4), rect.getP1(), "Scaling p1 failed.");
        assertEquals(new Point_2D(7, 0), rect.getP2(), "Scaling p2 failed.");
        assertEquals(new Point_2D(7, 4), rect.getP3(), "Scaling p3 failed.");
        assertEquals(new Point_2D(-1, 0), rect.getP4(), "Scaling p4 failed.");
    }

    @ParameterizedTest
    @MethodSource("rectProvider")
    void rotate(Rect_2D rect) {
        rect = new Rect_2D(new Point_2D(0, 4), new Point_2D(4, 0));
        Point_2D center = new Point_2D(2, 2); // center of the rectangle
        double angleDegrees = 90;
        rect.rotate(center, angleDegrees);

        Point_2D expectedP1 = new Point_2D(-4.440892098500626E-16, 0);
        Point_2D expectedP2 = new Point_2D(0.0,4.0);
        Point_2D expectedP3 = new Point_2D(4.0,4.0);
        Point_2D expectedP4 = new Point_2D(4.0,0.0);

        assertEquals(expectedP1, rect.getP1(), "Point P1 did not rotate correctly.");
        assertEquals(expectedP2, rect.getP3(), "Point P3 did not rotate correctly.");
        assertEquals(expectedP3, rect.getP2(), "Point P2 did not rotate correctly.");
        assertEquals(expectedP4, rect.getP4(), "Point P4 did not rotate correctly.");
    }
    @ParameterizedTest
    @MethodSource("rectProvider")
    void testToString(Rect_2D rect) {
        String result = rect.toString();
        String expected = "1.0,3.0,5.0,3.0,5.0,1.0,1.0,1.0";

        assertEquals(expected, result, "toString() method does not match.");
    }
    }
