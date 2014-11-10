package controller;

import static org.junit.Assert.*;

import model.NPCEncounter;
import model.Player;
import model.Police;
import model.Ship;
import model.Enum.EncounterType;
import model.Enum.ShipType;

import org.junit.Before;
import org.junit.Test;

import controller.FightEngine;

/**
 * JUnit test for bribing the police in FightEngine
 * 
 * @author Larry He
 * 
 */
public class FightEngineTest {
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
    }
    
    @Test
    public void acceptBribeTest() {
        police = new Police(player.getPoliceRep(), player.getCredits());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        assertTrue("Bribe failed when it should have succeeded", engine.bribePolice(encounter, 10000));
    }
    
    @Test
    public void rejectBribeTest() {
        police = new Police(player.getPoliceRep(), player.getCredits());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        assertFalse("Bribe succeeded when it should have failed", engine.bribePolice(encounter, 10));
    }
    
    @Test
    public void playerMaxRepTest() {
        player.setPoliceRep(99);
        police = new Police(player.getPoliceRep(), player.getCredits());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        engine.bribePolice(encounter, 10);
        //System.out.println(player.getPoliceRep());
        //assertFalse("Bribe succeeded when it should have failed", engine.bribePolice(encounter, 10));
        assertEquals("Player police rep not set to highest possible rep", 100, player.getPoliceRep());
    }
    
    @Test
    public void playerIncreasedRepTest() {
        player.setPoliceRep(45);
        police = new Police(player.getPoliceRep(), player.getCredits());
        encounter = new NPCEncounter(EncounterType.POLICE, police);
        engine.bribePolice(encounter, 10);
        assertEquals("Player police rep not increased by correct amount", 50, player.getPoliceRep());
    }
}
