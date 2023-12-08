public class VigenereTable {
    //Generate a 2D array with all the Vigenere table values
    String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

    public String[] shift(String[] ab) {
        String firstChar = ab[0];
        for (int i = 0; i < ab.length; i++) {
            String next;
            if (i == 25) {
                next = firstChar;
            } else {
                next = ab[i+1];
            }
            ab[i] = next;
        }
        System.out.println("Shifted alphabet = " + ab[0] + " - " + ab[25]);
        return ab;
    }
}
