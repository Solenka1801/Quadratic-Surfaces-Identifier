package proyectocalculo.test;

import java.util.Scanner;

import proyectocalculo.exception.EcuacionException;

public class Test {
	public static void main(String args[]) throws EcuacionException{
	Scanner entrada = new Scanner(System.in);
    System.out.println("Ingrese la ecuación: ");
    String ecuacion = entrada.nextLine();
//    ((x-h)^2)/(a^2) + ((y-k)^2)/(b^2) - ((z-l)^2)/(c^2) = 0

    String signos = "";
    signos += buscarSigno(ecuacion, buscarInicioSigno(ecuacion, ecuacion.length() - 1, 'x'), '/') == 1 ? "+" : "-";
    signos += buscarSigno(ecuacion, buscarInicioSigno(ecuacion, ecuacion.length() - 1, 'y'), '/') == 1 ? "+" : "-";
    signos += buscarSigno(ecuacion, buscarInicioSigno(ecuacion, ecuacion.length() - 1, 'z'), '/') == 1 ? "+" : "-";
    int x;
    int y;
    int z;

    if (buscarVariableNumerador(ecuacion, buscarInicio(ecuacion, 0, 'x')).equals("")) {
        x = 0;
    } else {
        x = Integer.parseInt(buscarVariableNumerador(ecuacion, buscarInicio(ecuacion, 0, 'x'))) * -1;
    }

    if (buscarVariableNumerador(ecuacion, buscarInicio(ecuacion, 0, 'y')).equals("")) {
        y = 0;
    } else {
        y = Integer.parseInt(buscarVariableNumerador(ecuacion, buscarInicio(ecuacion, 0, 'y'))) * -1;
    }

    if (buscarVariableNumerador(ecuacion, buscarInicio(ecuacion, 0, 'z')).equals("")) {
        z = 0;
    } else {
        z = Integer.parseInt(buscarVariableNumerador(ecuacion, buscarInicio(ecuacion, 0, 'z'))) * -1;
    }

    System.out.println("Centro (" + x + ", " + y + ", " + z + ")");

    int a = Integer.parseInt(buscarVariableDenominador(ecuacion, buscarInicio(ecuacion, buscarInicio(ecuacion, 0, 'x'), '/')));
    int b = Integer.parseInt(buscarVariableDenominador(ecuacion, buscarInicio(ecuacion, buscarInicio(ecuacion, 0, 'y'), '/')));
    int c = Integer.parseInt(buscarVariableDenominador(ecuacion, buscarInicio(ecuacion, buscarInicio(ecuacion, 0, 'z'), '/')));

    trazaCono(-1 * x, -1 * y, -1 * z, a, b, c, signos);
//    ((x-4)^2)/3 + ((y-5)^2)/6 - ((z-2)^2)/9 = 0
}

public static void trazaCono(int h, int k, int l, double a, double b, double c, String signos) {

    double raizA = Math.sqrt(a);
    double raizB = Math.sqrt(b);
    double raizC = Math.sqrt(c);

    String valorH = String.valueOf(h);
    String valorK = String.valueOf(k);
    String valorL = String.valueOf(l);

    String valorcH = String.valueOf(h * -1);
    String valorcK = String.valueOf(k * -1);
    String valorcL = String.valueOf(l * -1);

    if (h == 0 && k == 0 && l == 0) {
        System.out.println("Traza yz:");

        if (signos.charAt(1) == '-' || signos.charAt(2) == '-') {
            if (signos.charAt(1) == '-') {
                System.out.println("(" + String.format("%.2f", (raizB / raizC)) + ")z = y");
                System.out.println("(" + String.format("%.2f", (-1 * (raizB / raizC))) + ")z = y");
            } else {
                System.out.println("(" + String.format("%.2f", (raizC / raizB)) + ")y = z");
                System.out.println("(" + String.format("%.2f", (-1 * (raizC / raizB))) + ")y = z");
            }
        } else {
            System.out.println("(y^2)/" + b + "+(z^2)/" + c + "=1");
        }

        System.out.println("Traza xz:");

        if (signos.charAt(0) == '-' || signos.charAt(2) == '-') {
            if (signos.charAt(0) == '-') {
                System.out.println("(" + String.format("%.2f", (raizA / raizC)) + ")z = x");
                System.out.println("(" + String.format("%.2f", (-1 * (raizA / raizC))) + ")z = x");
            } else {
                System.out.println("(" + String.format("%.2f", (raizC / raizA)) + ")x = z");
                System.out.println("(" + String.format("%.2f", (-1 * (raizC / raizA))) + ")x = z");
            }
        } else {
            System.out.println("(x^2)/" + a + "+(z^2)/" + c + "=1");
        }

        System.out.println("Traza xy:");

        if (signos.charAt(0) == '-' || signos.charAt(1) == '-') {
            if (signos.charAt(0) == '-') {
                System.out.println("(" + String.format("%.2f", (raizA / raizB)) + ")y = x");
                System.out.println("(" + String.format("%.2f", (-1 * (raizA / raizB))) + ")y = x");
            } else {
                System.out.println("(" + String.format("%.2f", (raizB / raizA)) + ")x = y");
                System.out.println("(" + String.format("%.2f", (-1 * (raizB / raizA))) + ")x = y");
            }
        } else {
            System.out.println("(x^2)/" + a + "+(y^2)/" + b + "=1");
        }

    } else {

        System.out.println("Traza yz:");

        if (signos.charAt(1) == '-' || signos.charAt(2) == '-') {
            if (signos.charAt(1) == '-') {
                System.out.println((k != 0 ? "(" : "") + String.format("%.2f", (raizB / raizC)) + "(z" + (l != 0 ? (l > 0 ? "+" + valorL : valorL) : "") + ")" + (k != 0 ? ")" + ((k * -1) > 0 ? "+" + valorcK : valorcK) : "") + " = y");
                System.out.println((k != 0 ? "(" : "") + String.format("%.2f", (-1 * (raizB / raizC))) + "(z" + (l != 0 ? (l > 0 ? "+" + valorL : valorL) : "") + ")" + (k != 0 ? ")" + ((k * -1) > 0 ? "+" + valorcK : valorcK) : "") + " = y");
            } else {
                System.out.println((l != 0 ? "(" : "") + String.format("%.2f", (raizC / raizB)) + "(y" + (k != 0 ? (k > 0 ? "+" + valorK : valorK) : "") + ")" + (l != 0 ? ")" + ((l * -1) > 0 ? "+" + valorcL : valorcL) : "") + " = z");
                System.out.println((l != 0 ? "(" : "") + String.format("%.2f", (-1 * (raizC / raizB))) + "(y" + (k != 0 ? (k > 0 ? "+" + valorK : valorK) : "") + ")" + (l != 0 ? ")" + ((l * -1) > 0 ? "+" + valorcL : valorcL) : "") + " = z");
            }
        } else {
            System.out.println((k != 0 ? "(" : "") + "(y" + (k != 0 ? (k > 0 ? "+" + valorK : valorK) + ")" : "") + "^2)/" + b + "+" + (l != 0 ? "(" : "") + "(z" + (l != 0 ? (l > 0 ? "+" + valorL : valorL) + ")" : "") + "^2)/" + c + "=1");
        }

        System.out.println("Traza xz:");

        if (signos.charAt(0) == '-' || signos.charAt(2) == '-') {
            if (signos.charAt(0) == '-') {
                System.out.println((h != 0 ? "(" : "") + String.format("%.2f", (raizA / raizC)) + "(z" + (l != 0 ? (l > 0 ? "+" + valorL : valorL) : "") + ")" + (h != 0 ? ")" + ((h * -1) > 0 ? "+" + valorcH : valorcH) : "") + " = x");
                System.out.println((h != 0 ? "(" : "") + String.format("%.2f", (-1 * (raizA / raizC))) + "(z" + (l != 0 ? (l > 0 ? "+" + valorL : valorL) : "") + ")" + (h != 0 ? ")" + ((h * -1) > 0 ? "+" + valorcH : valorcH) : "") + " = x");
            } else {
                System.out.println((l != 0 ? "(" : "") + String.format("%.2f", (raizC / raizA)) + "(x" + (h != 0 ? (h > 0 ? "+" + valorH : valorH) : "") + ")" + (l != 0 ? ")" + ((l * -1) > 0 ? "+" + valorcL : valorcL) : "") + " = z");
                System.out.println((l != 0 ? "(" : "") + String.format("%.2f", (-1 * (raizC / raizA))) + "(x" + (h != 0 ? (h > 0 ? "+" + valorH : valorH) : "") + ")" + (l != 0 ? ")" + ((l * -1) > 0 ? "+" + valorcL : valorcL) : "") + " = z");
            }
        } else {
            System.out.println((h != 0 ? "(" : "") + "(x" + (h != 0 ? (h > 0 ? "+" + valorH : valorH) + ")" : "") + "^2)/" + a + "+" + (l != 0 ? "(" : "") + "(z" + (l != 0 ? (l > 0 ? "+" + valorL : valorL) + ")" : "") + "^2)/" + c + "=1");
        }

        System.out.println("Traza xy:");

        if (signos.charAt(0) == '-' || signos.charAt(1) == '-') {
            if (signos.charAt(0) == '-') {
                System.out.println((h != 0 ? "(" : "") + String.format("%.2f", (raizA / raizB)) + "(y" + (k != 0 ? (k > 0 ? "+" + valorK : valorK) : "") + ")" + (h != 0 ? ")" + ((h * -1) > 0 ? "+" + valorcH : valorcH) : "") + " = x");
                System.out.println((h != 0 ? "(" : "") + String.format("%.2f", (-1 * (raizA / raizB))) + "(y" + (k != 0 ? (k > 0 ? "+" + valorK : valorK) : "") + ")" + (h != 0 ? ")" + ((h * -1) > 0 ? "+" + valorcH : valorcH) : "") + " = x");
            } else {
                System.out.println((k != 0 ? "(" : "") + String.format("%.2f", (raizB / raizA)) + "(x" + (h != 0 ? (h > 0 ? "+" + valorH : valorH) : "") + ")" + (k != 0 ? ")" + ((k * -1) > 0 ? "+" + valorcK : valorcK) : "") + " = y");
                System.out.println((k != 0 ? "(" : "") + String.format("%.2f", (-1 * (raizB / raizA))) + "(x" + (h != 0 ? (h > 0 ? "+" + valorH : valorH) : "") + ")" + (k != 0 ? ")" + ((k * -1) > 0 ? "+" + valorcK : valorcK) : "") + " = y");
            }
        } else {
            System.out.println((h != 0 ? "(" : "") + "(x" + (h != 0 ? (h > 0 ? "+" + valorH : valorH) + ")" : "") + "^2)/" + a + "+" + (k != 0 ? "(" : "") + "(y" + (k != 0 ? (k > 0 ? "+" + valorK : valorK) + ")" : "") + "^2)/" + b + "=1");
        }
    }
}

public static boolean esNumero(String ecuacion, int i) {

    char ecuacionChar = ecuacion.charAt(i);
    return "0123456789".contains(String.valueOf(ecuacionChar).toLowerCase());
}

public static int buscarInicio(String ecuacion, int i, char variable) {
    if (ecuacion.charAt(i) == variable) {
        return i;
    } else {
        return buscarInicio(ecuacion, i + 1, variable);
    }
}

public static int buscarInicioSigno(String ecuacion, int i, char variable) {
    if (ecuacion.charAt(i) == variable) {
        return i;
    } else {
        return buscarInicioSigno(ecuacion, i - 1, variable);
    }
}

public static int buscarSigno(String ecuacion, int i, char llegada) {
    if (i == -1) {
        return 1;
    } else {
        if (ecuacion.charAt(i) == llegada) {
            return 1;
        } else {
            if (ecuacion.charAt(i) == '+') {
                return 1;
            }
            if (ecuacion.charAt(i) == '-') {
                return 0;
            }
            return buscarSigno(ecuacion, i - 1, llegada);
        }
    }

}

public static String buscarVariableNumerador(String ecuacion, int i) {
    if (i < ecuacion.length()) {
        if (ecuacion.charAt(i) == ')') {
            return "";
        } else {
            if (ecuacion.charAt(i) == '-') {
                return "-" + buscarVariableNumerador(ecuacion, i + 1);
            }
            if (esNumero(ecuacion, i)) {
                return ecuacion.charAt(i) + buscarVariableNumerador(ecuacion, i + 1);
            }
            return buscarVariableNumerador(ecuacion, i + 1);
        }
    } else {
        return "";
    }
}

public static String buscarVariableDenominador(String ecuacion, int i) {
    if (i < ecuacion.length()) {
        if (ecuacion.charAt(i) == '+' || ecuacion.charAt(i) == '-' || ecuacion.charAt(i) == '=') {
            return "";
        } else {
            if (ecuacion.charAt(i) == '-') {
                return "-" + buscarVariableDenominador(ecuacion, i + 1);
            }
            if (esNumero(ecuacion, i)) {
                return ecuacion.charAt(i) + buscarVariableDenominador(ecuacion, i + 1);
            }
            return buscarVariableDenominador(ecuacion, i + 1);
        }
    } else {
        return "";
    }
}

}
