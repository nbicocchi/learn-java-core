# Esercizio 1 — Controllo del volume

**Tempo indicativo: 5–7 minuti · Java 25**

Il livello audio deve rimanere tra 0 e 10. Il costruttore e `setLevel` rifiutano valori esterni a questo intervallo con `IllegalArgumentException`, senza modificare lo stato di un oggetto già costruito. Il programma è monothread.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  final class Volume {
02      private int level;
03      Volume(int initial) { setLevel(initial); }
04      public int getLevel() { return level; }
05      public void setLevel(int value) {
06          if (value < 0 || value > 10)
07              throw new IllegalArgumentException();
08          level = value;
09      }
10  }
```

```java
11  Volume first = new Volume(3);
12  Volume second = first;
13  second.setLevel(5);
14  System.out.println(first.getLevel());
```

## 1. Scelta multipla

Che cosa accade eseguendo le righe 11–14? Seleziona una sola risposta.

- **A.** Stampa `5`.
- **B.** Stampa `3`.
- **C.** Solleva `IllegalArgumentException`.
- **D.** Non compila perché `level` è privato.

## 2. Risposta aperta

Per una nuova versione, dopo la costruzione il chiamante deve poter leggere il livello e variarlo soltanto di un’unità per operazione, in aumento o in diminuzione. Ai limiti, l’operazione non ammessa deve sollevare `IllegalArgumentException` lasciando invariato lo stato.

Proponi la nuova API: indica firme e visibilità dei metodi necessari, quali membri attuali cambieresti e dove collocheresti la validazione. Non implementare i corpi e non cambiare il tipo del campo.

**Lunghezza indicativa:** massimo 4 firme di metodi e 2 frasi. Non occorre aggiornare le istruzioni dimostrative.

---

# Esercizio 2 — Numero di una sessione

**Tempo indicativo: 5–7 minuti · Java 25**

La classe tiene il conto delle sessioni costruite. Il programma è monothread, parte senza sessioni preesistenti e ne crea al massimo mille.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  final class Session {
02      private static int count;
03      Session() { count++; }
04      public int number() { return count; }
05  }
```

```java
06  Session first = new Session();
07  Session second = new Session();
08  System.out.println(first.number());
09  System.out.println(second.number());
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le due righe nell’ordine.

- **A.** `1`, poi `2`.
- **B.** `1`, poi `1`.
- **C.** `2`, poi `2`.
- **D.** `0`, poi `0`.

## 2. Risposta aperta

Per una nuova versione, ogni sessione deve ricevere alla costruzione un numero distinto e stabile: 1 per la prima, 2 per la seconda, e così via. Il numero non deve poter essere cambiato dal chiamante e deve rimanere lo stesso anche dopo altre costruzioni.

Scegli quali informazioni debbano appartenere alla classe e quali a ciascuna istanza. Mostra i campi, il costruttore e `number()` della nuova versione, senza aggiungere setter.

**Lunghezza indicativa:** massimo 5 righe di codice e una frase di motivazione.

---

# Esercizio 3 — Dimensioni di una scatola

**Tempo indicativo: 5–7 minuti · Java 25**

La scatola rettangolare conserva larghezza e altezza. Il costruttore con un solo argomento deve costruire un quadrato. Le dimensioni ricevute sono piccoli interi, ma possono essere non positive; non considerare overflow.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  final class Box {
02      private final int width;
03      private final int height;
04      Box(int side) { this(side, side); }
05      Box(int width, int height) {
06          this.width = width;
07          this.height = height;
08      }
09      public int area() { return width * height; }
10  }
```

```java
11  Box square = new Box(3);
12  System.out.println(square.area());
```

## 1. Scelta multipla

Che cosa accade eseguendo le righe 11–12? Seleziona una sola risposta.

- **A.** Stampa `9`.
- **B.** Stampa `6`.
- **C.** Stampa `0`, perché i campi sono `final`.
- **D.** Non compila perché un costruttore non può chiamarne un altro.

## 2. Risposta breve

