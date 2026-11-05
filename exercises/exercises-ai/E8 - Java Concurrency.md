# Esercizio 1 — Conteggio degli eventi

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio conta gli eventi prodotti da due sorgenti. Ogni sorgente esegue un solo incremento sullo stesso contatore, inizialmente pari a zero. Ogni chiamata a `increment` deve aumentarlo esattamente di uno. Il chiamante vuole leggere il totale dopo la fine delle due elaborazioni. Entrambi i thread terminano normalmente e il thread che esegue il frammento non viene interrotto.

Le istruzioni dopo la classe sono eseguite in un metodo che dichiara `throws InterruptedException`. I numeri di riga non fanno parte del codice.

```java
01  class Counter {
02      private volatile int value;
03      void increment() { value++; }
04      int get() { return value; }
05  }
```

```java
06  Counter counter = new Counter();
07  Thread first = new Thread(counter::increment);
08  Thread second = new Thread(counter::increment);
09  first.start();
10  second.start();
11  first.join();
12  second.join();
13  IO.println(counter.get());
```

## 1. Scelta multipla

Quali valori può stampare il programma secondo le garanzie di Java? Seleziona una sola risposta.

- **A.** Soltanto `2`, perché il campo è `volatile`.
- **B.** `0`, `1` o `2`, perché la stampa potrebbe precedere gli incrementi.
- **C.** `1` oppure `2`, in base all’interleaving degli incrementi.
- **D.** Soltanto `1`, perché i thread condividono lo stesso oggetto.

## 2. Risposta breve

Considera una versione distinta in cui `increment` è dichiarato `synchronized`, il campo resta `volatile` e i due `join` vengono eliminati. La stampa finale è garantita pari a `2`? Motiva distinguendo la protezione dell’incremento dall’attesa del completamento dei thread; non modificare questa versione.

**Lunghezza indicativa:** 2–3 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 1</summary>

### 1. Scelta multipla: C

`volatile` dà visibilità ma non rende atomico il ciclo lettura-incremento-scrittura. I thread possono leggere entrambi zero e scrivere entrambi uno; altrimenti il risultato è due. I `join` impediscono la lettura prima della loro terminazione.

- **A errata:** un incremento composto non diventa atomico grazie a `volatile`.
- **B errata:** dopo entrambi i `join` la stampa non precede le scritture e zero non è un esito possibile.
- **D errata:** l’interleaving può anche serializzare gli incrementi, producendo due.

### 2. Risposta breve

No: `synchronized` evita la perdita di incrementi, ma non attende l’esecuzione dei thread. Senza `join` la stampa può leggere `0`, `1` o `2` dal campo ancora `volatile`. Mutua esclusione e attesa del completamento sono requisiti distinti.

</details>

---

# Esercizio 2 — Apertura di un varco

**Tempo indicativo: 5–7 minuti · Java 25**

Un worker attende il permesso prima di attraversare un varco. Il varco parte chiuso e, una volta aperto, rimane aperto. `await` deve ritornare soltanto quando il permesso è disponibile. Il thread principale apre il varco e attende la conclusione del worker. Il worker non viene interrotto, termina normalmente e tutti i thread vengono prima o poi eseguiti.

Le istruzioni dopo la classe sono eseguite in un metodo che dichiara `throws InterruptedException`. I numeri di riga non fanno parte del codice.

```java
01  class Gate {
02      private boolean open;
03      synchronized void await() throws InterruptedException {
04          while (!open) wait();
05      }
06      synchronized void open() {
07          open = true;
08          notifyAll();
09      }
10  }
```

```java
11  Gate gate = new Gate();
12  Thread worker = new Thread(() -> {
13      try { gate.await(); }
14      catch (InterruptedException exception) { throw new AssertionError(exception); }
15      IO.println("Passed");
16  });
17  worker.start();
18  gate.open();
19  worker.join();
20  IO.println("End");
```

## 1. Scelta multipla

Quale affermazione sull’output è corretta? Seleziona una sola risposta.

