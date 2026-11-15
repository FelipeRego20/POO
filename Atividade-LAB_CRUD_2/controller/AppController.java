package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;

import view.AppView;

public class AppController implements Initializable {
    @FXML
    private TableView<view.Veiculo> veiculosTable; 
    @FXML
    private TableColumn<view.Veiculo, Integer> idColumn;
    @FXML
    private TableColumn<view.Veiculo, String> carroColumn; 
    @FXML
    private TableColumn<view.Veiculo, String> placaColumn;
    @FXML
    private TableColumn<view.Veiculo, Integer> anoColumn; 
    @FXML
    private TableColumn<view.Veiculo, Double> quilometragemColumn; 
    
    @FXML
    private TextField carroField;
    @FXML
    private TextField placaField;
    @FXML
    private TextField anoField;
    @FXML
    private TextField quilometragemField;
    
    @FXML
    private Button adicionarButton;
    @FXML
    private Button atualizarButton;
    @FXML
    private Button limparButton;
    @FXML
    private Button cancelarButton;    
    @FXML
    private Button salvarButton;
    
    AppView appView;
    
    private static model.Database database = new model.Database("veiculos.sqlite");
    private static model.VeiculoRepositorio veiculoRepo = new model.VeiculoRepositorio(database);
        
    public AppController() {
        this.appView = new AppView();
    }
    
    public static void main(String[] args) throws Exception {
        AppController appController = new AppController();
        appController.appView.run(args);
    }
    
    private void desabilitarBotoes(boolean adicionar, boolean atualizar, boolean limpar, boolean cancelar, boolean salvar) {
        adicionarButton.setDisable(adicionar);
        atualizarButton.setDisable(atualizar);
        limparButton.setDisable(limpar);
        cancelarButton.setDisable(cancelar);
        salvarButton.setDisable(salvar);        
    }
    
    private void desabilitarCampos(boolean desabilitado) {
        carroField.setDisable(desabilitado);
        placaField.setDisable(desabilitado);
        anoField.setDisable(desabilitado);
        quilometragemField.setDisable(desabilitado);
    }
    
    private void limparCampos() {
        carroField.setText("");
        placaField.setText("");
        anoField.setText("");
        quilometragemField.setText("");        
    }
    
    @FXML
    public void onCancelarButtonAction() {
        desabilitarCampos(true);
        desabilitarBotoes(false, true, true, true, true);
        limparCampos();
        veiculosTable.getSelectionModel().select(-1);        
    }
    
    @FXML
    public void onSalvarButtonAction() {
        try {
            model.Veiculo veiculo = new model.Veiculo();            
            veiculo.setNomeVeiculo(carroField.getText());
            veiculo.setPlacaVeiculo(placaField.getText());
            veiculo.setAnoVeiculo(Integer.parseInt(anoField.getText()));            
            veiculo.setQuilometragem(Double.parseDouble(quilometragemField.getText()));            
            
            model.Veiculo veiculo_salvo = veiculoRepo.create(veiculo); 
            view.Veiculo veiculoView = modelToView(veiculo_salvo);
            veiculosTable.getItems().add(veiculoView);
            veiculosTable.getSelectionModel().select(veiculoView);    
            
            desabilitarCampos(true);
            desabilitarBotoes(false, true, true, true, true);            
        }
        catch(Exception e) {
            new Alert(AlertType.ERROR, "Erro ao salvar: " + e.getMessage()).show();
        }
    }    
    
    @FXML
    public void onAdicionarButtonAction() {
        veiculosTable.getSelectionModel().select(-1);
        desabilitarCampos(false);
        desabilitarBotoes(true, true, true, false, false);
        limparCampos();
    }
    
    @FXML
    public void onAtualizarButtonAction() {
        try {
            // Pega o veículo que está selecionado na tabela no momento
            view.Veiculo veiculoSelecionado = veiculosTable.getSelectionModel().getSelectedItem();
            
            if (veiculoSelecionado != null) {
               
                model.Veiculo veiculoAtualizado = new model.Veiculo();
                veiculoAtualizado.setId(veiculoSelecionado.getId()); 
                veiculoAtualizado.setNomeVeiculo(carroField.getText());
                veiculoAtualizado.setPlacaVeiculo(placaField.getText());
                veiculoAtualizado.setAnoVeiculo(Integer.parseInt(anoField.getText()));
                veiculoAtualizado.setQuilometragem(Double.parseDouble(quilometragemField.getText()));

                veiculoRepo.update(veiculoAtualizado);
                veiculoSelecionado.setNomeVeiculo(veiculoAtualizado.getNomeVeiculo());
                veiculoSelecionado.setPlacaVeiculo(veiculoAtualizado.getPlacaVeiculo());
                veiculoSelecionado.setAnoVeiculo(veiculoAtualizado.getAnoVeiculo());
                veiculoSelecionado.setQuilometragem(veiculoAtualizado.getQuilometragem());
                veiculosTable.refresh();
                onCancelarButtonAction();
            }
        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Erro ao atualizar: " + e.getMessage()).show();
        }
    }
    @FXML
    public void onLimparButtonAction() {
        limparCampos();
    }
    @FXML
    private void handleVeiculoSelected(view.Veiculo newSelection) {
        if (newSelection != null) {
            carroField.setText(newSelection.getNomeVeiculo());
            placaField.setText(newSelection.getPlacaVeiculo());
            anoField.setText(Integer.toString(newSelection.getAnoVeiculo()));
            quilometragemField.setText(Double.toString(newSelection.getQuilometragem()));
            desabilitarCampos(false);
            desabilitarBotoes(true, false, false, false, true);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        carroColumn.setCellValueFactory(new PropertyValueFactory<>("nomeVeiculo"));
        placaColumn.setCellValueFactory(new PropertyValueFactory<>("placaVeiculo"));
        anoColumn.setCellValueFactory(new PropertyValueFactory<>("anoVeiculo"));
        quilometragemColumn.setCellValueFactory(new PropertyValueFactory<>("quilometragem"));
        
        veiculosTable.setItems(loadAllVeiculos());
        veiculosTable.getSelectionModel().selectedItemProperty().addListener(
            (observableValue, oldSelection, newSelection) -> {
                handleVeiculoSelected(newSelection);
            });
            
        onCancelarButtonAction();
    }
    private view.Veiculo modelToView(model.Veiculo veiculo) {
        return new view.Veiculo(
            veiculo.getId(),
            veiculo.getNomeVeiculo(),
            veiculo.getPlacaVeiculo(),
            veiculo.getAnoVeiculo(),
            veiculo.getQuilometragem()
        );
    }
    private ObservableList<view.Veiculo> loadAllVeiculos() {
        ObservableList<view.Veiculo> lista = FXCollections.observableArrayList();
        List<model.Veiculo> listaFromDatabase = veiculoRepo.loadAll();
        for(model.Veiculo veiculo: listaFromDatabase) {
            lista.add(modelToView(veiculo));
        }
        return lista;
    }
}