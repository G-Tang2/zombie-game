package game;

public class Food extends PortableItem {

	private int nutrition = 20;

	public Food() {
		super("food", 'f');
		allowableActions.add(new Eat(this)); // FIXME: remove this allowable action when dropped on ground
	}

	public int getNutrition() {
		return nutrition;
	}

}
