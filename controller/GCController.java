package controller; // Troque pelo seu pacote real

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Auto;
import model.MemorySlot;

public class GCController {

    @FXML private FlowPane memoryGrid;
    @FXML private TextArea console;
    @FXML private Button botaoAutomatico;

    private List<MemorySlot> slots;
    private List<MemorySlot> slotsPercorridos;
    private List<MemorySlot> slotsAlocados;
    private List<MemorySlot> slotsRemovidos;

    private Auto automatico;
    private Boolean automaticoOn = false;

    @FXML
    public void initialize() {
        slots = new ArrayList<>();
        slotsPercorridos = new ArrayList<>();
        slotsAlocados = new ArrayList<>();
        slotsRemovidos = new ArrayList<>();

        //automatico = new Auto(this);

        Platform.runLater(() -> {
            construirSlots(); // Metodo que itera memoryGrid e cria os MemorySlot
        });
        
    }

    public void construirSlots(){
        for (Node node : memoryGrid.getChildren()) {
            if (node instanceof StackPane) {
                StackPane stack = (StackPane) node;
                Rectangle rect = null;
                Text txt  = null;
                Label lbl = null;

                for (Node inner : stack.getChildren()) {
                    if (inner instanceof Rectangle) rect = (Rectangle) inner;
                    else if (inner instanceof Label) lbl = (Label) inner;
                    else if (inner instanceof Text)  txt  = (Text) inner;
                }

                if (rect != null && txt != null && lbl != null) {
                    slots.add(new MemorySlot(rect, lbl, txt));
                } else {
                    System.err.println("StackPane sem filhos esperados.");
                }
            }
        }
    }

    @FXML
    public void alocar() {
        while (true) {
            MemorySlot slot = slots.get(new Random().nextInt(slots.size()));

            if(!slotsPercorridos.contains(slot)){
                slotsPercorridos.add(slot);
            }

            if (Color.WHITE.equals(slot.getRect().getFill())) {
                slotsAlocados.add(slot);
                slot.getRect().setFill(Color.LIMEGREEN);
                mensagemConsole("[INFO] Alocou memória no espaço " + slot.getCode());
                break;
            } else if (slotsPercorridos.size() >= slots.size()){
                mensagemConsole("[INFO] Sem espaço na memória " + slot.getCode());
                break;
            }
        }
    }

    @FXML
    public void removerReferencia() {
        while (slotsAlocados.size() != 0) {
            MemorySlot slot = slotsAlocados.get(new Random().nextInt(slotsAlocados.size()));

            if (Color.LIMEGREEN.equals(slot.getRect().getFill())) {
                slotsAlocados.remove(slot);
                slotsRemovidos.add(slot);
                slot.getRect().setFill(Color.RED);
                mensagemConsole("[INFO] Removeu referencia a memória no espaço " + slot.getCode());
                break;
            }
        }
    }

    @FXML
    public void executarGC() {
        if(slotsRemovidos.isEmpty()){
            mensagemConsole("[INFO] Garbage collector nao encontrou nenhum lixo");
        }
        for (MemorySlot slot : slotsRemovidos) {
            slotsPercorridos.remove(slot);
            slot.getRect().setFill(Color.WHITE);
            slot.setCounter(0);
            slot.setCounterText();
            mensagemConsole("[INFO] Garbage collector rodou no espaço " + slot.getCode());
        }
        for (MemorySlot slot : slotsAlocados){
            slot.setCounter(slot.incrementCounter());
            slot.setCounterText();
        }
        slotsRemovidos.clear();
    }

    @FXML
    private void mensagemConsole(String mensagem) {
        console.appendText(mensagem + "\n");
    }
    
    @FXML
    public void modoAutomatico() {

        System.out.println("[INFO] entrou no modoAutomatico");
        
        if(!automaticoOn){

            automaticoOn = true;

            automatico = new Auto(this);
            automatico.start();
        }else if(automaticoOn){

            automaticoOn = false;
            automatico.desligarThread();
            
        }

    }
}
