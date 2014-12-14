package mygame;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;

/**
 *
 * @author IsaacWheeler
 */
public class ship {
    
    private ArrayList<Geometry> shipBlocks;
    private Node pivot;
    private Node rootNode;
    
    public ship(Node rootNode, float[] cords){
        this.rootNode = rootNode;
        pivot = new Node("pivot");
        pivot.setLocalTranslation(new Vector3f(cords[0],cords[1],cords[2]));
        this.rootNode.attachChild(pivot);
    }
    
    public void updateShip(){
        for(int i = 0; i < shipBlocks.size(); i++){
            pivot.attachChild(shipBlocks.get(i));
        }
    }
    
    public void addBlock(Geometry block){
        shipBlocks.add(block);
    }
}
