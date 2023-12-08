import java.util.Arrays;

public class VigenereTable {
    //Generate a 2D array with all the Vigenere table values
    final String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    String[][] table = new String[26][26];

//    public VigenereTable() {
//        populateTable();
//    }
    public void populateTable() {
        for (int x = 0; x < 26; x++) {
            for (int y = 0; y < 26; y++) {
                table[x][y] = shift(alphabet,x)[y];
                System.out.println("Table [" + x + "][" + y + "] = " + table[x][y]);
            }
        }
    }

    public String[] shift(String[] ab, int amount) {
        if (!(amount > 26)) {
            for (int i = 0; i < ab.length; i++) {
                String next = "-";
                if (i + amount >= 26) {
                    next = alphabet[i + amount - 26];
                } else {
                    next = alphabet[i + amount];
                }
                ab[i] = next;
            }
            for (int k = 0; k < 26; k++) {
                System.out.println("Letter #" + k + " = " + ab[k]);
            }
            return ab;
        } else {
            return null;
        }
    }
}
