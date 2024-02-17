package ex2.geo;

/**
 * This class represents a 2D segment on the plane.
 * @author Ariel Ya'acobi
 * ID - 318727187
 */
public class Segment_2D implements GeoShape {
	private Point_2D a, b;

	public Segment_2D(Point_2D a, Point_2D b) {
		this.a = new Point_2D(a);
		this.b = new Point_2D(b);
	}

	public Segment_2D(Segment_2D t1) {
		this(t1.a, t1.b);
	}

	public Point_2D get_p1() {
		return this.a;
	}

	public Point_2D get_p2() {
		return this.b;
	}

	/** -contains-
	 * Checks if a Point (x,y) is on the segment.
	 * @param ot - a query 2D point
	 * @return True if the Point is on the segment, False otherwise
	 */
	@Override
	public boolean contains(Point_2D ot) {
		if (this.a.distance(ot) + this.b.distance(ot) - this.a.distance(this.b) < 0.01)
			return true;
		return false;
	}

	/** -area-
	 * The segment has no area.
	 * @return 0
	 */
	@Override
	public double area() {
		return 0;
	}

	/** -perimeter-
	 * Calculates the perimeter of the segment.
	 * @return Twice the distance from a to b
	 */
	@Override
	public double perimeter() {
		return a.distance(b) * 2;
	}

	/** -translate-
	 * Moves the segment by the specified vector.
	 * @param vec - a vector from the origin (0,0)
	 */
	@Override
	public void translate(Point_2D vec) {
		a.move(vec);
		b.move(vec);
	}

	/** -copy-
	 * Creates a deep copy of this segment.
	 * @return A new segment object with the same properties
	 */
	@Override
	public GeoShape copy() {
		return new Segment_2D(new Point_2D(this.a), new Point_2D(this.b));
	}

	/** -scale-
	 * Rescales the segment with respect to the given center point and ratio.
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		if (ratio == 1) {
			return;
		}

		if (ratio == -1) {
			// Reverse the direction of the segment by swapping points a and b
			Point_2D temp = a;
			a = b;
			b = temp;
			return;
		}

		// Calculate the scaled coordinates of points a and b with respect to the center
		double scaledAx = center.x() + (a.x() - center.x()) * ratio;
		double scaledAy = center.y() + (a.y() - center.y()) * ratio;
		double scaledBx = center.x() + (b.x() - center.x()) * ratio;
		double scaledBy = center.y() + (b.y() - center.y()) * ratio;

		// Update the points with the scaled coordinates
		a.set_x(scaledAx);
		a.set_y(scaledAy);
		b.set_x(scaledBx);
		b.set_y(scaledBy);
	}

	/** -rotate-
	 * Rotates the segment around the specified center point by the given angle in degrees.
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		a.rotate(center, angleDegrees);
		b.rotate(center, angleDegrees);
	}

	/** -toString-
	 * Returns a String representing this shape,
	 * such that one can use this string for saving the shape into a text file.
	 * @return a String representing this shape
	 */
	@Override
	public String toString() {
		return a + ", " + b;
	}
}
