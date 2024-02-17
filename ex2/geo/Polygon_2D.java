package ex2.geo;

import java.util.ArrayList;

public class Polygon_2D implements GeoShape {
	private ArrayList<Point_2D> vertexList;

	/**
	 * Constructs a new Polygon_2D object with an empty list of vertices.
	 */
	public Polygon_2D() {
		this.vertexList = new ArrayList<>();
	}

	/** Polygon_2D
	 * Constructs a new Polygon_2D object that is a copy of the specified Polygon_2D object.
	 * @param po the Polygon_2D object to copy
	 */
	public Polygon_2D(Polygon_2D po) {
		this.vertexList = new ArrayList<>(po.vertexList);
	}

	/** -getAllPoints-
	 * Return an array containing all the vertices of the polygon.
	 * @return an array containing all the vertices of the polygon
	 */
	public Point_2D[] getAllPoints() {
		return vertexList.toArray(new Point_2D[0]);
	}

	/** add
	 * Adds a new vertex to the polygon.
	 * @param p the Point_2D representing the new vertex
	 */
	public void add(Point_2D p) {
		this.vertexList.add(p);
	}

	/** toString
	 * Returns a string representation of this polygon.
	 * @return a string representing this polygon
	 */
	@Override
	public String toString() {
		return vertexList.toString();
	}

	/** contains
	 * Checks if a given 2D point is inside the polygon using the Ray Casting algorithm.
	 * @param ot the query 2D point
	 * @return true if the point is inside the polygon, false otherwise
	 */
	@Override
	public boolean contains(Point_2D ot) {
		double x = ot.x();
		double y = ot.y();
		int crossings = 0;
		int n = vertexList.size();
		for (int i = 0; i < n; i++) {
			double x1 = vertexList.get(i).x();
			double y1 = vertexList.get(i).y();
			double x2 = vertexList.get((i + 1) % n).x();
			double y2 = vertexList.get((i + 1) % n).y();
			if (((y1 <= y && y < y2) || (y2 <= y && y < y1)) &&
					(x < (x2 - x1) * (y - y1) / (y2 - y1) + x1)) {
				crossings++;
			}
		}
		return crossings % 2 != 0;
	}

	/** area
	 * Calculates the area of the polygon using the Shoelace formula.
	 * @return the area of the polygon
	 */
	@Override
	public double area() {
		double area = 0.0;
		int n = vertexList.size();
		for (int i = 0; i < n; i++) {
			double x1 = vertexList.get(i).x();
			double y1 = vertexList.get(i).y();
			double x2 = vertexList.get((i + 1) % n).x();
			double y2 = vertexList.get((i + 1) % n).y();
			area += (x1 * y2) - (x2 * y1);
		}
		return Math.abs(area / 2.0);
	}

	/** perimeter
	 * Calculates the perimeter of the polygon by summing the distances between consecutive vertices.
	 * @return the perimeter of the polygon
	 */
	@Override
	public double perimeter() {
		double totalP = 0;
		int n = vertexList.size();
		for (int i = 0; i < n; i++) {
			Point_2D currentVertex = vertexList.get(i);
			Point_2D nextVertex = vertexList.get((i + 1) % n);
			totalP += currentVertex.distance(nextVertex);
		}
		return totalP;
	}

	/** translate
	 * Moves the polygon by a given vector.
	 * @param vec the vector representing the translation
	 */
	@Override
	public void translate(Point_2D vec) {
		for (Point_2D vertex : vertexList) {
			vertex.move(vec);
		}
	}


	/** copy
	 * Creates a deep copy of this polygon.
	 * @return a new Polygon_2D object with the same vertices as this polygon
	 */
	@Override
	public GeoShape copy() {
		Polygon_2D copyPolygon = new Polygon_2D();
		for (Point_2D v : this.vertexList) {
			Point_2D copiedPoint = new Point_2D(v.x(), v.y()); // Create a copy of the Point_2D
			copyPolygon.add(copiedPoint); // Add the copied point to the new polygon
		}
		return copyPolygon;
	}

	/** scale
	 * Rescales the polygon with respect to a given center point and a scaling ratio.
	 * @param center the center point for scaling
	 * @param ratio the scaling ratio
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		if (ratio == 1) {
			return; // No scaling needed
		}

		// Apply scaling to each vertex relative to the center
		for (int i = 0; i < vertexList.size(); i++) {
			Point_2D p = vertexList.get(i);
			double dx = p.x() - center.x();
			double dy = p.y() - center.y();
			double scaledDx = dx * ratio;
			double scaledDy = dy * ratio;
			double scaledX = center.x() + scaledDx;
			double scaledY = center.y() + scaledDy;
			vertexList.set(i, new Point_2D(scaledX, scaledY));
		}

		// If ratio is negative, reverse the order of vertices
		if (ratio < 0) {
			ArrayList<Point_2D> reversed = new ArrayList<>(vertexList.size());
			for (int i = vertexList.size() - 1; i >= 0; i--) {
				reversed.add(vertexList.get(i));
			}
			vertexList = reversed;
		}
	}


	/** -rotate-
	 * Rotates the polygon around a given center point by a specified angle.
	 * @param center the center point for rotation
	 * @param angleDegrees the angle of rotation in degrees
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
        for (Point_2D point2D : vertexList) {
            point2D.rotate(center, angleDegrees);
        }
	}

}
