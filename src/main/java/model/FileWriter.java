package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * A class for writing text files
 * 
 * @author Jack Croft
 *
 */
public class FileWriter {

	/**
	 * Method for saving game data to file
	 * 
	 * @param p
	 *            The current player to be saved
	 * @param u
	 *            The current universe to be saved
	 */
	public void saveGameData(Player p, Universe u) {
		String saveData = "";

		saveData += p.getName();
		saveData += "\n" + p.getPilotSkill();
		saveData += "\n" + p.getFighterSkill();
		saveData += "\n" + p.getTraderSkill();
		saveData += "\n" + p.getEngineerSkill();
		saveData += "\n" + p.getInvestorSkill();

		List<Planet> planets = u.getPlanets();
		for (int i = 0; i < planets.size(); i++) {
			Planet planet = planets.get(i);
			saveData += "\n" + planet.getName();
			saveData += "\n" + planet.getTechLevel();
			saveData += "\n" + planet.getResource();
			saveData += "\n" + planet.getGovernment();
			saveData += "\n" + planet.getLocation().getX();
			saveData += "\n" + planet.getLocation().getY();
		}
		try {
			File saveFile = new File("src/main/java/model/GameData");
			FileOutputStream os = new FileOutputStream(saveFile);
			os.write(saveData.getBytes());
			os.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
}