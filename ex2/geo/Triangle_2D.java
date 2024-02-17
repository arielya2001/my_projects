package ex2.geo;

/**
 * This class represents a 2D Triangle in the plane.
 * @author Ariel Ya'acobi
 * ID - 318727187
 */
public class Triangle_2D implements GeoShape {
	private Point_2D p1, p2, p3;
	/**
	 * Constructs a new Triangle_2D object with the given vertices.
	 * @param p1 The first vertex of the triangle.
	 * @param p2 The second vertex of the triangle.
	 * @param p3 The third vertex of the triangle.
	 */
	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {
		this.p1 = new Point_2D(p1);
		this.p2 = new Point_2D(p2);
		this.p3 = new Point_2D(p3);
	}

	/** Triangle_2D
	 * Constructs a new Triangle_2D object that is a copy of the specified triangle.
	 * @param t1 The triangle to copy.
	 */
	public Triangle_2D(Triangle_2D t1) {
		this(t1.p1, t1.p2, t1.p3);
	}

	/** getAllPoints
	 * Returns an array containing all the vertices of the triangle.
	 * The vertices are ordered as follows: p1, p2, p3.
	 * @return An array containing the three vertices of the triangle.
	 */
	public Point_2D[] getAllPoints() {
		return new Point_2D[]{p1, p2, p3};
	}

	/** contains
	 * Checks if a given point is contained within the boundaries of the triangle.
	 * Uses the barycentric coordinates method to determine containment.
	 * @param ot The point to check for containment.
	 * @return True if the point is inside the triangle, false otherwise.
	 */
	@Override
	public boolean contains(Point_2D ot) {
		double areaTotal, area1, area2, area3;
		areaTotal = this.area();

		area1 = Math.abs((ot.x() - p3.x()) * (p2.y() - p3.y()) -
				(ot.y() - p3.y()) * (p2.x() - p3.x())) / areaTotal;
		area2 = Math.abs((p1.x() - ot.x()) * (p2.y() - ot.y()) -
				(p1.y() - ot.y()) * (p2.x() - ot.x())) / areaTotal;
		area3 = 1 - area1 - area2;

		return area1 >= 0 && area1 <= 1 && area2 >= 0 && area2 <= 1 && area3 >= 0 && area3 <= 1;
	}

	/** area
	 * Calculates the area of the triangle.
	 * @return The area of the triangle.
	 */
	@Override
	public double area() {
		return Math.abs((p1.x() - p3.x()) *
				(p2.y() - p3.y()) - (p1.y() - p3.y()) * (p2.x() - p3.x()));
	}

	/** perimeter
	 * Calculates the perimeter of the triangle.
	 * @return The perimeter of the triangle.
	 */
	@Override
	public double perimeter() {
		double distance1 = p1.distance(p2);
		double distance2 = p1.distance(p3);
		double distance3 = p3.distance(p2);
		return distance1 + distance2 + distance3;
	}

	/** translate
	 * Translates (moves) the triangle by a specified vector.
	 * @param vec The vector by which to translate the triangle.
	 */
	@Override
	public void translate(Point_2D vec) {
		this.p1.move(vec);
		this.p3.move(vec);
		this.p2.move(vec);
	}

	/** copy
	 * Creates a deep copy of this triangle.
	 * @return A new Triangle_2D object with the same properties as this triangle.
	 */
	@Override
	public GeoShape copy() {
		return new Triangle_2D(this);
	}

	/** scale
	 * Scales the triangle relative to a given center point by a specified ratio.
	 * @param center The center point for scaling.
	 * @param ratio The scaling ratio.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		if (ratio != 1) {
			p1.scale(center, ratio);
			p2.scale(center, ratio);
			p3.scale(center, ratio);
		}
	}

	/** rotate
	 * Rotates the triangle around a specified center point by a given angle.
	 * @param center The center point of rotation.
	 * @param angleDegrees The angle of rotation in degrees.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this.p1.rotate(center, angleDegrees);
		this.p2.rotate(center, angleDegrees);
		this.p3.rotate(center, angleDegrees);
	}

	/** toString
	 * Returns a string representation of the triangle.
	 * The format is "p1, p2, p3", where p1, p2, and p3 are the vertices of the triangle.
	 * @return A string representation of the triangle.
	 */
	@Override
	public String toString() {
		return p1 + ", " + p2 + ", " + p3;
	}
}
