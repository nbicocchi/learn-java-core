# Esercizio 1 — Importazione di quantità

**Tempo indicativo: 5–7 minuti · Java 25**

Un magazzino importa quantità da un file già letto in memoria. Ogni riga rappresenta un intero decimale non negativo da aggiungere al totale. Una riga non numerica deve produrre una segnalazione ed essere ignorata. L’errore su una riga non deve impedire l’elaborazione delle successive. Le righe sono non nulle; non considerare numeri negativi o overflow.

Il frammento è eseguito in un metodo. I numeri di riga non fanno parte del codice.

```java
01  String[] rows = {"2", "3"};
02  int total = 0;
03  try {
04      for (String row : rows) {
05          total += Integer.parseInt(row);
06      }
07  } catch (NumberFormatException exception) {
08      IO.println("Invalid row");
09  }
10  IO.println(total);
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

<details>
<summary>Mostra la soluzione dell’esercizio 1</summary>

### 1. Scelta multipla: C

Le due conversioni riescono e la somma stampata è `5`.

- **A errata:** si sommano interi, non si concatenano stringhe.
- **B errata:** nessuna riga provoca un errore.
- **D errata:** entrambe le quantità vengono sommate.

### 2. Risposta breve

Con l’input modificato si stampa `Invalid row`, poi `2`: il `catch` esterno termina l’elaborazione al primo errore. Per continuare sulle righe successive:

```java
for (String row : rows) {
    try {
        total += Integer.parseInt(row);
    } catch (NumberFormatException exception) {
        IO.println("Invalid row");
    }
}
```

Il totale finale diventa `5`.

</details>

---

# Esercizio 2 — Scrittura di un resoconto

**Tempo indicativo: 5–7 minuti · Java 25**

Un componente prepara un resoconto usando due risorse chiudibili. Deve rilasciare entrambe anche se il lavoro fallisce. Per osservare il rilascio senza dipendere da file reali, `Trace` stampa il proprio nome quando viene chiusa. Il chiamante gestisce l’errore del lavoro dopo il rilascio delle risorse. Costruzione e chiusura delle due risorse non sollevano eccezioni.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  class Trace implements AutoCloseable {
02      private final String name;
03      Trace(String name) { this.name = name; }
04      public void close() { IO.println("Close " + name); }
05  }
```

```java
06  try (Trace first = new Trace("A"); Trace second = new Trace("B")) {
07      IO.println("Work");
08      throw new IllegalArgumentException();
09  } catch (IllegalArgumentException exception) {
10      IO.println("Handled");
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

<details>
<summary>Mostra la soluzione dell’esercizio 2</summary>

### 1. Scelta multipla: D

Il corpo stampa `Work`, poi le risorse vengono chiuse in ordine inverso di dichiarazione: `Close B`, `Close A`. Infine il `catch` stampa `Handled`.

- **A errata:** il rilascio precede l’ingresso nel `catch` esterno.
- **B errata:** l’ordine di chiusura è invertito rispetto alla costruzione.
- **C errata:** l’eccezione non salta il rilascio automatico.

### 2. Risposta breve

Il try-with-resources chiude anche una risorsa ricevuta come parametro e viola il contratto di prestito. Il corpo deve essere:

```java
output.write("Report");
```

La firma con `throws IOException` propaga il fallimento; la chiusura resta responsabilità del chiamante.

</details>

---

# Esercizio 3 — Quantità richiesta

**Tempo indicativo: 5–7 minuti · Java 25**

Un componente riceve quantità sotto forma di testo. Il risultato numerico viene usato per pianificare un’elaborazione, nella quale anche zero è un valore significativo. La versione corrente intercetta gli errori di conversione e registra il termine dell’operazione con una stampa. Il metodo attuale è mostrato integralmente. Il testo è non nullo e non sono richiesti vincoli di intervallo ulteriori rispetto al tipo `int`.

Il metodo va dichiarato nel corpo di una classe e la chiamata in un suo metodo statico. I numeri di riga non fanno parte del codice.

```java
01  static int count(String text) {
02      try {
03          return Integer.parseInt(text);
04      } catch (NumberFormatException exception) {
05          return 0;
06      } finally {
07          IO.println("Done");
08      }
09  }
```

```java
10  IO.println(count("abc"));
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

