import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = new ArrayList<>(blocks);
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return null;
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return List.of();
    }

    @Override
    public int count() {
        return blocks.stream()
                .map(block -> block instanceof CompositeBlock ? new Wall(((CompositeBlock) block).getBlocks()).count() : 1).
                collect(Collectors.summingInt(Integer::intValue));
    }
}