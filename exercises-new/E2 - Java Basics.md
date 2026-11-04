# Esercizio 1 — Aggiornamento delle misure

**Tempo indicativo: 5–7 minuti · Java 25**

Un’applicazione raccoglie misure numeriche in un array condiviso da più parti del programma. Una schermata conserva un riferimento all’array per mostrarne il contenuto aggiornato. `increase` deve aumentare di uno tutti gli elementi, mentre `reset` deve azzerarli senza cambiare l’oggetto array osservato dalla schermata. Prima di un incremento, le misure sono interi tra 0 e 99; l’array è non nullo, anche eventualmente vuoto. Il programma è monothread.

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

<details>
<summary>Mostra la soluzione dell’esercizio 1</summary>

### 1. Scelta multipla: C

`original` e `alias` indicano lo stesso array. Il metodo modifica i suoi elementi, quindi si stampano `[3, 6]` e `true`.

- **A errata:** gli elementi cambiano e i riferimenti restano uguali.
- **B errata:** l’incremento non crea un array diverso.
- **D errata:** gli elementi non restano invariati.

### 2. Risposta breve

Il contratto non è rispettato: viene riassegnata solo la copia locale del riferimento e `data` conserva `[4, 7]`. Occorre modificare gli elementi dell’array ricevuto.

```java
Arrays.fill(values, 0);
```

**Alternativa valida:** un ciclo che assegna zero a ogni elemento. Entrambe le soluzioni funzionano anche con un array vuoto.

</details>

---

# Esercizio 2 — Righe di una tabella

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio registra le quantità raccolte da più postazioni in una tabella. Ogni riga rappresenta una postazione, che può aver prodotto un numero diverso di misure. Il metodo `sum` deve sommare tutti gli elementi senza modificare la tabella, restituendo zero se non ci sono valori. Un’altra operazione crea una copia con `clone()` e ne modifica alcune parti per preparare una simulazione. La tabella e le righe sono non nulle, anche vuote; i valori sono piccoli interi e non si considera overflow.

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

<details>
<summary>Mostra la soluzione dell’esercizio 2</summary>

### 1. Scelta multipla: B

`clone()` copia l’array esterno, ma condivide inizialmente le righe. La modifica di `copy[0][0]` raggiunge la prima riga originale; riassegnare `copy[1]` sostituisce invece soltanto una posizione nell’array esterno copiato. Si stampano `9` e `3`.

- **A errata:** la prima riga non è stata copiata in profondità.
- **C errata:** la sostituzione di `copy[1]` non sostituisce `original[1]`.
- **D errata:** entrambe le previsioni ignorano la distinzione fra modifica di una riga e sostituzione del suo riferimento.

### 2. Risposta breve

Il risultato richiesto è `6`, ma il metodo restituisce `3`: usa sempre la lunghezza della prima riga e non visita il secondo elemento della seconda. La riga 5 deve usare la lunghezza della riga corrente:

```java
for (int c = 0; c < values[r].length; c++) {
```

Il ciclo esterno non entra per una tabella vuota; quello interno non entra per una riga vuota.

</details>

---

# Esercizio 3 — Ricerca di un codice

**Tempo indicativo: 5–7 minuti · Java 25**

Un archivio conserva codici nell’ordine in cui sono stati acquisiti. Il metodo `firstIndex` deve individuare la prima posizione di un codice in quell’ordine, oppure restituire `-1` se manca. Il chiamante continua a usare l’array dopo la ricerca, quindi il suo contenuto non deve cambiare. Un frammento separato prepara un array più lungo per acquisizioni future. Gli array sono non nulli, anche vuoti; i codici possono essere duplicati e non ordinati. Per un array ordinato, `Arrays.binarySearch` restituisce un indice corrispondente, non necessariamente il primo tra duplicati, oppure un valore negativo se l’elemento manca.

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

<details>
<summary>Mostra la soluzione dell’esercizio 3</summary>

### 1. Scelta multipla: D

`Arrays.copyOf` crea un array indipendente e riempie le posizioni aggiuntive di un `int[]` con zero. La modifica riguarda solo `expanded`: si stampano `[9, 7, 0, 0]` e `[4, 7]`.

- **A errata:** il nuovo array non condivide le proprie posizioni con quello originale.
- **B errata:** la lunghezza richiesta è quattro.
- **C errata:** una lunghezza maggiore è ammessa.

### 2. Risposta breve

Con `values = {9, 2, 5}` e codice `9`, l’indice richiesto è `0`, ma il metodo restituisce `2` e lascia l’array ordinato come `{2, 5, 9}`. Un algoritmo conforme visita l’array da sinistra a destra e restituisce subito l’indice del primo elemento uguale al codice. Se la visita termina senza trovarlo, restituisce `-1`: non modifica l’input e gestisce anche duplicati e array vuoti.

