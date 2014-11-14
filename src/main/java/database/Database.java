package database;

import model.Enum.Condition;
import model.Enum.EncounterRate;
import model.Enum.GoodType;
import model.Enum.Government;
import model.Enum.LaserType;
import model.Enum.ShieldType;
import model.Enum.ShipType;
import model.Enum.SpecialResource;
import model.Enum.TechLevel;
import model.Gadget;
import model.GadgetFactory;
import model.Location;
import model.Planet;
import model.Player;
import model.Ship;
import model.Universe;

import org.postgresql.util.PGobject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javafx.scene.paint.Color;



/**
 * Class that handles all input and output to the database
 * 
 * @author Jack Croft
 *
 */
public class Database {
    private Connection connection;
    private String username;
    private Map<Integer, String> techLevelValues;
    private Map<String, String> specialResourceValues;
    private Map<String, String> governmentValues;
    private Map<String, String> encounterRateValues;
    private Map<String, String> conditionValues;
    private Map<String, String> goodTypeValues;
    private Map<String, String> shipTypeValues;
    private Map<String, String> laserTypeValues;
    private Map<String, String> shieldTypeValues;
    private Map<String, String> gadgetTypeValues;

    /**
     * Creates a new Database class and attempts to connect to the database
     * 
     * @param u
     *            The username of the person logging into the game
     */
    public Database(String username) {
        this.username = username;
        techLevelValues = new HashMap<Integer, String>();
        specialResourceValues = new HashMap<String, String>();
        governmentValues = new HashMap<String, String>();
        encounterRateValues = new HashMap<String, String>();
        conditionValues = new HashMap<String, String>();
        goodTypeValues = new HashMap<String, String>();
        shipTypeValues = new HashMap<String, String>();
        laserTypeValues = new HashMap<String, String>();
        shieldTypeValues = new HashMap<String, String>();
        gadgetTypeValues = new HashMap<String, String>();
        connect();
        createMaps();
    }

