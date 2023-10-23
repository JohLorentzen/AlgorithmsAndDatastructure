package Stack;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        final String PATH_TO_FILE = "text.txt"; //endre dette filnavnet hvis du ønsker å teste en annen fil

        try {
            String text = lesFil(PATH_TO_FILE);
            char[] signs = text.toCharArray();
            Stakk stakk =  new Stakk(signs.length);

            boolean gyldig = true;
            for (char ch : signs) {
                if (!erParentes(ch)) continue;
                if (lukketParentes(ch)) {
                    if (stakk.sjekkStakk() != null && harÅpenOgLukket((char) stakk.sjekkStakk(), ch)) {
                        stakk.pop();
                    } else {
                        gyldig = false;
                    }
                } else {
                    stakk.push(ch);
                }
            }
            gyldig = stakk.tom(); // hvis det er elementer igjen i stakken, betyr det at det finnes ulukkede parenteser


            if (gyldig) {
                System.out.println("\nTeksten bestod testen.");
            } else {
                System.out.println("\nTeksten bestod ikke testen.");
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sjekker etter parentestegn.
     *
     * @param ch Tegnet som skal sjekkes
     * @return Om tegnet er en parentes eller ikke
     */
    public static boolean erParentes(char ch) {
        return ch == '(' || ch == ')' || ch == '[' || ch == ']' || ch == '{' || ch == '}';
    }

    /**
     * Sjekker etter lukkede parenteser
     * @param ch Tegnet som skal sjekkes
     * @return Om tegnet er en lukket parentes eller ikke
     */
    public static boolean lukketParentes(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    /**
     * Sjekker om parentesene som blir sendt inn matcher hverandre.
     * @param åpen Den venstre parentesen
     * @param lukket Den høyre parentesen
     * @return Om parentesene åpnes og lukkes
     */
    public static boolean harÅpenOgLukket(char åpen, char lukket) {
        return ((åpen == '(' && lukket == ')' || åpen == '[' && lukket == ']' || åpen == '{' && lukket == '}'));
    }

    /**
     * Leser av teksten i en fil og returnerer denne som en String.
     *
     * @param filAdresse Relative adressen til filen man ønsker å lese
     * @return En string av teksten fra filen
     * @throws IOException
     */
    public static String lesFil(String filAdresse) throws IOException {
        InputStreamReader isr = new InputStreamReader(Main.class.getResourceAsStream(filAdresse));
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String linje;

        while ((linje = reader.readLine()) != null) {
            sb.append(linje);
        }

        return sb.toString();
    }
}
