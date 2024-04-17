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
        result = addition(msgOriginal);

        return result;
    }



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
        result = subtract(msgCifrado);

        return result;
    }

    private void keyGenClassic(String msgCifrado) {
        //En la versión Clasica la clave extendida de longitud n se crea basandose en repeticion cíclica de la clave original.
        int initialKeyLength = keyK.length();
        int tempIndex = 0;
        while(keyK.length() != msgCifrado.length()){
            if(tempIndex == initialKeyLength){
                tempIndex = 0;
            }
            keyK += keyK.charAt(tempIndex) + "";
            tempIndex++;
        }
    }
    private void keyGenFlux(String msgCifrado) {
        //En la version de flujo la clave extendida se forma con una ecuación concreta
        // en este caso se usa una de recurrencia lineal y homogenea
        ArrayList<Integer> valoresKeyCifrados = getDecimalValues(this.keyK);
        int initialKeySize = valoresKeyCifrados.size();

        while (valoresKeyCifrados.size()< msgCifrado.length()){
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

    private String subtract(String msgCifrado) {
        //Obtenemos valores decimales del mensaje y de la key para restarlos
        ArrayList<Integer> valoresMsgCifrado = getDecimalValues(msgCifrado);
        ArrayList<Integer> valoresKeyCifrados = getDecimalValues(this.keyK);
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
    private String addition(String msgOriginal) {
        //Obtenemos valores decimales del mensaje y de la key para restarlos
        ArrayList<Integer> valoresMsgCifrado = getDecimalValues(msgOriginal);
        ArrayList<Integer> valoresKeyCifrados = getDecimalValues(this.keyK);
        //Restamos los valores
        ArrayList<Integer> valoresResultado = new ArrayList<Integer>();
        for (int i = 0; i < valoresMsgCifrado.size() ; i++) {
            int tempVal = valoresMsgCifrado.get(i) + valoresKeyCifrados.get(i);
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

    private ArrayList<Integer> getDecimalValues(String msgCifrado) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i <msgCifrado.length() ; i++) {
            result.add(alphabet.indexOf(msgCifrado.charAt(i)));
        }
        return result;
    }


}
