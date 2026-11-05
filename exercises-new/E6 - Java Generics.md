# Esercizio 1 — Primo elemento

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio consulta il primo elemento di liste provenienti da archivi diversi. Il metodo `first` non deve modificare le liste. I client possono lavorare sia con nomi sia con conteggi e vogliono conservare l’informazione sul tipo degli elementi. Le liste sono non nulle e non vuote. I due frammenti di chiamata vanno considerati separatamente, non come parti dello stesso programma.

I metodi e le istruzioni si trovano rispettivamente nel corpo di una classe e in un suo metodo statico, con `java.util.List` importata. I numeri di riga non fanno parte del codice.

```java
01  static Object first(List<?> values) {
02      return values.get(0);
03  }
```

```java
04  List<Integer> values = List.of(4, 7);
05  List<Number> numbers = values;
06  IO.println(numbers.get(0));
```

## 1. Scelta multipla

Che cosa accade eseguendo le righe 4–6? Seleziona una sola risposta.

- **A.** Stampa `4`, perché `Integer` è un sottotipo di `Number`.
- **B.** Non compila: `List<Integer>` non è assegnabile a `List<Number>`.
- **C.** Compila, ma solleva `ClassCastException` alla riga 6.
- **D.** Non compila perché `List.of` non può contenere wrapper numerici.

## 2. Risposta aperta

Si vuole usare lo stesso metodo `first`, senza cast nel metodo o nei chiamanti, in entrambi questi casi:

```java
07  String name = first(List.of("Ada", "Linus"));
08  Integer count = first(List.of(4, 7));
```

Proponi il contratto di tipo e il corpo necessari, senza tipi raw né overload dedicati ai singoli tipi. Spiega quale informazione il contratto attuale non conserva per il chiamante.

**Lunghezza indicativa:** massimo 3 righe di codice e 1–2 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 1</summary>

### 1. Scelta multipla: B

I tipi generici sono invarianti: `List<Integer>` non è un sottotipo di `List<Number>`.

- **A errata:** la relazione tra i tipi degli elementi non si trasferisce alle liste.
- **C errata:** il problema è rilevato a compilazione, non durante la lettura.
- **D errata:** `List.of` accetta wrapper numerici.

### 2. Risposta aperta

```java
static <T> T first(List<T> values) {
    return values.get(0);
}
```

Il parametro di tipo collega il risultato al tipo degli elementi, mentre `Object` non conserva questo legame per il chiamante. È valida anche `List<? extends T>` come parametro, lasciando invariato il risultato `T`.

</details>

---

# Esercizio 2 — Operazioni su misure numeriche

**Tempo indicativo: 5–7 minuti · Java 25**

Un laboratorio riceve misure numeriche da strumenti diversi. Alcuni producono interi e altri valori decimali. Il componente deve poter leggere entrambe le rappresentazioni tramite le operazioni di `Number`. Le misure sono non nulle e di piccola grandezza: non considerare overflow o problemi di precisione. Le liste sono non nulle, anche eventualmente vuote.

I metodi vanno dichiarati nel corpo di una classe e le istruzioni in un suo metodo statico, con `java.util.List` importata. I numeri di riga non fanno parte del codice.

```java
01  static <T extends Number> double twice(T value) {
02      return 2 * value.doubleValue();
03  }
04  static double sum(List<Number> values) {
05      double result = 0;
06      for (Number value : values) result += value.doubleValue();
07      return result;
08  }
```

```java
09  IO.println(twice("3"));
```

## 1. Scelta multipla

Che cosa accade alla riga 9? Seleziona una sola risposta.

- **A.** Stampa `6.0` dopo aver convertito la stringa in un numero.
- **B.** Compila, ma solleva `ClassCastException` dentro `twice`.
- **C.** Non compila: `String` non soddisfa il vincolo sul parametro di tipo.
- **D.** Stampa `33`, applicando la concatenazione.

## 2. Risposta aperta

Il metodo `sum` deve essere chiamabile sia con una variabile `List<Integer>` sia con una `List<Double>` o `List<Number>`. Deve soltanto leggere gli elementi, senza scrivere, copiare la lista o introdurre cast.

Proponi una firma che permetta questo uso lasciando invariato il corpo. Spiega quali operazioni sugli elementi sono garantite dal tipo scelto e se la nuova firma renda immutabile la lista originale.

**Lunghezza indicativa:** una firma e 2–3 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 2</summary>

### 1. Scelta multipla: C

Il vincolo `T extends Number` esclude `String`: non viene eseguita una conversione dal testo.

