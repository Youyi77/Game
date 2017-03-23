package game.Map;

import game.Image.Content;
import java.awt.Graphics2D;

public class Map {

    private final int map[][];
    private final Tile[][] tiles;
    private final int tilesize;

    public Map(int[][] map) {

        this.map = map;
        tilesize = 50;
        tiles = new Tile[map.length][map[0].length];
        loadTiles();
    }

    public Tile getTile(int i, int j){
        return tiles[i][j];
    }
    
    private void loadTiles() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j]) {
                    case 0:
                        tiles[i][j] = new Tile(Content.GRASS, j * tilesize, i * tilesize,Tile.NORMAL);
                        break;
                    case 1:
                        tiles[i][j] = new Tile(Content.MUD, j * tilesize, i * tilesize,Tile.NORMAL);
                        break;
                    case 2:
                        tiles[i][j] = new Tile(Content.SAND, j * tilesize, i * tilesize,Tile.NORMAL);
                        break;
                    case 3:
                        tiles[i][j] = new Tile(Content.SMALLTREE, j * tilesize, i * tilesize,Tile.BLOCKED);
                        break;
                    case 4:
                        tiles[i][j] = new Tile(Content.BIGTREE, j * tilesize, i * tilesize,Tile.BLOCKED);
                        break;
                }
            }
        }
    }

    public void draw(Graphics2D g) {

        for (Tile[] tile : tiles) {
            for (int j = 0; j < tiles[0].length; j++) {
                if(tile[j].getType()==Tile.BLOCKED){
                    g.drawImage(Content.GRASS, tile[j].getX(), tile[j].getY(), tilesize, tilesize, null);
                }
                g.drawImage(tile[j].getImage(), tile[j].getX(), tile[j].getY(), tilesize, tilesize, null);
            }
        }
    }

}