**Alternativa valida:** altre strategie che preservino l’array e restituiscano il minimo indice originale; non serve introdurre ordinamento o strutture aggiuntive.

</details>

---

# Esercizio 4 — Riconoscimento di un comando

**Tempo indicativo: 5–7 minuti · Java 25**

Una console riceve comandi testuali da un operatore. `isStart` deve riconoscere `START` ignorando maiuscole/minuscole e spazi iniziali e finali. Il testo ricevuto viene conservato anche in un registro e deve rimanere invariato. La console può creare nuove stringhe durante la preparazione dei comandi; il risultato non deve dipendere dall’identità degli oggetti. Gli input sono non nulli e contengono solo lettere ASCII e spazi.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato, con `java.util.Locale` importata. I numeri di riga non fanno parte del codice.

```java
01  class Command {
02      static boolean isStart(String text) {
03          String clean = new String(text.strip().toUpperCase(Locale.ROOT));
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

<details>
<summary>Mostra la soluzione dell’esercizio 4</summary>

### 1. Scelta multipla: B

L’assegnazione conserva in `original` il risultato di `strip()`. Il risultato di `toUpperCase` viene invece scartato; `saved` mantiene il riferimento al testo iniziale. Si stampano `[start]` e `[ start ]`.

- **A errata:** una trasformazione non modifica in-place le stringhe condivise.
- **C errata:** il risultato della conversione in maiuscolo non viene assegnato.
- **D errata:** il risultato di `strip()` viene assegnato a `original`.

### 2. Risposta breve

Per esempio, `isStart(" start ")` restituisce `false`, mentre il contratto richiede `true`. `new String(...)` crea esplicitamente un oggetto distinto dal letterale: `==` confronta i riferimenti, non il contenuto. Sostituire la riga 4 con:

```java
return clean.equals("START");
```

**Alternativa valida:** `return text.strip().equalsIgnoreCase("START");`, eliminando anche la variabile locale non più necessaria. Nessuna delle due soluzioni modifica il testo ricevuto.

</details>

---

# Esercizio 5 — Valore di un campo

**Tempo indicativo: 5–7 minuti · Java 25**

Un lettore di configurazioni riceve una riga alla volta nel formato `chiave:valore`. Il metodo `value` deve estrarre tutto ciò che segue il primo separatore, anche quando il valore è vuoto o contiene altri `:`. Il chiamante usa il risultato come testo e non richiede ulteriori controlli sul contenuto della chiave. Una riga priva di separatore non è valida e deve produrre `IllegalArgumentException`, non essere interpretata come un valore. La riga è non nulla e contiene solo caratteri ASCII; `indexOf` restituisce `-1` se il carattere cercato manca.

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

<details>
<summary>Mostra la soluzione dell’esercizio 5</summary>

### 1. Scelta multipla: C

Il primo `:` si trova all’indice 4; `substring(5)` conserva tutto il suffisso `admin:local`. Per `"role:"`, la sottostringa inizia alla fine del testo ed è vuota, quindi ha lunghezza `0`.

- **A errata:** `substring` non interrompe il risultato al secondo separatore.
- **B errata:** viene cercato il primo separatore, non l’ultimo; la stringa vuota non ha lunghezza uno.
- **D errata:** iniziare la sottostringa alla lunghezza della stringa è ammesso.

### 2. Risposta breve

Con `"admin"`, `separator` vale `-1` e il metodo restituisce l’intera riga invece di segnalare un errore. Un corpo conforme è:

```java
int separator = line.indexOf(':');
if (separator < 0) throw new IllegalArgumentException("Separatore mancante");
return line.substring(separator + 1);
```

Il controllo non rifiuta né il suffisso vuoto né eventuali separatori successivi. Sono accettabili anche le parentesi graffe attorno al corpo dell’`if`.

</details>

---

# Esercizio 6 — Registro dei nomi

**Tempo indicativo: 5–7 minuti · Java 25**

Un’applicazione mantiene un registro testuale dei partecipanti a un evento. Il registro cresce tramite `StringBuilder`, mentre una stringa salvata rappresenta una fotografia del testo in un determinato momento. Il metodo `readName` acquisisce un nuovo nome leggendo un’intera riga, inclusi eventuali spazi interni. La riga vuota è ammessa e deve essere restituita senza saltarla. Lo scanner è fornito dal chiamante e non deve essere chiuso dal metodo; si assume che la prossima riga esista e che lo scanner sia posizionato al suo inizio.

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

<details>
<summary>Mostra la soluzione dell’esercizio 6</summary>

### 1. Scelta multipla: A

`toString()` produce una stringa che non cambia quando viene modificato il builder. `saved` contiene `Ada`; il builder finale contiene `Ada Linus`, lungo nove caratteri, incluso lo spazio.

- **B errata:** `saved` non è una vista modificabile del builder.
- **C errata:** il secondo `append` modifica il builder e ne aumenta la lunghezza.
- **D errata:** la stringa aggiunta contiene uno spazio e `saved` non contiene il testo finale.

### 2. Risposta breve

`next()` legge solo `Ada`, non l’intera riga. Il corpo richiesto è:

```java
return input.nextLine();
```

`nextLine()` legge anche gli spazi interni e restituisce `""` per una riga vuota. Non si deve chiudere lo scanner né usare `nextLine()` una seconda volta per saltare righe: il contratto lo colloca già all’inizio della riga da leggere.

</details>

---

# Esercizio 7 — Numero di tentativi

**Tempo indicativo: 5–7 minuti · Java 25**

Un client permette di configurare quanti tentativi effettuare per un’operazione. Il valore `null` significa «non specificato» e, nella versione attuale, deve produrre il valore predefinito `3`. Un valore esplicito, incluso zero, deve invece essere rispettato. Il metodo `effective` restituisce un `int` da usare nel resto dell’applicazione, senza modificare la configurazione ricevuta. Gli eventuali valori non nulli sono tra 0 e 10; il frammento sulle variabili `stored` e `requested` è una prova separata dei confronti numerici e non rappresenta una configurazione valida del client.

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

Esamina separatamente `Attempts.effective(null)` e `Attempts.effective(0)`: indica il comportamento effettivo e se rispetta il contratto. Ora il valore predefinito deve essere scelto da ciascun chiamante, senza stato globale. Proponi la nuova firma e un corpo che conservi la distinzione tra assenza e zero; assumi che anche il predefinito ricevuto sia tra 0 e 10.

**Lunghezza indicativa:** 1–2 frasi e massimo 4 righe di codice, inclusa la firma.

<details>
<summary>Mostra la soluzione dell’esercizio 7</summary>

### 1. Scelta multipla: D

Nel confronto con un `int`, `stored` viene convertito nel valore primitivo tramite unboxing. I valori sono entrambi `2000`, quindi si stampa `true`, indipendentemente dalla cache dei wrapper.

- **A errata:** questo confronto non avviene fra due riferimenti.
- **B errata:** la cache non determina il risultato di un confronto fra valori primitivi.
- **C errata:** il confronto è consentito grazie all’unboxing.

### 2. Risposta breve

`effective(null)` solleva `NullPointerException` alla riga 3, prima del controllo, e viola il contratto; `effective(0)` restituisce correttamente zero. Il nuovo parametro consente di scegliere il predefinito per chiamata, controllando l’assenza prima dell’unboxing:

```java
static int effective(Integer supplied, int defaultValue) {
    return supplied == null ? defaultValue : supplied;
}
```

Per esempio, `effective(null, 5)` restituisce `5`, mentre `effective(0, 5)` restituisce `0`. È equivalente una versione con `if` esplicito; non serve uno stato statico modificabile.

</details>

---

# Esercizio 8 — Conversione di un’età

**Tempo indicativo: 5–7 minuti · Java 25**

Un modulo di iscrizione riceve l’età come testo e deve convertirla prima di salvarla. Il metodo `parseAge` accetta età intere tra 0 e 130, inclusi, ignorando spazi iniziali e finali. Un input non valido deve produrre `IllegalArgumentException` o una sua sottoclasse, così che il chiamante possa richiedere una correzione senza salvare valori predefiniti. Un frammento separato confronta due rappresentazioni numeriche già presenti nel sistema. Il testo ricevuto da `parseAge` è non nullo; `NumberFormatException`, sollevata dal parsing di testi non numerici o fuori dall’intervallo di `int`, è una sottoclasse di `IllegalArgumentException`.

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

<details>
<summary>Mostra la soluzione dell’esercizio 8</summary>

### 1. Scelta multipla: D

`Integer.equals` non considera uguale un oggetto `Long`, anche se rappresenta lo stesso numero. Il secondo confronto usa valori primitivi, promuovendo l’`int` a `long`, e restituisce `true`.

- **A errata:** `equals` tiene conto del tipo del wrapper.
- **B errata:** il confronto numerico tra i valori primitivi restituisce `true`.
- **C errata:** `equals` accetta un argomento di tipo `Object`, quindi la chiamata compila.

### 2. Risposta breve

`" 42 "` produce correttamente `42`; `"131"` produce invece un valore fuori dal dominio ammesso. Un corpo conforme è:

```java
int age = Integer.parseInt(text.strip());
if (age < 0 || age > 130) {
    throw new IllegalArgumentException("Età fuori intervallo");
}
return age;
```

Gli input non numerici e quelli fuori dall’intervallo di `int` continuano a produrre `NumberFormatException`, già ammessa dal contratto. Non occorre intercettarla né trasformarla in un valore predefinito.

</details>

---

# Esercizio 9 — Aggiornamento delle scorte

**Tempo indicativo: 5–7 minuti · Java 25**

Un magazzino rappresenta le quantità disponibili tramite numeri interi. Un’operazione di incremento deve permettere al chiamante di ottenere la quantità aumentata di uno. La quantità può essere conservata in una variabile `Integer`, ma non si vuole introdurre un oggetto mutabile per aggiornarla. Un frammento separato usa una lista per rimuovere una quantità da una sequenza di scorte. Tutte le quantità sono piccoli interi non negativi e non nulli; non si considera overflow.

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

<details>
<summary>Mostra la soluzione dell’esercizio 9</summary>

### 1. Scelta multipla: B

L’argomento `1` ha tipo `int`, quindi viene scelto `remove(int index)`: si elimina l’elemento all’indice 1, cioè `20`. La lista stampata è `[10, 30]`.

- **A errata:** l’operazione rimuove un elemento.
- **C errata:** gli indici partono da zero; l’indice 1 non indica il primo elemento.
- **D errata:** la ricerca del valore `1` richiederebbe l’overload con argomento oggetto.

### 2. Risposta breve

Il chiamante conserva `3`: `Integer` è immutabile e `value++` riassegna solo il parametro locale dopo unboxing, incremento e boxing. Il metodo può restituire il nuovo valore, che il chiamante deve assegnare:

```java
static int increment(int value) { return value + 1; }
Integer quantity = 3;
quantity = increment(quantity);
```

**Alternativa valida:** mantenere un parametro `Integer` e restituire un `Integer`, rispettando l’assunzione di non nullità. Il punto essenziale è il contratto di restituzione e la riassegnazione nel chiamante, non il tipo wrapper o primitivo della firma.

</details>

---

# Esercizio 10 — Media dei consumi

**Tempo indicativo: 5–7 minuti · Java 25**

Un’applicazione riceve i consumi giornalieri di un dispositivo. Un valore negativo indica una lettura mancante e deve essere ignorato. Il componente deve calcolare la media delle sole letture disponibili, senza modificare l’array. La media può avere una parte frazionaria. L’array è non nullo, anche vuoto, e contiene al massimo cento interi tra -1 e 100; non si considera overflow.

Il frammento è eseguito in un metodo. I numeri di riga non fanno parte del codice.

```java
01  int[] readings = {2, -1, 3};
02  int total = 0;
03  int count = 0;
04  for (int value : readings) {
05      if (value < 0) continue;
06      total += value;
07      count++;
08  }
09  double average = total / count;
10  System.out.println(average);
```

## 1. Scelta multipla

Qual è l’output? Seleziona una sola risposta.

- **A.** `2.5`.
- **B.** `1.0`.
- **C.** Solleva `ArithmeticException`.
- **D.** `2.0`.

## 2. Risposta aperta

Proponi firma e contratto di un metodo che riceva l’array e restituisca la media frazionaria, segnalando con `IllegalArgumentException` l’assenza di letture valide. Indica quali parti del ciclo riutilizzare e come terminare il metodo. Non riscrivere il ciclo.

**Lunghezza indicativa:** una firma, massimo 3 righe finali e una frase.

<details>
<summary>Mostra la soluzione dell’esercizio 10</summary>

### 1. Scelta multipla: D

La somma è `5` e il conteggio è `2`. La divisione tra interi produce `2`, poi convertito in `double`.

- **A errata:** l’assegnazione a `double` non cambia il tipo della divisione già eseguita.
- **B errata:** la lettura negativa non partecipa né alla somma né al conteggio.
- **C errata:** il denominatore è `2`, non zero.

### 2. Risposta aperta

La firma può essere `static double average(int[] readings)`. Riutilizzo il ciclo con somma e conteggio locali; dopo il ciclo eseguo:

```java
if (count == 0) throw new IllegalArgumentException("Nessuna lettura");
return (double) total / count;
```

Il controllo copre array vuoti e array di sole letture mancanti; il cast rende frazionaria la divisione. È equivalente accumulare la somma in un `double`.

</details>
