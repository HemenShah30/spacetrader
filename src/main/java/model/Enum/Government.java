package model.Enum;

/**
 * Class representing the government of a planet
 * 
 * @author Caroline Zhang
 * 
 */
public enum Government {
    ANARCHY, CAPITALIST, COMMUNIST, CONFEDERACY, CORPORATE, CYBERNETIC, DEMOCRACY, DICTATORSHIP, 
    FASCIST, FEUDAL, MILITARY, MONARCHY, PACIFIST, SOCIALIST, SATORI, TECHNOCRACY, THEOCRACY;

    /**
     * Returns the factor to be multiplied to the minimumBribe of a police officer based off of
     * destination planet - ranges from 1.0 - 3.0
     * 
     * @return double representing bribe factor
     */
    public double getBribeFactor() {
        switch (this) {
        case ANARCHY:
            return 3.0;
        case CAPITALIST:
            return 1.3;
        case COMMUNIST:
            return 2.0;
        case CONFEDERACY:
            return 1.5;
        case CORPORATE:
            return 2.0;
        case CYBERNETIC:
            return 1.0;
        case DEMOCRACY:
            return 1.0;
        case DICTATORSHIP:
            return 2.5;
        case FASCIST:
            return 1.4;
        case FEUDAL:
            return 2.8;
        case MILITARY:
            return 3.0;
        case MONARCHY:
            return 1.0;
        case PACIFIST:
            return 1.0;
        case SOCIALIST:
            return 1.5;
        case SATORI:
            return 3.0;
        case TECHNOCRACY:
            return 1.0;
        case THEOCRACY:
            return 1.2;
        default:
            return 1.0;
        }
    }

    @Override
    public String toString() {
        switch (this) {
        case ANARCHY:
            return "Anarchy";
        case CAPITALIST:
            return "Capitalist";
        case COMMUNIST:
            return "Communist";
        case CONFEDERACY:
            return "Confederacy";
        case CORPORATE:
            return "Corporate";
        case CYBERNETIC:
            return "Cybernetic";
        case DEMOCRACY:
            return "Democracy";
        case DICTATORSHIP:
            return "Dictatorship";
        case FASCIST:
            return "Fascist";
        case FEUDAL:
            return "Feudal";
        case MILITARY:
            return "Military";
        case MONARCHY:
            return "Monarchy";
        case PACIFIST:
            return "Pacifist";
        case SOCIALIST:
            return "Socialist";
        case SATORI:
            return "Satori";
        case TECHNOCRACY:
            return "Technocracy";
        case THEOCRACY:
            return "Theocracy";
        default:
            return "";
        }
    }
}