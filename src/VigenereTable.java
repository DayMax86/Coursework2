import java.util.Arrays;

public class VigenereTable {
    //Generate a 2D array with all the Vigenere table values
    public String[] getAlphabet() {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    }
    String[][] table = new String[26][26];

    public VigenereTable() {
        populateTable();
    }

    public String lookup(String columnLetter, String rowLetter) {
        int x = 0, y = 0;
        x = Arrays.stream(getAlphabet()).toList().indexOf(columnLetter.toUpperCase());
        y = Arrays.stream(getAlphabet()).toList().indexOf(rowLetter.toUpperCase());
        return table[x][y];
    }

    public void populateTable() {
        for (int x = 0; x < 26; x++) {
            for (int y = 0; y < 26; y++) {
                table[x][y] = shift(getAlphabet(),x)[y];
                //System.out.println("Table [" + x + "][" + y + "] = " + table[x][y]);
            }
        }
    }

    public String[] shift(String[] ab, int amount) {
        if (!(amount > 26)) {
            for (int i = 0; i < ab.length; i++) {
                String next;
                if (i + amount >= 26) {
                    next = getAlphabet()[i + amount - 26];
                } else {
                    next = getAlphabet()[i + amount];
                }
                ab[i] = next;
            }
            return ab;
        } else {
            return null;
        }
    }
}
