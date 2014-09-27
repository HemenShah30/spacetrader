package spaceTrader;

import model.Player;
import model.Ship;
import model.ShipType;
import model.Universe;

public class Test {

	public static void main(String[] args) {
		Universe u = new Universe();
		u.createPlanets();
		Ship ship = new Ship(ShipType.FLEA);
		Player p = new Player("Doctor", 10, 1, 1, 1, 1, ship);
		p.setPlanet(u.getPlanets().get(0));
		p.getPlanet().getMarketplace();
		
	}

}
