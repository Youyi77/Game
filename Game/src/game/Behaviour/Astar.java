package game.Behaviour;

import game.Map.Map;
import game.Map.Tile;
import java.util.ArrayList;

/**
 *
 * @author trifissy
 */
public class Astar {

    public Map map;
    private ArrayList<Tile> bestPath;

    public Astar(Map map) {
        this.map = map;
        this.bestPath = new ArrayList();
    }

    public ArrayList<Tile> getBestPath() {
        return bestPath;
    }

    public boolean shortestPath(Tile start, Tile end) {
        ArrayList<Tile> openlist = new ArrayList();
        ArrayList<Tile> closelist = new ArrayList();

        openlist.add(start);
        while (!openlist.isEmpty()) {
            Tile current = openlist.get(0);
            System.out.println(current.getRow());
            System.out.println(current.getCol());
            System.out.println("**********");
            if (current == null) {
                System.out.println("error");
                return false;
            }
            openlist.remove(current);
            if (current == end) {
                getPath(start,current);
                return true;
            }

            // NEIGHBORS
            ArrayList<Tile> neighbours = getNeighbours(current);
            for(Tile neighbour : neighbours)
            {
                if(neighbour.isBlocked() || closelist.contains(neighbour)){
                    // do nothing
                }
                
                int cost = current.getgCost() + getDistance(current, neighbour);
                
                if(cost<neighbour.getgCost() || !openlist.contains(neighbour)){
                    neighbour.setgCost(cost);
                    neighbour.sethCost(getDistance(neighbour,end));
                    neighbour.setfCost(neighbour.getgCost()+neighbour.gethCost());
                    
                    neighbour.setPredecessor(current);
                    
                    if(!openlist.contains(neighbour)){
                        openlist.add(neighbour);
                    }
                }
            }
            if(!openlist.contains(current)){
                closelist.add(current);
            }
        }
        return false;
    }

    /**
     * @return the distance between two tiles
     */
    public int getDistance(Tile start, Tile end) {
        return ((start.getX() - end.getX()) - (start.getY() - end.getY()));

    }

    public void getPath(Tile start, Tile end) {
        ArrayList<Tile> result = new ArrayList();
        Tile current = end;
        int count = 0;

        while (current != start && current.getPredecessor() != null) {
            count++;
            result.add(current);
            current = current.getPredecessor();
        }

        result.add(start);
        result = reverseList(result);
        this.bestPath = result;
    }

    public ArrayList<Tile> reverseList(ArrayList<Tile> initialList) {
        ArrayList<Tile> reversedList = new ArrayList();
        int lastTileIndex = initialList.size() - 1;
        for (int i = lastTileIndex; -1 < i; i--) {
            reversedList.add(initialList.get(i));
        }
        return reversedList;
    }

    public ArrayList<Tile> getNeighbours(Tile current) {
        ArrayList<Tile> neighbours = new ArrayList();
        if (current != null) {
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    if (x == 0 && y == 0) {
                        // do nothing
                    } else if (x == 0 || y == 0) {
                        int row = current.getRow();
                        int col = current.getCol();
                        Tile neighbour = map.getTile(row + x, col + y);
                        if (neighbour != null) {
                            neighbours.add(neighbour);
                        }
                    }

                }
            }
        }
        return neighbours;
    }
}
