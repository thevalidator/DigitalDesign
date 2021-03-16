import utils.StringDecoder;

public class Main {
    public static void main(String[] args) {

        String s = "2[qw2[f1[oo]f]e]3[r]10[m4[aa]3[yy]]";

        System.out.println(StringDecoder.validateString(s));
        System.out.println(StringDecoder.getDecodedText(s));

    }
}
