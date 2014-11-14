package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javafx.scene.paint.Color;
import model.Location;
import model.NPCEncounter;
import model.Planet;
import model.Player;
import model.Police;
import model.Ship;
import model.enums.Condition;
import model.enums.EncounterRate;
import model.enums.Government;
import model.enums.ShipType;
import model.enums.SpecialResource;
import model.enums.TechLevel;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for ShipyardEngine
 * 
 * @author Hemen Shah
 *
 */
public class ShipyardEngineTest {

	Planet planet;
	Ship ship;
	Player player;
	ShipyardEngine engine;
	Police police;
	NPCEncounter encounter;
	Location loc;
	// These are ship types that are expected on a planet given a tech level.
	// These arrays may need to be changed if more ship types are added or if
	// values within certain shiptypes change
	ShipType expectedShipTypesTechLevel0[] = {};
	ShipType expectedShipTypesTechLevel1[] = {};
	ShipType expectedShipTypesTechLevel2[] = {};
	ShipType expectedShipTypesTechLevel3[] = {};
	ShipType expectedShipTypesTechLevel4[] = { ShipType.FLEA };
	ShipType expectedShipTypesTechLevel5[] = { ShipType.FLEA, ShipType.GNAT,
			ShipType.FIREFLY, ShipType.MOSQUITO, ShipType.BUMBLEBEE,
			ShipType.BEETLE };
	ShipType expectedShipTypesTechLevel6[] = { ShipType.FLEA, ShipType.GNAT,
			ShipType.FIREFLY, ShipType.MOSQUITO, ShipType.BUMBLEBEE,
			ShipType.BEETLE, ShipType.HORNET, ShipType.GRASSHOPPER,
			ShipType.TERMITE };
	ShipType expectedShipTypesTechLevel7[] = { ShipType.FLEA, ShipType.GNAT,
			ShipType.FIREFLY, ShipType.MOSQUITO, ShipType.BUMBLEBEE,
			ShipType.BEETLE, ShipType.HORNET, ShipType.GRASSHOPPER,
			ShipType.TERMITE, ShipType.WASP, ShipType.MANTIS };

	@Before
	public void setUp() throws Exception {
		loc = new Location(50, 50);
		ship = new Ship(ShipType.FLEA);
		player = new Player("Test", 5, 5, 5, 5, 5, ship);
		engine = new ShipyardEngine(player);
	}

	@Test
	public void getAvailableShipsTestTechLevel0() {
		planet = new Planet("test", TechLevel.PREAGRICULTURE,
				SpecialResource.DESERT, Government.DEMOCRACY, loc,
				Condition.BOREDOM, EncounterRate.FEW, EncounterRate.FEW,
				EncounterRate.FEW, 1, Color.AQUA);
		player.setPlanet(planet);

		List<ShipType> available = engine.getAvailableShips();
		assertEquals("Number of ships dont match",
				expectedShipTypesTechLevel0.length, available.size());
		for (ShipType expected : expectedShipTypesTechLevel0) {
			assertTrue("At least one of the expected ships is not returned",
					available.contains(expected));
		}
	}

	@Test
	public void getAvailableShipsTestTechLevel1() {
		planet = new Planet("test", TechLevel.AGRICULTURE,
				SpecialResource.DESERT, Government.DEMOCRACY, loc,
				Condition.BOREDOM, EncounterRate.FEW, EncounterRate.FEW,
				EncounterRate.FEW, 1, Color.AQUA);
		player.setPlanet(planet);

		List<ShipType> available = engine.getAvailableShips();
		assertEquals("Number of ships dont match",
				expectedShipTypesTechLevel1.length, available.size());
		for (ShipType expected : expectedShipTypesTechLevel1) {
			assertTrue("At least one of the expected ships is not returned",
					available.contains(expected));
		}
	}

