package proyectocalculo.app;
import java.util.Scanner;
public class Libreria {


    public static String leerCadena(String mensaje) {
        String ecuacion = "";
        System.out.println(mensaje);
        Scanner teclado = new Scanner(System.in);
        ecuacion = teclado.nextLine();
        return ecuacion;
    }

    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

}
