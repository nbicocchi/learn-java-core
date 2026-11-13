# Esercizio 1 — Controllo del volume

**Tempo indicativo: 5–7 minuti · Java 25**

Un lettore musicale espone un controllo del volume usato da più comandi della schermata. Ogni controllo conserva un livello tra 0 e 10. Il costruttore e `setLevel` rifiutano valori esterni a questo intervallo con `IllegalArgumentException`, senza modificare lo stato di un oggetto già costruito. Due variabili possono riferirsi allo stesso controllo, senza creare una copia. Il programma è monothread.

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
14  IO.println(first.getLevel());
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

<details>
<summary>Mostra la soluzione dell’esercizio 1</summary>

### 1. Scelta multipla: A

Si stampa `5`: le due variabili indicano lo stesso oggetto.

- **B errata:** non viene creata una copia del volume.
- **C errata:** `5` è ammesso.
- **D errata:** il campo viene usato attraverso metodi accessibili.

### 2. Risposta aperta

Conservo `getLevel()` pubblico; sostituisco il setter pubblico con `public void increase()` e `public void decrease()`. Mantengo il costruttore con il livello iniziale e rendo il setter privato, riutilizzandone il controllo prima dell’assegnazione. I due nuovi metodi gli passano rispettivamente `level + 1` e `level - 1`, così il fallimento non modifica lo stato.

**Alternativa valida:** controlli nel costruttore e nei due metodi, senza setter; l’API deve impedire aggiornamenti arbitrari dall’esterno.

</details>

---

# Esercizio 2 — Numero di una sessione

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio crea una sessione per ogni nuovo collegamento. La classe mantiene un conteggio delle sessioni costruite durante l’esecuzione. Il metodo `number` viene invocato su una singola sessione per consultarne l’informazione corrente. Il programma è monothread, parte senza sessioni preesistenti e ne crea al massimo mille. Non vengono distrutte o riutilizzate sessioni per questo conteggio.

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
08  IO.println(first.number());
09  IO.println(second.number());
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

<details>
<summary>Mostra la soluzione dell’esercizio 2</summary>

### 1. Scelta multipla: C

Entrambe le chiamate leggono il campo statico, che dopo due costruzioni vale `2`.

- **A errata:** il codice non salva un numero distinto in ogni istanza.
- **B errata:** il contatore è condiviso e viene incrementato due volte.
- **D errata:** i costruttori aggiornano il contatore prima delle letture.

### 2. Risposta aperta

```java
private static int count;
private final int number;
Session() { number = ++count; }
public int number() { return number; }
```

Il contatore appartiene alla classe; il numero assegnato appartiene all’istanza ed è stabile perché `final` e senza setter. L’assunzione monothread evita problemi di assegnazione concorrente.

</details>

---

# Esercizio 3 — Dimensioni di una scatola

**Tempo indicativo: 5–7 minuti · Java 25**

Un catalogo descrive scatole rettangolari attraverso larghezza e altezza. Dopo la costruzione, le dimensioni non devono essere riassegnate. Per le scatole quadrate è disponibile un costruttore con un solo argomento. Il metodo `area` viene usato per confrontare gli ingombri. Le dimensioni ricevute sono piccoli interi, ma possono essere non positive; non considerare overflow.

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
12  IO.println(square.area());
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

<details>
<summary>Mostra la soluzione dell’esercizio 3</summary>

### 1. Scelta multipla: A

Il costruttore delega a quello con due parametri, assegnando `3` a entrambe le dimensioni: l’area è `9`.

- **B errata:** l’area è un prodotto, non una somma.
- **C errata:** `final` non impedisce l’inizializzazione nel costruttore.
- **D errata:** la delega con `this(...)` è consentita.

### 2. Risposta breve

Inserisco il controllo nel costruttore a due parametri, prima delle assegnazioni: anche quello a un parametro vi delega.

```java
if (width <= 0 || height <= 0) throw new IllegalArgumentException();
```

`final` impedisce riassegnazioni successive, ma non rifiuta valori iniziali non validi.

