package com.example.usuario.prueba01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText etusuario, etcontraseña;
    private Button bingresar;

    private VolleyRP volley;
    private RequestQueue mRequest;

    private static String ip = "http://iarq.pe.hu/ArchivosPHP/Login_GETID.php?id=";

    private String USER="";     //Esta variable "USER" sirve para almacenar el usuario digitado en el ETU, con la finalidad..
                                //.. de almacenar los datos para su uso porterior.
    private String PASSWORD="";     //Esta variable "PASSWORD" sirve para almacenar el password digitado en el ETC, con la finalidad..
                                    //.. de almacenar los datos para su uso porterior.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        etusuario = (EditText) findViewById(R.id.ETU);
        etcontraseña = (EditText) findViewById(R.id.ETC);
        bingresar = (Button) findViewById(R.id.BI);

        bingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarLogin(etusuario.getText().toString().toLowerCase(),etcontraseña.getText().toString().toLowerCase());
            }
        });

    }

    public void verificarLogin(String user, String password){
        USER = user;
        PASSWORD = password;
        Toast.makeText(this,"El usuario es: "+user+" y la contraseña es: "+password,Toast.LENGTH_SHORT).show();
        SolicitudJSON(ip+user);     //Enviamos "ip+user" (URL) al JsonObjectRequest
    }

    public void SolicitudJSON(String URL){
        JsonObjectRequest solicitud = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
            @Override
            //El método "onResponse(JSONObject)" se ejecuta al instante que el servidor termina de enviar los datos solicitados (JSONObject) al cliente
            public void onResponse(JSONObject datos) {
                VerificarPassword(datos);
            }
        }, new Response.ErrorListener() {
            @Override
            //El método "onErrorResponse (VolleyError)" se ejecuta en el instante que no se encuentra una conexión con el servidor
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Ocurrio un error",Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud,mRequest,this,volley);
    }

    public void VerificarPassword(JSONObject datos){
        Toast.makeText(this,"Los datos son: "+datos.toString(),Toast.LENGTH_SHORT).show();
        try{
            String estado = datos.getString("resultado");   //Obtenemos el valor del campo "resultado" del "JSONObject" (datos)
            if(estado.equals("CC")){
                Toast.makeText(this,"El usuario si existe",Toast.LENGTH_SHORT).show();
                JSONObject Jsondatos = new JSONObject(datos.getString("datos"));    //Creamos un nuevo "JSONObject" a partir de la..
                                                                                    //.. información contenida en "datos.getString("datos")"
                String usuario = Jsondatos.getString("id"); //Obtenemos el valor del campo "id" del "JSONObject" (Jsondatos)
                String contraseña = Jsondatos.getString("Password");    //Obtenemos el valor del campo "password" del "JSONObject" (Jsondatos)
                if(usuario.equals(USER) && contraseña.equals(PASSWORD)){
                    Toast.makeText(this,"Usted se logeo correctamente",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"La contraseña es incorrecta",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,estado,Toast.LENGTH_SHORT).show();
            }
        }catch (JSONException e){

        }
    }
}
