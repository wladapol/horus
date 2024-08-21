import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WallTest {

    @Test
    void testFindBlockByColorNotFound() {
        //given
        Block blueBlock = new BlockImpl("blue", "wood");
        Block redBlock = new BlockImpl("red", "paper");
        Structure wall = new Wall(List.of(blueBlock, redBlock));

        //when
        Optional<Block> foundBlock = wall.findBlockByColor("yellow");

        //then
        assertTrue(foundBlock.isEmpty(), "red block should be not found");
    }

    @Test
    void testFindSimpleBlockByColor() {
        //given
        Block blueBlock = new BlockImpl("blue", "wood");
        Block redBlock = new BlockImpl("red", "paper");
        Structure wall = new Wall(List.of(blueBlock, redBlock));

        //when
        Optional<Block> foundBlock = wall.findBlockByColor("red");

        //then
        assertTrue(foundBlock.isPresent(), "red block should be present");
        assertEquals(foundBlock.get(), redBlock);
    }

    @Test
    void testFindCompositeBlockByColor() {
        //given
        Block blueBlock = new BlockImpl("blue", "wood");
        Block redBlock = new BlockImpl("red", "paper");
        Block compositeBlock = new CompositeBlockImpl("yellow", "iron", List.of(redBlock));

        Structure wall = new Wall(List.of(blueBlock, compositeBlock));

        //when
        Optional<Block> foundBlock = wall.findBlockByColor("yellow");

        //then
        assertTrue(foundBlock.isPresent(), "red block should be present");
        assertEquals(foundBlock.get(), compositeBlock);
    }

    @Test
    void testFindNestedBlockByColor() {
        //given
        Block blueBlock = new BlockImpl("blue", "wood");
        Block redBlock = new BlockImpl("red", "paper");
        Block compositeBlock = new CompositeBlockImpl("yellow", "iron", List.of(redBlock));

        Structure wall = new Wall(List.of(blueBlock, compositeBlock));

        //when
        Optional<Block> foundBlock = wall.findBlockByColor("red");

        //then
        assertTrue(foundBlock.isPresent(), "red block should be present");
        assertEquals(foundBlock.get(), redBlock);
    }

    @Test
    void testFindBlocksByMaterial() {
        //given
        Block woodenBlock = new BlockImpl("blue", "wood");
        Block paperRedBlock = new BlockImpl("red", "paper");
        Block paperBlueBlock = new CompositeBlockImpl("blue", "paper", List.of());
        Block paperYellowBlock = new CompositeBlockImpl("yellow", "paper", List.of(paperRedBlock, paperBlueBlock));

        Structure wall = new Wall(List.of(woodenBlock, paperYellowBlock));

        //when
        List<Block> paperBlocks = wall.findBlocksByMaterial("paper");

        //then
        assertEquals(3, paperBlocks.size());

        assertTrue(paperBlocks.contains(paperRedBlock));
        assertTrue(paperBlocks.contains(paperBlueBlock));
        assertTrue(paperBlocks.contains(paperYellowBlock));
    }

    @Test
    void testCountBlocks() {
        //given
        Block woodenBlock = new BlockImpl("blue", "wood");
        Block paperRedBlock = new BlockImpl("red", "paper");
        Block paperBlueBlock = new CompositeBlockImpl("blue", "paper", List.of());
        Block paperYellowBlock = new CompositeBlockImpl("yellow", "paper", List.of(paperRedBlock, paperBlueBlock));

        Structure wall = new Wall(List.of(woodenBlock, paperYellowBlock));

        //when
        int count = wall.count();

        //then
        assertEquals(4, count);
    }

}
