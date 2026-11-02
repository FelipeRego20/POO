import java.util.Date;
import java.text.SimpleDateFormat;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;

@DatabaseTable(tableName = "veiculo")
public class Veiculo
{   
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField
    public String nome_veiculo;
    
    @DatabaseField 
    public String placa_veiculo;
    
    @DatabaseField
    public double quilometragem;
    
    @DatabaseField
    public int ano_veiculo;
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNome_Veiculo(){
        return this.nome_veiculo;
    }
    
    public void setNome_Veiculo(String nome_veiculo){
        this.nome_veiculo = nome_veiculo;
    }
    
    public String getPlaca_Veiculo(){
        return this.placa_veiculo;
    }
    
    public void setPlaca_Veiculo(String placa_veiculo){
        this.placa_veiculo = placa_veiculo;
    }
    
    public double getQuilometragem(){
        return this.quilometragem;
    }
    
    public void setQuilometragem(double quilometragem){
        this.quilometragem = quilometragem;
    }
    
    public int getAno_Veiculo(){
        return this.ano_veiculo;
    }
    
    public void setAno_Veiculo(int ano_veiculo){
        this.ano_veiculo = ano_veiculo;
    }
    }


    

    