package Controlador;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * Guarda y carga caché en 2 pasos 1. Guarda/carga en memoria (inevitable) 2.
 * Guarda/carga en TMP/qr
 *
 * @author ian
 */
public class ControladorQR {

    // Usa como key el hash de RegistroDocentes#toString
    // Usa como value una copia de la imagen generada por #generarQR
    private final static HashMap<Integer, byte[]> cache = new HashMap();
    private final static String CACHE_DIR = System.getProperty("java.io.tmpdir") + File.separator + "qr";
    private final static Logger l = Logger.getLogger(ControladorQR.class.getName());

    private static final String CARACTERES_POSIBLES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static ImageIcon generarQRAleatorio(int width, int height) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(20);

        for (int i = 0; i < sb.capacity(); i++) {
            int indiceCaracter = random.nextInt(CARACTERES_POSIBLES.length());
            char caracterAleatorio = CARACTERES_POSIBLES.charAt(indiceCaracter);
            sb.append(caracterAleatorio);
        }
        return new ImageIcon(generarQR(sb.toString(), width, height));
    }

    private static byte[] generarQR(String info, int width, int height) {
        ByteArrayOutputStream out = QRCode.from(info).to(ImageType.PNG).withSize(width, height).stream();
        return out.toByteArray();
    }

    static {
        new File(CACHE_DIR).mkdirs();
    }

    public static ImageIcon loadImage(RegistroDocentes rd, int width, int height) {
        final int key = rd.toString().hashCode();
        byte[] bytes = new byte[0];
        final Path path = Paths.get(CACHE_DIR, key + ".jpg");
        if (cache.containsKey(key)) {
            // Existe en caché
            bytes = cache.get(key);
            l.log(Level.INFO, "QR obtenido desde caché");
        } else {
            // No existe en caché
            l.log(Level.INFO, "QR no encontrado en caché");
            try {
                // Existe un archivo con el hash?
                final File f = new File(CACHE_DIR, key + ".jpg");
                if (f.exists()) {
                    l.log(Level.INFO, "QR encontrado en " + path.toString());
                    bytes = Files.readAllBytes(path);
                } else {
                    // No hay archivo con el hash
                    bytes = generarQR(rd.toString(), width, height);
                    // Guarda en TMP
                    Files.write(path, bytes);
                    l.log(Level.INFO, "QR guardado en " + path.toString());
                }

                // Guarda en memoria
                cache.put(key, bytes);
                l.log(Level.INFO, "QR guardado en caché");

            } catch (IOException ex) {
                Logger.getLogger(ControladorQR.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new ImageIcon(bytes);
    }
}
