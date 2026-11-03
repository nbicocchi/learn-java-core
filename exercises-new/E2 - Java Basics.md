> **E2 — Java Basics** · [Indice del corso](../README.md#modules)
> Gli esercizi sono organizzati per obiettivo principale e possono richiamare competenze dei moduli precedenti. La numerazione è indipendente per ciascun macro-modulo.


---

# Esercizio 1 — Aggiornamento delle misure

**Tempo indicativo: 5–7 minuti · Java 25**

Le misure sono interi tra 0 e 100. `increase` deve aumentare di uno gli elementi dell’array ricevuto; `reset` deve azzerarli. Gli array sono non nulli, anche eventualmente vuoti.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato, con `java.util.Arrays` importata. I numeri di riga non fanno parte del codice.

```java
01  class Measures {
02      static void increase(int[] values) {
03          for (int i = 0; i < values.length; i++) {
04              values[i]++;
05          }
06      }
07      static void reset(int[] values) {
08          values = new int[values.length];
09      }
10  }
```

```java
11  int[] original = {2, 5};
12  int[] alias = original;
13  Measures.increase(original);
14  System.out.println(Arrays.toString(alias));
15  System.out.println(original == alias);
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le due righe nell’ordine.

- **A.** `[2, 5]`, poi `false`.
- **B.** `[3, 6]`, poi `false`.
- **C.** `[3, 6]`, poi `true`.
- **D.** `[2, 5]`, poi `true`.

## 2. Risposta breve

Considera separatamente `int[] data = {4, 7}; Measures.reset(data);`. Il metodo rispetta il proprio contratto? Motiva facendo riferimento al passaggio dei parametri e, se necessario, correggine il corpo mantenendo la firma.

**Lunghezza indicativa:** 1–2 frasi e massimo 3 righe di codice.

---

# Esercizio 2 — Righe di una tabella

**Tempo indicativo: 5–7 minuti · Java 25**

Una tabella è un array non nullo di righe non nulle, anche di lunghezze diverse. `sum` deve sommare tutti gli elementi, restituendo zero per una tabella vuota. I valori sono piccoli interi: non considerare overflow.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  class Tables {
02      static int sum(int[][] values) {
03          int total = 0;
04          for (int r = 0; r < values.length; r++) {
05              for (int c = 0; c < values[0].length; c++) {
06                  total += values[r][c];
07              }
08          }
09          return total;
10      }
11  }
```

```java
12  int[][] original = {{1, 2}, {3}};
13  int[][] copy = original.clone();
14  copy[0][0] = 9;
15  copy[1] = new int[]{7};
16  System.out.println(original[0][0]);
17  System.out.println(original[1][0]);
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le due righe nell’ordine.

- **A.** `1`, poi `3`.
- **B.** `9`, poi `3`.
- **C.** `9`, poi `7`.
- **D.** `1`, poi `7`.

## 2. Risposta breve

Valuta separatamente `Tables.sum(new int[][]{{1}, {2, 3}})` rispetto al contratto. Indica il risultato richiesto e quello effettivo; se necessario, modifica una sola riga del metodo perché funzioni con tutte le forme ammesse.

**Lunghezza indicativa:** 1–2 frasi e una riga di codice.

---

# Esercizio 3 — Ricerca di un codice

**Tempo indicativo: 5–7 minuti · Java 25**

`firstIndex` deve restituire l’indice della prima occorrenza di un codice nell’array originale, oppure `-1` se assente, senza modificare l’input. L’array è non nullo e può essere non ordinato o contenere duplicati. Per un array ordinato, `Arrays.binarySearch` restituisce un indice corrispondente, non necessariamente il primo tra duplicati, oppure un valore negativo se l’elemento manca.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato, con `java.util.Arrays` importata. I numeri di riga non fanno parte del codice.

```java
01  class Codes {
02      static int firstIndex(int[] values, int code) {
03          Arrays.sort(values);
04          int position = Arrays.binarySearch(values, code);
05          return position < 0 ? -1 : position;
06      }
07  }
```

```java
08  int[] source = {4, 7};
09  int[] expanded = Arrays.copyOf(source, 4);
10  expanded[0] = 9;
11  System.out.println(Arrays.toString(expanded));
12  System.out.println(Arrays.toString(source));
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le due righe nell’ordine.

- **A.** `[9, 7, 0, 0]`, poi `[9, 7]`.
- **B.** `[9, 7]`, poi `[4, 7]`.
- **C.** Solleva un’eccezione perché la nuova lunghezza supera quella originale.
- **D.** `[9, 7, 0, 0]`, poi `[4, 7]`.

## 2. Risposta breve

Valuta `firstIndex` rispetto al contratto. Fornisci un input di tre elementi distinti che permetta di controllare sia l’indice restituito sia lo stato dell’array dopo la chiamata. Descrivi poi un algoritmo che rispetti anche il requisito sui duplicati, senza scrivere il codice.

**Lunghezza indicativa:** un input con il codice cercato e 2–3 frasi.

---

# Esercizio 4 — Riconoscimento di un comando

**Tempo indicativo: 5–7 minuti · Java 25**

`isStart` deve riconoscere `START` ignorando maiuscole/minuscole e spazi iniziali e finali. Gli input sono non nulli e contengono solo lettere ASCII e spazi. Il testo ricevuto deve rimanere invariato.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato, con `java.util.Locale` importata. I numeri di riga non fanno parte del codice.

```java
01  class Command {
02      static boolean isStart(String text) {
03          String clean = text.strip().toUpperCase(Locale.ROOT);
04          return clean == "START";
05      }
06  }
```

```java
07  String original = " start ";
08  String saved = original;
09  original = original.strip();
10  original.toUpperCase(Locale.ROOT);
11  System.out.println("[" + original + "]");
12  System.out.println("[" + saved + "]");
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le due righe nell’ordine.

- **A.** `[START]`, poi `[START]`.
- **B.** `[start]`, poi `[ start ]`.
- **C.** `[START]`, poi `[ start ]`.
- **D.** `[ start ]`, poi `[ start ]`.

## 2. Risposta breve

Valuta se `isStart` rispetta il contratto per tutti gli input ammessi. Se non lo rispetta, fornisci un input che lo dimostri e una modifica minima al metodo; altrimenti giustificane la correttezza. Non basarti su ipotesi sul riuso degli oggetti da parte dei metodi di trasformazione.

**Lunghezza indicativa:** un input, una riga di codice e 1–2 frasi.

---

# Esercizio 5 — Valore di un campo

**Tempo indicativo: 5–7 minuti · Java 25**

Una riga contiene una chiave, il separatore `:` e un valore. `value` deve restituire tutto ciò che segue il primo separatore, anche una stringa vuota. Se il separatore manca deve sollevare `IllegalArgumentException`. La riga è non nulla e contiene solo caratteri ASCII. `indexOf` restituisce `-1` se il carattere cercato manca.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  class Field {
02      static String value(String line) {
03          int separator = line.indexOf(':');
04          return line.substring(separator + 1);
05      }
06  }
```

```java
07  String line = "role:admin:local";
08  System.out.println(Field.value(line));
09  System.out.println(Field.value("role:").length());
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le due righe nell’ordine.

- **A.** `admin`, poi `0`.
- **B.** `local`, poi `1`.
- **C.** `admin:local`, poi `0`.
- **D.** `admin:local`, poi un’eccezione alla riga 9.

## 2. Risposta breve

Valuta separatamente la chiamata `Field.value("admin")` rispetto al contratto. Se necessario, modifica il metodo conservando il comportamento richiesto per i valori vuoti e per i valori che contengono altri `:`.

**Lunghezza indicativa:** una frase e massimo 4 righe per il corpo del metodo.

---

# Esercizio 6 — Registro dei nomi

**Tempo indicativo: 5–7 minuti · Java 25**

Il registro costruisce testo progressivamente. Il metodo `readName` deve invece leggere un’intera riga come nome, includendo eventuali spazi interni; la riga vuota è ammessa. Lo scanner è fornito dal chiamante e non deve essere chiuso dal metodo. Si assume che la prossima riga esista e che lo scanner sia posizionato al suo inizio.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato, con `java.util.Scanner` importata. I numeri di riga non fanno parte del codice.

```java
01  class Names {
02      static String readName(Scanner input) {
03          return input.next();
04      }
05  }
```

```java
06  StringBuilder register = new StringBuilder();
07  register.append("Ada");
08  String saved = register.toString();
09  register.append(" Linus");
10  System.out.println(saved);
11  System.out.println(register.length());
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le due righe nell’ordine.

- **A.** `Ada`, poi `9`.
- **B.** `Ada Linus`, poi `9`.
- **C.** `Ada`, poi `3`.
- **D.** `AdaLinus`, poi `8`.

## 2. Risposta breve

Valuta `readName` con uno scanner costruito dalla stringa `"Ada Lovelace\n"`. Indica ciò che viene letto rispetto al requisito e proponi, se necessaria, una modifica minima. La soluzione deve funzionare anche quando la prossima riga è vuota.

**Lunghezza indicativa:** una riga di codice e 1–2 frasi.

---

# Esercizio 7 — Numero di tentativi

**Tempo indicativo: 5–7 minuti · Java 25**

`effective` riceve il numero di tentativi: `null` significa «non specificato» e deve produrre il valore predefinito `3`. I valori non nulli sono tra 0 e 10 e devono essere restituiti senza modifiche.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  class Attempts {
02      static int effective(Integer supplied) {
03          int value = supplied;
04          if (supplied == null) {
05              return 3;
06          }
07          return value;
08      }
09  }
```

```java
10  Integer stored = Integer.valueOf(2000);
11  int requested = 2000;
12  System.out.println(stored == requested);
```

## 1. Scelta multipla

Che cosa accade eseguendo le righe 10–12? Seleziona una sola risposta.

- **A.** Stampa `false`, perché uno degli operandi è un oggetto.
- **B.** Stampa `true` soltanto se il wrapper proviene dalla cache.
- **C.** Non compila perché i tipi dei due operandi sono diversi.
- **D.** Stampa sempre `true`.

## 2. Risposta breve

Esamina separatamente `Attempts.effective(null)` e `Attempts.effective(0)`. Per ciascuna chiamata, indica se il contratto è rispettato. Se necessario, correggi il metodo preservando la distinzione tra assenza e zero.

**Lunghezza indicativa:** 1–2 frasi e massimo 4 righe per il corpo del metodo.

---

# Esercizio 8 — Conversione di un’età

**Tempo indicativo: 5–7 minuti · Java 25**

`parseAge` deve accettare età intere tra 0 e 130, inclusi, ignorando spazi iniziali e finali. Gli altri input devono produrre `IllegalArgumentException` o una sua sottoclasse. Il testo è non nullo. `NumberFormatException`, sollevata dal parsing di testi non numerici o fuori dall’intervallo di `int`, è una sottoclasse di `IllegalArgumentException`.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  class Ages {
02      static int parseAge(String text) {
03          return Integer.parseInt(text.strip());
04      }
05  }
```

```java
06  Integer entered = Integer.valueOf(7);
07  Long archived = Long.valueOf(7);
08  System.out.println(entered.equals(archived));
09  System.out.println(entered.intValue() == archived.longValue());
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le due righe nell’ordine.

- **A.** `true`, poi `true`.
- **B.** `false`, poi `false`.
- **C.** Non compila perché `equals` non accetta un `Long`.
- **D.** `false`, poi `true`.

## 2. Risposta breve

Valuta `parseAge` per gli input `" 42 "` e `"131"`. Se necessario, riscrivine il corpo perché rispetti l’intero contratto, senza trasformare un dato non valido in un valore predefinito.

**Lunghezza indicativa:** una frase e massimo 5 righe di codice.

---

# Esercizio 9 — Aggiornamento delle scorte

**Tempo indicativo: 5–7 minuti · Java 25**

Le scorte sono piccoli interi non negativi. Un’operazione di incremento deve permettere al chiamante di ottenere il valore aumentato di uno. Non considerare overflow o valori nulli.

Il metodo va dichiarato nel corpo di una classe e le istruzioni in un suo metodo statico, con `java.util.List` e `java.util.ArrayList` importate. Per `List<Integer>`, `remove(int index)` rimuove per posizione, mentre `remove(Object value)` rimuove la prima occorrenza uguale al valore. I numeri di riga non fanno parte del codice.

```java
01  static void increment(Integer value) {
02      value++;
03  }
```

```java
04  List<Integer> stock = new ArrayList<>(List.of(10, 20, 30));
05  stock.remove(1);
06  System.out.println(stock);
```

## 1. Scelta multipla

Qual è l’output delle righe 4–6? Seleziona una sola risposta.

- **A.** `[10, 20, 30]`.
- **B.** `[10, 30]`.
- **C.** `[20, 30]`.
- **D.** Solleva un’eccezione perché `1` non compare nella lista.

## 2. Risposta breve

Considera separatamente `Integer quantity = 3; increment(quantity);`. Il chiamante osserva l’incremento nella variabile `quantity`? Motiva e proponi una modifica al metodo e alla chiamata per ottenere l’aggiornamento, senza introdurre un contenitore mutabile.

**Lunghezza indicativa:** 1–2 frasi e massimo 4 righe di codice complessive.