<details>
<summary>Mostra la soluzione dell’esercizio 3</summary>

### 1. Scelta multipla: B

Il parsing fallisce e il `catch` prepara il risultato zero; prima del ritorno viene eseguito il `finally`, che stampa `Done`.

- **A errata:** il `finally` precede il ritorno al chiamante.
- **C errata:** `NumberFormatException` viene intercettata.
- **D errata:** il `finally` viene eseguito anche dopo un `return` nel `catch`.

### 2. Risposta aperta

```java
class InvalidCountException extends IllegalArgumentException {
    InvalidCountException(String message, Throwable cause) { super(message, cause); }
}
// Nel catch di count:
throw new InvalidCountException("Quantità non valida: " + text, exception);
```

Scelgo unchecked per un argomento non valido: `count` non deve aggiungere `throws` obbligatori. Elimino il `finally` che stampa; il chiamante distingue lo zero valido dall’eccezione e può consultarne la causa.

**Alternativa valida:** eccezione checked derivata da `Exception`, con dichiarazione `throws InvalidCountException`, se si vuole imporre ai chiamanti una gestione o propagazione esplicita.

</details>

---

# Esercizio 4 — Lettura di un valore remoto

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio simula il recupero di un valore da una sorgente esterna. Il contratto dichiara che la lettura può fallire con `IOException`. Il client vuole stampare il valore quando disponibile oppure segnalare il fallimento. Per questo esercizio la sorgente fallisce sempre, senza effetti esterni. Tutti i metodi sono mostrati e non esistono gestori esterni delle eccezioni.

I metodi sono nella stessa classe, con `java.io.IOException` importata. Il secondo è il metodo chiamato dal programma. I numeri di riga non fanno parte del codice.

```java
01  static int read() throws IOException {
02      throw new IOException("Offline");
03  }
04  static void printValue() {
05      IO.println(read());
06  }
```

## 1. Scelta multipla

La classe compila? Seleziona una sola risposta.

- **A.** Sì, e stampa `0`.
- **B.** Sì, perché `println` gestisce `IOException`.
- **C.** No: `printValue` non gestisce né dichiara l’eccezione checked.
- **D.** No: un metodo con risultato `int` non può terminare con `throw`.

## 2. Risposta breve

Il requisito di `printValue` è stampare `Unavailable` in caso di `IOException`, senza propagarla. Indica la modifica necessaria e spiega se sostituire l’eccezione con `RuntimeException` risolverebbe da solo tale requisito.

**Lunghezza indicativa:** massimo 5 righe di codice e una frase.

<details>
<summary>Mostra la soluzione dell’esercizio 4</summary>

### 1. Scelta multipla: C

`IOException` è checked e il chiamante deve gestirla o dichiararla.

- **A errata:** non esiste un ritorno automatico di zero.
- **B errata:** il calcolo dell’argomento avviene prima della stampa.
- **D errata:** un percorso che termina con `throw` non deve restituire un valore.

### 2. Risposta breve

```java
try {
    IO.println(read());
} catch (IOException exception) {
    IO.println("Unavailable");
}
```

Rendere l’errore unchecked elimina un obbligo del compilatore, ma non produce da solo la segnalazione richiesta.

</details>

---

# Esercizio 5 — Segnalazioni di importazione

**Tempo indicativo: 5–7 minuti · Java 25**

Un importatore usa un handler per classificare gli input non validi. Il parsing può sollevare `NumberFormatException`, che estende `IllegalArgumentException`. Il chiamante vuole distinguere il testo non numerico dagli altri argomenti non validi. Le righe sono non nulle e non servono risorse esterne. Il frammento contiene tutti i gestori rilevanti.