    /**
     * Attempts a connection to the database
     */
    private void connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver?"
                    + "Include in your library path!");
            e.printStackTrace();
        }

        // System.out.println("PostgreSQL JDBC Driver Registered!");

        try {
            connection = DriverManager
                    .getConnection(
                            "jdbc:postgresql://spacetraders.cucctpybeipt.us-west-2.rds."
                            + "amazonaws.com:5432/SpaceTraders",
                            "meep366", "chromium");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }

    /**
     * Creates the maps of enum values to UUIDs to be referenced in saving and loading later
     */
    private void createMaps() {
        try {
            Statement statement = connection.createStatement();
            ResultSet techLevels = statement
                    .executeQuery("SELECT \"TechLevelId\", \"TechLevelValue\" FROM \"TechLevel\"");
            while (techLevels.next()) {
                techLevelValues.put(techLevels.getInt("TechLevelValue"),
                        techLevels.getString("TechLevelId"));
            }

            ResultSet specialResources = statement
                    .executeQuery("SELECT \"SpecialResourceId\","
                            + " \"SpecialResourceName\"FROM \"SpecialResource\"");
            while (specialResources.next()) {
                specialResourceValues.put(specialResources.getString("SpecialResourceName"),
                        specialResources.getString("SpecialResourceId"));
            }

            ResultSet governments = statement
                    .executeQuery("SELECT \"GovernmentId\","
                            + " \"GovernmentName\" FROM \"Government\"");
            while (governments.next()) {
                governmentValues.put(governments.getString("GovernmentName"),
                        governments.getString("GovernmentId"));
            }

            ResultSet conditions = statement
                    .executeQuery("SELECT \"ConditionId\", \"ConditionName\" FROM \"Condition\"");
            while (conditions.next()) {
                conditionValues.put(conditions.getString("ConditionName"),
                        conditions.getString("ConditionId"));
            }

            ResultSet encounterRates = statement
                    .executeQuery("SELECT \"EncounterRateId\","
                            + " \"EncounterRateName\" FROM \"EncounterRate\"");
            while (encounterRates.next()) {
                encounterRateValues.put(encounterRates.getString("EncounterRateName"),
                        encounterRates.getString("EncounterRateId"));
            }

            ResultSet goodTypes = statement
                    .executeQuery("SELECT \"GoodTypeId\", \"GoodTypeName\" FROM \"GoodType\"");
            while (goodTypes.next()) {
                goodTypeValues.put(goodTypes.getString("GoodTypeName"),
                        goodTypes.getString("GoodTypeId"));
            }

            ResultSet shipTypes = statement
                    .executeQuery("SELECT \"ShipTypeId\", \"ShipTypeName\" FROM \"ShipType\"");
            while (shipTypes.next()) {
                shipTypeValues.put(shipTypes.getString("ShipTypeName"),
                        shipTypes.getString("ShipTypeId"));
            }

            ResultSet laserTypes = statement
                    .executeQuery("SELECT \"LaserId\", \"LaserName\" FROM \"Laser\"");
            while (laserTypes.next()) {
                laserTypeValues.put(laserTypes.getString("LaserName"),
                        laserTypes.getString("LaserId"));
            }

            ResultSet shieldTypes = statement
                    .executeQuery("SELECT \"ShieldId\", \"ShieldName\" FROM \"Shield\"");
            while (shieldTypes.next()) {
                shieldTypeValues.put(shieldTypes.getString("ShieldName"),
                        shieldTypes.getString("ShieldId"));
            }

            ResultSet gadgetTypes = statement
                    .executeQuery("SELECT \"GadgetId\", \"GadgetName\" FROM \"Gadget\"");
            while (gadgetTypes.next()) {
                gadgetTypeValues.put(gadgetTypes.getString("GadgetName"),
                        gadgetTypes.getString("GadgetId"));
            }

        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * Closes a connection with the database
     */
    public void close() {
        System.out.println("Closing Connection");
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Determines whether or not the given user exists in the database or not
     * 
     * @return Whether or not the user exists in the database already
     */
    public boolean userExists() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT \"UserId\""
                    + "FROM \"User\" WHERE \"Username\"='"
                    + username + "'");
            return resultset.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Determines whether or not a given player exists
     * 
     * @param playerName
     *            The name of the player
     * @return Whether or not the player is present in the database
     */
    public boolean playerExists(String playerName) {
        try {
            Statement statement = connection.createStatement();
            String playerQuery = "SELECT up.\"PlayerId\" FROM \"UserPlayers\" up INNER JOIN"
                    + "\"User\" u ON u.\"UserId\"=up.\"UserId\" WHERE u.\"Username\"='"
                    + username + "' AND up.\"PlayerName\"='" + playerName + "'";
            ResultSet resultset = statement.executeQuery(playerQuery);
            return resultset.next();
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return false;
    }

    /**
     * Saves the game data to the database
     * 
     * @param universe
     *            The universe to be saved
     * @param player
     *            The player to be saved
     */
    public void saveGame(Universe universe, Player player) {
        long startTime = System.nanoTime();
        if (userExists() && playerExists(player.getName())) {
            try {
                Statement playerShipStatment = connection.createStatement();
                String playerShipQuery = "SELECT up.\"PlayerId\", p.\"ShipId\""
                        + "FROM \"User\" u INNER JOIN \"UserPlayers\" up "
                        + "ON up.\"UserId\"=u.\"UserId\""
                        + "INNER JOIN \"Player\" p ON p.\"PlayerId\"=up.\"PlayerId\""
                        + "WHERE u.\"Username\"='"
                        + username + "' AND up.\"PlayerName\"='" + player.getName() + "'";
                ResultSet playerShipSet = playerShipStatment.executeQuery(playerShipQuery);
                playerShipSet.next();
                String playerId = playerShipSet.getString("PlayerId");
                

                ResultSet planetSet = playerShipStatment
                        .executeQuery("SELECT \"PlanetId\" FROM \"Planet\" WHERE \"PlanetName\"='"
                                + player.getPlanet().getName() + "'");
                planetSet.next();
                String planetId = planetSet.getString("PlanetId");
                playerShipStatment.execute("UPDATE \"Player\" SET \"PlanetId\"='" + planetId
                        + "' WHERE \"PlayerId\"='" + playerId + "'");

                connection.setAutoCommit(false);
                // for (Planet planet : universe.getPlanets()) {
                // update marketplace info here
                // }
                Statement statement = connection.createStatement();
                String updatePlayerCmd = "UPDATE \"Player\" SET \"PilotSkill\"="
                        + player.getPilotSkill() + ", \"FighterSkill\"=" + player.getFighterSkill()
                        + ", \"TraderSkill\"=" + player.getTraderSkill() + ", \"EngineerSkill\"="
                        + player.getEngineerSkill() + ", \"InvestorSkill\"="
                        + player.getInvestorSkill() + ", \"TraderReputation\"="
                        + player.getTraderRep() + ", \"PoliceReputation\"=" + player.getPoliceRep()
                        + ", \"PirateReputation\"=" + player.getPirateRep() + ", \"Credits\"="
                        + player.getCredits() + " WHERE \"PlayerId\"='" + playerId + "'";
                statement.execute(updatePlayerCmd);
                String shipId = playerShipSet.getString("ShipId");
                Ship ship = player.getShip();
                String updateShipCmd = "UPDATE \"Ship\" SET \"CurrentHullPoints\"="
                        + ship.getCurrHP()
                        + ", \"CurrentFuel\"="
                        + ship.getFuel()
                        + ", \"ShipTypeId\"=(SELECT \"ShipTypeId\" "
                        + "FROM \"ShipType\" WHERE \"ShipTypeName\"='"
                        + ship.getShipType().toString() + "')" + " WHERE \"ShipId\"='" + shipId
                        + "'";
                statement.execute(updateShipCmd);

                String deleteWeaponsCmd = "DELETE FROM \"ShipLasers\" WHERE \"ShipId\"='" + shipId
                        + "'";
                statement.execute(deleteWeaponsCmd);
                String deleteShieldsCmd = "DELETE FROM \"ShipShields\" WHERE \"ShipId\"='" + shipId
                        + "'";
                statement.execute(deleteShieldsCmd);
                String deleteGadgetsCmd = "DELETE FROM \"ShipGadgets\" WHERE \"ShipId\"='" + shipId
                        + "'";
                statement.execute(deleteGadgetsCmd);
                for (LaserType laser : ship.getLasers()) {
                    String insertWeaponsCmd = "INSERT INTO \"ShipLasers\" VALUES ('" + shipId
                            + "', '" + laserTypeValues.get(laser.toString()) + "')";
                    statement.execute(insertWeaponsCmd);
                }
                for (ShieldType shield : ship.getShields()) {
                    String insertShieldsCmd = "INSERT INTO \"ShipShields\" VALUES ('" + shipId
                            + "', '" + shieldTypeValues.get(shield.toString()) + "')";
                    statement.execute(insertShieldsCmd);
                }
                for (Gadget gadget : ship.getGadgets()) {
                    String insertGadgetsCmd = "INSERT INTO \"ShipGadgets\" VALUES ('" + shipId
                            + "', '" + gadgetTypeValues.get(gadget.toString()) + "')";
                    statement.execute(insertGadgetsCmd);
                }

                for (GoodType g : GoodType.values()) {
                    String updateShipCargoCmd = "UPDATE \"ShipCargo\" SET \"GoodTypeQuantity\"="
                            + ship.amountInCargo(g)
                            + " WHERE \"ShipId\"='"
                            + shipId
                            + "' AND \"GoodTypeId\" = (SELECT \"GoodTypeId\" FROM \"GoodType\""
                            + " WHERE \"GoodTypeName\"='"
                            + g.toString() + "')";
                    statement.execute(updateShipCargoCmd);
                }
                connection.commit();
                connection.setAutoCommit(true);

            } catch (SQLException s) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
                s.printStackTrace();
            }
        } else {
            try {
                connection.setAutoCommit(false);

                PreparedStatement shipInsertStatement = connection
                        .prepareStatement("INSERT INTO \"Ship\" VALUES(?, ?, ?, ?)");
                UUID shipUUID = UUID.randomUUID();
                PGobject shipUUIDObject = new PGobject();
                shipUUIDObject.setType("uuid");
                shipUUIDObject.setValue(shipUUID.toString());
                shipInsertStatement.setObject(1, shipUUIDObject);
                
                Ship ship = player.getShip();
                PGobject shipTypeUUIDObject = new PGobject();
                shipTypeUUIDObject.setType("uuid");
                shipTypeUUIDObject.setValue(shipTypeValues.get(ship.getShipType().toString()));
                shipInsertStatement.setObject(2, shipTypeUUIDObject);

                shipInsertStatement.setInt(3, ship.getCurrHP());
                shipInsertStatement.setInt(4, ship.getFuel());
                shipInsertStatement.execute();

                PreparedStatement shipLaserStatement = connection
                        .prepareStatement("INSERT INTO \"ShipLasers\" VALUES (?, ?)");
                for (LaserType laser : ship.getLasers()) {
                    shipLaserStatement.setObject(1, shipUUIDObject);
                    PGobject laserUUIDObject = new PGobject();
                    laserUUIDObject.setType("uuid");
                    laserUUIDObject.setValue(laserTypeValues.get(laser.toString()));
                    shipLaserStatement.setObject(2, laserUUIDObject);
                    shipLaserStatement.execute();
                }

                PreparedStatement shipShieldStatement = connection
                        .prepareStatement("INSERT INTO \"ShipShields\" VALUES (?, ?)");
                for (ShieldType shield : ship.getShields()) {
                    shipShieldStatement.setObject(1, shipUUIDObject);
                    PGobject shieldUUIDObject = new PGobject();
                    shieldUUIDObject.setType("uuid");
                    shieldUUIDObject.setValue(laserTypeValues.get(shield.toString()));
                    shipShieldStatement.setObject(2, shieldUUIDObject);
                    shipShieldStatement.execute();
                }

                PreparedStatement shipGadgetStatement = connection
                        .prepareStatement("INSERT INTO \"ShipGadgets\" VALUES (?, ?)");
                for (Gadget gadget : ship.getGadgets()) {
                    shipGadgetStatement.setObject(1, shipUUIDObject);
                    PGobject gadgetUUIDObject = new PGobject();
                    gadgetUUIDObject.setType("uuid");
                    gadgetUUIDObject.setValue(gadgetTypeValues.get(gadget.toString()));
                    shipGadgetStatement.setObject(2, gadgetUUIDObject);
                    shipGadgetStatement.execute();
                }

                for (GoodType g : GoodType.values()) {
                    PreparedStatement shipCargoInsertStatement = connection
                            .prepareStatement("INSERT INTO \"ShipCargo\" VALUES(?, ?, ?)");
                    shipCargoInsertStatement.setObject(1, shipUUIDObject);
                    PGobject shipGoodTypeUUIDObject = new PGobject();
                    shipGoodTypeUUIDObject.setType("uuid");
                    shipGoodTypeUUIDObject.setValue(goodTypeValues.get(g.toString()));
                    shipCargoInsertStatement.setObject(2, shipGoodTypeUUIDObject);
                    shipCargoInsertStatement.setInt(3, ship.amountInCargo(g));
                    shipCargoInsertStatement.execute();
                }

                PreparedStatement playerInsertStatement = connection
                        .prepareStatement("INSERT INTO \"Player\" VALUES(?, ?, ?, ?, ?,"
                                + "?, ?, ?, ?, ?, ?, ?, ?)");

                UUID playerUUID = UUID.randomUUID();
                PGobject playerUUIDObject = new PGobject();
                playerUUIDObject.setType("uuid");
                playerUUIDObject.setValue(playerUUID.toString());
                playerInsertStatement.setObject(1, playerUUIDObject);

                playerInsertStatement.setInt(2, player.getPilotSkill());
                playerInsertStatement.setInt(3, player.getFighterSkill());
                playerInsertStatement.setInt(4, player.getTraderSkill());
                playerInsertStatement.setInt(5, player.getEngineerSkill());
                playerInsertStatement.setInt(6, player.getInvestorSkill());
                playerInsertStatement.setInt(7, player.getTraderRep());
                playerInsertStatement.setInt(8, player.getPoliceRep());
                playerInsertStatement.setInt(9, player.getPirateRep());

                PGobject playerCreditsUUIDObject = new PGobject();
                playerCreditsUUIDObject.setType("numeric");
                playerCreditsUUIDObject.setValue(Double.toString(player.getCredits()));
                playerInsertStatement.setObject(10, playerCreditsUUIDObject);
                playerInsertStatement.setObject(11, shipUUIDObject);
                playerInsertStatement.setNull(12, Types.NULL);
                playerInsertStatement.setString(13, player.getName());
                playerInsertStatement.execute();

                PGobject userUUIDObject = new PGobject();
                userUUIDObject.setType("uuid");
                if (!userExists()) {
                    PreparedStatement userInsertStatement = connection
                            .prepareStatement("INSERT INTO \"User\" VALUES(?, ?, ?)");
                    UUID userUUID = UUID.randomUUID();
                    userUUIDObject.setValue(userUUID.toString());
                    userInsertStatement.setObject(1, userUUIDObject);
                    userInsertStatement.setString(2, username);
                    userInsertStatement.setString(3, "password");
                    userInsertStatement.execute();
                } else {
                    PreparedStatement getUserIdStatement = connection
                            .prepareStatement("SELECT \"UserId\" from \"User\" WHERE \"Username\"='"
                                    + username + "'");
                    ResultSet userIdSet = getUserIdStatement.executeQuery();
                    userIdSet.next();
                    userUUIDObject.setValue(userIdSet.getString("UserId"));
                }

                PreparedStatement userPlayersInsertStatement = connection
                        .prepareStatement("INSERT INTO \"UserPlayers\" VALUES(?, ?, ?)");
                userPlayersInsertStatement.setObject(1, userUUIDObject);
                userPlayersInsertStatement.setObject(2, playerUUIDObject);
                userPlayersInsertStatement.setString(3, player.getName());
                userPlayersInsertStatement.execute();

                PreparedStatement planetInsertStatement = connection
                        .prepareStatement("INSERT INTO \"Planet\" VALUES(?, ?, ?, ?, ?, "
                                + "?, ?, ?, ?, ?, ?, ?, ?)");
                PreparedStatement playerPlanetsInsertStatement = connection
                        .prepareStatement("INSERT INTO \"PlayerPlanets\" VALUES(?, ?)");
                List<Planet> planets = universe.getPlanets();
                for (Planet p : planets) {
                    UUID planetUUID = UUID.randomUUID();
                    PGobject planetUUIDObject = new PGobject();
                    planetUUIDObject.setType("uuid");
                    planetUUIDObject.setValue(planetUUID.toString());
                    planetInsertStatement.setObject(1, planetUUIDObject);

                    PGobject techLevelUUIDObject = new PGobject();
                    techLevelUUIDObject.setType("uuid");
                    techLevelUUIDObject.setValue(techLevelValues.get(p.getTechLevel().getValue()));
                    planetInsertStatement.setObject(2, techLevelUUIDObject);

                    PGobject resourceUUIDObject = new PGobject();
                    resourceUUIDObject.setType("uuid");
                    resourceUUIDObject.setValue(specialResourceValues.get(p.getResource()
                            .toString()));
                    planetInsertStatement.setObject(3, resourceUUIDObject);

                    PGobject governmentUUIDObject = new PGobject();
                    governmentUUIDObject.setType("uuid");
                    governmentUUIDObject.setValue(governmentValues
                            .get(p.getGovernment().toString()));
                    planetInsertStatement.setObject(4, governmentUUIDObject);

                    planetInsertStatement.setInt(5, p.getLocation().getX());
                    planetInsertStatement.setInt(6, p.getLocation().getY());

                    PGobject conditionUUIDObject = new PGobject();
                    conditionUUIDObject.setType("uuid");
                    conditionUUIDObject.setValue(conditionValues.get(p.getCondition().toString()));
                    planetInsertStatement.setObject(7, conditionUUIDObject);

                    PGobject policeEncounterUUIDObject = new PGobject();
                    policeEncounterUUIDObject.setType("uuid");
                    policeEncounterUUIDObject.setValue(encounterRateValues.get(p
                            .getPoliceEncounterRate().toString()));
                    planetInsertStatement.setObject(8, policeEncounterUUIDObject);

                    PGobject pirateEncounterUUIDObject = new PGobject();
                    pirateEncounterUUIDObject.setType("uuid");
                    pirateEncounterUUIDObject.setValue(encounterRateValues.get(p
                            .getPirateEncounterRate().toString()));
                    planetInsertStatement.setObject(9, pirateEncounterUUIDObject);

                    planetInsertStatement.setString(10, p.getName());

                    PGobject traderEncounterUUIDObject = new PGobject();
                    traderEncounterUUIDObject.setType("uuid");
                    traderEncounterUUIDObject.setValue(encounterRateValues.get(p
                            .getTraderEncounterRate().toString()));
                    planetInsertStatement.setObject(11, traderEncounterUUIDObject);

                    planetInsertStatement.setInt(12, p.getDiameter());
                    planetInsertStatement.setString(13, p.getColor().toString());

                    planetInsertStatement.execute();

                    playerPlanetsInsertStatement.setObject(1, playerUUIDObject);
                    playerPlanetsInsertStatement.setObject(2, planetUUIDObject);
                    playerPlanetsInsertStatement.execute();

                    // PreparedStatement marketplaceInsertStatement =
                    // connection.prepareStatement("INSERT INTO \"Marketplace\" VALUES(?, ?)");
                    // UUID marketplaceUUID = UUID.randomUUID();
                    // PGobject marketplaceUUIDObject = new PGobject();
                    // marketplaceUUIDObject.setType("uuid");
                    // marketplaceUUIDObject.setValue(marketplaceUUID.toString());
                    // marketplaceInsertStatement.setObject(1,
                    // marketplaceUUIDObject);
                    //
                    // marketplaceInsertStatement.setObject(2,
                    // planetUUIDObject);
                    // marketplaceInsertStatement.execute();
                    //
                    //
                    //
                    // Marketplace m = p.getMarketplace();
                    // for(GoodType g: GoodType.values()) {
                    // PreparedStatement marketplaceGoodsInsertStatement =
                    // connection.prepareStatement("INSERT INTO \"MarketplaceGoods\" VALUES(?, ?, "
                    // +"?, ?)");
                    // marketplaceGoodsInsertStatement.setObject(1,
                    // marketplaceUUIDObject);
                    // PGobject marketplaceGoodTypeUUIDObject = new PGobject();
                    // marketplaceGoodTypeUUIDObject.setType("uuid");
                    // marketplaceGoodTypeUUIDObject.setValue(goodTypeValues.get(g.toString()));
                    // marketplaceGoodsInsertStatement.setObject(2,
                    // marketplaceGoodTypeUUIDObject);
                    // marketplaceGoodsInsertStatement.setInt(3,
                    // m.getQuantity(g));
                    // marketplaceGoodsInsertStatement.setInt(4, (int)
                    // m.getSellPrice(g));
                    // marketplaceGoodsInsertStatement.execute();
                    // }

                }

                Statement statement = connection.createStatement();
                ResultSet planet = statement
                        .executeQuery("SELECT \"PlanetId\" FROM \"Planet\" WHERE \"PlanetName\"='"
                                + player.getPlanet().getName() + "'");
                planet.next();
                String planetUUID = planet.getString("PlanetId");
                statement.execute("UPDATE \"Player\" SET \"PlanetId\"='" + planetUUID
                        + "' WHERE \"PlayerId\"='" + playerUUID + "'");

                connection.commit();
                connection.setAutoCommit(true);

            } catch (SQLException s) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
                s.printStackTrace();
            }
        }

        System.out.println("Save Time: " + (System.nanoTime() - startTime) / 1000000000.);
    }

    /**
     * Gets the list of player names associated with a given user
     * 
     * @return The list of players that a user has
     */
    public List<String> getUserPlayers() {
        List<String> players = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            String playerQuery = "SELECT up.\"PlayerName\" FROM \"User\""
                    + "u INNER JOIN \"UserPlayers\" up ON u.\"UserId\"=up.\"UserId\""
                    + " WHERE u.\"Username\"='"
                    + username + "'";
            ResultSet playerSet = statement.executeQuery(playerQuery);
            while (playerSet.next()) {
                players.add(playerSet.getString("PlayerName"));
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }

        return players;
    }

    /**
     * Loads the users game from the database
     * 
     * @param playerName
     *            The name of the player being loaded into the game
     * @return An object[] of the universe and planets
     */
    public Object[] loadGame(String playerName) {
        long startTime = System.nanoTime();
        Player player = null;
        Universe universe = null;
        try {

            Statement statement = connection.createStatement();
            String execPlayerStatement = "SELECT p.\"PlayerId\", p.\"PilotSkill\","
                    + " p.\"FighterSkill\", p.\"TraderSkill\", p.\"EngineerSkill\","
                    + " p.\"InvestorSkill\", p.\"TraderReputation\", p.\"PoliceReputation\","
                    + " p.\"PirateReputation\", p.\"Credits\", s.\"ShipId\","
                    + " s.\"CurrentHullPoints\",  s.\"CurrentFuel\", st.\"ShipTypeName\","
                    + " plan.\"PlanetName\" FROM \"User\" u INNER JOIN \"UserPlayers\" up"
                    + " ON u.\"UserId\"=up.\"UserId\" INNER JOIN \"Player\" p"
                    + " ON p.\"PlayerId\"=up.\"PlayerId\" INNER JOIN \"Ship\" s"
                    + " ON p.\"ShipId\"=s.\"ShipId\" INNER JOIN \"ShipType\" st"
                    + " ON s.\"ShipTypeId\"=st.\"ShipTypeId\" INNER JOIN \"Planet\" plan"
                    + " ON p.\"PlanetId\"=plan.\"PlanetId\" WHERE u.\"Username\"='"
                    + username + "' AND up.\"PlayerName\"='" + playerName + "'";

            ResultSet playerInfo = statement.executeQuery(execPlayerStatement);
            playerInfo.next();
            String shipId = playerInfo.getString("ShipId");
            int shipFuel = playerInfo.getInt("CurrentFuel");
            int currentHullPoints = playerInfo.getInt("CurrentHullPoints");
            String playerPlanetName = playerInfo.getString("PlanetName");
            double credits = Double.valueOf(playerInfo.getString("Credits"));
            ShipType type = ShipType.valueOf(playerInfo.getString("ShipTypeName").toUpperCase());
            Ship ship = new Ship(type, currentHullPoints, shipFuel);
            String playerId = playerInfo.getString("PlayerId");
            player = new Player(playerName, playerInfo.getInt("PilotSkill"),
                    playerInfo.getInt("FighterSkill"), playerInfo.getInt("TraderSkill"),
                    playerInfo.getInt("EngineerSkill"), playerInfo.getInt("InvestorSkill"), ship,
                    credits, playerInfo.getInt("TraderReputation"),
                    playerInfo.getInt("PoliceReputation"), playerInfo.getInt("PirateReputation"));

            String shipLaserCmd = "SELECT \"LaserName\" FROM \"Laser\" l INNER JOIN \"ShipLasers\""
                    + " sl ON l.\"LaserId\"=sl.\"LaserId\" WHERE sl.\"ShipId\"='"
                    + shipId + "'";
            ResultSet lasers = statement.executeQuery(shipLaserCmd);
            while (lasers.next()) {
                ship.addLaser(LaserType.getEnum(lasers.getString("LaserName")));
            }
            String shipShieldCmd = "SELECT \"ShieldName\" FROM \"Shield\" s "
                    + "INNER JOIN \"ShipShields\""
                    + " ss ON s.\"ShieldId\"=ss.\"ShieldId\" WHERE ss.\"ShipId\"='"
                    + shipId + "'";
            ResultSet shields = statement.executeQuery(shipShieldCmd);
            while (shields.next()) {
                ship.addShield(ShieldType.getEnum(shields.getString("ShieldName")));
            }
            String shipGadgetCmd = "SELECT \"GadgetName\" FROM \"Gadget\" g "
                    + "INNER JOIN \"ShipGadgets\""
                    + " sg ON g.\"GadgetId\"=sg.\"GadgetId\" WHERE sg.\"ShipId\"='"
                    + shipId + "'";
            ResultSet gadgets = statement.executeQuery(shipGadgetCmd);
            while (gadgets.next()) {
                ship.addGadget(GadgetFactory.createGadget((gadgets.getString("GadgetName"))));
            }

            ResultSet shipCargo = statement
                    .executeQuery("SELECT goods.\"GoodTypeName\", cargo.\"GoodTypeQuantity\" "
                            + "FROM \"ShipCargo\" cargo INNER JOIN \"GoodType\" goods "
                            + "ON cargo.\"GoodTypeId\"=goods.\"GoodTypeId\" "
                            + "WHERE cargo.\"ShipId\"='"
                            + shipId + "'");

            while (shipCargo.next()) {
                ship.addToCargo(
                        GoodType.valueOf(shipCargo.getString("GoodTypeName").toUpperCase()),
                        shipCargo.getInt("GoodTypeQuantity"));
            }
            String execPlanetStatement = "SELECT p.\"PlanetName\", p.\"Radius\", p.\"Color\", "
                    + "tl.\"TechLevelName\", sr.\"SpecialResourceName\", g.\"GovernmentName\", "
                    + "p.\"LocationX\", p.\"LocationY\", c.\"ConditionName\", "
                    + "er.\"EncounterRateName\" AS \"PoliceEncounterRateId\", "
                    + "pirEr.\"EncounterRateName\" AS \"PirateEncounterRateId\", "
                    + "traEr.\"EncounterRateName\" AS \"TraderEncounterRateId\" "
                    + "FROM \"Planet\" p INNER JOIN \"PlayerPlanets\" pp "
                    + "ON p.\"PlanetId\"=pp.\"PlanetId\" INNER JOIN  \"TechLevel\" tl "
                    + "ON tl.\"TechLevelId\"=p.\"TechLevelId\" INNER JOIN \"SpecialResource\" sr "
                    + "ON sr.\"SpecialResourceId\"=p.\"SpecialResourceId\" "
                    + "INNER JOIN \"Government\" g ON g.\"GovernmentId\"=p.\"GovernmentId\" "
                    + "INNER JOIN \"Condition\" c ON c.\"ConditionId\"=p.\"ConditionId\" "
                    + "INNER JOIN \"EncounterRate\" er "
                    + "ON er.\"EncounterRateId\"=p.\"PoliceEncounterRateId\" "
                    + "INNER JOIN \"EncounterRate\" pirEr "
                    + "ON pirEr.\"EncounterRateId\"=p.\"PirateEncounterRateId\" "
                    + "INNER JOIN \"EncounterRate\" traEr "
                    + "ON traEr.\"EncounterRateId\"=p.\"TraderEncounterRateId\" "
                    + "WHERE pp.\"PlayerId\"='"
                    + playerId + "'";
            ResultSet planets = statement.executeQuery(execPlanetStatement);
            List<Planet> planetList = new ArrayList<Planet>();
            while (planets.next()) {
                String planetName = planets.getString("PlanetName");
                TechLevel techLevel = TechLevel.getEnum(planets.getString("TechLevelName")
                        .toUpperCase());
                SpecialResource resource = SpecialResource.getEnum(planets.getString(
                        "SpecialResourceName").toUpperCase());
                Government government = Government.valueOf(planets.getString("GovernmentName")
                        .toUpperCase());
                Location location = new Location(planets.getInt("LocationX"),
                        planets.getInt("LocationY"));
                Condition condition = Condition.getEnum(planets.getString("ConditionName")
                        .toUpperCase());
                EncounterRate pirates = EncounterRate.valueOf(planets.getString(
                        "PirateEncounterRateId").toUpperCase());
                EncounterRate police = EncounterRate.valueOf(planets.getString(
                        "PoliceEncounterRateId").toUpperCase());
                EncounterRate trader = EncounterRate.valueOf(planets.getString(
                        "TraderEncounterRateId").toUpperCase());
                int radius = planets.getInt("Radius");
                Color color = Color.valueOf(planets.getString("Color"));
                Planet planet = new Planet(planetName, techLevel, resource, government, location,
                        condition, pirates, police, trader, radius, color);
                planetList.add(planet);
                if (planet.getName().equalsIgnoreCase(playerPlanetName)) {
                    player.setPlanet(planet);
                }
            }
            universe = new Universe(planetList);

        } catch (SQLException s) {
            s.printStackTrace();
        }
        System.out.println("Load Time: " + (System.nanoTime() - startTime) / 1000000000.);
        return new Object[] { player, universe };
    }
}