- **A.** Stampa sempre `Passed`, poi `End`.
- **B.** Stampa sempre `End`, poi `Passed`.
- **C.** Può stampare le due righe in qualsiasi ordine.
- **D.** Può restare in attesa per sempre soltanto perché `open()` viene eseguito prima di `await()`.

## 2. Risposta breve

In una versione distinta si sostituisce il `while` con `if (!open) wait();`. Considera che Java ammette risvegli di `wait` anche senza una notifica. La versione modificata garantisce ancora il contratto di `await`? Descrivi una sequenza di eventi che sostenga la tua valutazione, senza usare pause temporizzate.

**Lunghezza indicativa:** 2–3 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 2</summary>

### 1. Scelta multipla: A

Il worker stampa `Passed` dopo aver osservato il varco aperto; il thread principale stampa `End` solo dopo il suo `join`.

- **B errata:** `join` attende anche la stampa del worker.
- **C errata:** esiste un vincolo di ordine esplicito.
- **D errata:** se il varco è già aperto, `await` non entra in `wait`.

### 2. Risposta breve

La versione con `if` non garantisce il contratto. Il worker può osservare `open == false`, entrare in attesa e risvegliarsi senza notifica mentre il varco è ancora chiuso; con `if` proseguirebbe senza ricontrollare. Il `while` verifica nuovamente la condizione dopo ogni risveglio, sotto lo stesso monitor.

</details>

---

# Esercizio 3 — Risultato di un’elaborazione

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio affida un calcolo a un executor con un solo worker. Il chiamante conserva un `Future` per recuperarne il risultato. Dopo l’invio non sono necessari altri task e viene richiesto lo shutdown. La chiusura delle risorse deve evitare di lasciare worker attivi. L’invio riesce, il calcolo termina normalmente e il chiamante non viene interrotto.

Il frammento è eseguito in un metodo che dichiara `throws Exception`, con `java.util.concurrent.ExecutorService`, `Executors` e `Future` importate. I numeri di riga non fanno parte del codice.

```java
01  try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
02      Future<Integer> result = executor.submit(() -> 6 * 7);
03      executor.shutdown();
04      IO.println(result.get());
05      IO.println(executor.isShutdown());
06  }
```

## 1. Scelta multipla

Che cosa accade? Seleziona una sola risposta.

- **A.** Stampa `42`, poi `false`.
- **B.** Solleva `RejectedExecutionException` perché `shutdown` annulla il task già inviato.
- **C.** Stampa `42`, poi `true`.
- **D.** Non compila perché `ExecutorService` non può essere usato in try-with-resources in Java 25.

## 2. Risposta breve

In un’esecuzione separata, con un executor ancora attivo, viene avviato questo task. `performStep()` è un metodo disponibile che termina ogni volta, non blocca e non modifica lo stato di interruzione.

```java
07  Future<?> job = executor.submit(() -> {
08      while (true) {
09          performStep();
10      }
11  });
12  job.cancel(true);
```

Supponi che il task sia già iniziato e che `cancel(true)` restituisca `true`. È garantito che il codice del task si sia fermato quando `cancel` ritorna? Motiva e indica una modifica minima al ciclo per rendere la terminazione cooperativa rispetto all’interruzione.

**Lunghezza indicativa:** una riga di codice e 2 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 3</summary>

### 1. Scelta multipla: C

`shutdown` non annulla i task già accettati; `get` attende il risultato `42`. L’executor ha ricevuto la richiesta di shutdown, quindi `isShutdown()` è `true`.

- **A errata:** lo shutdown è già stato richiesto.
- **B errata:** un task già inviato non viene rifiutato retroattivamente.
- **D errata:** in Java 25 `ExecutorService` è `AutoCloseable`.

### 2. Risposta breve

Il successo di `cancel(true)` non garantisce che il codice del task si sia già fermato: l’interruzione è cooperativa. Si può sostituire la condizione del ciclo con:

```java
while (!Thread.currentThread().isInterrupted()) {
```

Dato che `performStep()` termina e non cancella l’interruzione, il task potrà osservare il segnale e uscire a un controllo successivo; la cancellazione non è un’attesa della sua terminazione.