Frammento in un metodo. I numeri di riga non fanno parte del codice.

```java
01  try {
02      IO.println(Integer.parseInt("x"));
03  } catch (IllegalArgumentException exception) {
04      IO.println("Argument");
05  } catch (NumberFormatException exception) {
06      IO.println("Number");
07  }
```

## 1. Scelta multipla

Che cosa accade? Seleziona una sola risposta.

- **A.** Non compila: il secondo `catch` è già coperto dal primo.
- **B.** Stampa `Argument`, poi `Number`.
- **C.** Stampa solo `Number`.
- **D.** Stampa solo `Argument`.

## 2. Risposta breve

Proponi una modifica minima che conservi entrambi i gestori e dia priorità al messaggio specifico. Indica l’output dopo la modifica e quanti `catch` vengono eseguiti per questa singola eccezione.

**Lunghezza indicativa:** 2–3 frasi; non riscrivere il frammento.

<details>
<summary>Mostra la soluzione dell’esercizio 5</summary>

### 1. Scelta multipla: A

Il gestore di `IllegalArgumentException` include già la sua sottoclasse, rendendo irraggiungibile il gestore successivo.

- **B errata:** per una singola eccezione non si attraversano entrambi i gestori.
- **C errata:** il codice originale non compila.
- **D errata:** non è solo una questione di scelta a runtime: l’ordine è rifiutato dal compilatore.

### 2. Risposta breve

Scambio i due `catch`, mettendo prima `NumberFormatException`. L’input mostrato produce `Number`; viene eseguito soltanto il primo gestore compatibile. Il gestore più generale resta disponibile per altre `IllegalArgumentException`.

</details>

---

# Esercizio 6 — Esito di una verifica

**Tempo indicativo: 5–7 minuti · Java 25**

Un componente verifica un dato e restituisce un codice di esito. Il chiamante deve poter distinguere il risultato normale da un input non valido. Un blocco di pulizia viene eseguito prima del ritorno. Nel frammento la pulizia è simulata senza risorse esterne. Il parametro è un booleano e l’eccezione di validazione deve raggiungere il chiamante.

Metodo in una classe; la chiamata è in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  static int check(boolean valid) {
02      try {
03          if (!valid) throw new IllegalArgumentException("Invalid");
04          return 1;
05      } finally {
06          return 2;
07      }
08  }
09  // Chiamata in un altro metodo:
10  IO.println(check(false));
```

## 1. Scelta multipla

Che cosa accade? Seleziona una sola risposta.

- **A.** Non compila perché `finally` non può contenere `return`.
- **B.** Propaga `IllegalArgumentException`.
- **C.** Stampa `1`.
- **D.** Stampa `2`.

## 2. Risposta breve

La pulizia deve stampare solo `Cleanup`, senza alterare il risultato o nascondere l’errore. Indica il nuovo corpo di `finally` e i comportamenti attesi con `true` e `false`.

**Lunghezza indicativa:** una riga di codice e 2 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 6</summary>

### 1. Scelta multipla: D

Il `return` nel `finally` sostituisce l’uscita eccezionale del `try` con il valore `2`.

- **A errata:** è sintassi ammessa, anche se pericolosa.
- **B errata:** il `finally` maschera l’eccezione.
- **C errata:** il parametro è `false` e comunque il `finally` sostituirebbe anche quel risultato.

### 2. Risposta breve

```java
IO.println("Cleanup");
```

Con `true`, dopo `Cleanup` il metodo restituisce `1`. Con `false`, dopo la stessa stampa propaga `IllegalArgumentException`, senza un valore di ritorno normale.

</details>

---

# Esercizio 7 — Rilascio di una connessione

**Tempo indicativo: 5–7 minuti · Java 25**

Un componente acquisisce una risorsa e la usa per un’operazione. Tanto l’operazione quanto la chiusura possono fallire. Per riconoscere entrambi i problemi, il chiamante esamina l’eccezione principale e quelle soppresse. La risorsa qui è simulata e la costruzione riesce sempre. `getSuppressed()` restituisce le eccezioni di chiusura conservate dal try-with-resources quando il corpo è già fallito.

Classe e istruzioni in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  class Connection implements AutoCloseable {
02      public void close() { throw new IllegalStateException("Close"); }
03  }
04  // In un altro metodo:
05  try (Connection connection = new Connection()) {
06      throw new IllegalArgumentException("Work");
07  } catch (RuntimeException exception) {
08      IO.println(exception.getMessage());
09      IO.println(exception.getSuppressed().length);
10  }
```

