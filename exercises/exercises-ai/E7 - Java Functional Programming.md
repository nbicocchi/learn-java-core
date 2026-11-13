# Esercizio 1 — Controllo di una misura

**Tempo indicativo: 5–7 minuti · Java 25**

Un laboratorio costruisce regole riutilizzabili per validare le misure. Una misura è ammessa se è non negativa e minore di una soglia. Ogni regola viene creata con una soglia fissa e può essere usata più volte. Le misure sono interi non nulli e le soglie sono piccoli interi. `Predicate.and` produce la congiunzione dei due predicati, senza modificare quelli originali.

Il metodo va dichiarato nel corpo di una classe, le istruzioni in un suo metodo statico; è importata `java.util.function.Predicate`. I numeri di riga non fanno parte del codice.

```java
01  static Predicate<Integer> below(int limit) {
02      return value -> value < limit;
03  }
```

```java
04  Predicate<Integer> nonNegative = value -> value >= 0;
05  Predicate<Integer> allowed = nonNegative.and(below(5));
06  IO.println(allowed.test(-1));
07  IO.println(allowed.test(4));
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le due righe nell’ordine.

- **A.** `true`, poi `true`.
- **B.** `false`, poi `false`.
- **C.** `true`, poi `false`.
- **D.** `false`, poi `true`.

## 2. Risposta breve

Un revisore propone questo corpo alternativo per `below`:

```java
08  Predicate<Integer> rule = value -> value < limit;
09  limit++;
10  return rule;
```

Valuta se sia compilabile e rispetti il contratto. Il revisore sostiene che la lambda conserverebbe automaticamente il valore di `limit` precedente all’incremento: giustifica o confuta questa spiegazione.

**Lunghezza indicativa:** 2–3 frasi; non occorre riscrivere il metodo.

<details>
<summary>Mostra la soluzione dell’esercizio 1</summary>

### 1. Scelta multipla: D

`-1` non soddisfa la non negatività; `4` soddisfa entrambe le condizioni.

- **A errata:** la prima condizione rifiuta `-1`.
- **B errata:** `4` è minore di `5` e non negativo.
- **C errata:** inverte entrambi i risultati.

### 2. Risposta breve

La variante non compila: `limit` è un parametro locale catturato dalla lambda e non è più effettivamente finale perché viene incrementato. Non viene automaticamente creata una fotografia che consenta di ignorare la riassegnazione. La versione originale, senza incremento, compila e conserva la soglia richiesta.

</details>

---

# Esercizio 2 — Nome da visualizzare

**Tempo indicativo: 5–7 minuti · Java 25**

Una schermata riceve un nome eventualmente assente. Il metodo `display` deve restituire il valore presente senza calcolare un sostituto. Soltanto in sua assenza deve invocare una volta il fornitore del nome di riserva. Il fornitore può svolgere un calcolo costoso o avere effetti osservabili. L’`Optional`, il fornitore e le stringhe prodotte sono non nulli.

Il metodo va dichiarato nel corpo di una classe e le istruzioni in un suo metodo statico, con `java.util.Optional` e `java.util.function.Supplier` importate. I numeri di riga non fanno parte del codice.

```java
01  static String display(Optional<String> name, Supplier<String> fallback) {
02      return name.orElse(fallback.get());
03  }
```

```java
04  Optional<String> name = Optional.of(" Ada ");
05  Optional<Integer> length = name.map(String::strip).map(String::length);
06  IO.println(length.orElse(-1));
07  IO.println("[" + name.get() + "]");
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le due righe nell’ordine.

- **A.** `5`, poi `[ Ada ]`.
- **B.** `3`, poi `[Ada]`.
- **C.** `-1`, poi `[ Ada ]`.
- **D.** `3`, poi `[ Ada ]`.

## 2. Risposta breve

Valuta separatamente `display(Optional.of("Ada"), fallback)` e `display(Optional.empty(), fallback)` rispetto al contratto. Se necessario, correggi il corpo del metodo indicando quando verrà invocato il fornitore.