	@Test
	public void getAvailableShipsTestTechLevel2() {
		planet = new Planet("test", TechLevel.MEDIEVAL, SpecialResource.DESERT,
				Government.DEMOCRACY, loc, Condition.BOREDOM,
				EncounterRate.FEW, EncounterRate.FEW, EncounterRate.FEW, 1,
				Color.AQUA);
		player.setPlanet(planet);

		List<ShipType> available = engine.getAvailableShips();
		assertEquals("Number of ships dont match",
				expectedShipTypesTechLevel2.length, available.size());
		for (ShipType expected : expectedShipTypesTechLevel2) {
			assertTrue("At least one of the expected ships is not returned",
					available.contains(expected));
		}
	}

	@Test
	public void getAvailableShipsTestTechLevel3() {
		planet = new Planet("test", TechLevel.RENAISSANCE,
				SpecialResource.DESERT, Government.DEMOCRACY, loc,
				Condition.BOREDOM, EncounterRate.FEW, EncounterRate.FEW,
				EncounterRate.FEW, 1, Color.AQUA);
		player.setPlanet(planet);

		List<ShipType> available = engine.getAvailableShips();
		assertEquals("Number of ships dont match",
				expectedShipTypesTechLevel3.length, available.size());
		for (ShipType expected : expectedShipTypesTechLevel3) {
			assertTrue("At least one of the expected ships is not returned",
					available.contains(expected));
		}
	}

	@Test
	public void getAvailableShipsTestTechLevel4() {
		planet = new Planet("test", TechLevel.EARLYINDUSTRIAL,
				SpecialResource.DESERT, Government.DEMOCRACY, loc,
				Condition.BOREDOM, EncounterRate.FEW, EncounterRate.FEW,
				EncounterRate.FEW, 1, Color.AQUA);
		player.setPlanet(planet);

		List<ShipType> available = engine.getAvailableShips();
		assertEquals("Number of ships dont match",
				expectedShipTypesTechLevel4.length, available.size());
		for (ShipType expected : expectedShipTypesTechLevel4) {
			assertTrue("At least one of the expected ships is not returned",
					available.contains(expected));
		}
	}

	@Test
	public void getAvailableShipsTestTechLevel5() {
		planet = new Planet("test", TechLevel.INDUSTRIAL,
				SpecialResource.DESERT, Government.DEMOCRACY, loc,
				Condition.BOREDOM, EncounterRate.FEW, EncounterRate.FEW,
				EncounterRate.FEW, 1, Color.AQUA);
		player.setPlanet(planet);

		List<ShipType> available = engine.getAvailableShips();
		assertEquals("Number of ships dont match",
				expectedShipTypesTechLevel5.length, available.size());
		for (ShipType expected : expectedShipTypesTechLevel5) {
			assertTrue("At least one of the expected ships is not returned",
					available.contains(expected));
		}
	}

	@Test
	public void getAvailableShipsTestTechLevel6() {
		planet = new Planet("test", TechLevel.POSTINDUSTRIAL,
				SpecialResource.DESERT, Government.DEMOCRACY, loc,
				Condition.BOREDOM, EncounterRate.FEW, EncounterRate.FEW,
				EncounterRate.FEW, 1, Color.AQUA);
		player.setPlanet(planet);

		List<ShipType> available = engine.getAvailableShips();
		assertEquals("Number of ships dont match",
				expectedShipTypesTechLevel6.length, available.size());
		for (ShipType expected : expectedShipTypesTechLevel6) {
			assertTrue("At least one of the expected ships is not returned",
					available.contains(expected));
		}
	}

	@Test
	public void getAvailableShipsTestTechLevel7() {
		planet = new Planet("test", TechLevel.HITECH, SpecialResource.DESERT,
				Government.DEMOCRACY, loc, Condition.BOREDOM,
				EncounterRate.FEW, EncounterRate.FEW, EncounterRate.FEW, 1,
				Color.AQUA);
		player.setPlanet(planet);

		List<ShipType> available = engine.getAvailableShips();
		assertEquals("Number of ships dont match",
				expectedShipTypesTechLevel7.length, available.size());
		for (ShipType expected : expectedShipTypesTechLevel7) {
			assertTrue("At least one of the expected ships is not returned",
					available.contains(expected));
		}
	}

}
