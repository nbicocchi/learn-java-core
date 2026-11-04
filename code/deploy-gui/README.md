# Temperature converter — JavaFX

Requires **JDK 25** and Maven, plus a graphical desktop.

From the repository root:

```bash
mvn -f code/deploy-gui/pom.xml clean verify
mvn -f code/deploy-gui/pom.xml javafx:run
```

The application uses `temperature-view.fxml` and `ConverterController`, with
`ConversionAction` for input/error handling and `TemperatureConverter` for the
tested conversion rules. Inputs use a dot for decimals. Non-numeric input,
non-finite values and temperatures below absolute zero show an error dialog
without changing the output field.

To build a distribution including its Java runtime:

```bash
mvn -f code/deploy-gui/pom.xml clean verify javafx:jlink
code/deploy-gui/target/app/bin/app
```

Distribute `target/app.zip`, not just the application JAR. The runtime image is
specific to the OS and architecture used to build it; build separately on each
target platform. On Windows, use `target/app/bin/app.bat`.

The JavaFX Maven plugin 0.0.8 can emit a deprecated `--compress=0` warning with
JDK 25 while building the image. This is a plugin compatibility warning, not a
failed build.
