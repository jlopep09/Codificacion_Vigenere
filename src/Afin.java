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
        return "";
    }

    public String decode(String msg){
        int[][] decodeMatrix = getDecryptionMatrix();
        ArrayList<String> msgBlocks = makeItBlocks(msg, longitudBloque);
        int[][] blocksMatrix = matrixIt(msgBlocks);
        blocksMatrix = removeOffset(blocksMatrix);
        int[][] resultMatrix = MatrixProduct(blocksMatrix, decodeMatrix);

        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < resultMatrix[0].length; i++) {
            sb1.append(resultMatrix[resultMatrix.length-1][i]);
        }

        if(Integer.valueOf(sb1.toString())%longitudBloque!=0){
            System.out.println("cuidao");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resultMatrix.length-1; i++) {
            for (int j = 0; j <resultMatrix[i].length ; j++) {
                sb.append(alphabet.charAt(resultMatrix[i][j]));
            }
        }
        return sb.toString();
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
                blocksMatrix[i][j] = alphabet.indexOf(blocks.get(i).charAt(j));
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
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
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
                block = block+"0";
            }
            result.add(block);
        }
        return result;
    }
}
