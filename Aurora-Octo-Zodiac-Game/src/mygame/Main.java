package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
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
    ship testShip;

    @Override
    public void simpleInitApp() {
        initCrossHairs();
        float[] cords = {0.5f, 0.5f, 0.5f};

        basicCube cube1 = new basicCube(cords, ColorRGBA.Blue, assetManager);

        cords[1] = -.5f;

        AmourCube amour1 = new AmourCube(cords, assetManager);

        cords[0] = -.5f;

        basicCube cube2 = new basicCube(cords, ColorRGBA.randomColor(), assetManager);

        float[] cords1 = {0,0,0};
        
        testShip = new ship(rootNode, cords1);

        testShip.addBlock(cube1.getGeom());
        testShip.addBlock(amour1.getGeom());
        testShip.addBlock(cube2.getGeom());
        
        testShip.updateShip();
        
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
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_L));
        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_I));
        inputManager.addMapping("Rotate", new KeyTrigger(KeyInput.KEY_SPACE),
                new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        // Add the names to the action listener.
        inputManager.addListener(analogListener, "Left", "Right", "Rotate");

    }
    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
            if (isRunning) {
                if (name.equals("Rotate")) {
                    testShip.rotateShip(0, value * speed, 0);
                }
                if (name.equals("Right")) {
                    testShip.moveRight(speed, value);
                }
                if (name.equals("Left")) {
                   testShip.moveLeft(speed, value);
                }
                if(name.equals("Forward")){
                    testShip.moveForward(speed, value);
                }
            } else {
                System.out.println("Press P to unpause.");
            }
        }
    };
        /** A centred plus sign to help the player aim. */
        protected void initCrossHairs() {
            setDisplayStatView(false);
            guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
            BitmapText ch = new BitmapText(guiFont, false);
            ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
            ch.setText("+"); // crosshairs
            ch.setLocalTranslation( // center
            settings.getWidth() / 2 - ch.getLineWidth()/2, settings.getHeight() / 2 + ch.getLineHeight()/2, 0);
            guiNode.attachChild(ch);
        }
}