**Lunghezza indicativa:** una riga di codice e 1–2 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 2</summary>

### 1. Scelta multipla: D

I due `map` producono un nuovo `Optional` contenente `3`; `name` conserva il testo originale con spazi.

- **A errata:** la lunghezza è calcolata dopo `strip`.
- **B errata:** le trasformazioni non cambiano il valore nell’`Optional` originale.
- **C errata:** il valore trasformato è presente, quindi non si usa `-1`.

### 2. Risposta breve

```java
return name.orElseGet(fallback);
```

La versione originale invoca `fallback.get()` prima della chiamata a `orElse`, anche con nome presente, violando il contratto. `orElseGet` invoca il fornitore soltanto per l’assenza, una volta per questa chiamata.

</details>

---

# Esercizio 3 — Trasformazioni di un’etichetta

**Tempo indicativo: 5–7 minuti · Java 25**

Un componente prepara i dati prima della visualizzazione. I client possono combinare trasformazioni elementari in un ordine scelto. La versione attuale del metodo testuale applica sempre la stessa pulizia. Un frammento numerico separato mostra l’effetto dell’ordine di composizione. I valori sono piccoli e senza overflow; testi, trasformazioni e risultati testuali sono non nulli.

Il metodo va dichiarato nel corpo di una classe e le istruzioni in un suo metodo statico, con `java.util.function.Function` importata. `f.andThen(g)` applica prima `f` e poi `g`; `f.compose(g)` applica prima `g` e poi `f`. I numeri di riga non fanno parte del codice.

```java
01  static String format(String text) {
02      return text.strip();
03  }
```

```java
04  Function<Integer, Integer> increment = value -> value + 1;
05  Function<Integer, Integer> twice = value -> 2 * value;
06  IO.println(increment.andThen(twice).apply(3));
07  IO.println(increment.compose(twice).apply(3));
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le due righe nell’ordine.

- **A.** `7`, poi `8`.
- **B.** `8`, poi `7`.
- **C.** `8`, poi `8`.
- **D.** `7`, poi `7`.

## 2. Risposta aperta

Una nuova versione di `format` deve ricevere dal chiamante la trasformazione testuale da usare, invece di fissare sempre `strip`. La trasformazione riceve il testo e restituisce il testo formattato: non deve stamparlo o modificarne contenitori esterni. Deve poter essere fornita con una lambda o un riferimento a metodo.

Scegli un’interfaccia funzionale standard, definisci la nuova firma e il corpo minimo di `format` e motiva il tipo scelto. Non introdurre nuove interfacce o classi concrete.

**Lunghezza indicativa:** massimo 3 righe di codice e una frase. Puoi omettere eventuali nuovi import.

<details>
<summary>Mostra la soluzione dell’esercizio 3</summary>

### 1. Scelta multipla: B

`andThen` calcola prima `3 + 1`, poi raddoppia ottenendo `8`; `compose` raddoppia `3` e poi incrementa ottenendo `7`.

- **A errata:** scambia i due ordini.
- **C errata:** la composizione non equivale sempre alla sequenza nell’ordine di scrittura.
- **D errata:** il primo calcolo incrementa prima del raddoppio.

### 2. Risposta aperta

```java
static String format(String text, Function<String, String> operation) {
    return operation.apply(text);
}
```

`Function` esprime un ingresso e un risultato; `Consumer` non restituisce un testo. È valido anche `UnaryOperator<String>`, specializzazione per ingresso e risultato dello stesso tipo.

</details>

---

# Esercizio 4 — Nomi selezionati

**Tempo indicativo: 5–7 minuti · Java 25**

Una schermata deve consultare i nomi che iniziano con `A`. La selezione deve preservare l’ordine e non modificare la lista originale. Il filtro stampa un messaggio diagnostico quando viene valutato, così da osservare quando il lavoro è eseguito. La prima richiesta riguarda soltanto il primo nome selezionato. I nomi sono non nulli e lo stream è sequenziale.

Il frammento è eseguito in un metodo con `java.util.List` e `java.util.stream.Stream` importate. I numeri di riga non fanno parte del codice.

```java
01  List<String> names = List.of("Ada", "Linus", "Anna");
02  Stream<String> selected = names.stream()
03      .filter(name -> {
04          IO.println("Check " + name);
05          return name.startsWith("A");
06      });
07  IO.println("Ready");
08  IO.println(selected.findFirst().orElse("None"));
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le righe nell’ordine.

