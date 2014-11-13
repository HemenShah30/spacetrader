package model;

public interface ShipUpgrade {

    /**
     * 
     * @return
     */
    double getPrice();

    /**
     * 
     * @param ship
     * @return
     */
    boolean canBuy(Ship ship);

    /**
     * 
     * @param ship
     * @return
     */
    boolean canSell(Ship ship);

    /**
     * 
     * @return
     */
    int getMinTechLevel();

    /**
     * 
     * @return
     */
    int getType();
}