## 1. Scelta multipla

Seleziona l’output corretto.

- **A.** `Close`, poi `0`.
- **B.** `Work`, poi `1`.
- **C.** `Work`, poi `0`.
- **D.** `Close`, poi `1`.

## 2. Risposta breve

Considera la versione in cui il corpo del `try` termina normalmente e solo `close()` fallisce. Quale eccezione raggiunge il `catch` e quante eccezioni soppresse contiene in questo esempio? Spiega la differenza rispetto al codice mostrato.

**Lunghezza indicativa:** 2–3 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 7</summary>

### 1. Scelta multipla: B

Il fallimento del corpo resta principale con messaggio `Work`; quello della chiusura è conservato come unica eccezione soppressa.

- **A errata:** la chiusura non sostituisce il fallimento già avvenuto.
- **C errata:** il fallimento di chiusura viene conservato.
- **D errata:** il principale non ha messaggio `Close`.

### 2. Risposta breve

Arriva `IllegalStateException("Close")`, senza eccezioni soppresse. In assenza di un fallimento del corpo, la prima eccezione di chiusura è essa stessa il fallimento principale.

</details>

---

# Esercizio 8 — Apertura di due risorse

**Tempo indicativo: 5–7 minuti · Java 25**

Un’elaborazione deve acquisire due risorse prima di iniziare il lavoro. L’acquisizione della seconda può fallire. La prima è già stata aperta e deve essere rilasciata anche in quel caso. La simulazione stampa le chiusure e segnala il fallimento dell’apertura, senza dipendere dal filesystem. Soltanto il costruttore relativo al nome `B` fallisce; `close()` termina normalmente.

