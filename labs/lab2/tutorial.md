# Lab 2 — Docker and PostgreSQL from the CLI

## Goal and boundaries

Install and start a local PostgreSQL server, create the contacts table, and execute CRUD operations using `psql`. Then use two small JDBC console programs to import and read names. **Do not modify the JavaFX application to connect to it yet.** Read [theory.md](theory.md) first.

## 1. Install Docker and Compose

Choose **one** installation method for your operating system:

* **Windows:** install [Docker Desktop](https://docs.docker.com/desktop/setup/install/windows-install/) with its supported WSL 2 backend. Complete its virtualization/WSL prerequisites, start Docker Desktop, and use a WSL terminal for the POSIX commands below.
* **macOS:** install [Docker Desktop](https://docs.docker.com/desktop/setup/install/mac-install/) for your CPU architecture, then start it.
* **Linux:** install [Docker Engine](https://docs.docker.com/engine/install/) and the [Compose plugin](https://docs.docker.com/compose/install/linux/) following your distribution's instructions, or use [Docker Desktop for Linux](https://docs.docker.com/desktop/setup/install/linux-install/). Avoid mixing installations without understanding their Docker contexts.

For example, on an Ubuntu machine using Ubuntu's packaged Docker Engine (not Docker Desktop), the installation is typically:

```bash
sudo apt update
sudo apt install docker.io docker-compose-v2
sudo systemctl enable --now docker
```

Package names differ on other distributions. Follow the official installation path if those packages are unavailable. On Engine installations, your user must have permission to access the daemon: use `sudo docker ...` consistently if needed, or follow the official [post-installation instructions](https://docs.docker.com/engine/install/linux-postinstall/). Docker-group membership grants root-level privileges; do not change permissions on the socket to make it world-writable.

Verify:

```bash
docker version
docker compose version
docker run --rm hello-world
```

`docker version` must show both client and server information. A client-only installation or a stopped daemon is insufficient. Use **Compose V2**, written `docker compose`, not the legacy `docker-compose` command.

## 2. Inspect the supplied Compose file

From the repository root, move into the lab directory:

```bash
cd labs/lab2
docker compose config
```

Read [`compose.yaml`](compose.yaml). Identify:

1. Image `postgres:17-alpine`.
2. Database and user `addressbook` with local exercise password `local-course-password`.
3. Host binding `127.0.0.1:5432` to container port `5432`.
4. Named volume `postgres-data`, mounted at PostgreSQL 17's data directory.
5. Health check using `pg_isready`.

The explicit project name `address-book-course` means that this lab and lab3 share the same service/volume identity. Do not start both copies concurrently.

No pgAdmin or host PostgreSQL installation is required. The container includes `psql`. These credentials and the privileged initialization user are acceptable for this isolated exercise, not a production deployment.

## 3. Start PostgreSQL

```bash
docker compose up -d --wait
docker compose ps
docker compose logs --tail=30 postgres
```

The service should become healthy. The first run downloads the image and initializes the volume. Later starts reuse the existing data.

If host port `5432` is already occupied, **do not stop an unrelated database**. For this shell session on Linux/macOS/WSL, use:

```bash
export POSTGRES_PORT=55432
docker compose up -d --wait
```

In PowerShell, set `$env:POSTGRES_PORT = '55432'`. Internal `psql` commands remain unchanged; in lab3 the Java JDBC URL must then use host port `55432` too.

## 4. Open the SQL client

```bash
docker compose exec postgres psql -U addressbook -d addressbook
```

You should see the `addressbook=#` prompt. This local connection runs inside the container; it does not test password authentication through the host port.

Try these `psql` commands:

```text
\conninfo
\l
\dt
```

They show connection details, databases, and tables. Backslash commands belong to `psql`, not SQL, and do not need semicolons. A new course database has no application tables yet.

Leave the client with `\q`.

## 5. Create the table and load sample contacts

Read [`sql/01-schema.sql`](sql/01-schema.sql) before running it. Then execute:

```bash
docker compose exec -T postgres psql -U addressbook -d addressbook -v ON_ERROR_STOP=1 < sql/01-schema.sql
docker compose exec -T postgres psql -U addressbook -d addressbook -v ON_ERROR_STOP=1 < sql/02-seed.sql
```

`-T` disables the pseudo-terminal for redirected input. `ON_ERROR_STOP=1` makes a script stop and report a nonzero exit code at the first SQL error.

**PowerShell alternative**, also usable on other systems without shell input redirection:

```bash
docker compose cp sql/01-schema.sql postgres:/tmp/01-schema.sql
docker compose cp sql/02-seed.sql postgres:/tmp/02-seed.sql
docker compose exec postgres psql -U addressbook -d addressbook -v ON_ERROR_STOP=1 -f /tmp/01-schema.sql
docker compose exec postgres psql -U addressbook -d addressbook -v ON_ERROR_STOP=1 -f /tmp/02-seed.sql
```

The schema script never drops a table. `CREATE TABLE IF NOT EXISTS` does not upgrade an older/incompatible schema; this is a creation script, not a migration framework.

The optional seed script avoids duplicating these exact sample contacts when rerun sequentially. It does **not** establish name/address uniqueness as an application rule. Rerunning it later can reintroduce sample contacts you deliberately deleted, so do not seed automatically on each application start.

## 6. Inspect data interactively

Reopen `psql`, then run:

```sql
SELECT id, first_name, last_name, postal_code, city
FROM persons
ORDER BY last_name, first_name, id;

SELECT first_name, postal_code FROM persons WHERE postal_code = '00118';
SELECT count(*) FROM persons;
```

On a fresh database, expect six contacts; Luca's postal code must remain `00118`. Inspect the definition with `\d persons`. Use `\pset pager off` if long output is opening a pager; if already in a pager, `q` returns to `psql`.

## 7. Insert, update, and delete your own practice contact

Still inside `psql`, insert a separate contact:

```sql
INSERT INTO persons (first_name, last_name, street, postal_code, city, birthday)
VALUES ('Ada', 'CLI Demo', 'Via Test 1', '00118', 'Roma', DATE '2000-02-29')
RETURNING id;
```

Write down the returned ID. **Do not assume it is 7**: identities can have gaps. In each command below, replace `123` with your actual ID.

```sql
SELECT * FROM persons WHERE id = 123;
UPDATE persons SET city = 'Modena' WHERE id = 123;
SELECT id, first_name, city FROM persons WHERE id = 123;
DELETE FROM persons WHERE id = 123;
SELECT * FROM persons WHERE id = 123;
```

Expect one inserted row, one updated row, one deleted row, and no row in the final query. The six original contacts are unaffected.

**Before every write:** check the target with a `SELECT`. Leaving out `WHERE` in an update/delete changes the whole table.

## 8. Try rollback and a repeatable script

Pick an existing contact's ID. Replace `123` below with that ID:

```sql
BEGIN;
UPDATE persons SET city = 'Temporary city' WHERE id = 123;
SELECT city FROM persons WHERE id = 123;
ROLLBACK;
SELECT city FROM persons WHERE id = 123;
```

The final query should show the original city. You can also try inserting an empty first name: the database rejects it with a check-constraint error. If you do this inside an explicit transaction, execute `ROLLBACK` afterward.

Exit with `\q`, then run the supplied repeatable practice script:

```bash
docker compose exec -T postgres psql -U addressbook -d addressbook -v ON_ERROR_STOP=1 < sql/03-cli-practice.sql
```

It captures a generated ID using `psql`'s `\gset`, then reads, updates, and deletes only that contact within a rolled-back transaction. It leaves application rows unchanged, although the identity sequence advances. For PowerShell, copy this script into the container and use `-f`, as in step 5.

## 9. Verify persistence

```bash
docker compose down
docker compose up -d --wait
docker compose exec postgres psql -U addressbook -d addressbook -c "SELECT count(*) FROM persons;"
```

Your data should still exist. The container was recreated, but the named volume remained. If you left a practice contact in the database, it remains too.

* `stop`: stops containers without removing them.
* `down`: stops/removes this project's containers and network, retaining named volumes by default.
* **`down --volumes` deletes this project's named volumes and its database data. Do not use it as a routine shutdown command.** Back up any data you need before deliberately resetting a disposable exercise database.

## 10. Prepare two small JDBC console programs

Now perform a little database work from Java, **without JavaFX or a repository layer**. Use the supplied [jdbc-examples/](jdbc-examples/) Maven project as reference, or recreate its structure in a separate working directory:

```text
jdbc-examples/
 ├─ pom.xml
 ├─ names.txt
 └─ src/main/java/com/nbicocchi/jdbc/
     ├─ Database.java
     ├─ ImportNames.java
     └─ ReadNames.java
```

Use JDK 25 and Maven; check the Java version reported by `mvn -version`. Copy the [minimal POM](jdbc-examples/pom.xml): its only library dependency is the PostgreSQL JDBC driver. The compiler plugin targets Java 25; `exec-maven-plugin` runs either `main` class with the driver on its classpath. There is no `module-info.java` and no JavaFX runtime here.

Copy the short connection helper [Database.java](jdbc-examples/src/main/java/com/nbicocchi/jdbc/Database.java). It calls `DriverManager.getConnection`, using these defaults:

| Environment variable | Default |
|---|---|
| `ADDRESSBOOK_DB_URL` | `jdbc:postgresql://localhost:5432/addressbook` |
| `ADDRESSBOOK_DB_USER` | `addressbook` |
| `ADDRESSBOOK_DB_PASSWORD` | `local-course-password` |

They match Compose. If you selected host port `55432`, also set `export ADDRESSBOOK_DB_URL=jdbc:postgresql://localhost:55432/addressbook` (PowerShell: `$env:ADDRESSBOOK_DB_URL = 'jdbc:postgresql://localhost:55432/addressbook'`) in the shell used to run Maven. `POSTGRES_PORT` alone changes Compose's port mapping, not the Java connection URL.

We use a **separate table `names`** for these examples. It stores distinct text labels, not full contacts: deduplicating names is an exercise policy, **not** a rule that two people cannot share a name. The `persons` table and all your lab2 contacts remain unchanged.

## 11. Create a table and import names from a file

Create UTF-8 file `names.txt`, with one name per line:

```text
Anna
D'Angelo
Zoë

Anna
```

The apostrophe and accented character intentionally test parameter binding and UTF-8 file input. Blank lines are ignored; outer whitespace is stripped. Identical names, including duplicates in the same file or in a previous import, are skipped using a unique constraint and `ON CONFLICT ... DO NOTHING`. Comparisons are case-sensitive with the supplied PostgreSQL configuration.

Save [ImportNames.java](jdbc-examples/src/main/java/com/nbicocchi/jdbc/ImportNames.java) in package `com.nbicocchi.jdbc`. Its main pieces are:

```java
try (BufferedReader reader = Files.newBufferedReader(Path.of(args[0]), StandardCharsets.UTF_8);
     Connection connection = Database.openConnection()) {
    connection.setAutoCommit(false);
    try {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS names (
                        id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        name VARCHAR(80) NOT NULL UNIQUE CHECK (btrim(name) <> '')
                    )
                    """);
        }

        int inserted = 0;
        String sql = "INSERT INTO names (name) VALUES (?) ON CONFLICT (name) DO NOTHING";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String name = line.strip();
                if (!name.isEmpty()) {
                    statement.setString(1, name);
                    inserted += statement.executeUpdate();
                }
            }
        }
        connection.commit();
        IO.println("Inserted " + inserted + " new names.");
    } catch (IOException | SQLException | RuntimeException failure) {
        try {
            connection.rollback();
        } catch (SQLException rollbackFailure) {
            failure.addSuppressed(rollbackFailure);
        }
        throw failure;
    }
}
```

This is the method-body excerpt; the linked complete file includes imports, the class, `main`, and argument validation. Read it alongside the JDBC concepts in the theory:

* The reader consumes the file line by line rather than loading it all into memory.
* A plain `Statement` is sufficient for the fixed table definition; no user data is included in that SQL.
* The insert uses a `PreparedStatement` and binds each value at position **1**. Never concatenate a name into SQL.
* `executeUpdate()` returns `1` for an inserted row and `0` for a duplicate skipped by `ON CONFLICT`.
* Table creation and all inserts share one transaction. PostgreSQL can roll back this table creation too if the table was new.
* A file-reading or SQL failure triggers rollback and is rethrown. A rollback failure is attached rather than replacing the original cause.
* Try-with-resources closes the reader, connection, and statements.

From **`labs/lab2/`**, run:

```bash
mvn -f jdbc-examples/pom.xml compile exec:java -Dexec.mainClass=com.nbicocchi.jdbc.ImportNames -Dexec.args="jdbc-examples/names.txt"
```

On a fresh `names` table, expect `Inserted 3 new names.`. Run the same command again: expect `Inserted 0 new names.`. Existing rows are never deleted or replaced. `CREATE TABLE IF NOT EXISTS` does not migrate an incompatible existing table definition.

**File paths:** the argument is resolved relative to the shell's working directory, not relative to the POM selected with `-f`. If you instead `cd jdbc-examples`, use `mvn compile exec:java -Dexec.mainClass=com.nbicocchi.jdbc.ImportNames -Dexec.args="names.txt"`. On PowerShell, quote each complete property argument if necessary, for example `"-Dexec.mainClass=com.nbicocchi.jdbc.ImportNames"` and `"-Dexec.args=jdbc-examples/names.txt"`.

## 12. Read the table with a ResultSet

Save [ReadNames.java](jdbc-examples/src/main/java/com/nbicocchi/jdbc/ReadNames.java):

```java
package com.nbicocchi.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadNames {
    public static void main(String[] args) throws SQLException {
        String sql = "SELECT id, name FROM names ORDER BY id";
        try (Connection connection = Database.openConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rows = statement.executeQuery()) {
            while (rows.next()) {
                IO.println(rows.getLong("id") + " | " + rows.getString("name"));
            }
        }
    }
}
```

From **`labs/lab2/`**:

```bash
mvn -f jdbc-examples/pom.xml compile exec:java -Dexec.mainClass=com.nbicocchi.jdbc.ReadNames
```

Expect Anna, D'Angelo, and Zoë with their IDs. IDs need not be consecutive: duplicate attempts and rolled-back transactions can consume identity values. The reader never creates a table: if you run it before the first successful import, PostgreSQL reports that `names` does not exist. This is an error, not an empty result.

Compare Java's output with:

```bash
docker compose exec postgres psql -U addressbook -d addressbook -c "SELECT id, name FROM names ORDER BY id;"
```

## 13. Check that a failed import leaves no partial rows

Create a separate UTF-8 file `rollback-names.txt` in `labs/lab2/` with these two lines (the second contains 90 characters, exceeding `VARCHAR(80)`):

```text
Rollback probe
123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
```

From `labs/lab2/`, run:

```bash
mvn -f jdbc-examples/pom.xml compile exec:java -Dexec.mainClass=com.nbicocchi.jdbc.ImportNames -Dexec.args="rollback-names.txt"
```

The command must fail because the second name is too long. Read the SQL error, then rerun `ReadNames`: the first line, `Rollback probe`, must **not** have been added either, assuming it did not already exist before this experiment. Existing rows remain unchanged. This is the difference between a whole-file transaction and auto-committing each insert.

Finally, the application in [lab1](../lab1/tutorial.md) still uses in-memory contacts and is unaffected. [Lab3](../lab3/tutorial.md) takes the next step: map complete contacts into Java objects, isolate SQL in a repository, and connect it to JavaFX.

## Acceptance checklist

* Docker client, daemon, and Compose V2 work.
* PostgreSQL becomes healthy and accepts `psql` connections.
* You can explain the table definition and identify its primary key.
* You can insert, read, update, and delete one contact without changing unrelated rows.
* `00118` retains its leading zeros.
* Rollback restores a modified value.
* Contacts survive `docker compose down` followed by `up`.
* Java imports a UTF-8 file with parameterized inserts and reads the result through a `ResultSet`.
* Rerunning an import does not duplicate identical names, and a failed import leaves no partial rows.
* You can explain why JavaFX still shows its in-memory sample list.

## Troubleshooting

| Problem | Check |
|---|---|
| Cannot connect to the Docker daemon | Start Docker Desktop or the Engine service; inspect permissions/context. |
| Published port is already allocated | Choose another `POSTGRES_PORT`; do not terminate an unrelated service. |
| Database service is unhealthy | Inspect `docker compose logs postgres`, disk space, and initialization errors. |
| Password changes have no effect | Existing volumes retain their original users/passwords; initialization variables are not migration commands. |
| `psql` shows `addressbook-#` | A SQL command is unfinished, often missing `;` or a closing quote; cancel with Ctrl+C. |
| `current transaction is aborted` | Issue `ROLLBACK` before continuing. |
| `relation persons does not exist` | Run the schema script in the intended database; inspect `\conninfo`. |
| JDBC cannot connect or authenticate | Check the host port, `ADDRESSBOOK_DB_*` settings, and PostgreSQL readiness; an internal `psql` session does not verify the host TCP/password path. |
| Import file is missing | Check the shell's working directory; Maven's `-f` does not change the path passed to Java. |
| `relation names does not exist` | Complete a successful import before running the reader. |