Una review richiede di garantire larghezza e altezza strettamente positive fin dalla costruzione, rifiutando gli altri valori con `IllegalArgumentException`. Indica dove inseriresti il controllo per coprire entrambi i costruttori senza duplicarlo e mostra il controllo. Spiega se i campi `final` siano da soli sufficienti a garantire questo vincolo.

**Lunghezza indicativa:** massimo 2 righe di codice e 2 frasi.

---

# Esercizio 4 — Tariffe di consegna

**Tempo indicativo: 5–7 minuti · Java 25**

Ogni consegna espone la propria tariffa mediante `fee`. Il metodo `quote` deve restituire quella tariffa per qualsiasi sottotipo di `Delivery`. Gli argomenti sono non nulli.

Le istruzioni dopo le classi sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  class Delivery {
02      public int fee() { return 5; }
03  }
04  class Express extends Delivery {
05      @Override public int fee() { return 9; }
06  }
07  class Quotes {
08      static int quote(Delivery delivery) {
09          if (delivery instanceof Express) return 9;
10          return 5;
11      }
12  }
```

```java
13  Delivery delivery = new Express();
14  System.out.println(delivery.fee());
15  System.out.println(new Delivery().fee());
```

## 1. Scelta multipla

Seleziona l’unico output corretto, indicando le due righe nell’ordine.

- **A.** `5`, poi `5`.
- **B.** `5`, poi `9`.
- **C.** `9`, poi `9`.
- **D.** `9`, poi `5`.

## 2. Risposta breve

Valuta se `quote` rispetta il requisito per ogni possibile sottotipo. Se non è così, descrivi un sottotipo e una chiamata che lo dimostrino, indicando risultato richiesto e risultato effettivo. Non modificare il codice esistente.

**Lunghezza indicativa:** 2–3 frasi oppure una piccola classe e una frase.

---

# Esercizio 5 — Confronto di biglietti

**Tempo indicativo: 5–7 minuti · Java 25**

Ogni biglietto ha un codice intero. Il metodo di confronto mostrato fa parte dell’API corrente; la classe non dichiara altri metodi oltre a quelli riportati.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  final class Ticket {
02      private final int code;
03      Ticket(int code) { this.code = code; }
04      public boolean equals(Ticket other) {
05          return other != null && code == other.code;
06      }
07  }
```

```java
08  Ticket first = new Ticket(7);
09  Object second = new Ticket(7);
10  System.out.println(first.equals(second));
```

## 1. Scelta multipla

Che cosa accade eseguendo le righe 8–10? Seleziona una sola risposta.

- **A.** Stampa `true`, perché i codici sono uguali.
- **B.** Non compila perché `Ticket` dichiara soltanto `equals(Ticket)`.
- **C.** Solleva `ClassCastException`.
- **D.** Stampa `false`.

## 2. Risposta aperta

Si decide di definire l’uguaglianza logica di due biglietti attraverso il codice, anche nei client che li trattano come `Object` o li inseriscono in una collezione hash. Un biglietto non deve risultare uguale a `null` o a un oggetto di un altro tipo.

Indica le firme dei metodi da ridefinire e descrivi le rispettive regole. Precisa la relazione richiesta tra i loro risultati, senza implementare i corpi.

**Lunghezza indicativa:** 2 firme e 2–3 frasi.

---

# Esercizio 6 — Report del personale

**Tempo indicativo: 5–7 minuti · Java 25**

Un report può ricevere persone generiche e dipendenti. Soltanto i dipendenti hanno una retribuzione, rappresentata da un piccolo intero non negativo. Gli elementi sono non nulli.

