> **E6 — Java Generics** · [Indice del corso](../README.md#modules)
> Gli esercizi sono organizzati per obiettivo principale e possono richiamare competenze dei moduli precedenti. La numerazione è indipendente per ciascun macro-modulo.


---

# Esercizio 1 — Primo elemento

**Tempo indicativo: 5–7 minuti · Java 25**

`first` legge il primo elemento di una lista non nulla e non vuota. I due frammenti di chiamata vanno considerati separatamente, non come parti dello stesso programma.

I metodi e le istruzioni si trovano rispettivamente nel corpo di una classe e in un suo metodo statico, con `java.util.List` importata. I numeri di riga non fanno parte del codice.

```java
01  static Object first(List<?> values) {
02      return values.get(0);
03  }
```

```java
04  List<Integer> values = List.of(4, 7);
05  List<Number> numbers = values;
06  System.out.println(numbers.get(0));
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

---

# Esercizio 2 — Operazioni su misure numeriche

**Tempo indicativo: 5–7 minuti · Java 25**

Le misure sono numeri non nulli e di piccola grandezza. Si possono convertire a `double`; non considerare overflow o problemi di precisione. Le liste sono non nulle, anche eventualmente vuote.

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
09  System.out.println(twice("3"));
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

---

# Esercizio 3 — Riepilogo di una lista

**Tempo indicativo: 5–7 minuti · Java 25**

Un componente espone metodi per riepilogare liste di dati. Le liste ricevute dai due metodi sono non nulle.

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
