/**
 * @author José Antonio López Pérez (ChispyDev)
 */
public class Main {
    public static void main(String[] args) {
        //ejercicio1Hill();
        ejercicio1Afin();

    }

    /**
     * printFormat uses System.out.print to show in console the output from Vigenere coder.
     *      when 2 " " are concatenated the method print "\n" instead.
     * @param msgOriginal
     */
    private static void printFormat(String msgOriginal) {
        System.out.println("----------------------------------------");
        for (int i = 0; i <msgOriginal.length() ; i++) {
            if(i<msgOriginal.length()-1){
                if(msgOriginal.charAt(i)==' ' && msgOriginal.charAt(i+1)== ' '){
                    System.out.println("");
                    i++;
                    continue;
                }
            }
            System.out.print(msgOriginal.charAt(i));

        }
        System.out.println("\n----------------------------------------");
    }


    //  SOME EXAMPLES TO TEST THE IMPLEMENTATION

    //  VIGENERE EXERCICES
    private static void VigenereEjercicio0Classic() {
        String alphabeto = "ABCDE";
        String claveK = "EC";
        String msgCifrado = "EDBAD";
        Vigenere coder = new Vigenere(alphabeto, claveK);
        String msgOriginal = coder.decode(msgCifrado, "classic");
        printFormat(msgOriginal);
         }

    private static void ejercicio0FluxVigenere() {
        String alphabeto = "ABCDE";
        String claveK = "EC";
        String msgCifrado = "EDDBD";
        Vigenere coder = new Vigenere(alphabeto, claveK);
        String msgOriginal = coder.decode(msgCifrado, "flux");
        printFormat(msgOriginal);
    }
    private static void ejercicio1Vigenere() {
        String alphabeto = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZáéíóúÁÉÍÓÚ0123456789 ,.:!-¿?()";
        String claveK = "UCBxHcl!6jñydiFoMB!vol!:da";
        String msgCifrado = "cCTñScE((ÚHGfiXfúPhmqzgi:uÍCrzVtCc vHC:lJfMHovhb4xAi3VFKxfsp AGyv(YsáSWvHb¿iopÁKDxKcD5ÉnGofpJGÁP5mfA(lr ÁCrIhis¿6)o-vpHoCFnmAFs3veÍEJIRcd4pneGjCFzCrgKrz4kxeLGMñHiF!ÉñySBmvrPT?zfFh3ñuáCSñLnop6mCo:oFqTB4KICj3oá3sCxPqbñbuñLgwvzMr?DHE!gfiUsN!Z.njeCñp:(X8WP4CoJ4ñpaLEPOYkohgneKdBJAjUcxod43: Sk8mr:bB6AIIl(7JóBevAs53NiÚVPño,-35";
        Vigenere coder = new Vigenere(alphabeto, claveK);
        String msgOriginal = coder.decode(msgCifrado, "classic");
        String msgRecifrado = coder.encode(msgOriginal, "classic");
        System.out.println(msgRecifrado);
        printFormat(msgOriginal);
    }
    private static void ejercicio2Vigenere() {
        String alphabeto = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZáéíóúÁÉÍÓÚ0123456789 ,.:!-¿?()";
        String claveK = "ÑaKtfDaZ2F77DU8ExqtUVdñQmzw1úF";
        String msgCifrado = "aeWMfPd4íJaÍH3duBJNUMpCSsDñéÉTé5K2ÓtjIYVñp!VS9ópUT5Gúr4vcOwxdna!ó0UÁÑÚQ Í1 ACú4EmARh(VWxt6t23!SL14Oú NG-rt¿Áe4aA0YjeÓÍiTLóuñÍV¿i7S7z4KCÍEDÍLcPL9qpÍYxIz7Ó35í5JLs45-FFy-xÍ2:5O(V:Ú.,NAmxFlN(1F!klv:HbmLWRGaCk!1Ú87:YYD7HmGxGJh(qwsYÓ3-yDiUIñaPI¿E-0hJWzñx.ZrJÓ.QUOxVhú2jGzu buoEy!I3f10?eaCáñ.SHUFNCsAFJQaiwZlHS:qf8xcÉÁI(X¿22lÉKFÓÍaztñyS0PZ suxé(f0CÉhb(7Óxw,bóT5BorRQFVQQAHeÑ:o7ÑtyG,9gHEÚkO,uÍkÚéYI)8dyáG)-KR)Ú:pr5!JéBpAIOHFxNeaqSQr5Ñ";
        Vigenere coder = new Vigenere(alphabeto, claveK);
        String msgOriginal = coder.decode(msgCifrado, "flux");
        String msgRecifrado = coder.encode(msgOriginal, "flux");
        System.out.println(msgRecifrado);
        printFormat(msgOriginal);
    }

