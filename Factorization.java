package Tut01;
public class Factorization {

    // Die Methode stellt fest, wieviele Faktoren es werden, also wie groß das Array werden muss, das diese speichert.
    public static int arrLaenge(int n) {
        int anzahlFaktoren = 1;                     // anzahlFaktoren entspricht der Anzahl der Faktoren ( also >= 1).
        // Die folgende Schleife führt quasi schon eine Primfaktorzerlegung durch, merkt sich aber nicht die Werte,
        // sondern zählt nur wie oft n restlos durch i teilbar ist, mit einem i das um eins erhöht wird, bis n/i nicht
        // mehr >= i:
        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0 && n != i) {
                n = n / i;

                anzahlFaktoren++;
            }

        }

        // Dieser Wert entspricht jetzt der notwendigen Länge des Arrays, deshalb wird er zurückgegeben:
        return anzahlFaktoren;
    }

    // Berechnet die Länge des Arrays, in dem die Potenz gespeichert wird. Das ist erforderlich, da der Wert je
    // nach Eingabeparameter variiert:
    public static int potenzLaenge(int[] arr){
        int anzahlPotenzen = 1;          // anzahlPotenzen entspricht der Anzahl der Potenzen ( also >= 1)
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                anzahlPotenzen++;
            }
        }

        // Dieser Wert entspricht jetzt der notwendigen Länge des Arrays, deshalb wird er zurückgegeben:
        return anzahlPotenzen;
    }

    public static void main (String args [] ){

        // Überträgt den Eingabeparameter in die Variable int n:
        int n = Integer.parseInt(args[0]);
        int eingabe = n;

        // Prüft die Eingabe auf gültigen Wert:
        if (eingabe > 0 ){
            int[] faktorArray = new int[arrLaenge(n)];

            // Die for-Schleife führt die Bestimmung der Primzahlfaktoren von n durch. i ist der Teiler, der solange erhöht
            // wird, bis n nicht mehr restlos durch i zu teilen ist. Dann erhöht sich i, solang bis n % i wieder 0.
            int j = 0;
            for (int i = 2; i <= n / i; i++) {
                while (n % i == 0) {
                    n = n / i;
                    faktorArray[j] = i;
                    j++;
                }
            }

            // Für den letzten Wert bzw. falls n selbst eine Primzahl ist.
            if (n > 1) {
                faktorArray[j] = n;
            }

            // die Länge vom array potenzArray wird durch Methode potenzlaenge von faktorArray bestimmt.
            int potenzArray[] = new int[potenzLaenge(faktorArray)];
            int potenz = 0;                     // Hilfsvariable zum Füllen vom potenzArray.
            for (int i = 0; i < faktorArray.length - 1; i++) {
                potenzArray[potenz] += 1;
                // wenn der Wert im Array dem darauf folgenden Wert entspricht, soll nicht addiert werden.
                if (faktorArray[i] == faktorArray[i + 1]) continue;
                potenz += 1;
            }

            // Für den letzten Wert in potenzArray kann die for-Schleife nicht mehr +1 addieren, deshalb muss das hier
            // explizit getan werden.
            potenzArray[potenz] += 1;

            // Folgende if-else-Verzweigung unterscheidet den Fall a) Eingabeparameter war eine Primzahl und b) es gab eine
            // Primfaktorzerlegung in mehrere Faktoren.

            // a)
            System.out.print(eingabe + " = ");
            if (eingabe == faktorArray[0]){
                System.out.print(eingabe + "^1");
            }

            // b)
            else{
                // Es wird ein Array namens ausgabeArray erstellt, das die gleiche Länge wie potenzArray hat und die
                // gleichen Werte wie faktorArray enthält, aber gleiche Faktoren entfernt.
                // Beispiel: aus faktorArray {2,2,5,5,5} übernimmt ausgabeArray die Werte {2,5}.
                int[] ausgabeArray = new int[potenzArray.length];
                int z = 0;
                for (int i = 0; i < faktorArray.length -1; i++) {
                    ausgabeArray[z] = faktorArray[i];
                    if (faktorArray[i] == faktorArray[i + 1]) continue;
                    z += 1;
                }

                // Der letzte Wert kann ggf. nicht von der vorherigen for-Schleife bestimmt werden, deshalb muss dies hier
                // nochmals explizit geschehen:
                ausgabeArray[ausgabeArray.length -1] = faktorArray[faktorArray.length -1];

                // Standard for-Schleife zur Ausgabe der Werte in ausgabeArray als Exponent und in potenzArray als Potenz:
                for (int i = 0; i < ausgabeArray.length -1; i++){
                    System.out.print(ausgabeArray[i] + "^" + potenzArray[i] + " * ");
                }

                // Die for-Schleife stoppt bevor es die Werte an der letzten Stelle der Arrays ausgibt. Das liegt daran,
                // dass hinter dem letzten Faktor sonst auch ein "*" stehen würde. Deshalb muss der letzte Faktor hier
                // esxplizit ausgegeben werden:
                System.out.print(ausgabeArray[ausgabeArray.length -1] + "^" + potenzArray[potenzArray.length -1] + "\n");

            }
        }
        // Falls Eingabewert ungültig:
        else System.out.println("Keine gültige Eingabe.");
    }
}