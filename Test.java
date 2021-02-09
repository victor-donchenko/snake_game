package snake_game;

import java.util.ArrayList;
import java.util.Scanner;

import snake_game.Game;

class Test {
	public static void print_game(Game game) {
		ArrayList<Coords2d> vertices = game.get_snake().get_vertices();
		ArrayList<Coords2d> directions = game.get_snake().get_directions();

		for (int i = 0; i < vertices.size(); ++i) {
			System.out.print(vertices.get(i).x);
			System.out.print(" ");
			System.out.print(vertices.get(i).y);
			System.out.print(" ");
			System.out.print(directions.get(i).x);
			System.out.print(" ");
			System.out.print(directions.get(i).y);
			System.out.println();
		}

		char[][] characters = new char[game.get_grid_width()][game.get_grid_height()];
		for (int x = 0; x < game.get_grid_width(); ++x) {
			for (int y = 0; y < game.get_grid_height(); ++y) {
				characters[x][y] = '.';
			}
		}
		for (Apple apple : game.get_apples()) {
			Coords2d position = apple.get_position();
			characters[position.x][position.y] = 'a';
		}
		for (int i = 0; i < vertices.size() - 1; ++i) {
			Coords2d vertex1 = vertices.get(i);
			Coords2d vertex2 = vertices.get(i + 1);
			Coords2d direction = directions.get(i + 1);
			for (Coords2d current_vertex = vertex2.clone();
					!current_vertex.equals(vertex1);
					current_vertex = game.get_snake().loop_around_point(
						current_vertex.add(direction))
					) {
				characters[current_vertex.x][current_vertex.y] = 's';
			}
			characters[vertex1.x][vertex1.y] = 's';
		}

		for (int r = 0; r < game.get_grid_height(); ++r) {
			for (int c = 0; c < game.get_grid_width(); ++c) {
				int x = c;
				int y = game.get_grid_height() - 1 - r;
				System.out.print(characters[x][y]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Game game = new Game(80, 30, 3);

		Scanner scanner = new Scanner(System.in);
		while (!game.get_game_finished()) {
			print_game(game);
			String line = scanner.nextLine();
			if (line.equals("u")) {
				game.get_snake().turn_to(new Coords2d(
					0,
					1
				));
			}
			if (line.equals("j")) {
				game.get_snake().turn_to(new Coords2d(
					0,
					-1
				));
			}
			if (line.equals("h")) {
				game.get_snake().turn_to(new Coords2d(
					-1,
					0
				));
			}
			if (line.equals("k")) {
				game.get_snake().turn_to(new Coords2d(
					1,
					0
				));
			}
			if (line.equals(".")) {
				System.out.println("Skipping turn.");
			}
			game.update();
		}
	}
}

