

## Descrizione
Questo repository contiene il progetto relativo all'integrazione e ai test software. L'obiettivo del progetto è valutare diverse metodologie di testing, tra cui:
- **Specification-based testing**
- **Structural testing**
- **PIT Mutation Testing**
- **Property-Based Testing (PBT)**

## Struttura del Progetto
- `src/main` - Contiene il codice sorgente dei metodi analizzati.
- `src/test/` - Contiene gli script per l'automazione dei test.
- `reports/` - Contiene i report generati per l'analisi della copertura del codice.

## Requisiti
Per eseguire il progetto, assicurarsi di avere installato:
- Java 11+
- Maven/Gradle
- IntelliJ IDEA (opzionale, ma consigliato)

## Esecuzione dei Test
Per eseguire i test:
```bash
mvn test
```
oppure, se si utilizza Gradle:
```bash
gradle test
```

## Metodologie di Testing
### Specification-Based Testing
Questa tecnica si basa sulla verifica delle specifiche del software, identificando i casi di test sulla base dei requisiti. Gli step seguiti sono:
1. Analisi dei requisiti
2. Identificazione dei casi limite
3. Ideazione dei casi di test
4. Automazione dei test
5. Analisi e miglioramento della suite di test

### Structural Testing
Questo metodo si concentra sull'analisi del codice sorgente per garantire la copertura totale del codice, includendo:
- Line coverage
- Branch coverage
- Condition coverage

### PIT Mutation Testing
Il **PIT Mutation Testing** introduce piccole modifiche al codice per verificare la robustezza della suite di test. I risultati sono disponibili in `reports/pit/index.html`.

### Property-Based Testing (PBT)
Il **PBT** genera input casuali per verificare il comportamento generale della funzione e identificare edge cases. La generazione degli input casuali avviene con 500 test randomizzati.

## Risultati e Report
Tutti i report dei test sono disponibili nella directory `reports/`, inclusi:
- Code coverage (`htmlReports/index.html`)
- PIT Mutation Testing (`pit/index.html`)
- Risultati specifici dei test 

## Autore
- **Mariniuc George Alexandru**
- **Università di Bari - Corso: Integrazione e Test di Sistemi Software (2022/23)**

## Contributi
Se vuoi contribuire al progetto:
1. Fai un fork del repository
2. Crea un nuovo branch (`feature/nome-feature`)
3. Implementa le modifiche e crea una pull request

## License
Questo progetto è rilasciato sotto licenza MIT.

