package com.example.miprimeropengl;

import android.opengl.GLES20;

import java.io.OptionalDataException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Cuadrado {

    private ShortBuffer drawListBuffer;
    private FloatBuffer vertexBuffer;

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "gl_Position =  vPosition;" +
                    "}";
    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    static final int COORDS_PER_VERTEX = 3;
    static float[] coordenadasCuadrado = {
            -0.5f, 0.5f, 0.0f, //Arriba
            -0.5f, -0.5f, 0.0f, //Izquierda
            0.5f, -0.5f, 0.0f, //Derecha
            0.5f, 0.5f, 0.0f
    };

    private short drawOrder[] = { 1, 2, 3}; //Orden de los indices

    float[] color = {0.4f, 0.0f, 0.6f, 1.0f};

    private final int mProgram;

    //Constructor para cuando crea el objeto
    public Cuadrado() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(coordenadasCuadrado.length * 4); //Generacion vertices
        byteBuffer.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuffer.asFloatBuffer();
        vertexBuffer.put(coordenadasCuadrado);
        vertexBuffer.position(0);

        ByteBuffer dlb = ByteBuffer.allocateDirect(drawOrder.length * 2); //Generacion indice
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);


        int vertexShader = MyGlRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode); //Asignar shader para continuar
        int fragmentShader = MyGlRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        //Creamos un programa de OpenGL vacío
        mProgram = GLES20.glCreateProgram();

        //añadimos los vertex shader al programa con todos sus atributos
        GLES20.glAttachShader(mProgram, vertexShader);

        //añadimos los fragmentos shader al programa con todos sus atributos
        GLES20.glAttachShader(mProgram, fragmentShader);

        //Creamos ejecutable de OpenGL para poderlo ejecutar
        GLES20.glLinkProgram(mProgram);
    }

    private int positionHandle, colorHandle; //Posición y color a usar

    private final int vertexCount = coordenadasCuadrado.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4; //Vertex stride

    public void draw(){
        //Añadir nuestro programa al entorno de OpenGL
        GLES20.glUseProgram(mProgram);

        //Obtener el identificador del miembro vPosition del sombreado de vértices
        positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer );
        GLES20.glUniform4fv(colorHandle, 1, color, 0);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        GLES20.glDisableVertexAttribArray(positionHandle);
    }
}
