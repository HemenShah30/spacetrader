package model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A class for reading in text files
 * 
 * @author Jack Croft
 *
 */
public class Reader {

	/**
	 * Reader for planet names from a given file
	 * 
	 * @param fileName
	 *            The name of the file with the planet names
	 * @return The list of planet names from the file
	 */
	public List<String> readPlanetNames(String fileName) {
		List<String> planetNames = new ArrayList<String>();
		InputStream is = ClassLoader
				.getSystemResourceAsStream("model/PlanetNames.txt");

		Scanner reader = new Scanner(is);
		reader.useDelimiter("\n");
		while (reader.hasNext())
			planetNames.add(reader.next());
		reader.close();

		return planetNames;
	}
}