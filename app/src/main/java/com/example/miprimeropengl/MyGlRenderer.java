package com.example.miprimeropengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.time.temporal.ChronoUnit;

public class MyGlRenderer implements GLSurfaceView.Renderer {

    private Triangulo mtriangulo;
    private Cuadrado mcuadrado;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f,0.2f,0.0f,1.0f); //Aplica el inicio el color principal = lienzo
        mtriangulo = new Triangulo();
        mcuadrado = new Cuadrado();
    }
    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT); //Se crea un ciclo de memoria en donde guarda el color anterior  y lo dibuja
        //mtriangulo.draw(); //Creacion del triangulo
        mcuadrado.draw();
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0,0, width, height);
    }

    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