Le istruzioni dopo le classi sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  class Person { }
02  class Employee extends Person {
03      private final int salary;
04      Employee(int salary) { this.salary = salary; }
05      public int salary() { return salary; }
06  }
```

```java
07  Employee original = new Employee(20);
08  Person person = original;
09  Employee recovered = (Employee) person;
10  System.out.println(original == recovered);
```

## 1. Scelta multipla

Che cosa accade eseguendo le righe 7–10? Seleziona una sola risposta.

- **A.** Stampa `false`, perché il cast crea un nuovo oggetto.
- **B.** Stampa `true`.
- **C.** Non compila perché non si può tornare da `Person` a `Employee`.
- **D.** Solleva `ClassCastException`.

## 2. Risposta breve

In un’esecuzione separata il report usa:

```java
11  Person[] people = {new Employee(20), new Person()};
12  for (Person value : people) {
13      System.out.println(((Employee) value).salary());
14  }
```

Il requisito è stampare la retribuzione dei soli dipendenti, ignorando le persone generiche. Valuta il ciclo e, se necessario, correggilo senza aggiungere una retribuzione fittizia a `Person`.

**Lunghezza indicativa:** una frase e massimo 4 righe di codice.

---

# Esercizio 7 — Invio di notifiche

**Tempo indicativo: 5–7 minuti · Java 25**

Un’applicazione di prenotazione deve avvisare un operatore quando si verifica un evento che richiede attenzione. La classe `Alarm` rappresenta il componente che attiva l’avviso, mentre il canale ricevuto dal costruttore si occupa dell’invio del testo. Il chiamante sceglie il canale quando costruisce ciascun allarme e può conservarlo attraverso il tipo `Notifier`. Nell’esempio sono disponibili email e SMS, così da poter configurare allarmi per destinatari diversi senza cambiare `Alarm`. Per rendere osservabile il comportamento senza servizi esterni, l’invio è simulato mediante una stampa sulla console.

**Assunzioni:** il canale e i testi sono non nulli; l’invio termina normalmente. Non sono richiesti rete, concorrenza o gestione degli errori di consegna.

Le istruzioni dopo le dichiarazioni sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  interface Notifier {
02      void send(String text);
03  }
04  class Email implements Notifier {
05      @Override
06      public void send(String text) {
07          System.out.println("EMAIL: " + text);
08      }
09  }
10  class Sms implements Notifier {
11      @Override
12      public void send(String text) {
13          System.out.println("SMS: " + text);
14      }
15  }
16  class Alarm {
17      private final Notifier channel;
18      Alarm(Notifier channel) {
19          this.channel = channel;
20      }
21      void fire() {
22          channel.send("Alert");
23      }
24  }
```

```java
25  Notifier channel = new Email();
26  Alarm first = new Alarm(channel);
27  channel = new Sms();
28  Alarm second = new Alarm(channel);
29  first.fire();
30  second.fire();
```

## 1. Scelta multipla

Qual è l’output delle righe 25–30? Seleziona una sola risposta, considerando le due righe nell’ordine.

- **A.** `SMS: Alert`, poi `SMS: Alert`.
- **B.** `EMAIL: Alert`, poi `SMS: Alert`.
- **C.** `EMAIL: Alert`, poi `EMAIL: Alert`.
- **D.** `SMS: Alert`, poi `EMAIL: Alert`.

## 2. Risposta aperta

Si vogliono aggiungere canali di notifica, per esempio email e SMS, che inseriscano un prefisso prima del testo da inviare. Il prefisso viene scelto quando si crea il canale: con il prefisso `"[URGENTE] "`, la chiamata `send("Alert")` deve inviare il testo `"[URGENTE] Alert"`. Il prefisso è sempre non nullo e può essere diverso per due oggetti dello stesso tipo.

La preparazione del testo deve essere scritta una sola volta e riutilizzata dai diversi canali; cambia soltanto il modo in cui il testo viene inviato. Non puoi modificare `Alarm` o `Notifier`.

Proponi un’organizzazione del codice indicando:
- dove conservare il prefisso e come fornirlo alla creazione del canale;
- dove preparare il testo completo senza duplicare questa operazione;
- quale metodo deve occuparsi dell’invio specifico per email, SMS o altri canali.

**Lunghezza indicativa:** uno schema di classi o interfacce di circa 6 righe e una frase di spiegazione. Indica le relazioni tra i tipi e le firme dei metodi, ma non implementare i corpi o gli invii reali. Sono ammesse soluzioni diverse che rispettino i requisiti.

