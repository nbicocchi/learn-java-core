package com.project.burger.audio;

import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;

/** Small cached, locally generated cues. Audio failure must never stop a game. */
public final class SoundEffects {
    public enum Effect {
        START("start"), MOVE("move"), ADD("add"), CORRECT("correct"), WRONG("wrong"),
        TIME_LOW("time_low"), WIN("win"), GAME_OVER("game_over");

        private final String resource;

        Effect(String name) {
            resource = "/sounds/" + name + ".wav";
        }

        public String resource() {
            return resource;
        }
    }

    private final Map<Effect, AudioClip> clips = new EnumMap<>(Effect.class);
    private boolean enabled;
    private boolean available = true;

    public SoundEffects() {
        this(Boolean.parseBoolean(System.getProperty("burger.sound", "true")));
    }

    public SoundEffects(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled && available;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (!enabled) stop();
    }

    public void play(Effect effect) {
        if (!isEnabled()) return;
        try {
            AudioClip clip = clips.computeIfAbsent(effect, key -> {
                URL resource = SoundEffects.class.getResource(key.resource());
                if (resource == null) throw new IllegalStateException("Missing sound: " + key.resource());
                return new AudioClip(resource.toExternalForm());
            });
            clip.play(0.25);
        } catch (RuntimeException | LinkageError failure) {
            available = false;
            stop();
            System.err.println("Audio disabled: " + failure.getMessage());
        }
    }

    public void stop() {
        for (AudioClip clip : clips.values()) {
            try {
                clip.stop();
            } catch (RuntimeException | LinkageError ignored) {
                // Cleanup remains safe even when an audio backend is unavailable.
            }
        }
    }
}
