package codes.oskarveerhoek.episode11;

public enum BlockType {
    STONE("res/stone.png"), AIR("res/air.png"), GRASS("res/grass.png"), DIRT("res/dirt.png");

    public final String location;

    BlockType(String location) {
        this.location = location;
    }
}
