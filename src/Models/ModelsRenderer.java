package Models;

import Camera.Camera;
import Light.*;
import Terrain.Terrain;
import Utils.MathUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import Walls.Walls;
import java.util.ArrayList;
import java.util.List;

public class ModelsRenderer {
    Camera mainCamera;
    List<Light>LightHandler = new ArrayList<Light>();


    public ModelsRenderer() {
        projectionMatrix = MathUtils.createProjectionMatrix();
    }

    private Matrix4f projectionMatrix;

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    private List<Model> modelsToRender = new ArrayList<>();

    public List<Model> getModelsToRender() {
        return modelsToRender;
    }

    public void addModelsToRender(Model modelToRender) {
        if (!modelsToRender.contains(modelToRender)) {
            modelsToRender.add(modelToRender);
        }
    }


    public void renderTerrain(Terrain terrain) {
        terrain.getTerrainModel().startUsingShader();
        // terrain.getTerrainModel().loadProjectionMatrix(getProjectionMatrix());
        bindAttribArrays(terrain.getTerrainModel().getVaoID());
        terrain.getTerrainModel().loadTexture();
        loadMatrices(terrain.getTerrainModel());
        terrain.getTerrainModel().loadLights(LightHandler);
        drawModel(terrain.getTerrainModel().getVertexCount());
        unbindAttribArrays();
        terrain.getTerrainModel().stopUsingShader();


    }

    public void renderWalls(Walls wallsObject) {
        for(int i=0;i<wallsObject.getWalls().size();i++)
        {
            wallsObject.getWalls().get(i).startUsingShader();
            bindAttribArrays(wallsObject.getWalls().get(i).getVaoID());
            wallsObject.getWalls().get(i).loadTexture();
            loadMatrices(wallsObject.getWalls().get(i));
            wallsObject.getWalls().get(i).loadLights(LightHandler);
            drawModel(wallsObject.getWalls().get(i).getVertexCount());
            unbindAttribArrays();
            wallsObject.getWalls().get(i).stopUsingShader();
        }
    }


    public void renderModels() {
        //checks which key has been pressed
        //computes the view matrix
        mainCamera.update();


        for (Model model : modelsToRender) {

            model.startUsingShader();
            bindAttribArrays(model.getVaoID());
            model.loadTexture();

            loadMatrices(model);

            model.loadLights(LightHandler);

            drawModel(model.getVertexCount());
            unbindAttribArrays();
            model.stopUsingShader();
        }
    }

    private void drawModel(int vertexCount) {
        GL11.glDrawElements(GL11.GL_TRIANGLES, vertexCount, GL11.GL_UNSIGNED_INT, 0);

    }


    private void loadMatrices(Model model) {
        model.loadTransformationMatrix();

        model.loadViewMatrix(mainCamera.getViewMatrix());
    }

    private void bindAttribArrays(int vaoID) {
        GL30.glBindVertexArray(vaoID);
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
    }

    private void unbindAttribArrays() {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    public void useCamera(Camera mainCamera) {
        this.mainCamera = mainCamera;
    }

    public void useLight(List<Light>lightArray) {
        this.LightHandler = lightArray;
    }

    
}
