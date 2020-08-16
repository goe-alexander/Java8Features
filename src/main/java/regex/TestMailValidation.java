package regex;

import java.util.regex.Pattern;

public class TestMailValidation {

    // The Regex is from http://emailregex.com should be conformant to RFC 5322
    static void tester(String mail, boolean valid) {
        String regex = "[a-z0-9!#$%&\'*+\\/=?\\^_\\`{|}~\\-]+(?:\\.[a-z0-9!#$%&\'*+\\/=?\\^_\\`{|}~\\-]+)*@(?:[a-z0-9](?:[a-z0-9\\-]*[a-z0-9])?\\.)+[a-z0-9-]{2,24}$";
        System.out.print(mail+ " ");
        if (Pattern.matches(regex,mail)) {
            System.out.println("VALID");
        } else {
            System.out.println("NOT VALID");
        }
        System.out.print("Should be:");
        if (valid) {
            System.out.println("VALID");
        } else {
            System.out.println("NOT VALID");
        }

        System.out.println();


    }
    public static void main(String[] args) {
        // Plausability Test
        tester("olaf@flebbe.de", true);
        tester("someone@foo.family", true);
        tester("o_laf@flebbe.de", true);
        tester("o-laf@flebbe.de", true);
        tester("olaf@flebbe.de_", false);
        tester("olaf@f_lebbe.de", false);
        tester("olaf@f--lebbe.de", true);
        tester("olaf@-oflebbe.de", false);
        tester("ölaf@flebbe.de", false);
        tester("olaf@flebbe.de--zz", true);
        tester("olaf@flebbe.accountants", true);

    }
}

// old pattern "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
// new pattern ^[a-z0-9!#$%&\'*+\/=?\^_\`{|}~\-]+(?:\.[a-z0-9!#$%&\'*+\/=?\^_\`{|}~\-]+)*@(?:[a-z0-9](?:[a-z0-9\-]*[a-z0-9])?\.)+[a-z0-9]{2,4}$(?:[a-z0-9\-]*[a-z0-9])?$
// what should ^[a-z0-9!#$%&\'*+\\/=?\\^_\\`{|}~\\-]+(?:\\.[a-z0-9!#$%&\'*+\\/=?\\^_\\`{|}~\\-]+)*@(?:[a-z0-9](?:[a-z0-9\\-]*[a-z0-9])?\\.)+[a-z0-9]{2,4}$(?:[a-z0-9\\-]*[a-z0-9])?