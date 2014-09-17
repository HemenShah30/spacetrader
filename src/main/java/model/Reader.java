package model;

import java.io.File;
import java.io.IOException;
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

	public List<String> readPlanetNames(String fileName) {
		List<String> planetNames = new ArrayList<String>();
		File planetFile = new File(fileName);
		try {
			Scanner reader = new Scanner(planetFile);
			reader.useDelimiter("\n");
			while (reader.hasNext())
				planetNames.add(reader.next());
			reader.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}

		return planetNames;
	}
}