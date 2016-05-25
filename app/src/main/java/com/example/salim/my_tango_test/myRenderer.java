package com.example.salim.my_tango_test;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import com.example.salim.my_tango_test.R;

import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.methods.SpecularMethod;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Sphere;
import org.rajawali3d.renderer.RajawaliRenderer;

/**
 * Created by salim on 25/05/16.
 */
public class myRenderer extends RajawaliRenderer {

    //tutorial says we need a context, but we dont need it it since it is never used
    private Context context;
    private DirectionalLight directionalLight;
    private Sphere earthSphere;


    public myRenderer(Context context) {
        super(context);
        this.context = context;
        //Do we need this set frame rate, will it crash with tango device?
        setFrameRate(60);
    }

    @Override
    protected void initScene() {
        //setting up the light, should not change when using tango
        directionalLight = new DirectionalLight(1f, -3f, -1.0f);
        directionalLight.setColor(1.0f, 1.0f, 1.0f );
        directionalLight.setPower(2);
        getCurrentScene().addLight(directionalLight);

        Material material = new Material();
        material.enableLighting(true);
        material.setDiffuseMethod(new DiffuseMethod.Lambert());
        material.setSpecularMethod(new SpecularMethod.Phong());
        material.setColor(0);

        Texture earthTexture = new Texture("Earth", R.drawable.earth);
        try{
            material.addTexture(earthTexture);
        } catch (ATexture.TextureException error){
            Log.d("DEBUG","TEXTURE ERROR");
        }

        earthSphere = new Sphere(1,24,24);
        earthSphere.setMaterial(material);
        getCurrentScene().addChild(earthSphere);
        getCurrentCamera().setZ(4.2f);
    }

    @Override
    public void onRender(final long elapsedTime, final double deltaTime){
        super.onRender(elapsedTime, deltaTime);
        earthSphere.rotate(Vector3.Axis.Y, 1.0);
    }

    @Override
    public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }
}
