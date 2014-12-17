package mygame;

import com.jme3.renderer.RenderManager;
import com.jme3.input.controls.AnalogListener;
import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

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

        float[] cords1 = {0, 0, 0};

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
        inputManager.addMapping("Rotate", new KeyTrigger(KeyInput.KEY_SPACE));
        // Add the names to the action listener.
        inputManager.addListener(analogListener, "Left", "Right", "Rotate");
        inputManager.addMapping("Shoot",
                new KeyTrigger(KeyInput.KEY_SPACE), // trigger 1: spacebar
                new MouseButtonTrigger(MouseInput.BUTTON_LEFT)); // trigger 2: left-button click
        inputManager.addListener(actionListener, "Shoot");
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
                if (name.equals("Forward")) {
                    testShip.moveForward(speed, value);
                }
            } else {
                System.out.println("Press P to unpause.");
            }
        }
    };

    /**
     * A centred plus sign to help the player aim.
     */
    protected void initCrossHairs() {
        setDisplayStatView(false);
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText ch = new BitmapText(guiFont, false);
        ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        ch.setText("+"); // crosshairs
        ch.setLocalTranslation( // center
                settings.getWidth() / 2 - ch.getLineWidth() / 2, settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
        guiNode.attachChild(ch);
    }
    /**
     * Defining the "Shoot" action: Determine what was hit and how to respond.
     */
    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("Shoot") && !keyPressed) {
                // 1. Reset results list.
                CollisionResults results = new CollisionResults();
                // 2. Aim the ray from cam loc to cam direction.
                Ray ray = new Ray(cam.getLocation(), cam.getDirection());
                // 3. Collect intersections between Ray and Shootables in results list.
                testShip.getPivot().collideWith(ray, results);
                // 4. Print the results
                System.out.println("----- Collisions? " + results.size() + "-----");
                for (int i = 0; i < results.size(); i++) {
                    // For each hit, we know distance, impact point, name of geometry.
                    float dist = results.getCollision(i).getDistance();
                    Vector3f pt = results.getCollision(i).getContactPoint();
                    String hit = results.getCollision(i).getGeometry().getName();
                    System.out.println("* Collision #" + i);
                    System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
                }
                // 5. Use the results (we mark the hit object)
                if (results.size() > 0) {
                    // The closest collision point is what was truly hit:
                    CollisionResult closest = results.getClosestCollision();
                    Vector3f cords = closest.getContactPoint();
                    float[] cord = {cords.x, cords.y, cords.z};
                    //detects which side the block is on -Isaac
                    if (cord[0] > closest.getGeometry().getLocalTranslation().x) {
                        
                        float[] blockLocation = {closest.getGeometry().getLocalTranslation().x + 1f, closest.getGeometry().getLocalTranslation().y, closest.getGeometry().getLocalTranslation().z};
                        basicCube cube2 = new basicCube(blockLocation, ColorRGBA.randomColor(), assetManager);
                        testShip.addBlock(cube2.getGeom());
                        testShip.updateShip();
                    } else if (cord[0] < closest.getGeometry().getLocalTranslation().x) {
                        
                        float[] blockLocation = {closest.getGeometry().getLocalTranslation().x - 1f, closest.getGeometry().getLocalTranslation().y, closest.getGeometry().getLocalTranslation().z};
                        basicCube cube2 = new basicCube(blockLocation, ColorRGBA.randomColor(), assetManager);
                        testShip.addBlock(cube2.getGeom());
                        testShip.updateShip();

                    } else if (cord[1] > closest.getGeometry().getLocalTranslation().y) {
                        float[] blockLocation = {closest.getGeometry().getLocalTranslation().x, closest.getGeometry().getLocalTranslation().y + 1f, closest.getGeometry().getLocalTranslation().z};
                        basicCube cube2 = new basicCube(blockLocation, ColorRGBA.randomColor(), assetManager);
                        testShip.addBlock(cube2.getGeom());
                        testShip.updateShip();

                    } else if (cord[1] < closest.getGeometry().getLocalTranslation().y) {
                        
                        float[] blockLocation = {closest.getGeometry().getLocalTranslation().x, closest.getGeometry().getLocalTranslation().y - 1f, closest.getGeometry().getLocalTranslation().z};
                        basicCube cube2 = new basicCube(blockLocation, ColorRGBA.randomColor(), assetManager);
                        testShip.addBlock(cube2.getGeom());
                        testShip.updateShip();

                    } else if (cord[2] > closest.getGeometry().getLocalTranslation().z) {
                        
                        float[] blockLocation = {closest.getGeometry().getLocalTranslation().x , closest.getGeometry().getLocalTranslation().y , closest.getGeometry().getLocalTranslation().z + 1f}; 
                        basicCube cube2 = new basicCube(blockLocation, ColorRGBA.randomColor(), assetManager);
                        testShip.addBlock(cube2.getGeom());
                        testShip.updateShip();
                    } else if (cord[2] < closest.getGeometry().getLocalTranslation().z) {
                        
                        float[] blockLocation = {closest.getGeometry().getLocalTranslation().x , closest.getGeometry().getLocalTranslation().y , closest.getGeometry().getLocalTranslation().z - 1f}; 
                        basicCube cube2 = new basicCube(blockLocation, ColorRGBA.randomColor(), assetManager);
                        testShip.addBlock(cube2.getGeom());
                        testShip.updateShip();
                    }
                    /*
                     basicCube cube2 = new basicCube(cord, ColorRGBA.randomColor(), assetManager);
                     testShip.addBlock(cube2.getGeom());
                     testShip.updateShip();
                     */
                } else {
                }
            }
        }
    };
}
