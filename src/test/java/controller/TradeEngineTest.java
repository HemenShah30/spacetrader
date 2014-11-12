package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import model.Gadget;
import model.Location;
import model.Planet;
import model.Player;
import model.Ship;
import model.Shipyard;
import model.Enum.Condition;
import model.Enum.EncounterRate;
import model.Enum.Government;
import model.Enum.ShipType;
import model.Enum.SpecialResource;
import model.Enum.TechLevel;

import org.junit.Before;
import org.junit.Test;

import controller.TradeEngine;

/**
 * JUnit Test for trading Eric - Buying ShipUpgrade tests
 * 
 * @author E
 *
 */
public class TradeEngineTest {
	Planet planet;
	Ship ship;
	Player player;
	TradeEngine engine;
	Shipyard shipyard;

	@Before
	public void initialSetUp() throws Exception {
		Location l = new Location(50, 50);
		planet = new Planet("test", TechLevel.HITECH, SpecialResource.DESERT,
				Government.DEMOCRACY, l, Condition.BOREDOM, EncounterRate.FEW,
				EncounterRate.FEW, EncounterRate.FEW, 1, Color.AQUA);
		ship = new Ship(ShipType.FLEA);
		player = new Player("Test", 5, 5, 5, 5, 5, ship);
		player.setPlanet(planet);
		player.increaseCredits(10000);
		engine = new TradeEngine(player);
		shipyard = new Shipyard(planet);
	}

	@Test
	public void addShipUpgradeZeroCapacityTest() {
		ship = new Ship(ShipType.FLEA);
		assertTrue(
				"Flea should not have room for upgrades",
				engine.buyShipUpgrade(new Gadget(100.0, 1), shipyard).contains(
						"Not enough space in your ship"));
	}

	@Test
	public void addShipUpgradeSomeCapacityTest() {
		ship = new Ship(ShipType.GNAT);
		ship.addShipUpgrade(new Gadget(100.0, 1));
		assertTrue(
				"Ship should not have room for upgrades",
				engine.buyShipUpgrade(new Gadget(100.0, 1), shipyard).contains(
						"Not enough space in your ship"));
	}

	@Test
	public void addShipUpgradeCostTest() {
		ship = new Ship(ShipType.MANTIS);
		Gadget g = new Gadget(1000000.0, 1);
		assertTrue("Player should not have sufficient money", engine
				.buyShipUpgrade(g, shipyard).contains("Not enough credits"));
	}
	
	@Test
	public void addShipUpgradeTechLevelTest() {
		Gadget g = new Gadget(100.0, 3);
		Location l = new Location(55, 55);
		planet = new Planet("test", TechLevel.PREAGRICULTURE, SpecialResource.DESERT,
				Government.DEMOCRACY, l, Condition.BOREDOM, EncounterRate.FEW,
				EncounterRate.FEW, EncounterRate.FEW, 1, Color.AQUA);
		player.setPlanet(planet);
		shipyard = new Shipyard(planet);
		assertTrue("Planet should not be able to sell this ShipUpgrade", engine.buyShipUpgrade(g, shipyard).contains("Planet cannot sell this upgrade"));
	}
}
