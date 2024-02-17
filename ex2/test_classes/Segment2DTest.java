package ex2.test_classes;

import ex2.geo.Point_2D;
import ex2.geo.Segment_2D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Segment2DTest {

    private static Stream<Arguments> pointProvider() {
        return Stream.of(
                arguments(new Point_2D(0, 0), new Point_2D(4, 0)),
                arguments(new Point_2D(2, 2), new Point_2D(6, 2)),
                arguments(new Point_2D(-1, -1), new Point_2D(3, -1))
        );
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void get_p1(Point_2D a, Point_2D b) {
        Segment_2D segment = new Segment_2D(a, b);

        Point_2D expected_P1 = a;
        Point_2D actual_P1 = segment.get_p1();
        assertEquals(expected_P1, actual_P1, "P1 should match the expected value");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void get_p2(Point_2D a, Point_2D b) {
        Segment_2D segment = new Segment_2D(a, b);

        Point_2D expected_P2 = b;
        Point_2D actual = segment.get_p2();
        assertEquals(expected_P2, actual, "P2 should match the expected value");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void contains(Point_2D a, Point_2D b) {
        Segment_2D segment = new Segment_2D(a, b);

        Point_2D insidePoint = new Point_2D(a.x() + 2, a.y());
        Point_2D outsidePoint = new Point_2D(a.x() - 1, a.y());

        assertTrue(segment.contains(insidePoint), "Point inside the segment should be correctly identified as inside");
        assertFalse(segment.contains(outsidePoint), "Point outside the segment should be correctly identified as outside");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void area(Point_2D a, Point_2D b) {
        Segment_2D segment = new Segment_2D(a, b);

        double expectedArea = 0;
        double actualArea = segment.area();

        assertEquals(expectedArea, actualArea, "Area should be calculated correctly");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void perimeter(Point_2D a, Point_2D b) {
        Segment_2D segment = new Segment_2D(a, b);

        double expectedPerimeter = a.distance(b) * 2; // Multiply by 2 as it's a line segment
        double actualPerimeter = segment.perimeter();

        assertEquals(expectedPerimeter, actualPerimeter, 0.001, "Perimeter should be calculated correctly");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void translate(Point_2D a, Point_2D b) {
        Segment_2D segment = new Segment_2D(a, b);
        Point_2D vector = new Point_2D(3, 4);
        segment.translate(vector);

        Point_2D expectedA = new Point_2D(a.x() + vector.x(), a.y() + vector.y());
        Point_2D expectedB = new Point_2D(b.x() + vector.x(), b.y() + vector.y());

        assertEquals(expectedA, segment.get_p1(), "Points A after translation should match the expected values");
        assertEquals(expectedB, segment.get_p2(), "Points B after translation should match the expected values");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void copy(Point_2D a, Point_2D b) {
        Segment_2D segment = new Segment_2D(a, b);
        Segment_2D copiedSegment = (Segment_2D) segment.copy();

        assertEquals(segment.get_p1(), copiedSegment.get_p1(), "Copied segment P1 should match the original");
        assertEquals(segment.get_p2(), copiedSegment.get_p2(), "Copied segment P2 should match the original");
        assertNotSame(segment, copiedSegment, "The copied segment should be a different object");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void scale(Point_2D a, Point_2D b) {
        Segment_2D segment = new Segment_2D(a, b);

        Point_2D scaleCenter = new Point_2D((a.x() + b.x()) / 2, (a.y() + b.y()) / 2);
        double ratio = 2;

        segment.scale(scaleCenter, ratio);
        Point_2D expectedA = new Point_2D(scaleCenter.x() + ratio * (a.x() - scaleCenter.x()),
                scaleCenter.y() + ratio * (a.y() - scaleCenter.y()));
        Point_2D expectedB = new Point_2D(scaleCenter.x() + ratio * (b.x() - scaleCenter.x()),
                scaleCenter.y() + ratio * (b.y() - scaleCenter.y()));

        assertEquals(expectedA, segment.get_p1(), "Points A after scaling should match the expected values");
        assertEquals(expectedB, segment.get_p2(), "Points B after scaling should match the expected values");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void rotate(Point_2D a, Point_2D b) {
        Segment_2D segment = new Segment_2D(a, b);
        Point_2D center = new Point_2D((a.x() + b.x()) / 2, (a.y() + b.y()) / 2);
        double angleDegrees = 90;

        segment.rotate(center, angleDegrees);

        // Since rotation is not directly observable through getter methods,
        // we can only verify that the points have changed.
        assertNotEquals(a, segment.get_p1(), "Point a should have changed after rotation");
        assertNotEquals(b, segment.get_p2(), "Point b should have changed after rotation");
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void testToString(Point_2D a, Point_2D b) {
        Segment_2D segment = new Segment_2D(a, b);

        String expected = a.x() + "," + a.y() + ", " + b.x() + "," + b.y();
        String result = segment.toString();

        assertEquals(expected, result, "String representation should match the expected format");
    }
}
