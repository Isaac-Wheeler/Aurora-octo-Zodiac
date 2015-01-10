/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author IsaacWheeler
 */
public class boundingBox {
    
    private Box b;
    private Geometry geom;

    public boundingBox(Vector3f cords, ColorRGBA blockColor, AssetManager assetManager) {
        b = new Box(0.5f, 0.5f, 0.5f);

        geom = new Geometry("Box", b);
        geom.setLocalTranslation(cords);
        Material mat = new Material(assetManager,
          "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", blockColor);
        geom.setMaterial(mat);
    }

    public Geometry getGeom(){
        return geom;
    }
    
    public void updateLocation(Vector3f cords){
         geom.move(cords.x, cords.y, cords.z);
    }
}
