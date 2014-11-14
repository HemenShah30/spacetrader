package controller;

import javafx.scene.paint.Color;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Location;
import model.NPCEncounter;
import model.Planet;
import model.Player;
import model.Police;
import model.Ship;
import model.enums.Condition;
import model.enums.EncounterRate;
import model.enums.EncounterType;
import model.enums.GoodType;
import model.enums.Government;
import model.enums.ShipType;
import model.enums.SpecialResource;
import model.enums.TechLevel;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for bribing the police in FightEngine
 * 
 * @author Larry He
 * 
 */
public class FightEngineTest {
    Planet planet;
    Ship ship;
    Player player;
    FightEngine engine;
    Police police;
    NPCEncounter encounter;

    @Before
    public void setUp() throws Exception {
        Location l = new Location(50, 50);
        planet = new Planet("test", TechLevel.HITECH, SpecialResource.DESERT, Government.DEMOCRACY,
                l, Condition.BOREDOM, EncounterRate.FEW, EncounterRate.FEW, EncounterRate.FEW, 1,
                Color.AQUA);
        ship = new Ship(ShipType.FLEA);
        player = new Player("Test", 5, 5, 5, 5, 5, ship);
        player.setPlanet(planet);
        player.increaseCredits(10000);
        engine = new FightEngine(player);
    }

    @Test
    public void acceptBribeTest() {
        police = new Police(player.getPoliceRep());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        assertTrue("Bribe failed when it should have succeeded",
                engine.bribePolice(encounter, 10000));
    }

    @Test
    public void rejectBribeTest() {
        police = new Police(player.getPoliceRep());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        assertFalse("Bribe succeeded when it should have failed", engine.bribePolice(encounter, 10));
    }

    @Test
    public void playerMaxRepTest() {
        player.setPoliceRep(99);
        police = new Police(player.getPoliceRep());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        engine.bribePolice(encounter, 10);
        // System.out.println(player.getPoliceRep());
        // assertFalse("Bribe succeeded when it should have failed",
        // engine.bribePolice(encounter, 10));
        assertEquals("Player police rep not set to highest possible rep", 100,
                player.getPoliceRep());
    }

    @Test
    public void playerIncreasedRepTest() {
        player.setPoliceRep(45);
        police = new Police(player.getPoliceRep());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        engine.bribePolice(encounter, 10);
        assertEquals("Player police rep not increased by correct amount", 50, player.getPoliceRep());
    }

    @Test
    public void noIllegalGoodsTest() {
        police = new Police(player.getPoliceRep());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        assertFalse("Search turned out positive when it should have been negative",
                engine.consentToSearch(encounter));
    }

    @Test
    public void carryingNarcoticsTest() {
        ship.addToCargo(GoodType.NARCOTICS, 1);
        police = new Police(player.getPoliceRep());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        assertTrue("Search turned out negative when it should have been positive",
                engine.consentToSearch(encounter));
    }

    @Test
    public void carryingFirearmsTest() {
        ship.addToCargo(GoodType.FIREARMS, 1);
        police = new Police(player.getPoliceRep());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        assertTrue("Search turned out negative when it should have been positive",
                engine.consentToSearch(encounter));
    }

    @Test
    public void MinPoliceRepTest() {
        player.setPoliceRep(1);
        police = new Police(player.getPoliceRep());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        engine.consentToSearch(encounter);
        assertFalse("Search turned out positive when it should have been negative",
                engine.consentToSearch(encounter));
        assertEquals("Player police rep not set to lowest possible rep", 1, player.getPoliceRep());
    }

    @Test
    public void IncreasePoliceRepTest() {
        player.setPoliceRep(50);
        ship.addToCargo(GoodType.NARCOTICS, 1);
        police = new Police(player.getPoliceRep());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        engine.consentToSearch(encounter);
        assertEquals("Player police rep not increased by correct amount", 55, player.getPoliceRep());
    }
}
