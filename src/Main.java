public class Main {
    public static void main(String[] args) {

        ejercicio2();

    }

    private static void ejercicio0Classic() {
        String alphabeto = "ABCDE";
        String claveK = "EC";
        String msgCifrado = "EDBAD";
        Vigenere coder = new Vigenere(alphabeto, claveK);
        String msgOriginal = coder.decode(msgCifrado, "classic");
        printFormat(msgOriginal);
         }

    private static void printFormat(String msgOriginal) {
        for (int i = 0; i <msgOriginal.length() ; i++) {
            if(i<msgOriginal.length()-1){
                if(msgOriginal.charAt(i)==' ' && msgOriginal.charAt(i+1)== ' '){
                    System.out.println();
                    i+=1;
                }
            }
            System.out.print(msgOriginal.charAt(i));
        }
    }

    private static void ejercicio0Flux() {
        String alphabeto = "ABCDE";
        String claveK = "EC";
        String msgCifrado = "EDDBD";
        Vigenere coder = new Vigenere(alphabeto, claveK);
        String msgOriginal = coder.decode(msgCifrado, "flux");
        System.out.println(msgOriginal + " - "+(msgOriginal.equals("ABCDE")));
        printFormat(msgOriginal);
    }
    private static void ejercicio1() {
        String alphabeto = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZáéíóúÁÉÍÓÚ0123456789 ,.:!-¿?()";
        String claveK = "UCBxHcl!6jñydiFoMB!vol!:da";
        String msgCifrado = "cCTñScE((ÚHGfiXfúPhmqzgi:uÍCrzVtCc vHC:lJfMHovhb4xAi3VFKxfsp AGyv(YsáSWvHb¿iopÁKDxKcD5ÉnGofpJGÁP5mfA(lr ÁCrIhis¿6)o-vpHoCFnmAFs3veÍEJIRcd4pneGjCFzCrgKrz4kxeLGMñHiF!ÉñySBmvrPT?zfFh3ñuáCSñLnop6mCo:oFqTB4KICj3oá3sCxPqbñbuñLgwvzMr?DHE!gfiUsN!Z.njeCñp:(X8WP4CoJ4ñpaLEPOYkohgneKdBJAjUcxod43: Sk8mr:bB6AIIl(7JóBevAs53NiÚVPño,-35";
        Vigenere coder = new Vigenere(alphabeto, claveK);
        String msgOriginal = coder.decode(msgCifrado, "classic");
        System.out.println(msgOriginal);
        printFormat(msgOriginal);
    }
    private static void ejercicio2() {
        String alphabeto = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZáéíóúÁÉÍÓÚ0123456789 ,.:!-¿?()";
        String claveK = "ÑaKtfDaZ2F77DU8ExqtUVdñQmzw1úF";
        String msgCifrado = "aeWMfPd4íJaÍH3duBJNUMpCSsDñéÉTé5K2ÓtjIYVñp!VS9ópUT5Gúr4vcOwxdna!ó0UÁÑÚQ Í1 ACú4EmARh(VWxt6t23!SL14Oú NG-rt¿Áe4aA0YjeÓÍiTLóuñÍV¿i7S7z4KCÍEDÍLcPL9qpÍYxIz7Ó35í5JLs45-FFy-xÍ2:5O(V:Ú.,NAmxFlN(1F!klv:HbmLWRGaCk!1Ú87:YYD7HmGxGJh(qwsYÓ3-yDiUIñaPI¿E-0hJWzñx.ZrJÓ.QUOxVhú2jGzu buoEy!I3f10?eaCáñ.SHUFNCsAFJQaiwZlHS:qf8xcÉÁI(X¿22lÉKFÓÍaztñyS0PZ suxé(f0CÉhb(7Óxw,bóT5BorRQFVQQAHeÑ:o7ÑtyG,9gHEÚkO,uÍkÚéYI)8dyáG)-KR)Ú:pr5!JéBpAIOHFxNeaqSQr5Ñ";
        Vigenere coder = new Vigenere(alphabeto, claveK);
        String msgOriginal = coder.decode(msgCifrado, "flux");
        System.out.println(msgOriginal);
        printFormat(msgOriginal);
    }
}
