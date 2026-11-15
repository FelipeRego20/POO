package view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Veiculo {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty nomeVeiculo;
    private SimpleStringProperty placaVeiculo;
    private SimpleIntegerProperty anoVeiculo;
    private SimpleDoubleProperty quilometragem;
    
    public Veiculo(int id, String nomeVeiculo, String placaVeiculo, int anoVeiculo, double quilometragem) {
        this.id = new SimpleIntegerProperty(id);
        this.nomeVeiculo = new SimpleStringProperty(nomeVeiculo);
        this.placaVeiculo = new SimpleStringProperty(placaVeiculo);
        this.anoVeiculo = new SimpleIntegerProperty(anoVeiculo);
        this.quilometragem = new SimpleDoubleProperty(quilometragem);
    }
    
    
    public int getId() {
        return this.id.get();
    }
    public void setId(int id) {
        this.id.set(id);
    }
    public String getNomeVeiculo() {
        return this.nomeVeiculo.get();
    }
    public void setNomeVeiculo(String nomeVeiculo) {
        this.nomeVeiculo.set(nomeVeiculo);
    }
    public String getPlacaVeiculo() {
        return this.placaVeiculo.get();
    }
    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo.set(placaVeiculo);
    }
    public int getAnoVeiculo() {
        return this.anoVeiculo.get();
    }
    public void setAnoVeiculo(int anoVeiculo) {
        this.anoVeiculo.set(anoVeiculo);
    }
    public double getQuilometragem() {
        return this.quilometragem.get();
    }
    public void setQuilometragem(double quilometragem) {
        this.quilometragem.set(quilometragem);
    }
}