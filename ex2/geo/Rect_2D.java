package ex2.geo;

/**
 * This class represents a 2D axis parallel rectangle.
 * @author Ariel Ya'acobi
 * ID - 318727187
 */
public class Rect_2D implements GeoShape {

	private Point_2D p1, p2, p3, p4;

	public Point_2D getP1() { return p1; }
	public Point_2D getP2() { return p2; }
	public Point_2D getP3() { return p3; }
	public Point_2D getP4() { return p4; }

	public Rect_2D(Point_2D topLeft, Point_2D bottomRight) {
		this.p1 = new Point_2D(topLeft);
		this.p2 = new Point_2D(bottomRight);
		this.p3 = new Point_2D(bottomRight.x(), topLeft.y());
		this.p4 = new Point_2D(topLeft.x(), bottomRight.y());
	}

	/** -Rect_2D-
	 * Creates a new object of Rect_2D that is a copy of the specified Rect_2D object.
	 * creates a new object with the same properties.
	 * @param t1 - the Rect_2D object to copy.
	 */
	public Rect_2D(Rect_2D t1) {
		this(t1.p1, t1.p2);
	}

	/** -contains-
	 * check if a Point (x,y) is in the area of the rectangle
	 * @param ot - a query 2D point
	 * @return True if the Point is in the area and False if not
	 */
	@Override
	public boolean contains(Point_2D ot) {
		if (this.p1.y() < this.p2.y()) {
			if (this.p1.y() <= ot.y() && ot.y() <= this.p2.y()) {
				if (this.p1.x() < this.p2.x()) {
					if (this.p1.x() <= ot.x() && ot.x() <= this.p2.x())
						return true;
				} else if (this.p2.x() <= ot.x() && ot.x() <= this.p1.x())
					return true;
			}
		} else {
			if (this.p2.y() <= ot.y() && ot.y() <= this.p1.y()) {
				if (this.p1.x() < this.p2.x()) {
					if (this.p1.x() <= ot.x() && ot.x() <= this.p2.x())
						return true;
				} else {
					if (this.p2.x() <= ot.x() && ot.x() <= this.p1.x())
						return true;
				}
			}
		}
		return false;
	}

	/** -area-
	 * multiply the width with the length
	 * @return the value of the mul (the area of the rectangle)
	 */
	@Override
	public double area() {
		Point_2D bottomLeft = new Point_2D(p1.x(), p2.y());
		return this.p1.distance(bottomLeft) * this.p2.distance(bottomLeft);
	}

	/** -perimeter-
	 *  sum twice the width and twice the length
	 * @return the value of the perimeter
	 */
	@Override
	public double perimeter() {
		return (2 * this.p2.distance(p4)) + (2 * this.p1.distance(p4));
	}

	/** -translate-
	 * Move the rectangle by the vector 0,0-->vec
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		this.p1.move(vec);
		this.p2.move(vec);
		this.p3.move(vec);
		this.p4.move(vec);
	}

	/** -copy-
	 * Computes a new (deep) copy of this rectangle
	 * @return a new rectangle object with the same properties
	 */
	@Override
	public GeoShape copy() {
		Rect_2D copyRect = new Rect_2D(new Point_2D(this.p1), new Point_2D(this.p2));
		copyRect.p3 = new Point_2D(this.p3);
		copyRect.p4 = new Point_2D(this.p4);
		return copyRect;
	}


	/** -scale-
	 * Rescales the rectangle with respect to the given center point
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		if (ratio == 1) {
			return; // No scaling needed
		}

		if (ratio == -1) {
			// Reverse the direction of the rectangle by swapping points p1 and p2, and p3 and p4
			Point_2D temp = p1;
			p1 = p2;
			p2 = temp;
			temp = p3;
			p3 = p4;
			p4 = temp;
			return;
		}

		// Calculate the scaled distances from the center to each corner of the rectangle
		double dx1 = p1.x() - center.x();
		double dy1 = p1.y() - center.y();
		double scaledDx1 = dx1 * ratio;
		double scaledDy1 = dy1 * ratio;
		double scaledX1 = center.x() + scaledDx1;
		double scaledY1 = center.y() + scaledDy1;

		double dx2 = p2.x() - center.x();
		double dy2 = p2.y() - center.y();
		double scaledDx2 = dx2 * ratio;
		double scaledDy2 = dy2 * ratio;
		double scaledX2 = center.x() + scaledDx2;
		double scaledY2 = center.y() + scaledDy2;

		double dx3 = p3.x() - center.x();
		double dy3 = p3.y() - center.y();
		double scaledDx3 = dx3 * ratio;
		double scaledDy3 = dy3 * ratio;
		double scaledX3 = center.x() + scaledDx3;
		double scaledY3 = center.y() + scaledDy3;

		double dx4 = p4.x() - center.x();
		double dy4 = p4.y() - center.y();
		double scaledDx4 = dx4 * ratio;
		double scaledDy4 = dy4 * ratio;
		double scaledX4 = center.x() + scaledDx4;
		double scaledY4 = center.y() + scaledDy4;

		// Update the points with the scaled coordinates
		p1.set_x(scaledX1);
		p1.set_y(scaledY1);
		p2.set_x(scaledX2);
		p2.set_y(scaledY2);
		p3.set_x(scaledX3);
		p3.set_y(scaledY3);
		p4.set_x(scaledX4);
		p4.set_y(scaledY4);
	}


	/** -rotate-
	 * Rotates the rectangle with respect to the given center point by an angle.
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		if (angleDegrees == 0) { return; }
		p1.rotate(center, angleDegrees);
		p2.rotate(center, angleDegrees);
		p3.rotate(center, angleDegrees);
		p4.rotate(center, angleDegrees);
	}

	/** -toString-
	 * This method returns a String representing this shape,
	 * such that one can use this string for saving the shape into a text file.
	 * @return a String representing this shape
	 */
	@Override
	public String toString() { return p1 + "," + p3 + "," + p2 + "," + p4; }

}
