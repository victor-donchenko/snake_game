package snake_game;

import java.lang.Math;
import java.util.ArrayList;

import snake_game.Snake;
import snake_game.Apple;

class Game {
	private int grid_width;
	private int grid_height;
	private int num_apples;
	private Snake snake;
	private boolean game_finished;
	private ArrayList<Apple> apples;

	private void add_apple() {
		Apple new_apple = new Apple(
			this,
			new Coords2d(
				(int)(Math.random() * grid_width),
				(int)(Math.random() * grid_height)
			)
		);
		apples.add(new_apple);
	}

	public Game(int i_grid_width, int i_grid_height, int i_num_apples) {
		grid_width = i_grid_width;
		grid_height = i_grid_height;
		num_apples = i_num_apples;
		snake = new Snake(this, new Coords2d(0, 0), new Coords2d(1, 0));
		game_finished = false;
		apples = new ArrayList<Apple>();
		for (int i = 0; i < num_apples; ++i) {
			add_apple();
		}
	}

	private int get_apple_index_at(Coords2d position) {
		for (int i = 0; i < apples.size(); ++i) {
			if (apples.get(i).get_position().equals(position)) {
				return i;
			}
		}
		return -1;
	}

	public void update() {
		if (game_finished) {
			return;
		}
		snake.advance();
		Coords2d head_position = snake.get_head_position();
		int apple_index = get_apple_index_at(head_position);
		while (apple_index != -1) {
			snake.eat_apple();
			apples.remove(apple_index);
			add_apple();
			apple_index = get_apple_index_at(head_position);
		}	
		if (snake.intersects_self()) {
			game_finished = true;
			return;
		}
	}

	public Snake get_snake() {
		return snake;
	}

	public boolean get_game_finished() {
		return game_finished;
	}

	public ArrayList<Apple> get_apples() {
		return new ArrayList<Apple>(apples);
	}

	public int get_grid_width() {
		return grid_width;
	}

	public int get_grid_height() {
		return grid_height;
	}
}