<details>
<summary>Mostra la soluzione dell’esercizio 7</summary>

### 1. Scelta multipla: B

L’output è `EMAIL: Alert`, poi `SMS: Alert`. Ogni allarme conserva nel proprio campo il riferimento ricevuto alla costruzione: il primo a un’istanza di `Email`, il secondo a un’istanza di `Sms`. Le chiamate a `send` eseguono le rispettive implementazioni.

- **A errata:** riassegnare la variabile locale `channel` non cambia il campo del primo allarme.
- **C errata:** il secondo allarme riceve il riferimento al nuovo oggetto `Sms`, non al precedente oggetto `Email`.
- **D errata:** le stampe seguono le chiamate a `first.fire()` e `second.fire()`, non l’ordine inverso.

### 2. Risposta aperta

#### Risposta sufficiente

Una possibile risposta è questo schema, accompagnato dalla spiegazione che il prefisso appartiene a ciascun oggetto e la preparazione comune risiede in `send`:

```text
PrefixedNotifier: classe astratta che implementa Notifier
- private final String prefix
- protected PrefixedNotifier(String prefix)
- public final void send(String text): prepara il testo e chiama deliver
- protected abstract void deliver(String preparedText)
Canali concreti: estendono PrefixedNotifier e implementano deliver
```

#### Approfondimento

L’implementazione seguente esplicita il funzionamento dello schema; non è richiesta nella risposta dello studente.

```java
abstract class PrefixedNotifier implements Notifier {
    private final String prefix;

    protected PrefixedNotifier(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public final void send(String text) {
        deliver(prefix + text);
    }

    protected abstract void deliver(String preparedText);
}
```

Il campo non è `static`: ogni oggetto può avere un prefisso diverso. `send` contiene la preparazione comune e, essendo `final`, non può essere ridefinito dalle sottoclassi per aggirarla. Un canale concreto implementa soltanto `deliver`, per esempio:

```java
final class PrefixedEmail extends PrefixedNotifier {
    PrefixedEmail(String prefix) {
        super(prefix);
    }

    @Override
    protected void deliver(String preparedText) {
        System.out.println("EMAIL: " + preparedText);
    }
}
```

Un `PrefixedEmail("[URGENTE] ")` può essere passato al costruttore di `Alarm` senza modificarlo. Il testo affidato all’invio è `[URGENTE] Alert`; la simulazione sulla console aggiunge anche l’indicazione `EMAIL:`.

#### Alternativa per composizione

È altrettanto valida una classe che implementa `Notifier` e riceve nel costruttore un prefisso e un altro `Notifier`, conservandoli in due campi di istanza. Il suo `send` prepara il testo e chiama `delegate.send(prefix + text)`: il delegato può essere un’istanza dell’attuale `Email` o `Sms`. Questa descrizione, con le firme essenziali, è sufficiente come risposta alternativa: la preparazione è riutilizzata per composizione invece che per ereditarietà.

</details>

---

# Esercizio 8 — Etichette dei componenti

**Tempo indicativo: 5–7 minuti · Java 25**

Una schermata mostra etichette testuali per diversi componenti di un catalogo. Il codice che costruisce la schermata riceve riferimenti di tipo `Named` e usa `label()` per ottenere il testo da visualizzare, senza esaminare la classe concreta. Il contratto offre un’etichetta generica alle implementazioni che non ne definiscono una propria. La classe `Tag` conserva invece il nome di una voce del catalogo e fornisce il testo usato per quella voce. Il frammento mostra come viene richiesto il testo di un componente prima di stampare la schermata.

**Assunzioni:** i nomi e i riferimenti sono non nulli; le interfacce non hanno relazioni di ereditarietà ulteriori rispetto a quelle dichiarate.

