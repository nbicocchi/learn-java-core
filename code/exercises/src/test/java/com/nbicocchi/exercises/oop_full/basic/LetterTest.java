package com.nbicocchi.exercises.oop_full.basic;

import com.nbicocchi.exercises.oop_basic.Letter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetterTest {

    @Test
    void getText() {
        Letter letter = new Letter("me", "you");
        letter.addLine("Hello");
        letter.addLine("World!");
        String dst = """
                Dear you:

                Hello
                World!
                
                Sincerely,
                
                me""";
        assertEquals(dst, letter.getText());
    }
}