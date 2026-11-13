# Esercizio 1 — Destinatari di un avviso

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio prepara i destinatari di un avviso a partire da una lista di iscrizioni. La stessa persona può comparire più volte perché iscritta a canali diversi. La prima versione copia le iscrizioni e rimuove un destinatario prima dell’invio. La lista originale deve restare disponibile per altre elaborazioni. I nomi sono non nulli e l’uguaglianza è sensibile a maiuscole e minuscole.

Il frammento è eseguito in un metodo con gli import indicati; i numeri di riga non fanno parte del codice.

```java
01  import java.util.ArrayList;
02  import java.util.List;
```

```java
03  List<String> input = List.of("Ada", "Linus", "Ada", "Grace");
04  List<String> recipients = new ArrayList<>(input);
05  recipients.remove("Ada");
06  IO.println(recipients);
07  IO.println(input.size());
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le due righe nell’ordine.

- **A.** `[Linus, Ada, Grace]`, poi `4`.
- **B.** `[Linus, Grace]`, poi `4`.
- **C.** `[Linus, Ada, Grace]`, poi `3`.
- **D.** Solleva `UnsupportedOperationException` prima di stampare.

## 2. Risposta aperta

Per una nuova elaborazione, riparti dalla lista `input` originale: ogni destinatario deve comparire una sola volta, nell’ordine della prima comparsa. Il risultato deve essere indipendente da `input`; non sono richieste rimozioni.

Scegli tipo dichiarato e implementazione della collezione risultante e mostra come costruirla. Motiva come la scelta garantisca entrambi i requisiti, senza basarti sul particolare ordine dei quattro nomi dell’esempio.

**Lunghezza indicativa:** massimo 2 righe di codice e 2 frasi. Puoi omettere gli import aggiuntivi.

<details>
<summary>Mostra la soluzione dell’esercizio 1</summary>

### 1. Scelta multipla: A

`remove("Ada")` elimina solo la prima occorrenza nella copia: resta `[Linus, Ada, Grace]`. L’originale conserva quattro elementi.

- **B errata:** non vengono eliminate tutte le occorrenze.
- **C errata:** la rimozione non cambia la lista originale.
- **D errata:** `ArrayList` è modificabile anche se costruita da `List.of`.

### 2. Risposta aperta

```java
Set<String> unique = new LinkedHashSet<>(input);
```

Il set elimina duplicati e `LinkedHashSet` conserva l’ordine di prima inserzione. È un contenitore distinto da `input`; le stringhe condivise sono immutabili. È valida anche una `SequencedSet<String>` come tipo dichiarato.

</details>

---

# Esercizio 2 — Visite a una pagina

**Tempo indicativo: 5–7 minuti · Java 25**

Un sito mantiene un riepilogo delle visite per pagina. Una mappa associa il nome di ogni pagina al conteggio corrente. `visit` deve incrementare il conteggio oppure creare una voce pari a 1 per una pagina nuova. Le pagine già presenti non devono essere duplicate come chiavi. Mappa e nomi sono non nulli, i conteggi sono piccoli interi non negativi e mai nulli; il programma è monothread e senza overflow.

Il metodo va dichiarato nel corpo di una classe e le istruzioni in un suo metodo statico, con `java.util.Map` e `java.util.HashMap` importate. `put` restituisce il valore precedente associato alla chiave, oppure `null` se non esisteva. I numeri di riga non fanno parte del codice.

```java
01  static void visit(Map<String, Integer> counts, String page) {
02      counts.put(page, counts.get(page) + 1);
03  }
```

```java
04  Map<String, Integer> counts = new HashMap<>();
05  IO.println(counts.put("home", 1));
06  IO.println(counts.put("home", 2));
07  IO.println(counts.size());
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le tre righe nell’ordine.

- **A.** `1`, poi `2`, poi `2`.
- **B.** `null`, poi `1`, poi `1`.
- **C.** `null`, poi `1`, poi `2`.
- **D.** `1`, poi `2`, poi `1`.

## 2. Risposta breve

Considera separatamente una mappa inizialmente vuota e due chiamate consecutive `visit(counts, "home")`. Il metodo soddisfa il contratto? Se necessario, correggilo indicando il conteggio che deve risultare dopo entrambe le chiamate.

