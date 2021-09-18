package Models;

import Shaders.TexturedShader;
import Light.Light;
import java.util.*;
import Shaders.ShaderProgram;
import Texturing.Texture;
import Utils.ObjectLoader;
import Utils.VBOManager;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Model {

    private int vaoID;
    private int vertexCount;

    ModelData data;
    public ModelTransformation modelTransformation;
    private ShaderProgram modelShader;
    public Texture modelTexture;
    //sets the default transform to
    //positions 0,0,0
    //rotations 0,0,0
    //scale 1,1,1
    private Model()
    {
        modelTransformation = new ModelTransformation(
                new Vector3f(0  ,0,0),    //positions
                new Vector3f(1,1,1),    //rotations
                new Vector3f(.5f,.5f,.5f));   //scale
    }

    public Model(ShaderProgram shader, Texture texture)
    {
        this();
        this.modelShader = shader;
        this.modelTexture = texture;
    }



    //useful for loading models from a file
    //give the name of the file that is in the modelsData folder, without the extension
    public Model(String filename, ShaderProgram shader, Texture texture, ObjectLoader.FacesMode facesMode)
    {
        this();
        ModelData modelData = ObjectLoader.loadModel(filename, facesMode); //to facesMode niech zostanie
        //loading an object with vertices, normals and texturing coordinates
        this.data = modelData;
        this.modelTexture = texture; 
        this.modelShader = shader;

        loadToVAO();

    }


    public void loadTexture()
    {
        if(modelTexture != null)
        {
            modelTexture.setTextureActive();
            modelShader.loadShineVariables(modelTexture.getCameraReflectDistance(),modelTexture.getReflectivity());
        }else
        {

        }
    }



    public void startUsingShader()
    {
        if(modelShader != null)
        {
            modelShader.startUsingShader();
        }
    }

    public void stopUsingShader()
    {
        if(modelShader != null)
        {
            modelShader.stopUsingShader();
        }
    }

    public void loadTransformationMatrix()
    {
        modelShader.loadTransformationLocation(modelTransformation.getTansformationMatrix());
    }

    public void loadProjectionMatrix(Matrix4f projectionMatrix)
    {
        modelShader.startUsingShader();
        modelShader.loadProjectionMatix(projectionMatrix);
        modelShader.stopUsingShader();
    }

    public void loadViewMatrix(Matrix4f viewMatrix)
    {
        modelShader.loadViewMatrix(viewMatrix);
    }

    public void loadLights(List<Light> lights)
    {
        modelShader.loadLights(lights);
    }


    //loading into VAO buffer, performed once after object initialization
    //if the object is not to be generated, it should not be loaded into the VAO buffer
    public void loadToVAO()
    {
        VBOManager.getInstance().loadToVAO(this);
    }



    //getters and setters
    public ModelData getData() {
        return data;
    }

    public void setData(ModelData data) {
        this.data = data;
    }

    public int getVertexCount() {
        if(data == null)
            return 0;
        return data.vertexCount;
    }


    public  int getVaoID()
    {
        return  vaoID;
    }

    public void setVaoID(int vaoID) {
        this.vaoID = vaoID;
    }

}