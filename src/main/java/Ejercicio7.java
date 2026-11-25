import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio7 {

    private static final String FICHERO_DATOS = "personas.dat";
    private static final String FICHERO_INDICE = "personas.idx";
    private static final int LONGITUD_NOMBRE = 20;
    private static final int TAM_REGISTRO = 48;

    public static void main(String[] args) {
        try (RandomAccessFile datos = new RandomAccessFile(FICHERO_DATOS, "rw");
             RandomAccessFile indice = new RandomAccessFile(FICHERO_INDICE, "rw");
             Scanner sc = new Scanner(System.in)) {

            datos.setLength(0);

            if (datos.length() == 0) {
                escribirRegistro(datos, 1, "ANA", 23);
                escribirRegistro(datos, 2, "LUIS", 31);
                escribirRegistro(datos, 3, "MARTA", 27);
            }

            construirIndice(datos, indice);

            System.out.println("Introduce el id a buscar: ");
            int idBuscado = sc.nextInt();

            Long offsetEncontrado = buscarOffsetPorId(indice, idBuscado);

            if (offsetEncontrado == null) {
                System.out.println("No existe el id en el registro. ");
                return;
            }

            datos.seek(offsetEncontrado);

            int id = datos.readInt();
            String nombre = leerCadenaFija(datos, LONGITUD_NOMBRE);
            int edad = datos.readInt();

            System.out.println("Registro encontrado usando el indice. ");
            System.out.println("ID: " + id + " | Nombre: " + nombre + " | Edad: " + edad);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void construirIndice(RandomAccessFile datos, RandomAccessFile indice) throws IOException {
        datos.seek(0);
        indice.setLength(0);

        while (datos.getFilePointer() < datos.length()) {
            long offset = datos.getFilePointer();
            int id = datos.readInt();

            indice.writeInt(id);
            indice.writeLong(offset);

            datos.seek(offset + TAM_REGISTRO);
        }
    }

    private static Long buscarOffsetPorId(RandomAccessFile indice, int idBuscado) throws IOException {
        indice.seek(0);

        while (indice.getFilePointer() < indice.length()) {
            int id = indice.readInt();
            long offset = indice.readLong();

            if (id == idBuscado) {
                return offset;
            }
        }
        return null;

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