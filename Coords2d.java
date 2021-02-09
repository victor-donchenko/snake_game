package snake_game;

class Coords2d {
	public int x;
	public int y;

	public Coords2d(int i_x, int i_y) {
		x = i_x;
		y = i_y;
	}

	public Coords2d(Coords2d other) {
		x = other.x;
		y = other.y;
	}

	public Coords2d clone() {
		return new Coords2d(x, y);
	}

	public Coords2d add(Coords2d other) {
		return new Coords2d(x + other.x, y + other.y);
	}

	public Coords2d subtract(Coords2d other) {
		return new Coords2d(x - other.x, y - other.y);
	}

	public boolean equals(Coords2d other) {
		return x == other.x && y == other.y;
	}
}

