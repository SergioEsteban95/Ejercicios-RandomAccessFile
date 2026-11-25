import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio3 {

    private static final String FICHERO = "slots.bin";

    public static void main(String[] args) {
        int[] numeros = {11, 22, 33, 44, 55};

        try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "rw")) {
            raf.setLength(0);

            for (int i = 0; i < numeros.length; i++) {
                long offset = i * 4L;
                raf.seek(offset);
                raf.writeInt(numeros[i]);
            }

            raf.seek(4);
            int segundoValor = raf.readInt();

            System.out.println("El segundo valor leído directamente: " + segundoValor);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
