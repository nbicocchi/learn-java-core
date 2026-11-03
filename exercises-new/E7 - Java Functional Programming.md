> **E7 — Java Functional Programming** · [Indice del corso](../README.md#modules)
> Gli esercizi sono organizzati per obiettivo principale e possono richiamare competenze dei moduli precedenti. La numerazione è indipendente per ciascun macro-modulo.


---

# Esercizio 1 — Controllo di una misura

**Tempo indicativo: 5–7 minuti · Java 25**

Una misura è valida se è non negativa e minore di una soglia. Ogni regola viene creata con una soglia fissa; misure e soglie sono piccoli interi. `Predicate.and` produce la congiunzione dei due predicati, senza modificare quelli originali.

Il metodo va dichiarato nel corpo di una classe, le istruzioni in un suo metodo statico; è importata `java.util.function.Predicate`. I numeri di riga non fanno parte del codice.

```java
01  static Predicate<Integer> below(int limit) {
02      return value -> value < limit;
03  }
```

```java
04  Predicate<Integer> nonNegative = value -> value >= 0;
05  Predicate<Integer> allowed = nonNegative.and(below(5));
06  System.out.println(allowed.test(-1));
07  System.out.println(allowed.test(4));
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

---

# Esercizio 2 — Nome da visualizzare

**Tempo indicativo: 5–7 minuti · Java 25**

Un nome può essere presente o assente. `display` deve restituire il valore presente; soltanto in sua assenza deve invocare una volta il fornitore del nome di riserva. L’`Optional`, il fornitore e le stringhe prodotte sono non nulli. Il fornitore può svolgere un calcolo costoso o avere effetti osservabili.

Il metodo va dichiarato nel corpo di una classe e le istruzioni in un suo metodo statico, con `java.util.Optional` e `java.util.function.Supplier` importate. I numeri di riga non fanno parte del codice.

```java
01  static String display(Optional<String> name, Supplier<String> fallback) {
02      return name.orElse(fallback.get());
03  }
```

```java
04  Optional<String> name = Optional.of(" Ada ");
05  Optional<Integer> length = name.map(String::strip).map(String::length);
06  System.out.println(length.orElse(-1));
07  System.out.println("[" + name.get() + "]");
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

---

# Esercizio 3 — Trasformazioni di un’etichetta

**Tempo indicativo: 5–7 minuti · Java 25**

Il componente costruisce trasformazioni da applicare ai dati prima della visualizzazione. I valori numerici sono piccoli; non considerare overflow. Testi, trasformazioni e risultati testuali sono non nulli.

Il metodo va dichiarato nel corpo di una classe e le istruzioni in un suo metodo statico, con `java.util.function.Function` importata. `f.andThen(g)` applica prima `f` e poi `g`; `f.compose(g)` applica prima `g` e poi `f`. I numeri di riga non fanno parte del codice.

```java
01  static String format(String text) {
02      return text.strip();
03  }
```

```java
04  Function<Integer, Integer> increment = value -> value + 1;
05  Function<Integer, Integer> twice = value -> 2 * value;
06  System.out.println(increment.andThen(twice).apply(3));
07  System.out.println(increment.compose(twice).apply(3));
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

---

# Esercizio 4 — Nomi selezionati

**Tempo indicativo: 5–7 minuti · Java 25**

Si vogliono selezionare i nomi che iniziano con `A`, preservandone l’ordine e senza modificare la lista originale. I nomi sono non nulli e lo stream è sequenziale.

Il frammento è eseguito in un metodo con `java.util.List` e `java.util.stream.Stream` importate. I numeri di riga non fanno parte del codice.

```java
01  List<String> names = List.of("Ada", "Linus", "Anna");
02  Stream<String> selected = names.stream()
03      .filter(name -> {
04          System.out.println("Check " + name);
05          return name.startsWith("A");
06      });
07  System.out.println("Ready");
08  System.out.println(selected.findFirst().orElse("None"));
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
09  System.out.println(selected.count());
10  System.out.println(selected.toList());
```

Valuta se il programma stampi sia il numero sia la lista completa dei nomi selezionati. Se necessario, riscrivi soltanto le due nuove righe affinché il filtro venga eseguito una sola volta e siano disponibili entrambi i risultati. Le stampe diagnostiche del filtro possono rimanere.

**Lunghezza indicativa:** una frase e massimo 3 righe di codice.

---

# Esercizio 5 — Autori dei gruppi

**Tempo indicativo: 5–7 minuti · Java 25**

I gruppi contengono nomi non nulli. La selezione deve produrre ciascun nome una sola volta, nell’ordine della prima comparsa. Le liste originali non devono essere modificate. Lo stream è sequenziale.

Il frammento è eseguito in un metodo con `java.util.List` importata. I numeri di riga non fanno parte del codice.

```java
01  List<List<String>> groups = List.of(
02      List.of("Ada", "Linus"),
03      List.of("Ada", "Grace"));
04  List<String> result = groups.stream()
05      .flatMap(List::stream)
06      .distinct()
07      .toList();
08  System.out.println(result);
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

---

# Esercizio 6 — Totali delle misure

**Tempo indicativo: 5–7 minuti · Java 25**

Le misure sono interi non nulli compresi tra -100 e 100 e le liste contengono al massimo cento elementi. Non ci sono quindi problemi di overflow. Le elaborazioni non devono modificare la lista di partenza.

Il frammento è eseguito in un metodo con `java.util.List` importata. I numeri di riga non fanno parte del codice.

```java
01  List<Integer> values = List.of(2, -1, 4);
02  int total = values.stream()
03      .filter(value -> value >= 0)
04      .mapToInt(value -> 2 * value)
05      .sum();
06  System.out.println(total);
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
