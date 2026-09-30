package no.uib.inf101.yatzy.Model.score;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class IScoreCategoryTest {
    private static class DummyCategory implements IScoreCategory {
        @Override
        public String name() {
            return "Dummy";
        }
    }

    @Test
    public void testDefaultGetDisplayName() {
        IScoreCategory category = new DummyCategory();
        assertEquals("Dummy", category.getDisplayName());
    }

    @Test
    public void testDefaultIsUpperCategory() {
        IScoreCategory category = new DummyCategory();
        assertFalse(category.isUpperCategory());
    }
}