</details>

---

# Esercizio 4 — Tariffe di consegna

**Tempo indicativo: 5–7 minuti · Java 25**

Un negozio offre diversi tipi di consegna. Ogni consegna espone la propria tariffa attraverso `fee`. Il componente `Quotes` deve fornire un preventivo uguale alla tariffa dell’oggetto ricevuto, anche quando vengono introdotti nuovi sottotipi di `Delivery`. Il chiamante può conservare una consegna mediante un riferimento al tipo base. Gli argomenti sono non nulli e le tariffe sono piccoli interi non negativi.

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
14  IO.println(delivery.fee());
15  IO.println(new Delivery().fee());
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

<details>
<summary>Mostra la soluzione dell’esercizio 4</summary>

### 1. Scelta multipla: D

La chiamata sul primo oggetto usa l’override di `Express`, restituendo `9`; il secondo oggetto è un `Delivery` e restituisce `5`.

- **A errata:** il tipo dichiarato non elimina il dispatch dinamico.
- **B errata:** scambia le due implementazioni.
- **C errata:** il secondo oggetto non è un `Express`.

### 2. Risposta breve

Un sottotipo `Pickup extends Delivery` che ridefinisce `fee()` restituendo zero è un controesempio: `Quotes.quote(new Pickup())` restituisce `5` anziché `0`. Anche un sottotipo di `Express` con tariffa diversa verrebbe gestito male. La selezione dei due valori fissi non rispetta il contratto generale.

</details>

---

# Esercizio 5 — Confronto di biglietti

**Tempo indicativo: 5–7 minuti · Java 25**

Un sistema di prenotazione rappresenta ciascun biglietto con un codice intero. Più oggetti possono contenere lo stesso codice perché arrivano da richieste diverse. Alcuni client conservano i biglietti attraverso variabili di tipo `Object`. Il metodo di confronto mostrato fa parte dell’API corrente; la classe non dichiara altri metodi oltre a quelli riportati. I codici non cambiano dopo la costruzione.

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
10  IO.println(first.equals(second));
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

<details>
<summary>Mostra la soluzione dell’esercizio 5</summary>

### 1. Scelta multipla: D

L’argomento ha tipo dichiarato `Object`: viene scelto `equals(Object)` ereditato da `Object`, che confronta l’identità. I due oggetti sono distinti.

- **A errata:** `equals(Ticket)` non ridefinisce `equals(Object)`.
- **B errata:** il metodo ereditato è comunque disponibile.
- **C errata:** non viene eseguito alcun cast.

### 2. Risposta aperta

Ridefinisco `public boolean equals(Object other)` e `public int hashCode()`. `equals` accetta soltanto un `Ticket` con lo stesso codice, rifiutando `null` e altri tipi. `hashCode` dipende dal codice: oggetti uguali devono avere lo stesso hash, mentre la coincidenza degli hash non implica uguaglianza.

**Alternativa valida:** trasformare il tipo in un record con il solo codice, se è ammesso cambiare la dichiarazione e l’API preserva il requisito; i metodi generati forniscono la semantica richiesta.

</details>

---

# Esercizio 6 — Report del personale

**Tempo indicativo: 5–7 minuti · Java 25**

