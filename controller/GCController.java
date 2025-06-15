package controller; // Troque pelo seu pacote real

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GCController {

    @FXML
    private FlowPane memoryGrid;

    private List<Rectangle> blocos;
    private List<Rectangle> blocosVerdes;
    private List<Rectangle> blocosVermelhos;
    private List<Rectangle> blocosPercorridos;

    @FXML
    public void initialize() {
        blocosPercorridos = new ArrayList<>();
        blocosVerdes = new ArrayList<>();
        blocosVermelhos = new ArrayList<>();
        blocos = new ArrayList<>();

        for (javafx.scene.Node node : memoryGrid.getChildren()) {
            if (node instanceof Rectangle) {
                blocos.add((Rectangle) node);
            }
        }
    }

    @FXML
    public void alocar() {
        while (blocosPercorridos.size() < blocos.size()) {
            Rectangle elem = blocos.get(new Random().nextInt(blocos.size()));

            if(!blocosPercorridos.contains(elem)){
                blocosPercorridos.add(elem);
            }

            if (Color.WHITE.equals(elem.getFill())) {
                blocosVerdes.add(elem);
                elem.setFill(Color.LIMEGREEN);
                break;
            }

            
        }
    }

    @FXML
    public void removerReferencia() {
        while (blocosVerdes.size() != 0) {
            Rectangle elem = blocosVerdes.get(new Random().nextInt(blocosVerdes.size()));

            if (Color.LIMEGREEN.equals(elem.getFill())) {
                blocosVerdes.remove(elem);
                blocosVermelhos.add(elem);
                elem.setFill(Color.RED);
                break;
            }
        }
    }

    @FXML
    public void executarGC() {
        for (Rectangle bloco : blocosVermelhos) {
            blocosPercorridos.remove(bloco);
            bloco.setFill(Color.WHITE);
        }
        blocosVermelhos.clear();
    }
}
