package game.Map;

import game.Image.Content;
import java.awt.Graphics2D;

public class Map {

    private final int map[][];
    private final Tile[][] tiles;

    public Map(int[][] map) {

        this.map = map;
        tiles = new Tile[map.length][map[0].length];
        loadTiles();
    }

    public Tile getTile(int i, int j) {
        return tiles[i][j];
    }

    private void loadTiles() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j]) {
                    case 0:
                        tiles[i][j] = new Tile(Content.GRASS, j * Tile.tilesize, i * Tile.tilesize, false);
                        break;
                    case 1:
                        tiles[i][j] = new Tile(Content.MUD, j * Tile.tilesize, i * Tile.tilesize, false);
                        break;
                    case 2:
                        tiles[i][j] = new Tile(Content.SAND, j * Tile.tilesize, i * Tile.tilesize, false);
                        break;
                    case 3:
                        tiles[i][j] = new Tile(Content.SMALLTREE, j * Tile.tilesize, i * Tile.tilesize, true);
                        break;
                    case 4:
                        tiles[i][j] = new Tile(Content.BIGTREE, j * Tile.tilesize, i * Tile.tilesize, true);
                        break;
                }
            }
        }
    }

    public void draw(Graphics2D g) {

        for (Tile[] tile : tiles) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tile[j].isBlocked()) {
                    g.drawImage(Content.GRASS, tile[j].getX(), tile[j].getY(), Tile.tilesize, Tile.tilesize, null);
                }
                g.drawImage(tile[j].getImage(), tile[j].getX(), tile[j].getY(), Tile.tilesize, Tile.tilesize, null);
            }
        }
    }

}
