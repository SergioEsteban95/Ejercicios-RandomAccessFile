import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio1 {

    private static final String FICHERO = "empleados.dat";
    private static final int LONGITUD_APELLIDO = 10;
    private static final int TAMAÑO_REGISTRO = 4 + (LONGITUD_APELLIDO * 2) + 4 + 8;

    public static void main(String[] args) {
        String[] apellidos = {"GOMEZ", "RUIZ", "LOPEZ", "MARTIN"};
        int[] departamentos = {10, 20, 10, 30};
        double[] salarios = {1500.50, 1725.80, 2100.00, 1899.95};

        try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "rw")) {

            raf.setLength(0);

            for (int i = 0; i < apellidos.length; i++) {
                int id = i + 1;

                raf.writeInt(id);
                escribirCadenaFija(raf, apellidos[i], LONGITUD_APELLIDO);
                raf.writeInt(departamentos[i]);
                raf.writeDouble(salarios[i]);
            }

            raf.seek(0);

            while (raf.getFilePointer() < raf.length()) {
                int id = raf.readInt();
                String apellido = leerCadenaFija(raf, LONGITUD_APELLIDO);
                int departamento = raf.readInt();
                double salario = raf.readDouble();

                System.out.println("ID: " + id
                        + " | Apellido: " + apellido
                        + " | Departamento " + departamento
                        + " | Salario " + String.format("%.2f", salario));

            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void escribirCadenaFija(RandomAccessFile raf, String texto, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder(texto);

        if (sb.length() > longitud) {
            sb.setLength(longitud);
        } else {
            while (sb.length() < longitud) {
                sb.append(' ');
            }
        }

        for (int i = 0; i < longitud; i++) {
            raf.writeChar(sb.charAt(i));
        }
    }

    private static String leerCadenaFija(RandomAccessFile raf, int longitud) throws IOException {
        char[] caracteres = new char[longitud];
        for (int i = 0; i < longitud; i++) {
            caracteres[i] = raf.readChar();
        }
        return new String(caracteres).trim();
    }
}