- **A errata:** il metodo non effettua parsing.
- **B errata:** il compilatore rifiuta già la chiamata.
- **D errata:** la moltiplicazione usa il valore numerico di `Number`, non concatenazioni.

### 2. Risposta aperta

`static double sum(List<? extends Number> values)` consente di leggere ogni elemento come `Number` e invocare `doubleValue`. Il tipo preciso non è noto, quindi non si possono aggiungere arbitrariamente numeri non nulli. La firma non rende immutabile l’oggetto lista: limita le operazioni disponibili attraverso quel riferimento, senza impedire modifiche tramite altri alias.

**Alternativa valida:** `static <T extends Number> double sum(List<T> values)`.

</details>

---

# Esercizio 3 — Riepilogo di una lista

**Tempo indicativo: 5–7 minuti · Java 25**

Un componente espone il numero di elementi di liste provenienti da due archivi. Il primo archivio contiene testi e il secondo conteggi interi. Il programmatore propone due overload con lo stesso nome per servire entrambi i client. Nessun metodo deve modificare la lista ricevuta. Le liste sono non nulle e possono essere vuote.

I metodi vanno dichiarati nella stessa classe, con `java.util.List` importata. I numeri di riga non fanno parte del codice.

```java
01  static int size(List<String> values) {
02      return values.size();
03  }
04  static int size(List<Integer> values) {
05      return values.size();
06  }
```

## 1. Scelta multipla

La classe può essere compilata con entrambe le dichiarazioni? Seleziona una sola risposta.

- **A.** No: i due metodi hanno la stessa firma dopo l’erasure.
- **B.** Sì: il tipo degli elementi rende distinte le firme a runtime.
- **C.** Sì, purché nessun chiamante passi una lista vuota.
- **D.** No: i metodi statici non possono ricevere liste parametrizzate.

## 2. Risposta breve

Separatamente dai due metodi, serve verificare un oggetto ricevuto come `Object`: il controllo deve restituire `true` soltanto se si tratta di una lista i cui elementi sono tutti stringhe non nulle. Una lista vuota deve essere accettata; `null`, gli altri oggetti e le liste con elementi di altro tipo devono essere rifiutati.

Descrivi i controlli da effettuare senza tipi raw o cast non verificati. Precisa che cosa puoi controllare sul contenitore e che cosa richiede l’esame degli elementi.

**Lunghezza indicativa:** 3 frasi oppure massimo 6 righe di pseudocodice.

<details>
<summary>Mostra la soluzione dell’esercizio 3</summary>

### 1. Scelta multipla: A

Entrambi i parametri diventano `List` dopo l’erasure; le dichiarazioni hanno lo stesso nome e la stessa firma erasa.

- **B errata:** gli argomenti di tipo non distinguono questi overload a runtime.
- **C errata:** i contenuti delle liste non incidono sulla validità delle dichiarazioni.
- **D errata:** un metodo statico può ricevere tipi parametrizzati.

### 2. Risposta breve

Controllo `value instanceof List<?> list`; se fallisce, restituisco `false`, anche per `null`. Visito poi ogni elemento e rifiuto quelli per cui `element instanceof String` è falso, compreso `null`. Se la visita termina, restituisco `true`: il contenitore è riconoscibile a runtime, ma il tipo di tutti i suoi elementi richiede la visita; quella vuota viene accettata.

</details>

---

# Esercizio 4 — Deposito di un elemento

**Tempo indicativo: 5–7 minuti · Java 25**

Un componente conserva un singolo dato ricevuto dal chiamante. Lo stesso contenitore deve essere riutilizzabile per tipi diversi senza cast nei client. La versione mostrata conserva il tipo tramite un parametro generico. Due depositi distinti non condividono stato. I dati sono non nulli e non sono richieste operazioni concorrenti.

