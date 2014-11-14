package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import model.Gadget;
import model.Location;
import model.Planet;
import model.Player;
import model.Ship;
import model.Shipyard;
import model.Trader;

import model.enums.Condition;
import model.enums.EncounterRate;
import model.enums.GoodType;
import model.enums.Government;
import model.enums.ShipType;
import model.enums.SpecialResource;
import model.enums.TechLevel;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javafx.scene.paint.Color;

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
    public void setup() throws Exception {
        Location loc = new Location(50, 50);
        planet = new Planet("test", TechLevel.HITECH, SpecialResource.DESERT, Government.DEMOCRACY,
                loc, Condition.BOREDOM, EncounterRate.FEW, EncounterRate.FEW, EncounterRate.FEW, 1,
                Color.AQUA);
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
        Gadget gadget = new Gadget(1000000.0, 1);
        assertTrue("Player should not have sufficient money",
                engine.buyShipUpgrade(gadget, shipyard).contains("Not enough credits"));
    }

    @Test
    public void addShipUpgradeTechLevelTest() {
        Location location = new Location(55, 55);
        Gadget gdt = new Gadget(100.0, 3);
        planet = new Planet("test", TechLevel.PREAGRICULTURE, SpecialResource.DESERT,
                Government.DEMOCRACY, location, Condition.BOREDOM, EncounterRate.FEW,
                EncounterRate.FEW, EncounterRate.FEW, 1, Color.AQUA);
        player.setPlanet(planet);
        shipyard = new Shipyard(planet);
        assertTrue("Planet should not be able to sell this ShipUpgrade",
                engine.buyShipUpgrade(gdt, shipyard).contains("Planet cannot sell this upgrade"));
    }

    @Test
    public void testTradeWithTrader() {
        try {
            Trader trader = new Trader(10, true);
            int quantity = trader.getQuantity();
            player.setShip(new Ship(ShipType.GNAT));
            String error = engine.tradeWithTrader(trader, quantity + 1).get(0);
            assertEquals("Trader will not " + (trader.isBuying() ? "buy" : "sell") + " this much "
                    + trader.getGoodOfInterest(), error);
            error = engine.tradeWithTrader(trader, quantity).get(0);
            assertEquals("You do not have " + quantity + " " + trader.getGoodOfInterest()
                    + " to sell", error);
            trader = new Trader(10, false);
            quantity = trader.getQuantity();
            player.decreaseCredits(player.getCredits());
            error = engine.tradeWithTrader(trader, quantity).get(0);
            assertEquals(
                    "You do not have enough credits to buy " + quantity + " "
                            + trader.getGoodOfInterest(), error);
            player.getShip().addToCargo(GoodType.FIREARMS, 15);
            player.increaseCredits(1000000);
            error = engine.tradeWithTrader(trader, quantity).get(0);
            assertEquals(
                    "Your ship cannot hold " + quantity + " more " + trader.getGoodOfInterest(),
                    error);
            player.getShip().removeFromCargo(GoodType.FIREARMS, 15);
            List<String> errors = engine.tradeWithTrader(trader, 1);
            assertTrue(errors.isEmpty());
        } catch (IndexOutOfBoundsException ie) {
            ie.printStackTrace();
            fail(ie.getMessage());
        }
    }
}