**Lunghezza indicativa:** 1–2 frasi e massimo 3 righe per il corpo del metodo.

<details>
<summary>Mostra la soluzione dell’esercizio 2</summary>

### 1. Scelta multipla: B

Il primo `put` non sostituisce valori e restituisce `null`; il secondo restituisce il precedente `1`. La chiave rimane una sola.

- **A errata:** `put` restituisce il valore precedente, non quello inserito.
- **C errata:** sostituire un valore non aggiunge una chiave.
- **D errata:** i primi due risultati non sono i nuovi valori.

### 2. Risposta breve

La prima chiamata fallisce durante l’unboxing di `null` restituito da `get`. Il corpo può diventare:

```java
counts.put(page, counts.getOrDefault(page, 0) + 1);
```

Dopo due chiamate il conteggio è `2`. È valido anche un controllo esplicito dell’assenza prima dell’incremento, oppure `merge(page, 1, Integer::sum)`.

</details>

---

# Esercizio 3 — Gestione delle richieste

**Tempo indicativo: 5–7 minuti · Java 25**

Uno sportello riceve richieste identificate da un nome. La prima versione deve servirle nell’ordine di arrivo. La consultazione della prossima richiesta non deve estrarla, mentre il servizio effettivo deve rimuoverla. Il frammento simula arrivi e servizio senza altri componenti esterni. I nomi sono non nulli e tutte le operazioni avvengono nello stesso thread.

Il frammento è eseguito in un metodo con `java.util.Queue` e `java.util.ArrayDeque` importate. I numeri di riga non fanno parte del codice.

