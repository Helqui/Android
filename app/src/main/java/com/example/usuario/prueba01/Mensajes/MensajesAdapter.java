package com.example.usuario.prueba01.Mensajes;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.usuario.prueba01.R;

import java.util.List;

/**
 * Created by Usuario on 27/05/2017.
 */

public class MensajesAdapter extends RecyclerView.Adapter<MensajesAdapter.MensajeViewHolder> {

    private List<MensajeDeTexto> mensajeDeTextos;   //"mensajeDeTextos" almacenará la Lista proveniente de "Mensajeria.java"
    private Context context;

    public MensajesAdapter(List<MensajeDeTexto> mensajeDeTextos, Context context){   //Contructor que creará una instancia de "MensajesAdapter" que tendrá..
                                                                    //.. como parámetro la Lista proveniente de "Mensajeria.java"
        this.mensajeDeTextos = mensajeDeTextos;
        this.context = context;
    }

    public MensajeDeTexto getMensaje(int i){
        return mensajeDeTextos.get(i);
    }

    @Override
    public MensajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {   //El método "onCreateViewHolder()" creará solamente los "View" que necesite para colocar..
                                                                                    //.. todos los mensajes que se encuentran en la "Lista". Así sean 40 mensajes, solamente creará..
                                                                                    //..12, para lo cual ANDROID usará los 12 "View" para mostrar los 40 mensajes
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_mensajes,parent,false);    //El método "inflate()" de un "LayoutInflater()"..
                                                                                                                //.. sirve para inflar un "View" con las ..
                                                                                                                //.. características del "ViewGroup" con la finalidad ..
                                                                                                                //.. de que quepa el "View" en el "GroupView", y para especificar..
                                                                                                                //.. si el "View" se adjuntará al "ViewGroup".
        return new MensajeViewHolder(v);    //Crea una instancia de "MensajeViewHolder" con un "View" como parámetro
                                            //Un "View" será el almacén de 1 solo mensaje. Esto quiere decir que por más que haya 40 mensajes, ANDROID..
                                            //.. usará un "View" para mostrar unicamente 1 mensaje. Se supone que el resto de mensajes estarán en cache..
                                            //.. esperando ser mostrados por este mismo "View"
    }

    @Override
    public void onBindViewHolder(MensajeViewHolder holder, int position) {  //Este método sirve para colocar iterativamente los mensajes en su respectivo "View"

        RelativeLayout.LayoutParams rl = (RelativeLayout.LayoutParams) holder.cardView.getLayoutParams();   //Con el método "getLayoutParams()" obtenemos el ..
                                                                                                            //..LayoutParams (RelativeLayout) que contiene al "cardView"
        FrameLayout.LayoutParams rf = (FrameLayout.LayoutParams) holder.linearLayout.getLayoutParams(); //Con el método "getLayoutParams()" obtenemos el ..
                                                                                                        //..LayoutParams (FrameLayout) que contiene al "linearLayout". No olvidar..
                                                                                                        //..que los "CardView" son "FrameLayout" ("CardView" es clase hija de "FrameLayout")
        LinearLayout.LayoutParams ll1 = (LinearLayout.LayoutParams) holder.hora.getLayoutParams();  //Con el método "getLayoutParams()" obtenemos el LayoutParams (LinearLayout)..
                                                                                                    //..que contiene a "hora"

        if(mensajeDeTextos.get(position).getTipoMensaje()==1){
            rl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);  //Agregamos una regla al "RelativeLayout" (Alinear su contenido a la derecha)
            rf.gravity = Gravity.RIGHT; //Alineamos el "FrameLayout" (CardView) a la derecha
            ll1.gravity = Gravity.RIGHT;    //Le colocamos al atributo "gravity" de "lll" un "Gravity.RIGHT"
            holder.hora.setGravity(Gravity.RIGHT);  //A "holder.hora" le establecemos un gravity de "Gravity.RIGHT"
            holder.mensaje.setGravity(Gravity.RIGHT);   //A "holder.mensaje" le establecemos un gravity de "Gravity.RIGHT"
            holder.linearLayout.setBackgroundResource(R.drawable.in_message_bg);    //Colocamos la imagen que tendrá cada "MensajeViewHolder" creado

        }else if (mensajeDeTextos.get(position).getTipoMensaje()==2){
            rl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);   //Agregamos una regla al "RelativeLayout" (Alinear su contenido a la izquierda)
            rf.gravity = Gravity.LEFT;  //Alineamos el "FrameLayout" (CardView) a la izquierda
            ll1.gravity = Gravity.LEFT; ////Le colocamos al atributo "gravity" de "lll" un "Gravity.LEFT"
            holder.hora.setGravity(Gravity.LEFT);   //A "holder.hora" le establecemos un gravity de "Gravity.LEFT"
            holder.mensaje.setGravity(Gravity.LEFT);    //A "holder.mensaje" le establecemos un gravity de "Gravity.RIGHT"
            holder.linearLayout.setBackgroundResource(R.drawable.out_message_bg);   //Colocamos la imagen que tendrá cada "MensajeViewHolder" creado
        }

        holder.cardView.setLayoutParams(rl);    //El método "setLayoutParams()" permite agregar la regla creada a su Layout contenedor (RelativeLayout)
        holder.linearLayout.setLayoutParams(rf);    //El método "setLayoutParams()" permite agregar la regla creada a su Layout contenedor (FrameLayout)
        holder.hora.setLayoutParams(ll1);   //El método "setLayoutParams()" permite agregar la regla creada a su Layout contenedor (LinearLayout)

        holder.mensaje.setText(mensajeDeTextos.get(position).getMensaje()); //Colocamos cada elemento de la "Lista" en cada "MensajeViewHolder" creado
        holder.hora.setText(mensajeDeTextos.get(position).getHoraDelMensaje()); //Colocamos cada elemento de la "Lista" en cada "MensajeViewHolder" creado

        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP) {    //"if" que permitirá ejecutar las sentencias dependiendo a la versión del ANDROID que se esté usando
            holder.cardView.getBackground().setAlpha(0);    //Aquí especificamos el color de fondo del "cardView"
        }else{
            holder.cardView.setBackgroundColor(ContextCompat.getColor(context,android.R.color.transparent));    //Aquí especificamos que el color del fondo ("setBackgroundColor()")..
                                                                                                                //.. del "cardView" tendrá..
                                                                                                                //..una apariencia "transparent" en su "context"
        }
    }

    @Override
    public int getItemCount() {     //Este método sirve para obtener la cantidad de elements de la "Lista"
        return mensajeDeTextos.size();
    }

    public static class MensajeViewHolder extends RecyclerView.ViewHolder{  //"ViewHolder" es una subclase de "RecyclerView.Adapter"
                                                                            //El constructor de esta clase tendrá como parámetro un "View" creado..
                                                                            //.. en el método "onCreateViewHolder()"

        CardView cardView;
        TextView mensaje;
        TextView hora;
        LinearLayout linearLayout;

        public MensajeViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.CV); //Obtenemos el "cardView" del "View"
            mensaje = (TextView) itemView.findViewById(R.id.TVCM);  //Obtenemos el "TextView" del "View"
            hora = (TextView) itemView.findViewById(R.id.TVCH); //Obtenemos el "TextView" del "View"
            linearLayout = (LinearLayout) itemView.findViewById(R.id.mensajeBG); //Obtenemos el "LinearLayout" del "View"
        }
    }
}
