package model;

import controller.GCController;

public class Auto extends Thread{
    
    private GCController controller;
    private Boolean automatico = true;
    private int contador = 0;

    //construtor
    public Auto(GCController controller){
        this.automatico = true;
        this.controller = controller;
    }

    @Override
  public void run() {
    while (automatico) {
      try {
        automatico();
        
        sleep(1000); // responsavel pela animacao
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  //funcao pra rodar automaticamente na ordem 6/4/3
  public void automatico(){
    if(contador <= 6){
        controller.alocar();
        contador++;
    }else if(contador <= 10){
        controller.removerReferencia();
        contador++;
    }else if(contador <=13){
        controller.executarGC();
        contador = 0;
    }
  }

  //desliga a thread
  public void desligarThread(){
    this.automatico = false;
  }
}
