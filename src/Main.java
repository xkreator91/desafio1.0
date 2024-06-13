import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("0 - Menu");
            System.out.println("1 - Genera un archivo");
            System.out.println("2 - Lee archivo generado");
            System.out.println("3 - Ordena archivo");
            System.out.println("4 - Leer archivo ordenado");
            System.out.println("5 - Buscar numero en archivo");
            System.out.println("6 - Salir");
            System.out.println("Seleccione una opción :");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    generarNumerosAleatorios();
                    break;
                case 2:
                    leerNumerosAleatorios();
                    break;
                case 3:
                    ordenarNumerosEnArchivo();
                    break;
                case 4:
                    leerArchivoOrdenado();
                    break;
                case 5:
                    buscarNumeroEnArchivo();
                    break;
                case 6:
                    System.out.println("Saliendo del programa.");
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }

        scanner.close();
    }

    public static void generarNumerosAleatorios() {
        String rutaArchivo = "numeros.txt";
        int cantidadNumeros = 30;
        Random random = new Random();

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (int i = 0; i < cantidadNumeros; i++) {
                int numeroAleatorio = random.nextInt(100) + 1;
                escritor.write(numeroAleatorio + "\n");
            }
            System.out.printf("Números aleatorios generados y guardados en %s%n", rutaArchivo);
        } catch (IOException e) {
            System.out.printf("Error al generar números aleatorios y guardar en archivo: %s%n", e.getMessage());
        }
    }

    public static void leerNumerosAleatorios() {
        String rutaArchivo = "numeros.txt";

        try (Scanner lector = new Scanner(new File(rutaArchivo))) {
            System.out.println("Números aleatorios en el archivo:");
            while (lector.hasNextLine()) {
                System.out.println(lector.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.printf("No se pudo encontrar el archivo: %s%n", e.getMessage());
        }
    }

    public static void ordenarNumerosEnArchivo() {
        String rutaArchivo = "numeros.txt";
        List<Integer> numeros = new ArrayList<>();

        try (Scanner lector = new Scanner(new File(rutaArchivo))) {
            while (lector.hasNextLine()) {
                numeros.add(Integer.parseInt(lector.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.printf("No se pudo encontrar el archivo: %s%n", e.getMessage());
            return;
        }

        Collections.sort(numeros);

        String rutaArchivoOrdenado = "numeros_ordenados.txt";
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivoOrdenado))) {
            for (int numero : numeros) {
                escritor.write(numero + "\n");
            }
            System.out.printf("Números ordenados guardados en %s%n", rutaArchivoOrdenado);
        } catch (IOException e) {
            System.out.printf("Error al ordenar y guardar números en archivo: %s%n", e.getMessage());
        }
    }

    public static void leerArchivoOrdenado() {
        String rutaArchivoOrdenado = "numeros_ordenados.txt";

        try (Scanner lector = new Scanner(new File(rutaArchivoOrdenado))) {
            System.out.println("Números ordenados en el archivo:");
            while (lector.hasNextLine()) {
                System.out.println(lector.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.printf("No se pudo encontrar el archivo ordenado: %s%n", e.getMessage());
        }
    }

    public static void buscarNumeroEnArchivo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número a buscar: ");
        int numeroBuscar = scanner.nextInt();
        scanner.nextLine();

        String rutaArchivo = "numeros.txt";
        boolean encontrado = false;

        try (Scanner lector = new Scanner(new File(rutaArchivo))) {
            while (lector.hasNextLine()) {
                int numero = Integer.parseInt(lector.nextLine());
                if (numero == numeroBuscar) {
                    encontrado = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("No se pudo encontrar el archivo: %s%n", e.getMessage());
            return;
        }

        if (encontrado) {
            System.out.printf("El número %d fue encontrado en el archivo.%n", numeroBuscar);
        } else {
            System.out.printf("El número %d no fue encontrado en el archivo.%n", numeroBuscar);
        }
    }
}
