package basededatosdesktop;

import com.badlogic.gdx.utils.compression.lzma.Base;
import com.el.poli.basededatos.BaseDeDatos;

public class BaseDatosDesktop implements BaseDeDatos {
    @Override
    public int cargar() {
        return 5;
    }

    @Override
    public void guardar(int puntuacion) {

    }
}
