Proyecto en Java con ejercicios prácticos sobre acceso secuencial y directo a ficheros binarios utilizando `RandomAccessFile`.

## Estructura del proyecto

Cada ejercicio está implementado en su propia clase principal dentro de `src/main/java`:

* **`Ejercicio1.java`** (`empleados.dat`): Escritura y lectura secuencial de registros fijos de empleados de 36 bytes (`id: int`, `apellido: 10 char`, `departamento: int`, `salario: double`).
* **`Ejercicio2.java`** (`nums.bin`): Escritura secuencial de 5 enteros introducidos por consola, comprobación de `getFilePointer()` (20 bytes) y lectura desde el inicio con `seek(0)`.
* **`Ejercicio3.java`** (`slots.bin`): Escritura en slots fijos de 4 bytes y acceso directo al segundo elemento mediante cálculo de offset (`slot * 4L`).
* **`Ejercicio4.java`** (`nombres.bin`): Escritura y lectura secuencial de cadenas de longitud variable mediante `writeUTF` y `readUTF`.
* **`Ejercicio5.java`** (`registros_fijos.dat`): Definición de registros fijos de 24 bytes (`id` + `nombre` de 10 caracteres) y acceso directo al tercer registro (`2 * 24 = 48`).
* **`Ejercicio6.java`** (`personas.dat`): Búsqueda secuencial en registros fijos de 48 bytes y actualización parcial en disco únicamente del campo `edad` (`regInicio + 44`) sin sobrescribir el resto del registro.
* **`Ejercicio7.java`** (`personas.dat` y `personas.idx`): Generación de un fichero índice auxiliar con entradas fijas de 12 bytes (`id: int` + `offset: long`) para localizar y leer registros directamente mediante `seek(offset)`.