Classe e istruzioni in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  class Slot<T> {
02      private T value;
03      Slot(T value) { this.value = value; }
04      T get() { return value; }
05      void set(T value) { this.value = value; }
06  }
07  // In un altro metodo:
08  Slot<String> slot = new Slot<>("Ada");
09  slot.set("Linus");
10  IO.println(slot.get().length());
```

## 1. Scelta multipla

Che cosa accade? Seleziona una sola risposta.

- **A.** Stampa `3`.
- **B.** Non compila perché l’erasure impedisce di chiamare `length`.
- **C.** Stampa `5`.
- **D.** Solleva `ClassCastException`.

## 2. Risposta aperta

Un client deve poter leggere qualunque `Slot`, senza conoscere il tipo del dato e senza sostituirlo. Proponi il tipo del parametro di `Object inspect(...)` e il suo corpo minimo; spiega perché non serve un tipo raw.

**Lunghezza indicativa:** massimo 3 righe di codice e una frase.

<details>
<summary>Mostra la soluzione dell’esercizio 4</summary>

### 1. Scelta multipla: C

Il dato è stato sostituito con `Linus`, che ha lunghezza cinque. Il compilatore conserva l’informazione di tipo necessaria al client.

- **A errata:** il vecchio testo non è più il valore del deposito.
- **B errata:** l’erasure non annulla il controllo statico della chiamata.
- **D errata:** il dato letto ha effettivamente tipo `String`.

### 2. Risposta aperta

```java
static Object inspect(Slot<?> slot) {
    return slot.get();
}
```

La wildcard rappresenta un tipo sconosciuto e consente la lettura come `Object`, senza rinunciare ai controlli sui generics. Non rende immutabile il deposito, ma impedisce a questo client di assegnargli un arbitrario valore non nullo.

</details>

---

# Esercizio 5 — Destinazione dei conteggi

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio trasferisce conteggi interi verso una raccolta scelta dal chiamante. La destinazione può essere una lista di interi o di un loro supertipo. La sorgente non viene modificata. I valori sono non nulli e le raccolte sono modificabili e non nulle. Il metodo aggiunge un solo conteggio per chiamata.

Metodo e istruzioni in un metodo statico separato, con `java.util.*` importata. I numeri di riga non fanno parte del codice.

```java
01  static void append(List<? super Integer> target, int count) {
02      target.add(count);
03  }
04  // In un altro metodo:
05  List<Number> target = new ArrayList<>();
06  append(target, 4);
07  IO.println(target.get(0));
```

## 1. Scelta multipla

Che cosa accade? Seleziona una sola risposta.

- **A.** Stampa `4`.
- **B.** Non compila perché `Number` non è esattamente `Integer`.
- **C.** Compila, ma `add` solleva sempre un’eccezione.
- **D.** Stampa `null`.

## 2. Risposta breve

Nel corpo di `append`, si può aggiungere `2.5` oppure leggere un elemento direttamente in una variabile `Integer`? Valuta separatamente le due operazioni sulla base del tipo del parametro, senza cast.

**Lunghezza indicativa:** 2–3 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 5</summary>

### 1. Scelta multipla: A

`List<Number>` è una destinazione ammessa per `? super Integer`; il conteggio viene inserito e letto.

- **B errata:** la wildcard ammette i supertipi di `Integer`.
- **C errata:** la destinazione concreta è un’`ArrayList` modificabile.
- **D errata:** viene aggiunto il valore `4`.

### 2. Risposta breve

Non si può aggiungere `2.5`, perché la destinazione potrebbe essere una `List<Integer>`. Neppure la lettura diretta come `Integer` è garantita: potrebbe essere una `List<Object>` contenente altri oggetti. La lettura generale è come `Object`, mentre la scrittura di un `Integer` è sicura.

</details>

---

# Esercizio 6 — Trasferimento tra archivi

**Tempo indicativo: 5–7 minuti · Java 25**

Due archivi devono trasferire dati senza conversioni numeriche. La sorgente produce elementi da leggere e la destinazione li riceve. Un primo metodo usa lo stesso tipo preciso di lista per entrambi. I client devono poter trasferire interi in raccolte di numeri o oggetti, senza tipi raw. Le liste sono non nulle, distinte e la destinazione è modificabile.

Metodo e chiamata separata nella stessa classe, con `java.util.*` importata. I numeri di riga non fanno parte del codice.

```java
01  static <T> void transfer(List<T> source, List<T> target) {
02      for (T value : source) {
03          target.add(value);
04      }
05  }
06  // In un altro metodo:
07  List<Integer> source = List.of(2, 3);
08  List<Number> target = new ArrayList<>();
09  transfer(source, target);
```

## 1. Scelta multipla

La chiamata compila? Seleziona una sola risposta.

- **A.** Sì, con `T = Object`.
- **B.** Sì, e converte ogni `Integer` in `Double`.
- **C.** Sì, perché entrambe le liste hanno elementi numerici.
- **D.** No: i due parametri `List<T>` impongono tipi incompatibili per questa chiamata.

## 2. Risposta aperta

Proponi una firma generica che consenta il trasferimento richiesto lasciando invariato il corpo. Descrivi il ruolo dei due limiti e indica se consente anche il percorso inverso da `List<Number>` a `List<Integer>`.

**Lunghezza indicativa:** una firma e 2 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 6</summary>

### 1. Scelta multipla: D

L’invarianza impedisce di scegliere un unico `T` che renda assegnabili entrambe le liste ai parametri dichiarati.

- **A errata:** `List<Integer>` non diventa `List<Object>`.
- **B errata:** i generics non convertono gli elementi tra wrapper.
- **C errata:** una comune superclasse degli elementi non rende covarianti le liste.

### 2. Risposta aperta

`static <T> void transfer(List<? extends T> source, List<? super T> target)` legge elementi compatibili con `T` e li scrive in un contenitore che può riceverli. Con sorgente `Integer` e destinazione `Number` si può scegliere `T = Integer`; il percorso inverso non è ammesso perché un `Number` potrebbe essere un `Double`.

</details>

---

# Esercizio 7 — Contenuto di un pacco

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio riceve oggetti da componenti con informazioni di tipo diverse. Deve riconoscere le liste e consultarne la dimensione senza assumere il tipo degli elementi. Il controllo non deve modificare il dato. `null` e gli oggetti che non sono liste devono essere rifiutati. La lista dell’esempio contiene stringhe non nulle.

Frammento in un metodo con `java.util.*` importata. I numeri di riga non fanno parte del codice.

```java
01  Object payload = List.of("Ada", "Linus");
02  if (payload instanceof List<?> values) {
03      IO.println(values.size());
04      IO.println(values.get(0));
05  }
```

## 1. Scelta multipla

Che cosa accade? Seleziona una sola risposta.

- **A.** Non compila perché nessun `instanceof` può usare generics.
- **B.** Stampa `2`, poi `Ada`.
- **C.** Solleva `ClassCastException` leggendo il primo elemento.
- **D.** Stampa `0`, poi `null`.

## 2. Risposta breve

Un revisore vuole sostituire il controllo con `payload instanceof List<String>`. Perché non è ammesso qui? Spiega anche perché il controllo originale non dimostri che tutti gli elementi siano stringhe.

**Lunghezza indicativa:** 2–3 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 7</summary>

### 1. Scelta multipla: B

`List<?>` può essere verificato con questo `instanceof`; la dimensione è due e l’elemento letto come `Object` viene stampato come `Ada`.

- **A errata:** una wildcard non limitata è consentita in questo controllo.
- **C errata:** non si effettua un cast a un tipo specifico di elemento.
- **D errata:** il controllo non svuota la lista.

### 2. Risposta breve

Da un `Object` non si può verificare a runtime l’argomento `String` di una lista: l’erasure non lo conserva per quel controllo. `List<?>` dimostra soltanto che il contenitore è una lista. Per verificare il contenuto corrente bisogna esaminare i singoli elementi, senza dedurne un vincolo permanente su tutte le possibili modifiche future.

</details>

---

# Esercizio 8 — Dati di un componente precedente

**Tempo indicativo: 5–7 minuti · Java 25**

Un vecchio componente usa una lista senza argomenti di tipo. Un nuovo client dichiara invece di lavorare soltanto con stringhe. Le due parti condividono lo stesso oggetto lista. La migrazione deve rendere rilevabili gli inserimenti di tipo errato prima dell’esecuzione. Il frammento ammette warning del compilatore, che non sono trattati come errori.

Frammento in un metodo con `java.util.*` importata. I numeri di riga non fanno parte del codice.

```java
01  List<String> names = new ArrayList<>();
02  List legacy = names;
03  legacy.add(42);
04  String first = names.get(0);
05  IO.println(first);
```

## 1. Scelta multipla

Che cosa accade? Seleziona una sola risposta.

- **A.** Stampa `42` come stringa.
- **B.** Non compila neppure se si ammettono i warning.
- **C.** Solleva `ClassCastException` alla riga 4.
- **D.** Solleva `ClassCastException` alla riga 3.

## 2. Risposta breve

Per la nuova versione tutto il codice è modificabile e non deve restare alcun tipo raw. Quale dichiarazione useresti per `legacy` e quale riga diventerebbe errata a compilazione? Spiega perché aggiungere un cast alla lettura non risolve il problema di progetto.

**Lunghezza indicativa:** una dichiarazione e 2 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 8</summary>

### 1. Scelta multipla: C

L’inserimento raw produce un warning ma entra nella lista. La lettura tramite `List<String>` richiede un cast a `String`, che fallisce sull’`Integer`.

- **A errata:** non esiste una conversione automatica a testo.
- **B errata:** il codice può compilare con warning unchecked.
- **D errata:** la lista erasa non rifiuta quel valore al momento dell’inserimento.

### 2. Risposta breve

Uso `List<String> legacy = names;`: `legacy.add(42)` diventa un errore di tipo. Un cast alla lettura non cambia il contenuto incompatibile e può solo posticipare o ripetere il fallimento a runtime.

</details>

---

# Esercizio 9 — Massimo tra valori

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio deve scegliere il maggiore tra due valori dello stesso tipo. Il criterio è l’ordine naturale del tipo scelto dal chiamante. Il servizio non modifica gli oggetti e non deve convertire tutto in stringhe. Gli argomenti sono non nulli e la relazione d’ordine è quella di `compareTo`. Un risultato negativo di `compareTo` significa che il ricevente precede l’argomento.

Metodo e chiamata in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  static <T extends Comparable<T>> T larger(T first, T second) {
02      if (first.compareTo(second) >= 0) return first;
03      return second;
04  }
05  // In un altro metodo:
06  IO.println(larger("Ada", "Linus"));
```

