public class Main {
    // main Methode (wird bei Starten von Programm aufgerufen).
    public static void main(String[] args) {
        
        // Initialisierung & Deklarierung von Variable | Aggregation
        Fahrrad mountainBike = new Fahrrad();

        // Aufruf Methode über Punktnotation.
        float steuerPreis = mountainBike.steuerBerechnung();
        System.out.println(steuerPreis);

        Dreirad kinderRad = new Dreirad();
        kinderRad.printFarbe();
    }
}