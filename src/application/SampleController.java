package application;

import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;

import application.SampleController.Kayitlar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Kayitlar> kayitlar_table;

    @FXML
    private TableColumn<Kayitlar, String> hastaadi;

    @FXML
    private TableColumn<Kayitlar,String> memleket;

    @FXML
    private TableColumn<Kayitlar, String> meslek;

    @FXML
    private TableColumn<Kayitlar, String> cinsiyet;

    @FXML
    private TableColumn<Kayitlar,String> ates;

    @FXML
    private TableColumn<Kayitlar,String> teshis;

    @FXML
    private Slider slider_ates;

    @FXML
    private Label ates_label;

    @FXML
    private ComboBox<String> memleket_combo;

    @FXML
    private CheckBox teshis_check;

    @FXML
    private RadioButton kadin_radio;

    @FXML
    private RadioButton erkek_radio;

    @FXML
    private RadioButton isci_radio;

    @FXML
    private RadioButton memur_radio;

    @FXML
    private RadioButton ogrenci_radio;

    @FXML
    private RadioButton emekli_radio;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_guncelle;

    @FXML
    private Button btn_sorgula;

    @FXML
    private TextField txt_hastaismi;
    
    @FXML
    private Label teshis_label;

    ObservableList<Kayitlar> veriler;
    
    @FXML
    void btn_ekle_click(ActionEvent event) {
    	veriler=FXCollections.observableArrayList();
        veriler.add(new Kayitlar(
        txt_hastaismi.getText(),memleket_combo.getSelectionModel().getSelectedItem(),meslek.getText(),cinsiyet.getText(),ates_label.getText(),teshis_label.getText()));
    	kayitlar_table.getItems().addAll(veriler);
    	Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("DÝKKAT!");
		alert.setHeaderText("Yeni kayýt ekleme baþarýlý.");
		alert.showAndWait();
    }

    @FXML
    void btn_guncelle_click(ActionEvent event) {
    	
    	Alert alert3=new Alert(AlertType.CONFIRMATION);
		alert3.setTitle("DÝKKAT!");
		alert3.setContentText("Emin misiniz ? ");
		alert3.setHeaderText("Seçili kayýt güncelleniyor.");
	
    	Kayitlar kayit=new Kayitlar();
    	kayit=(Kayitlar)
    	kayitlar_table.getItems().get(kayitlar_table.getSelectionModel().getSelectedIndex());
    	String yeni=kayit.hastaadi;
    	Kayitlar guncelkayit=new Kayitlar(yeni,memleket_combo.getSelectionModel().getSelectedItem(),meslek.getText(),cinsiyet.getText(),ates_label.getText(),teshis_label.getText());
        int sira=kayitlar_table.getSelectionModel().getSelectedIndex();
    	
    	Optional<ButtonType>result=alert3.showAndWait();
		if(result.get()==ButtonType.OK) {
			 kayitlar_table.getItems().set(sira, guncelkayit);
		}
    }

    @FXML
    void btn_sil_click(ActionEvent event) {
    	
    	Alert alert2=new Alert(AlertType.CONFIRMATION);
		alert2.setTitle("DÝKKAT!");
		alert2.setContentText("Onaylýyor musunuz?");
		alert2.setHeaderText("Seçili kayýt siliniyor.");
	
		Optional<ButtonType>result=alert2.showAndWait();
		if(result.get()==ButtonType.OK) {
			ObservableList<Kayitlar>tumkayitlar,secilenkayit;
	    	tumkayitlar=kayitlar_table.getItems();
	    	secilenkayit=kayitlar_table.getSelectionModel().getSelectedItems();
	        secilenkayit.forEach(tumkayitlar::remove);
		}
        
    }

    @FXML
    void btn_sorgula_click(ActionEvent event) {
 
    	for(Kayitlar islem:veriler) {
    		if(islem.getmemleket().getSelectionModel().getSelectedItem() != null) {
    			System.out.println("secili sehir  : "+memleket_combo.getSelectionModel().getSelectedItem());
    		
    		}
    	}
    			
    }
    
    
    @FXML
    void initialize() {
    	
    slider_ates.setMax(43.7);
    slider_ates.setMin(21.0);
    slider_ates.setMajorTickUnit(1);
    slider_ates.setShowTickLabels(true);
    ates_label.setText(String.valueOf(slider_ates));
    
    
    
    ObservableList<String>sehirler=FXCollections.observableArrayList("Hatay","Adana","Mersin","Ýstanbul","Ýzmir");
    memleket_combo.getItems().addAll(sehirler);
    memleket_combo.setItems(sehirler); 
    
   
     
     ToggleGroup cinsiyet1=new ToggleGroup();
     kadin_radio.setToggleGroup(cinsiyet1);
     erkek_radio.setToggleGroup(cinsiyet1);
     
     
     ToggleGroup meslek1=new ToggleGroup();
     isci_radio.setToggleGroup(meslek1);
     memur_radio.setToggleGroup(meslek1);
     emekli_radio.setToggleGroup(meslek1);
     ogrenci_radio.setToggleGroup(meslek1);
     
     boolean teshissonuc=teshis_check.isSelected();
 	if(teshissonuc) {
 	     teshis_label.setText("Pozitif");
 	}
 	else {
 		
 		teshis_label.setText("negatif");
 	}
     
     
     veriler=FXCollections.observableArrayList();

 
  
     veriler.add(new Kayitlar("ayse","hatay", "öðrenci","kýz","37",""));
     
    	
    hastaadi.setCellValueFactory(new PropertyValueFactory<>("hastaadi"));
    memleket.setCellValueFactory(new PropertyValueFactory<>("memleket"));	
    meslek.setCellValueFactory(new PropertyValueFactory<>("meslek"));		
    cinsiyet.setCellValueFactory(new PropertyValueFactory<>("cinsiyet"));	  
    ates.setCellValueFactory(new PropertyValueFactory<>("ates"));	
    teshis.setCellValueFactory(new PropertyValueFactory<>("teshis"));	
    
    kayitlar_table.setItems(veriler);
    
    }
    
    public static class Kayitlar{
    	
    	private String hastaadi;
    	private ComboBox memleket;
    	private RadioButton meslek;
    	private RadioButton cinsiyet;
    	private Label ates;
    	private CheckBox teshis;
    	
    	Kayitlar(){}
     	
    	Kayitlar(String hastaadi,String memleket,String meslek,String cinsiyet,String ates,String teshis){
    	
    		this.hastaadi=hastaadi;
    		this.memleket=new ComboBox();
            this.meslek=new RadioButton();
    	    this.meslek.setText(meslek);
    	    this.cinsiyet=new RadioButton();
    	    this.cinsiyet.setText(cinsiyet);
            this.ates=new Label(ates);
    	    this.teshis=new CheckBox();
    	}
    	
    	public String gethastaadi() {
    	return hastaadi;
    	}
    	public void sethastaadi(String hastaadi) {
    		this.hastaadi=hastaadi;
    	}
    	
    	public ComboBox getmemleket() {
    		return memleket;
    	}
    	
    	public void setmemleket(ComboBox memleket) {
    		this.memleket=memleket;
    	}	
    	
    	public RadioButton getmeslek() {
    		return meslek;
    	}
    	public void setmeslek(RadioButton meslek) {
    		this.meslek=meslek;
    	}
    	
    	public RadioButton getcinsiyet() {
    		return cinsiyet;
    	}
    	
    	public void setcinsiyet(RadioButton cinsiyet) {
    		this.cinsiyet=cinsiyet;
    	}
    	
    	
    	public Label getates(){
    		return ates;
    	}
    	public void setates(Label ates) {
    		this.ates=ates;
    	}
    	
    	public CheckBox getteshis() {
    		return teshis;
    	}
    	public void setteshis(CheckBox teshis) {
    		this.teshis=teshis;
    	}
    }
}