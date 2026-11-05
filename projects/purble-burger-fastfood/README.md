# 🍔 Purble Burger Fastfood

A single-player JavaFX cooking game. Move the tray between ingredient stations, reproduce the displayed orders, and deliver **five correct burgers to earn five stars and win**.

The game runs entirely on your desktop: no database, Docker, backend, account, API key, or configuration file is needed.

## Contents

- [Quick start](#quick-start)
- [Requirements](#requirements)
- [How to play](#how-to-play)
- [Display and sound](#display-and-sound)
- [Tests](#tests)
- [Troubleshooting](#troubleshooting)
- [Project structure](#project-structure)
- [Limitations](#limitations)

## Quick start

```bash
mvn clean javafx:run
```

Maven compiles the application and opens the start menu. Click **Play** to begin; close the main window to exit.

## Requirements

| Requirement | Details |
|---|---|
| Java | **JDK 25**, not just a Java runtime. |
| Build tool | **Maven 3.6.3+**, available as `mvn`. |
| Desktop | Linux, Windows, or macOS with a graphical session. Linux also requires GTK 3 runtime libraries. |
| Screen space | Room for at least an **800 × 600** window. The initial game scene is 1000 × 700; allow extra room for window decorations. |
| Internet | Required for the first build to download dependencies. The game itself makes no network requests. |

Maven downloads **JavaFX 25.0.1** and the native libraries for your platform. No separate JavaFX SDK or Scene Builder installation is required.

## How to play

### Win condition and timer

- **Win with five stars:** all five burgers must be correct. Finishing with 0–4 stars is a defeat.
- Start with **60 seconds** on one shared countdown. Moving to another order does not reset it.
- Each delivery consumes one of the **five orders**, whether correct or incorrect.
- A correct burger earns **one star** and **20 extra seconds** if another order remains.
- An incorrect burger earns no star or time bonus, but does not remove stars already earned.
- The game ends after the fifth delivery or when time expires. The final delivery grants no unused time bonus.

The order panel shows the requested layers, fries, sauce, order number, and current stars.

### Prepare a burger

1. Read the order preview.
2. Use **◀ / ▶** to move the tray to the required station.
3. Choose the bread, then add only the requested layers **from bottom to top**:

   ```text
   meat → cheese → pickles → tomato → onion → lettuce
   ```

4. Add fries and the requested sauce according to the checkboxes below the preview.
5. Click **Deliver**. Use the arrows to return to Buns for the next burger; the tray does not return automatically.

**Layer order matters.** Skip unrequested ingredients and revisit stations as needed—for example, add cheese before returning to Veggies. Baguette orders use ham; for other bread shapes, follow the displayed meat image.

Bread automatically creates the visible top and bottom. Ingredient clicks add layers rather than toggling them off: unwanted or duplicate layers can make a burger incorrect. Sauce must match exactly; an extra sauce also makes the order incorrect.

### Stations and controls

Ingredient buttons only work when the tray is at their station.

| Station | Ingredients |
|---|---|
| **Buns** | Classic, Baguette, or Bagel bread |
| **Meat** | Patty or ham |
| **Veggies** | Lettuce, onion, tomato |
| **Additions** | Cheese, pickles |
| **Fries & Dips** | Fries, ketchup, mustard |

| Control | Action |
|---|---|
| **◀ / ▶** | Move between stations. |
| **Deliver** | Submit the burger and clear the tray. Available at any station. |
| **Restart** | Reset orders, stars, tray, and countdown to start a new game. |
| **Sound: on/off** | Enable or mute sound effects. |
| **Play Again / Try Again** | Start a new game from the result screen. |

After the game ends, gameplay controls are disabled. **Restart** remains available even if you dismiss the result dialog.

## Display and sound

### Resizing

Enlarge or maximize the window to grow the game. Images, text, controls, and click targets scale together while preserving proportions.

The interface uses a centered 1000 × 700 logical playing field. Wide or tall windows may leave extra space around it; the background fills the window and may be cropped, but is not stretched out of proportion. Original image resolutions are preserved.

### Sound effects

Eight short, locally generated WAV effects accompany start/restart, tray movement, ingredient selection, correct and incorrect deliveries, the ten-second warning, and victory or defeat.

Use **Sound: on/off** in the top-right corner to mute or enable them. Audio is optional: if playback initialization fails, the game continues without sound. No external recordings or audio downloads are used.

## Tests

Run the following commands from this project's directory. From the repository root, add `-f projects/purble-burger-fastfood/pom.xml` after `mvn`.

### Default suite

```bash
mvn clean verify
```

Runs **23 tests** without requiring a display or audio device. Coverage includes order matching, exact sauce requirements, layer order, protected model collections, scoring, time bonuses, game-state guards, expiry, restart, controller actions, and audio resource integrity. Seven optional JavaFX tests are skipped.

### Full suite, including JavaFX

```bash
mvn -Djavafx.smoke=true verify
```

Runs **all 30 tests** in a graphical desktop session. Additional checks cover image caching, proportional resizing, preview rendering, gameplay controls, timer labels, the five-star victory requirement, result-dialog dismissal/restart, and clock stop/restart behavior.

For headless Linux with Xvfb installed:

```bash
xvfb-run -a mvn -Djavafx.smoke=true verify
```

Automated tests intentionally disable audio playback; resource validation does not prove that speakers produce sound.

### Manual checklist

- Launch the game and check the logo, backgrounds, and order preview.
- Resize the window and verify that controls stay visible and clickable.
- Check that only the current station accepts ingredient clicks.
- Compare correct and incorrect deliveries: both advance the order, but only correct ones award a star and bonus time for the next order.
- Verify victory with five correct burgers and defeat with fewer than five stars or an expired timer.
- Check that Restart resets the whole session, including after dismissing the result dialog.
- Listen to the action/result cues and test the sound toggle.
- Close the main window and confirm the application exits.

## Troubleshooting

| Problem | What to check |
|---|---|
| Compilation fails with a Java version error | Run `java -version` and `mvn -version`; Maven must use JDK 25. |
| JavaFX cannot open a window | Use a graphical desktop session. On Linux, check GTK 3 libraries; use Xvfb for headless GUI tests. |
| An ingredient button does nothing | Move the tray to that ingredient's station first. |
| A burger is rejected | Check bread, layer order, duplicates, fries, and the exact sauce. |
| Controls look too small | Enlarge or maximize the window. Scaling is limited by the smaller window dimension. |
| No sound | Check the in-game toggle, system volume, and output device. Playback initialization failures are reported in the terminal. |

## Project structure

```text
src/main/java/com/project/burger/
 ├─ audio/       Cached sound effects and safe mute/playback handling
 ├─ model/       Burger, order, ingredient, and bread data
 ├─ engine/      Order generation, progression, scoring, and countdown rules
 ├─ controller/  Station actions, tray navigation, and clock scheduling
 └─ view/        Application lifecycle, layout, graphics, dialogs, and image cache
src/main/resources/
 ├─ assets/      27 PNG/JPEG images
 └─ sounds/      Eight generated PCM WAV effects
src/test/java/com/project/burger/
 └─ Model, engine, controller, audio, and optional JavaFX regression tests
```

### Implementation notes

- The UI is built in Java, not FXML. `MainApp` coordinates application/session lifecycle, `GameView` renders the screen, and `GameClock` schedules ticks.
- The engine owns countdown and final-state rules. Every restart uses the same session-reset path, without a static game-state flag.
- The order preview and validation share a canonical ingredient sequence.
- Resources load from the classpath (`/assets/...` and `/sounds/...`), not developer-specific filesystem paths.
- `ImageCache` reuses decoded images across views and refreshes; each `ImageView` remains a separate node. Opaque backgrounds use JPEG and transparent artwork uses PNG, without changing original pixel dimensions.

## Limitations

- Panels scale together rather than rearranging independently for different window shapes.
- Games, scores, and settings are not saved between runs.
- There is no pause action or online mode.
