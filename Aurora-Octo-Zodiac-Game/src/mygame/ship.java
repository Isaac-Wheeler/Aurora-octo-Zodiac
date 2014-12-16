package mygame;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;

/**
 *
 * hanles a ship
 * 
 * @author IsaacWheeler
 */
public class ship {
    
    private ArrayList<Geometry> shipBlocks;
    private Node pivot;
    private Node rootNode;
    
    /**
     * intiliss the ship class
     * @param rootNode the display node of the game
     * @param cords the starting location of the ship center point
     */
    public ship(Node rootNode, float[] cords){
        shipBlocks= new ArrayList<Geometry>();
        this.rootNode = rootNode;
        pivot = new Node("pivot");
        pivot.setLocalTranslation(new Vector3f(cords[0],cords[1],cords[2]));
        this.rootNode.attachChild(pivot);
    }
    
    public Node getPivot(){
        return pivot;
    }
    
    public void updateShip(){
        for(int i = 0; i < shipBlocks.size(); i++){
            pivot.attachChild(shipBlocks.get(i));
        }
    }
    
    public void addBlock(Geometry block){
        shipBlocks.add(block);
    }
    
    public void rotateShip(float x, float y, float z){
        pivot.rotate(x, y, z);
    }
    
    public void moveRight(float speed, float value){
        Vector3f v = pivot.getLocalTranslation();
        pivot.move(v.x + value, v.y, v.z);
    }
    
    public void moveLeft(float speed, float value){
        Vector3f v = pivot.getLocalTranslation();
        pivot.move(v.x - value, v.y, v.z);
    }
    
    public void moveForward(float speed, float value){
        Vector3f v = pivot.getLocalTranslation();
        pivot.move(v.x , v.y, v.z + value * speed);
    }
}