## 1. Scelta multipla

Che cosa accade? Seleziona una sola risposta.

- **A.** Stampa `Linus`.
- **B.** Stampa `Ada` perché è il primo argomento.
- **C.** Non compila: i generics non ammettono vincoli su interfacce.
- **D.** Stampa il numero restituito da `compareTo`.

## 2. Risposta aperta

Una variante deve ricevere l’ordinamento dal chiamante invece di richiedere `Comparable`. Proponi la firma con un `Comparator` e descrivi la sola modifica al confronto. Non implementare un comparatore concreto.

**Lunghezza indicativa:** una firma e 1–2 frasi. Puoi omettere l’import di `Comparator`.

<details>
<summary>Mostra la soluzione dell’esercizio 9</summary>

### 1. Scelta multipla: A

Nell’ordine naturale delle stringhe `Ada` precede `Linus`, quindi il metodo restituisce il secondo valore.

- **B errata:** il primo valore viene restituito solo se non minore.
- **C errata:** `extends` può esprimere un vincolo su un’interfaccia.
- **D errata:** il risultato del metodo è un oggetto di tipo `T`, non l’esito numerico del confronto.

### 2. Risposta aperta

`static <T> T larger(T first, T second, Comparator<? super T> order)` elimina il vincolo `Comparable`. Il confronto diventa `order.compare(first, second) >= 0`; resta invariata la scelta dell’oggetto da restituire. È valida anche una firma con `Comparator<T>` se basta l’uso con il tipo preciso.