- **A.** `Ready`, poi `Check Ada`, poi `Ada`.
- **B.** `Check Ada`, poi `Ready`, poi `Ada`.
- **C.** `Ready`, poi `Check Ada`, `Check Linus`, `Check Anna`, infine `Ada`.
- **D.** `Ready`, poi `None`.

## 2. Risposta breve

In un’esecuzione separata, si mantengono le righe 1–6 ma si sostituiscono le righe 7–8 con:

```java
09  IO.println(selected.count());
10  IO.println(selected.toList());
```

Valuta se il programma stampi sia il numero sia la lista completa dei nomi selezionati. Se necessario, riscrivi soltanto le due nuove righe affinché il filtro venga eseguito una sola volta e siano disponibili entrambi i risultati. Le stampe diagnostiche del filtro possono rimanere.

**Lunghezza indicativa:** una frase e massimo 3 righe di codice.

<details>
<summary>Mostra la soluzione dell’esercizio 4</summary>

### 1. Scelta multipla: A

La pipeline è lazy: prima si stampa `Ready`; `findFirst` valuta il primo elemento, stampa `Check Ada` e restituisce `Ada` senza cercare oltre.

- **B errata:** la sola costruzione della pipeline non esegue il filtro.
- **C errata:** `findFirst` interrompe la ricerca al primo risultato.
- **D errata:** esiste un elemento che soddisfa il filtro.

### 2. Risposta breve

Il terminale `count` consuma lo stream; non è possibile usarlo nuovamente per ottenere la lista. Si materializza una volta il risultato:

```java
List<String> result = selected.toList();
IO.println(result.size());
IO.println(result);
```

I messaggi diagnostici precedono le due stampe finali; il filtro non viene eseguito due volte.

</details>

---

# Esercizio 5 — Autori dei gruppi

**Tempo indicativo: 5–7 minuti · Java 25**

Un’organizzazione raccoglie gli autori presenti in diversi gruppi di lavoro. Ogni gruppo contiene una lista di nomi. Il riepilogo deve produrre ciascun nome una sola volta, nell’ordine della prima comparsa tra i gruppi. Le liste originali devono restare disponibili e non devono essere modificate. Nomi e liste sono non nulli e lo stream è sequenziale.

Il frammento è eseguito in un metodo con `java.util.List` importata. I numeri di riga non fanno parte del codice.

```java
01  List<List<String>> groups = List.of(
02      List.of("Ada", "Linus"),
03      List.of("Ada", "Grace"));
04  List<String> result = groups.stream()
05      .flatMap(List::stream)
06      .distinct()
07      .toList();
08  IO.println(result);
```

## 1. Scelta multipla

Qual è l’output? Seleziona una sola risposta.

- **A.** `[[Ada, Linus], [Ada, Grace]]`.
- **B.** `[Ada, Linus, Ada, Grace]`.
- **C.** `[Ada, Linus, Grace]`.
- **D.** `[Ada, Grace, Linus]`.

## 2. Risposta breve

Un nuovo chiamante vuole ordinare `result` sul posto e aggiungervi successivamente un nome. Valuta se il risultato attuale lo consenta. Se necessario, modifica soltanto la costruzione del risultato, mantenendo la selezione e la sua indipendenza dalle liste originali.

**Lunghezza indicativa:** 1–2 frasi e massimo 5 righe di codice. Puoi omettere gli import aggiuntivi.

