package Models;

import java.util.ArrayList;
import java.util.List;

public class ModelData {

    private float[] vertices;
    private float[] normals;
    private float[] texCoords;
    private int[] indices;


    public float[] getNormals() {
        return normals;
    }

    public float[] getVertices() {
        return vertices;
    }

    public float[] getTexCoords() {
        return texCoords;
    }

    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }

    public void setNormals(float[] normals) {
        this.normals = normals;
    }

    public void setTexCoords(float[] texCoords) {
        this.texCoords = texCoords;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }

    public int [] getIndices() { return this.indices;}

    int vertexCount;

    public ModelData(int vertexCount) {
        this.vertexCount = vertexCount;
        vertices = new float[vertexCount];
        normals = new float[vertexCount];
        texCoords = new float[vertexCount];
    }

    public ModelData(float [] vertices, float [] normals, float []texCoords, int [] indices)
    {
        this.vertexCount = indices.length;
        this.vertices = vertices;
        this.normals = normals;
        this.texCoords = texCoords;
        this.indices = indices;
    }
}