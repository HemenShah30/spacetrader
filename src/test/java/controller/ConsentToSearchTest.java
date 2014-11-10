package controller;

import static org.junit.Assert.*;
import model.NPCEncounter;
import model.Player;
import model.Police;
import model.Ship;
import model.Enum.EncounterType;
import model.Enum.GoodType;
import model.Enum.ShipType;

import org.junit.Before;
import org.junit.Test;

import controller.FightEngine;

/**
 * JUnit test for bribing the police in FightEngine
 * 
 * @author Caroline Zhang
 * 
 */
public class ConsentToSearchTest {
	Ship ship;
    Player player;
    FightEngine engine;
    Police police;
    NPCEncounter encounter;
    @Before
    public void setUp() throws Exception {
        ship = new Ship(ShipType.FLEA);
        player = new Player("Test", 5, 5, 5, 5, 5, ship);
        player.increaseCredits(10000);
        engine = new FightEngine(player);
        police = new Police(player.getPoliceRep(), player.getCredits());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
    }

	@Test
    public void noIllegalGoodsTest() {
        assertFalse("Search turned out positive when it should have been negative", engine.consentToSearch(encounter));
    }
    
    @Test
    public void carryingIllegalGoodsTest() {
        ship.addToCargo(GoodType.NARCOTICS, 1);
        assertTrue("Search turned out negative when it should have been positive", engine.consentToSearch(encounter));
    }
    
    @Test
    public void playerMinRepTest() {
        player.setPoliceRep(1);
        police = new Police(player.getPoliceRep(), player.getCredits());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        engine.consentToSearch(encounter);
        assertFalse("Search turned out positive when it should have been negative", engine.consentToSearch(encounter));
        assertEquals("Player police rep not set to lowest possible rep", 1, player.getPoliceRep());
    }
    
    @Test
    public void playerMaxRepTest() {
    	ship.addToCargo(GoodType.NARCOTICS, 1);
        player.setPoliceRep(100);
        police = new Police(player.getPoliceRep(), player.getCredits());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        engine.consentToSearch(encounter);
        assertEquals("Player police rep not set to highest possible rep", 100, player.getPoliceRep());
    }
    
    @Test
    public void playerIncreasedRepTest() {
    	player.setPoliceRep(50);
    	ship.addToCargo(GoodType.NARCOTICS, 1);
        police = new Police(player.getPoliceRep(), player.getCredits());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        engine.consentToSearch(encounter);
        assertEquals("Player police rep not increased by correct amount", 55, player.getPoliceRep());
    }
    
    @Test
    public void playerDecreasedRepTest() {
    	player.setPoliceRep(50);
        police = new Police(player.getPoliceRep(), player.getCredits());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        engine.consentToSearch(encounter);
        assertEquals("Player police rep not increased by correct amount", 45, player.getPoliceRep());
    }

}
