package com.example.usuario.prueba01;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyRP {
    
    private static VolleyRP mVolleyRP = null;   //Esta declaración permitirá conjuntamente con el método "getInstance (Context)" crear unicamente una..
                                                //.. instancia de la clase "VolleyRP"

    private RequestQueue mRequestQueue;

    private VolleyRP(Context context) {     //Constructor de la clase "VolleyRP"
        mRequestQueue = Volley.newRequestQueue(context);
    }

    //La estructura de este método "getInstance (Context)" servirá para asegurar que únicamente una instancia de la clase "VolleyRP" podrá..
    //.. ser usada en el MainActivity. Este método también asegura que la única instancia permitida contenga el "contexto" inicial de su creación
    //Obviamente tendrá el "contexto" del MainActivity
    public static VolleyRP getInstance(Context context) {
        if (mVolleyRP == null) {
            mVolleyRP = new VolleyRP(context);
        }
        return mVolleyRP;
    }

    //Este método "getRequestQueue()" sirve para obtener la "cola de solicitudes" (RequestQueue) que será creado en el MainActivity
    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    public static void addToQueue(Request request, RequestQueue fRequestQueue, Context context, VolleyRP volley) {
        if (request != null) {
            request.setTag(context);    //Se establece el "contexto" (contexto que viene de MainActivity) a la "solicitud" (Solicitud que..
                                        //.. viene del MainActivity)
            if (fRequestQueue == null)
                fRequestQueue = volley.getRequestQueue(); //Esto es para asegurar que exista una "cola de solicitudes" (fRequestQueue)
            request.setRetryPolicy(new DefaultRetryPolicy(  //Configuramos la solicitud (request) con una "política de intentos" (Esto servirá..
                                                            //.. para definir el número de intentos que el "request" necesitará para..
                                                            //..conectarse al servidor)
                    60000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT   //60000 = Tiempo de espera inicial
                                                                        //3 = Número máximo de intentos
                                                                        //DEFAULT_B...T = Tiempo para dejar de intentar
            ));
            fRequestQueue.add(request); //Se agrega la solicitud configurada (request, que viene de MainActivity) a la "cola de solicitudes"
        }
    }
}
