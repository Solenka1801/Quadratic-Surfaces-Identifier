package proyectocalculo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import proyectocalculo.exception.EcuacionException;

public class Ecuacion {

    public int posC;

    public String ecuacion;

    private String xi, yi, zi;
    private double xn, yn, zn;
    private int posx, posy, posz;
    private int h, k, l, ig;
    private double a;
    private double p;

    private double b;

    private double c;

    private double r;
    private char vrbl;

    public int x;
    public int y;
    public int z;

    char[] ordenV = new char[3];
    double[] orden = new double[3];

    public Ecuacion() {

    }

    public Ecuacion(String ecuacion) {
        this.ecuacion = ecuacion;
    }

    private String trazasEsfera(Ecuacion nuevaecuacion) {
        int i = 0;
        if (buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'x')).isEmpty()) {
            x = 0;
        } else {
            x = Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'x'))) * -1;
        }
        if (buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'y')).isEmpty()) {
            y = 0;
        } else {
            y = Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'y'))) * -1;
        }
        if (buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'z')).isEmpty()) {
            z = 0;
        } else {
            z = Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'z'))) * -1;
        }
        double r = Math.sqrt(Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, '='))));
        return "Centro (" + x + ", " + y + ", " + z + ")"
                + "\nRadio= " + r
                + "\n" + trazasEsfera('x', r)
                + "\n" + trazasEsfera('y', r)
                + "\n" + trazasEsfera('z', r);
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

    public static String buscarVariable(String ecuacion, int i) {
        if (i < ecuacion.length()) {
            if (ecuacion.charAt(i) == ')') {
                return "";
            } else {
                if (ecuacion.charAt(i) == '-') {
                    return "-" + buscarVariable(ecuacion, i + 1);
                }
                if (esNumero(ecuacion, i)) {
                    return ecuacion.charAt(i) + buscarVariable(ecuacion, i + 1);
                }
                return buscarVariable(ecuacion, i + 1);
            }
        } else {
            return "";
        }
    }

    public static String trazasEsfera(char variable, double r) {
        if (variable == 'x') {
            return "Traza (y,z): \tPuntos y: (" + String.format("%.2f", r).replaceFirst(",", ".") + " , " + String.format("%.2f", r * -1).replaceFirst(",", ".") + ")\n"
                    + "\t\tPuntos z: (" + String.format("%.2f", r).replaceFirst(",", ".") + " , " + String.format("%.2f", r * -1).replaceFirst(",", ".") + ")";
        }
        if (variable == 'y') {
            return "Traza (x,z): \tPuntos x: (" + String.format("%.2f", r).replaceFirst(",", ".") + " , " + String.format("%.2f", r * -1).replaceFirst(",", ".") + ")\n"
                    + "\t\tPuntos z: (" + String.format("%.2f", r).replaceFirst(",", ".") + " , " + String.format("%.2f", r * -1).replaceFirst(",", ".") + ")";
        }
        if (variable == 'z') {
            return "Traza (x,y): \tPuntos x: (" + String.format("%.2f", r).replaceFirst(",", ".") + " , " + String.format("%.2f", r * -1).replaceFirst(",", ".") + ")\n"
                    + "\t\tPuntos y: (" + String.format("%.2f", r).replaceFirst(",", ".") + " , " + String.format("%.2f", r * -1).replaceFirst(",", ".") + ")";
        }
        return "";
    }

    private String trazasElipsoide(Ecuacion nuevaecuacion) {

        ordenVariables(nuevaecuacion);
        int i = 0;
        if (buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'x')).isEmpty()) {
            x = 0;
        } else {
            x = Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'x'))) * -1;
        }
        if (buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'y')).isEmpty()) {
            y = 0;
        } else {
            y = Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'y'))) * -1;
        }
        if (buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'z')).isEmpty()) {
            z = 0;
        } else {
            z = Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'z'))) * -1;
        }
        return trazasElipsoide('x', r)
                + "\n" + trazasElipsoide('y', r)
                + "\n" + trazasElipsoide('z', r);
    }

    private String imprimirTrazasETipo1(char v1, char v2, double r1, double r2) {
        return "Traza (" + v1 + ", " + v2 + "): \nEl Centro es (" + r1 + ", " + r2 + ")\n"
                + "Focos:  ---> f1(" + String.format("%.2f", (r1 + c)).replaceFirst(",", ".") + ", " + r2 + ")  ,  f2(" + String.format("%.2f", (r1 - c)).replaceFirst(",", ".") + ", " + r2 + ")\n"
                + "Vertices:  --->  v1(" + String.format("%.2f", (r1 + a)).replaceFirst(",", ".") + ", " + r2 + ")  ,  v2(" + String.format("%.2f", (r1 - a)).replaceFirst(",", ".") + ", " + r2 + ")\n"
                + "El valor de a es: " + String.format("%.2f", a).replaceFirst(",", ".") + "\n"
                + "El valor de b es:" + String.format("%.2f", b).replaceFirst(",", ".") + "\n"
                + "La longitud del lado menor es de " + String.format("%.2f", (2 * b)).replaceFirst(",", ".") + " para cada lado.";
    }

    private String imprimirTrazasETipo2(char v1, char v2, double r1, double r2) {
        return "Traza (" + v1 + ", " + v2 + "): \nEl Centro es (" + r1 + ", " + r2 + ")\n"
                + "Focos:  ---> f1(" + r1 + ", " + String.format("%.2f", (r2 + c)).replaceFirst(",", ".") + ")  ,  f2(" + r1 + ", " + String.format("%.2f", (r2 - c)).replaceFirst(",", ".") + ")\n"
                + "Vertices:  ---> v1(" + r1 + ", " + String.format("%.2f", (r2 + a)).replaceFirst(",", ".") + ")  ,  v2(" + r1 + ", " + String.format("%.2f", (r2 - a)).replaceFirst(",", ".") + ")\n"
                + "El valor de a es: " + String.format("%.2f", a).replaceFirst(",", ".") + "\n"
                + "El valor de b es:" + String.format("%.2f", b).replaceFirst(",", ".") + "\n"
                + "La longitud del lado menor es de " + String.format("%.2f", (2 * b)).replaceFirst(",", ".") + " para cada lado.";
    }

    public String trazasElipsoide(char variable, double r) {
        if (variable == 'x') {
            determinarABElip(yn, zn);
            if (orden[0] > orden[1]) {
                if (ordenV[0] == 'y' && ordenV[1] == 'z') {
                    return imprimirTrazasETipo1('y', 'z', y, z);
                } else if (ordenV[0] == 'z' && ordenV[1] == 'y') {
                    return imprimirTrazasETipo1('z', 'y', z, y);
                }

                if (ordenV[1] == 'y' && ordenV[2] == 'z') {
                    return imprimirTrazasETipo1('y', 'z', y, z);
                } else if (ordenV[1] == 'z' && ordenV[2] == 'y') {
                    return imprimirTrazasETipo1('z', 'y', z, y);
                }

            } else if (orden[0] > orden[2]) {
                if (ordenV[0] == 'y' && ordenV[2] == 'z') {
                    return imprimirTrazasETipo1('y', 'z', y, z);

                } else if (ordenV[0] == 'z' && ordenV[2] == 'y') {
                    return imprimirTrazasETipo1('z', 'y', z, y);
                }
                if (ordenV[1] == 'y' && ordenV[2] == 'z') {
                    return imprimirTrazasETipo1('y', 'z', y, z);

                } else if (ordenV[1] == 'z' && ordenV[2] == 'y') {
                    return imprimirTrazasETipo1('z', 'y', z, y);
                }

            } else if (orden[1] > orden[2]) {
                if (ordenV[1] == 'y' && ordenV[2] == 'z') {
                    return imprimirTrazasETipo1('y', 'z', y, z);

                } else if (ordenV[1] == 'z' && ordenV[2] == 'y') {
                    return imprimirTrazasETipo1('z', 'y', z, y);
                }

            } else if (orden[0] < orden[1]) {
                if (ordenV[0] == 'y' && ordenV[1] == 'z') {
                    return imprimirTrazasETipo2('y', 'z', y, z);
                } else if (ordenV[0] == 'z' && ordenV[1] == 'y') {
                    return imprimirTrazasETipo2('z', 'y', z, y);
                }
                if (ordenV[1] == 'y' && ordenV[2] == 'z') {
                    return imprimirTrazasETipo2('y', 'z', y, z);
                } else if (ordenV[1] == 'z' && ordenV[2] == 'y') {
                    return imprimirTrazasETipo2('z', 'y', z, y);
                }

            } else if (orden[0] < orden[2]) {
                if (ordenV[0] == 'y' && ordenV[2] == 'z') {
                    return imprimirTrazasETipo2('y', 'z', y, z);
                } else if (ordenV[0] == 'z' && ordenV[2] == 'y') {
                    return imprimirTrazasETipo2('z', 'y', z, y);
                }

            }
            if (orden[1] < orden[2]) {
                if (ordenV[1] == 'y' && ordenV[2] == 'z') {
                    return imprimirTrazasETipo2('y', 'z', y, z);
                } else if (ordenV[1] == 'z' && ordenV[2] == 'y') {
                    return imprimirTrazasETipo2('z', 'y', z, y);
                }
            }
        }

        if (variable == 'y') {
            determinarABElip(xn, zn);
            if (orden[0] > orden[1]) {
                if (ordenV[0] == 'x' && ordenV[1] == 'z') {
                    return imprimirTrazasETipo1('x', 'z', x, z);
                } else if (ordenV[0] == 'z' && ordenV[1] == 'x') {
                    return imprimirTrazasETipo1('z', 'x', z, x);
                }

                if (ordenV[0] == 'x' && ordenV[2] == 'z') {
                    return imprimirTrazasETipo1('x', 'z', x, z);
                } else if (ordenV[0] == 'z' && ordenV[2] == 'x') {
                    return imprimirTrazasETipo1('z', 'x', z, x);
                }

                if (ordenV[1] == 'x' && ordenV[2] == 'z') {
                    return imprimirTrazasETipo1('x', 'z', x, z);
                } else if (ordenV[2] == 'z' && ordenV[1] == 'x') {
                    return imprimirTrazasETipo1('z', 'x', z, x);
                }

            } else if (orden[0] > orden[2]) {
                if (ordenV[0] == 'x' && ordenV[2] == 'z') {
                    return imprimirTrazasETipo1('x', 'z', x, z);
                } else if (ordenV[0] == 'z' && ordenV[2] == 'x') {
                    return imprimirTrazasETipo1('z', 'x', z, x);
                }

            } else if (orden[1] > orden[2]) {
                if (ordenV[1] == 'x' && ordenV[2] == 'z') {
                    return imprimirTrazasETipo1('x', 'z', x, z);
                } else if (ordenV[1] == 'z' && ordenV[2] == 'x') {
                    return imprimirTrazasETipo1('z', 'x', z, x);
                }
            }
            if (orden[0] < orden[1]) {
                if (ordenV[0] == 'x' && ordenV[1] == 'z') {
                    return imprimirTrazasETipo2('x', 'z', x, z);
                } else if (ordenV[0] == 'z' && ordenV[1] == 'x') {
                    return imprimirTrazasETipo2('z', 'x', z, x);
                }
            } else if (orden[0] < orden[2]) {
                if (ordenV[0] == 'x' && ordenV[2] == 'z') {
                    return imprimirTrazasETipo2('x', 'z', x, z);
                } else if (ordenV[0] == 'z' && ordenV[2] == 'x') {
                    return imprimirTrazasETipo2('z', 'x', z, x);
                }
            } else if (orden[1] < orden[2]) {
                if (ordenV[1] == 'x' && ordenV[2] == 'z') {
                    return imprimirTrazasETipo2('x', 'z', x, z);
                } else if (ordenV[1] == 'z' && ordenV[2] == 'x') {
                    return imprimirTrazasETipo1('z', 'x', z, x);
                }
            }
        }
        if (variable == 'z') {
            determinarABElip(xn, yn);
            if (orden[0] > orden[1]) {
                if (ordenV[0] == 'x' && ordenV[1] == 'y') {
                    return imprimirTrazasETipo1('x', 'y', x, y);
                } else if (ordenV[0] == 'y' && ordenV[1] == 'x') {
                    return imprimirTrazasETipo1('y', 'x', y, x);
                }

            } else if (orden[0] > orden[2]) {
                if (ordenV[0] == 'x' && ordenV[2] == 'y') {
                    return imprimirTrazasETipo1('x', 'y', x, y);
                } else if (ordenV[0] == 'y' && ordenV[2] == 'x') {
                    return imprimirTrazasETipo1('y', 'x', y, x);
                }
            } else if (orden[1] > orden[2]) {
                if (ordenV[1] == 'x' && ordenV[2] == 'y') {
                    return imprimirTrazasETipo1('x', 'y', x, y);
                } else if (ordenV[1] == 'y' && ordenV[2] == 'x') {
                    return imprimirTrazasETipo1('y', 'x', y, x);
                }
            }
            if (orden[0] < orden[1]) {
                if (ordenV[0] == 'x' && ordenV[1] == 'y') {
                    return imprimirTrazasETipo2('x', 'y', x, y);
                } else if (ordenV[0] == 'y' && ordenV[1] == 'x') {
                    return imprimirTrazasETipo2('y', 'x', y, x);
                }

            } else if (orden[0] < orden[2]) {
                if (ordenV[0] == 'x' && ordenV[2] == 'y') {
                    return imprimirTrazasETipo2('x', 'y', x, y);
                } else if (ordenV[0] == 'y' && ordenV[2] == 'x') {
                    return imprimirTrazasETipo2('y', 'x', y, x);
                }
            } else if (orden[1] < orden[2]) {
                if (ordenV[1] == 'x' && ordenV[2] == 'y') {
                    return imprimirTrazasETipo2('x', 'y', x, y);
                } else if (ordenV[1] == 'y' && ordenV[2] == 'x') {
                    return imprimirTrazasETipo2('y', 'x', y, x);
                }
            }
        }
        return "";
    }

    private void determinarABElip(double dividiendo1, double dividiendo2) {
        if (dividiendo1 >= dividiendo2) {
            c = Math.sqrt(dividiendo1 - dividiendo2);
            a = Math.sqrt(dividiendo1);
            b = Math.sqrt(dividiendo2);
        } else {
            c = Math.sqrt(dividiendo2 - dividiendo1);
            b = Math.sqrt(dividiendo1);
            a = Math.sqrt(dividiendo2);
        }
    }

    private String trazasDeCono(Ecuacion nuevaecuacion) {
        String signos = "";
        signos += buscarSigno(nuevaecuacion.getEcuacion(), buscarInicioSigno(nuevaecuacion.getEcuacion(), nuevaecuacion.getEcuacion().length() - 1, 'x'), '/') == 1 ? "+" : "-";
        signos += buscarSigno(nuevaecuacion.getEcuacion(), buscarInicioSigno(nuevaecuacion.getEcuacion(), nuevaecuacion.getEcuacion().length() - 1, 'y'), '/') == 1 ? "+" : "-";
        signos += buscarSigno(nuevaecuacion.getEcuacion(), buscarInicioSigno(nuevaecuacion.getEcuacion(), nuevaecuacion.getEcuacion().length() - 1, 'z'), '/') == 1 ? "+" : "-";
        int x;
        int y;
        int z;

        if (buscarVariableNumerador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'x')).equals("")) {
            x = 0;
        } else {
            x = Integer.parseInt(buscarVariableNumerador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'x'))) * -1;
        }

        if (buscarVariableNumerador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'y')).equals("")) {
            y = 0;
        } else {
            y = Integer.parseInt(buscarVariableNumerador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'y'))) * -1;
        }

        if (buscarVariableNumerador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'z')).equals("")) {
            z = 0;
        } else {
            z = Integer.parseInt(buscarVariableNumerador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'z'))) * -1;
        }

        int a = Integer.parseInt(buscarVariableDenominador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'x'), '/')));
        int b = Integer.parseInt(buscarVariableDenominador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'y'), '/')));
        int c = Integer.parseInt(buscarVariableDenominador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'z'), '/')));
        return "Centro (" + x + ", " + y + ", " + z + ")\n"
                + trazaCono(-1 * x, -1 * y, -1 * z, a, b, c, signos);
    }

    public static String trazaCono(int h, int k, int l, double a, double b, double c, String signos) {
        String retorno = "";
        double valor;
        double resultado;
        double resultado2;
        double raizMayor;
        double raizMenor;

        double raizA = Math.sqrt(a);
        double raizB = Math.sqrt(b);
        double raizC = Math.sqrt(c);

        String valorH = String.valueOf(h);
        String valorK = String.valueOf(k);
        String valorL = String.valueOf(l);

        String valorraizTrazaYZ = String.valueOf(h * -1);
        String valorcK = String.valueOf(k * -1);
        String valorcL = String.valueOf(l * -1);

        if (h == 0 && k == 0 && l == 0) {
            retorno += "\nTraza yz:\n\n";

            if (signos.charAt(1) == '-' || signos.charAt(2) == '-') {
                if (signos.charAt(1) == '-') {
                    retorno += "(" + String.format("%.2f", (raizB / raizC)).replaceFirst(",", ".") + ")z = y\n"
                            + "(" + String.format("%.2f", (-1 * (raizB / raizC))).replaceFirst(",", ".") + ")z = y\n";
                    valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese un valor para z:"));
                    resultado = (raizB / raizC) * valor;
                    resultado2 = -1 * ((raizB / raizC) * valor);
                    retorno += "Valores y: Valor 1: " + String.format("%.2f", resultado).replaceFirst(",", ".") + " Valor 2: " + String.format("%.2f", resultado2).replaceFirst(",", ".") + "\n";
                } else {
                    retorno += "(" + String.format("%.2f", (raizC / raizB)).replaceFirst(",", ".") + ")y = z\n"
                            + "(" + String.format("%.2f", (-1 * (raizC / raizB))).replaceFirst(",", ".") + ")y = z\n";
                    valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese un valor para y:"));
                    resultado = (raizC / raizB) * valor;
                    resultado2 = -1 * ((raizC / raizB) * valor);
                    retorno += "Valores z: Valor 1: " + String.format("%.2f", resultado).replaceFirst(",", ".") + " Valor 2: " + String.format("%.2f", resultado2).replaceFirst(",", ".") + "\n\n";
                }
            } else {
                retorno += "(y^2)/" + b + "+(z^2)/" + c + "=1";

                raizMayor = raizB > raizC ? raizB : raizC;
                raizMenor = raizB < raizC ? raizB : raizC;
                retorno += "Puntos: Punto 1: (" + raizMayor + ", " + (k * -1) + ")"
                        + "\n\tPunto 2: (" + raizMayor * -1 + ", " + (k * -1) + ")"
                        + "\n\tPunto 3: (" + (l * -1) + ", " + raizMenor + ")"
                        + "\n\tPunto 4: (" + (l * -1) + ", " + raizMenor * -1 + ")";
            }

            retorno += "\nTraza xz:\n";

            if (signos.charAt(0) == '-' || signos.charAt(2) == '-') {
                if (signos.charAt(0) == '-') {
                    retorno += "(" + String.format("%.2f", (raizA / raizC)).replaceFirst(",", ".") + ")z = x\n"
                            + "(" + String.format("%.2f", (-1 * (raizA / raizC))).replaceFirst(",", ".") + ")z = x\n";
                    valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese un valor para z:"));
                    resultado = (raizA / raizC) * valor;
                    resultado2 = -1 * ((raizA / raizC) * valor);
                    retorno += "Valores x: Valor 1: " + String.format("%.2f", resultado).replaceFirst(",", ".") + " Valor 2: " + String.format("%.2f", resultado2).replaceFirst(",", ".") + "\n";
                } else {
                    retorno += "(" + String.format("%.2f", (raizC / raizA)).replaceFirst(",", ".") + ")x = z\n"
                            + "(" + String.format("%.2f", (-1 * (raizC / raizA))).replaceFirst(",", ".") + ")x = z\n";
                    valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese un valor para x:"));
                    resultado = (raizC / raizA) * valor;
                    resultado2 = -1 * ((raizC / raizA) * valor);
                    retorno += "Valores z: Valor 1: " + String.format("%.2f", resultado).replaceFirst(",", ".") + " Valor 2: " + String.format("%.2f", resultado2).replaceFirst(",", ".") + "\n";
                }
            } else {
                retorno += "(x^2)/" + a + "+(z^2)/" + c + "=1";

                raizMayor = raizA > raizC ? raizA : raizC;
                raizMenor = raizA < raizC ? raizA : raizC;
                retorno += "Puntos: Punto 1: (" + raizMayor + ", " + (h * -1) + ")"
                        + "\n\tPunto 2: (" + raizMayor * -1 + ", " + (h * -1) + ")"
                        + "\n\tPunto 3: (" + (l * -1) + ", " + raizMenor + ")"
                        + "\n\tPunto 4: (" + (l * -1) + ", " + raizMenor * -1 + ")";
            }

            retorno += "\nTraza xy:\n";

            if (signos.charAt(0) == '-' || signos.charAt(1) == '-') {
                if (signos.charAt(0) == '-') {
                    retorno += "(" + String.format("%.2f", (raizA / raizB)).replaceFirst(",", ".") + ")y = x\n"
                            + "(" + String.format("%.2f", (-1 * (raizA / raizB))).replaceFirst(",", ".") + ")y = x\n";
                    valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese un valor para y:"));
                    resultado = (raizA / raizB) * valor;
                    resultado2 = -1 * ((raizA / raizB) * valor);
                    retorno += "Valores x: Valor 1: " + String.format("%.2f", resultado).replaceFirst(",", ".") + " Valor 2: " + String.format("%.2f", resultado2).replaceFirst(",", ".") + "\n";
                } else {
                    retorno += "(" + String.format("%.2f", (raizB / raizA)).replaceFirst(",", ".") + ")x = y\n"
                            + "(" + String.format("%.2f", (-1 * (raizB / raizA))).replaceFirst(",", ".") + ")x = y\n";
                    valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese un valor para x:"));
                    resultado = (raizB / raizA) * valor;
                    resultado2 = -1 * ((raizB / raizA) * valor);
                    retorno += "Valores y: Valor 1: " + String.format("%.2f", resultado).replaceFirst(",", ".") + " Valor 2: " + String.format("%.2f", resultado2).replaceFirst(",", ".") + "\n";
                }
            } else {
                retorno += "(x^2)/" + a + "+(y^2)/" + b + "=1\n";

                raizMayor = raizA > raizB ? raizA : raizB;
                raizMenor = raizA < raizB ? raizA : raizB;
                retorno += "Puntos: Punto 1: (" + raizMayor + ", " + (h * -1) + ")"
                        + "\n\tPunto 2: (" + raizMayor * -1 + ", " + (h * -1) + ")"
                        + "\n\tPunto 3: (" + (k * -1) + ", " + raizMenor + ")"
                        + "\n\tPunto 4: (" + (k * -1) + ", " + raizMenor * -1 + ")";
            }

        } else {

            retorno += "\nTraza yz:\n";

            if (signos.charAt(1) == '-' || signos.charAt(2) == '-') {
                if (signos.charAt(1) == '-') {
                    retorno += (k != 0 ? "(" : "") + String.format("%.2f", (raizB / raizC)).replaceFirst(",", ".") + "(z" + (l != 0 ? (l > 0 ? "+" + valorL : valorL) : "") + ")" + (k != 0 ? ")" + ((k * -1) > 0 ? "+" + valorcK : valorcK) : "") + " = y\n"
                            + (k != 0 ? "(" : "") + String.format("%.2f", (-1 * (raizB / raizC))).replaceFirst(",", ".") + "(z" + (l != 0 ? (l > 0 ? "+" + valorL : valorL) : "") + ")" + (k != 0 ? ")" + ((k * -1) > 0 ? "+" + valorcK : valorcK) : "") + " = y\n";

                    valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese un valor para z:"));
                    resultado = ((raizB / raizC) * (valor + l)) + (k * -1);
                    resultado2 = ((-1 * (raizB / raizC)) * (valor + l)) + (k * -1);
                    retorno += "Valores y: Valor 1: " + String.format("%.2f", resultado).replaceFirst(",", ".") + " Valor 2: " + String.format("%.2f", resultado2).replaceFirst(",", ".") + "\n";
                } else {
                    retorno += (l != 0 ? "(" : "") + String.format("%.2f", (raizC / raizB)).replaceFirst(",", ".") + "(y" + (k != 0 ? (k > 0 ? "+" + valorK : valorK) : "") + ")" + (l != 0 ? ")" + ((l * -1) > 0 ? "+" + valorcL : valorcL) : "") + " = z\n"
                            + (l != 0 ? "(" : "") + String.format("%.2f", (-1 * (raizC / raizB))).replaceFirst(",", ".") + "(y" + (k != 0 ? (k > 0 ? "+" + valorK : valorK) : "") + ")" + (l != 0 ? ")" + ((l * -1) > 0 ? "+" + valorcL : valorcL) : "") + " = z\n";

                    valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese un valor para y:"));
                    resultado = ((raizC / raizB) * (valor + k)) + (l * -1);
                    resultado2 = ((-1 * (raizC / raizB)) * (valor + k)) + (l * -1);
                    retorno += "Valores z: Valor 1: " + String.format("%.2f", resultado).replaceFirst(",", ".") + " Valor 2: " + String.format("%.2f", resultado2).replaceFirst(",", ".") + "\n";
                }
            } else {
                retorno += (k != 0 ? "(" : "") + "(y" + (k != 0 ? (k > 0 ? "+" + valorK : valorK) + ")" : "") + "^2)/" + b + "+" + (l != 0 ? "(" : "") + "(z" + (l != 0 ? (l > 0 ? "+" + valorL : valorL) + ")" : "") + "^2)/" + c + "=1\n";

                raizMayor = raizB > raizC ? raizB : raizC;
                retorno += "Puntos: Punto 1: (" + ((k * -1) + raizMayor) + ", " + (l * -1) + ")"
                        + "\n\tPunto 2: (" + ((k * -1) - raizMayor) + ", " + (l * -1) + ")"
                        + "\n\tPunto 3: (" + (k * -1) + ", " + ((l * -1) + raizMayor) + ")"
                        + "\n\tPunto 4: (" + (k * -1) + ", " + ((l * -1) - raizMayor) + ")";
            }

            retorno += "\nTraza xz:\n";

            if (signos.charAt(0) == '-' || signos.charAt(2) == '-') {
                if (signos.charAt(0) == '-') {
                    retorno += (h != 0 ? "(" : "") + String.format("%.2f", (raizA / raizC)).replaceFirst(",", ".") + "(z" + (l != 0 ? (l > 0 ? "+" + valorL : valorL) : "") + ")" + (h != 0 ? ")" + ((h * -1) > 0 ? "+" + valorraizTrazaYZ : valorraizTrazaYZ) : "") + " = x\n"
                            + (h != 0 ? "(" : "") + String.format("%.2f", (-1 * (raizA / raizC))).replaceFirst(",", ".") + "(z" + (l != 0 ? (l > 0 ? "+" + valorL : valorL) : "") + ")" + (h != 0 ? ")" + ((h * -1) > 0 ? "+" + valorraizTrazaYZ : valorraizTrazaYZ) : "") + " = x\n";

                    valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese un valor para z:"));
                    resultado = ((raizA / raizC) * (valor + l)) + (h * -1);
                    resultado2 = ((-1 * (raizA / raizC)) * (valor + l)) + (h * -1);
                    retorno += "Valores x: Valor 1: " + String.format("%.2f", resultado) + " Valor 2: " + String.format("%.2f", resultado2).replaceFirst(",", ".") + "\n";
                } else {
                    retorno += (l != 0 ? "(" : "") + String.format("%.2f", (raizC / raizA)).replaceFirst(",", ".") + "(x" + (h != 0 ? (h > 0 ? "+" + valorH : valorH) : "") + ")" + (l != 0 ? ")" + ((l * -1) > 0 ? "+" + valorcL : valorcL) : "") + " = z\n"
                            + (l != 0 ? "(" : "") + String.format("%.2f", (-1 * (raizC / raizA))).replaceFirst(",", ".") + "(x" + (h != 0 ? (h > 0 ? "+" + valorH : valorH) : "") + ")" + (l != 0 ? ")" + ((l * -1) > 0 ? "+" + valorcL : valorcL) : "") + " = z\n";

                    valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese un valor para x:"));
                    resultado = ((raizC / raizA) * (valor + h)) + (l * -1);
                    resultado2 = ((-1 * (raizC / raizA)) * (valor + h)) + (l * -1);
                    retorno += "Valores z: Valor 1: " + String.format("%.2f", resultado).replaceFirst(",", ".") + " Valor 2: " + String.format("%.2f", resultado2).replaceFirst(",", ".") + "\n";
                }
            } else {
                retorno += (h != 0 ? "(" : "") + "(x" + (h != 0 ? (h > 0 ? "+" + valorH : valorH) + ")" : "") + "^2)/" + a + "+" + (l != 0 ? "(" : "") + "(z" + (l != 0 ? (l > 0 ? "+" + valorL : valorL) + ")" : "") + "^2)/" + c + "=1\n";

                raizMayor = raizA > raizC ? raizA : raizC;
                retorno += "Puntos: Punto 1: (" + ((h * -1) + raizMayor) + ", " + (l * -1) + ")"
                        + "\n\tPunto 2: (" + ((h * -1) - raizMayor) + ", " + (l * -1) + ")"
                        + "\n\tPunto 3: (" + (h * -1) + ", " + ((l * -1) + raizMayor) + ")"
                        + "\n\tPunto 4: (" + (h * -1) + ", " + ((l * -1) - raizMayor) + ")";
            }

            retorno += "\nTraza xy:\n";

            if (signos.charAt(0) == '-' || signos.charAt(1) == '-') {
                if (signos.charAt(0) == '-') {
                    retorno += (h != 0 ? "(" : "") + String.format("%.2f", (raizA / raizB)).replaceFirst(",", ".") + "(y" + (k != 0 ? (k > 0 ? "+" + valorK : valorK) : "") + ")" + (h != 0 ? ")" + ((h * -1) > 0 ? "+" + valorraizTrazaYZ : valorraizTrazaYZ) : "") + " = x\n"
                            + (h != 0 ? "(" : "") + String.format("%.2f", (-1 * (raizA / raizB))).replaceFirst(",", ".") + "(y" + (k != 0 ? (k > 0 ? "+" + valorK : valorK) : "") + ")" + (h != 0 ? ")" + ((h * -1) > 0 ? "+" + valorraizTrazaYZ : valorraizTrazaYZ) : "") + " = x\n";

                    valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese un valor para y:"));
                    resultado = ((raizA / raizB) * (valor + k)) + (h * -1);
                    resultado2 = ((-1 * (raizA / raizB)) * (valor + k)) + (h * -1);
                    retorno += "Valores x: Valor 1: " + String.format("%.2f", resultado).replaceFirst(",", ".") + " Valor 2: " + String.format("%.2f", resultado2).replaceFirst(",", ".") + "\n";
                } else {
                    retorno += (k != 0 ? "(" : "") + String.format("%.2f", (raizB / raizA)).replaceFirst(",", ".") + "(x" + (h != 0 ? (h > 0 ? "+" + valorH : valorH) : "") + ")" + (k != 0 ? ")" + ((k * -1) > 0 ? "+" + valorcK : valorcK) : "") + " = y\n"
                            + (k != 0 ? "(" : "") + String.format("%.2f", (-1 * (raizB / raizA))).replaceFirst(",", ".") + "(x" + (h != 0 ? (h > 0 ? "+" + valorH : valorH) : "") + ")" + (k != 0 ? ")" + ((k * -1) > 0 ? "+" + valorcK : valorcK) : "") + " = y\n";

                    valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese un valor para x:"));
                    resultado = ((raizB / raizA) * (valor + h)) + (k * -1);
                    resultado2 = ((-1 * (raizB / raizA)) * (valor + h)) + (k * -1);
                    retorno += "Valores y: Valor 1: " + String.format("%.2f", resultado).replaceFirst(",", ".") + " Valor 2: " + String.format("%.2f", resultado2).replaceFirst(",", ".") + "\n";
                }
            } else {
                retorno += (h != 0 ? "(" : "") + "(x" + (h != 0 ? (h > 0 ? "+" + valorH : valorH) + ")" : "") + "^2)/" + a + "+" + (k != 0 ? "(" : "") + "(y" + (k != 0 ? (k > 0 ? "+" + valorK : valorK) + ")" : "") + "^2)/" + b + "=1\n";

                raizMayor = raizA > raizB ? raizA : raizB;
                retorno += "Puntos: Punto 1: (" + ((h * -1) + raizMayor) + ", " + (k * -1) + ")"
                        + "\n\tPunto 2: (" + ((h * -1) - raizMayor) + ", " + (k * -1) + ")"
                        + "\n\tPunto 3: (" + (h * -1) + ", " + ((k * -1) + raizMayor) + ")"
                        + "\n\tPunto 4: (" + (h * -1) + ", " + ((k * -1) - raizMayor) + ")";
            }
        }
        return retorno;
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

    private String trazasParaboloideElip(Ecuacion nuevaecuacion) {
        String signos = "";
        signos += buscarSigno(nuevaecuacion.getEcuacion(), buscarInicioSigno(nuevaecuacion.getEcuacion(), nuevaecuacion.getEcuacion().length() - 1, 'x'), '/') == 1 ? "+" : "-";
        signos += buscarSigno(nuevaecuacion.getEcuacion(), buscarInicioSigno(nuevaecuacion.getEcuacion(), nuevaecuacion.getEcuacion().length() - 1, 'y'), '/') == 1 ? "+" : "-";
        signos += buscarSigno(nuevaecuacion.getEcuacion(), buscarInicioSigno(nuevaecuacion.getEcuacion(), nuevaecuacion.getEcuacion().length() - 1, 'z'), '/') == 1 ? "+" : "-";

        double mayor = 0;
        ordenVariables(nuevaecuacion);
        int i = 0;

        if (buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'x')).isEmpty()) {
            x = 0;
        } else {
            x = Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'x'))) * -1;
        }
        if (buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'y')).isEmpty()) {
            y = 0;
        } else {
            y = Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'y'))) * -1;
        }
        if (buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'z')).isEmpty()) {
            z = 0;
        } else {
            z = Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'z'))) * -1;
        }
        return "Vertice en " + "(" + x + ", " + y + ", " + z + ")"
                + "\n" + trazasParaboloideE('x', r, signos)
                + "\n" + trazasParaboloideE('y', r, signos)
                + "\n" + trazasParaboloideE('z', r, signos) + "\n\n";
    }

    public String trazasParaboloideE(char variable, double r, String signos) {
        String retorno = "";

        if (variable == 'x') {
            if (ordenV[0] == 'x') {

                double valor;
                valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese un valor para cerrar x:"));
                valor = valor * c;
                yn = yn * valor;
                zn = zn * valor;

                double raizB = Math.sqrt(yn);
                double raizC = Math.sqrt(zn);

                retorno += "La traza YZ es una elipse con el Centro en (0,0)\n";
                if (raizB > raizC) {
                    if (ordenV[1] == 'y' && ordenV[2] == 'z') {
                        retorno
                                += "Vertices en (" + String.format("%.2f", (raizB * -1)).replaceFirst(",", ".") + ", " + 0 + ") , (" + String.format("%.2f", (raizB)).replaceFirst(",", ".") + ", " + 0 + ")\n"
                                + "Focos en (" + String.format("%.2f", (x + c)).replaceFirst(",", ".") + ", " + y + ")  ,  f2(" + String.format("%.2f", (x - c)).replaceFirst(",", ".") + ", " + y + ")\n\n";
                    }
                    if (ordenV[1] == 'z' && ordenV[2] == 'y') {
                        retorno
                                += "Vertices en (" + 0 + ", " + String.format("%.2f", (raizB * -1)).replaceFirst(",", ".") + ") , (" + 0 + ", " + String.format("%.2f", (raizB)).replaceFirst(",", ".") + ")\n"
                                + "Focos en (" + y + ", " + String.format("%.2f", (x + c)).replaceFirst(",", ".") + ")  ,  f2(" + y + ", " + String.format("%.2f", (x - c)).replaceFirst(",", ".") + ")\n\n";

                    }

                } else if (raizC > raizB) {

                    retorno += "Vertices en (" + String.format("%.2f", (raizC * -1)).replaceFirst(",", ".") + ", " + 0 + ") , (" + String.format("%.2f", (raizC)).replaceFirst(",", ".") + ", " + 0 + ")\n"
                            + "Focos en (" + 0 + ", " + String.format("%.2f", (raizB)).replaceFirst(",", ".") + ")  ,  f2(" + 0 + ", " + String.format("%.2f", (raizB * -1)).replaceFirst(",", ".") + ")\n\n";

                } else {
                    retorno += "Vertices en (" + String.format("%.2f", (raizC * -1)).replaceFirst(",", ".") + ", " + 0 + ") , (" + String.format("%.2f", (raizC)).replaceFirst(",", ".") + ", " + 0 + ")\n"
                            + "Focos en (" + 0 + ", " + String.format("%.2f", (raizB)).replaceFirst(",", ".") + ") , (" + 0 + ", " + String.format("%.2f", (raizB * -1)).replaceFirst(",", ".") + ")\n\n";

                }
            } else if (ordenV[1] == 'x') {
                if (ordenV[0] == 'z' && ordenV[2] == 'y') {
                    double aux = yn * c;
                    if (yi.startsWith("+")) {
                        p = buscarP(aux) * -1;
                    } else if (yi.startsWith("-")) {
                        p = buscarP(aux);
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    p = p * -1;

                    if (p > 0) {
                        retorno += "Abre hacia arriba\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia abajo\n";
                    }
                    retorno += "\nTraza (y,z):\n"
                            + "Foco:--->(" + y + ", " + String.format("%.2f", (z + p)).replaceFirst(",", ".") + ")\n"
                            + "Vertice:--->(" + y + ", " + z + ")\n\n";

                } else if (ordenV[0] == 'y' && ordenV[2] == 'z') {
                    double aux = zn * c;
                    if (zi.startsWith("+")) {
                        p = buscarP(aux) * -1;
                    } else if (zi.startsWith("-")) {
                        p = buscarP(aux);
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    p = p * -1;

                    if (p > 0) {
                        retorno += "Abre hacia derecha\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia izquierda\n";
                    }
                    retorno += "Traza (y,z):\n"
                            + "Foco:--->(" + String.format("%.2f", (y + p)).replaceFirst(",", ".") + ", " + z + ")\n"
                            + "Vertice:--->(" + y + ", " + z + ")\n\n";

                }

            } else if (ordenV[2] == 'x') {
                if (ordenV[0] == 'z' && ordenV[1] == 'y') {
                    double aux = yn * c;
                    if (yi.startsWith("+")) {
                        p = buscarP(aux) * -1;
                    } else if (yi.startsWith("-")) {
                        p = buscarP(aux);
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    p = p * -1;

                    if (p > 0) {
                        retorno += "Abre hacia arriba\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia abajo\n";
                    }
                    retorno += "\nTraza (y,z):\n"
                            + "Foco:--->(" + y + ", " + String.format("%.2f", (z + p)).replaceFirst(",", ".") + ")\n"
                            + "Vertice:--->(" + y + ", " + z + ")\n";

                } else if (ordenV[0] == 'y' && ordenV[1] == 'z') {
                    double aux = zn * c;
                    if (zi.startsWith("+")) {
                        p = buscarP(aux) * -1;
                    } else if (zi.startsWith("-")) {
                        p = buscarP(aux);
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    p = p * -1;

                    if (p > 0) {
                        retorno += "Abre hacia derecha\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia izquierda\n";
                    }
                    retorno += "\nTraza (y,z):\n"
                            + "Foco:--->(" + String.format("%.2f", (y + p)).replaceFirst(",", ".") + ", " + z + ")\n"
                            + "Vertice:--->(" + y + ", " + z + ")\n";

                }

            }

        } else if (variable == 'y') {
            if (ordenV[0] == 'y') {

                double valor;
                valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese un valor para cerrar y:"));
                valor = valor * c;
                xn = xn * valor;
                zn = zn * valor;

                double raizB = Math.sqrt(xn);
                double raizC = Math.sqrt(zn);

                retorno += "La traza XZ es una elipse con el Centro en (0,0)\n";
                if (raizB > raizC) {

                    retorno
                            += "Vertices en (" + String.format("%.2f", (raizB * -1)).replaceFirst(",", ".") + ", " + 0 + ") , (" + String.format("%.2f", (raizB)).replaceFirst(",", ".") + ", " + 0 + ")\n"
                            + "Focos en (" + String.format("%.2f", (y + c)).replaceFirst(",", ".") + ", " + y + ")  ,  f2(" + String.format("%.2f", (y - c)).replaceFirst(",", ".") + ", " + y + ")\n\n";

                } else if (raizC > raizB) {

                    retorno += "Vertices en (" + String.format("%.2f", (raizC * -1)).replaceFirst(",", ".") + ", " + 0 + ") , (" + String.format("%.2f", (raizC)).replaceFirst(",", ".") + ", " + 0 + ")\n"
                            + "Focos en (" + 0 + ", " + String.format("%.2f", (raizB)).replaceFirst(",", ".") + ")  ,  f2(" + 0 + ", " + String.format("%.2f", (raizB * -1)).replaceFirst(",", ".") + ")\n\n";

                } else {
                    retorno += "Vertices en (" + String.format("%.2f", (raizC * -1)).replaceFirst(",", ".") + ", " + 0 + ") , (" + String.format("%.2f", (raizC)).replaceFirst(",", ".") + ", " + 0 + ")\n"
                            + "Focos en (" + 0 + ", " + String.format("%.2f", (raizB)).replaceFirst(",", ".") + ") , (" + 0 + ", " + String.format("%.2f", (raizB * -1)).replaceFirst(",", ".") + ")\n\n";

                }
            } else if (ordenV[1] == 'y') {

                if (ordenV[0] == 'z' && ordenV[2] == 'x') {
                    double aux = xn * c;
                    if (xi.startsWith("+")) {
                        p = buscarP(aux) * -1;
                    } else if (xi.startsWith("-")) {
                        p = buscarP(aux);
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    p = p * -1;

                    if (p > 0) {
                        retorno += "Abre hacia arriba\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia abajo\n";
                    }
                    retorno += "\nTraza (x,z):\n"
                            + "Foco:--->(" + x + ", " + String.format("%.2f", (z + p)).replaceFirst(",", ".") + ")\n"
                            + "Vertice:--->(" + x + ", " + z + ")\n\n";

                } else if (ordenV[0] == 'x' && ordenV[2] == 'z') {
                    double aux = zn * c;
                    if (zi.startsWith("+")) {
                        p = buscarP(aux) * -1;
                    } else if (zi.startsWith("-")) {
                        p = buscarP(aux);
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    p = p * -1;

                    if (p > 0) {
                        retorno += "Abre hacia derecha\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia izquierda\n";
                    }
                    retorno += "\nTraza (x,z):\n"
                            + "Foco:--->(" + String.format("%.2f", (x + p)).replaceFirst(",", ".") + ", " + z + ")\n"
                            + "Vertice:--->(" + x + ", " + z + ")\n\n";
                }

            } else if (ordenV[2] == 'y') {
                if (ordenV[0] == 'z' && ordenV[1] == 'x') {
                    double aux = xn * c;
                    if (xi.startsWith("+")) {
                        p = buscarP(aux) * -1;
                    } else if (xi.startsWith("-")) {
                        p = buscarP(aux);
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    p = p * -1;

                    if (p > 0) {
                        retorno += "Abre hacia arriba\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia abajo\n";
                    }
                    retorno += "\nTraza (x,z):\n"
                            + "Foco:--->(" + x + ", " + String.format("%.2f", (z + p)).replaceFirst(",", ".") + ")\n"
                            + "Vertice:--->(" + x + ", " + z + ")\n";

                } else if (ordenV[0] == 'x' && ordenV[1] == 'z') {
                    double aux = zn * c;
                    if (zi.startsWith("+")) {
                        p = buscarP(aux) * -1;
                    } else if (zi.startsWith("-")) {
                        p = buscarP(aux);
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    p = p * -1;

                    if (p > 0) {
                        retorno += "Abre hacia derecha\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia izquierda\n";
                    }
                    retorno += "\nTraza (y,z):\n"
                            + "Foco:--->(" + String.format("%.2f", (y + p)).replaceFirst(",", ".") + ", " + z + ")\n"
                            + "Vertice:--->(" + y + ", " + z + ")\n";
                }
            }
        } else if (variable == 'z') {
            if (ordenV[0] == 'z') {
                double valor;
                valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese un valor para cerrar z:"));
                valor = valor * c;
                xn = xn * valor;
                yn = yn * valor;

                double raizB = Math.sqrt(xn);
                double raizC = Math.sqrt(yn);

                retorno += "La traza XZ es una elipse con el Centro en (0,0)\n";
                if (raizB > raizC) {

                    retorno
                            += "Vertices en (" + String.format("%.2f", (raizB * -1)).replaceFirst(",", ".") + ", " + 0 + ") , (" + String.format("%.2f", (raizB)).replaceFirst(",", ".") + ", " + 0 + ")\n"
                            + "Focos en (" + String.format("%.2f", (z + c)).replaceFirst(",", ".") + ", " + y + ")  ,  f2(" + String.format("%.2f", (z - c)).replaceFirst(",", ".") + ", " + y + ")\n\n";

                } else if (raizC > raizB) {

                    retorno += "Vertices en (" + String.format("%.2f", (raizC * -1)).replaceFirst(",", ".") + ", " + 0 + ") , (" + String.format("%.2f", (raizC)).replaceFirst(",", ".") + ", " + 0 + ")\n"
                            + "Focos en (" + 0 + ", " + String.format("%.2f", (raizB)).replaceFirst(",", ".") + ")  ,  f2(" + 0 + ", " + String.format("%.2f", (raizB * -1)).replaceFirst(",", ".") + ")\n\n";

                } else {
                    retorno += "Vertices en (" + String.format("%.2f", (raizC * -1)).replaceFirst(",", ".") + ", " + 0 + ") , (" + String.format("%.2f", (raizC)).replaceFirst(",", ".") + ", " + 0 + ")\n"
                            + "Focos en (" + 0 + ", " + String.format("%.2f", (raizB)).replaceFirst(",", ".") + ") , (" + 0 + ", " + String.format("%.2f", (raizB * -1)).replaceFirst(",", ".") + ")\n\n";

                }

            } else if (ordenV[1] == 'z') {

                if (ordenV[0] == 'y' && ordenV[2] == 'x') {
                    double aux = xn * c;
                    if (xi.startsWith("+")) {
                        p = buscarP(aux) * -1;
                    } else if (xi.startsWith("-")) {
                        p = buscarP(aux);
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    p = p * -1;

                    if (p > 0) {
                        retorno += "Abre hacia arriba\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia abajo\n";
                    }
                    retorno += "\nTraza (x,y):\n"
                            + "Foco:--->(" + x + ", " + String.format("%.2f", (y + p)).replaceFirst(",", ".") + ")\n"
                            + "Vertice:--->(" + x + ", " + y + ")\n\n";
                } else if (ordenV[0] == 'x' && ordenV[2] == 'y') {
                    double aux = yn * c;
                    if (yi.startsWith("+")) {
                        p = buscarP(aux) * -1;
                    } else if (yi.startsWith("-")) {
                        p = buscarP(aux);
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    p = p * -1;

                    if (p > 0) {
                        retorno += "Abre hacia derecha\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia izquierda\n";
                    }
                    retorno += "Traza (x,y):\n"
                            + "Foco:--->(" + String.format("%.2f", (x + p)).replaceFirst(",", ".") + ", " + y + ")\n"
                            + "Vertice:--->(" + x + ", " + y + ")\n\n";
                }

            } else if (ordenV[2] == 'z') {

                if (ordenV[0] == 'y' && ordenV[1] == 'x') {
                    double aux = xn * c;
                    if (xi.startsWith("+")) {
                        p = buscarP(aux) * -1;
                    } else if (xi.startsWith("-")) {
                        p = buscarP(aux);
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    p = p * -1;

                    if (p > 0) {
                        retorno += "Abre hacia arriba\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia abajo\n";
                    }
                    retorno += "\nTraza (x,y):\n"
                            + "Foco:--->(" + x + ", " + String.format("%.2f", (y + p)).replaceFirst(",", ".") + ")\n"
                            + "Vertice:--->(" + x + ", " + y + ")\n";

                } else if (ordenV[0] == 'x' && ordenV[1] == 'y') {
                    double aux = yn * c;
                    if (yi.startsWith("+")) {
                        p = buscarP(aux) * -1;
                    } else if (yi.startsWith("-")) {
                        p = buscarP(aux);
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    p = p * -1;
                    if (p > 0) {
                        retorno += "Abre hacia derecha\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia izquierda\n";
                    }
                    retorno += "\nTraza (x,y):\n"
                            + "Foco:--->(" + String.format("%.2f", (x + p)).replaceFirst(",", ".") + ", " + y + ")\n"
                            + "Vertice:--->(" + x + ", " + y + ")\n";
                }
            }
        }
        return retorno;
    }

    private double buscarP(double aux) {
        double p = 0;
        if (aux == 4.0) {
            p = aux * 0.25;
        } else if (aux == -4.0 || aux % 4 != 0 && aux % 4 < 0) {
            p = 0.25;
        } else if (aux % 4 == 0.0) {
            p = aux / 4;
        } else {
            p = aux * 0.25;
        }
        return p;
    }

    public String trazasHiperboloideUnaHoja(Ecuacion nuevaecuacion) {
        String signos = "";
        signos += buscarSigno(nuevaecuacion.getEcuacion(), buscarInicioSigno(nuevaecuacion.getEcuacion(), nuevaecuacion.getEcuacion().length() - 1, 'x'), '/') == 1 ? "+" : "-";
        signos += buscarSigno(nuevaecuacion.getEcuacion(), buscarInicioSigno(nuevaecuacion.getEcuacion(), nuevaecuacion.getEcuacion().length() - 1, 'y'), '/') == 1 ? "+" : "-";
        signos += buscarSigno(nuevaecuacion.getEcuacion(), buscarInicioSigno(nuevaecuacion.getEcuacion(), nuevaecuacion.getEcuacion().length() - 1, 'z'), '/') == 1 ? "+" : "-";

        int x;
        int y;
        int z;

        if (buscarVariableNumerador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'x')).equals("")) {
            x = 0;
        } else {
            x = Integer.parseInt(buscarVariableNumerador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'x'))) * -1;
        }

        if (buscarVariableNumerador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'y')).equals("")) {
            y = 0;
        } else {
            y = Integer.parseInt(buscarVariableNumerador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'y'))) * -1;
        }

        if (buscarVariableNumerador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'z')).equals("")) {
            z = 0;
        } else {
            z = Integer.parseInt(buscarVariableNumerador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'z'))) * -1;
        }

        int a = Integer.parseInt(buscarVariableDenominador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'x'), '/')));
        int b = Integer.parseInt(buscarVariableDenominador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'y'), '/')));
        int c = Integer.parseInt(buscarVariableDenominador(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), 0, 'z'), '/')));
        return "Centro (" + x + ", " + y + ", " + z + ")\n"
                + trazaHiperbole(-1 * x, -1 * y, -1 * z, a, b, c, signos);

    }

    public static String trazaHiperbole(int h, int k, int l, double a, double b, double c, String signos) {

        String retorno = "";
        double raizMayor;
        double raizMenor;
        double resultado;
        double valor;
        double raizA = Math.sqrt(a);
        double raizB = Math.sqrt(b);
        double raizC = Math.sqrt(c);

        double raizTrazaYZ = Math.sqrt(b + c);
        double raizTrazaXZ = Math.sqrt(a + c);
        double raizTrazaXY = Math.sqrt(a + b);

        String valorH = String.valueOf(h);
        String valorK = String.valueOf(k);
        String valorL = String.valueOf(l);
        if (h == 0 && k == 0 && l == 0) {
            retorno += "\nTRAZA YZ:\n";

            if (signos.charAt(1) == '-' || signos.charAt(2) == '-') {
                if (signos.charAt(1) == '-') {
                    retorno += " \nEs una hiperbola con el Centro en: (" + l + ", " + k + ")\n"
                            + "\nFocos:  n---> f1(" + k + ", " + String.format("%.2f", (raizTrazaYZ)).replaceFirst(",", ".") + ") ,  f2(" + k + ", " + String.format("%.2f", (raizTrazaYZ * -1)).replaceFirst(",", ".") + ")\n"
                            + "\nVertices: n--->  v1(" + k + ", " + String.format("%.2f", (raizC)).replaceFirst(",", ".") + ")  ,  v2(" + k + ", " + String.format("%.2f", (raizC * -1)).replaceFirst(",", ".") + ")\n" + "\nEje conjugado: (" + 2 * raizB + ")\n";
                } else {
                    retorno += "\nEs una hiperbola con el Centro en (" + k + ", " + l + ")\n"
                            + "\nFocos: n----> f1(" + String.format("%.2f", (raizTrazaYZ)).replaceFirst(",", ".") + " , " + l + ") , f2(" + String.format("%.2f", (raizTrazaYZ * -1)).replaceFirst(",", ".") + ", " + l + ")\n"
                            + "\nvertices: n ---> v1(" + String.format("%.1f", (raizB)).replaceFirst(",", ".") + " ," + l + ") , v2(" + String.format("%.2f", (-raizB)).replaceFirst(",", ".") + "," + l + ")\n" + "\nEje conjugado: (" + 2 * raizC + ")\n";
                }
            } else {
                retorno += "\nEs una elipse de la siguiente forma: \n"
                        + "(y^2)/" + b + "+(z^2)/" + c + "=1";

                raizMayor = raizB > raizC ? raizB : raizC;
                raizMenor = raizB < raizC ? raizB : raizC;
                retorno += "\nPUNTOS: "
                        + "\n\tPunto 1: (" + raizMayor + ", " + (k * -1) + ")"
                        + "\n\tPunto 2: (" + raizMayor * -1 + ", " + (k * -1) + ")"
                        + "\n\tPunto 3: (" + (l * -1) + ", " + raizMenor + ")"
                        + "\n\tPunto 4: (" + (l * -1) + ", " + raizMenor * -1 + ")\n";

                int valorX = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un valor para x:"));
                retorno += "\nSe forma una elipse con el centro en: (" + valorX + " ," + k * -1 + " ," + l * -1 + ")";
                resultado = ((k * -1) ^ 2) / ((b * ((1 * a) + (valorX ^ 2 * 1))) / a) + ((l * -1) ^ 2) / ((c * (1 * a) + (valorX ^ 2 * 1)) / a);
                double raiz1 = Math.sqrt((b * ((1 * a) + (Math.pow(valorX, 2) * 1))) / a);
                double raiz2 = Math.sqrt((c * ((1 * a) + (Math.pow(valorX, 2) * 1))) / a);

                retorno += "\nEl valor de a positiva es: " + (raiz1 > raiz2 ? raiz1 : raiz2);
                retorno += "\nEl valor de a negativa es: " + (raiz1 > raiz2 ? -raiz1 : -raiz2);
                retorno += "\nEl valor de b positiva es: " + (raiz1 < raiz2 ? raiz1 : raiz2);
                retorno += "\nEl valor de b negativa es: " + (raiz1 < raiz2 ? -raiz1 : -raiz2 + "\n");

            }

            retorno += "\nTRAZA XZ:\n";

            if (signos.charAt(0) == '-' || signos.charAt(2) == '-') {
                if (signos.charAt(0) == '-') {
                    retorno += "\nEs una hiperbola con el Centro en: (" + l + ", " + h + ")\n"
                            + "\nFocos:  n---> f1(" + h + ", " + String.format("%.2f", (raizTrazaXZ)).replaceFirst(",", ".") + ")  ,  f2(" + h + ", " + String.format("%.2f", (raizTrazaXZ * -1)).replaceFirst(",", ".") + ")\n"
                            + "\nVertices: n--->  v1(" + h + ", " + String.format("%.2f", (raizC)).replaceFirst(",", ".") + ")  ,  v2(" + h + ", " + String.format("%.2f", (raizC * -1)).replaceFirst(",", ".") + ")\n" + "\nEje conjugado: (" + 2 * raizA + ")\n";
                } else {
                    retorno += "\nEs una hiperbola con el Centro en: (" + h + ", " + l + ")\n"
                            + "\nFocos: n----> f1(" + String.format("%.2f", (raizTrazaXZ)).replaceFirst(",", ".") + " , " + l + ") , f2(" + String.format("%.2f", (raizTrazaXZ * -1)).replaceFirst(",", ".") + ", " + l + ")\n"
                            + "\nvertices: n ---> v1(" + String.format("%.2f", (raizA)).replaceFirst(",", ".") + " ," + l + ") , v2(" + String.format("%.2f", (-raizA)).replaceFirst(",", ".") + "," + l + ")\n" + "\nEje conjugado: (" + 2 * raizC + ")\n";
                }

            } else {
                retorno += "\nEs una elipse de la siguiente forma: \n"
                        + "(x^2)/" + a + "+(z^2)/" + c + "=1";

                raizMayor = raizA > raizC ? raizA : raizC;
                raizMenor = raizA < raizC ? raizA : raizC;
                retorno += "\nPUNTOS: "
                        + "\n\tPunto 1: (" + raizMayor + ", " + (h * -1) + ")"
                        + "\n\tPunto 2: (" + raizMayor * -1 + ", " + (h * -1) + ")"
                        + "\n\tPunto 3: (" + (l * -1) + ", " + raizMenor + ")"
                        + "\n\tPunto 4: (" + (l * -1) + ", " + raizMenor * -1 + ")\n";

                int valorY = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un valor para y:"));
                retorno += "\nSe forma una elipse con el centro en: (" + h * -1 + " ," + valorY + " ," + l * -1 + ")";
                resultado = ((h * -1) ^ 2) / ((a * ((1 * b) + (valorY ^ 2 * 1))) / b) + ((l * -1) ^ 2) / ((c * (1 * b) + (valorY ^ 2 * 1)) / b);
                double raiz1 = Math.sqrt((a * ((1 * b) + (Math.pow(valorY, 2) * 1))) / b);
                double raiz2 = Math.sqrt((c * ((1 * b) + (Math.pow(valorY, 2) * 1))) / b);

                retorno += "\nEl valor de a positiva es: " + (raiz1 > raiz2 ? raiz1 : raiz2);
                retorno += "\nEl valor de a negativa es: " + (raiz1 > raiz2 ? -raiz1 : -raiz2);
                retorno += "\nEl valor de b positiva es: " + (raiz1 < raiz2 ? raiz1 : raiz2);
                retorno += "\nEl valor de b negativa es: " + (raiz1 < raiz2 ? -raiz1 : -raiz2 + "\n\n");

            }
            retorno += "\nTRAZA XY:\n";

            if (signos.charAt(0) == '-' || signos.charAt(1) == '-') {
                if (signos.charAt(0) == '-') {
                    retorno += "\nEs una hiperbola con el Centro en (" + k + ", " + h + ")\n"
                            + "\nFocos:  n---> f1(" + h + ", " + String.format("%.2f", (raizTrazaXY)).replaceFirst(",", ".") + ")  ,  f2(" + h + ", " + String.format("%.2f", (raizTrazaXY * -1)).replaceFirst(",", ".") + ")\n"
                            + "\nVertices: n--->  v1(" + h + ", " + String.format("%.2f", (raizB)).replaceFirst(",", ".") + ")  ,  v2(" + h + ", " + String.format("%.2f", (raizB * -1)).replaceFirst(",", ".") + ")\n" + "\nEje conjugado: (" + 2 * raizA + ")\n";
                } else {
                    retorno += "\nEs una hiperbola con el Centro en (" + h + ", " + k + ")\n"
                            + "\nFocos: n----> f1(" + String.format("%.2f", (raizTrazaXY)).replaceFirst(",", ".") + " , " + k + ") , f2(" + String.format("%.2f", (raizTrazaXY * -1)).replaceFirst(",", ".") + ", " + k + ")\n"
                            + "\nvertices: n ---> v1(" + String.format("%.2f", (raizA)).replaceFirst(",", ".") + " ," + k + ") , v2(" + String.format("%.2f", (-raizA)).replaceFirst(",", ".") + "," + k + ")\n" + "\nEje conjugado: (" + 2 * raizB + ")\n";
                }
            } else {
                retorno += ("\nEs una elipse de la siguiente forma: \n"
                        + "(x^2)/" + a + "+(y^2)/" + b + "=1");

                raizMayor = raizA > raizB ? raizA : raizB;
                raizMenor = raizA < raizB ? raizA : raizB;
                retorno += "\n\nPUNTOS: "
                        + "\n\tPunto 1: (" + raizMayor + ", " + (h * -1) + ")"
                        + "\n\tPunto 2: (" + raizMayor * -1 + ", " + (h * -1) + ")"
                        + "\n\tPunto 3: (" + (k * -1) + ", " + raizMenor + ")"
                        + "\n\tPunto 4: (" + (k * -1) + ", " + raizMenor * -1 + ")\n";

                int valorZ = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor de Z:"));

                retorno += "\nSe forma una elipse con el centro en: (" + h * -1 + " ," + k * -1 + " ," + valorZ + ")";
                resultado = ((l * -1) ^ 2) / ((a * ((1 * b) + (valorZ ^ 2 * 1))) / b) + ((l * -1) ^ 2) / ((c * (1 * b) + (valorZ ^ 2 * 1)) / b);
                double raiz1 = Math.sqrt((a * ((1 * c) + (Math.pow(valorZ, 2) * 1))) / c);
                double raiz2 = Math.sqrt((b * ((1 * c) + (Math.pow(valorZ, 2) * 1))) / c);

                retorno += ("\nEl valor de a positiva es: " + (raiz1 > raiz2 ? raiz1 : raiz2));
                retorno += ("\nEl valor de a negativa es: " + (raiz1 > raiz2 ? -raiz1 : -raiz2));
                retorno += ("\nEl valor de b positiva es: " + (raiz1 < raiz2 ? raiz1 : raiz2));
                retorno += ("\nEl valor de b negativa es: " + (raiz1 < raiz2 ? -raiz1 : -raiz2) + "\n");
            }
        } else {
            if (h != 0 && k != 0 && l != 0) {
                retorno += "\nTRAZA YZ\n";
                if (signos.charAt(1) == '-' || signos.charAt(2) == '-') {
                    if (signos.charAt(1) == '-') {
                        retorno += ("\nEs una hiperbola con el Centro en (" + k * -1 + ", " + l * -1 + ")\n"
                                + "\nFocos:  n---> f1(" + k * -1 + ", " + String.format("%.2f", (raizTrazaYZ + l * -1)).replaceFirst(",", ".") + ")  ,  f2(" + k * -1 + ", " + String.format("%.2f", (-1 * l - raizTrazaYZ)).replaceFirst(",", ".") + ")\n"
                                + "\nVertices: n--->  v1(" + k * -1 + ", " + String.format("%.2f", (l * -1 + raizC)).replaceFirst(",", ".") + ")  ,  v2(" + k * -1 + ", " + String.format("%.2f", (-1 * l - raizC)).replaceFirst(",", ".") + ")\n" + "\nEje conjugado: (" + 2 * raizB + ")");
                    } else {
                        retorno += ("\nEs una hiperbola con el Centro en (" + k * -1 + ", " + l * -1 + ")\n"
                                + "\nFocos: n----> f1(" + String.format("%.2f", (-1 * k + raizTrazaYZ)).replaceFirst(",", ".") + " , " + l * -1 + ") , f2(" + String.format("%.2f", (-1 * k - raizTrazaYZ)).replaceFirst(",", ".") + ", " + l * -1 + ")\n"
                                + "\nvertices: n ---> v1(" + String.format("%.2f", (-1 * k + raizB)).replaceFirst(",", ".") + " ," + l * -1 + ") , v2(" + String.format("%.2f", (-1 * k - raizB)).replaceFirst(",", ".") + "," + l * -1 + ")\n" + "\nEje conjugado: (" + 2 * raizC + ")\n");
                    }
                } else {
                    retorno += ((k != 0 ? "(" : "") + "(y" + (k != 0 ? (k > 0 ? "+" + valorK : valorK) + ")" : "") + "^2)/" + b + "+" + (l != 0 ? "(" : "") + "(z" + (l != 0 ? (l > 0 ? "+" + valorL : valorL) + ")" : "") + "^2)/" + c + "=1");

                    raizMayor = raizB > raizC ? raizB : raizC;
                    retorno += ("\n\nPUNTOS: "
                            + "\n\tPunto 1: (" + ((k * -1) + raizMayor) + ", " + (l * -1) + ")"
                            + "\n\tPunto 2: (" + ((k * -1) - raizMayor) + ", " + (l * -1) + ")"
                            + "\n\tPunto 3: (" + (k * -1) + ", " + ((l * -1) + raizMayor) + ")"
                            + "\n\tPunto 4: (" + (k * -1) + ", " + ((l * -1) - raizMayor) + ")\n\n");

                    int valorX = 0;
                    try {
                        valorX = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor de X: "));

                        retorno += ("Se forma una elipse con el centro en: (" + valorX + " ," + k * -1 + " ," + l * -1 + ")");
                        resultado = ((k * -1) ^ 2) / ((b * ((1 * a) + (valorX ^ 2 * 1))) / a) + ((l * -1) ^ 2) / ((c * (1 * a) + (valorX ^ 2 * 1)) / a);
                        double raiz1 = Math.sqrt((b * ((1 * a) + (Math.pow(valorX, 2) * 1))) / a);
                        double raiz2 = Math.sqrt((c * ((1 * a) + (Math.pow(valorX, 2) * 1))) / a);

                        retorno += ("\nEl valor de a positiva es: " + (raiz1 > raiz2 ? raiz1 : raiz2));
                        retorno += "\nEl valor de a negativa es: " + (raiz1 > raiz2 ? -raiz1 : -raiz2);
                        retorno += ("\nEl valor de b positiva es: " + (raiz1 < raiz2 ? raiz1 : raiz2));
                        retorno += ("\nEl valor de b negativa es: " + (raiz1 < raiz2 ? -raiz1 : -raiz2) + "\n");
                    } catch (NumberFormatException e) {
                        valorX = 0;
                    }
                }
                retorno += ("\n TRAZA XZ:\n");

                if (signos.charAt(0) == '-' || signos.charAt(2) == '-') {
                    if (signos.charAt(0) == '-') {
                        retorno += ("\nEs una hiperbola con el Centro en (" + h * -1 + ", " + l * -1 + ")\n"
                                + "\nFocos:  n---> f1(" + h * -1 + ", " + String.format("%.1f", (l * -1 + raizTrazaXZ)).replaceFirst(",", ".") + ")  ,  f2(" + h * -1 + ", " + String.format("%.1f", (l * -1 - raizTrazaXZ)).replaceFirst(",", ".") + ")\n"
                                + "\nVertices: n--->  v1(" + h * -1 + ", " + String.format("%.1f", (l * -1 + raizC)).replaceFirst(",", ".") + ")  ,  v2(" + h * -1 + ", " + String.format("%.1f", (l * -1 - raizC)).replaceFirst(",", ".") + ")\n" + "\nEje conjugado: (" + 2 * raizA + ")\n");
                    } else {
                        retorno += ("\nEs una hiperbola con el Centro en (" + h * -1 + ", " + l * -1 + ")\n"
                                + "\nFocos: n----> f1(" + String.format("%.1f", (-1 * h + raizTrazaXZ)).replaceFirst(",", ".") + " , " + l * -1 + ") , f2(" + String.format("%.2f", (-1 * h - raizTrazaXZ)).replaceFirst(",", ".") + ", " + l * -1 + ")\n"
                                + "\nvertices: n ---> v1(" + String.format("%.1f", (-1 * h + raizA)).replaceFirst(",", ".") + " ," + l * -1 + ") , v2(" + String.format("%.1f", (-1 * h - raizA)).replaceFirst(",", ".") + "," + l * -1 + ")\n" + "\nEje conjugado: (" + 2 * raizC + ")\n");
                    }
                } else {
                    retorno += ((h != 0 ? "(" : "") + "(x" + (h != 0 ? (h > 0 ? "+" + valorH : valorH) + ")" : "") + "^2)/" + a + "+" + (l != 0 ? "(" : "") + "(z" + (l != 0 ? (l > 0 ? "+" + valorL : valorL) + ")" : "") + "^2)/" + c + "=1");

                    raizMayor = raizA > raizC ? raizA : raizC;
                    retorno += ("\n\nPUNTOS: "
                            + "\n\tPunto 1: (" + ((h * -1) + raizMayor) + ", " + (l * -1) + ")"
                            + "\n\tPunto 2: (" + ((h * -1) - raizMayor) + ", " + (l * -1) + ")"
                            + "\n\tPunto 3: (" + (h * -1) + ", " + ((l * -1) + raizMayor) + ")"
                            + "\n\tPunto 4: (" + (h * -1) + ", " + ((l * -1) - raizMayor) + ")\n");

                    int valorY = 0;
                    try {
                        valorY = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor de y:"));

                        retorno += ("Se forma una elipse con el centro en: (" + h * -1 + " ," + valorY + " ," + l * -1 + ")");
                        resultado = ((h * -1) ^ 2) / ((a * ((1 * b) + (valorY ^ 2 * 1))) / b) + ((l * -1) ^ 2) / ((c * (1 * b) + (valorY ^ 2 * 1)) / b);
                        double raiz1 = Math.sqrt((a * ((1 * b) + (Math.pow(valorY, 2) * 1))) / b);
                        double raiz2 = Math.sqrt((c * ((1 * b) + (Math.pow(valorY, 2) * 1))) / b);

                        retorno += ("\nEl valor de a positivo es: " + (raiz1 > raiz2 ? raiz1 : raiz2));
                        retorno += ("\nEl valor de a negativo es: " + (raiz1 > raiz2 ? -raiz1 : -raiz2));
                        retorno += ("\nEl valor de b positivo es: " + (raiz1 < raiz2 ? raiz1 : raiz2));
                        retorno += ("\nEl valor de b negativo es: " + (raiz1 < raiz2 ? -raiz1 : -raiz2) + "\n");

                    } catch (NumberFormatException e) {
                        valorY = 0;
                    }
                }
                retorno += ("\nTRAZA XY:\n");

                if (signos.charAt(0) == '-' || signos.charAt(1) == '-') {
                    if (signos.charAt(0) == '-') {
                        retorno += ("\nEs una hiperbola con el Centro en (" + h * -1 + ", " + k * -1 + ")\n"
                                + "\nFocos:  n---> f1(" + h * -1 + ", " + String.format("%.2f", (-1 * k + raizTrazaXY)).replaceFirst(",", ".") + ")  ,  f2(" + h * -1 + ", " + String.format("%.2f", (-1 * k - raizTrazaXY)).replaceFirst(",", ".") + ")\n"
                                + "\nVertices: n--->  v1(" + h * -1 + ", " + String.format("%.2f", (-1 * k + raizB)).replaceFirst(",", ".") + ")  ,  v2(" + h * -1 + ", " + String.format("%.2f", (-1 * k - raizB)).replaceFirst(",", ".") + ")\n" + "\nEje conjugado: (" + 2 * raizA + ")\n");
                    } else {
                        retorno += ("\nEs una hiperbola con el Centro en (" + h * -1 + ", " + k * -1 + ")\n"
                                + "\nFocos: n----> f1(" + String.format("%.1f", (-1 * h + raizTrazaXY)).replaceFirst(",", ".") + " , " + k * -1 + ") , f2(" + String.format("%.2f", (-1 * h - raizTrazaXY)).replaceFirst(",", ".") + ", " + k * -1 + ")\n"
                                + "\nvertices: n ---> v1(" + String.format("%.1f", (-1 * h + raizA)).replaceFirst(",", ".") + " ," + k * -1 + ") , v2(" + String.format("%.2f", (-1 * h - raizA)).replaceFirst(",", ".") + "," + k * -1 + ")\n" + "\nEje conjugado: (" + 2 * raizB + ")\n");
                    }
                } else {
                    retorno += ((h != 0 ? "(" : "") + "(x" + (h != 0 ? (h > 0 ? "+" + valorH : valorH) + ")" : "") + "^2)/" + a + "+" + (k != 0 ? "(" : "") + "(y" + (k != 0 ? (k > 0 ? "+" + valorK : valorK) + ")" : "") + "^2)/" + b + "=1");

                    raizMayor = raizA > raizB ? raizA : raizB;
                    retorno += ("\n\nPUNTOS: "
                            + "\n\tPunto 1: (" + ((h * -1) + raizMayor) + ", " + (k * -1) + ")"
                            + "\n\tPunto 2: (" + ((h * -1) - raizMayor) + ", " + (k * -1) + ")"
                            + "\n\tPunto 3: (" + (h * -1) + ", " + ((k * -1) + raizMayor) + ")"
                            + "\n\tPunto 4: (" + (h * -1) + ", " + ((k * -1) - raizMayor) + ")\n");

                    int valorZ = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor de z:")) + l;

                    retorno += "\nSe forma una elipse con el centro en: (" + h * -1 + " ," + k * -1 + " ," + valorZ + ")";
                    resultado = ((l * -1) ^ 2) / ((a * ((1 * b) + (valorZ ^ 2 * 1))) / b) + ((l * -1) ^ 2) / ((c * (1 * b) + (valorZ ^ 2 * 1)) / b);
                    double raiz1 = Math.sqrt((a * ((1 * c) + (Math.pow(valorZ, 2) * 1))) / c);
                    double raiz2 = Math.sqrt((b * ((1 * c) + (Math.pow(valorZ, 2) * 1))) / c);

                    retorno += ("\nEl valor de a positiva es: " + (raiz1 > raiz2 ? raiz1 : raiz2));
                    retorno += "\nEl valor de a negativa es: " + (raiz1 > raiz2 ? -raiz1 : -raiz2);
                    retorno += ("\nEl valor de b positiva es: " + (raiz1 < raiz2 ? raiz1 : raiz2));
                    retorno += ("\nEl valor de b negativa es: " + (raiz1 < raiz2 ? -raiz1 : -raiz2));

                }
            }
        }
        return retorno;
    }

    private String trazasParaboloideHiperbolico(Ecuacion nuevaecuacion) {
        double mayor = 0;
        ordenVariables(nuevaecuacion);
        int i = 0;
        if (buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'x')).isEmpty()) {
            x = 0;
        } else {
            x = Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'x'))) * -1;
        }
        if (buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'y')).isEmpty()) {
            y = 0;
        } else {
            y = Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'y'))) * -1;
        }
        if (buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'z')).isEmpty()) {
            z = 0;
        } else {
            z = Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'z'))) * -1;
        }

        if (zi.startsWith("-") && xi.startsWith("-") && yi.startsWith("+")
                && ordenV[0] == 'z') {
            xi = xi.replaceFirst("-", "+");
            yi = yi.replaceFirst("\\+", "-");
        } else if (zi.startsWith("-") && xi.startsWith("+") && yi.startsWith("-")
                && ordenV[0] == 'z') {

            xi = xi.replaceFirst("\\+", "-");
            yi = yi.replaceFirst("-", "+");
        }

        if (xi.startsWith("-") && yi.startsWith("-") && zi.startsWith("+")
                && ordenV[0] == 'x') {
            yi = yi.replaceFirst("-", "+");
            zi = zi.replaceFirst("\\+", "-");
        } else if (xi.startsWith("-") && yi.startsWith("+") && zi.startsWith("-")
                && ordenV[0] == 'x') {

            yi = yi.replaceFirst("\\+", "-");
            zi = zi.replaceFirst("-", "+");
        }

        if (yi.startsWith("-") && xi.startsWith("-") && zi.startsWith("+")
                && ordenV[0] == 'y') {
            xi = xi.replaceFirst("-", "+");
            zi = zi.replaceFirst("\\+", "-");
        } else if (yi.startsWith("-") && xi.startsWith("+") && zi.startsWith("-")
                && ordenV[0] == 'y') {

            xi = xi.replaceFirst("\\+", "-");
            zi = zi.replaceFirst("-", "+");
        }

        return "Vertice en " + "(" + x + ", " + y + ", " + z + ")\n"
                + trazasParaboloideHip('x', r)
                + trazasParaboloideHip('y', r)
                + trazasParaboloideHip('z', r);
    }

    private String trazasParaboloideHip(char variable, double r) {
        double cAux = 0;
        cAux = c;
        String retorno = "";
        if (variable == 'x') {

            if (ordenV[0] == 'x') {
                if (x != 0) {

                    x = x * (-1);
                    double d1 = yn * (x * c);
                    double d2 = zn * (x * c);
                    String auxy = yi;
                    String auxz = zi;
                    determinarABEHip(d1, d2, auxy, auxz);
                    if (yi.startsWith("+") && zi.startsWith("-")) {
                        retorno += imprimirTrazasTipo1('y', 'z', y, z);
                    } else if (zi.startsWith("+") && yi.startsWith("-")) {
                        retorno += imprimirTrazasTipo2('y', 'z', y, z);
                    }

                } else if (y == 0 && x == 0 && z == 0) {
                    retorno += "La traza XZ es:\n\n"
                            + "y=z," + " y=-z";
                }
            } else if (ordenV[1] == 'x') {
                if (ordenV[0] == 'z' && ordenV[2] == 'y') {
                    double aux = yn * c;
                    if (yi.startsWith("+")) {
                        p = buscarP(aux);
                    } else if (yi.startsWith("-")) {
                        p = buscarP(aux) * -1;
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    if (p > 0) {
                        retorno += "Abre hacia arriba\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia abajo\n";
                    }
                    retorno += "Traza (y,z):\n"
                            + "Foco:--->(" + y + ", " + String.format("%.2f", (z + p)).replaceFirst(",", ".") + ")\n"
                            + "Vertice:--->(" + y + ", " + z + ")\n";

                } else if (ordenV[0] == 'y' && ordenV[2] == 'z') {
                    double aux = zn * c;
                    if (zi.startsWith("+")) {
                        p = buscarP(aux);
                    } else if (zi.startsWith("-")) {
                        p = buscarP(aux) * -1;
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    if (p > 0) {
                        retorno += "Abre hacia derecha\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia izquierda\n";
                    }
                    retorno += "Traza (y,z):\n"
                            + "Foco:--->(" + String.format("%.2f", (y + p)).replaceFirst(",", ".") + ", " + z + ")\n"
                            + "Vertice:--->(" + y + ", " + z + ")\n";

                }

            } else if (ordenV[2] == 'x') {
                if (ordenV[0] == 'z' && ordenV[1] == 'y') {
                    double aux = yn * c;
                    if (yi.startsWith("+")) {
                        p = buscarP(aux);
                    } else if (yi.startsWith("-")) {
                        p = buscarP(aux) * -1;
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    if (p > 0) {
                        retorno += "Abre hacia arriba\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia abajo\n";
                    }
                    retorno += "Traza (y,z):\n"
                            + "Foco:--->(" + y + ", " + String.format("%.2f", (z + p)).replaceFirst(",", ".") + ")\n"
                            + "Vertice:--->(" + y + ", " + z + ")\n";

                } else if (ordenV[0] == 'y' && ordenV[1] == 'z') {
                    double aux = zn * c;
                    if (zi.startsWith("+")) {
                        p = buscarP(aux);
                    } else if (zi.startsWith("-")) {
                        p = buscarP(aux) * -1;
                    }
                    retorno += "El valor de P es:[" + p + "]";
                    if (p > 0) {
                        retorno += "Abre hacia derecha\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia izquierda\n";
                    }
                    retorno += "Traza (y,z):\n"
                            + "Foco:--->(" + String.format("%.2f", (y + p)).replaceFirst(",", ".") + ", " + z + ")\n"
                            + "Vertice:--->(" + y + ", " + z + ")\n";
                }

            }

        } else if (variable == 'y') {

            if (ordenV[0] == 'y') {
                if (y != 0) {
                    y = y * (-1);
                    double d1 = xn * (y * c);
                    double d2 = zn * (y * c);

                    String auxX = xi;
                    String auxz = zi;
                    determinarABEHip(d1, d2, auxX, auxz);
                    if (xi.startsWith("+") && zi.startsWith("-")) {
                        retorno += imprimirTrazasTipo1('x', 'z', x, z);
                    } else if (zi.startsWith("+") && xi.startsWith("-")) {
                        retorno += imprimirTrazasTipo2('x', 'z', x, z);
                    }

                } else if (y == 0 && x == 0 && z == 0) {
                    retorno += "\nLa traza XZ es:\n\n"
                            + "x=z," + " x=-z";

                }
            } else if (ordenV[1] == 'y') {
                if (ordenV[0] == 'z' && ordenV[2] == 'x') {
                    double aux = xn * c;
                    if (xi.startsWith("+")) {
                        p = buscarP(aux);
                    } else if (xi.startsWith("-")) {
                        p = buscarP(aux) * -1;
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    if (p > 0) {
                        retorno += "Abre hacia arriba\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia abajo\n";
                    }
                    retorno += "\nTraza (x,z):\n"
                            + "Foco:--->(" + x + ", " + String.format("%.2f", (z + p)).replaceFirst(",", ".") + ")\n"
                            + "Vertice:--->(" + x + ", " + z + ")\n";

                } else if (ordenV[0] == 'x' && ordenV[2] == 'z') {
                    double aux = zn * c;
                    if (zi.startsWith("+")) {
                        p = buscarP(aux);
                    } else if (zi.startsWith("-")) {
                        p = buscarP(aux) * -1;
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    if (p > 0) {
                        retorno += "Abre hacia derecha\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia izquierda\n";
                    }
                    retorno += "\nTraza (x,z):\n"
                            + "Foco:--->(" + String.format("%.2f", (x + p)).replaceFirst(",", ".") + ", " + z + ")\n"
                            + "Vertice:--->(" + x + ", " + z + ")\n";
                }
            } else if (ordenV[2] == 'y') {
                if (ordenV[0] == 'z' && ordenV[1] == 'x') {
                    double aux = xn * c;
                    if (xi.startsWith("+")) {
                        p = buscarP(aux);
                    } else if (xi.startsWith("-")) {
                        p = buscarP(aux) * -1;
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    if (p > 0) {
                        retorno += "Abre hacia arriba\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia abajo\n";
                    }
                    retorno += "\nTraza (x,z):\n"
                            + "Foco:--->(" + x + ", " + String.format("%.2f", (z + p)).replaceFirst(",", ".") + ")\n"
                            + "Vertice:--->(" + x + ", " + z + ")\n";

                } else if (ordenV[0] == 'x' && ordenV[1] == 'z') {
                    double aux = zn * c;
                    if (zi.startsWith("+")) {
                        p = buscarP(aux);
                    } else if (zi.startsWith("-")) {
                        p = buscarP(aux) * -1;
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    if (p > 0) {
                        retorno += "Abre hacia derecha\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia izquierda\n";
                    }
                    retorno += "\nTraza (y,z):\n"
                            + "Foco:--->(" + String.format("%.2f", (y + p)).replaceFirst(",", ".") + ", " + z + ")\n"
                            + "Vertice:--->(" + y + ", " + z + ")\n";
                }
            }
        } else if (variable == 'z') {

            if (ordenV[0] == 'z') {
                if (z != 0) {

                    z = z * (-1);

                    double d1 = xn * (z * cAux);
                    double d2 = yn * (z * cAux);

                    String auxX = xi;
                    String auxy = yi;
                    determinarABEHip(d1, d2, auxX, auxy);

                    if (xi.startsWith("+") && yi.startsWith("-")) {

                        retorno += imprimirTrazasTipo1('x', 'y', x, y);
                    } else if (yi.startsWith("+") && xi.startsWith("-")) {

                        retorno += imprimirTrazasTipo2('x', 'y', x, y);
                    }
                } else if (y == 0 && x == 0 && z == 0) {
                    retorno += "\nLa traza XY es:\n\n"
                            + "x=y," + " x=-y";
                }
            } else if (ordenV[1] == 'z') {
                if (ordenV[0] == 'y' && ordenV[2] == 'x') {
                    double aux = xn * cAux;
                    if (xi.startsWith("+")) {
                        p = buscarP(aux);
                    } else if (xi.startsWith("-")) {
                        p = buscarP(aux) * -1;
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    if (p > 0) {
                        retorno += "Abre hacia arriba\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia abajo\n";
                    }
                    retorno += "\nTraza (x,y):\n"
                            + "Foco:--->(" + x + ", " + String.format("%.2f", (y + p)).replaceFirst(",", ".") + ")\n"
                            + "Vertice:--->(" + x + ", " + y + ")\n";
                } else if (ordenV[0] == 'x' && ordenV[2] == 'y') {
                    double aux = yn * cAux;
                    if (yi.startsWith("+")) {
                        p = buscarP(aux);
                    } else if (yi.startsWith("-")) {
                        p = buscarP(aux) * -1;
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    if (p > 0) {
                        retorno += "Abre hacia derecha\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia izquierda\n";
                    }
                    retorno += "\nTraza (x,y):\n"
                            + "Foco:--->(" + String.format("%.2f", (x + p)).replaceFirst(",", ".") + ", " + y + ")\n"
                            + "Vertice:--->(" + x + ", " + y + ")\n";
                }
            } else if (ordenV[2] == 'z') {
                if (ordenV[0] == 'y' && ordenV[1] == 'x') {

                    double aux = xn * cAux;
                    if (xi.startsWith("+")) {
                        p = buscarP(aux);
                    } else if (xi.startsWith("-")) {
                        p = buscarP(aux) * -1;
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    if (p > 0) {
                        retorno += "Abre hacia arriba\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia abajo\n";
                    }
                    retorno += "\nTraza (x,y):\n"
                            + "Foco:--->(" + x + ", " + String.format("%.2f", (y + p)).replaceFirst(",", ".") + ")\n"
                            + "Vertice:--->(" + x + ", " + y + ")\n";

                } else if (ordenV[0] == 'x' && ordenV[2] == 'y') {
                    double aux = yn * cAux;
                    if (yi.startsWith("+")) {
                        p = buscarP(aux);
                    } else if (yi.startsWith("-")) {
                        p = buscarP(aux) * -1;
                    }
                    retorno += "El valor de P es:[" + p + "]\n";
                    if (p > 0) {
                        retorno += "Abre hacia derecha\n";
                    } else if (p < 0) {
                        retorno += "Abre hacia izquierda\n";
                    }
                    retorno += "\nTraza (x,y):\n"
                            + "Foco:--->(" + String.format("%.2f", (x + p)).replaceFirst(",", ".") + ", " + y + ")\n"
                            + "Vertice:--->(" + x + ", " + y + ")\n";
                }
            }
        }
        return retorno;
    }

    private void determinarABEHip(double dividiendo1, double dividiendo2, String auxd1, String auxd2) {
        c = Math.sqrt(dividiendo1 + dividiendo2);
        if (auxd1.startsWith("+")) {
            a = Math.sqrt(dividiendo1);
            b = Math.sqrt(dividiendo2);
        } else {
            a = Math.sqrt(dividiendo2);
            b = Math.sqrt(dividiendo1);
        }
    }

    private String trazasHiperboloide2Hojas(Ecuacion nuevaecuacion) {
        double mayor = 0;
        ordenVariables(nuevaecuacion);

        int i = 0;

        if (buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'x')).isEmpty()) {
            x = 0;
        } else {
            x = Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'x'))) * -1;
        }
        if (buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'y')).isEmpty()) {
            y = 0;
        } else {
            y = Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'y'))) * -1;
        }
        if (buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'z')).isEmpty()) {
            z = 0;
        } else {
            z = Integer.parseInt(buscarVariable(nuevaecuacion.getEcuacion(), buscarInicio(nuevaecuacion.getEcuacion(), i, 'z'))) * -1;
        }
        return "Vector del orden para hip de 2 hojas: " + Arrays.toString(orden)
                + "\nVector del orden para hip de 2 hojas: " + Arrays.toString(ordenV)
                + "\nVertice en " + "(" + x + ", " + y + ", " + z + ")"
                + "\n" + trazasParaboloideHip2Hojas('x', r)
                + "\n" + trazasParaboloideHip2Hojas('y', r)
                + "\n" + trazasParaboloideHip2Hojas('z', r);

    }

    private String imprimirTrazasTipo1(char v1, char v2, double r1, double r2) {
        return "\nTraza (" + v1 + ", " + v2 + "): \n\nEl Centro es (" + r1 + ", " + r2 + ")\n"
                + "Focos:  ---> f1(" + String.format("%.2f", (r1 + c)).replaceFirst(",", ".") + ", " + r2 + ")  ,  f2(" + String.format("%.2f", (r1 - c)).replaceFirst(",", ".") + ", " + r2 + ")\n"
                + "Vertices:  --->  v1(" + String.format("%.2f", (r1 + a)).replaceFirst(",", ".") + ", " + r2 + ")  ,  v2(" + String.format("%.2f", (r1 - a)).replaceFirst(",", ".") + ", " + r2 + ")\n"
                + "El valor de a es: " + String.format("%.2f", a).replaceFirst(",", ".") + "\n"
                + "El valor de b es:" + String.format("%.2f", b).replaceFirst(",", ".");
    }

    private String imprimirTrazasTipo2(char v1, char v2, double r1, double r2) {
        return "\nTraza (" + v1 + ", " + v2 + "): \n\nEl Centro es (" + r1 + ", " + r2 + ")\n"
                + "Focos:  ---> f1(" + r1 + ", " + String.format("%.2f", (r2 + c)).replaceFirst(",", ".") + ")  ,  f2(" + r1 + ", " + String.format("%.2f", (r2 - c)) + ")\n"
                + "Vertices:  ---> v1(" + r1 + ", " + String.format("%.2f", (r2 + a)).replaceFirst(",", ".") + ")  ,  v2(" + r1 + ", " + String.format("%.2f", (r2 - a)) + ")\n"
                + "El valor de a es: " + String.format("%.2f", a).replaceFirst(",", ".") + "\n"
                + "El valor de b es:" + String.format("%.2f", b).replaceFirst(",", ".") + "\n\n";
    }

    private String trazasParaboloideHip2Hojas(char variable, double r) {
        String retorno = "";
        if (variable == 'x') {
            if (xi.startsWith("+")) {
                retorno += "La traza YZ no existe\n\n";
            } else if (xi.startsWith("-") && ordenV[0] == 'x') {
                if (ordenV[1] == 'y' && ordenV[2] == 'z') {
                    if (yi.startsWith("+")) {
                        String auxy = yi;
                        String auxz = zi;
                        determinarABEHip(yn, zn, auxy, auxz);
                        retorno += imprimirTrazasTipo1('y', 'z', y, z);
                    } else {
                        String auxy = yi;
                        String auxz = zi;
                        determinarABEHip(yn, zn, auxy, auxz);
                        retorno += imprimirTrazasTipo2('y', 'z', y, z);
                    }
                }
                if (ordenV[1] == 'z' && ordenV[2] == 'y') {
                    if (zi.startsWith("+")) {
                        String auxy = yi;
                        String auxz = zi;
                        determinarABEHip(yn, zn, auxy, auxz);
                        retorno += imprimirTrazasTipo2('y', 'z', y, z);
                    } else {
                        String auxy = yi;
                        String auxz = zi;
                        determinarABEHip(yn, zn, auxy, auxz);
                        retorno += imprimirTrazasTipo1('y', 'z', y, z);
                    }
                }
            } else if (xi.startsWith("-") && ordenV[1] == 'x') {
                if (ordenV[0] == 'y' && ordenV[2] == 'z') {
                    if (yi.startsWith("+")) {
                        String auxy = yi;
                        String auxz = zi;
                        determinarABEHip(yn, zn, auxy, auxz);
                        retorno += imprimirTrazasTipo1('y', 'z', y, z);
                    } else {
                        String auxy = yi;
                        String auxz = zi;
                        determinarABEHip(yn, zn, auxy, auxz);
                        retorno += imprimirTrazasTipo2('y', 'z', y, z);
                    }
                }
                if (ordenV[0] == 'z' && ordenV[2] == 'y') {
                    if (zi.startsWith("+")) {
                        String auxy = yi;
                        String auxz = zi;
                        determinarABEHip(yn, zn, auxy, auxz);
                        retorno += imprimirTrazasTipo2('y', 'z', y, z);
                    } else {
                        String auxy = yi;
                        String auxz = zi;
                        determinarABEHip(yn, zn, auxy, auxz);
                        retorno += imprimirTrazasTipo1('y', 'z', y, z);
                    }
                }
            } else if (xi.startsWith("-") && ordenV[2] == 'x') {
                if (ordenV[0] == 'y' && ordenV[1] == 'z') {
                    if (yi.startsWith("+")) {
                        String auxy = yi;
                        String auxz = zi;
                        determinarABEHip(yn, zn, auxy, auxz);
                        retorno += imprimirTrazasTipo1('y', 'z', y, z);
                    } else {
                        String auxy = yi;
                        String auxz = zi;
                        determinarABEHip(yn, zn, auxy, auxz);
                        retorno += imprimirTrazasTipo2('y', 'z', y, z);
                    }
                }
                if (ordenV[0] == 'z' && ordenV[1] == 'y') {
                    if (zi.startsWith("+")) {
                        String auxy = yi;
                        String auxz = zi;
                        determinarABEHip(yn, zn, auxy, auxz);
                        retorno += imprimirTrazasTipo2('y', 'z', y, z);
                    } else {
                        String auxy = yi;
                        String auxz = zi;
                        determinarABEHip(yn, zn, auxy, auxz);
                        retorno += imprimirTrazasTipo1('y', 'z', y, z);
                    }
                }
            }
        } else if (variable == 'y') {
            if (yi.startsWith("+")) {
                retorno += "La traza XZ no existe";
            } else if (yi.startsWith("-") && ordenV[0] == 'y') {
                if (ordenV[1] == 'x' && ordenV[2] == 'z') {
                    if (xi.startsWith("+")) {
                        String auxX = xi;
                        String auxz = zi;
                        determinarABEHip(xn, zn, auxX, auxz);
                        retorno += imprimirTrazasTipo1('x', 'z', x, z);

                    } else {
                        String auxX = xi;
                        String auxz = zi;
                        determinarABEHip(xn, zn, auxX, auxz);
                        retorno += imprimirTrazasTipo2('x', 'z', x, z);
                    }
                }
                if (ordenV[1] == 'z' && ordenV[2] == 'x') {
                    if (zi.startsWith("+")) {
                        String auxX = xi;
                        String auxz = zi;
                        determinarABEHip(xn, zn, auxX, auxz);
                        retorno += imprimirTrazasTipo2('x', 'z', x, z);
                    } else {
                        String auxX = yi;
                        String auxz = zi;
                        determinarABEHip(xn, zn, auxX, auxz);
                        retorno += imprimirTrazasTipo1('x', 'z', x, z);
                    }
                }
            } else if (yi.startsWith("-") && ordenV[1] == 'y') {
                if (ordenV[0] == 'x' && ordenV[2] == 'z') {
                    if (xi.startsWith("+")) {
                        String auxX = xi;
                        String auxz = zi;
                        determinarABEHip(xn, zn, auxX, auxz);
                        retorno += imprimirTrazasTipo1('x', 'z', x, z);
                    } else {
                        String auxX = xi;
                        String auxz = zi;
                        determinarABEHip(xn, zn, auxX, auxz);
                        retorno += imprimirTrazasTipo2('x', 'z', x, z);
                    }
                }
                if (ordenV[0] == 'z' && ordenV[2] == 'x') {
                    if (zi.startsWith("+")) {
                        String auxX = xi;
                        String auxz = zi;
                        determinarABEHip(yn, zn, auxX, auxz);
                        retorno += imprimirTrazasTipo2('x', 'z', x, z);
                    } else {
                        String auxX = xi;
                        String auxz = zi;
                        determinarABEHip(xn, zn, auxX, auxz);
                        retorno += imprimirTrazasTipo1('x', 'z', x, z);
                    }
                }
            } else if (yi.startsWith("-") && ordenV[2] == 'y') {
                if (ordenV[0] == 'x' && ordenV[1] == 'z') {
                    if (xi.startsWith("+")) {
                        String auxX = xi;
                        String auxz = zi;
                        determinarABEHip(xn, zn, auxX, auxz);
                        retorno += imprimirTrazasTipo1('x', 'z', x, z);
                    } else {
                        String auxX = xi;
                        String auxz = zi;
                        determinarABEHip(xn, zn, auxX, auxz);
                        retorno += imprimirTrazasTipo2('x', 'z', x, z);
                    }
                }
                if (ordenV[0] == 'z' && ordenV[1] == 'x') {
                    if (zi.startsWith("+")) {
                        String auxX = xi;
                        String auxz = zi;
                        determinarABEHip(xn, zn, auxX, auxz);
                        retorno += imprimirTrazasTipo2('x', 'z', x, z);
                    } else {
                        String auxX = xi;
                        String auxz = zi;
                        determinarABEHip(xn, zn, auxX, auxz);
                        retorno += imprimirTrazasTipo1('x', 'z', x, z);
                    }
                }
            }
        } else if (variable == 'z') {
            if (zi.startsWith("+")) {
                retorno += "La traza XY no existe\n";
            } else if (zi.startsWith("-") && ordenV[0] == 'z') {
                if (ordenV[1] == 'x' && ordenV[2] == 'y') {
                    if (xi.startsWith("+")) {
                        String auxX = xi;
                        String auxy = yi;
                        determinarABEHip(xn, yn, auxX, auxy);
                        retorno += imprimirTrazasTipo1('x', 'y', x, y);
                    } else {
                        String auxX = xi;
                        String auxy = yi;
                        determinarABEHip(xn, yn, auxX, auxy);
                        retorno += imprimirTrazasTipo2('x', 'y', x, y);
                    }
                }
                if (ordenV[1] == 'y' && ordenV[2] == 'x') {
                    if (yi.startsWith("+")) {
                        String auxX = xi;
                        String auxy = yi;
                        determinarABEHip(xn, yn, auxX, auxy);
                        retorno += imprimirTrazasTipo2('x', 'y', x, y);
                    } else {
                        String auxX = yi;
                        String auxy = yi;
                        determinarABEHip(xn, yn, auxX, auxy);
                        retorno += imprimirTrazasTipo1('x', 'y', x, y);
                    }
                }
            } else if (zi.startsWith("-") && ordenV[1] == 'z') {
                if (ordenV[0] == 'x' && ordenV[2] == 'y') {
                    if (xi.startsWith("+")) {
                        String auxX = xi;
                        String auxy = yi;
                        determinarABEHip(xn, yn, auxX, auxy);
                        retorno += imprimirTrazasTipo1('x', 'y', x, y);
                    } else {
                        String auxX = xi;
                        String auxy = yi;
                        determinarABEHip(xn, yn, auxX, auxy);
                        retorno += imprimirTrazasTipo2('x', 'y', x, y);
                    }
                }
                if (ordenV[0] == 'y' && ordenV[2] == 'x') {
                    if (yi.startsWith("+")) {
                        String auxX = xi;
                        String auxy = yi;
                        determinarABEHip(xn, yn, auxX, auxy);
                        retorno += imprimirTrazasTipo2('x', 'y', x, y);
                    } else {
                        String auxX = xi;
                        String auxy = yi;
                        determinarABEHip(xn, yn, auxX, auxy);
                        retorno += imprimirTrazasTipo1('x', 'y', x, y);
                    }
                }
            } else if (zi.startsWith("-") && ordenV[2] == 'z') {
                if (ordenV[0] == 'x' && ordenV[1] == 'y') {
                    if (xi.startsWith("+")) {
                        String auxX = xi;
                        String auxy = yi;
                        determinarABEHip(xn, yn, auxX, auxy);
                        retorno += imprimirTrazasTipo1('x', 'y', x, y);
                    } else {
                        String auxX = xi;
                        String auxy = yi;
                        determinarABEHip(xn, yn, auxX, auxy);
                        retorno += imprimirTrazasTipo2('x', 'y', x, y);
                    }
                }
                if (ordenV[0] == 'y' && ordenV[1] == 'x') {
                    if (yi.startsWith("+")) {
                        String auxX = xi;
                        String auxy = yi;
                        determinarABEHip(xn, yn, auxX, auxy);
                        retorno += imprimirTrazasTipo2('x', 'y', x, y);
                    } else {
                        String auxX = xi;
                        String auxy = yi;
                        determinarABEHip(xn, yn, auxX, auxy);
                        retorno += imprimirTrazasTipo1('x', 'y', x, y);
                    }
                }
            }
        }
        return retorno;
    }

    public String getEcuacion() {
        return ecuacion;
    }

    public void setEcuacion(String ecuacion) {
        this.ecuacion = ecuacion;
    }

    public boolean esEcuacion(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public String crearEcuacion(Ecuacion nuevaecuacion) throws EcuacionException {
        String respuesta = "Finalizado.";
        if (nuevaecuacion == null) {
            throw new EcuacionException("Ingrese un valor adecuado");
        } else if (verificarEcuacion(nuevaecuacion, 0) != 4) {
            respuesta = "Error al ingresar los datos de entrada";
        } else {
            identificarEcuacion1(nuevaecuacion);
        }
        return respuesta;
    }

    private void ordenVariables(Ecuacion nuevaecuacion) {
        int pos = 0;
        for (int i = 0; i < nuevaecuacion.getEcuacion().length() - 1; i++) {
            if (nuevaecuacion.getEcuacion().charAt(i) == 'x') {
                ordenV[pos] = 'x';
                orden[pos] = xn;
                pos++;
            } else if (nuevaecuacion.getEcuacion().charAt(i) == 'y') {
                ordenV[pos] = 'y';
                orden[pos] = yn;
                pos++;
            } else if (nuevaecuacion.getEcuacion().charAt(i) == 'z') {
                ordenV[pos] = 'z';
                orden[pos] = zn;
                pos++;
            }
        }
    }

    private int contadorPrimeraVariable(Ecuacion nuevaecuacion) {
        int cont = 0;
        for (int i = 0; nuevaecuacion.getEcuacion().charAt(i) != '='; i++) {
            if (nuevaecuacion.getEcuacion().charAt(i) == 'x' || nuevaecuacion.getEcuacion().charAt(i) == 'y' || nuevaecuacion.getEcuacion().charAt(i) == 'z') {
                cont++;
            }
        }
        return cont;
    }

    public String identificarEcuacion1(Ecuacion nuevaecuacion) {
        if (contadorPrimeraVariable(nuevaecuacion) == 1) {
            return identificarEcuacionP(nuevaecuacion);
        } else if (!nuevaecuacion.getEcuacion().contains("/")) {
            identificarEsfera(nuevaecuacion);
            return identificarEcuacion2(nuevaecuacion);
        } else {
            for (int i = 0; i < nuevaecuacion.getEcuacion().length() - 1; i++) {
                if (nuevaecuacion.getEcuacion().charAt(i) == '=') {
                    String aux4 = almacenarVariables4(nuevaecuacion, i + 1);
                    ig = Integer.parseInt(aux4);
                }
                if (nuevaecuacion.getEcuacion().charAt(i) == 'x') {
                    posx = i;
                    xi = almacenarVariables(nuevaecuacion, i);
                    String aux1 = almacenarVariables2(nuevaecuacion, i);
                    xn = Double.parseDouble(aux1);
                }
                if (nuevaecuacion.getEcuacion().charAt(i) == 'y') {
                    posy = i;
                    yi = almacenarVariables(nuevaecuacion, i);
                    String aux2 = almacenarVariables2(nuevaecuacion, i);
                    yn = Double.parseDouble(aux2);
                }
                if (nuevaecuacion.getEcuacion().charAt(i) == 'z') {
                    posz = i;
                    zi = almacenarVariables(nuevaecuacion, i);
                    String aux3 = almacenarVariables2(nuevaecuacion, i);
                    zn = Double.parseDouble(aux3);
                }
            }
            return identificarEcuacion2(nuevaecuacion);
        }
    }

    private void identificarEsfera(Ecuacion nuevaecuacion) {
        if (comprobarPotencias(nuevaecuacion) == 3) {
            for (int i = 0; i < nuevaecuacion.getEcuacion().length() - 1; i++) {
                if (nuevaecuacion.getEcuacion().charAt(i) == 'x') {
                    posx = i;
                    xi = almacenarVariablesE(nuevaecuacion, i) + "^2)";
                    xn = 1;
                }
                if (nuevaecuacion.getEcuacion().charAt(i) == 'y') {
                    posy = i;
                    yi = almacenarVariablesE(nuevaecuacion, i) + "^2)";
                    yn = 1;
                }
                if (nuevaecuacion.getEcuacion().charAt(i) == 'z') {
                    posz = i;
                    zi = almacenarVariablesE(nuevaecuacion, i) + "^2)";
                    zn = 1;
                }
                if (nuevaecuacion.getEcuacion().charAt(i) == '=') {
                    String aux4 = almacenarVariables4(nuevaecuacion, i + 1);
                    ig = Integer.parseInt(aux4);
                }
            }
        } else {
            System.out.println("error en las potencias de las variables");
        }
    }

    private int comprobarPotencias(Ecuacion nuevaecuacion) {
        int cont = 0;
        for (int i = 0; i < nuevaecuacion.getEcuacion().length() - 1; i++) {
            if (nuevaecuacion.getEcuacion().charAt(i) == '^' && nuevaecuacion.getEcuacion().charAt(i + 1) == '2') {
                cont++;
            }
        }
        return cont;
    }

    private String almacenarVariablesE(Ecuacion nuevaecuacion, int i) {
        String variable = "";
        for (int j = i - 3; nuevaecuacion.getEcuacion().charAt(j) != '^'; j++) {
            variable += nuevaecuacion.getEcuacion().charAt(j);
        }
        return variable;
    }

    public String identificarEcuacionP(Ecuacion nuevaecuacion) {
        for (int i = 0; i < nuevaecuacion.getEcuacion().length() - 1; i++) {
            if (nuevaecuacion.getEcuacion().charAt(i) == '=') {
                String aux4 = almacenarVariables4(nuevaecuacion, i + 1);
                ig = Integer.parseInt(aux4);
            }
            vrbl = nuevaecuacion.getEcuacion().charAt(i + 4);
            if (vrbl == 'x') {
                for (int t = 0; t < nuevaecuacion.getEcuacion().length() - 1; t++) {
                    if (nuevaecuacion.getEcuacion().charAt(t) == 'x') {
                        posx = i;
                        xi = almacenarVariablesP(nuevaecuacion, t - 5);
                        hallarc(xi);
                    }
                    if (nuevaecuacion.getEcuacion().charAt(t) == 'y') {
                        posy = i;
                        yi = almacenarVariables(nuevaecuacion, t);
                        String aux2 = almacenarVariables2(nuevaecuacion, t);
                        yn = Double.parseDouble(aux2);

                    }
                    if (nuevaecuacion.getEcuacion().charAt(t) == 'z') {
                        posz = i;
                        zi = almacenarVariables(nuevaecuacion, t);
                        String aux3 = almacenarVariables2(nuevaecuacion, t);
                        zn = Double.parseDouble(aux3);
                    }

                }
                break;
            } else if (vrbl == 'y') {
                for (int t = 0; t < nuevaecuacion.getEcuacion().length() - 1; t++) {
                    if (nuevaecuacion.getEcuacion().charAt(t) == 'y') {
                        posy = i;
                        yi = almacenarVariablesP(nuevaecuacion, t - 5);
                        hallarc(yi);
                    }
                    if (nuevaecuacion.getEcuacion().charAt(t) == 'x') {
                        posx = i;
                        xi = almacenarVariables(nuevaecuacion, t);
                        String aux1 = almacenarVariables2(nuevaecuacion, t);
                        xn = Double.parseDouble(aux1);
                    }
                    if (nuevaecuacion.getEcuacion().charAt(t) == 'z') {
                        posz = i;
                        zi = almacenarVariables(nuevaecuacion, t);
                        String aux3 = almacenarVariables2(nuevaecuacion, t);
                        zn = Double.parseDouble(aux3);
                    }
                }
                break;
            } else if (vrbl == 'z') {

                for (int t = 0; t < nuevaecuacion.getEcuacion().length() - 1; t++) {
                    if (nuevaecuacion.getEcuacion().charAt(t) == 'z') {
                        posz = i;
                        zi = almacenarVariablesP(nuevaecuacion, t - 5);
                        hallarc(zi);
                    }
                    if (nuevaecuacion.getEcuacion().charAt(t) == 'x') {
                        posx = i;
                        xi = almacenarVariables(nuevaecuacion, t);
                        String aux1 = almacenarVariables2(nuevaecuacion, t);
                        xn = Double.parseDouble(aux1);
                    }
                    if (nuevaecuacion.getEcuacion().charAt(t) == 'y') {
                        posy = i;
                        yi = almacenarVariables(nuevaecuacion, t);
                        String aux2 = almacenarVariables2(nuevaecuacion, t);
                        yn = Double.parseDouble(aux2);
                    }
                }
                break;
            }
        }
        return identificarEcuacion2(nuevaecuacion);
    }

    private void hallarc(String variable) {
        String v = "";
        String d = "";
        String n = "";
        for (int i = 0; variable.charAt(i) != '*'; i++) {
            if (variable.charAt(i) == '1' || variable.charAt(i) == '2' || variable.charAt(i) == '3' || variable.charAt(i) == '4' || variable.charAt(i) == '5'
                    || variable.charAt(i) == '6' || variable.charAt(i) == '7' || variable.charAt(i) == '8' || variable.charAt(i) == '9' || variable.charAt(i) == '/') {
                v += (variable.charAt(i));
            }

        }
        if (v.contains("/")) {
            n = separarN(v, 0, "");
            d = separar(v, posC + 1, "", "");

            c = Double.parseDouble(n) / Double.parseDouble(d);
        } else {
            c = Double.parseDouble(v);

        }
    }

    private String separarN(String v, int i, String num) {
        if (v.charAt(i) == '/') {
            posC = i;
            return num;
        } else {
            num += v.charAt(i);
        }
        return separarN(v, i + 1, num);
    }

    private String separar(String v, int i, String den, String ce) {
        if (i > v.length() - 1) {
            return den;
        } else {
            den += v.charAt(i);
        }
        return separar(v, i + 1, den, ce);
    }

    private String almacenarVariablesP(Ecuacion nuevaecuacion, int i) {
        String variable = "";
        for (int j = i; nuevaecuacion.getEcuacion().charAt(j) != '='; j++) {
            variable += nuevaecuacion.getEcuacion().charAt(j);
        }
        return variable;
    }

    private String almacenarVariables4(Ecuacion nuevaecuacion, int i) {
        String variable = "";
        for (int n = i; n < nuevaecuacion.getEcuacion().length(); n++) {
            variable += nuevaecuacion.getEcuacion().charAt(n);
        }
        return variable;
    }

    private String almacenarVariables3(Ecuacion nuevaecuacion, int j) {
        String variable = "";
        for (int t = j; nuevaecuacion.getEcuacion().charAt(t) != ')'; t++) {
            variable += nuevaecuacion.getEcuacion().charAt(t);
        }
        return variable;

    }

    private String almacenarVariables2(Ecuacion nuevaecuacion, int i) {
        String variable = "";
        for (int j = i; nuevaecuacion.getEcuacion().charAt(j) != '/'; j++) {
            if (nuevaecuacion.getEcuacion().charAt(j + 1) == '/') {
                variable = almacenarVariables3(nuevaecuacion, j + 2);
            }
        }
        return variable;
    }

    private String almacenarVariables(Ecuacion nuevaecuacion, int i) {
        String variable = "";
        for (int j = i - 4; nuevaecuacion.getEcuacion().charAt(j) != '/'; j++) {
            variable += nuevaecuacion.getEcuacion().charAt(j);
        }
        return variable;
    }

    public String identificarEcuacion2(Ecuacion nuevaecuacion) {
        if (xi.contains("^2") && yi.contains("^2") && zi.contains("^2")
                && xi.startsWith("+") && yi.startsWith("+") && zi.startsWith("+")) {
            if (xn == yn && xn == zn && yn == zn && ig != 1) {
                return "La ecuacion es una Esfera.\n\n" + trazasEsfera(nuevaecuacion);
            } else {
                return "La ecuacion es un Elipsoide.\n\n" + trazasElipsoide(nuevaecuacion);
            }
        } else if (xi.contains("^2") && yi.contains("^2") && zi.contains("^2")) {
            if (xi.startsWith("-") || yi.startsWith("-") || zi.startsWith("-") && ig == 1) {
                if (contadorNegativos() == 2) {
                    return "La ecuacion es una hiperboloide de 2 hojas\n\n" + trazasHiperboloide2Hojas(nuevaecuacion);
                } else if (contadorNegativos() == 1 && ig == 1) {
                    return "La ecuacion es una hiperboloide de 1 hoja\n\n" + trazasHiperboloideUnaHoja(nuevaecuacion);
                } else if (contadorNegativos() == 1 && ig == 0) {
                    return "La ecuacion Es un cono\n\n" + trazasDeCono(nuevaecuacion);
                }
            }

        } else if (xn == 0 || yn == 0 || zn == 0) {
            if (vrbl == 'x' && yi.startsWith("+") && zi.startsWith("+")) {
                return "La ecuacion es una Paraboloide Eliptica en x\n\n" + trazasParaboloideElip(nuevaecuacion);
            } else if (vrbl == 'y' && xi.startsWith("+") && zi.startsWith("+")) {
                return "La ecuacion es una Paraboloide Eliptica en y\n\n " + trazasParaboloideElip(nuevaecuacion);
            } else if (vrbl == 'z' && xi.startsWith("+") && yi.startsWith("+")) {

                return "La ecuacion es una Paraboloide Eliptica en z\n\n" + trazasParaboloideElip(nuevaecuacion);
            } else if (vrbl == 'x' && yi.startsWith("-") && zi.startsWith("+") || vrbl == 'x' && yi.startsWith("+") && zi.startsWith("-")) {
                return "La ecuacion es una Paraboloide Hiperbolica en x\n\n" + trazasParaboloideHiperbolico(nuevaecuacion);
            } else if (vrbl == 'y' && xi.startsWith("-") && zi.startsWith("+") || vrbl == 'y' && xi.startsWith("+") && zi.startsWith("-")) {
                return "La ecuacion es una Paraboloide Hiperbolica en y\n\n" + trazasParaboloideHiperbolico(nuevaecuacion);
            } else if (vrbl == 'z' && xi.startsWith("-") && yi.startsWith("+") || vrbl == 'z' && xi.startsWith("+") && yi.startsWith("-")) {
                return "La ecuacion es una Paraboloide Hiperbolica en z\n " + trazasParaboloideHiperbolico(nuevaecuacion);
            }
        }
        return "Ecuación no encontrada...";
    }

    private int contadorNegativos() {
        int cont = 0;
        List<String> signos = new ArrayList<>();
        signos.add(xi);
        signos.add(yi);
        signos.add(zi);
        for (int i = 0; i < signos.size(); i++) {
            if (signos.get(i).startsWith("-")) {
                cont++;
            }
        }
        System.out.println(cont);
        return cont;
    }

    private int verificarEcuacion(Ecuacion nuevaecuacion, int cont) {
        boolean isValid = false;
        if (cont == nuevaecuacion.getEcuacion().length()) {
            return 0;
        } else {
            if (nuevaecuacion.getEcuacion().charAt(cont) == 'x' || nuevaecuacion.getEcuacion().charAt(cont) == 'y'
                    || nuevaecuacion.getEcuacion().charAt(cont) == 'z' || nuevaecuacion.getEcuacion().charAt(cont) == '=') {
                isValid = true;
            }
            return (isValid ? 1 : 0) + verificarEcuacion(nuevaecuacion, cont + 1);
        }
    }

    @Override
    public String toString() {
        return "Formula=" + ecuacion + "";
    }
}