</details>

---

# Esercizio 4 — Avvio di un’attività

**Tempo indicativo: 5–7 minuti · Java 25**

Un’applicazione prepara un’attività che stampa un messaggio. Il chiamante crea un oggetto `Thread` per rappresentarla. Nella versione mostrata invoca direttamente `run`. Si vuole capire se l’operazione introduca un’esecuzione concorrente. Non ci sono altri thread applicativi che stampano o modificano lo stato osservato.

Frammento in un metodo. I numeri di riga non fanno parte del codice.

```java
01  Thread caller = Thread.currentThread();
02  Thread worker = new Thread(() -> {
03      IO.println(Thread.currentThread() == caller);
04  });
05  worker.run();
06  IO.println("End");
```

## 1. Scelta multipla

Seleziona l’output corretto.

- **A.** `false`, poi `End`.
- **B.** `End`, poi `true`.
- **C.** `true`, poi `End`.
- **D.** Le due righe possono uscire in qualsiasi ordine.

## 2. Risposta aperta

Proponi la sequenza minima che esegua l’attività in un nuovo thread e garantisca che `End` venga stampato dopo la sua conclusione. Non usare attese temporizzate e assumi che il chiamante possa dichiarare `throws InterruptedException`.

**Lunghezza indicativa:** massimo 3 righe di codice e una frase.

<details>
<summary>Mostra la soluzione dell’esercizio 4</summary>

### 1. Scelta multipla: C

`run()` è una chiamata ordinaria eseguita dal thread chiamante; la verifica d’identità restituisce `true`.

- **A errata:** non viene avviato un nuovo thread.
- **B errata:** la chiamata termina prima della stampa successiva.
- **D errata:** qui le operazioni avvengono sequenzialmente nello stesso thread.

### 2. Risposta aperta

```java
worker.start();
worker.join();
IO.println("End");
```

`start` avvia il nuovo thread e `join` ne attende la conclusione; la verifica nel worker diventa `false`. Non si deve chiamare anche `run` per ripetere il lavoro.

</details>

---

# Esercizio 5 — Pubblicazione di un risultato

**Tempo indicativo: 5–7 minuti · Java 25**

Un worker calcola un valore e lo salva in un oggetto condiviso. Il chiamante legge il risultato solo dopo aver atteso il worker. Il campo non usa `volatile` e non ci sono altre scritture. Il worker termina normalmente. Il chiamante non viene interrotto e può dichiarare `throws InterruptedException`.

