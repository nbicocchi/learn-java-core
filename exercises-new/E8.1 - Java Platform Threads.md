# Esercizio 1 — Conteggio degli eventi

**Tempo indicativo: 5–7 minuti · Java 25**

Due thread condividono un contatore inizialmente pari a zero. Ogni chiamata a `increment` deve aumentarlo esattamente di uno. Entrambi i thread terminano normalmente; il thread che esegue il frammento non viene interrotto.

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
13  System.out.println(counter.get());
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

---

# Esercizio 2 — Apertura di un varco

**Tempo indicativo: 5–7 minuti · Java 25**

Il varco parte chiuso e, una volta aperto, rimane aperto. `await` deve ritornare soltanto quando il varco è aperto. Il worker non viene interrotto, termina normalmente e tutti i thread vengono prima o poi eseguiti.

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
15      System.out.println("Passed");
16  });
17  worker.start();
18  gate.open();
19  worker.join();
20  System.out.println("End");
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

---

# Esercizio 3 — Risultato di un’elaborazione

**Tempo indicativo: 5–7 minuti · Java 25**

Un executor esegue un calcolo e il chiamante ne recupera il risultato. L’invio del task riesce, il calcolo termina normalmente e il chiamante non viene interrotto.

Il frammento è eseguito in un metodo che dichiara `throws Exception`, con `java.util.concurrent.ExecutorService`, `Executors` e `Future` importate. I numeri di riga non fanno parte del codice.

```java
01  try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
02      Future<Integer> result = executor.submit(() -> 6 * 7);
03      executor.shutdown();
04      System.out.println(result.get());
05      System.out.println(executor.isShutdown());
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
