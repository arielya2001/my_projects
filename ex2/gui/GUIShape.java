package ex2.gui;
/**
 * This class implements the GUI_shape.
 * Ex2: you should implement this class!
 * Note! - I have changed only the "GUIShape(String s)" function.
 * @author I2CS and Ariel Ya'acobi
 */
import ex2.geo.*;
import java.awt.*;
import java.util.ArrayList;


public class GUIShape implements GUI_Shape{
	private GeoShape _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;
	
	public GUIShape(GeoShape g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}

	/**
	 * GUIShape Constructor
	 * Parses a string representation of a geometric shape and initializes the corresponding GeoShape object.
	 * @param s the string representation of the geometric shape
	 */
	public GUIShape(String s) {
		String[] prop = s.split(",");
		String shapeType = prop[4];

		switch (shapeType) {
			case "Circle_2D":
				_g = new Circle_2D(new Point_2D(Double.parseDouble(prop[5]), Double.parseDouble(prop[6])), Double.parseDouble(prop[7]));
				break;

			case "Polygon_2D":
				Polygon_2D p = new Polygon_2D();
				for (int i = 5; i + 1 < prop.length; i += 2) {
					p.add(new Point_2D(Double.parseDouble(prop[i]), Double.parseDouble(prop[i + 1])));
				}
				_g = p;
				break;

			case "Rect_2D":
				double xTop = Double.parseDouble(prop[5]);
				double yTop = Double.parseDouble(prop[6]);
				double xRight = Double.parseDouble(prop[9]);
				double yRight = Double.parseDouble(prop[10]);
				_g = new Rect_2D(new Point_2D(xTop, yTop), new Point_2D(xRight, yRight));
				break;

			case "Segment_2D":
				_g = new Segment_2D(new Point_2D(Double.parseDouble(prop[5]), Double.parseDouble(prop[6])), new Point_2D(Double.parseDouble(prop[7]), Double.parseDouble(prop[8])));
				break;

			case "Triangle_2D":
				ArrayList<Point_2D> pointList = new ArrayList<>();
				for (int i = 5; i < prop.length; i += 2) {
					pointList.add(new Point_2D(Double.parseDouble(prop[i]), Double.parseDouble(prop[i + 1])));
				}
				_g = new Triangle_2D(pointList.get(0), pointList.get(1), pointList.get(2));
				break;

			default:
				_g = null; // Handle unrecognized shape types
				break;
		}

		if (_g != null) {
			_fill = Boolean.parseBoolean(prop[2]);
			_tag = Integer.parseInt(prop[3]);
			_color = Color.decode(prop[1]);
			_isSelected = false;
		}
	}


	@Override
	public GeoShape getShape() {
		return _g;
	}

	@Override
	public void setShape(GeoShape g) {
		_g = g;
	}

	@Override
	public boolean isFilled() {
		return _fill;
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;
		
	}

	@Override
	public GUI_Shape copy() {
		GUI_Shape cp = new GUIShape(this);
		return cp;
	}
	@Override
	public String toString() {
		String ans = ""+this.getClass().getSimpleName()+","+_color.getRGB()+","+_fill+","+_tag+","+this._g.getClass().getSimpleName()+","+_g.toString();
		return ans;
	}
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
}
