package com.project.burger.audio;

import org.junit.jupiter.api.Test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SoundEffectsTest {
    @Test
    void everyEffectHasADistinctShortPcmWavResource() throws Exception {
        Set<String> paths = new HashSet<>();
        for (SoundEffects.Effect effect : SoundEffects.Effect.values()) {
            assertTrue(paths.add(effect.resource()));
            var resource = SoundEffects.class.getResource(effect.resource());
            assertNotNull(resource, effect.name());
            try (var audio = AudioSystem.getAudioInputStream(resource)) {
                assertEquals(AudioFormat.Encoding.PCM_SIGNED, audio.getFormat().getEncoding());
                assertEquals(16, audio.getFormat().getSampleSizeInBits());
                assertEquals(1, audio.getFormat().getChannels());
                assertEquals(44100f, audio.getFormat().getSampleRate());
                assertTrue(audio.getFrameLength() > 0);
                assertTrue(audio.getFrameLength() < 44100, "Effects should be shorter than one second");
            }
        }
        assertEquals(8, paths.size());
    }

    @Test
    void disabledEffectsNeedNoAudioDeviceAndCanBeStoppedSafely() {
        SoundEffects sound = new SoundEffects(false);
        assertFalse(sound.isEnabled());
        for (SoundEffects.Effect effect : SoundEffects.Effect.values()) {
            assertDoesNotThrow(() -> sound.play(effect));
        }
        assertDoesNotThrow(sound::stop);
        sound.setEnabled(true);
        assertTrue(sound.isEnabled());
        sound.setEnabled(false);
        assertFalse(sound.isEnabled());
    }
}