</details>

---

# Esercizio 10 — Costruzione di una raccolta

**Tempo indicativo: 5–7 minuti · Java 25**

Un componente generico conserva elementi e vuole creare una nuova raccolta vuota dello stesso tipo di elemento. La versione mostrata tenta di usare un array. Il componente deve restare indipendente dalla classe concreta degli elementi. Non occorre riempire o leggere la raccolta in questo esercizio. Le dimensioni ricevute sono non negative.

Metodo nel corpo di una classe. I numeri di riga non fanno parte del codice.

```java
01  static <T> T[] empty(int size) {
02      return new T[size];
03  }
```

## 1. Scelta multipla

Il metodo compila? Seleziona una sola risposta.

- **A.** Sì, crea un array del tipo inferito dal chiamante.
- **B.** Sì, se `size` è zero.
- **C.** Sì, ma fallisce sempre con `ClassCastException`.
- **D.** No: non è consentita questa creazione di un array di tipo `T`.

## 2. Risposta aperta

Il requisito può cambiare: il risultato deve essere una raccolta vuota modificabile di elementi `T`, non necessariamente un array e senza una capacità pubblica prestabilita. Proponi una firma senza il parametro dimensione e il corpo minimo, evitando cast e reflection.

**Lunghezza indicativa:** massimo 3 righe di codice e una frase. Puoi usare `java.util.List` e `ArrayList`.

<details>
<summary>Mostra la soluzione dell’esercizio 10</summary>

### 1. Scelta multipla: D

Il parametro di tipo non fornisce il tipo runtime necessario a creare quell’array.

- **A errata:** l’inferenza non rende disponibile una classe runtime per `new T[]`.
- **B errata:** anche con lunghezza zero la dichiarazione resta vietata.
- **C errata:** il problema è di compilazione, non un fallimento inevitabile a runtime.

### 2. Risposta aperta

```java
static <T> List<T> empty() {
    return new ArrayList<>();
}
```

La lista fornisce una raccolta modificabile con controllo statico del tipo, senza creare array generici. `List.of()` non soddisferebbe il requisito di modificabilità.

</details>