Classe e istruzioni in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  class Result { int value; }
02  // In un altro metodo:
03  Result result = new Result();
04  Thread worker = new Thread(() -> result.value = 42);
05  worker.start();
06  worker.join();
07  IO.println(result.value);
```

## 1. Scelta multipla

Quale risultato è garantito? Seleziona una sola risposta.

- **A.** `0` oppure `42`, perché il campo non è `volatile`.
- **B.** `42`, grazie alla terminazione osservata tramite `join`.
- **C.** `0`, perché il campo appartiene al chiamante.
- **D.** Errore di compilazione per accesso da un altro thread.

## 2. Risposta breve

Un revisore sostituisce `join` con `Thread.sleep(100)`. Tale versione garantisce lo stesso risultato su ogni macchina? Distingui il tempo trascorso dall’osservazione del completamento.

**Lunghezza indicativa:** 2–3 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 5</summary>

### 1. Scelta multipla: B

Il completamento atteso con `join` stabilisce la relazione happens-before necessaria a rendere visibile la scrittura precedente del worker.

- **A errata:** `volatile` non è l’unico meccanismo di visibilità.
- **C errata:** l’oggetto è condiviso e il worker può modificarlo.
- **D errata:** Java permette accessi condivisi; la sincronizzazione è una responsabilità del programma.

### 2. Risposta breve

No: una pausa di cento millisecondi non garantisce che il worker abbia finito, né sostituisce la relazione stabilita da `join`. Lo scheduling può ritardarlo oltre la pausa. Serve attendere il completamento con un meccanismo appropriato, non stimarne la durata.

</details>

---

# Esercizio 6 — Aggiornamento di una coppia

**Tempo indicativo: 5–7 minuti · Java 25**

Un componente mantiene due contatori che devono avanzare insieme. Tutti gli aggiornamenti acquisiscono il monitor dell’oggetto. Dopo la fine degli aggiornamenti i due valori devono coincidere. Il chiamante crea due worker, ciascuno con una sola chiamata. Entrambi terminano normalmente, il chiamante non viene interrotto e non si considera overflow.

Classe e istruzioni in un metodo statico che dichiara `throws InterruptedException`. I numeri di riga non fanno parte del codice.

```java
01  class Pair {
02      private int first, second;
03      synchronized void advance() { first++; second++; }
04      synchronized boolean equal() { return first == second; }
05  }
06  // In un altro metodo:
07  Pair pair = new Pair();
08  Thread a = new Thread(pair::advance);
09  Thread b = new Thread(pair::advance);
10  a.start();
11  b.start();
12  a.join();
13  b.join();
14  IO.println(pair.equal());
```

## 1. Scelta multipla

Quale risultato è garantito? Seleziona una sola risposta.

- **A.** `false`, perché due thread eseguono incrementi.
- **B.** `true` solo se entrambi i campi sono `volatile`.
- **C.** Qualsiasi booleano, perché i thread possono alternarsi.
- **D.** `true`, perché ogni aggiornamento protegge la coppia con lo stesso monitor.

## 2. Risposta aperta

Una nuova API deve restituire insieme i due valori mentre gli aggiornamenti possono continuare. Proponi firma e strategia di un metodo che produca una fotografia coerente, senza esporre stato interno modificabile. Non servono altri tipi oltre a quelli standard o a un piccolo record.

**Lunghezza indicativa:** massimo 4 righe di codice e una frase.

<details>
<summary>Mostra la soluzione dell’esercizio 6</summary>

### 1. Scelta multipla: D

Le chiamate si escludono a vicenda sullo stesso oggetto; dopo i `join` entrambi gli incrementi di ciascun campo sono terminati.

- **A errata:** la concorrenza è protetta, non implica corruzione automatica.
- **B errata:** il monitor fornisce già la sincronizzazione necessaria.
- **C errata:** nessun aggiornamento parziale può essere osservato sotto quel monitor.

### 2. Risposta aperta

```java
record Snapshot(int first, int second) { }
synchronized Snapshot snapshot() {
    return new Snapshot(first, second);
}
```

La lettura dei due campi usa lo stesso monitor degli aggiornamenti e restituisce valori primitivi in un oggetto immutabile. È valido anche un nuovo array di due interi, costruito sotto quel monitor e non conservato come stato interno.

</details>

---

# Esercizio 7 — Due archivi condivisi

**Tempo indicativo: 5–7 minuti · Java 25**

Due worker devono accedere a due archivi protetti da monitor distinti. Ogni worker deve acquisire entrambi prima di svolgere il lavoro. Il programma può eseguire i worker in qualunque ordine. Non ci sono interruzioni o operazioni esterne che rilasciano forzatamente i monitor. Gli oggetti `left` e `right` sono distinti e condivisi da entrambi i worker.

Frammento in un metodo. I numeri di riga non fanno parte del codice.

```java
01  Object left = new Object();
02  Object right = new Object();
03  Thread a = new Thread(() -> {
04      synchronized (left) {
05          synchronized (right) { IO.println("A"); }
06      }
07  });
08  Thread b = new Thread(() -> {
09      synchronized (right) {
10          synchronized (left) { IO.println("B"); }
11      }
12  });
13  a.start();
14  b.start();
```

## 1. Scelta multipla

Quale affermazione è corretta? Seleziona una sola risposta.

- **A.** È possibile un deadlock se ciascuno acquisisce prima il proprio monitor esterno.
- **B.** Il deadlock è inevitabile a ogni esecuzione.
- **C.** `synchronized` impedisce qualsiasi deadlock.
- **D.** Il programma non compila perché i monitor sono locali.

## 2. Risposta breve

Indica una modifica alla politica di acquisizione che elimini questo ciclo d’attesa senza rimuovere la protezione dei due archivi. Spiega perché aggiungere `sleep` non darebbe la stessa garanzia.

**Lunghezza indicativa:** 2–3 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 7</summary>

### 1. Scelta multipla: A

Un worker può trattenere `left` aspettando `right`, mentre l’altro trattiene `right` aspettando `left`.

- **B errata:** uno dei due può acquisire entrambi e completare prima dell’altro.
- **C errata:** la mutua esclusione non impone un ordine globale dei monitor.
- **D errata:** i riferimenti locali sono effettivamente finali e possono essere catturati.

### 2. Risposta breve

Entrambi devono acquisire prima `left` e poi `right`, secondo un unico ordine condiviso. In questo modo non può formarsi il ciclo d’attesa tra questi due monitor. Una pausa può solo cambiare la probabilità di un interleaving, non eliminarlo dalle esecuzioni possibili.

</details>

---

# Esercizio 8 — Fallimento di un task

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio invia un task che può fallire. Il chiamante vuole osservare il fallimento recuperando il risultato del task. L’invio viene accettato e il worker esegue il codice mostrato. Il chiamante non viene interrotto. `ExecutionException` è il contenitore con cui `Future.get()` riporta il fallimento del task; `getCause()` restituisce l’eccezione originaria.

Frammento in un metodo che dichiara `throws InterruptedException`, con `java.util.concurrent.*` importata. I numeri di riga non fanno parte del codice.

```java
01  try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
02      Future<Integer> result = executor.submit(() -> {
03          throw new IllegalArgumentException("Bad input");
04      });
05      try {
06          IO.println(result.get());
07      } catch (ExecutionException exception) {
08          IO.println(exception.getCause().getMessage());
09      }
10  }
```

## 1. Scelta multipla

Che cosa accade? Seleziona una sola risposta.

- **A.** Stampa `null`.
- **B.** Il `catch` non vede errori avvenuti in un altro thread.
- **C.** Stampa `Bad input`.
- **D.** Non compila perché una lambda che lancia non può essere un `Callable<Integer>`.

## 2. Risposta breve

Se il chiamante ignora il `Future` e non ne recupera mai l’esito, il solo ritorno di `submit` dimostra il successo del calcolo? Indica dove deve essere gestito l’errore per poterlo riportare al client, senza introdurre stampe nel task.

**Lunghezza indicativa:** 2–3 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 8</summary>

### 1. Scelta multipla: C

`get` solleva `ExecutionException`; il gestore stampa il messaggio della causa `IllegalArgumentException`.

- **A errata:** un task fallito non produce un risultato nullo di successo.
- **B errata:** il `Future` trasporta esplicitamente l’esito tra i thread.
- **D errata:** un corpo che termina sempre con `throw` è compatibile con quel risultato.

### 2. Risposta breve

Il ritorno di `submit` dimostra solo che l’invio è stato accettato, non che il calcolo sia riuscito. Un livello che possiede il `Future` deve recuperarne l’esito e gestire o tradurre `ExecutionException`, conservando la causa. Ignorare il `Future` può lasciare il fallimento non osservato dal client.

</details>

---

# Esercizio 9 — Passaggio di un messaggio

**Tempo indicativo: 5–7 minuti · Java 25**

Un produttore consegna un messaggio a un consumatore mediante una coda bloccante di capacità uno. `put` attende spazio e `take` attende un elemento, rimuovendolo. I due task devono poter procedere senza polling. Nel frammento un solo worker deve eseguirli entrambi e non ci sono produttori esterni. Nessun thread viene interrotto e i task accettati vengono prima o poi eseguiti se il worker è libero.

Frammento in un metodo con `java.util.concurrent.*` importata. I numeri di riga non fanno parte del codice.

```java
01  BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
02  try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
03      executor.submit(() -> {
04          IO.println(queue.take());
05          return null;
06      });
07      executor.submit(() -> {
08          queue.put("Ready");
09          return null;
10      });
11  }
```

## 1. Scelta multipla

Quale comportamento descrive questo programma? Seleziona una sola risposta.

- **A.** Stampa sempre `Ready` e termina.
- **B.** Il consumatore attende nel primo task e impedisce al produttore di partire; la chiusura attende senza terminare.
- **C.** `take` restituisce `null` perché la coda è vuota.
- **D.** Non compila perché una coda bloccante richiede thread virtuali.

## 2. Risposta aperta

Per questo scambio finito di un solo messaggio, scegli un’organizzazione dell’executor che permetta a entrambi i task di procedere e indica come osservare l’eventuale fallimento dei due task. Non usare `sleep`, polling o un numero illimitato di worker.

**Lunghezza indicativa:** una dichiarazione e 2 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 9</summary>

### 1. Scelta multipla: B

Il primo task occupa l’unico worker aspettando un elemento che soltanto il secondo task potrebbe produrre. La chiusura ordinata attende task che non riescono a completare.

- **A errata:** il produttore non trova un worker libero.
- **C errata:** `take` attende, non segnala la coda vuota con `null`.
- **D errata:** le code bloccanti funzionano anche con thread di piattaforma.

### 2. Risposta aperta

```java
ExecutorService executor = Executors.newFixedThreadPool(2);
```

Con due worker, produttore e consumatore possono avanzare contemporaneamente; la capacità uno basta per il messaggio. Conservo entrambi i `Future` e ne recupero l’esito con `get`, gestendo interruzione e fallimenti; per protocolli più generali servirebbe anche definire come cancellare il task che resta in attesa se l’altro fallisce.

</details>

---

# Esercizio 10 — Attesa con scadenza

**Tempo indicativo: 5–7 minuti · Java 25**

Un client non vuole attendere indefinitamente il risultato di un task. Usa quindi un `get` con timeout su un `Future` già ottenuto da un executor attivo. Per questa esecuzione si assume esplicitamente che il timeout scada mentre il task è ancora in corso. Il task non viene cancellato da altri componenti e il chiamante non viene interrotto. `get(timeout, unit)` segnala lo scadere dell’attesa con `TimeoutException`, senza cancellare il calcolo.

Frammento in un metodo che dichiara `throws InterruptedException, ExecutionException`, con `java.util.concurrent.*` importata; `result` è un `Future<Integer>` già disponibile. I numeri di riga non fanno parte del codice.

```java
01  try {
02      IO.println(result.get(1, TimeUnit.MILLISECONDS));
03  } catch (TimeoutException exception) {
04      IO.println("Timeout");
05  }
06  IO.println(result.isCancelled());
```

## 1. Scelta multipla

Sotto le assunzioni date, seleziona l’output corretto.

- **A.** `Timeout`, poi `false`.
- **B.** `Timeout`, poi `true`.
- **C.** Soltanto `Timeout`, perché il client termina automaticamente.
- **D.** Il risultato numerico, poi `false`.

## 2. Risposta breve

Il client propone `result.cancel(true)` nel `catch` e sostiene che questo annulli anche tutti gli effetti già prodotti dal task. Valuta la proposta distinguendo richiesta di cancellazione, cooperazione del task e operazioni già completate.

**Lunghezza indicativa:** 2–3 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 10</summary>

### 1. Scelta multipla: A

Il timeout termina questa attesa, non il task. In assenza di cancellazioni, `isCancelled()` resta `false`, anche se nel frattempo il task completa.

- **B errata:** il timeout non cancella automaticamente.
- **C errata:** il `catch` termina normalmente e si esegue la stampa successiva.
- **D errata:** l’ipotesi della consegna esclude il ritorno di un valore da questa chiamata a `get`.

### 2. Risposta breve

`cancel(true)` tenta di cancellare e, se opportuno, interrompere il task; il codice deve cooperare per fermarsi. Non riporta indietro effetti già completati, come una scrittura esterna. Se il task nel frattempo è terminato, la cancellazione può anche essere rifiutata: timeout e rollback sono contratti diversi.

</details>