Le istruzioni dopo le dichiarazioni sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  interface Named {
02      default String label() {
03          return "generic";
04      }
05  }
06  class Tag implements Named {
07      private final String name;
08      Tag(String name) {
09          this.name = name;
10      }
11      @Override
12      public String label() {
13          return "tag:" + name;
14      }
15  }
```

```java
16  Named component = new Tag("invoice");
17  System.out.println(component.label());
```

## 1. Scelta multipla

Che cosa accade eseguendo le righe 16–17? Seleziona una sola risposta.

- **A.** Stampa `generic`, perché il riferimento ha tipo `Named`.
- **B.** Non compila: un metodo `default` non può essere ridefinito.
- **C.** Stampa `tag:invoice`.
- **D.** Stampa prima `generic` e poi `tag:invoice`.

## 2. Risposta breve

Considera questa seconda versione, che sostituisce completamente la prima `Tag`. `Named` resta quella definita alle righe 1–5; `Audited` è un’interfaccia distinta, senza relazioni di ereditarietà con `Named`.

```java
18  interface Audited {
19      default String label() {
20          return "audit";
21      }
22  }
23  class Tag implements Named, Audited {
24      private final String name;
25      Tag(String name) {
26          this.name = name;
27      }
28  }
```

Valuta la compilazione della seconda versione con la chiamata delle righe 16–17. Se necessario, spiega come intervenire su `Tag` per fare in modo che usi il comportamento definito da `Named`, senza cambiare le interfacce. Puoi descrivere la modifica a parole: non è necessario ricordare la sintassi esatta della chiamata al metodo dell’interfaccia.

**Lunghezza indicativa:** 2–3 frasi, oppure 1–2 frasi e poche righe di codice.

<details>
<summary>Mostra la soluzione dell’esercizio 8</summary>

### 1. Scelta multipla: C

L’output è `tag:invoice`. L’oggetto è un `Tag` e il suo metodo `label` ridefinisce quello dell’interfaccia.

- **A errata:** il tipo del riferimento non impone di eseguire l’implementazione `default`.
- **B errata:** i metodi `default` possono essere ridefiniti dalle classi che implementano l’interfaccia.
- **D errata:** viene eseguito un solo metodo; non c’è una chiamata automatica anche al metodo dell’interfaccia.

### 2. Risposta breve

#### Risposta sufficiente

La seconda versione non compila perché eredita due metodi `default` con la stessa firma da interfacce non imparentate. `Tag` deve dichiarare un proprio metodo pubblico `label` che risolva il conflitto richiamando esplicitamente l’implementazione di `Named`.

Questa spiegazione è sufficiente anche senza ricordare la sintassi della chiamata.

#### Approfondimento

Il metodo da aggiungere alla seconda versione di `Tag` è:

```java
@Override
public String label() {
    return Named.super.label();
}
```

La chiamata delle righe 16–17 stampa ora `generic`. Il campo `name` non viene usato da questa versione del metodo.

Restituire direttamente `"generic"` riprodurrebbe l’output attuale, ma non riutilizzerebbe il comportamento dell’interfaccia: `Named.super.label()` esprime la scelta richiesta senza duplicarne l’implementazione.

</details>

---

# Esercizio 9 — Costo di un piano

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio calcola il costo di utilizzo di diverse offerte commerciali. Ogni offerta comprende una quota fissa e una parte che dipende dal numero di unità consumate. La classe `Plan` conserva la quota fissa e definisce il punto di ingresso usato dal codice di fatturazione. Le implementazioni concrete determinano il contributo variabile attraverso `extra`, mentre il chiamante richiede il totale senza conoscere la formula specifica. L’offerta `Metered` usa un prezzo unitario scelto alla costruzione e permette quindi di rappresentare più tariffe con la stessa classe.

**Requisiti e assunzioni:**
- Un numero di unità negativo deve essere rifiutato con `IllegalArgumentException`; `extra` deve ricevere soltanto unità valide.
- Quote fisse, prezzi unitari e contributi variabili sono non negativi; i costruttori ricevono valori validi.
- Tutti i valori sono piccoli e non producono overflow. Non sono richiesti sconti, arrotondamenti o valute diverse.

Le istruzioni dopo le classi sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  abstract class Plan {
02      private final int base;
03      Plan(int base) {
04          this.base = base;
05      }
06      public final int total(int units) {
07          if (units < 0) throw new IllegalArgumentException();
08          return base + extra(units);
09      }
10      protected abstract int extra(int units);
11  }
12  class Metered extends Plan {
13      private final int unitPrice;
14      Metered(int unitPrice) {
15          super(2);
16          this.unitPrice = unitPrice;
17      }
18      @Override
19      protected int extra(int units) {
20          return unitPrice * units;
21      }
22  }
```

