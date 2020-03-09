package basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDOpenHelper extends SQLiteOpenHelper {

    public BDOpenHelper(Context c, int version){
        super(c,"baseDatosJuego",null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table puntosBolas(puntos int(3) primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
