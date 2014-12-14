package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;

/**
 * code testing
 *
 * @author normenhansen mage_dragon
 * @version 12.13.14
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    protected Geometry player;
    Boolean isRunning = true;

    @Override
    public void simpleInitApp() {
        float[] cords = {0.5f, 0.5f, 0.5f};

        basicCube cube1 = new basicCube(cords, ColorRGBA.Blue, assetManager);

        cords[1] = -.5f;

        AmourCube amour1 = new AmourCube(cords, assetManager);

        cords[0] = -.5f;

        basicCube cube2 = new basicCube(cords, ColorRGBA.randomColor(), assetManager);

        float[] cords2 = {0.5f,0.5f,0.5f};
        
        ship testShip = new ship(rootNode, cords2);

        testShip.addBlock(cube1.getGeom());
        testShip.addBlock(amour1.getGeom());
        testShip.addBlock(cube2.getGeom());
        
        testShip.updateShip();
        
        float[] cords1 = {1.5f,1.5f,.5f};
        AmourCube amour2 = new AmourCube(cords2, assetManager);
        player = amour2.getGeom();
        rootNode.attachChild(player);
        initKeys();
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    /**
     * Custom Keybinding: Map named actions to inputs.
     */
    private void initKeys() {
        // You can map one or several inputs to one named action
        inputManager.addMapping("Pause", new KeyTrigger(KeyInput.KEY_P));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping("Rotate", new KeyTrigger(KeyInput.KEY_SPACE),
                new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        // Add the names to the action listener.
        inputManager.addListener(actionListener, "Pause");
        inputManager.addListener(analogListener, "Left", "Right", "Rotate");

    }
    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("Pause") && !keyPressed) {
                isRunning = !isRunning;
            }
        }
    };
    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
            if (isRunning) {
                if (name.equals("Rotate")) {
                    player.rotate(0, value * speed, 0);
                }
                if (name.equals("Right")) {
                    Vector3f v = player.getLocalTranslation();
                    player.setLocalTranslation(v.x + value * speed, v.y, v.z);
                }
                if (name.equals("Left")) {
                    Vector3f v = player.getLocalTranslation();
                    player.setLocalTranslation(v.x - value * speed, v.y, v.z);
                }
            } else {
                System.out.println("Press P to unpause.");
            }
        }
    };
}