```java
23  Plan plan = new Metered(3);
24  System.out.println(plan.total(4));
```

## 1. Scelta multipla

Che cosa accade eseguendo le righe 23–24? Seleziona una sola risposta.

- **A.** Stampa `14`.
- **B.** Stampa `12`.
- **C.** Stampa `2`.
- **D.** Non compila perché `Plan` è astratta.

## 2. Risposta breve

Aggiungi un piano gratuito che rispetti lo stesso contratto sugli input: zero per ogni numero di unità valido ed eccezione per un numero negativo. Indica quali membri sono necessari nella nuova classe e quali puoi riutilizzare senza modificarli. Non cambiare `Plan` o `Metered`.

**Lunghezza indicativa:** 2–3 frasi oppure uno schema di massimo 5 righe; non occorre una classe completa.

<details>
<summary>Mostra la soluzione dell’esercizio 9</summary>

### 1. Scelta multipla: A

L’output è `14`: la quota fissa è `2`, mentre il contributo variabile è `3 × 4 = 12`. `total` esegue il controllo e richiama l’implementazione di `extra` fornita da `Metered`.

- **B errata:** considera soltanto la parte variabile, omettendo la quota fissa.
- **C errata:** considera soltanto la quota fissa, omettendo la parte variabile.
- **D errata:** l’oggetto costruito è un `Metered`, una classe concreta; `Plan` è soltanto il tipo del riferimento.

### 2. Risposta breve

#### Risposta sufficiente

Definisco una sottoclasse di `Plan` il cui costruttore chiama `super(0)` e che implementa `extra` restituendo zero. Riutilizzo `total` senza modificarlo: somma due contributi nulli per gli input validi e conserva il controllo sui valori negativi. Non servono nuovi campi.

#### Approfondimento

Una possibile implementazione completa è:

```java
final class FreePlan extends Plan {
    FreePlan() {
        super(0);
    }

    @Override
    protected int extra(int units) {
        return 0;
    }
}
```

Il metodo `total` resta quello ereditato: restituisce zero per ogni numero di unità valido, ma continua a sollevare `IllegalArgumentException` per un valore negativo, prima di invocare `extra`.

</details>

---

# Esercizio 10 — Campioni di una rilevazione

**Tempo indicativo: 5–7 minuti · Java 25**

`Reading` conserva campioni interi in un array non nullo. Per questa versione si considera soltanto l’accesso ai campioni, non l’uguaglianza tra rilevazioni.

Le istruzioni dopo il record sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  record Reading(int[] samples) {
02      Reading {
03          samples = samples.clone();
04      }
05  }
```

```java
06  int[] source = {4, 7};
07  Reading reading = new Reading(source);
08  source[0] = 9;
09  System.out.println(reading.samples()[0]);
```

## 1. Scelta multipla

Che cosa accade eseguendo le righe 6–9? Seleziona una sola risposta.

- **A.** Stampa `9`.
- **B.** Non compila perché un record non può contenere un array.
- **C.** Stampa `4`.
- **D.** Solleva un’eccezione alla riga 8 perché il componente è `final`.

## 2. Risposta aperta

Una nuova versione deve essere immutabile e considerare uguali due rilevazioni con la stessa sequenza di valori, anche quando costruite da array distinti. Il chiamante può continuare a modificare l’array passato al costruttore. Puoi cambiare tipo del componente e API pubblica.

Scegli una rappresentazione interna e descrivi come costruzione, lettura, `equals` e `hashCode` soddisferanno i requisiti. Non implementare i metodi: motiva le scelte ed evita modifiche non necessarie.

**Lunghezza indicativa:** 3 frasi oppure uno schema di massimo 5 righe.

---

# Esercizio 11 — Indicatore di un contatore

**Tempo indicativo: 5–7 minuti · Java 25**

Un indicatore viene ottenuto da un contatore e permette di leggerne un valore. Tutte le operazioni avvengono nello stesso thread; i valori sono piccoli interi.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  class Counter {
02      private int value;
03      void set(int value) { this.value = value; }
04      class Indicator {
05          int read() { return value; }
06      }
07      Indicator indicator() { return new Indicator(); }
08  }
```

