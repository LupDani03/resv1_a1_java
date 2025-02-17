import com.google.gson.annotations.SerializedName;

/**
 * Die Klasse repräsentiert eine Evenimente mit relevanten Informationen
 * zu einem medizinischen Fall, einschließlich ID, mitgliedsnameendaten, hausen, ereignis,
 * Datum und Krankenhaus.
 * <p>
 * Diese Klasse unterstützt die Serialisierung in XML und JSON.
 */
public class Evenimente {
    @SerializedName("Id")
    int id;
    @SerializedName("Mitgliedsname")
    String mitgliedsname;
    @SerializedName("Haus")
    String haus;
    @SerializedName("Ereignis")
    String ereignis;
    @SerializedName("Datum")
    String datum;

    /**
     * Konstruktor für die Initialisierung einer Evenimente.
     *
     * @param id          Eindeutige Fall-ID
     * @param mitgliedsname     Name des Evenimente
     * @param haus     hause des Evenimente
     * @param ereignis    ereignis des Evenimente
     * @param datum       Datum des Ereignis
     */
    public Evenimente(int id, String mitgliedsname, String haus, String ereignis, String datum) {
        this.id = id;
        this.mitgliedsname = mitgliedsname;
        this.haus = haus;
        this.ereignis = ereignis;
        this.datum = datum;
    }

    /**
     * Gibt eine String-Darstellung der mitgliedsnamen zurück.
     *
     * @return Formatierte Zeichenkette mit allen Informationen des Falls.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + mitgliedsname + ", haus: " + haus + ", ereignis: " + ereignis + ", Datum: " + datum;
    }

    /**
     * Vergleicht zwei Mitgliedsnamen basierend auf dem Datum.
     * Ereignis mit einem neueren Datum werden als "größer" betrachtet.
     *
     * @param o Die zu vergleichende mitgliedsnamen
     * @return Ein negativer Wert, wenn diese Ereignis neuer ist als die übergebene,
     * ein positiver Wert, wenn sie älter ist, oder 0, wenn beide gleich alt sind.
     */
    public int compareTo(Evenimente o) {
        return this.datum.compareTo(o.datum);
    }
}
