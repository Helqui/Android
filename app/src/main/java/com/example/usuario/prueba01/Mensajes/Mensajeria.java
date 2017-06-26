package com.example.usuario.prueba01.Mensajes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.usuario.prueba01.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 26/05/2017.
 */

public class Mensajeria extends AppCompatActivity {

    private RecyclerView recyclerView;  //Objeto que contendrá la lista de mensajes
    private List<MensajeDeTexto> mensajeDeTextos;   //Listado de mensajes que tendrán que ser puestos..
                                                    //..en el "recyclerView" mediante un "MensajesAdapter"
    private MensajesAdapter adapter;    //Permitirá intruducir la "List<MensajeDeTexto>" al "RecyclerView"

    private Button btnmensaje;
    private EditText etmensaje;

    private int TEXT_LINE=1;    //Contador que permitirá contabilizar la cantidad de líneas que contiene el mensaje en el "etmensaje"

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajeria);
        Toolbar toolbar = (Toolbar) findViewById(R.id.Toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.RV);
        btnmensaje = (Button) findViewById(R.id.B);
        etmensaje = (EditText) findViewById(R.id.ETM);

        mensajeDeTextos = new ArrayList<>();    //Instanciamos la lista que contendrá los mensajes
/*
        for(int i = 0; i <10;i++){
            MensajeDeTexto mm = new MensajeDeTexto();
            mm.setId("0");
            mm.setMensaje("emisojjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjgggggggggr"+i);
            mm.setTipoMensaje(1);
            mm.setHoraDelMensaje("10:40");
            mensajeDeTextos.add(mm);
        }

        for(int i = 0; i <10;i++){
            MensajeDeTexto mm = new MensajeDeTexto();
            mm.setId("0");
            mm.setMensaje("receptorgggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg"+i);
            mm.setTipoMensaje(2);
            mm.setHoraDelMensaje("10:40");
            mensajeDeTextos.add(mm);
        }
*/

        adapter = new MensajesAdapter(mensajeDeTextos,this);
        recyclerView.setAdapter(adapter);

        etmensaje.addTextChangedListener(new TextWatcher() {    //El "etmensaje" llamará a su método "addTextChangedListener()" para poder lograr que..
                                                                //.. cada vez que el "etmensaje" aumente su tamaño, el último mensaje contenido en el..
                                                                //.. "recyclerView" tenga el foco.
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {   //Método que es llamado cada vez que se ingresa texto o digitos..
                                                                                            //..en el "etmensaje"
                if(etmensaje.getLayout().getLineCount() != TEXT_LINE){  //"if" que permitirá que cada vez que exista una diferencia entre las cantidades de lineas que tiene el..
                                                                        //.."etmensaje" se procederá a colocar el foco en el último mensaje contenido en el "recyclerView"
                    setScrollbarChat(); //Con esto se colocará el foco en el último mensaje ingresado en el "recyclerView"
                    TEXT_LINE = etmensaje.getLayout().getLineCount(); //El valor de la cantidad de lineas que tiene el "etmensaje" será trasladada al "TEXT_LINE"
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        LinearLayoutManager lm = new LinearLayoutManager(this); //Aquí creamos un "LinearLayoutManager (Context)"..
                                                                //..que hereda de la clase "RecyclerView.LayoutManager"..
                                                                //.. el cual crea un objeto "lm" que tiene la orientación..
                                                                //.. vertical por defecto. El cual servirá para que los mensajes..
                                                                //.. que contendrá el "recyclerView" aparezcan en forma vertical
        recyclerView.setLayoutManager(lm);  //Asignamos el "lm" a "recyclerView"

        btnmensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMensaje(etmensaje.getText().toString());
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {   //Este evento sirve para que cuando pulsemos en el "toolbar", finalizemos..
                                                                            //.. el activity (Mensajeria)
            @Override
            public void onClick(View v) {
                finish();;
            }
        });

        setScrollbarChat(); //Este método sirve para posicionar el foco en el último mensaje ingresado en el "RecyclerView"
    }

    public void createMensaje(String mensaje){
        MensajeDeTexto m = new MensajeDeTexto();
        m.setId("0");
        m.setMensaje(mensaje);
        m.setTipoMensaje(2);
        m.setHoraDelMensaje("10:40");
        mensajeDeTextos.add(m); //Cuando se agrega un objeto "m" a un "ArrayList", el objeto "m" automaticamente aparecerá en todas las instancias..
                                //.. de dicho objeto "ArrayList"
        adapter.notifyDataSetChanged(); //Este método sirve para avisar al "adapter" que su conjunto de datos (List<>) a cambiado
        etmensaje.setText("");
        setScrollbarChat(); //Este método sirve para posicionar el foco en el último mensaje ingresado en el "RecyclerView"
    }

    public void setScrollbarChat(){ //Este método sirve para posicionar el foco en el último mensaje ingresado en el "RecyclerView"
        recyclerView.scrollToPosition(adapter.getItemCount()-1);
    }
}
