package com.example.miprimeropengl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends Activity {
    private GLSurfaceView glView;

    @Override //Esto sirve para no saturar de informacion
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glView = new MyGlSurfaceView(this); //Objeto de nuestro lienzo que nos permite dibujar
        setContentView(glView); //Nos muestra el lienzo en la pantalla
    }
}