Un ufficio produce un report delle persone registrate. L’elenco può contenere persone generiche e dipendenti. Soltanto i dipendenti espongono una retribuzione, rappresentata da un piccolo intero non negativo. Il codice conserva tutti gli elementi mediante il tipo comune `Person`, ma deve distinguere quali operazioni siano disponibili sugli oggetti effettivi. Gli elementi sono non nulli.

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
10  IO.println(original == recovered);
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
13      IO.println(((Employee) value).salary());
14  }
```

Il requisito è stampare la retribuzione dei soli dipendenti, ignorando le persone generiche. Valuta il ciclo e, se necessario, correggilo senza aggiungere una retribuzione fittizia a `Person`.

**Lunghezza indicativa:** una frase e massimo 4 righe di codice.

<details>
<summary>Mostra la soluzione dell’esercizio 6</summary>

### 1. Scelta multipla: B

Il cast riesce perché l’oggetto effettivo è un `Employee` e non crea una copia: i riferimenti restano uguali.

- **A errata:** un cast non costruisce un oggetto.
- **C errata:** il cast esplicito è ammesso.
- **D errata:** l’oggetto soddisfa il tipo richiesto dal cast.

### 2. Risposta breve

Il ciclo originale stampa `20` e poi fallisce con `ClassCastException` sulla persona generica; non soddisfa il requisito. Si può verificare il tipo prima dell’accesso:

```java
for (Person value : people) {
    if (value instanceof Employee employee)
        IO.println(employee.salary());
}
```

**Alternativa valida:** `instanceof Employee` seguito dal cast esplicito nello stesso ramo protetto.

</details>

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
07          IO.println("EMAIL: " + text);
08      }
09  }
10  class Sms implements Notifier {
11      @Override
12      public void send(String text) {
13          IO.println("SMS: " + text);
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
        IO.println("EMAIL: " + preparedText);
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
17  IO.println(component.label());
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
24  IO.println(plan.total(4));
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

Uno strumento produce rilevazioni composte da campioni interi. `Reading` riceve i campioni dal chiamante, che può continuare a usare e modificare il proprio array. Il record espone i dati per la consultazione attraverso l’accessor generato. Nella versione mostrata si considera soltanto l’accesso ai campioni, non l’uguaglianza tra rilevazioni. L’array è non nullo, anche eventualmente vuoto.

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
09  IO.println(reading.samples()[0]);
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

<details>
<summary>Mostra la soluzione dell’esercizio 10</summary>

### 1. Scelta multipla: C

Il costruttore conserva un clone: la modifica di `source` non raggiunge quell’array e si stampa `4`.

- **A errata:** la copia in ingresso separa i due array.
- **B errata:** un componente di un record può essere un array.
- **D errata:** `final` non rende immutabile il contenuto di un array.

### 2. Risposta aperta

Una possibilità è un record con componente `List<Integer>`, costruita copiando i valori dell’array e poi resa non modificabile con `List.copyOf`. La lettura può esporre tale lista perché gli elementi `Integer` sono immutabili; i metodi generati del record usano l’uguaglianza e l’hash della lista, basati sulla sequenza dei valori. Le successive modifiche all’array del chiamante non incidono sul record.

**Alternativa valida:** conservare l’array con copie difensive sia in ingresso sia nell’accessor e ridefinire `equals`/`hashCode` tramite `Arrays.equals`/`Arrays.hashCode`. Il solo clone nel costruttore non basta: l’accessor generato espone l’array interno e l’uguaglianza predefinita degli array è per identità.

</details>

---

# Esercizio 11 — Indicatore di un contatore

**Tempo indicativo: 5–7 minuti · Java 25**

Una schermata ottiene un indicatore da un contatore. Il contatore può ricevere nuovi valori dopo che l’indicatore è stato creato. La schermata usa `read` per consultare il valore esposto dall’indicatore. La versione mostrata usa una classe interna per organizzare i due componenti. Tutte le operazioni avvengono nello stesso thread e i valori sono piccoli interi.

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
13  IO.println(indicator.read());
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

<details>
<summary>Mostra la soluzione dell’esercizio 11</summary>

### 1. Scelta multipla: D

L’indicatore interno legge il campo del contatore a cui è associato; dopo `set(7)` legge `7`.

- **A errata:** il costruttore implicito non fotografa il valore `4`.
- **B errata:** il campo è già stato aggiornato.
- **C errata:** la classe annidata può accedere ai membri privati dell’esterna.

### 2. Risposta aperta

```java
static final class Indicator {
    private final int snapshot;
    Indicator(int snapshot) { this.snapshot = snapshot; }
    int read() { return snapshot; }
}
```

`indicator()` restituisce `new Indicator(value)`: la classe annidata statica non ha un riferimento implicito al contatore e conserva solo il valore ricevuto. È valida anche una dichiarazione di record annidato con componente numerico e metodo `read` equivalente.

</details>

---

# Esercizio 12 — Stato di una lavorazione

**Tempo indicativo: 5–7 minuti · Java 25**

Un laboratorio tiene traccia dello stato di una lavorazione. Ogni nuova istanza parte da `NEW` e permette di leggere lo stato corrente. Nella versione mostrata il chiamante assegna direttamente uno degli stati attraverso `setState`. L’unico controllo del setter attuale riguarda la non nullità. Il programma è monothread e non sono richiesti altri stati oltre a quelli elencati.

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
12  IO.println(job.state() == State.DONE);
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

<details>
<summary>Mostra la soluzione dell’esercizio 12</summary>

### 1. Scelta multipla: B

Il setter accetta `DONE`, che non è nullo; il confronto tra la costante enum letta e `State.DONE` vale `true`.

- **A errata:** `==` è appropriato per le costanti enum.
- **C errata:** un accesso a una costante enum non crea una nuova istanza.
- **D errata:** il setter corrente non impone un percorso tra gli stati.

### 2. Risposta aperta

Conservo `public State state()` e sostituisco il setter pubblico con `public void start()` e `public void finish()`. `start` verifica `NEW` prima di assegnare `RUNNING`; `finish` verifica `RUNNING` prima di assegnare `DONE`, altrimenti sollevano `IllegalStateException` senza assegnazioni.

**Alternativa valida:** mantenere `setState(State next)` validando esattamente le due coppie di transizione ammesse prima di aggiornare il campo e rifiutando ogni altra richiesta, incluso `null`, con l’eccezione prevista.

</details>

---

# Esercizio 13 — Calcolo delle spese

**Tempo indicativo: 5–7 minuti · Java 25**

Un negozio calcola il totale di un ordine sommando subtotale e spese di spedizione. `Checkout` riceve il nome della modalità scelta dal cliente. La versione mostrata riconosce due modalità, ciascuna con una spesa fissa. Le modalità non riconosciute devono essere segnalate invece di applicare una tariffa arbitraria. Subtotali e risultati sono piccoli interi non negativi, senza overflow; `shipping` è non nullo.

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
13  IO.println(checkout.total(20, "express"));
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

<details>
<summary>Mostra la soluzione dell’esercizio 13</summary>

### 1. Scelta multipla: D

`pickup` seleziona il ramo `default`, che solleva l’eccezione prima che `println` riceva un risultato.

- **A errata:** non è prevista una tariffa zero per il ritiro.
- **B errata:** `pickup` non coincide con `standard`.
- **C errata:** il ramo `default` gestisce i valori non elencati.

### 2. Risposta aperta

```java
interface ShippingRule { int fee(int subtotal); }
int total(int subtotal, ShippingRule shipping) {
    return subtotal + shipping.fee(subtotal);
}
```

Le nuove regole risiedono in implementazioni di `ShippingRule`, scelte dal chiamante per ogni calcolo; `Checkout` dipende dal contratto, non dai loro nomi. È valida anche un’interfaccia funzionale standard appropriata, se nel contratto viene mantenuto il vincolo di spesa non negativa.

</details>

---

# Esercizio 14 — Operazioni su un conto

**Tempo indicativo: 5–7 minuti · Java 25**

Un servizio bancario consulta saldi ed esegue prelievi. Il contratto di `Account.withdraw` richiede che un prelievo positivo non superiore al saldo abbia successo e riduca il saldo della quantità richiesta. Una versione del progetto introduce anche conti bloccati. Il frammento mostra la relazione attuale tra i due tipi di conto. Considera soltanto prelievi che rispettano la precondizione indicata e un programma monothread.

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
12  IO.println(account.balance());
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

<details>
<summary>Mostra la soluzione dell’esercizio 14</summary>

### 1. Scelta multipla: C

Viene letto il saldo iniziale `10`; il frammento non chiama `withdraw`.

- **A errata:** la sottoclasse non azzera il saldo.
- **B errata:** il metodo che solleva l’eccezione non viene invocato.
- **D errata:** il metodo `balance` viene ereditato.

### 2. Risposta aperta

La gerarchia viola il contratto: un `LockedAccount` rifiuta anche un prelievo valido, come `withdraw(1)` con saldo `10`. Si possono distinguere i ruoli:

```java
interface BalanceView { int balance(); }
interface Withdrawable extends BalanceView { void withdraw(int amount); }
// Conto bloccato: implementa BalanceView.
// Conto prelevabile: implementa Withdrawable.
void processWithdrawal(Withdrawable account, int amount);
```

Il servizio riceve solo conti che promettono davvero il prelievo. Sono valide altre gerarchie o composizioni che mantengano questa distinzione statica.

</details>

---

# Esercizio 15 — Dispositivi da ufficio

**Tempo indicativo: 5–7 minuti · Java 25**

Un ufficio usa dispositivi per stampare e acquisire documenti. Alcuni dispositivi offrono soltanto una delle due capacità. L’interfaccia corrente le raggruppa entrambe, mentre l’implementazione mostrata sa solo stampare. Il servizio di stampa riceve un testo e lo invia al dispositivo scelto. Testi e dispositivi sono non nulli e la stampa simulata termina normalmente.

Le istruzioni dopo le dichiarazioni sono eseguite in un metodo statico separato. I numeri di riga non fanno parte del codice.

```java
01  interface Device {
02      void print(String text);
03      String scan();
04  }
05  class PrintOnly implements Device {
06      public void print(String text) { IO.println(text); }
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

<details>
<summary>Mostra la soluzione dell’esercizio 15</summary>

### 1. Scelta multipla: A

La chiamata a `print` stampa `Report`; `scan` non viene invocato.

- **B errata:** l’eccezione appartiene a un altro metodo.
- **C errata:** un metodo che termina sempre con `throw` non deve raggiungere un `return`.
- **D errata:** il compilatore controlla dichiarazioni e corpi, non la capacità fisica di acquisire documenti.

### 2. Risposta aperta

```java
interface Printer { void print(String text); }
interface ScannerDevice { String scan(); }
String acquire(ScannerDevice device);
```

Un dispositivo multifunzione implementa entrambe le interfacce; il servizio di acquisizione dipende solo da `ScannerDevice`. È valida anche un’interfaccia aggregata che estenda le due capacità, senza imporla ai dispositivi monofunzione.

</details>

---

# Esercizio 16 — Pulsante di conteggio

**Tempo indicativo: 5–7 minuti · Java 25**

Un dispositivo conta le pressioni di un pulsante mediante un oggetto `ClickCounter`. Il contatore parte da zero, `click` aggiunge un evento e `reset` azzera il valore. L'operazione `undo` annulla un evento se presente; quando il valore è zero deve lasciarlo invariato. Il valore del contatore non deve mai diventare negativo.

Si considerano soltanto sequenze brevi, senza overflow e senza concorrenza. Il frammento appartiene a una classe di test, con `ClickCounter` come classe annidata statica; gli import sono omessi. `@Test` identifica un test e `assertEquals(atteso, effettivo)` confronta i valori. I numeri di riga non fanno parte del codice.

```java
01  static class ClickCounter {
02      private int value;
03      int value() { return value; }
04      void click() { value++; }
05      void reset() { value = 0; }
06      void undo() {
07          if (value >= 0) {
08              value--;
09          }
10      }
11  }
12
13  @Test
14  void cancelOneEvent() {
15      ClickCounter counter = new ClickCounter();
16      counter.click();
17      counter.undo();
18      assertEquals(0, counter.value());
19  }
20
21  @Test
22  void clearEvents() {
23      ClickCounter counter = new ClickCounter();
24      counter.click();
25      counter.click();
26      counter.reset();
27      assertEquals(0, counter.value());
28  }
```

## 1. Scelta multipla

Qual è l'esito dei test mostrati?

- **A.** Passano entrambi.
- **B.** Passa soltanto `cancelOneEvent`.
- **C.** Passa soltanto `clearEvents`.
- **D.** Falliscono entrambi.

## 2. Risposta aperta

I test forniti bastano a verificare l'invariante per tutte le operazioni ammesse? In **1–3 frasi**, indica un'ulteriore breve sequenza di chiamate, il valore atteso e quello effettivo che motivano la tua valutazione. Non scrivere codice.

<details>
<summary>Mostra la soluzione dell’esercizio 16</summary>

### 1. Scelta multipla: A

Nel primo test il valore passa da `0` a `1` e poi a `0`. Nel secondo passa da `0` a `2`, poi `reset` lo porta a `0`.

- **B errata:** anche l'azzeramento soddisfa l'asserzione.
- **C errata:** annullare un evento esistente porta correttamente da `1` a `0`.
- **D errata:** nessuno dei due test chiama `undo` quando il valore è zero.

### 2. Risposta aperta

No: su un contatore appena creato, una chiamata a `undo` dovrebbe lasciare `0`, ma produce `-1` perché la condizione accetta anche lo zero. I test mostrano il comportamento di alcune sequenze, ma non verificano la transizione da zero mediante `undo`.

**Alternativa valida:** `click`, `undo`, `undo`, oppure `click`, `reset`, `undo`: in entrambi i casi il valore finale atteso è `0`, quello effettivo è `-1`.

</details>

---

# Esercizio 17 — Richiesta di prelievo

**Tempo indicativo: 5–7 minuti · Java 25**

Un conto di pagamento consente prelievi soltanto entro il saldo disponibile. Se l'importo supera il saldo, `withdraw` deve lanciare `IllegalArgumentException` e lasciare invariato il conto. Un prelievo accettato deve invece ridurre il saldo dell'importo richiesto. Il test seguente rappresenta una richiesta rifiutata.

Saldo iniziale e importi sono interi non negativi espressi in centesimi; non vi sono commissioni, overflow o concorrenza. Il frammento appartiene a una classe di test e `Account` è una classe annidata statica. `@Test` identifica un test; `assertThrows(T.class, azione)` passa se l'azione lancia un'eccezione di tipo `T` o di un suo sottotipo. `assertEquals(atteso, effettivo)` confronta i valori; se un'asserzione fallisce, il test termina. Import omessi; i numeri di riga non fanno parte del codice.

```java
01  static class Account {
02      private int balance;
03      Account(int initialBalance) { balance = initialBalance; }
04      int balance() { return balance; }
05      void withdraw(int amount) {
06          balance -= amount;
07          if (balance < 0) {
08              throw new IllegalArgumentException("Insufficient funds");
09          }
10      }
11  }
12
13  @Test
14  void rejectedWithdrawal() {
15      Account account = new Account(100);
16      assertThrows(IllegalArgumentException.class,
17              () -> account.withdraw(150));
18      assertEquals(100, account.balance());
19  }
```

## 1. Scelta multipla

Quale descrizione dell'esecuzione del test è corretta?

- **A.** Entrambe le asserzioni passano: l'eccezione annulla automaticamente le modifiche.
- **B.** `assertThrows` fallisce perché `withdraw` non dichiara `throws`.
- **C.** `assertThrows` passa; la seconda asserzione non viene eseguita perché l'eccezione esce dal test.
- **D.** `assertThrows` passa; `assertEquals` fallisce perché il saldo è `-50`.

## 2. Risposta aperta

Un revisore propone di conservare soltanto `assertThrows` nel test e di verificare il saldo solo nei test dei prelievi accettati. Valuta la proposta rispetto al contratto, in **1–3 frasi**, senza correggere il codice.

<details>
<summary>Mostra la soluzione dell’esercizio 17</summary>

### 1. Scelta multipla: D

Il metodo sottrae prima di verificare il saldo: ottiene `-50` e poi lancia l'eccezione attesa. `assertThrows` la intercetta, quindi l'esecuzione prosegue e il confronto con `100` fallisce.

- **A errata:** un'eccezione non esegue un rollback automatico delle assegnazioni.
- **B errata:** `IllegalArgumentException` è unchecked e non deve essere dichiarata nella firma.
- **C errata:** l'eccezione attesa è gestita da `assertThrows`, non abbandona il metodo di test.

### 2. Risposta aperta

La proposta elimina la verifica di una parte esplicita del contratto: il conto deve restare invariato dopo un rifiuto. I test dei prelievi accettati non verificano questo percorso; occorre osservare il saldo anche dopo l'operazione che lancia l'eccezione.

</details>

---

# Esercizio 18 — Contatti con lo stesso cognome

**Tempo indicativo: 5–7 minuti · Java 25**

Una rubrica deve restituire tutti i contatti con il cognome richiesto, nell'ordine in cui sono memorizzati. I contatti possono avere lo stesso cognome ma nomi diversi. Una ricerca senza corrispondenze deve restituire una lista vuota. Il team vuole usare i test del contratto anche per una futura implementazione della rubrica basata su array.

Rubrica, contatti e stringhe sono non nulli; il confronto distingue maiuscole e minuscole. Il frammento appartiene a una classe di test, con `Directory` come classe annidata statica. Gli import sono omessi. `@Test` identifica un test; `assertEquals(atteso, effettivo)` confronta le liste per contenuto e ordine, usando `equals` sui contatti. Il record fornisce l'uguaglianza per valori. I numeri di riga non fanno parte del codice.

```java
01  record Person(String name, String lastname) {}
02  static class Directory {
03      private final List<Person> people;
04      Directory(List<Person> people) { this.people = people; }
05      List<Person> find(String lastname) {
06          List<Person> found = new ArrayList<>();
07          for (Person person : people) {
08              if (person.lastname().equals(lastname)) {
09                  found.add(person);
10                  break;
11              }
12          }
13          return found;
14      }
15  }
16
17  @Test
18  void searchContact() {
19      Person ada = new Person("Ada", "Rossi");
20      Directory book = new Directory(List.of(ada));
21      assertEquals(List.of(ada), book.find("Rossi"));
22      assertEquals(List.of(), book.find("Verdi"));
23  }
24
25  @Test
26  void searchSharedLastname() {
27      Person ada = new Person("Ada", "Rossi");
28      Person lino = new Person("Lino", "Rossi");
29      Directory book = new Directory(List.of(ada, lino));
30      assertEquals(List.of(ada, lino), book.find("Rossi"));
31  }
```

## 1. Scelta multipla

Qual è l'esito dei due test?

- **A.** Passano entrambi perché i due contatti hanno lo stesso cognome.
- **B.** Passa soltanto `searchContact`.
- **C.** Passa soltanto `searchSharedLastname`.
- **D.** Falliscono entrambi perché le liste vengono confrontate per identità.

## 2. Risposta aperta

Per la futura implementazione basata su array, quali parti di questi test devono rimanere indipendenti dalla rappresentazione interna? Rispondi in **1–3 frasi**, distinguendo preparazione della rubrica e comportamento verificato, senza implementare classi o test.

<details>
<summary>Mostra la soluzione dell’esercizio 18</summary>

### 1. Scelta multipla: B

Il primo test verifica una corrispondenza e un'assenza, entrambe gestite come richiesto. Nel secondo, il `break` interrompe la ricerca dopo `ada`: la lista effettiva è `[ada]`, non `[ada, lino]`.

- **A errata:** il contratto richiede tutti i contatti, non un rappresentante per cognome.
- **C errata:** la seconda ricerca perde il secondo contatto, mentre il primo test passa.
- **D errata:** `List.equals` confronta contenuto e ordine; non richiede che le liste siano lo stesso oggetto.

### 2. Risposta aperta

Devono rimanere gli stessi dati logici e le stesse aspettative: tutti i contatti corrispondenti nello stesso ordine, oppure una lista vuota. Può cambiare il modo di costruire e popolare la rubrica, ma le asserzioni devono usare l'operazione pubblica di ricerca, non ispezionare array o liste interne; una comune interfaccia consentirebbe di riutilizzare le verifiche.

</details>
