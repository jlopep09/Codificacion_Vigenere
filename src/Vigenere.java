/**
 * @author José Antonio López Pérez (ChispyDev)
 */
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

    /**
     * Vigenere encode method
     * @param msgOriginal msg to be encoded
     * @param mode the desire mode, must be "classic" or "flux"
     * @return encoded String
     */
    public String encode(String msgOriginal, String mode) {
        String result = "";
        //actualizamos las claves segun el modo
        switch (mode){
            case "classic":
                keyGenClassic(msgOriginal);
                break;
            case "flux":
                keyGenFlux(msgOriginal);
                break;
            default:
                throw new IllegalArgumentException(mode + " is not a valid mode");
        }
        //tenemos el mensaje original y la key, para sacar el codificado tenemos que hacer la suma de ambos
        result = operator(msgOriginal, true);

        return result;
    }


    /**
     * Vigenere decode method
     * @param msgCifrado msg to be decoded
     * @param mode the desire mode, must be "classic" or "flux"
     * @return decoded String
     */
    public String decode(String msgCifrado, String mode) {
        String result = "";
        //actualizamos las claves segun el modo
        switch (mode){
            case "classic":
                 keyGenClassic(msgCifrado);
                break;
            case "flux":
                keyGenFlux(msgCifrado);
                break;
            default:
                throw new IllegalArgumentException(mode + " is not a valid mode");
        }
        //tenemos el mensaje codificado y la key, para sacar el original tenemos que hacer la diferencia
        result = operator(msgCifrado, false);

        return result;
    }

    /**
     * keyGenClassic method is used to set up correctly the keyK to be used in decode or encode methods using classic mode
     * @param msgInput msgInput is required to prepare key length correctly
     */
    private void keyGenClassic(String msgInput) {
        //En la versión Clasica la clave extendida de longitud n se crea basandose en repeticion cíclica de la clave original.
        int initialKeyLength = keyK.length();
        int tempIndex = 0;
        while(keyK.length() != msgInput.length()){
            if(tempIndex == initialKeyLength){
                tempIndex = 0;
            }
            keyK += keyK.charAt(tempIndex) + "";
            tempIndex++;
        }
    }
    /**
     * keyGenFlux method is used to set up correctly the keyK to be used in decode or encode methods using flux mode
     * @param msgInput msgInput is required to prepare key length correctly
     */
    private void keyGenFlux(String msgInput) {
        //En la version de flujo la clave extendida se forma con una ecuación concreta
        // en este caso se usa una de recurrencia lineal y homogenea
        ArrayList<Integer> valoresKeyCifrados = getDecimalValues(this.keyK);
        int initialKeySize = valoresKeyCifrados.size();

        while (valoresKeyCifrados.size()< msgInput.length()){
            int newValue = 0;
            for (int i = 0; i < initialKeySize; i++) {
                newValue += valoresKeyCifrados.get(i)*valoresKeyCifrados.get(valoresKeyCifrados.size()-(1+i));
            }
            //lo buscamos en el modulo
            while (newValue <0){
                newValue+=alphabetMod;
            }
            while (newValue >= alphabetMod){
                newValue-=alphabetMod;
            }
            valoresKeyCifrados.add(newValue);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(keyK);

        for (int i = initialKeySize; i < valoresKeyCifrados.size(); i++) {

            sb.append(alphabet.charAt(valoresKeyCifrados.get(i)));
        }

        keyK = sb.toString();

    }

    /**
     * operator method is used to add or sub the msgInput and the keyK
     * @param msgInput msg to be used in the operation
     * @param positive must be true to addition and false to subtract
     * @return result String value
     */
    private String operator(String msgInput, boolean positive){
        //Obtenemos valores decimales del mensaje y de la key para restarlos
        ArrayList<Integer> valoresMsgCifrado = getDecimalValues(msgInput);
        ArrayList<Integer> valoresKeyCifrados = getDecimalValues(this.keyK);
        //Operamos los valores
        ArrayList<Integer> valoresResultado = new ArrayList<Integer>();
        for (int i = 0; i < valoresMsgCifrado.size() ; i++) {
            int tempVal = 0;
            if(positive){
                tempVal = valoresMsgCifrado.get(i) + valoresKeyCifrados.get(i);
            }else{
                tempVal = valoresMsgCifrado.get(i) - valoresKeyCifrados.get(i);
            }

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

    /**
     * getDecimalValues method is used to convert the msgInput into the corresponding Integer values. The values are
     * grouped into an ArrayList Integer result.
     * @param msgInput
     * @return Arraylist of the corresponding msgInput characters values according to the Alphabet
     */
    private ArrayList<Integer> getDecimalValues(String msgInput) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i <msgInput.length() ; i++) {
            result.add(alphabet.indexOf(msgInput.charAt(i)));
        }
        return result;
    }


}
