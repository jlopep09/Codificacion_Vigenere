import java.util.ArrayList;

public class Vigenere {
    String alphabet;
    String keyK;
    int alphabetMod = 0;

    public Vigenere(String alphabeto, String claveK) {
        this.alphabet = alphabeto;
        this.keyK = claveK;
        alphabetMod = alphabeto.length();
    }


    public String decode(String msgCifrado, String mode) {
        String result = "";
        switch (mode){
            case "classic":
                result = decodeClassic(msgCifrado);
                break;
            case "flux":
                result = decodeFlux(msgCifrado);
                break;
            default:
                throw new IllegalArgumentException(mode + " is not a valid mode");
        }
        return result;
    }

    private String decodeClassic(String msgCifrado) {
        String result = "";

        //En la versión Clasica la clave extendida de longitud n se crea basandose en repeticion cíclica de la clave original.
        //creamos clave extendida
        int initialKeyLength = keyK.length();
        int tempIndex = 0;
        while(keyK.length() != msgCifrado.length()){
            if(tempIndex == initialKeyLength){
                tempIndex = 0;
            }
            keyK += keyK.charAt(tempIndex) + "";
            tempIndex++;
        }
        //tenemos el mensaje codificado y la key, para sacar el original tenemos que hacer la diferencia
        result = subtract(msgCifrado);
        return result;
    }

    private String subtract(String msgCifrado) {
        //Obtenemos valores decimales del mensaje y de la key para restarlos
        ArrayList<Integer> valoresMsgCifrado = getDecimalValues(msgCifrado);
        ArrayList<Integer> valoresKeyCifrados = getDecimalValues(keyK);

        //Restamos los valores
        ArrayList<Integer> valoresResultado = new ArrayList<Integer>();
        for (int i = 0; i < valoresMsgCifrado.size() ; i++) {
            int tempVal = valoresMsgCifrado.get(i) - valoresKeyCifrados.get(i);
            while (tempVal <0){
                tempVal+=alphabetMod;
            }
            while (tempVal >= alphabetMod){
                tempVal-=alphabetMod;
            }
            valoresResultado.add(tempVal);
        }

        //Construimos el mensaje traduciendo los valores a su correspondiente simbolo
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < valoresResultado.size(); i++) {
            sb.append(alphabet.charAt(valoresResultado.get(i)));
        }

        //retornamos el valor
        return sb.toString();


    }

    private String decodeFlux(String msgCifrado) {
        String result = "";

        //En la version de flujo la clave extendida se forma con una ecuación concreta
        // en este caso se usa una de recurrencia lineal y homogenea

        return result;
    }
}