<details>
<summary>Mostra la soluzione dell’esercizio 5</summary>

### 1. Scelta multipla: C

`flatMap` visita i nomi dei gruppi in ordine; `distinct` mantiene la prima occorrenza di ogni stringa nello stream ordinato.

- **A errata:** il risultato non contiene più liste annidate.
- **B errata:** il duplicato `Ada` viene eliminato.
- **D errata:** `Linus` precede `Grace` nell’ordine di incontro.

### 2. Risposta breve

`Stream.toList()` restituisce una lista non modificabile. Una costruzione conforme è:

```java
List<String> result = groups.stream()
    .flatMap(List::stream)
    .distinct()
    .collect(java.util.stream.Collectors.toCollection(java.util.ArrayList::new));
```

È valida anche una nuova `ArrayList` costruita dal risultato originale. Non si deve presumere che `Collectors.toList()` garantisca una particolare implementazione o la modificabilità richiesta.

</details>

---

# Esercizio 6 — Totali delle misure

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio prepara totali a partire da misure già raccolte. La prima elaborazione ignora quelle negative e raddoppia le altre. Un secondo riepilogo deve considerare invece tutti gli elementi. Entrambe le elaborazioni devono lasciare invariata la lista di partenza. Le misure sono interi non nulli tra -100 e 100 e le liste contengono al massimo cento elementi, quindi non ci sono problemi di overflow.

Il frammento è eseguito in un metodo con `java.util.List` importata. I numeri di riga non fanno parte del codice.

```java
01  List<Integer> values = List.of(2, -1, 4);
02  int total = values.stream()
03      .filter(value -> value >= 0)
04      .mapToInt(value -> 2 * value)
05      .sum();
06  IO.println(total);
```

## 1. Scelta multipla

Qual è l’output? Seleziona una sola risposta.

- **A.** `6`.
- **B.** `10`.
- **C.** `5`.
- **D.** `12`.

## 2. Risposta breve

Separatamente, si vuole calcolare il negativo della somma di tutti gli elementi, anche con uno stream parallelo. Un revisore propone:

```java
07  int negativeTotal = values.parallelStream()
08      .reduce(0, (left, right) -> left - right);
```

Valuta se la riduzione garantisca il risultato richiesto. Motiva considerando il raggruppamento delle sottrazioni, con un piccolo esempio, e proponi un’espressione alternativa valida anche per una lista vuota.

**Lunghezza indicativa:** 2 frasi, un esempio numerico e una riga di codice.

<details>
<summary>Mostra la soluzione dell’esercizio 6</summary>

### 1. Scelta multipla: D

Restano `2` e `4`, trasformati in `4` e `8`: il totale è `12`.

- **A errata:** omette il raddoppio.
- **B errata:** include la misura negativa.
- **C errata:** somma l’input senza filtro né trasformazione.

### 2. Risposta breve

La sottrazione non è associativa e lo zero non è un’identità a sinistra: `(0 - 2) - 3 = -5`, mentre `(0 - 2) - (0 - 3) = 1`. I raggruppamenti della riduzione parallela non garantiscono il risultato richiesto; una forma corretta, anche sull’input vuoto, è:

```java
int negativeTotal = -values.parallelStream().mapToInt(Integer::intValue).sum();
```

</details>

---

# Esercizio 7 — Ordine di selezione

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio estrae un numero limitato di misure valide. Le misure devono essere positive e conservare il loro ordine. Il chiamante richiede al massimo due risultati. La sorgente è una lista non nulla di interi non nulli. Lo stream è sequenziale e nessuna elaborazione modifica la lista.

Frammento in un metodo con `java.util.List` importata. I numeri di riga non fanno parte del codice.

```java
01  List<Integer> values = List.of(-1, 2, 3, 4);
02  List<Integer> result = values.stream()
03      .limit(2)
04      .filter(value -> value > 0)
05      .toList();
06  IO.println(result);
```

## 1. Scelta multipla

