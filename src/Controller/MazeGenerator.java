
package Controller;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MazeGenerator extends Canvas {
    
    private int width;
    private int height;
    private int tileSize;
    
    private int visitedCells;
    
    private int[] maze;
    private Stack <Point> mazeStack = new Stack();
    
    private static final int CELL_PATH_N = 0x01;
    private static final int CELL_PATH_E = 0x02;
    private static final int CELL_PATH_S = 0x04;
    private static final int CELL_PATH_W = 0x08;
    private static final int CELL_VISITED = 0x10;
    
    private Random rand = new Random();
    
    private BufferedImage background;
               
    public MazeGenerator(int width, int height){
        this.width = width;
        this.height = height;
        visitedCells = 0;
        tileSize = 20;
                
        maze = new int[width*height];
        
        for(int i=0; i<width*height; i++){
            maze[i] = 0x00;
        }
        
        mazeStack.push(new Point(0,0));
        maze[0] = CELL_VISITED;
        visitedCells++;
        
    }
    
    public void tick(){
        if(visitedCells < width*height){
                
                ArrayList<Integer> neighbours = new ArrayList<>();
                
                //north neighbour
                if(mazeStack.peek().y>0 && (maze[offset(0, -1)] & CELL_VISITED)==0)
                    neighbours.add(0);
                
                //east neighbour
                if(mazeStack.peek().x<width-1 && (maze[offset(1, 0)] & CELL_VISITED)==0)
                    neighbours.add(1);
                
                //south neighbour
                if(mazeStack.peek().y<height-1 && (maze[offset(0, 1)] & CELL_VISITED)==0)
                    neighbours.add(2);
                
                //west neighbour
                if(mazeStack.peek().x>0 && (maze[offset(-1, 0)] & CELL_VISITED)==0)
                    neighbours.add(3);
                
                if(!neighbours.isEmpty()){
                    
                    int nextCell = neighbours.get(rand.nextInt(neighbours.size()));
                    
                    switch (nextCell){
                        
                        case 0:
                            maze[offset(0, -1)] |= CELL_VISITED | CELL_PATH_S;
                            maze[offset(0, 0)] |= CELL_PATH_N;
                            mazeStack.push(new Point(mazeStack.peek().x + 0, mazeStack.peek().y - 1));
                            break;
                            
                        case 1:
                            maze[offset(1, 0)] |= CELL_VISITED | CELL_PATH_W;
                            maze[offset(0, 0)] |= CELL_PATH_E;
                            mazeStack.push(new Point(mazeStack.peek().x + 1, mazeStack.peek().y + 0));
                            break;
                            
                        case 2:
                            maze[offset(0, 1)] |= CELL_VISITED | CELL_PATH_N;
                            maze[offset(0, 0)] |= CELL_PATH_S;
                            mazeStack.push(new Point(mazeStack.peek().x + 0, mazeStack.peek().y + 1));
                            break;
                            
                        case 3:
                            maze[offset(-1, 0)] |= CELL_VISITED | CELL_PATH_E;
                            maze[offset(0, 0)] |= CELL_PATH_W;
                            mazeStack.push(new Point(mazeStack.peek().x - 1, mazeStack.peek().y + 0));
                            break;
                            
                    }
                    
                    visitedCells++;
                    
                }
                else
                    mazeStack.pop();
                            
        }
        
    }
    
    public void render(Graphics g){
        
        if(visitedCells < width*height){
            
        g.setColor(Color.black);
        g.fillRect(10, 10, width*tileSize, height*tileSize);
        
        for(int x=0; x<width; x++){
            for(int y=0; y<height; y++){
                
                int current = maze[y*width + x];
                
                for (int py = 0; py < tileSize; py++){
                    for (int px = 0; px < tileSize; px++){
                
                        if((current & CELL_VISITED)!=0){
                            g.setColor(Color.white);
                            g.fillRect(x*(tileSize+5) + px, y*(tileSize+5)+py, 15, 15);
                        }
                        else{
                            g.setColor(Color.blue);
                            g.fillRect(x*(tileSize+5) + px, y*(tileSize+5)+py, 15, 15);
                        }
                    }
                }
                
                for (int p = 0; p < tileSize; p++){
                
                    if((current & CELL_PATH_S)!=0){
                        g.setColor(Color.red);
                        g.fillRect(x*(tileSize+5) + p, y*(tileSize+5) + tileSize, 15, 15);
                    }

                    if((current & CELL_PATH_E)!=0){
                        g.setColor(Color.red);
                        g.fillRect(x*(tileSize+5) + tileSize, y*(tileSize+5) + p, 15, 15);
                    }
                }
                
            }
        }
                
                g.setColor(Color.green);
                
                for (int py = 0; py < tileSize; py++){
                    for (int px = 0; px < tileSize; px++){
                        g.fillRect(mazeStack.peek().x*(tileSize+5) + px, mazeStack.peek().y*(tileSize+5) + py, 15, 15);
                    }
                }
         
        }
    }
    
    private int offset(int x, int y){
        return (((mazeStack.peek().y+y)*width) + (mazeStack.peek().x+x));
    }
    
    public int[] getMaze(){
        return maze;
    }

    public static int getCELL_PATH_E() {
        return CELL_PATH_E;
    }

    public static int getCELL_PATH_S() {
        return CELL_PATH_S;
    }

    
}