```java
01  Queue<String> pending = new ArrayDeque<>();
02  pending.offer("A");
03  pending.offer("B");
04  IO.println(pending.poll());
05  pending.offer("C");
06  IO.println(pending.peek());
07  IO.println(pending.size());
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le tre righe nell’ordine.

- **A.** `B`, poi `C`, poi `2`.
- **B.** `A`, poi `B`, poi `1`.
- **C.** `A`, poi `C`, poi `2`.
- **D.** `A`, poi `B`, poi `2`.

## 2. Risposta aperta

La nuova versione distingue richieste urgenti e normali. Alla scelta della prossima richiesta devono essere servite prima le urgenti; all’interno di ciascuna categoria va rispettato l’ordine di arrivo. Una richiesta normale può quindi attendere mentre arrivano nuove urgenti.

Scegli una rappresentazione con collezioni standard e descrivi inserimento ed estrazione. Specifica il risultato dell’estrazione quando non esistono richieste. Non serve implementare una classe completa.

**Lunghezza indicativa:** le dichiarazioni dei campi necessari e 3 frasi, oppure uno schema equivalente di massimo 6 righe.

<details>
<summary>Mostra la soluzione dell’esercizio 3</summary>

### 1. Scelta multipla: D

`poll` estrae `A`; `peek` legge `B` senza rimuoverlo. Dopo l’inserimento di `C` restano due richieste.

- **A errata:** una coda FIFO serve prima `A`.
- **B errata:** `peek` non riduce la dimensione.
- **C errata:** `B` precede `C` nella coda.

### 2. Risposta aperta

```java
Queue<String> urgent = new ArrayDeque<>();
Queue<String> normal = new ArrayDeque<>();
```

Inserisco con `offer` nella coda della categoria. Estraggo con `poll` dalle urgenti se non vuote, altrimenti dalle normali, ottenendo `null` se entrambe sono vuote. Le due code mantengono il FIFO interno e permettono che nuove urgenti precedano normali in attesa.

**Alternativa valida:** una coda a priorità con categoria e numero progressivo come criteri di ordinamento; la sola categoria non garantisce il FIFO tra pari priorità.

</details>

---

# Esercizio 4 — Pulizia di una lista

**Tempo indicativo: 5–7 minuti · Java 25**

Un catalogo conserva le quantità disponibili in una lista modificabile. Le quantità pari a zero devono essere rimosse prima della stampa. Il chiamante conserva la lista e deve osservare la pulizia sullo stesso oggetto. I valori sono interi non nulli e non negativi. Il programma è monothread.

Frammento in un metodo con `java.util.*` importata. I numeri di riga non fanno parte del codice.

```java
01  List<Integer> quantities = new ArrayList<>(List.of(0, 2, 0, 3));
02  Iterator<Integer> cursor = quantities.iterator();
03  while (cursor.hasNext()) {
04      if (cursor.next() == 0) cursor.remove();
05  }
06  IO.println(quantities);
```

## 1. Scelta multipla

Qual è l’output? Seleziona una sola risposta.

- **A.** `[0, 2, 0, 3]`.
- **B.** `[2, 0, 3]`.
- **C.** `[2, 3]`.
- **D.** Solleva sempre `ConcurrentModificationException`.

## 2. Risposta breve

Un revisore propone un ciclo per indici che, quando trova zero, esegue `quantities.remove(i)` e incrementa sempre `i`. Fornisci un input minimo che mostri una quantità zero residua e spiega perché il ciclo con l’iteratore non ha quel difetto.

**Lunghezza indicativa:** un input e 2 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 4</summary>

### 1. Scelta multipla: C

L’iteratore rimuove entrambi gli zeri tramite la propria operazione di rimozione.

- **A errata:** il codice modifica la lista.
- **B errata:** il ciclo visita anche il secondo zero.
- **D errata:** rimuovere tramite lo stesso iteratore è supportato da `ArrayList`.

### 2. Risposta breve

Con `[0, 0]`, dopo la rimozione all’indice zero, il secondo elemento scivola all’indice zero e viene saltato dall’incremento di `i`. L’iteratore mantiene correttamente la posizione dopo `remove()` e visita il successivo elemento rimasto.

</details>

---

# Esercizio 5 — Elenco pubblico dei turni

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio conserva i turni in una lista interna. Vuole esporre i nomi senza permettere ai lettori di modificarli. La versione mostrata costruisce una vista non modificabile e una copia. Dopo la pubblicazione il servizio può aggiungere nuovi turni alla lista interna. Tutte le operazioni sono nello stesso thread e i nomi sono stringhe non nulle.

Frammento in un metodo con `java.util.*` importata. I numeri di riga non fanno parte del codice.

```java
01  List<String> internal = new ArrayList<>(List.of("Ada"));
02  List<String> view = Collections.unmodifiableList(internal);
03  List<String> snapshot = List.copyOf(internal);
04  internal.add("Linus");
05  IO.println(view.size());
06  IO.println(snapshot.size());
```

## 1. Scelta multipla

Seleziona le due righe di output nell’ordine.

- **A.** `1`, poi `1`.
- **B.** `2`, poi `1`.
- **C.** `2`, poi `2`.
- **D.** Eccezione alla riga 4.

## 2. Risposta aperta

L’API deve pubblicare una fotografia non modificabile dei turni al momento della chiamata, non una vista aggiornata. Proponi il corpo di `List<String> turns()` assumendo che `internal` sia il campo della classe e motiva perché basta per questi elementi.

**Lunghezza indicativa:** una riga di codice e 2 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 5</summary>

### 1. Scelta multipla: B

La vista segue la lista interna, mentre la copia mantiene il contenuto iniziale.

- **A errata:** la vista riflette l’aggiunta.
- **C errata:** la copia non è collegata alle modifiche successive.
- **D errata:** la lista interna resta modificabile.

### 2. Risposta aperta

```java
return List.copyOf(internal);
```

Il risultato non permette modifiche e non riflette i successivi aggiornamenti di `internal`. La condivisione dei riferimenti agli elementi non espone stato modificabile perché `String` è immutabile. È valida una vista non modificabile su una nuova `ArrayList` copiata, non direttamente sul campo.

</details>

---

# Esercizio 6 — Graduatoria dei risultati

**Tempo indicativo: 5–7 minuti · Java 25**

Una gara mantiene una graduatoria con i punteggi degli iscritti. Due iscritti possono avere lo stesso punteggio. Il componente mostrato usa un insieme ordinato per memorizzare i risultati. La stampa serve a controllare ordinamento e numero di elementi. I punteggi sono interi e non ci sono operazioni concorrenti.

Frammento in un metodo con `java.util.*` importata. I numeri di riga non fanno parte del codice.

```java
01  Set<Integer> scores = new TreeSet<>();
02  scores.add(8);
03  scores.add(5);
04  scores.add(8);
05  IO.println(scores);
06  IO.println(scores.size());
```

## 1. Scelta multipla

Seleziona l’output corretto.

- **A.** `[8, 5, 8]`, poi `3`.
- **B.** `[8, 5]`, poi `2`.
- **C.** `[5, 8, 8]`, poi `3`.
- **D.** `[5, 8]`, poi `2`.

## 2. Risposta aperta

La graduatoria deve conservare una voce per ogni iscritto, anche a parità di punteggio, ordinandole per punteggio decrescente. Scegli una collezione e descrivi come ordinarla; non occorre aggiungere nomi o una nuova classe.

**Lunghezza indicativa:** massimo 2 righe di codice e 1–2 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 6</summary>

### 1. Scelta multipla: D

`TreeSet` ordina i valori e conserva una sola occorrenza per punteggio.

- **A errata:** un set non conserva questi duplicati.
- **B errata:** l’ordine naturale degli interi è crescente, non di inserimento.
- **C errata:** il secondo `8` non viene aggiunto.

### 2. Risposta aperta

```java
List<Integer> ranking = new ArrayList<>(List.of(8, 5, 8));
ranking.sort(Comparator.reverseOrder());
```

La lista mantiene le due occorrenze di `8`; il comparatore dà l’ordine decrescente. Sono valide altre liste modificabili ordinabili; un set dei soli punteggi non soddisfa il requisito.

</details>

---

# Esercizio 7 — Prossima attività

**Tempo indicativo: 5–7 minuti · Java 25**

Un pianificatore associa a ogni attività una priorità numerica. In questo esempio le attività sono rappresentate soltanto dalle priorità e il valore minore deve essere servito prima. La coda permette di estrarre ripetutamente la prossima priorità. Possono esserci duplicati e la coda può diventare vuota. Tutte le operazioni avvengono nello stesso thread.

Frammento in un metodo con `java.util.*` importata. I numeri di riga non fanno parte del codice.

```java
01  Queue<Integer> queue = new PriorityQueue<>();
02  queue.offer(4);
03  queue.offer(1);
04  queue.offer(3);
05  IO.println(queue.poll());
06  IO.println(queue.poll());
07  IO.println(queue.peek());
```

## 1. Scelta multipla

Seleziona l’output corretto.

- **A.** `1`, `3`, `4`.
- **B.** `4`, `1`, `3`.
- **C.** `1`, `4`, `3`.
- **D.** L’ordine delle tre stampe non è garantito.

## 2. Risposta breve

Un client sostituisce le estrazioni con un ciclo `for` sulla coda e presume di ottenere tutte le priorità in ordine crescente. La garanzia è corretta? Indica come produrre la sequenza ordinata senza consumare la coda originale.

**Lunghezza indicativa:** 2–3 frasi, senza codice obbligatorio.

<details>
<summary>Mostra la soluzione dell’esercizio 7</summary>

### 1. Scelta multipla: A

Le estrazioni prendono il minimo: prima `1`, poi `3`; resta `4` in testa.

- **B errata:** la coda non usa l’ordine di arrivo.
- **C errata:** dopo `1`, il minimo residuo è `3`.
- **D errata:** l’ordine di estrazione per queste priorità è garantito.

### 2. Risposta breve

L’iteratore di `PriorityQueue` non garantisce l’ordine crescente. Si può copiare la coda in una nuova `PriorityQueue` ed estrarre con `poll` fino allo svuotamento della copia. In alternativa, si copiano gli elementi in una lista e la si ordina.

</details>

---

# Esercizio 8 — Ordine dei contatti

**Tempo indicativo: 5–7 minuti · Java 25**

Una rubrica associa ogni nome a un numero interno. L’ordine richiesto è quello del primo inserimento del nome. Un aggiornamento del numero non deve spostare il contatto. La rubrica mostrata è monothread e non usa la modalità di ordine per accesso. Chiavi e valori sono stringhe non nulle.

Frammento in un metodo con `java.util.*` importata. I numeri di riga non fanno parte del codice.

```java
01  Map<String, String> contacts = new LinkedHashMap<>();
02  contacts.put("Ada", "10");
03  contacts.put("Linus", "20");
04  contacts.put("Ada", "30");
05  IO.println(contacts.keySet());
06  IO.println(contacts.get("Ada"));
```

## 1. Scelta multipla

Seleziona l’output corretto.

- **A.** `[Linus, Ada]`, poi `30`.
- **B.** `[Ada, Linus, Ada]`, poi `10`.
- **C.** `[Ada, Linus]`, poi `30`.
- **D.** `[Ada, Linus]`, poi `10`.

## 2. Risposta breve

Il client riceve `Set<String> names = contacts.keySet();` e chiama `names.remove("Ada")`. Descrivi l’effetto sulla rubrica. Se il requisito è dare una copia modificabile dei soli nomi senza permettere modifiche alla rubrica, proponi una costruzione che conservi l’ordine.

**Lunghezza indicativa:** 1–2 frasi e una riga di codice.

<details>
<summary>Mostra la soluzione dell’esercizio 8</summary>

### 1. Scelta multipla: C

L’aggiornamento mantiene la posizione della chiave ma sostituisce il suo valore con `30`.

- **A errata:** l’aggiornamento non reinserisce la chiave in fondo.
- **B errata:** una mappa non duplica una chiave uguale.
- **D errata:** il valore precedente è stato sostituito.

### 2. Risposta breve

`keySet()` è una vista collegata alla mappa: rimuovere la chiave elimina anche l’associazione. Una copia indipendente e modificabile è:

```java
Set<String> names = new LinkedHashSet<>(contacts.keySet());
```

</details>

---

# Esercizio 9 — Ricerca nel catalogo

**Tempo indicativo: 5–7 minuti · Java 25**

Un catalogo conserva codici unici in ordine crescente. Un client cerca un codice mediante `Collections.binarySearch`. Se non lo trova, deve inserirlo mantenendo l’ordinamento. Il confronto usa l’ordine naturale degli interi. Per una ricerca senza successo, l’API restituisce `-(puntoDiInserimento) - 1`.

Frammento in un metodo con `java.util.*` importata. I numeri di riga non fanno parte del codice.

```java
01  List<Integer> codes = new ArrayList<>(List.of(10, 30, 50));
02  int position = Collections.binarySearch(codes, 40);
03  IO.println(position);
04  IO.println(codes.size());
```

## 1. Scelta multipla

Seleziona l’output corretto.

- **A.** `2`, poi `4`.
- **B.** `-3`, poi `3`.
- **C.** `-1`, poi `3`.
- **D.** `3`, poi `3`.

## 2. Risposta breve

Mostra come usare `position` per inserire `40` soltanto se assente. Spiega perché applicare la stessa ricerca a una lista non ordinata non soddisferebbe il requisito generale.

**Lunghezza indicativa:** una riga di codice e 1–2 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 9</summary>

### 1. Scelta multipla: B

Il punto di inserimento è `2`, quindi il risultato è `-3`. La ricerca non modifica la dimensione.

- **A errata:** la ricerca non inserisce e restituisce una codifica negativa quando manca il codice.
- **C errata:** `-1` codificherebbe il punto zero.
- **D errata:** non è un indice trovato.

### 2. Risposta breve

```java
if (position < 0) codes.add(-position - 1, 40);
```

La ricerca binaria richiede l’ordinamento con lo stesso criterio di confronto. Su una lista non ordinata non garantisce né una ricerca corretta né un punto di inserimento utile.

</details>

---

# Esercizio 10 — Iscrizioni ai laboratori

**Tempo indicativo: 5–7 minuti · Java 25**

Un’organizzazione conserva gli iscritti a due laboratori. Ogni laboratorio usa un set di nomi, quindi un nome non si ripete al suo interno. Serve calcolare chi frequenta entrambi senza modificare le iscrizioni originali. La stampa verifica soltanto appartenenza e dimensione, non l’ordine dei set hash. I nomi sono stringhe non nulle e i set sono modificabili.

Frammento in un metodo con `java.util.*` importata. I numeri di riga non fanno parte del codice.

```java
01  Set<String> first = new HashSet<>(Set.of("Ada", "Linus"));
02  Set<String> second = new HashSet<>(Set.of("Ada", "Grace"));
03  Set<String> common = new HashSet<>(first);
04  common.retainAll(second);
05  IO.println(common.contains("Ada"));
06  IO.println(first.size());
```

## 1. Scelta multipla

Seleziona l’output corretto.

- **A.** `true`, poi `1`.
- **B.** `false`, poi `2`.
- **C.** `false`, poi `1`.
- **D.** `true`, poi `2`.

## 2. Risposta aperta

Una nuova funzione deve invece restituire tutti gli iscritti, senza duplicati, ordinati alfabeticamente e indipendenti dai set ricevuti. Proponi rappresentazione e costruzione del risultato senza modificare gli input.

**Lunghezza indicativa:** massimo 2 righe di codice e una frase.

<details>
<summary>Mostra la soluzione dell’esercizio 10</summary>

### 1. Scelta multipla: D

L’intersezione della copia contiene `Ada`; `first` resta invariato con due elementi.

- **A errata:** viene modificato solo `common`.
- **B errata:** `Ada` appartiene a entrambi i set.
- **C errata:** sono errate sia l’appartenenza sia la dimensione originale.

### 2. Risposta aperta

```java
Set<String> all = new TreeSet<>(first);
all.addAll(second);
```

`TreeSet` elimina duplicati e ordina alfabeticamente secondo l’ordine naturale delle stringhe; i contenitori originali non vengono modificati. È valida anche una lista ordinata costruita da un’unione deduplicata.

</details>

---

# Esercizio 11 — Indice dei nomi

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio inverte una mappa che associa codici numerici a nomi. Il risultato deve contenere ogni nome distinto come chiave, associato a uno dei codici che avevano quel nome nella mappa originale. Quando più codici hanno lo stesso nome, il contratto consente di sceglierne uno qualsiasi. Non è richiesto un particolare ordine e la mappa originale non deve essere modificata.

Chiavi e valori sono non nulli. `putIfAbsent(k, v)` inserisce l'associazione soltanto se la chiave non è già presente. La `LinkedHashMap` del test viene attraversata nell'ordine di inserimento. Il frammento appartiene a una classe di test; import omessi. `@Test` identifica un test e `assertEquals(atteso, effettivo)` confronta le mappe per associazioni, non per ordine. I numeri di riga non fanno parte del codice.

```java
01  static Map<String, Integer> invert(Map<Integer, String> source) {
02      Map<String, Integer> result = new HashMap<>();
03      for (Map.Entry<Integer, String> entry : source.entrySet()) {
04          result.putIfAbsent(entry.getValue(), entry.getKey());
05      }
06      return result;
07  }
08
09  @Test
10  void distinctNames() {
11      Map<Integer, String> source = Map.of(1, "Ada", 2, "Lino");
12      assertEquals(Map.of("Ada", 1, "Lino", 2), invert(source));
13  }
14
15  @Test
16  void sharedName() {
17      Map<Integer, String> source = new LinkedHashMap<>();
18      source.put(1, "Ada");
19      source.put(2, "Ada");
20      assertEquals(Map.of("Ada", 2), invert(source));
21  }
```

## 1. Scelta multipla

Quale valutazione dei test e dell'implementazione è corretta?

- **A.** Entrambi i test passano e verificano anche l'ordine delle mappe.
- **B.** `sharedName` fallisce, dimostrando che il metodo viola il contratto sui duplicati.
- **C.** Entrambi falliscono perché una `HashMap` non è confrontabile con `Map.of`.
- **D.** `distinctNames` passa; `sharedName` fallisce anche se il risultato prodotto è ammesso dal contratto.

## 2. Risposta aperta

Descrivi in **1–3 frasi** quali proprietà dovrebbe verificare un test del caso con nomi ripetuti, per accettare tutte e sole le associazioni consentite dal contratto. Non scrivere codice.

<details>
<summary>Mostra la soluzione dell’esercizio 11</summary>

### 1. Scelta multipla: D

Nel primo test ogni nome ha un solo codice e le associazioni coincidono. Nel secondo, `putIfAbsent` conserva `1`, mentre il test pretende `2`: l'asserzione fallisce, ma entrambe le scelte sono ammesse.

- **A errata:** il secondo test fallisce; inoltre l'uguaglianza delle mappe non confronta l'ordine.
- **B errata:** il contratto non richiede di conservare l'ultimo codice incontrato.
- **C errata:** mappe di implementazioni diverse possono essere uguali se contengono le stesse associazioni.

### 2. Risposta aperta

Le chiavi del risultato devono essere esattamente i nomi distinti dell'input; per ogni coppia `(nome, codice)` restituita, l'input deve associare proprio quel codice a quel nome. Nel caso mostrato, occorre accettare sia `{"Ada": 1}` sia `{"Ada": 2}`, ma non una mappa vuota, un codice estraneo o nomi aggiuntivi; va inoltre verificato separatamente che l'input resti invariato.

</details>

---

# Esercizio 12 — Messaggi degli utenti

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio combina gli utenti con i loro messaggi mediante l'identificativo dell'autore. Ogni utente ha un identificativo univoco e può avere zero, uno o più messaggi. Il risultato deve contenere una voce per ogni messaggio di un utente noto, con il nome dell'utente e il testo; i messaggi di utenti sconosciuti vanno ignorati. L'ordine richiesto è quello degli utenti e, per ciascun utente, quello dei suoi messaggi nell'input.

Liste, elementi e campi sono non nulli; gli input non devono essere modificati. Il frammento appartiene a una classe di test. I record confrontano i propri campi per valore; `Map.put` sostituisce il valore precedente se la chiave esiste già. `@Test` identifica un test e `assertEquals(atteso, effettivo)` confronta le liste per contenuto e ordine. Import omessi; i numeri di riga non fanno parte del codice.

```java
01  record User(long id, String name) {}
02  record Post(long userId, String text) {}
03  record UserPost(String name, String text) {}
04
05  static List<UserPost> combine(List<User> users, List<Post> posts) {
06      Map<Long, Post> byUser = new HashMap<>();
07      for (Post post : posts) {
08          byUser.put(post.userId(), post);
09      }
10      List<UserPost> result = new ArrayList<>();
11      for (User user : users) {
12          Post post = byUser.get(user.id());
13          if (post != null) {
14              result.add(new UserPost(user.name(), post.text()));
15          }
16      }
17      return result;
18  }
19
20  @Test
21  void messagesFromKnownUsers() {
22      List<User> users = List.of(new User(1, "Ada"), new User(2, "Lino"));
23      List<Post> posts = List.of(new Post(2, "B"), new Post(1, "A"),
24              new Post(9, "X"));
25      assertEquals(List.of(new UserPost("Ada", "A"),
26              new UserPost("Lino", "B")), combine(users, posts));
27  }
```

## 1. Scelta multipla

Come termina il test mostrato?

- **A.** Passa: il risultato è `[("Ada", "A"), ("Lino", "B")]`.
- **B.** Fallisce: il risultato segue l'ordine dei messaggi, quindi comincia con Lino.
- **C.** Fallisce: contiene anche il messaggio `X` dell'utente sconosciuto.
- **D.** Lancia `NullPointerException` quando incontra il messaggio dell'utente 9.

## 2. Risposta aperta

Valuta se il test è sufficiente rispetto alla molteplicità dei messaggi ammessa dal contratto. In **1–3 frasi**, proponi un piccolo input aggiuntivo e indica risultato atteso e risultato effettivo, senza scrivere codice.

<details>
<summary>Mostra la soluzione dell’esercizio 12</summary>

### 1. Scelta multipla: A

La mappa contiene i messaggi indicizzati per autore; il ciclo finale segue la lista degli utenti, prima Ada e poi Lino. L'utente 9 non viene attraversato e il suo messaggio non entra nel risultato.

- **B errata:** l'ordine finale è determinato dal ciclo sugli utenti, non dall'ordine di inserimento nella mappa.
- **C errata:** il risultato viene costruito soltanto per gli utenti noti.
- **D errata:** non viene cercato un utente per ogni messaggio e non si dereferenzia un utente mancante.

### 2. Risposta aperta

Il test non verifica più messaggi dello stesso autore: con utenti `[(1, "Ada")]` e messaggi `[(1, "A"), (1, "B")]`, il risultato atteso è `[("Ada", "A"), ("Ada", "B")]`, quello effettivo è soltanto `[("Ada", "B")]`. La mappa conserva un solo `Post` per identificativo e il secondo inserimento sostituisce il primo.

**Alternativa valida:** qualsiasi input con più messaggi di uno stesso utente noto, accompagnato dai risultati corretti e dalla spiegazione della sostituzione.

</details>