Qual è l’output? Seleziona una sola risposta.

- **A.** `[2, 3]`.
- **B.** `[-1, 2]`.
- **C.** `[2, 3, 4]`.
- **D.** `[2]`.

## 2. Risposta breve

La richiesta è «le prime due misure positive, o tutte quelle disponibili se meno di due». Valuta l’ordine attuale delle operazioni e proponi la modifica minima. Che risultato deve avere una sorgente di soli valori non positivi?

**Lunghezza indicativa:** 2 frasi oppure una frase e 2 righe di pipeline.

<details>
<summary>Mostra la soluzione dell’esercizio 7</summary>

### 1. Scelta multipla: D

`limit` seleziona prima `-1` e `2`; il filtro elimina `-1` e resta solo `2`.

- **A errata:** richiederebbe il filtro prima del limite.
- **B errata:** il filtro rifiuta `-1`.
- **C errata:** il limite impedisce di raggiungere tutti gli elementi.

### 2. Risposta breve

Scambio le operazioni: prima `filter(value -> value > 0)`, poi `limit(2)`. Così il limite conta i risultati validi, non gli elementi iniziali; in assenza di positivi si ottiene una lista vuota.

</details>

---

# Esercizio 8 — Quantità per categoria

**Tempo indicativo: 5–7 minuti · Java 25**

Un negozio riceve una sequenza di categorie per i prodotti venduti. Il riepilogo deve contare quante vendite appartengono a ciascuna categoria. Due stringhe uguali indicano la stessa categoria. Non interessa l’ordine delle chiavi della mappa risultante. La sorgente contiene stringhe non nulle e non deve essere modificata.

Frammento in un metodo con `java.util.*`, `java.util.function.Function` e `java.util.stream.Collectors` importate. `groupingBy` raggruppa per la chiave calcolata; `counting` conta gli elementi di ogni gruppo con risultato `Long`. I numeri di riga non fanno parte del codice.

```java
01  List<String> categories = List.of("book", "pen", "book");
02  Map<String, Long> counts = categories.stream()
03      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
04  IO.println(counts.get("book"));
05  IO.println(counts.size());
```

## 1. Scelta multipla

Seleziona l’output corretto.

- **A.** `1`, poi `3`.
- **B.** `2`, poi `2`.
- **C.** `2`, poi `3`.
- **D.** Eccezione per la chiave duplicata `book`.

## 2. Risposta aperta

Il chiamante vuole una funzione riutilizzabile `countBy` che riceva una lista di stringhe e una regola per calcolare la categoria di ciascuna stringa. Proponi la firma con un’interfaccia funzionale standard e descrivi come riusare il collector; regola e risultati sono non nulli.

**Lunghezza indicativa:** una firma e 1–2 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 8</summary>

### 1. Scelta multipla: B

Due elementi appartengono a `book`; le categorie distinte sono due.

- **A errata:** raggruppare non elimina il contributo delle ripetizioni al conteggio.
- **C errata:** la dimensione della mappa conta le chiavi, non gli input.
- **D errata:** il collector gestisce proprio gruppi con la stessa chiave.

### 2. Risposta aperta

`static Map<String, Long> countBy(List<String> values, Function<String, String> category)` usa `groupingBy(category, counting())` al posto della funzione identità. La funzione riceve l’elemento e restituisce la sua chiave; sono valide wildcard compatibili nella firma.

</details>

---

# Esercizio 9 — Scelta di un risultato

**Tempo indicativo: 5–7 minuti · Java 25**

Un componente cerca il primo nome sufficientemente lungo. L’assenza di risultati è prevista e non deve essere trattata come un guasto. Il chiamante vuole sempre una stringa da visualizzare. La lista è non nulla e contiene stringhe non nulle. Lo stream è sequenziale e segue l’ordine della lista.

Frammento in un metodo con `java.util.*` importata. I numeri di riga non fanno parte del codice.