Classe e istruzioni in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  class Resource implements AutoCloseable {
02      private final String name;
03      Resource(String name) {
04          this.name = name;
05          if (name.equals("B")) throw new IllegalStateException("Open B");
06      }
07      public void close() { IO.println("Close " + name); }
08  }
09  // In un altro metodo:
10  try (Resource first = new Resource("A"); Resource second = new Resource("B")) {
11      IO.println("Work");
12  } catch (IllegalStateException exception) {
13      IO.println(exception.getMessage());
14  }
```

## 1. Scelta multipla

Seleziona l’output corretto.

- **A.** `Work`, `Close B`, `Close A`, `Open B`.
- **B.** Soltanto `Open B`.
- **C.** `Close A`, poi `Open B`.
- **D.** `Close B`, `Close A`, `Open B`.

## 2. Risposta aperta

Un nuovo helper riceve una risorsa già aperta dal chiamante e ne apre una seconda di propria iniziativa. Definisci un contratto di proprietà che chiarisca chi chiude ciascuna risorsa, anche in caso di fallimento, e quale delle due va dichiarata nel try-with-resources dell’helper.

**Lunghezza indicativa:** 3 frasi; non serve codice.

<details>
<summary>Mostra la soluzione dell’esercizio 8</summary>

### 1. Scelta multipla: C

Fallisce l’acquisizione di `B`, quindi il corpo non parte e viene chiusa soltanto la risorsa `A`, già acquisita.

- **A errata:** il corpo non viene eseguito.
- **B errata:** la prima risorsa viene rilasciata.
- **D errata:** la risorsa la cui costruzione fallisce non viene registrata per la chiusura automatica.

### 2. Risposta aperta

La prima risorsa è in prestito: la chiude il chiamante, anche quando l’helper fallisce. La seconda è posseduta dall’helper, che deve garantirne il rilascio. Solo la seconda viene dichiarata nel try-with-resources dell’helper; eventuali eccezioni sono propagate o tradotte conservandone la causa.

</details>

---

# Esercizio 9 — Ricerca di una prenotazione

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio cerca una prenotazione mediante un codice. Una prenotazione assente è un esito normale, diverso dall’impossibilità di interrogare l’archivio. La versione mostrata usa un valore sostitutivo per la sola assenza. Il nuovo client deve poter distinguere i due esiti senza analizzare messaggi testuali. Codici e archivio sono non nulli e l’archivio in memoria non può fallire durante il frammento.

Frammento in un metodo con `java.util.*` importata. I numeri di riga non fanno parte del codice.

```java
01  Map<String, String> bookings = Map.of("A", "Ada");
02  String name = bookings.get("B");
03  if (name == null) {
04      IO.println("Missing");
05  } else {
06      IO.println(name);
07  }
```

## 1. Scelta multipla

Che cosa accade? Seleziona una sola risposta.

- **A.** Stampa `Missing`.
- **B.** Solleva `NullPointerException`.
- **C.** Stampa `Ada`.
- **D.** Non compila perché una mappa non può restituire `null`.

## 2. Risposta aperta

Proponi il contratto di `find` per un archivio che può fallire con `IOException`: rappresenta l’assenza senza eccezioni e lascia distinguibile il fallimento tecnico. Fornisci la firma e descrivi i tre esiti, senza implementare l’accesso all’archivio.

**Lunghezza indicativa:** una firma e 2–3 frasi. Puoi usare `Optional` specificando che rappresenta presenza o assenza.

<details>
<summary>Mostra la soluzione dell’esercizio 9</summary>

### 1. Scelta multipla: A

La chiave `B` manca e `get` restituisce `null`, che viene controllato prima della stampa.

- **B errata:** confrontare un riferimento con `null` non lo dereferenzia.
- **C errata:** il valore di `A` non viene usato come ripiego.
- **D errata:** anche una mappa che vieta valori nulli restituisce `null` per una chiave assente.

### 2. Risposta aperta

`Optional<String> find(String code) throws IOException`: valore presente per una prenotazione trovata, `Optional.empty()` per l’assenza, eccezione per il fallimento tecnico. Non si deve trasformare un’`IOException` in un risultato vuoto. È valido anche un contratto documentato con `null` per l’assenza e la stessa eccezione per l’errore, se la distinzione è esplicita.

</details>

---

# Esercizio 10 — Ripresa di un’operazione

**Tempo indicativo: 5–7 minuti · Java 25**

Un componente può fallire temporaneamente prima di completare un’operazione. Il chiamante consente al massimo due tentativi. La simulazione fallisce alla prima invocazione e riesce alla seconda. Il contatore parte da zero e il programma è monothread. Non sono richiesti attese, rete o concorrenza.

Metodi nella stessa classe; istruzioni finali in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  static int calls;
02  static int attempt() {
03      if (++calls == 1) throw new IllegalStateException("Retry");
04      return 7;
05  }
06  // In un altro metodo:
07  for (int i = 0; i < 2; i++) {
08      try {
09          IO.println(attempt());
10          break;
11      } catch (IllegalStateException exception) {
12          IO.println("Failed");
13      }
14  }
```

## 1. Scelta multipla

Seleziona l’output corretto.

- **A.** `Failed`, poi `Failed`.
- **B.** Soltanto `7`.
- **C.** `Failed`, poi `7`, poi `7`.
- **D.** `Failed`, poi `7`.

## 2. Risposta breve

