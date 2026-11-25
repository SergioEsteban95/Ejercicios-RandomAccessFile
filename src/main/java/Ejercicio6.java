import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio6 {

    private static final String FICHERO = "personas.dat";
    private static final int LONGITUD_NOMBRE = 20;
    private static final int TAM_REGISTRO = 48;
    private static final int OFFSET_EDAD = 44;

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "rw");
             Scanner sc = new Scanner(System.in)) {

            raf.setLength(0);

            if (raf.length() == 0) {
                escribirRegistro(raf, 1, "ANA", 23);
                escribirRegistro(raf, 2, "LUIS", 31);
                escribirRegistro(raf, 3, "MARTA", 27);
            }

            System.out.print("Introduzca un id existente: ");
            int idBuscado = sc.nextInt();

            System.out.print("Introduzca nueva edad: ");
            int nuevaEdad = sc.nextInt();

            raf.seek(0);

            boolean encontrado = false;
            long regInicio = 0;

            while (raf.getFilePointer() < raf.length()) {
                regInicio = raf.getFilePointer();
                int id = raf.readInt();

                if (id == idBuscado) {
                    encontrado = true;
                    break;
                } else {
                    raf.seek(regInicio + TAM_REGISTRO);
                }
            }
            if (!encontrado) {
                System.out.println("No existe el registro");
                return;
            }

            raf.seek(regInicio + OFFSET_EDAD);
            raf.writeInt(nuevaEdad);

            raf.seek(regInicio);
            int id = raf.readInt();
            String nombre = leerCadenaFija(raf, LONGITUD_NOMBRE);
            int edad = raf.readInt();

            System.out.println("Registro actualizado:");
            System.out.println("ID: " + id + " | Nombre: " + nombre + " | Edad: " + edad);
        } catch (IOException e) {
            System.out.println("Error de fichero: " + e.getMessage());
        }

    }

    private static void escribirRegistro(RandomAccessFile raf, int id, String nombre, int edad) throws IOException {
        raf.writeInt(id);

        StringBuilder sb = new StringBuilder(nombre);
        if (sb.length() > LONGITUD_NOMBRE) {
            sb.setLength(LONGITUD_NOMBRE);
        } else {
            while (sb.length() < LONGITUD_NOMBRE) {
                sb.append(' ');
            }
        }

        for (int i = 0; i < LONGITUD_NOMBRE; i++) {
            raf.writeChar(sb.charAt(i));
        }
        raf.writeInt(edad);
    }

    private static String leerCadenaFija(RandomAccessFile raf, int longitud) throws IOException {
        char[] caracteres = new char[longitud];
        for (int i = 0; i < longitud; i++) {
            caracteres[i] = raf.readChar();
        }
        return new String(caracteres).trim();
    }
}
