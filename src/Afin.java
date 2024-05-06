import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @author José Antonio López Pérez (ChispyDev)
 */
public class Afin {
    private int[][] claveCifrado;
    private int[] claveCifrado2;
    private int longitudBloque;
    private String alphabet;

    public Afin(String alphabet, int[][] claveCifrado,int[] claveCifrado2){
        this.claveCifrado = claveCifrado;
        this.alphabet = alphabet;
        longitudBloque = claveCifrado[0].length;
        if(longitudBloque != claveCifrado.length){
            throw new IllegalArgumentException("Encryption Matrix not valid, columns count != row count");
        }
        this.claveCifrado2 = claveCifrado2;
    }

    public String encode(String msg){
        //MSG -> numeros seguidos segun alfabeto
        // metemos dijitos aleatorios hasta que la parrafada tenga resto 0 respecto tam bloque
        // troceamos en bloques
        // agregamos bloque final de numero de digitos y rellenamos con 0s
        // codificamos los bloques

        System.out.println("msg initial val "+msg.length());
        ArrayList<String> msgBlocks = makeItBlocks(msg, longitudBloque);
        System.out.println("bloques "+msgBlocks.size());
        int[][] blocksMatrix = matrixIt(msgBlocks);

        long valorBloqueFinal = msg.length();
        ArrayList<Integer> bloqueFinal = getRepresentationModule(valorBloqueFinal, longitudBloque);
        int[][] blocksMatrix2 = new int[blocksMatrix.length+1][blocksMatrix[0].length];
        for (int i = 0; i < blocksMatrix.length; i++) {
            for (int j = 0; j < blocksMatrix[0].length; j++) {
                blocksMatrix2[i][j] = blocksMatrix[i][j];
            }
        }
        System.out.println(bloqueFinal);
        for (int i = blocksMatrix2[0].length-1; i >= 0; i--) {
            if(!bloqueFinal.isEmpty()){
                blocksMatrix2[blocksMatrix2.length-1][i] = bloqueFinal.removeFirst();
            }
        }
        blocksMatrix = blocksMatrix2;

        imprimirMatriz(blocksMatrix);
        StringBuilder sb = new StringBuilder();
        int[][] resultMatrix = MatrixProduct(blocksMatrix, claveCifrado);
        resultMatrix = addOffset(resultMatrix);
        for (int i = 0; i < resultMatrix.length; i++) {
            for (int j = 0; j <resultMatrix[i].length ; j++) {
                if(resultMatrix[i][j] == -1){
                    sb.append("0");
                }else{
                    sb.append(alphabet.charAt(resultMatrix[i][j]));
                }

            }
        }

        return sb.toString();
    }
    private ArrayList<Integer> getRepresentationModule(long decimalValue, int length) {
        System.out.println("the repre val is "+decimalValue);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = length-1; i >= 0; i--) {
            // Calcular el valor actual dividido por la base elevada a la potencia actual
            int digit = (int) (decimalValue / Math.pow(alphabet.length(), i));

            // Añadir el dígito al resultado
            result.addFirst(digit);

            // Actualizar el valor decimal restando el dígito calculado
            decimalValue -= digit * Math.pow(alphabet.length(), i);

        }
        return result;

    }

    public String decode(String msg){
        // decodificamos los bloques
        int[][] decodeMatrix = getDecryptionMatrix();
        ArrayList<String> msgBlocks = makeItBlocks(msg, longitudBloque);
        int[][] blocksMatrix = matrixIt(msgBlocks);

        blocksMatrix = removeOffset(blocksMatrix);
        int[][] resultMatrix = MatrixProduct(blocksMatrix, decodeMatrix);
        imprimirMatriz(resultMatrix);

        //Obtenemos bloque de cantidad de digitos
        ArrayList<Integer> ultimoBloque = new ArrayList<>();
        for (int i = 0; i < resultMatrix[0].length; i++) {
                ultimoBloque.add(resultMatrix[resultMatrix.length-1][i]);
        }
        long numeroDigitos = getRepresentation(ultimoBloque, alphabet.length());
        System.out.println("numero Digitos "+numeroDigitos);
        System.out.println("msg.length() "+msg.length());
        long numeroEliminar = msg.length()-longitudBloque-numeroDigitos;
        System.out.println(numeroEliminar+"afdaf");
        for (int i = resultMatrix[0].length-1; i >=0 ; i--) {
            if(numeroEliminar>0){
                resultMatrix[resultMatrix.length-2][i] = -1;
                numeroEliminar--;
            }

        }
        imprimirMatriz(resultMatrix);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resultMatrix.length-1; i++) {
            for (int j = 0; j <resultMatrix[i].length ; j++) {
                if(resultMatrix[i][j] == -1){

                }else{
                    sb.append(alphabet.charAt(resultMatrix[i][j]));
                }

            }
        }

        return sb.toString();
    }

    private long getRepresentation(ArrayList<Integer> ultimoBloque, int length) {
        BigInteger contador =new BigInteger("0");
        for (int i = 0; i < ultimoBloque.size(); i++) {
            BigInteger pow = new BigInteger((Math.pow(alphabet.length(), i)+"").substring(0,(Math.pow(alphabet.length(), i)+"").length()-2));
            contador = contador.add(pow.multiply(new BigInteger(ultimoBloque.removeLast()+"")));
        }
        return contador.longValue();

    }


    private int[][] removeOffset(int[][] blocksMatrix) {
        for (int i = 0; i < blocksMatrix.length ; i++) {
            for (int j = 0; j < blocksMatrix[0].length; j++) {
                blocksMatrix[i][j]= blocksMatrix[i][j] - claveCifrado2[j];
                while(blocksMatrix[i][j]<0){
                    blocksMatrix[i][j] += alphabet.length();
                }
            }
        }
        return blocksMatrix;
    }
    private int[][] addOffset(int[][] blocksMatrix) {
        for (int i = 0; i < blocksMatrix.length ; i++) {
            for (int j = 0; j < blocksMatrix[0].length; j++) {
                blocksMatrix[i][j]= blocksMatrix[i][j] + claveCifrado2[j];
                while(blocksMatrix[i][j]<0){
                    blocksMatrix[i][j] += alphabet.length();
                }
                blocksMatrix[i][j]= blocksMatrix[i][j] % alphabet.length();
            }
        }
        return blocksMatrix;
    }

    private int[][] MatrixProduct(int[][] a, int[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int colsB = b[0].length;

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                // Calculating the dot product of row i of matrix a and column j of matrix b
                int sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum += a[i][k] * b[k][j];
                }
                // Module value
                result[i][j] = sum % alphabet.length();
            }
        }

        return result;
    }
    private int[][] matrixIt(ArrayList<String> blocks) {
        int[][] blocksMatrix = new int[blocks.size()][longitudBloque];
        for(int i = 0; i<blocks.size(); i++){
            for(int j = 0; j<longitudBloque; j++){
                if(alphabet.indexOf(blocks.get(i).charAt(j))<0){
                    blocksMatrix[i][j] = 0;
                }else{
                    blocksMatrix[i][j] = alphabet.indexOf(blocks.get(i).charAt(j));
                }

            }
        }
        return blocksMatrix;
    }

    private int[][] getDecryptionMatrix() {

        int result[][] = InversaMatrizModulo.inversaMatrizModulo(claveCifrado, alphabet.length());

        return result;
    }


    // Función para imprimir una matriz
    public static void imprimirMatriz(int[][] matriz) {
        int n = matriz.length;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }
    public static void imprimirMatriz(double[][] matriz) {
        int n = matriz.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * This function is used to create an arraylist of String segments from a full String given a specific blockSize
     * @param msg to be segmented
     * @param blockSize length of the blocks
     * @return String ArrayList
     */
    private ArrayList<String> makeItBlocks(String msg, int blockSize){
        ArrayList<String> result = new ArrayList<>();

        while(msg.length()>blockSize){
            result.add(msg.substring(0,blockSize));
            msg = msg.substring(blockSize);
        }
        if(msg.length()!=0){
            int toAdd = blockSize - msg.length();
            String block = msg;
            for (int i = 0; i < toAdd; i++) {
                block = block+"Ç";
            }
            result.add(block);
        }
        return result;
    }
}
