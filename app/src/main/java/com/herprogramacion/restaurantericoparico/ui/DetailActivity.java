package com.herprogramacion.restaurantericoparico.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.modelo.Comida;
import com.herprogramacion.restaurantericoparico.modelo.Comidas;

import java.util.List;


public class DetailActivity  extends AppCompatActivity {
    private static final String EXTRA_POSITION = "com.herprogramacion.cursospoint.extra.POSITION";
    private static int position;
    private static List<Comida> itemss;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toast alert = Toast.makeText(this,"Valor Position"+position,Toast.LENGTH_LONG);
        alert.show();


        setToolbar(); // Reemplazar la action bar

        // Se obtiene la posición del item seleccionado
        int position = getIntent().getIntExtra(EXTRA_POSITION, -1);
        // Carga los datos en la vista
        setupViews(itemss,position);
    }
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)// Habilitar Up Button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setupViews(List<Comida> items,int position) {
        this.position = position;
        TextView name = (TextView) findViewById(R.id.detail_name);
        TextView description = (TextView) findViewById(R.id.detail_description);
        TextView author = (TextView) findViewById(R.id.detail_author);
        TextView price = (TextView) findViewById(R.id.detail_price);
        RatingBar rating = (RatingBar) findViewById(R.id.detail_rating);
        ImageView image = (ImageView) findViewById(R.id.detail_image);

        // Obtiene el curso ha detallar basado en la posición
        Comida detailCourse = Comidas.getCourseByPosition(items,position);
        name.setText(detailCourse.getNombre());
        description.setText(detailCourse.getDescripcion());
        author.setText("Creado Por:" + detailCourse.getNombre());
        price.setText(detailCourse.getRating()+"/10");
        rating.setRating(detailCourse.getRating());
        Glide.with(this).load(detailCourse.getIdDrawable()).into(image);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
                // Obtener intent de la actividad padre
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                // Comprobar si DetailActivity no se creó desde CourseActivity
                if (NavUtils.shouldUpRecreateTask(this, upIntent) || this.isTaskRoot()) {
                    // Construir de nuevo la tarea para ligar ambas actividades
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Terminar con el método correspondiente para Android 5.x
                    this.finishAfterTransition();
                    return true;
                }
                // Dejar que el sistema maneje el comportamiento del up button
                return false;
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public static void launch(Activity context, int position, View sharedView ,List<Comida> items) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_POSITION, position);
        itemss=items;
        context.startActivity(intent);
    }
}