Un revisore sposta il `try/catch` all’esterno dell’intero ciclo. Con lo stesso stato iniziale, il secondo tentativo viene ancora eseguito dopo il primo fallimento? Spiega il percorso di controllo e indica quale collocazione soddisfa la politica di riprova.

**Lunghezza indicativa:** 2–3 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 10</summary>

### 1. Scelta multipla: D

La prima chiamata fallisce e stampa `Failed`; la seconda restituisce `7` e il `break` termina il ciclo.

- **A errata:** solo la prima chiamata fallisce.
- **B errata:** il primo fallimento viene stampato.
- **C errata:** il limite e il `break` impediscono ulteriori stampe.

### 2. Risposta breve

Con un unico `try` esterno, l’eccezione abbandona tutto il ciclo e il secondo tentativo non parte. Il `catch` interno all’iterazione consente invece di proseguire alla successiva. La riprova richiede questa gestione per tentativo, non il rientro automatico nel punto che ha fallito.

</details>

---

# Esercizio 11 — Confronto delle date

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio verifica che la data iniziale di una prenotazione sia strettamente precedente a quella finale. Le date arrivano come stringhe nel formato `yyyy-MM-dd`. Il contratto di `before` richiede di propagare al chiamante gli errori di interpretazione, per distinguerli da un intervallo non valido. Un altro componente dell'applicazione usa invece una politica diversa, restituendo `false` anche per date malformate: i due contratti non vanno confusi.

Le stringhe sono non nulle. `LocalDate.parse` interpreta una data e lancia `DateTimeParseException` se non è valida; `isBefore` è falso anche quando le date sono uguali. Il frammento appartiene a una classe di test. `@Test` identifica un test; `assertTrue` richiede una condizione vera e `assertThrows(T.class, azione)` richiede che l'azione lanci un'eccezione di tipo `T` o di un suo sottotipo, altrimenti fallisce. Import omessi; i numeri di riga non fanno parte del codice.

```java
01  static boolean before(String begin, String end) {
02      try {
03          return LocalDate.parse(begin).isBefore(LocalDate.parse(end));
04      } catch (DateTimeParseException e) {
05          return false;
06      }
07  }
08
09  @Test
10  void validInterval() {
11      assertTrue(before("2026-05-01", "2026-05-03"));
12  }
13
14  @Test
15  void malformedDate() {
16      assertThrows(DateTimeParseException.class,
17              () -> before("not-a-date", "2026-05-03"));
18  }
```

## 1. Scelta multipla

Qual è l'esito dei test rispetto al metodo mostrato?

- **A.** Passano entrambi perché `parse` lancia l'eccezione richiesta.
- **B.** Passa soltanto `malformedDate`.
- **C.** Passa soltanto `validInterval`.
- **D.** Il metodo non compila perché `DateTimeParseException` deve comparire in una clausola `throws`.

## 2. Risposta aperta

Indica, in **1–3 frasi**, un caso che permetta di distinguere il requisito «strettamente precedente» da «precedente o uguale», con l'esito atteso. Spiega anche perché il solo caso di un intervallo crescente non distingue queste due interpretazioni. Non scrivere codice.

<details>
<summary>Mostra la soluzione dell’esercizio 11</summary>

### 1. Scelta multipla: C

Il primo intervallo è crescente e restituisce `true`. Nel secondo test `parse` lancia l'eccezione, ma il metodo la intercetta e restituisce `false`: `assertThrows` fallisce perché non riceve alcuna eccezione.

- **A errata:** conta l'eccezione che esce dall'azione, non una che viene lanciata e gestita al suo interno.
- **B errata:** è il test sull'input malformato a fallire; quello sull'intervallo valido passa.
- **D errata:** `DateTimeParseException` è unchecked e non richiede una dichiarazione `throws`.

### 2. Risposta aperta

