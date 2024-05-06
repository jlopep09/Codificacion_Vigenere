import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InversaMatrizModulo {

    // Función para calcular la inversa de una matriz de enteros modulo z
    public static int[][] inversaMatrizModulo(int[][] matriz, int z) {

        String scriptPath = "C:\\Users\\ChispyDev\\Documents\\AA_repos\\Vigenere\\Codificacion_Vigenere\\src\\inversa.py";
        String parametro = z+" ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                sb.append(matriz[i][j]+" ");
            }
        }
        String matrizParametro = sb.toString();
        String resultadoPython = "";
        try {
            // Ejecutar el script de Python
            String command = "python " + scriptPath + " " + parametro+ matrizParametro;
            Process proceso = Runtime.getRuntime().exec(command);

            // Leer la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

            resultadoPython = reader.readLine();

            // Imprimir el resultado devuelto por el script de Python
            System.out.println("El resultado devuelto por el script de Python es: " + resultadoPython);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[][] result = new int[matriz.length][matriz.length];
        resultadoPython = resultadoPython.substring(6);
        resultadoPython = resultadoPython.replace("(", "");
        resultadoPython = resultadoPython.replace(")", "");
        resultadoPython = resultadoPython.replace("[", "");
        resultadoPython = resultadoPython.replace("]", "");
        resultadoPython = resultadoPython.replace(",", "");
        String[] resultadosString = resultadoPython.split(" ");
        int contador = 0;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = Integer.valueOf(resultadosString[contador]);
                contador++;
            }
        }
        return result;
    }
}

