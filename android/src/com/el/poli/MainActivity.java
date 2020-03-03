package com.el.poli;


import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import servicio.Servicio;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void comenzar(View view) {

        Intent intent = new Intent(this,AndroidLauncher.class);
        startActivity(intent);

    }

    public void ser(View view) {
        Intent intent2 = new Intent(this, Servicio.class);
        intent2.putExtra("mensaje","holaila");
        startService(intent2);
    }

    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Quieres salir del juego o salir de matrix?'");
        builder.setMessage("¿De verdad?, hay que estar loco").setPositiveButton("Seguro que si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        builder.create();
        builder.show();

    }

}
