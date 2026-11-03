# Esercizio 1 — Importazione di quantità

**Tempo indicativo: 5–7 minuti · Java 25**

L’importazione somma interi decimali non negativi. Una riga non numerica deve produrre una segnalazione ed essere ignorata, senza impedire l’elaborazione delle successive. Le righe sono non nulle; non considerare numeri negativi o overflow.

Il frammento è eseguito in un metodo. I numeri di riga non fanno parte del codice.

```java
01  String[] rows = {"2", "3"};
02  int total = 0;
03  try {
04      for (String row : rows) {
05          total += Integer.parseInt(row);
06      }
07  } catch (NumberFormatException exception) {
08      System.out.println("Invalid row");
09  }
10  System.out.println(total);
```

## 1. Scelta multipla

Qual è l’output con l’input mostrato? Seleziona una sola risposta.

- **A.** `23`.
- **B.** `Invalid row`, poi `0`.
- **C.** `5`.
- **D.** `3`.

## 2. Risposta breve

Valuta il comportamento con `rows = new String[]{"2", "x", "3"}` rispetto al contratto. Se necessario, riscrivi soltanto il ciclo e la gestione dell’eccezione affinché il requisito sia rispettato per ogni riga.

**Lunghezza indicativa:** una frase e massimo 7 righe di codice.

---

# Esercizio 2 — Scrittura di un resoconto

**Tempo indicativo: 5–7 minuti · Java 25**

Il resoconto usa risorse chiudibili. Per osservare la chiusura, `Trace` stampa il proprio nome quando viene chiusa; costruzione e chiusura non sollevano eccezioni.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  class Trace implements AutoCloseable {
02      private final String name;
03      Trace(String name) { this.name = name; }
04      public void close() { System.out.println("Close " + name); }
05  }
```

```java
06  try (Trace first = new Trace("A"); Trace second = new Trace("B")) {
07      System.out.println("Work");
08      throw new IllegalArgumentException();
09  } catch (IllegalArgumentException exception) {
10      System.out.println("Handled");
11  }
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le righe nell’ordine.

- **A.** `Work`, `Handled`, `Close A`, `Close B`.
- **B.** `Work`, `Close A`, `Close B`, `Handled`.
- **C.** `Work`, `Handled`, senza altre stampe.
- **D.** `Work`, `Close B`, `Close A`, `Handled`.

## 2. Risposta breve

Separatamente, un helper riceve un `java.io.Writer` aperto dal chiamante. Il contratto richiede che lo lasci aperto, anche se la scrittura fallisce, e che propaghi eventuali `java.io.IOException`.

```java
12  static void writeReport(java.io.Writer output) throws java.io.IOException {
13      try (output) {
14          output.write("Report");
15      }
16  }
```

Valuta l’helper rispetto al contratto e, se necessario, correggine il corpo. Il parametro è non nullo; non occorre gestire il flush.

**Lunghezza indicativa:** una riga di codice e 1–2 frasi.

---

# Esercizio 3 — Quantità richiesta

**Tempo indicativo: 5–7 minuti · Java 25**

Un componente converte una quantità da testo a intero. Il testo è non nullo; non sono richiesti vincoli di intervallo ulteriori rispetto al tipo `int`. Il metodo attuale è mostrato integralmente.

Il metodo va dichiarato nel corpo di una classe e la chiamata in un suo metodo statico. I numeri di riga non fanno parte del codice.

```java
01  static int count(String text) {
02      try {
03          return Integer.parseInt(text);
04      } catch (NumberFormatException exception) {
05          return 0;
06      } finally {
07          System.out.println("Done");
08      }
09  }
```

```java
10  System.out.println(count("abc"));
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le righe nell’ordine.

- **A.** `0`, poi `Done`.
- **B.** `Done`, poi `0`.
- **C.** Solleva `NumberFormatException`, senza altre stampe.
- **D.** Stampa soltanto `0`.

## 2. Risposta aperta

Una nuova API deve distinguere un conteggio valido pari a zero da un errore di conversione. L’errore deve includere un messaggio di contesto e conservare la causa originaria, senza stampare né restituire valori sostitutivi.

Proponi un contratto di errore basato su un’eccezione di dominio: scegli checked o unchecked e motiva la scelta. Indica la relazione di ereditarietà, il costruttore necessario, l’eventuale modifica alla firma di `count` e il comportamento del suo `catch`. Non riscrivere il parsing.

**Lunghezza indicativa:** uno schema di massimo 6 righe e 1–2 frasi; indica anche che cosa accade al blocco `finally` attuale.
