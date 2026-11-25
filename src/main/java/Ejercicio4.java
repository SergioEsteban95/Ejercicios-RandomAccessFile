import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio4 {
    public static void main(String[] args) throws IOException {

        try (RandomAccessFile raf = new RandomAccessFile("nombres.bin", "rw")) {
            raf.setLength(0);
            raf.writeUTF("Ana");
            raf.writeUTF("Luis");
            raf.writeUTF("Marta");

            raf.seek(0);
            System.out.println(raf.readUTF());
            System.out.println(raf.readUTF());
            System.out.println(raf.readUTF());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}