```java
01  List<String> names = List.of("Ada", "Bo");
02  Optional<String> found = names.stream()
03      .filter(name -> name.length() > 5)
04      .findFirst();
05  IO.println(found.isPresent());
06  IO.println(found.orElse("None"));
```

## 1. Scelta multipla

Seleziona l’output corretto.

- **A.** `false`, poi `None`.
- **B.** `true`, poi `Ada`.
- **C.** `false`, poi `null`.
- **D.** Solleva `NoSuchElementException`.

## 2. Risposta aperta

Una variante deve visualizzare la lunghezza del nome trovato oppure zero se assente, senza `get()` né un controllo esplicito con `if`. Proponi l’espressione e spiega perché gestisce entrambe le situazioni.

**Lunghezza indicativa:** una riga di codice e 1–2 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 9</summary>

### 1. Scelta multipla: A

Nessun nome supera cinque caratteri: l’`Optional` è vuoto e si usa il testo di riserva.

- **B errata:** `Ada` non supera la soglia.
- **C errata:** il ripiego esplicito è `None`, non `null`.
- **D errata:** `orElse` gestisce l’assenza senza leggere con `get()`.

### 2. Risposta aperta

```java
int length = found.map(String::length).orElse(0);
```

`map` trasforma solo il valore presente; `orElse` fornisce zero quando il risultato è assente. Non occorre una lettura non protetta.

</details>

---

# Esercizio 10 — Riepilogo dei codici

**Tempo indicativo: 5–7 minuti · Java 25**

Un esportatore deve produrre una stringa con i codici ricevuti. I codici vanno separati da una virgola, senza separatore iniziale o finale. La lista può essere vuota e in quel caso il risultato deve essere una stringa vuota. I codici sono stringhe non nulle e non contengono virgole. Il codice deve funzionare anche se la pipeline viene resa parallela.

Frammento in un metodo con `java.util.List` e `java.util.stream.Collectors` importate. I numeri di riga non fanno parte del codice.

```java
01  List<String> codes = List.of("A", "B", "C");
02  String text = codes.stream()
03      .map(code -> code.toLowerCase(java.util.Locale.ROOT))
04      .collect(Collectors.joining(","));
05  IO.println(text);
```

## 1. Scelta multipla

Qual è l’output? Seleziona una sola risposta.

- **A.** `,a,b,c`.
- **B.** `a,b,c,`.
- **C.** `a,b,c`.
- **D.** `[a, b, c]`.

## 2. Risposta breve

Un revisore usa un unico `StringBuilder` esterno, condiviso dalle lambda di un `parallelStream().forEach(...)`, per aggiungere codici e virgole. Spiega due ragioni per cui tale versione non garantisce il contratto, senza proporre sincronizzazione. Quale parte della versione mostrata evita la necessità di uno stato esterno condiviso?

**Lunghezza indicativa:** 2–3 frasi.

<details>
<summary>Mostra la soluzione dell’esercizio 10</summary>

### 1. Scelta multipla: C

`joining` inserisce la virgola soltanto tra gli elementi; i codici sono trasformati in minuscolo.

- **A errata:** non viene aggiunto un delimitatore iniziale.
- **B errata:** non viene aggiunto un delimitatore finale.
- **D errata:** il risultato è una stringa unita, non la rappresentazione di una lista.

### 2. Risposta breve

Il builder condiviso non è sicuro per modifiche concorrenti e `forEach` parallelo non garantisce l’ordine della sorgente. Inoltre una semplice aggiunta della virgola dopo ogni codice lascerebbe un separatore finale. Il collector `joining` gestisce accumulazione, combinazione e delimitatori senza esporre un unico builder esterno alle lambda; preserva l’ordine di incontro dello stream ordinato.

</details>

---

# Esercizio 11 — Aggiornamento dei conti scaduti

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio seleziona i conti con scadenza strettamente precedente alla data di riferimento. Deve aumentare del 10% il saldo dei soli conti selezionati, modificando gli oggetti originali, e restituirli in ordine di saldo aggiornato decrescente. I conti non scaduti devono rimanere invariati. Ogni test prepara nuovi oggetti e invoca il servizio una sola volta.

