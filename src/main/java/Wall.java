import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Wall implements Structure {
    private final List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = new ArrayList<>(blocks);
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findAllBlocks().stream().filter(block -> block.getColor().equals(color)).findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return findAllBlocks().stream().filter(block -> block.getMaterial().equals(material)).toList();
    }

    @Override
    public int count() {
        return findAllBlocks().size();
    }

    private List<Block> findAllBlocks() {
        return blocks.stream().flatMap(this::flatten).toList();
    }

    private Stream<Block> flatten(Block block) {
        return block instanceof CompositeBlock ?
                Stream.concat(((CompositeBlock) block).getBlocks().stream().flatMap(this::flatten), Stream.of(block)) : Stream.of(block);
    }

}