```java
09  Counter counter = new Counter();
10  counter.set(4);
11  Counter.Indicator indicator = counter.indicator();
12  counter.set(7);
13  System.out.println(indicator.read());
```

## 1. Scelta multipla

Che cosa accade eseguendo le righe 9–13? Seleziona una sola risposta.

- **A.** Stampa `4`.
- **B.** Stampa `0`.
- **C.** Non compila perché `Indicator` legge un campo privato di `Counter`.
- **D.** Stampa `7`.

## 2. Risposta aperta

Per una nuova versione, l’indicatore deve conservare soltanto il valore numerico esistente al momento della creazione, senza cambiare in seguito e senza mantenere un riferimento al contatore. Deve rimanere un tipo annidato in `Counter`.

Scegli la forma della classe annidata, lo stato necessario e il modo in cui il valore le verrà fornito. Mostra le dichiarazioni essenziali e descrivi l’adeguamento di `indicator()`; non riscrivere `Counter`.

**Lunghezza indicativa:** uno schema di massimo 5 righe e una frase.

---

# Esercizio 12 — Stato di una lavorazione

**Tempo indicativo: 5–7 minuti · Java 25**

Una lavorazione espone uno stato appartenente a un insieme finito. Nella versione attuale il chiamante può assegnarlo attraverso `setState`, purché non sia nullo.

Le istruzioni dopo le dichiarazioni sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  enum State { NEW, RUNNING, DONE }
02  final class Job {
03      private State state = State.NEW;
04      public State state() { return state; }
05      public void setState(State next) {
06          if (next == null) throw new IllegalArgumentException();
07          state = next;
08      }
09  }
```

```java
10  Job job = new Job();
11  job.setState(State.DONE);
12  System.out.println(job.state() == State.DONE);
```

## 1. Scelta multipla

Che cosa accade eseguendo le righe 10–12? Seleziona una sola risposta.

- **A.** Non compila: gli enum devono essere confrontati soltanto con `equals`.
- **B.** Stampa `true`.
- **C.** Stampa `false`, perché ogni accesso a `State.DONE` crea un nuovo oggetto.
- **D.** Solleva un’eccezione perché non è stato attraversato lo stato `RUNNING`.

## 2. Risposta aperta

Una nuova versione deve consentire soltanto le transizioni `NEW → RUNNING → DONE`; ogni altra transizione deve essere rifiutata con `IllegalStateException` lasciando invariato lo stato. Le nuove istanze devono partire sempre da `NEW`.

Proponi l’API pubblica e le condizioni da verificare, indicando che cosa cambieresti nell’attuale setter. Non implementare i corpi; la lettura dello stato deve restare possibile.

**Lunghezza indicativa:** massimo 3 firme di metodi e 2 frasi.

---

# Esercizio 13 — Calcolo delle spese

**Tempo indicativo: 5–7 minuti · Java 25**

`Checkout` calcola un totale in centesimi. Subtotali e risultati sono piccoli interi non negativi: non considerare overflow. Il parametro `shipping` è non nullo.

Le istruzioni dopo la classe sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  class Checkout {
02      int total(int subtotal, String shipping) {
03          int fee;
04          switch (shipping) {
05              case "standard" -> fee = 5;
06              case "express" -> fee = 9;
07              default -> throw new IllegalArgumentException();
08          }
09          return subtotal + fee;
10      }
11  }
```

```java
12  Checkout checkout = new Checkout();
13  System.out.println(checkout.total(20, "express"));
```

## 1. Scelta multipla

