package snake_game;

import java.util.ArrayList;

import snake_game.Game;
import snake_game.Coords2d;

class Snake {
	private Game game;
	private ArrayList<Coords2d> vertices;
	private ArrayList<Coords2d> directions;
	private boolean will_expand;

	public Snake(Game i_game, Coords2d i_position, Coords2d i_direction) {
		game = i_game;
		vertices = new ArrayList<Coords2d>();
		directions = new ArrayList<Coords2d>();
		will_expand = false;
		vertices.add(i_position.clone());
		vertices.add(i_position.clone());
		directions.add(i_direction.clone());
		directions.add(i_direction.clone());
	}

	public void turn_to(Coords2d direction) {
		if (!direction.equals(directions.get(0))) {
			if (vertices.size() == 2 && vertices.get(0).equals(vertices.get(1))) {
				directions.set(0, direction.clone());
				directions.set(1, direction.clone());
			}
			else {
				vertices.add(0, vertices.get(0));
				directions.add(0, direction.clone());
				directions.set(1, direction.clone());
			}
		}
	}

	private static int nonnegative_modulus(int a, int b) {
		int out = a % b;
		if (out < 0) {
			out += b;
		}
		return out;
	}

	public Coords2d loop_around_point(Coords2d point) {
		return new Coords2d(
			nonnegative_modulus(point.x, game.get_grid_width()),
			nonnegative_modulus(point.y, game.get_grid_height())
		);
	}

	public void advance() {
		int last_index = vertices.size() - 1;
		vertices.set(
			0,
			loop_around_point(
				vertices.get(0).add(directions.get(0))
			)
		);
		if (will_expand) {
			will_expand = false;
			return;
		}
		vertices.set(
			last_index,
			loop_around_point(
				vertices.get(last_index).add(directions.get(last_index))
			)
		);
		if (vertices.get(last_index).equals(vertices.get(last_index - 1))
			&& vertices.size() > 2) {
			vertices.remove(last_index);
			directions.remove(last_index);
		}
	}

	public void eat_apple() {
		will_expand = true;
	}

	private static boolean point_in_between_y(
		Coords2d ep1,
		Coords2d ep2,
		Coords2d point,
		Coords2d direction
	) {
		float a = Math.signum(point.y - ep2.y);
		float b = Math.signum(ep1.y - point.y);
		float c = Math.signum(ep1.y - ep2.y);
		return point.x == ep1.x
		       && point.x == ep2.x
			   && ((a == direction.y || a == 0)
			   	   ^ (b == direction.y || b == 0)
				   ^ (c == direction.y));
	}

	private static Coords2d switch_x_and_y(Coords2d point) {
		return new Coords2d(point.y, point.x);
	}

	private static boolean point_in_between(
		Coords2d ep1,
		Coords2d ep2,
		Coords2d point,
		Coords2d direction
	) {
		return point_in_between_y(ep1, ep2, point, direction)
		       || point_in_between_y(
			       switch_x_and_y(ep1),
				   switch_x_and_y(ep2),
				   switch_x_and_y(point),
				   switch_x_and_y(direction)
			   );
	}

	public boolean intersects_self() {
		Coords2d head_position = vertices.get(0);
		for (int i = 1; i < vertices.size() - 1; ++i) {
			Coords2d vertex1 = vertices.get(i);
			Coords2d vertex2 = vertices.get(i + 1);
			Coords2d direction = directions.get(i + 1);
			if (point_in_between(vertex1, vertex2, head_position, direction)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Coords2d> get_vertices() {
		return new ArrayList<Coords2d>(vertices);
	}

	public ArrayList<Coords2d> get_directions() {
		return new ArrayList<Coords2d>(directions);
	}

	public Coords2d get_head_position() {
		return vertices.get(0);
	}
}
		
