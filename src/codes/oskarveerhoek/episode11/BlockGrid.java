package codes.oskarveerhoek.episode11;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static codes.oskarveerhoek.episode11.World.*;

public class BlockGrid {
    private Block[][] blocks = new Block[BLOCKS_WIDTH][BLOCKS_HEIGHT];

    public BlockGrid(File loadFile) {

    }

    public BlockGrid() {

        for (int x = 0; x < BLOCKS_WIDTH - 1; x++) {
            for (int y = 0; y < BLOCKS_HEIGHT - 1; y++) {
                blocks[x][y] = new Block(BlockType.AIR, x * BLOCK_SIZE, y * BLOCK_SIZE);
            }
        }
    }

    public void clear() {
        for (int x = 0; x < BLOCKS_WIDTH - 1; x++) {
            for (int y = 0; y < BLOCKS_HEIGHT - 1; y++) {
                blocks[x][y] = new Block(BlockType.AIR, x * BLOCK_SIZE, y * BLOCK_SIZE);
            }
        }
    }

    public void setAt(int x, int y, BlockType type) {
        blocks[x][y] = new Block(type, x * BLOCK_SIZE, y * BLOCK_SIZE);
    }

    public Block getAt(int x, int y) {
        return blocks[x][y];
    }

    public void draw() {
        for (int x = 0; x < BLOCKS_WIDTH - 1; x++) {
            for (int y = 0; y < BLOCKS_HEIGHT - 1; y++) {
                blocks[x][y].draw();
            }
        }
    }

    public void save(File saveFile) {
        Document document = new Document();

        Element root = new Element("blocks");
        document.setRootElement(root);

        for (int x = 0; x < BLOCKS_WIDTH - 1; x++) {
            for (int y = 0; y < BLOCKS_HEIGHT - 1; y++) {
                Element block = new Element("block");
                block.setAttribute("x", String.valueOf((int) (blocks[x][y].getX() / BLOCK_SIZE)));
                block.setAttribute("y", String.valueOf((int) (blocks[x][y].getY() / BLOCK_SIZE)));
                block.setAttribute("type", String.valueOf(blocks[x][y].getType()));
                root.addContent(block);
            }
        }

        XMLOutputter outputter = new XMLOutputter();
        try {
            outputter.output(document, new FileOutputStream(saveFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(File loadFile) {


        try {
            SAXBuilder reader = new SAXBuilder();
            Document document = reader.build(loadFile);

            Element root = document.getRootElement();

            for (Element block : root.getChildren()) {
                int x = Integer.parseInt(block.getAttributeValue("x"));
                int y = Integer.parseInt(block.getAttributeValue("y"));
                blocks[x][y] = new Block(BlockType.valueOf(block.getAttributeValue("type")), x * BLOCK_SIZE, y * BLOCK_SIZE);
            }

        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
    }


}
