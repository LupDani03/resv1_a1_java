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
 * Die Klasse {@code Ubung} liest Patientendaten aus einer XML-Datei, verarbeitet diese
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
        for (Evenimente objekt : evenimenteList) {
            if (objekt.mitgliedsname.charAt(0) == ch.charAt(0)) {
                System.out.println(objekt.mitgliedsname);
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
    }
}
