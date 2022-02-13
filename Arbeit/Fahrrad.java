// Klassenkopf
public class Fahrrad {
    // Klassenrumpf
    public String farbe;
    public float preis;
    public String benutzer;

    // Konstruktor
    public Fahrrad() {
        preis = 95.99f;
        System.out.println("test " + preis + "€");
    }

    // überladener Konstruktor
    public Fahrrad(String name) {
        preis = 95.99f;
        benutzer = name;
        System.out.println("Name " + benutzer);
    }

    // Methode mit Rückgabewert (float)
    public float steuerBerechnung() {
        return preis * 1.12f;
    }
    
    /* überladene Methode 
        - Betrifft Methodenparameter Name + Datentyp
        - Signatur muss unterschiedlich sein.
          (Methodenname & Methodenparameter-Typen)
    */
    public float steuerBerechnung(float steuerSatz) {
        return preis * steuerSatz;
    }

    public float steuerBerechnung(int steuerSatz) {
        return preis * steuerSatz;
    }

    /* Datentypen
        Primitive Datentypen:
            int (ganze Zahlen)
            short (kurzer int)
            long (langer int)
            float (Fließkommazahlen)
            double (längere Floats)
            char (Schriftzeichen)
            boolean (True / False)
        erweiterte Datentyp:
            String (Array)
    */
}

/* Kohäsion:
    jede Klasse oder Methode in einem Programm bearbeitet eine getrennte Aufgabe.
    Dadurch ist der Code effizient gut lesbar und Redundanz wird vermieden.
*/

/* Nutzerbeziehung:
    Beziehungen zwischen verschiedenen Klassen in einem Programm.
    Zum Beispiel in Form von Vererbung. (Hierarchie von Klassen, Oberklasse & Unterklasse)
    Assoziation / Aggregation (Ticket ist Teil von Ticketautomat)
*/

/* Namenskonventionen:
    Klassen (Konstruktor) werden groß geschrieben (Nomen).
    Methoden & Variablen in Kamel-Schreibweise.
    Alle Namen sollten sinnvoll gewählt sein (Vorgänge beschreiben).
*/