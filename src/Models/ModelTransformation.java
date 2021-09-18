package Models;

import Utils.MathUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class ModelTransformation {
    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;

    public ModelTransformation(Vector3f position, Vector3f rotation, Vector3f scale)
    {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void changePosition(Vector3f newPosition)
    {
        this.position = newPosition;
    }


    public Matrix4f getTansformationMatrix()
    {
        return MathUtils.createTransformationMatrix(this.position, this.rotation, this.scale);
    }

    public void increasePosition()
    {
        this.position.x += .5f;
        this.position.y += .5f;
        this.position.z += .5f;
    }

    public void decreasePosition()
    {
        this.position.x -= .5f;
        this.position.y -= .5f;
        this.position.z -= .5f;
    }

    public void rotate()
    {
        this.rotation.x += .1f;
        this.rotation.y += .1f;
    }

    public void rotate(Vector3f rotation)
    {
        this.rotation.x += rotation.x;
        this.rotation.y += rotation.y;
        this.rotation.z += rotation.z;

    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void changeScale(Vector3f scale) {
        this.scale = scale;
    }

    public void changeRotation(Vector3f rotation) {
        this.rotation = rotation;
    }
}