package com.example.arnavigation;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Frame;
import com.google.ar.core.Pose;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;
import com.google.ar.sceneform.ux.ArFragment;
//import com.google.ar.core.Frame;
//import com.google.ar.core.Pose;
//import com.google.ar.core.TrackingState;
//import com.google.ar.sceneform.ux.ArFragment;

public class MainActivity extends AppCompatActivity {
    private ArFragment arFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);



        try {
            arFragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
//                Toast.makeText(this, "FrameTime", Toast.LENGTH_LONG).show();
                Frame frame = arFragment.getArSceneView().getArFrame();
                if (frame != null) {
//                    Toast.makeText(this, "Frame not null", Toast.LENGTH_LONG).show();
                    updateDevicePosition(frame);
                }
                else {
//                    Toast.makeText(this, "Frame  null", Toast.LENGTH_LONG).show();

                }
            });
        }  catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    private void updateDevicePosition(Frame frame) {
        if (frame.getCamera().getTrackingState() == TrackingState.TRACKING) {
            Pose cameraPose = frame.getCamera().getPose();
            float[] translation = cameraPose.getTranslation();
            float[] rotation = cameraPose.getRotationQuaternion();
            Log.d("ARCore", "Device Position: " +
                    "X: " + translation[0] +
                    " Y: " + translation[1] +
                    " Z: " + translation[2]);
            Log.d("ARCore", "Device Rotation: " +
                    "QX: " + rotation[0] +
                    " QY: " + rotation[1] +
                    " QZ: " + rotation[2] +
                    " QW: " + rotation[3]);
        }
    }
}