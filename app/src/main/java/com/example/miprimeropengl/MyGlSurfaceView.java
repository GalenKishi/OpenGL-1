package com.example.miprimeropengl;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGlSurfaceView extends GLSurfaceView{
    private final MyGlRenderer renderer;

    public MyGlSurfaceView(Context context){ //Son los parametros que se ocuparan en parte del Context de android
        super(context); //Se esta inicializando como algo supercargado que es almacenar mas

        setEGLContextClientVersion(2); //Se establece la version en el 2.0

        renderer = new MyGlRenderer();
        setRenderer(renderer);
    }
}
