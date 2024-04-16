public class Main {
    public static void main(String[] args) {
        String alphabeto = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZáéíóúÁÉÍÓÚ0123456789 ,.:!-¿?()";
        String claveK = "UCBxHcl!6jñydiFoMB!vol!:da";
        String msgCifrado = "cCTñScE((ÚHGfiXfúPhmqzgi:uÍCrzVtCc vHC:lJfMHovhb4xAi3VFKxfsp AGyv(YsáSWvHb¿iopÁKDxKcD5ÉnGofpJGÁP5mfA(lr ÁCrIhis¿6)o-vpHoCFnmAFs3veÍEJIRcd4pneGjCFzCrgKrz4kxeLGMñHiF!ÉñySBmvrPT?zfFh3ñuáCSñLnop6mCo:oFqTB4KICj3oá3sCxPqbñbuñLgwvzMr?DHE!gfiUsN!Z.njeCñp:(X8WP4CoJ4ñpaLEPOYkohgneKdBJAjUcxod43: Sk8mr:bB6AIIl(7JóBevAs53NiÚVPño,-35";
        Vigenere coder = new Vigenere(alphabeto, claveK);

        String msgOriginal = coder.decode(msgCifrado, "classic");
    }
}
