//Gabe Alonso
//D Block Data Stuctures
//TileManager manages the Tiles on screen, changing aspects such as order, location,
//or whether or not it exists

import java.util.*;
import java.awt.*;
public class TileManager
{
    ArrayList<Tile> tileList = new ArrayList<Tile>();
    public TileManager()
    {
    }
    //Added Methods
    //rectIn: Checks if a Tile's area covers the location of a mouse click
    //and returns true if it does
    private boolean rectIn(int x, int y, int ind)
    {
        //Checks if the area of the Tile covers the x-coord of the mouseclick
        if(tileList.get(ind).getX() <= x &&
        x <= tileList.get(ind).getX() + tileList.get(ind).getWidth())
        {
            //Checks if the area of the Tile covers the y-coord of the mouseclick
            if(tileList.get(ind).getY() <= y && 
            y <= tileList.get(ind).getY() + tileList.get(ind).getHeight())
            {
                return true;
            }
        }
        return false;
    }
    //Assignment Methods
    //addTile: Adds a tile to the tileList ArrayList
    public void addTile(Tile rect)
    {
        tileList.add(0, rect);
    }
    //drawAll: Draws all tiles in the tileList ArrayList onto the Drawing Pad
    public void drawAll(Graphics g)
    {
        for (int i = tileList.size() - 1; i >= 0; i--)
        {
            tileList.get(i).draw(g);
        }
    }
    //raise: Selects the topmost Tile in the ArrayList at a certain mouseclick coordinate
    //(takes in int x and int y as coords) and brings it to the top of the arraylist
    public void raise(int x, int y)
    {
        for (int i = 0; i < tileList.size(); i++)
        {
            //If the mouseclick is in the area of the Tile, the Tile is copied, removed,
            //and then re-added at the begining of the ArrayList
            if (rectIn(x, y, i)){
                Tile tile = tileList.get(i);
                tileList.remove(i);
                tileList.add(0, tile);
                break;
            }
        }
    }
    //lower: Selects the topmost Tile in the ArrayList at a certain mouseclick coordinate
    //(takes in int x and int y as coords) and brings it to the bottom of the arraylist
    public void lower(int x, int y)
    {
        for (int i = 0; i < tileList.size(); i++)
        {
            //If the mouseclick is in the area of the Tile, the Tile is copied, removed,
            //and then re-added at the end of the ArrayList
            if (rectIn(x, y, i)){
                Tile tile = tileList.get(i);
                tileList.remove(i);
                tileList.add(tile);
                break;
            }
        }
    }
    //delete: Selects the topmost Tile in the ArrayList at a certain mouseclick coordinate
    //(takes in int x and int y as coords) and deletes it
    public void delete(int x, int y)
    {
        for (int i = 0; i < tileList.size(); i++)
        {
            //If the mouseclick is in the area of the Tile, the topmost Tile is removed
            if (rectIn(x, y, i)){
                tileList.remove(i);
                break;
            }
        }
    }
    //deleteAll: Selects all Tiles in the ArrayList at a certain mouseclick coordinate
    //(takes in int x and int y as coords) and deletes them
    public void deleteAll(int x, int y)
    {
        //If the mouseclick is in the area of the Tile, the Tile is removed
        //This applies to any Tile where the mouseclick touches
        for (int i = tileList.size() - 1; i >= 0; i--)
        {
            //If the mouseclick is in the area of the Tile, the Tile is removed
            if (rectIn(x, y, i)){
                tileList.remove(i);
            }
        }
    }
    //shuffle: Shuffles the elements of the arraylist and places each tile in a random location
    //based on whether or not it would still fit on screen (takes in int width and int height as
    //screen boundaries)
    public void shuffle(int width, int height)
    {
        Collections.shuffle(tileList);
        for (int i = tileList.size() - 1; i >= 0; i--)
        {
            tileList.get(i).setX((int)(Math.random() * (double)(width - tileList.get(i).getWidth())));
            tileList.get(i).setY((int)(Math.random() * (double)(height - tileList.get(i).getHeight())));
        }
    }
}