Con `begin` ed `end` entrambi uguali a `"2026-05-01"`, l'esito atteso è `false`; un confronto che ammette anche l'uguaglianza restituirebbe `true`. Un intervallo crescente soddisfa entrambe le interpretazioni e non permette di distinguerle; questa verifica del confine è distinta da quella sulla propagazione degli errori.

</details>

---

# Esercizio 12 — Invio di una notifica

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio prova a inviare una notifica e ripete l'operazione se riceve `IOException`. Il parametro `maxAttempts` è il numero massimo di chiamate totali, inclusa la prima, ed è almeno uno. Al primo successo il servizio deve terminare; se tutti i tentativi consentiti falliscono deve propagare l'ultima eccezione. Nei test, `Writer` simula un numero prestabilito di fallimenti iniziali e conta le chiamate.

Non vi sono concorrenza o altri tipi di errore. Il frammento appartiene a una classe di test e `Writer` è una classe annidata statica. `@Test` identifica un test; `assertEquals(atteso, effettivo)` confronta i valori e `assertThrows(T.class, azione)` intercetta un'eccezione del tipo richiesto o di un suo sottotipo, fallendo se non viene lanciata. Import omessi; i numeri di riga non fanno parte del codice.

```java
01  static class Writer {
02      final int failures;
03      int calls;
04      Writer(int failures) { this.failures = failures; }
05      void write() throws IOException {
06          calls++;
07          if (calls <= failures) {
08              throw new IOException("Unavailable");
09          }
10      }
11  }
12  static void send(Writer writer, int maxAttempts) throws IOException {
13      for (int attempt = 0; attempt <= maxAttempts; attempt++) {
14          try {
15              writer.write();
16              return;
17          } catch (IOException e) {
18              if (attempt == maxAttempts) {
19                  throw e;
20              }
21          }
22      }
23  }
24  @Test
25  void recovery() throws IOException {
26      Writer writer = new Writer(1);
27      send(writer, 2);
28      assertEquals(2, writer.calls);
29  }
30  @Test
31  void unavailable() {
32      Writer writer = new Writer(5);
33      assertThrows(IOException.class, () -> send(writer, 2));
34      assertEquals(2, writer.calls);
35  }
```

## 1. Scelta multipla

Qual è l'esito dei test?

- **A.** Passano entrambi: il limite rappresenta due ritentativi dopo il primo invio.
- **B.** Passa soltanto `recovery`; in `unavailable` l'eccezione è intercettata dall'asserzione, ma il contatore vale `3`.
- **C.** Passa soltanto `unavailable`; il metodo continua a chiamare `write` anche dopo un successo.
- **D.** Falliscono entrambi perché il primo `IOException` esce immediatamente da `send`.

## 2. Risposta aperta

Un collega propone di sostituire `Writer` nei test con un oggetto che fallisce casualmente a ogni chiamata. Valuta in **1–3 frasi** l'effetto di questa scelta sulla riproducibilità dei test e sulla verifica del limite di tentativi. Non scrivere codice.

<details>
<summary>Mostra la soluzione dell’esercizio 12</summary>

### 1. Scelta multipla: B

In `recovery` il primo invio fallisce e il secondo riesce: il `return` termina il metodo con due chiamate. In `unavailable` il ciclo usa gli indici `0`, `1`, `2`, effettuando tre chiamate: `assertThrows` passa, poi il confronto tra `2` e `3` fallisce.

- **A errata:** il contratto definisce due tentativi totali, non due ritentativi oltre al primo.
- **C errata:** il `return` impedisce chiamate successive al successo.
- **D errata:** il `catch` gestisce i fallimenti intermedi e propaga soltanto quello dell'ultima iterazione.

### 2. Risposta aperta

Con esiti casuali il numero di chiamate e l'eventuale eccezione cambierebbero tra esecuzioni: un test sul limite potrebbe incontrare un successo anticipato e non verificare il percorso desiderato. Gli esiti prestabiliti consentono invece di verificare separatamente recupero dopo un fallimento e fallimento di tutti i tentativi, con aspettative riproducibili.

</details>
