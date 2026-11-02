# Attività future: didattica Java nell'era dell'AI

Le slide aggiornate costituiscono la base teorica. Il prossimo passo è intervenire soprattutto su laboratorio e valutazione, spostando il centro dalla produzione di codice alla capacità di comprenderlo, modificarlo e verificarlo.

## 1. Affiancare un progetto trasversale alle slide

- [ ] Scegliere un progetto esistente, per esempio biblioteca o rubrica, come filo conduttore.
- [ ] Definire incrementi collegati ai moduli del corso, senza sostituire l'attuale indice per argomenti.
- [ ] Alternare nuovi requisiti, correzioni di bug e refactoring di codice già funzionante.
- [ ] Usare le slide come riferimento per motivare le decisioni incontrate nel progetto.

Il ciclo di lavoro da esercitare è: chiarire un requisito → leggere il codice esistente → apportare una modifica → verificare le conseguenze.

## 2. Preparare attività di diagnosi non guidata

- [ ] Creare piccoli progetti con un comportamento osservabile sbagliato, test incompleti e una modifica richiesta.
- [ ] Non anticipare nel titolo il concetto responsabile del problema, come unboxing, aliasing o race condition.
- [ ] Separare consegne, indizi e soluzioni, lasciando spazio a previsione e indagine prima della spiegazione.
- [ ] Chiedere una riproduzione minima del problema e un test che fallisca prima della correzione.

L'obiettivo è verificare se lo studente sa individuare il problema, non soltanto riconoscerlo dopo che è stato nominato.

## 3. Dare un peso diverso ai contenuti

- [ ] Definire per ogni modulo quali competenze Core devono essere applicate autonomamente.
- [ ] Rendere Note e Reference effettivamente consultabili durante le attività appropriate, senza trasformarle in memoria obbligatoria.
- [ ] Allineare tempo in aula, esercizi ed esame a questa distinzione.
- [ ] Ridurre il tempo sugli inventari di API per aumentarlo su contratti, riferimenti, mutabilità, errori, risorse e concorrenza.

## 4. Integrare l'AI oltre la ricerca di errori

- [ ] Progettare attività in cui l'AI aiuta a comprendere codice sconosciuto.
- [ ] Far confrontare implementazioni alternative e relativi compromessi.
- [ ] Usare l'assistente per proporre casi di test, verificandoli rispetto ai requisiti e non solo all'implementazione generata.
- [ ] Consentire la generazione di una prima soluzione, richiedendo poi revisione, test ed evidenze.
- [ ] Far confrontare spiegazioni dell'assistente, documentazione ufficiale e comportamento osservato.
- [ ] Definire regole d'uso dell'AI, incluse tutela dei dati e trasparenza sul suo contributo.

Non insegnare soltanto a diffidare delle proposte generate: insegnare a usarle, valutarle e correggerle.

## 5. Strutturare il laboratorio di ogni modulo

- [ ] Breve lettura e previsione del comportamento di codice senza assistente.
- [ ] Problema aperto con AI consentita e requisiti da chiarire.
- [ ] Consegna della modifica, dei test e delle evidenze di verifica.
- [ ] Piccola variazione finale da spiegare o realizzare dal vivo.

## 6. Aggiornare i criteri di valutazione

- [ ] Valutare la comprensione del contratto e delle assunzioni.
- [ ] Valutare la qualità dei test, inclusi casi limite e percorsi di errore.
- [ ] Verificare che lo studente sappia spiegare le modifiche e gli eventuali effetti collaterali.
- [ ] Chiedere quali suggerimenti dell'AI sono stati accettati, modificati o scartati, e perché.
- [ ] Distinguere i risultati osservati dalle garanzie effettive: passare alcuni test non prova la correttezza generale, soprattutto in concorrenza.

La domanda guida non è «hai scritto tutto tu?», ma:

> Sai spiegare cosa garantisce questa soluzione, cosa non garantisce e come l'hai verificata?
