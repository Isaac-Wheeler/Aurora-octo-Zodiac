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
public class basicCube {

    private Box b;
    private Geometry geom;

    public basicCube(float cords[], ColorRGBA blockColor, AssetManager assetManager) {
        b = new Box(0.5f, 0.5f, 0.5f);

        geom = new Geometry("Box", b);
        geom.setLocalTranslation(new Vector3f(cords[0],cords[1],cords[2]));
        Material mat = new Material(assetManager,
          "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", blockColor);
        geom.setMaterial(mat);
    }

    public Geometry getGeom(){
        return geom;
    }
}

