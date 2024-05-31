package task3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TextModel {

    @Test
    public void testHair(){
        Human human = new Human("Артур", "мужчина", null);
        assertThrows(RuntimeException.class, () -> human.move("медленно"));
    }

    @Test
    public void testHair2(){
        Human.Hair hair = new Human.Hair("Черные", 5);
        assertThrows(NullPointerException.class,() -> hair.moveHair(null));
    }

    @Test
    public void test3() {
        Human human = new Human("Артур", "мужчина", new Human.Hair("Черные", 5));
        assertEquals("Черные", human.getHair().getColor());
        assertEquals(5, human.getHair().getLength());
    }

}
