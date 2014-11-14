package model;

import fileinput.FileReader;

import model.enums.Condition;
import model.enums.EncounterRate;
import model.enums.Government;
import model.enums.SpecialResource;
import model.enums.TechLevel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javafx.scene.paint.Color;

/**
 * Class representing the entire game universe
 * 
 * @author Jack Croft
 *
 */
public class Universe {

    private List<Planet> planets;
    private final double universeSize = 100;
    private final double percentNoneCondition = 0.5;
    private final double percentNoSpecialResource = 0.5;
    private final int maximumNumberOfMercenaries = 4;
    private BoundaryTree planetLocations;
    private Color[] approvedColors;

    /**
     * Simple universe constructor, just creating a blank planet array
     */
    public Universe() {
        planets = new ArrayList<Planet>();
        approvedColors = Planet.APPROVEDCOLORS;
    }

    /**
     * Constructor for a universe taking in a list of planets to begin with
     * 
     * @param p
     *            The list of planets for the Universe to start with
     */
    public Universe(List<Planet> planets) {
        this.planets = planets;
        planetLocations = new BoundaryTree(400, 400, planets);
        approvedColors = Planet.APPROVEDCOLORS;
    }

    /**
     * Method for creating all the planets for the universe, setting up their tech level and
     * resources
     */
    public void createPlanets() {
        FileReader reader = new FileReader();
        List<String> planetNames = reader.readFile("model/PlanetNames.txt");
        Collections.shuffle(planetNames);
        Set<Location> uniqueLocations = new HashSet<Location>();
        Random rand = new Random();
        int x;
        int y;
        int uniSize = (int) Math.sqrt(planetNames.size());
        int uniSize2 = uniSize * uniSize;
        Location location = new Location(0, 0);
        for (int i = 0; i < planetNames.size(); i++) {
            TechLevel[] levels = TechLevel.values();
            SpecialResource[] resources = SpecialResource.values();
            Government[] governments = Government.values();
            Condition[] conditions = Condition.values();
            EncounterRate[] encounterRates = EncounterRate.values();

            int techlevel = (int) (Math.random() * levels.length);
            // int t = 6;
            int resource = (int) (Math.random() * resources.length);
            int government = (int) (Math.random() * governments.length);
            int condition = (int) (Math.random() * conditions.length);
            int pirate = (int) (Math.random() * encounterRates.length);
            int police = (int) (Math.random() * encounterRates.length);
            int trader = (int) (Math.random() * encounterRates.length);
            // int x = (int) (Math.random() * universeLength);
            // int y = (int) (Math.random() * universeWidth);

            if (Math.random() < percentNoneCondition) {
                condition = 0;
            }

            if (Math.random() < percentNoSpecialResource) {
                resource = 0;
            }

            /*
             * boolean uniqueLocation = false; Location l = null; while (!uniqueLocation) { l = new
             * Location(x, y); if (!uniqueLocations.contains(l)) uniqueLocation = true; else { x =
             * (int) (Math.random() * universeLength); y = (int) (Math.random() * universeWidth); }
             * }
             */

            if (i < uniSize2) {
                x = i / uniSize;
                y = i % uniSize;
                x = (int) (x * universeSize / uniSize);
                y = (int) (y * universeSize / uniSize);
                x = x + rand.nextInt(uniSize) + (uniSize / 4);
                y = y + rand.nextInt(uniSize) + (uniSize / 4);
                location = new Location(x, y);
            } else {
                while (uniqueLocations.contains(location)) {
                    x = rand.nextInt((int) universeSize);
                    y = rand.nextInt((int) universeSize);
                    location = new Location(x, y);
                }
            }
            int size = 3 + rand.nextInt(5);
            Color col = approvedColors[rand.nextInt(approvedColors.length)];
            Planet planet = new Planet(planetNames.get(i), levels[techlevel], resources[resource], 
                    governments[government], location,
                    conditions[condition], encounterRates[police], encounterRates[pirate],
                    encounterRates[trader], size, col);
            uniqueLocations.add(location);

            // handles mercenary generation
            List<String> mercenaryNames = reader.readFile("model/MercenaryNames.txt");
            Collections.shuffle(mercenaryNames);
            int currentMercenaryIndex = 0;
            List<Mercenary> mercenaries = new ArrayList<>();
            if (levels[techlevel].getValue() > 5 && currentMercenaryIndex < mercenaryNames.size()) {
                do {
                    String name = mercenaryNames.get(currentMercenaryIndex);
                    Mercenary merc = new Mercenary(name, planet);
                    mercenaries.add(merc);
                    currentMercenaryIndex++;
                } while ((Math.random()) < 0.5 && mercenaries.size() < maximumNumberOfMercenaries);
                // number of mercenaries a planet can be randomly given is capped
            }
            Bar bar = new Bar(mercenaries);
            planet.setBar(bar);

            System.out.println(planet);
            planets.add(planet);
        }
        planetLocations = new BoundaryTree(400, 400, planets);
    }

    /**
     * Gets the list of planets in the universe
     * 
     * @return The list of planets in the universe
     */
    public List<Planet> getPlanets() {
        return planets;
    }

    /**
     * Returns the planet at the given location or null if no planet is there
     * 
     * @param loc
     *            The location where the planet is being searched for
     * @return The planet at the location, or null if no planet is there
     */
    public Planet getPlanetAtLocation(Location loc) {
        return planetLocations.getPlanetAtLocation(loc);
    }

    public int getUniverseSize() {
        return (int) universeSize;
    }
}