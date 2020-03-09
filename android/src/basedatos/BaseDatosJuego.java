package basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.el.poli.basededatos.BaseDeDatos;

public class BaseDatosJuego implements BaseDeDatos {

    private BDOpenHelper openHelper;

    public BaseDatosJuego(Context c){
        openHelper=new BDOpenHelper(c,1);
    }


    @Override
    public int cargar() {
        SQLiteDatabase db=openHelper.getWritableDatabase();
        Cursor cursor=db.query("puntosBolas",
                null,null,null,
                null,null,null);
        if(cursor.moveToFirst()){//False si no hay ninguna fila, true si hay una
            //Caso en que ya haya una fila
            return cursor.getInt(cursor.getColumnIndex("puntos"));
        }else{
            //Si no hay puntuaciones guardadas, empiezo desde 0 puntos
            return 0;
        }
    }

    @Override
    public void guardar(int puntuacion) {
        SQLiteDatabase db=openHelper.getWritableDatabase();
        Cursor cursor=db.query("puntosBolas",
                null,null,null,
                null,null,null);

        ContentValues contentValuesv=new ContentValues();
        contentValuesv.put("puntos",puntuacion);

        if(cursor.moveToFirst()){ //False si no hay ninguna fila, true si hay un
            //todas las filas, es decir, la única que existe.
            db.update("puntosBolas",contentValuesv,null,
                    null);
        }else{
            //Caso en que la tabla esté vacía
            db.insert("puntosBolas",null,contentValuesv);
        }
        cursor.close();
        db.close();
    }
}
