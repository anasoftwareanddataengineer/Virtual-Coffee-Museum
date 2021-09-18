/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Camera;
import Utils.MathUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
/**
 *
 * @author Ana
 */
public class Camera2 {
    private Vector3f position;
    private static float MAX_X=9;
    private static float MAX_Y=9;
    private static float MAX_Z=9;
    private float pitch=0;
    private float yaw=180;
    private float roll=0;
    private float moveSpeed=0.1f;
    private float sensitivity = 0.3f;
    private Matrix4f viewMatrix;
     public Camera2(Vector3f initialPosition)
    {
        position = initialPosition;
        height = position.y;
        //viewMatrix = MathUtils.createViewMatrix2(this);


    }

    float dx = 0.0f;
    float dy = 0.0f;
    float angle = 0f;
    float angleDiff = 10f;
    float walkFactor = 0.1f;
    float height = 100f;
    public void walkForward(float distance) {
        position.x += distance * (float) Math.sin(Math.toRadians(yaw));
        position.z -= distance * (float) Math.cos(Math.toRadians(yaw));
        angle+= angleDiff;
        humanWalk();
    }

    public void walkBackwards(float distance) {
        position.x -= distance * (float) Math.sin(Math.toRadians(yaw));
        position.z += distance * (float) Math.cos(Math.toRadians(yaw));
        angle-= angleDiff;
        humanWalk();
    }

    public void walkLeft(float distance) {
        position.x -= distance * (float) Math.sin(Math.toRadians(yaw - 90));
        position.z += distance * (float) Math.cos(Math.toRadians(yaw - 90));

        angle-= angleDiff;
        humanWalk();

    }

    public void walkRight(float distance) {
        position.x -= distance * (float) Math.sin(Math.toRadians(yaw + 90));//what is yaw and angles in moving wtf
        position.z += distance * (float) Math.cos(Math.toRadians(yaw + 90));

        angle+= angleDiff;
        humanWalk();
    }

    private void humanWalk()
    {
        position.y = height + height * (float) Math.cos(Math.toRadians(angle)) * walkFactor;
    }

    public void update()
    {
        calculateAngle();
        //walls
        if(position.x>=19.5) position.x-=0.1;
        else if(position.x<=0.5)position.x+=0.1;
        else if(position.z>=19.5)position.z-=0.1;
        else if(position.z<=0.5)position.z+=0.1;
        else if(position.z>=9.5 && position.z<=10 && position.x<=5)position.z-=0.1;
        else if(position.z>=10.5 && position.z<=11 && position.x<=5)position.z+=0.1;
        else if(position.x>=9.5 && position.x<=10 && position.z <= 14) position.x-=0.1;
        else if(position.x>=10 && position.x<=10.5 && position.z <= 14) position.x+=0.1;
        else if(position.x<=13 && position.x>=12.5 && position.z >= 10) position.x-=0.1;
        else if(position.x<=13.5 && position.x>=13 && position.z >= 10) position.x+=0.1;
        else if(position.x<=13 && position.x>=12.5 && position.z >= 10) position.x-=0.1;
        else if(position.x<=5 && position.z<=17 && position.z >= 13) position.x+=0.1;
        else if(position.x>=8.5 && position.x<=10.5 && position.z>=17)position.z-=0.1;
        else if(position.x>=15 && position.x<=18 && position.z>=11)position.z-=0.1;
        else if(position.x>=8 && position.x<10 && position.z<=14 && position.z>=12)position.x-=0.1;
        else if(position.x>=8 && position.x<10 && position.z<=3 && position.z>=1)position.x-=0.1;
        else{
            if (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W)) {
                walkForward(moveSpeed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S)) {
                walkBackwards(moveSpeed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D)) {
                walkLeft(moveSpeed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A)) {
                walkRight(moveSpeed);
            }
        }



        //viewMatrix = MathUtils.createViewMatrix(this);

    }

    public void calculatePitch()
    {
        if(Mouse.isButtonDown(1))
        {
            float pitchChange= Mouse.getDY()*0.1f;
            yaw += pitchChange;
        }
    }

    public void calculateAngle()
    {
        dx = Mouse.getDX();
        dy = Mouse.getDY();

        yaw+=(dx * sensitivity);
        pitch-=(dy * sensitivity);
    }
    public void Mouse()
    {

    }
    public float getRoll() {
        return roll;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public Vector3f getPosition() {
        return position;
    }


    public Matrix4f getViewMatrix() {
        return viewMatrix;
    }
}
