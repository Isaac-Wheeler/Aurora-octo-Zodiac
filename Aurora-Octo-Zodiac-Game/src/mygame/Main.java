package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void simpleInitApp() {
        float[] size = {1,1,1};
        float[] cords = {1,1,1};
        
        basicCube cube1 = new basicCube(cords ,size, assetManager);

        cords[1] = -2;
        
        basicCube cube2 = new basicCube(cords, size, assetManager);
        
        cords[0] = -2;
        
        basicCube cube3 = new basicCube(cords, size, assetManager);
        
        rootNode.attachChild(cube1.getGeom());
        rootNode.attachChild(cube2.getGeom());
        rootNode.attachChild(cube3.getGeom());
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
