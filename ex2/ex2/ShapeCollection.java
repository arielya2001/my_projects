package ex2.ex2;

import ex2.geo.*;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a collection of GUI_Shape.
 * Ex2: you should implement this class!
 * @author Ariel Ya'acobi
 * ID - 318727187
 */
public class ShapeCollection implements GUI_Shape_Collection {
	private ArrayList<GUI_Shape> _shapes;
	
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}

	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shape removeElementAt(int i) {return this._shapes.remove(i);}

	@Override
	public void addAt(GUI_Shape s, int i) {
		if(s!=null && s.getShape()!=null) {
			this._shapes.add(i, s);
		}
	}

	@Override
	public void add(GUI_Shape s) {
		if(s!=null && s.getShape()!=null) {
			this._shapes.add(s);
		}
	}

	@Override
	public ShapeCollection copy() {
		ShapeCollection copyCollection = new ShapeCollection();
		for (int i = 0; i < this._shapes.size(); i++) {
			copyCollection.add(this._shapes.get(i).copy());
		}
		return copyCollection;
	}

	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		this._shapes.sort(comp);
	}

	@Override
	public void removeAll() {this._shapes.clear();}

	@Override
	public void save(String file_name) {
		String shapesToString = this._shapes.toString();
		String content = shapesToString.replace("[", "").replace("]", "");
		String modifiedContent = content.replaceAll("(?<!^)(GUIShape)", "\n$1");
		String path = file_name + ".txt";
		try {
			byte[] bytes = modifiedContent.trim().getBytes(StandardCharsets.UTF_8);
			Files.write(Paths.get(path), bytes);
			System.out.println("File saved successfully.");
		} catch (IOException e) {
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}


	@Override
	public void load(String file) {
		try {
			String path = file; // Moved inside the try block
			byte[] fileBytes = Files.readAllBytes(Paths.get(path));
			String content = new String(fileBytes, StandardCharsets.UTF_8);
			String[] string_shapes = content.split("\n");

			for (int i = 0; i < string_shapes.length; i++) {
				String shape = string_shapes[i];
				GUI_Shape gs = new GUIShape(shape);
				this._shapes.add(gs);
			}
		} catch (IOException e) {
			System.err.println("Cannot read the file not in the correct format: " + e.getMessage());
		}
	}

	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i) + "\n";
		}
		return ans;
	}
	

}
