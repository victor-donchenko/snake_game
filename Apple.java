package snake_game;

import snake_game.Coords2d;
import snake_game.Game;

class Apple {
	private Coords2d position;

	public Apple(Game game, Coords2d i_position) {
		position = i_position;
	}

	public Coords2d get_position() {
		return position;
	}
}

