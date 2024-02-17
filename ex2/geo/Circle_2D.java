package ex2.geo;

/**
 * This class represents a 2D circle in the plane.
 * Please ensure it adheres to the GeoShape interface.
 * Ex2: You should update this class!
 * Author: Ariel Ya'acobi
 * ID: 318727187
 */
public class Circle_2D implements GeoShape {
	private Point_2D _center;
	private double _radius;

	public Circle_2D(Point_2D center, double radius) {
		this._center = new Point_2D(center);
		this._radius = radius;
	}

	public Circle_2D(Circle_2D c) {
		this(c.getCenter(), c.getRadius());
	}

	public double getRadius() {
		return this._radius;
	}

	public Point_2D getCenter() {
		return _center;
	}

	/**
	 * Returns a String representation of this circle.
	 * The format is "center coordinates, radius".
	 * This string can be used for saving the shape into a text file.
	 *
	 * @return A string representing this circle.
	 */
	@Override
	public String toString() {
		return _center.toString() + ", " + _radius;
	}

	/**
	 * Checks if a given point is within the circle.
	 * @param ot The point to check.
	 * @return True if the point is inside the circle, False otherwise.
	 */
	@Override
	public boolean contains(Point_2D ot) {
		double distanceSquared = Math.pow(_center.x() - ot.x(), 2) + Math.pow(_center.y() - ot.y(), 2);
		double radiusSquared = Math.pow(_radius, 2);
		return distanceSquared <= radiusSquared;
	}

	/**
	 * Computes the area of the circle.
	 * @return The area of the circle.
	 */
	@Override
	public double area() {
		return Math.PI * Math.pow(_radius, 2);
	}

	/**
	 * Computes the perimeter (circumference) of the circle.
	 * @return The perimeter of the circle.
	 */
	@Override
	public double perimeter() {
		return 2 * Math.PI * _radius;
	}

	/**
	 * Moves the circle by the specified vector.
	 * @param vec The vector by which the circle is translated.
	 */
	@Override
	public void translate(Point_2D vec) {
		_center.move(vec);
	}

	/**
	 * Creates a deep copy of this circle.
	 * @return A new Circle_2D object with the same properties.
	 */
	@Override
	public GeoShape copy() {
		return new Circle_2D(this);
	}

	/**
	 * Rescales the circle relative to the specified center point.
	 * @param center The center point with respect to which scaling is performed.
	 * @param ratio  The scaling ratio.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		if (ratio == 1) {
			return;
		}
		// Scaling operation
		_center.scale(center, ratio);
		_radius *= ratio;
	}

	/**
	 * Rotates the circle around the specified center point by the given angle in degrees.
	 * @param center - The center point around which rotation is performed.
	 * @param angleDegrees - The angle of rotation in degrees.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		_center.rotate(center, angleDegrees);
	}
}