Liste, conti e date sono non nulli; i saldi sono centesimi interi positivi multipli di 100, senza overflow. Lo stream è sequenziale. `peek` esegue l'azione sugli elementi che attraversano quello stadio; `filter` lascia passare quelli che soddisfano il predicato, `sorted` li ordina e `toList` raccoglie i riferimenti senza copiare gli oggetti. Il frammento appartiene a una classe di test, con `Account` come classe annidata statica. `@Test` identifica un test e `assertEquals(atteso, effettivo)` confronta valori o contenuto e ordine delle liste. Import omessi; i numeri di riga non fanno parte del codice.

```java
01  static class Account {
02      int balance;
03      final LocalDate due;
04      Account(int balance, LocalDate due) {
05          this.balance = balance;
06          this.due = due;
07      }
08  }
09  static List<Account> applyInterest(List<Account> accounts, LocalDate today) {
10      return accounts.stream()
11              .peek(a -> a.balance += a.balance / 10)
12              .filter(a -> a.due.isBefore(today))
13              .sorted(Comparator.comparingInt((Account a) -> a.balance).reversed())
14              .toList();
15  }
16  @Test
17  void selectedAccounts() {
18      LocalDate today = LocalDate.of(2026, 5, 10);
19      Account old = new Account(100, today.minusDays(1));
20      Account current = new Account(200, today.plusDays(1));
21      List<Account> selected = applyInterest(List.of(old, current), today);
22      assertEquals(List.of(old), selected);
23      assertEquals(110, old.balance);
24  }
25  @Test
26  void currentBalance() {
27      LocalDate today = LocalDate.of(2026, 5, 10);
28      Account current = new Account(200, today.plusDays(1));
29      applyInterest(List.of(current), today);
30      assertEquals(200, current.balance);
31  }
```

## 1. Scelta multipla

Qual è l'esito dei test?

- **A.** Passano entrambi: `filter` impedisce ogni modifica ai conti esclusi.
- **B.** Falliscono entrambi perché `toList` crea copie indipendenti dei conti.
- **C.** Passa soltanto `selectedAccounts`; in `currentBalance` il saldo effettivo è `220`.
- **D.** Passa soltanto `currentBalance`; `peek` non viene eseguito quando si usa `toList`.

## 2. Risposta aperta

Il test `selectedAccounts` verifica anche il requisito sull'ordinamento? In **1–3 frasi**, proponi dati aggiuntivi e l'ordine atteso che permettano di verificarlo, senza scrivere codice.

<details>
<summary>Mostra la soluzione dell’esercizio 11</summary>

### 1. Scelta multipla: C

`toList` consuma la pipeline e il `peek` precedente al filtro aumenta il saldo anche dei conti che saranno esclusi. Il primo test osserva soltanto la selezione e il saldo del conto scaduto, entrambi corretti; nel secondo il conto non scaduto passa da `200` a `220`, violando il contratto.

- **A errata:** il filtro non annulla gli effetti già avvenuti negli stadi precedenti.
- **B errata:** `toList` raccoglie gli stessi riferimenti, non copie indipendenti dei conti.
- **D errata:** `toList` è un'operazione terminale e, in questa pipeline, gli elementi attraversano `peek` prima della selezione.

### 2. Risposta aperta

No: una lista con un solo conto selezionato non distingue ordinamenti diversi. Si possono inserire nell'ordine due conti entrambi scaduti, con saldi `100` e `300`: dopo l'aggiornamento devono comparire prima quello con `330`, poi quello con `110`; l'implementazione mostrata soddisfa questa verifica, pur non rispettando il requisito sui conti esclusi.

**Alternativa valida:** altri saldi distinti di conti scaduti, inizialmente non già in ordine decrescente, con saldi aggiornati e ordine atteso corretti.

</details>
