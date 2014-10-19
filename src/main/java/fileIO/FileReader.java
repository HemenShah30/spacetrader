package fileIO;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Universe;

/**
 * A class for reading in text files
 * 
 * @author Jack Croft
 *
 */
public class FileReader {

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
		reader.useDelimiter("\r\n");
		while (reader.hasNext())
			planetNames.add(reader.next());
		reader.close();

		return planetNames;
	}

	/**
	 * Loads the saved game data from file
	 * 
	 * @return The Player then the Universe in that order from file
	 */
	public Object[] loadGameData() {
		Object[] gameData = new Object[2];
		Universe u = new Universe();
		InputStream is = ClassLoader
				.getSystemResourceAsStream("model/GameData");
		Scanner input = new Scanner(is);
		input.useDelimiter("\n");

		input.close();
		gameData[1] = u;
		return gameData;
	}
}