Che cosa accade sostituendo soltanto `"express"` alla riga 13 con `"pickup"`? Seleziona una sola risposta.

- **A.** Stampa `20`.
- **B.** Stampa `25`.
- **C.** Non compila perché manca un ramo dello `switch`.
- **D.** Solleva `IllegalArgumentException` senza stampare un totale.

## 2. Risposta aperta

Sono previste nuove regole di spedizione, anche dipendenti dal subtotale, che devono poter essere aggiunte senza modificare nuovamente `Checkout`. Il chiamante deve poter scegliere la regola per ogni calcolo. Si assume che ogni regola ricevuta rispetti il contratto di restituire una spesa non negativa.

Proponi una modifica al contratto di `total` e le sole dichiarazioni di supporto indispensabili. Spiega dove risiederanno le nuove regole. Non implementare regole concrete e non limitarti a nominare un principio SOLID.

**Lunghezza indicativa:** massimo 6 righe di codice e una frase.

---

# Esercizio 14 — Operazioni su un conto

**Tempo indicativo: 5–7 minuti · Java 25**

Il contratto di `Account.withdraw` richiede che un prelievo positivo non superiore al saldo abbia successo e riduca il saldo della quantità richiesta. Considera soltanto chiamate che rispettano questa precondizione. Il programma è monothread.

Le istruzioni dopo le classi sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  class Account {
02      private int balance = 10;
03      public int balance() { return balance; }
04      public void withdraw(int amount) { balance -= amount; }
05  }
06  class LockedAccount extends Account {
07      @Override public void withdraw(int amount) {
08          throw new UnsupportedOperationException();
09      }
10  }
```

```java
11  Account account = new LockedAccount();
12  System.out.println(account.balance());
```

## 1. Scelta multipla

Che cosa accade eseguendo le righe 11–12? Seleziona una sola risposta.

- **A.** Stampa `0`.
- **B.** Solleva `UnsupportedOperationException`.
- **C.** Stampa `10`.
- **D.** Non compila perché la sottoclasse non ridefinisce `balance`.

## 2. Risposta aperta

Il progetto deve supportare sia conti prelevabili sia conti bloccati: entrambi permettono di leggere il saldo, ma soltanto i primi possono essere passati a un servizio di prelievo. Valuta la gerarchia attuale rispetto al contratto e proponi tipi o ruoli che esprimano la distinzione a compilazione, senza operazioni fittizie o cast nel servizio.

**Lunghezza indicativa:** uno schema di massimo 5 righe con le firme essenziali e 1–2 frasi. Non implementare i conti.

---

# Esercizio 15 — Dispositivi da ufficio

**Tempo indicativo: 5–7 minuti · Java 25**

Un’applicazione usa dispositivi di stampa e acquisizione. L’implementazione mostrata sa stampare ma non acquisire documenti.

Le istruzioni dopo le dichiarazioni sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  interface Device {
02      void print(String text);
03      String scan();
04  }
05  class PrintOnly implements Device {
06      public void print(String text) { System.out.println(text); }
07      public String scan() { throw new UnsupportedOperationException(); }
08  }
```

```java
09  Device device = new PrintOnly();
10  device.print("Report");
```

## 1. Scelta multipla

Che cosa accade eseguendo le righe 9–10? Seleziona una sola risposta.

- **A.** Stampa `Report`.
- **B.** Solleva `UnsupportedOperationException`.
- **C.** Non compila perché `scan` non restituisce una stringa.
- **D.** Non compila perché una classe deve avere le stesse capacità reali dell’interfaccia.

## 2. Risposta aperta

Devono essere supportati anche scanner privi di stampa e dispositivi che offrono entrambe le capacità. Un servizio di acquisizione deve dipendere solo da ciò che gli serve, senza costringere uno scanner a implementare una stampa fittizia.

Proponi le interfacce necessarie e la firma di un metodo del servizio che riceva il dispositivo appropriato. Indica come un dispositivo multifunzione parteciperà ai contratti. Non implementare dispositivi o servizi.

**Lunghezza indicativa:** massimo 5 righe di dichiarazioni e una frase.
