# Esercizio 1 — Destinatari di un avviso

**Tempo indicativo: 5–7 minuti · Java 25**

L’applicazione raccoglie nomi non nulli. L’uguaglianza è sensibile a maiuscole e minuscole. Il frammento è eseguito in un metodo con gli import indicati; i numeri di riga non fanno parte del codice.

```java
01  import java.util.ArrayList;
02  import java.util.List;
```

```java
03  List<String> input = List.of("Ada", "Linus", "Ada", "Grace");
04  List<String> recipients = new ArrayList<>(input);
05  recipients.remove("Ada");
06  System.out.println(recipients);
07  System.out.println(input.size());
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

---

# Esercizio 2 — Visite a una pagina

**Tempo indicativo: 5–7 minuti · Java 25**

Una mappa associa ogni pagina visitata al relativo numero di visite. `visit` deve incrementare il conteggio oppure creare una voce pari a 1 per una pagina nuova. Mappa e nomi sono non nulli, i conteggi presenti sono piccoli interi non negativi e mai nulli. Il programma è monothread.

Il metodo va dichiarato nel corpo di una classe e le istruzioni in un suo metodo statico, con `java.util.Map` e `java.util.HashMap` importate. `put` restituisce il valore precedente associato alla chiave, oppure `null` se non esisteva. I numeri di riga non fanno parte del codice.

```java
01  static void visit(Map<String, Integer> counts, String page) {
02      counts.put(page, counts.get(page) + 1);
03  }
```

```java
04  Map<String, Integer> counts = new HashMap<>();
05  System.out.println(counts.put("home", 1));
06  System.out.println(counts.put("home", 2));
07  System.out.println(counts.size());
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

---

# Esercizio 3 — Gestione delle richieste

**Tempo indicativo: 5–7 minuti · Java 25**

Le richieste sono identificate da nomi non nulli. La prima versione le gestisce in ordine di arrivo. Tutte le operazioni avvengono nello stesso thread.

Il frammento è eseguito in un metodo con `java.util.Queue` e `java.util.ArrayDeque` importate. I numeri di riga non fanno parte del codice.

```java
01  Queue<String> pending = new ArrayDeque<>();
02  pending.offer("A");
03  pending.offer("B");
04  System.out.println(pending.poll());
05  pending.offer("C");
06  System.out.println(pending.peek());
07  System.out.println(pending.size());
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
