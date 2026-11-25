import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio2 {
    private static final String FICHERO = "nums.bin";

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "rw");
             Scanner sc = new Scanner(System.in)) {

            raf.setLength(0);

            System.out.println("Introduce 5 números enteros: ");
            for (int i = 0; i < 5; i++) {
                System.out.print("Número " + (i + 1) + ": ");
                int numero = sc.nextInt();
                raf.writeInt(numero);
            }

            System.out.println("getFilePointer() = " + raf.getFilePointer());

            raf.seek(0);

            System.out.println("Lectura de los 5 números guardados: ");
            for (int i = 0; i < 5; i++) {
                System.out.print(raf.readInt() + " ");
            }
            System.out.println();


        } catch (IOException e) {
            System.out.println("Error de fichero: " + e.getMessage());
        }
    }
}
