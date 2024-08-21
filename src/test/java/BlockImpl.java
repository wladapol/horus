import java.util.Objects;

public class BlockImpl implements Block {

    protected String color;
    protected String material;

    public BlockImpl(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockImpl block)) return false;
        return Objects.equals(color, block.color) && Objects.equals(material, block.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, material);
    }
}