    // EXERCICES CIFRADO HILL
    private static void ejercicio1Hill(){
        String alphabeto = "aábcdeéfghiíjklmnñoópqrstuúvwxyzAÁBCDEÉFGHIÍJKLMNÑOÓPQRSTUÚVWXYZ0123456789 ,.:;-()¿?";
        int[][] claveCifrado = {
                {63, 57, 3,  29, 46, 35},
                {30, 52, 21, 80, 44, 12},
                {37, 23, 53, 60, 16, 56},
                {77, 11, 82, 74, 46, 53},
                {33, 56, 81, 72, 37, 37},
                {12, 11, 68, 55, 22, 19} };
        Hill hill = new Hill(alphabeto, claveCifrado);
        String msgDescifrado = hill.decode("3M)-y--2kdHÑSY-ZcG)y.UÚ8m,ZvlñíLAAÑsYO3A80r3y?C0:óon2zwbzdGÁáaAZTfú¿QHuYqqMq(Cí8á6cCW8KÍ-CaB7DJ1XczQZJ(eOzU8fvHAbúIpwáI-,sU2PBjjñPJBÉñx,UÚ1?RáÁÚRéó.pwGA9í6yZ)áééJTbwor¿w,?Vú--gFgÁFÍzaYiUtTx3B,ccóGahw óáiYAJOfD,zuéV:óNbÚÉ6é1WWoÚg(p7jGxESÚsGxLOVvNppqPgH0u6:Q0Y:É¿Gc)ñBLkBpMskWúÓÁa¿dU,NTÍú8ñhRbYósÚí:hAmBmiEgxg0VcIélÁ É)q,adóhÓloxLJlcc(yjÁ¿ñ8áOvn.TonwiPjgG2)C.EeKOw0l60Dig é(CEhFPÍÑiqzÚHmaxF4USavqÚZoéÉi(nfRv,t?ppEQCúd)crÑbú4:í?.d)crÑbIBñáIÚMC1vr(LMRNyÑ3ÓQ4iÚ¿.l  rtMcpcÁZfeñÉZ,Ó¿)pWb0ÁBB?w77UwÑj¿jrP25txdhÁ");
        System.out.println(msgDescifrado.length());
        printFormat(msgDescifrado);
        System.out.println(hill.encode(msgDescifrado));
    }
    // EXERCICES CIFRADO AFIN
    private static void ejercicio1Afin(){
        String alphabeto = "aábcdeéfghiíjklmnñoópqrstuúvwxyzAÁBCDEÉFGHIÍJKLMNÑOÓPQRSTUÚVWXYZ0123456789 ,.:;-()¿?";
        int[][] claveCifrado = {
                {50, 25, 0, 81, 4},
                {10, 39, 19, 67, 51},
                {34, 49, 63, 9, 56},
                {31, 21, 33, 55, 6},
                {82, 69, 34, 48, 1}
        };
        int[] claveCifrado2 =
                {24, 11, 34,  70, 78} ;
        Afin afin = new Afin(alphabeto, claveCifrado, claveCifrado2);
        String msgDescifrado = afin.decode("¿ÍlÚgÑ0ucPsÁó:KÓHaw60íRbp5AuvpéUs9m8.Q0zr?ZDYlCZ0lÁNzaÍÚ9SlÚ;hR7:(GWACB6H8OláxlRLóAcGÍoeñPj6,e)0Égn5ZÓJ;,(ÚCEwópCÁ;G0lOu3úzjfÍoeñPivZhsVfe6;QnlBz5S6(;NOpLvO¿GjkrsIÚÚXUÁga?ÍÑ)u0.?9éRfHN4ÚmmaJ4o49BFNTÓóAyóeY0B2rñjpnjenXQóRoáxfuTÚZFÚmpóG9v-úld8S3XáéÓ8ÚALz-;NníPídA ÓUeÍEd.o vwgu:eñT8)E7U-?hXeqÚHgpdÉhKiEñTáTxC8)KybPd.ÑZVuálwf(8lú;8.xnóCsWP1JP)ñn9ÉÚ;4iÍék9.lCAsÉOB");
        System.out.println(msgDescifrado.length());
        printFormat(msgDescifrado);
        System.out.println(afin.encode(msgDescifrado));
    }
}
