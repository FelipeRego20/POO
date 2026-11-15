package model;

import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;

@DatabaseTable(tableName="veiculos")
public class Veiculo {
    
    @DatabaseField(generatedId = true)
    private int id;
        
    @DatabaseField(dataType=DataType.STRING)    
    private String nomeVeiculo;
    
    @DatabaseField(dataType=DataType.STRING)        
    private String placaVeiculo;
    
    @DatabaseField(dataType=DataType.INTEGER)        
    private int anoVeiculo;
    
    @DatabaseField(dataType=DataType.DOUBLE)
    private double quilometragem;
    


    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNomeVeiculo(){
        return this.nomeVeiculo;
    }

    public void setNomeVeiculo(String nomeVeiculo){
        this.nomeVeiculo = nomeVeiculo;
    }

    public String getPlacaVeiculo(){
        return this.placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo){
        this.placaVeiculo = placaVeiculo;
    }

    public int getAnoVeiculo(){
        return this.anoVeiculo;
    }

    public void setAnoVeiculo(int anoVeiculo){
        this.anoVeiculo = anoVeiculo;
    }
    
    public double getQuilometragem(){
        return this.quilometragem;
    }
    
    public void setQuilometragem(double quilometragem){
        this.quilometragem = quilometragem;
    }
    }

