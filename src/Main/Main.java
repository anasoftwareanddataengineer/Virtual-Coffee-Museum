/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Models.Model;
import Light.*;

import java.util.*;
import Models.ModelsRenderer;
import Shaders.TexturedShader;
import Shaders.ShaderProgram;
import Shaders.TerrainShader;
import Terrain.Terrain;
import Texturing.Texture;
import Camera.Camera;
import Camera.Camera2;
import Utils.ApplicationEventsManager;
import Utils.Constants;
import Utils.DisplayManager;
import Utils.ObjectLoader;
import Walls.Walls;
//import Walls.Walls2;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.lwjgl.input.Keyboard;
import static org.lwjgl.input.Keyboard.KEY_ESCAPE;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.Dimension;
import static sun.jvm.hotspot.oops.CellTypeState.bottom;

public class Main {

    
    public static void main(String[] args) {
        new Main();
    }


    public Main() {
        
    

        Utils.DisplayManager displayManager = new DisplayManager();

        ApplicationEventsManager.getInstance().onApplicationStarted();

        Camera mainCamera = new Camera(new Vector3f(1, 1, 1));
        
        // pozicija i boja svetala

        List<Light> lights = new ArrayList<Light>();
        lights.add(new Light(new Vector3f(15f, 2, 5f), new Vector3f(0.3f, 0.3f, 0.3f))); // pozicija i boja
        lights.add(new Light(new Vector3f(15f, 2, 15f), new Vector3f(0.3f, 0.3f, 0.3f)));
        lights.add(new Light(new Vector3f(5f, 2, 15f), new Vector3f(0.3f, 0.3f, 0.3f)));
        lights.add(new Light(new Vector3f(5f, 2, 5f), new Vector3f(0.3f, 0.3f, 0.3f)));//
        lights.add(new Light(new Vector3f(3f, 2f, 15.5f), new Vector3f(0.3f, 0.3f, 0.3f)));
        lights.add(new Light(new Vector3f(10f, 2f, 18f), new Vector3f(0.3f, 0.3f, 0.3f)));
        lights.add(new Light(new Vector3f(16f, 2f, 13f), new Vector3f(0.3f, 0.3f, 0.3f)));

        ModelsRenderer renderer = new ModelsRenderer();
        renderer.useCamera(mainCamera);
        renderer.useLight(lights);
        TexturedShader shader1 = new TexturedShader("v_textured", "f_textured");


        Texture texture = Texture.loadTexture("info", 0);
        Texture texture2 = Texture.loadTexture("marble3", 0);
        Texture texture3 = Texture.loadTexture("frameWood", 0);
        Texture texture4 = Texture.loadTexture("coffee2", 0);
        Texture texture7 = Texture.loadTexture("coffee1", 0);
        Texture texture5 = Texture.loadTexture("coffee3", 0);
        Texture texture8 = Texture.loadTexture("cofeebean", 0);
        Texture texture10 = Texture.loadTexture("cofeebeanvertical", 0);
        Texture texture11 = Texture.loadTexture("coffee5", 0);
        Texture texture12 = Texture.loadTexture("coffee6", 0);
        Texture texture13 = Texture.loadTexture("coffee7", 0);
        Texture texture14 = Texture.loadTexture("enjoy", 0);
        Texture texture15 = Texture.loadTexture("unnamed", 0);
        Texture texture6 = Texture.loadTexture("clouds", 0);
        texture6.setReflectivity(1f);
        texture6.setCameraReflectDistance(2);

        //krov
        Texture roofTexture = Texture.loadTexture("dragonScale256x256", 0);
        roofTexture.setCameraReflectDistance(10);
        roofTexture.setReflectivity(1f);
        Model roof = new Model("roof", shader1, roofTexture, ObjectLoader.FacesMode.VertexNormalIndices);
        roof.modelTransformation.changePosition(new Vector3f(5, 7f, 5));
        roof.modelTransformation.changeScale(new Vector3f(2f, 2f, 2f));
        renderer.addModelsToRender(roof);
        
        //door
        Model door = new Model("canvas", shader1, texture15, ObjectLoader.FacesMode.VertexNormalIndices);
        door.modelTransformation.changePosition(new Vector3f(5f, -2.3f, -1.2f));
        door.modelTransformation.changeScale(new Vector3f(1.48f, 2.3f, 1.4f));
        door.modelTransformation.rotate(new Vector3f(0, 90, 0));
        renderer.addModelsToRender(door);
        
        
        
        //luster
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Texture lampTexture = Texture.loadTexture("marble3", 0);
                Model lamp = new Model("lamp3", shader1, lampTexture, ObjectLoader.FacesMode.VertexNormalIndices);
                lamp.modelTransformation.changePosition(new Vector3f(2.5f + i * 5f, 7f, 2.5f + j * 5f));
                lamp.modelTransformation.changeScale(new Vector3f(1.3f, 1.3f, 1.3f));
                renderer.addModelsToRender(lamp);
                //bulb
                
                Texture bulbTexture = Texture.loadTexture("bulb", 0);
                bulbTexture.setReflectivity(3f);
                Model bulb = new Model("bulb", shader1, bulbTexture, ObjectLoader.FacesMode.VertexNormalIndices);
                bulb.modelTransformation.changePosition(new Vector3f(2.5f + i * 5f, 7.5f, 2.5f + j * 5f));
                bulb.modelTransformation.changeScale(new Vector3f(1.3f, 1.3f, 1.3f));
                renderer.addModelsToRender(bulb);
            }
        }

        //instalacja coffee cup
        Texture artObjectTexture = Texture.loadTexture("marble", 0);
        Model artObject = new Model("coffeecupobj", shader1, artObjectTexture, ObjectLoader.FacesMode.VertexNormalIndices);
        artObject.modelTransformation.changeScale(new Vector3f(2.5f, 2.5f, 2.5f));
        artObject.modelTransformation.changePosition(new Vector3f(3f, 2f, 15.5f));
        renderer.addModelsToRender(artObject);
        
        //stand
        Model stand = new Model("postolje", shader1, texture2, ObjectLoader.FacesMode.VertexNormalIndices);
        stand.modelTransformation.changePosition(new Vector3f(2f, .05f, 15.5f));
        stand.modelTransformation.changeScale(new Vector3f(.5f, .5f, .5f));
        renderer.addModelsToRender(stand);

        for (int i = 0; i < 2; i++) {
            //stubic
            Texture columnTexture = Texture.loadTexture("simple2", 0);
            columnTexture.setCameraReflectDistance(10);
            columnTexture.setReflectivity(.1f);
            Model column = new Model("stubic", shader1, columnTexture, ObjectLoader.FacesMode.VertexNormalIndices);
            column.modelTransformation.changePosition(new Vector3f(2 + 3 * i, .4f, 13.5f));
            column.modelTransformation.changeScale(new Vector3f(.25f, .25f, .25f));
            renderer.addModelsToRender(column);

        }
        
        //stubici
            Texture columnTexture = Texture.loadTexture("simple2", 0);
            columnTexture.setCameraReflectDistance(10);
            columnTexture.setReflectivity(.1f);
            Model column = new Model("stubic", shader1, columnTexture, ObjectLoader.FacesMode.VertexNormalIndices);
            column.modelTransformation.changePosition(new Vector3f(2, .4f, 17.3f));
            column.modelTransformation.changeScale(new Vector3f(.25f, .25f, .25f));
            renderer.addModelsToRender(column);

            Model column1 = new Model("stubic", shader1, columnTexture, ObjectLoader.FacesMode.VertexNormalIndices);
            column1.modelTransformation.changePosition(new Vector3f(5, .4f, 17.3f));
            column1.modelTransformation.changeScale(new Vector3f(.25f, .25f, .25f));
            renderer.addModelsToRender(column1);
            
            Model column2 = new Model("stubic", shader1, columnTexture, ObjectLoader.FacesMode.VertexNormalIndices);
            column2.modelTransformation.changePosition(new Vector3f(8.5f, .4f, 17f));
            column2.modelTransformation.changeScale(new Vector3f(.1f, .1f, .1f));
            renderer.addModelsToRender(column2);

            Model column3 = new Model("stubic", shader1, columnTexture, ObjectLoader.FacesMode.VertexNormalIndices);
            column3.modelTransformation.changePosition(new Vector3f(10.7f, .4f, 17f));
            column3.modelTransformation.changeScale(new Vector3f(.1f, .1f, .1f));
            renderer.addModelsToRender(column3);

        
        
        //luster
        Texture lampTexture = Texture.loadTexture("marble3", 0);
        Model lampk1 = new Model("lamp3", shader1, lampTexture, ObjectLoader.FacesMode.VertexNormalIndices);
        lampk1.modelTransformation.changePosition(new Vector3f(3f, 7f, 15.5f));
        lampk1.modelTransformation.changeScale(new Vector3f(1.3f, 1.3f, 1.3f));
        renderer.addModelsToRender(lampk1);
        //bulb
                
        Texture bulbTexture = Texture.loadTexture("bulb", 0);
        bulbTexture.setReflectivity(3f);
        Model bulbk1 = new Model("bulb", shader1, bulbTexture, ObjectLoader.FacesMode.VertexNormalIndices);
        bulbk1.modelTransformation.changePosition(new Vector3f(3f, 7.5f, 15.5f));
        bulbk1.modelTransformation.changeScale(new Vector3f(1.3f, 1.3f, 1.3f));
        renderer.addModelsToRender(bulbk1);
        
        //zmaj
        Texture dragonTexture = Texture.loadTexture("dragonScale256x256", 0);
        dragonTexture.setCameraReflectDistance(10);
        dragonTexture.setReflectivity(3f);
        Model dragon = new Model("dragon", shader1, dragonTexture, ObjectLoader.FacesMode.VertexNormalIndices);
        dragon.modelTransformation.changeScale(new Vector3f(.3f, .3f, .3f));
        dragon.modelTransformation.changePosition(new Vector3f(10, .4f, 18f));
        dragon.modelTransformation.rotate(new Vector3f(0, 0, 0));
        renderer.addModelsToRender(dragon);

        
        //luster
        Model lampz1 = new Model("lamp3", shader1, lampTexture, ObjectLoader.FacesMode.VertexNormalIndices);
        lampz1.modelTransformation.changePosition(new Vector3f(10f, 7f, 18f));
        lampz1.modelTransformation.changeScale(new Vector3f(1.3f, 1.3f, 1.3f));
        renderer.addModelsToRender(lampz1);
        
        //bulb dragon
        Model bulbz1 = new Model("bulb", shader1, bulbTexture, ObjectLoader.FacesMode.VertexNormalIndices);
        bulbz1.modelTransformation.changePosition(new Vector3f(10f, 7.5f, 18f));
        bulbz1.modelTransformation.changeScale(new Vector3f(1.3f, 1.3f, 1.3f));
        renderer.addModelsToRender(bulbz1);
        
        
        
        //ram coffee beans
        Model frame2 = new Model("woodenFrame", shader1, texture3, ObjectLoader.FacesMode.VertexNormalIndices);
        frame2.modelTransformation.changePosition(new Vector3f(9.7f, 2f, 6.5f));
        frame2.modelTransformation.changeScale(new Vector3f(3.2f, 3.5f, 4.5f));
        frame2.modelTransformation.rotate(new Vector3f(90, 180, 0));
        renderer.addModelsToRender(frame2);

        //coffee beans
        Model rejtan = new Model("canvashorizontal", shader1, texture5, ObjectLoader.FacesMode.VertexNormalIndices);
        rejtan.modelTransformation.changePosition(new Vector3f(9.7f, 2f, 6f));
        rejtan.modelTransformation.changeScale(new Vector3f(2f, 3.9f, 2.1f));
        rejtan.modelTransformation.rotate(new Vector3f(0, 180, 0));
        renderer.addModelsToRender(rejtan);
        
        //plants
        Texture cupTextureRight = Texture.loadTexture("dragonScale256x256", 0);
        cupTextureRight.setCameraReflectDistance(10);
        cupTextureRight.setReflectivity(3f);
        Model cupRight = new Model("biljka", shader1, cupTextureRight, ObjectLoader.FacesMode.VertexNormalIndices);
        cupRight.modelTransformation.changePosition(new Vector3f(9f, 0.8f, 12f));
        cupRight.modelTransformation.changeScale(new Vector3f(.2f, .4f, .3f));
        cupRight.modelTransformation.rotate(new Vector3f(0, 180, 1));
        renderer.addModelsToRender(cupRight);
        
        //luster
        Model lampcr = new Model("lamp3", shader1, lampTexture, ObjectLoader.FacesMode.VertexNormalIndices);
        lampcr.modelTransformation.changePosition(new Vector3f(9f, 7f, 12f));
        lampcr.modelTransformation.changeScale(new Vector3f(1.3f, 1.3f, 1.3f));
        renderer.addModelsToRender(lampcr);
        //bulb
                
        Model bulbcr = new Model("bulb", shader1, bulbTexture, ObjectLoader.FacesMode.VertexNormalIndices);
        bulbcr.modelTransformation.changePosition(new Vector3f(8.8f, 7.5f, 12f));
        bulbcr.modelTransformation.changeScale(new Vector3f(1.3f, 1.3f, 1.3f));
        renderer.addModelsToRender(bulbcr);
        
        Texture cupTextureLeft = Texture.loadTexture("dragonScale256x256", 0);
        cupTextureLeft.setCameraReflectDistance(10);
        cupTextureLeft.setReflectivity(3f);
        Model cupLeft = new Model("biljka", shader1, cupTextureLeft, ObjectLoader.FacesMode.VertexNormalIndices);
        cupLeft.modelTransformation.changePosition(new Vector3f(9f, 0.8f, 1f));
        cupLeft.modelTransformation.changeScale(new Vector3f(.2f, .4f, .3f));
        cupLeft.modelTransformation.rotate(new Vector3f(0, 180, 1));
        renderer.addModelsToRender(cupLeft);
        
        //luster
        Model lampcl = new Model("lamp3", shader1, lampTexture, ObjectLoader.FacesMode.VertexNormalIndices);
        lampcl.modelTransformation.changePosition(new Vector3f(9f, 7f, 1f));
        lampcl.modelTransformation.changeScale(new Vector3f(1.3f, 1.3f, 1.3f));
        renderer.addModelsToRender(lampcl);
        //bulb
        Model bulbcl = new Model("bulb", shader1, bulbTexture, ObjectLoader.FacesMode.VertexNormalIndices);
        bulbcl.modelTransformation.changePosition(new Vector3f(9f, 7.5f, 1f));
        bulbcl.modelTransformation.changeScale(new Vector3f(1.3f, 1.3f, 1.3f));
        renderer.addModelsToRender(bulbcl);
        
        //info
        Model posterinfo = new Model("canvashorizontal", shader1, texture, ObjectLoader.FacesMode.VertexNormalIndices);
        posterinfo.modelTransformation.changePosition(new Vector3f(2.5f, 3.5f, 9.9f));
        posterinfo.modelTransformation.changeScale(new Vector3f(0.48f, 1.3f, .9f));
        posterinfo.modelTransformation.rotate(new Vector3f(0, 90, 0));
        renderer.addModelsToRender(posterinfo);
        
        //wall1 in hallway

        //ram coffee2
        Model frame = new Model("woodenFrame", shader1, texture3, ObjectLoader.FacesMode.VertexNormalIndices);
        frame.modelTransformation.changePosition(new Vector3f(10.1f, 1.5f, 2f));
        frame.modelTransformation.changeScale(new Vector3f(.4f, .4f, 0.3f));
        frame.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(frame);

        //coffee2
        Model canvas2 = new Model("canvas", shader1, texture11, ObjectLoader.FacesMode.VertexNormalIndices);
        canvas2.modelTransformation.changePosition(new Vector3f(10.4f, 2.35f, 2f));
        canvas2.modelTransformation.changeScale(new Vector3f(.28f, .4f, .3f));
        canvas2.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(canvas2);
        
        //poster
        //longcoffee
        Model poster1 = new Model("canvas", shader1, texture10, ObjectLoader.FacesMode.VertexNormalIndices);
        poster1.modelTransformation.changePosition(new Vector3f(10.3f, 2.35f, 3f));
        poster1.modelTransformation.changeScale(new Vector3f(.28f, .4f, .3f));
        poster1.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(poster1);
        
        Model poster3 = new Model("canvas", shader1, texture10, ObjectLoader.FacesMode.VertexNormalIndices);
        poster3.modelTransformation.changePosition(new Vector3f(10.3f, 2.35f, 3.55f));
        poster3.modelTransformation.changeScale(new Vector3f(.28f, .4f, .3f));
        poster3.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(poster3);
        
        Model poster4 = new Model("canvas", shader1, texture10, ObjectLoader.FacesMode.VertexNormalIndices);
        poster4.modelTransformation.changePosition(new Vector3f(10.3f, 3.35f, 3f));
        poster4.modelTransformation.changeScale(new Vector3f(.28f, .4f, .3f));
        poster4.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(poster4);
        
        Model poster5 = new Model("canvas", shader1, texture10, ObjectLoader.FacesMode.VertexNormalIndices);
        poster5.modelTransformation.changePosition(new Vector3f(10.3f, 3.35f, 3.55f));
        poster5.modelTransformation.changeScale(new Vector3f(.28f, .4f, .3f));
        poster5.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(poster5);
        
        Model poster6 = new Model("canvashorizontal", shader1, texture8, ObjectLoader.FacesMode.VertexNormalIndices);
        poster6.modelTransformation.changePosition(new Vector3f(10.3f, 4.5f, 6.55f));
        poster6.modelTransformation.changeScale(new Vector3f(4.28f, 1.4f, 1.3f));
        poster6.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(poster6);

        //ram coffee3
        Model frame4 = new Model("woodenFrame", shader1, texture3, ObjectLoader.FacesMode.VertexNormalIndices);
        frame4.modelTransformation.changePosition(new Vector3f(10.1f, 1.5f, 5.8f));
        frame4.modelTransformation.changeScale(new Vector3f(.2f, .4f, .3f));
        frame4.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(frame4);
               
        //longcoffee
        Model coffeelong = new Model("canvas", shader1, texture4, ObjectLoader.FacesMode.VertexNormalIndices);
        coffeelong.modelTransformation.changePosition(new Vector3f(10.4f, 2.35f, 5.75f));
        coffeelong.modelTransformation.changeScale(new Vector3f(.28f, .4f, .3f));
        coffeelong.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(coffeelong);
        
        //poster
        //longcoffee
        Model poster2 = new Model("canvas", shader1, texture10, ObjectLoader.FacesMode.VertexNormalIndices);
        poster2.modelTransformation.changePosition(new Vector3f(10.3f, 2.35f, 9f));
        poster2.modelTransformation.changeScale(new Vector3f(.28f, .4f, .3f));
        poster2.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(poster2);
        
        Model poster7 = new Model("canvas", shader1, texture10, ObjectLoader.FacesMode.VertexNormalIndices);
        poster7.modelTransformation.changePosition(new Vector3f(10.3f, 2.35f, 8.45f));
        poster7.modelTransformation.changeScale(new Vector3f(.28f, .4f, .3f));
        poster7.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(poster7);
        
        Model poster8 = new Model("canvas", shader1, texture10, ObjectLoader.FacesMode.VertexNormalIndices);
        poster8.modelTransformation.changePosition(new Vector3f(10.3f, 3.35f, 9f));
        poster8.modelTransformation.changeScale(new Vector3f(.28f, .4f, .3f));
        poster8.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(poster8);
        
        Model poster9 = new Model("canvas", shader1, texture10, ObjectLoader.FacesMode.VertexNormalIndices);
        poster9.modelTransformation.changePosition(new Vector3f(10.3f, 3.35f, 8.45f));
        poster9.modelTransformation.changeScale(new Vector3f(.28f, .4f, .3f));
        poster9.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(poster9);
        
       
        //ram coffee1
        Model frame3 = new Model("woodenFrame", shader1, texture3, ObjectLoader.FacesMode.VertexNormalIndices);
        frame3.modelTransformation.changePosition(new Vector3f(10.1f, 1.5f, 12f));
        frame3.modelTransformation.changeScale(new Vector3f(.4f, .4f, 0.3f));
        frame3.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(frame3);

        //coffee 1
        Model coffeepic = new Model("canvas", shader1, texture7, ObjectLoader.FacesMode.VertexNormalIndices);
        coffeepic.modelTransformation.changePosition(new Vector3f(10.4f, 2.35f, 11.8f));
        coffeepic.modelTransformation.changeScale(new Vector3f(.28f, .4f, .3f));
        coffeepic.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(coffeepic);
        
        //wall2 bakcroom
        //ram coffee6
        Model frame5 = new Model("woodenFrame", shader1, texture3, ObjectLoader.FacesMode.VertexNormalIndices);
        frame5.modelTransformation.changePosition(new Vector3f(13.4f, 1.5f, 10f));
        frame5.modelTransformation.changeScale(new Vector3f(.4f, .4f, 0.3f));
        frame5.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(frame5);

        //coffee6
        Model canvas3 = new Model("canvas", shader1, texture12, ObjectLoader.FacesMode.VertexNormalIndices);
        canvas3.modelTransformation.changePosition(new Vector3f(13.7f, 2.35f, 9.9f));
        canvas3.modelTransformation.changeScale(new Vector3f(.28f, .4f, .3f));
        canvas3.modelTransformation.rotate(new Vector3f(0, 180, 180));
        renderer.addModelsToRender(canvas3);


       //wall3 backroom
       
        Model frame6 = new Model("woodenFrame", shader1, texture3, ObjectLoader.FacesMode.VertexNormalIndices);
        frame6.modelTransformation.changePosition(new Vector3f(19.7f, 2f, 6.5f));
        frame6.modelTransformation.changeScale(new Vector3f(0.8f, 1.5f, 1.5f));
        frame6.modelTransformation.rotate(new Vector3f(90, 180, 0));
        renderer.addModelsToRender(frame6);

        //coffee beans
        Model poster10 = new Model("canvashorizontal", shader1, texture13, ObjectLoader.FacesMode.VertexNormalIndices);
        poster10.modelTransformation.changePosition(new Vector3f(19.55f, 2f, 6.6f));
        poster10.modelTransformation.changeScale(new Vector3f(0.48f, 1.3f, .9f));
        poster10.modelTransformation.rotate(new Vector3f(0, 180, 0));
        renderer.addModelsToRender(poster10);
        
        //wall4 backroom
        
        Model sign = new Model("canvashorizontal", shader1, texture14, ObjectLoader.FacesMode.VertexNormalIndices);
        sign.modelTransformation.changePosition(new Vector3f(3f, 3f, 19f));
        sign.modelTransformation.changeScale(new Vector3f(1.48f, 2.3f, 1.9f));
        sign.modelTransformation.rotate(new Vector3f(0, 90, 0));
        renderer.addModelsToRender(sign);
        
        //sculpture
        
        //coffe sculpture
        Model dawid = new Model("coffeecupobj", shader1, texture6, ObjectLoader.FacesMode.VertexNormalIndices);
        dawid.modelTransformation.changePosition(new Vector3f(16.6f, .9f, 13.4f));
        dawid.modelTransformation.changeScale(new Vector3f(2.8f, 2.8f, 2.8f));
        renderer.addModelsToRender(dawid);

        //postolje
        Model stand2 = new Model("postolje", shader1, texture2, ObjectLoader.FacesMode.VertexNormalIndices);
        stand2.modelTransformation.changePosition(new Vector3f(16.185f, .1f, 13f));
        stand2.modelTransformation.changeScale(new Vector3f(.5f, .5f, .5f));
        renderer.addModelsToRender(stand2);
        
        
        Model lampz2 = new Model("lamp3", shader1, lampTexture, ObjectLoader.FacesMode.VertexNormalIndices);
        lampz2.modelTransformation.changePosition(new Vector3f(16f, 7f, 13f));
        lampz2.modelTransformation.changeScale(new Vector3f(1.3f, 1.3f, 1.3f));
        renderer.addModelsToRender(lampz2);
        //bulb
                
        Model bulbz2 = new Model("bulb", shader1, bulbTexture, ObjectLoader.FacesMode.VertexNormalIndices);
        bulbz2.modelTransformation.changePosition(new Vector3f(16f, 7.5f, 13f));
        bulbz2.modelTransformation.changeScale(new Vector3f(1.3f, 1.3f, 1.3f));
        renderer.addModelsToRender(bulbz2);
        
        //dragon1
        Model dragon2 = new Model("dragon", shader1, dragonTexture, ObjectLoader.FacesMode.VertexNormalIndices);
        dragon2.modelTransformation.changeScale(new Vector3f(.1f, .1f, .1f));
        dragon2.modelTransformation.changePosition(new Vector3f(18f, .4f, 11f));
        dragon2.modelTransformation.rotate(new Vector3f(0, 0, 0));
        renderer.addModelsToRender(dragon2);
        
        Model dragon3 = new Model("dragon", shader1, dragonTexture, ObjectLoader.FacesMode.VertexNormalIndices);
        dragon3.modelTransformation.changeScale(new Vector3f(.1f, .1f, .1f));
        dragon3.modelTransformation.changePosition(new Vector3f(15f, .4f, 11f));
        dragon3.modelTransformation.rotate(new Vector3f(0, 0, 0));
        renderer.addModelsToRender(dragon3);

        Texture terrainTexture = Texture.loadTexture("woodenSurface", 0);
        ShaderProgram multipleTextureShader = new TerrainShader("v_terrain", "f_terrain");

        Texture wallTexture = Texture.loadTexture("walls", 0);


        dragon.loadProjectionMatrix(renderer.getProjectionMatrix());
        

        Mouse.setGrabbed(true);

        //floor
        terrainTexture.setCameraReflectDistance(1);
        terrainTexture.setReflectivity(0);
        Terrain terrain = new Terrain(1, 1, multipleTextureShader, terrainTexture);
        terrain.getTerrainModel().loadProjectionMatrix(renderer.getProjectionMatrix());
        terrain.getTerrainModel().modelTransformation.changePosition(new Vector3f(0, .3f, 0));

        Walls wallsGenerator = new Walls(multipleTextureShader, wallTexture);

        while (!Display.isCloseRequested()) {

            prepare();
            renderer.renderTerrain(terrain);
            renderer.renderWalls(wallsGenerator);
            renderer.renderModels();
            displayManager.update();

            artObject.modelTransformation.rotate(new Vector3f(0, 1.2f, 0));
            dawid.modelTransformation.rotate(new Vector3f(0, 1, 0));
            
            if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
                Display.destroy();
                System.exit(0);
            }
            
            if(Keyboard.isKeyDown(Keyboard.KEY_H)){
                helpBox("Help\n"
                        + "For movement do the following:\n"
                        + "w - forward\n"
                        + "s - backwards\n"
                        + "a - left\n"
                        + "d - right\n"
                        + "arrow up - forward\n"
                        + "arrow down - backwards\n"
                        + "arrow left - left\n"
                        + "arrow right - right\n"
                        + "mouse - use mouse to turn around and see\n"
                        + "Esc - exit the museum/app\n"
                        + "h - help instructions", "help");
                
            }
            
       
            


        }

        ApplicationEventsManager.getInstance().onApplicationEnded();
        displayManager.destroy();
        Texture.clearTextures();

    }

    
    public void helpBox(String infoMessage, String title){
        JOptionPane.showMessageDialog(null, infoMessage, "Help: " + title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(Constants.BACKGROUND_COLOR.x, Constants.BACKGROUND_COLOR.y, Constants.BACKGROUND_COLOR.z, Constants.BACKGROUND_COLOR.w);
    }
}
