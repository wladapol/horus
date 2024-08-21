import java.util.List;

public class CompositeBlockImpl extends BlockImpl implements CompositeBlock {

    private final List<Block> blocks;

    public CompositeBlockImpl(String color, String material, List<Block> blocks) {
        super(color, material);
        this.blocks = blocks;
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }
}
