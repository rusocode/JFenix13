package org.gszone.jfenix13.containers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import org.gszone.jfenix13.objects.TextureData;

/**
 * Manejador de las texturas del juego
 * TODO: usar la estructura Hash de Java!!!
 * TODO: PONER LIMITE DE MEMORIA Y BORRAR LAS TEXTURAS MAS VIEJAS!!!
 *
 * hash: array de array de TextureData. Se usa para agiliar la busqueda de texturas.
 */
public class Textures {

    private Array<TextureData>[] hash;

    public Textures() {
        hash = new Array[350];
    }

    /**
     * Obtiene una textura a partir de su nÃºmero de grÃ¡fico (si no existe la crea)
     */
    public Texture getTexture(int num) {
        // Determino en que posiciÃ³n del Hash va la textura solicitada
        int hashIndex = num % hash.length;

        // Si es la primera textura en esa posiciÃ³n del Hash, creo el array y la textura solicitada
        if (hash[hashIndex] == null) {
            hash[hashIndex] = new Array<>();
            return createTexture(num);
        }

        // De lo contrario, la busco...
        else {
            for (int i = 0; i < hash[hashIndex].size; i++) {
                TextureData td = hash[hashIndex].get(i);
                if (td.getNum() == num) return
                        td.getTex();
            }
        }

        // Si no la encontrÃ³, la crea...
        return createTexture(num);
    }

    /**
     * Crea una textura
     */
    private Texture createTexture(int num) {
        // Creo la nueva textura y la agrego en el array de la posiciÃ³n correspondiente del Hash
        TextureData td = new TextureData(num);
        hash[num % hash.length].add(td);
        return td.getTex();
    }

    /**
     * Elimina todas las texturas de la placa de video
     */
    public void dispose() {
        for (Array<TextureData> h : hash)
            if (h != null)
                for (int i = 0; i < h.size; i++)
                    h.get(i).dispose();
    }
}
