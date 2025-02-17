import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * Die Klasse {@code Ubung} liest evenimenteendaten aus einer XML-Datei, verarbeitet diese
 * und speichert Statistiken über Krankenhäuser in eine Datei.
 */

public class ubung1 {
    /**
     * Der Einstiegspunkt des Programms.
     *
     * @param args Kommandozeilenargumente (nicht verwendet)
     */
    public static void main(String[] args) {
        String filepath = "evenimente.json";
        List<Evenimente> evenimenteList = new ArrayList<>();

        try {
            // Liest den JSON-Inhalt aus der Datei und konvertiert ihn in eine Liste von Objekten
            String json = new String(Files.readAllBytes(Paths.get(filepath)));
            Gson gson = new Gson();
            evenimenteList = gson.fromJson(json, new TypeToken<List<Evenimente>>() {
            }.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //erste aufgabe(Mitglieder namen suchen)
        Scanner sc = new Scanner(System.in);
        System.out.println("Anfangsbuchstabe: ");
        String ch = sc.nextLine();
        List<String> noDuplicates = new ArrayList<>();
        for (Evenimente objekt : evenimenteList) {
            if (!noDuplicates.contains(objekt.mitgliedsname)) {
                if (objekt.mitgliedsname.charAt(0) == ch.charAt(0)) {
                    System.out.println(objekt.mitgliedsname);
                }
                noDuplicates.add(objekt.mitgliedsname);
            }
        }

        //2te Aufgabe(Alle Anzeige von die Stark-House)
        // Filtert Evenimente mit dem Haus "Stark" und sortiert nach Datum
        List<Evenimente> evenimenteDaten = new ArrayList<>();
        for (Evenimente objekt : evenimenteList) {
            if (objekt.haus.equals("Stark")) {
                evenimenteDaten.add(objekt);
            }
        }
        evenimenteDaten.sort(Evenimente::compareTo);

        // Ausgabe der gefilterten und sortierten Evenimente
        System.out.println("\nMitgliedern von den Hause Stark gesortet: ");
        for (Evenimente evenimente : evenimenteDaten) {
            System.out.println(evenimente.datum + " - Ereignis: " + evenimente.ereignis);
        }

        // Zählt die Anzahl der Evenimente pro haus
        int eStark = 0;
        int eLannister = 0;
        int eTargaryen = 0;
        int eGreyjoy = 0;
        int eBaratheon = 0;

        for (Evenimente evenimente : evenimenteList) {
            if (evenimente.haus.equals("Stark"))
                eStark++;
            else if (evenimente.haus.equals("Lannister"))
                eLannister++;
            else if (evenimente.haus.equals("Targaryen"))
                eTargaryen++;
            else if (evenimente.haus.equals("Greyjoy"))
                eGreyjoy++;
            else if (evenimente.haus.equals("Baratheon"))
                eBaratheon++;
            else
                throw new RuntimeException("Haus ist falsch");
        }

        // Schreibt die Evenimenten Anzahl in eine Datei
        try {
            String daten = "Stark#" + eStark + "\n" +
                    "Lannister#" + eLannister + "\n" +
                    "Targaryen#" + eTargaryen + "\n" +
                    "Greyjoy#" + eGreyjoy + "\n" +
                    "Baratheon#" + eBaratheon;
            Files.write(Path.of("ergebnis.txt"), daten.getBytes());
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben in die Datei: " + e.getMessage());
        }
    }
}
