package com.herprogramacion.restaurantericoparico.modelo;

import com.herprogramacion.restaurantericoparico.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/03/17.
 */

public class Comidas {
    public static List<Comida> getComidasPopulares() {
        return COMIDAS_POPULARES;
    }

    public static List<Comida> getBEBIDAS() {
        return BEBIDAS;
    }

    public static List<Comida> getPOSTRES() {
        return POSTRES;
    }

    public static List<Comida> getPLATILLOS() {
        return PLATILLOS;
    }

    public static final List<Comida> COMIDAS_POPULARES = new ArrayList<Comida>();
    public static final List<Comida> BEBIDAS = new ArrayList<>();
    public static final List<Comida> POSTRES = new ArrayList<>();
    public static final List<Comida> PLATILLOS = new ArrayList<>();

    static {
        COMIDAS_POPULARES.add(new Comida(5.4f, "Beauty and the Beast", R.drawable.beea,"Explore the mysterious and dangerous home of the king of the apes as a team of explorers ventures deep inside the treacherous, primordial island.",3f,0,305,"03/17/2017"));
        COMIDAS_POPULARES.add(new Comida(5.5f, "A Cure for Wellness", R.drawable.cure,"Descripcion",3f,1,778,"03/24/2017"));
        COMIDAS_POPULARES.add(new Comida(0.0f, "Ghost in the Shell", R.drawable.gost,"Descripcion",3f,2,34,"03/31/2017"));
        COMIDAS_POPULARES.add(new Comida(6.0f, "Grave", R.drawable.gr,"Descripcion",3f,3,1168,"03/17/2017"));
        COMIDAS_POPULARES.add(new Comida(6.3f, "Imperium", R.drawable.imp,"Descripcion",3f,4,589,"03/17/2017"));

        PLATILLOS.add(new Comida(5, "Camarones Tismados", R.drawable.camarones,"Descripcion",3f,5,589,"03/17/2017"));
        PLATILLOS.add(new Comida(3.2f, "Rosca Herbárea", R.drawable.rosca,"Descripcion",3f,6,589,"03/17/2017"));
        PLATILLOS.add(new Comida(12f, "Sushi Extremo", R.drawable.sushi,"Descripcion",3f,7,589,"03/17/2017"));
        PLATILLOS.add(new Comida(9, "Sandwich Deli", R.drawable.sandwich,"Descripcion",3f,8,589,"03/17/2017"));
        PLATILLOS.add(new Comida(34f, "Lomo De Cerdo Austral", R.drawable.lomo_cerdo,"Descripcion",3f,9,589,"03/17/2017"));

            BEBIDAS.add(new Comida(3, "Taza de Café", R.drawable.cafe,"Descripcion",3f,10,589,"03/17/2017"));
            BEBIDAS.add(new Comida(12, "Coctel Tronchatoro", R.drawable.coctel,"Descripcion",3f,11,589,"03/17/2017"));
            BEBIDAS.add(new Comida(5, "Jugo Natural", R.drawable.jugo_natural,"Descripcion",3f,12,589,"03/17/2017"));
            BEBIDAS.add(new Comida(24, "Coctel Jordano", R.drawable.coctel_jordano,"Descripcion",3f,13,589,"03/17/2017"));
            BEBIDAS.add(new Comida(30, "Botella Vino Tinto Darius", R.drawable.vino_tinto,"Descripcion",3f,14,589,"03/17/2017"));

        POSTRES.add(new Comida(2, "Postre De Vainilla", R.drawable.postre_vainilla,"Descripcion",3f,15,589,"03/17/2017"));
        POSTRES.add(new Comida(3, "Flan Celestial", R.drawable.flan_celestial,"Descripcion",3f,16,589,"03/17/2017"));
        POSTRES.add(new Comida(2.5f, "Cupcake Festival", R.drawable.cupcakes_festival,"Descripcion",3f,17,589,"03/17/2017"));
        POSTRES.add(new Comida(4, "Pastel De Fresa", R.drawable.pastel_fresa,"Descripcion",3f,18,589,"03/17/2017"));
        POSTRES.add(new Comida(5, "Muffin Amoroso", R.drawable.muffin_amoroso,"Descripcion",3f,19,589,"03/17/2017"));
    }
    public static Comida getCourseByPosition(List<Comida> items,int position) {
        if(items == POSTRES){
            return items.get(position);
        }else if(items == PLATILLOS){
            return items.get(position);
        }else if(items == BEBIDAS){
            return items.get(position);
        }else{
            return COMIDAS_POPULARES.get(position);
        }

    }
}


