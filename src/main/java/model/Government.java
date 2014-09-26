package model;

/**
 * Class representing the government of a planet
 * 
 * @author Caroline Zhang
 * 
 */
public enum Government {
	ANARCHY, CAPITALIST, COMMUNIST, CONFEDERACY, CORPORATE, CYBERNETIC, DEMOCRACY, DICTATORSHIP, FASCIST, FEUDAL, MILITARY, MONARCHY, PACIFIST, SOCIALIST, SATORI, TECHNOCRACY, THEOCRACY;

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
			return null;
		}
	}
}