package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import javax.swing.text.BoxView;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public DatePicker najemVozila;
    public DatePicker vracanjeVozila;
    public ComboBox velikostCar;
    public ComboBox izbraneCar;
    public ComboBox gorivoCar;
    public ComboBox menjalnikCar;
    public ComboBox oddajeCar;
    public ComboBox prevzemCar;
    public TextField znesekCar;
    public TextField statusCar;
    public ChoiceBox metodaPlacevanje;
    public TextField karticaC;
    public TextField imeCar;
    public TextField priimekCar;
    public TextField starostCar;
    public TextField naslovCar;
    public TextField emailCar;
    public TextField telefonCar;
    public TextField CCV;
    public TabPane tabPane;
    public CheckBox kaskoPlus;
    public CheckBox kasko;
    public TextField casIzpit;
    public Button preveriButton;
    public Button potrdiButton;
    private int pay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] velikost = new String[4];
        velikost[ 0 ] = "Mal";
        velikost[ 1 ] = "Srednji";
        velikost[ 2 ] = "Velik";
        velikost[ 3 ] = "Exotic";

        String[] maliCar = new String[5];
        maliCar [ 0 ] = "Seat Ibiza";
        maliCar [ 1 ] = "Fiat 500";
        maliCar [ 2 ] = "Volkswagen Polo";
        maliCar [ 3 ] = "Renault Clio";
        maliCar [ 4 ] = "Mitsubishi Colat";

        String[] srednjiCar = new String[5];
        srednjiCar [ 0 ] = "Volkswagen Golf";
        srednjiCar [ 1 ] = "Seat Leon";
        srednjiCar [ 2 ] = "Mitsubishi Carisma";
        srednjiCar [ 3 ] = "Citroen C4";
        srednjiCar [ 4 ] = "Peugeot 307";

        String[] velikCar = new String[5];
        velikCar [ 0 ] = "Volvo V40";
        velikCar [ 1 ] = "Renault Scenic";
        velikCar [ 2 ] = "Ford Mondeo";
        velikCar [ 3 ] = "Volkswagen Touareg";
        velikCar [ 4 ] = "Mercedes E30";

        String[] exoticCar = new String[5];
        exoticCar [ 0 ] = "Ferrari 488 Pista";
        exoticCar [ 1 ] = "Porsche Carrera GT";
        exoticCar [ 2 ] = "Mercedes-Benz SLS Black Series";
        exoticCar [ 3 ] = "McLaren 720S";
        exoticCar [ 4 ] = "Lamborghini Aventador SVJ";


        String[] places = new String[12];
        places [ 0 ] = "Ljubljana";
        places [ 1 ] = "Maribor";
        places [ 2 ] = "Celje";
        places [ 3 ] = "Kranj";
        places [ 4 ] = "Velenje";
        places [ 5 ] = "Koper";
        places [ 6 ] = "Novo Mesto";
        places [ 7 ] = "Murska Sobota";
        places [ 8 ] = "Jesenice";
        places [ 9 ] = "Portoroz";
        places [ 10 ] = "Brnik Airport";
        places [ 11 ] = "Maribor Airport";

        LocalDate trenutni = LocalDate.now();
        najemVozila.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isBefore(trenutni));
                    }});
        vracanjeVozila.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isBefore(trenutni));
                    }});

        potrdiButton.setDisable(true);

        oddajeCar.setItems(FXCollections.observableArrayList(places));
        prevzemCar.setItems(FXCollections.observableArrayList(places));

        gorivoCar.setItems(FXCollections.observableArrayList("Diesel","Benzin","Electric"));
        menjalnikCar.setItems(FXCollections.observableArrayList("Samodejnim","Rocnim"));
        metodaPlacevanje.setItems(FXCollections.observableArrayList("Kartica","Cash"));

        velikostCar.setItems(FXCollections.observableArrayList(velikost));
        velikostCar.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) {
                izbraneCar.getItems().clear();
                izbraneCar.setDisable(true);
            } else {
                if (velikostCar.getValue().equals("Mal")) {
                    izbraneCar.getItems().setAll(maliCar);
                    izbraneCar.setDisable(false);
                }
                if (velikostCar.getValue().equals("Srednji")) {
                    izbraneCar.getItems().setAll(srednjiCar);
                    izbraneCar.setDisable(false);
                }
                if (velikostCar.getValue().equals("Velik")) {
                    izbraneCar.getItems().setAll(velikCar);
                    izbraneCar.setDisable(false);
                }
                if (velikostCar.getValue().equals("Exotic")) {
                    izbraneCar.getItems().setAll(exoticCar);
                    izbraneCar.setDisable(false);
                }
                if (velikostCar.getValue().equals("Mal") && gorivoCar.getValue().equals("Electric")) {
                    izbraneCar.getItems().setAll("Tesla Model 3");
                    izbraneCar.setDisable(false);
                }
                if (velikostCar.getValue().equals("Srednji") && gorivoCar.getValue().equals("Electric")) {
                    izbraneCar.getItems().setAll("Tesla Roadster");
                    izbraneCar.setDisable(false);
                }
                if (velikostCar.getValue().equals("Velik") && gorivoCar.getValue().equals("Electric")) {
                    izbraneCar.getItems().setAll("Tesla Model X");
                    izbraneCar.setDisable(false);
                }
            }
        });
    }

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public void preveriCena(ActionEvent actionEvent) throws Exception{
        potrdiButton.setDisable(false);
        if (imeCar.getText().matches(".*\\d.*")){
            statusCar.setText("Neki ste naredili napacno");
            statusCar.setStyle("-fx-control-inner-background: red");
            imeCar.setStyle("-fx-control-inner-background: red");
        }
        if (priimekCar.getText().matches(".*\\d.*")){
            statusCar.setText("Neki ste naredili napacno");
            statusCar.setStyle("-fx-control-inner-background: red");
            priimekCar.setStyle("-fx-control-inner-background: red");
        }
        if (!isValidEmailAddress(emailCar.getText())){
            statusCar.setText("Neki ste naredili napacno");
            statusCar.setStyle("-fx-control-inner-background: red");
            emailCar.setStyle("-fx-control-inner-background: red");
        }
        if (Integer.parseInt(starostCar.getText()) < 18){
            statusCar.setText("Neki ste naredili napacno");
            statusCar.setStyle("-fx-control-inner-background: red");
            starostCar.setStyle("-fx-control-inner-background: red");
        }
        LocalDate start = najemVozila.getValue();
        LocalDate end = vracanjeVozila.getValue();
        Period period = Period.between(start,end);
        String carType = (String) velikostCar.getValue();
        if(kasko.isSelected()){
            pay += 500;
        }
        if (kaskoPlus.isSelected()){
            pay += 1000;
        }
        if (carType.equals("Mal")){
            pay += period.getDays() * 30;
        }
        if (carType.equals("Srednji")){
            pay += period.getDays() * 50;
        }
        if (carType.equals("Velik")){
            pay += period.getDays() * 100;
        }
        if (carType.equals("Exotic")){
            pay += period.getDays() * 1000;
        }
        znesekCar.setText(pay + " EUR");
    }

    public static String maskCardNumber(String cardNumber, String mask) {
        int index = 0;
        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c == '#') {
                maskedNumber.append(cardNumber.charAt(index));
                index++;
            } else if (c == 'x') {
                maskedNumber.append(c);
                index++;
            } else {
                maskedNumber.append(c);
            }
        }
        return maskedNumber.toString();
    }

    public void potrdiCar(ActionEvent actionEvent) {
        String kartica = karticaC.getText();
        kartica = kartica.replaceAll("-","");
        kartica = maskCardNumber(kartica,"xxxx-xxxx-xxxx-####");
        statusCar.setText("Nov rental ostvarjen, Lepa voznja!");
        statusCar.setStyle("-fx-control-inner-background: #7FFF00");
        infoBox("Ime: " + imeCar.getText() + "\n" + "Priimek: " + priimekCar.getText() + ","+ starostCar.getText() +"\n"
                + "Naslov: " + naslovCar.getText() + " " + telefonCar.getText() + "\n" + "Email: " + emailCar.getText() + "\n"
                + "--------------------------- \n" + "Prevzem: " + prevzemCar.getValue() + "  Oddaja: " + oddajeCar.getValue() + "\n"
                + "Datum prevzem: " + najemVozila.getValue() + "  Datum oddaja: " + vracanjeVozila.getValue() + "\n"
                + "Avtomobil: " + izbraneCar.getValue() + "\n" + "--------------------------- \n"
                + "Metoda placevanja: " + metodaPlacevanje.getValue() + "  Kartica: " + kartica + "  CCV: " + CCV.getText().replaceAll("^(0|[1-9][0-9]*)$","xxx") + "\n"
                + "Koncen znesek: " + znesekCar.getText(),"INFORMATION");
    }

    public void howTo(ActionEvent actionEvent) {
        infoBox("Zivjo! Dobro dosli v nasa aplikacija! Aplikacijo uporabljate takoj da vsi informacije morate" +
                " vnosit in potem pritinisnite na preveri information gumb kjer ne morete uporabit shranjevanje brez da pre" +
                "verite info ,\n potem boste dobili odgovor ce je vse vredu, Imejte lep dan in upam da bo vse vredu," +
                "Vas Rental Tim. ","IMPORTANT");
    }

    public void pobrisiCar(ActionEvent actionEvent) {
        imeCar.setText("");
        priimekCar.setText("");
        starostCar.setText("");
        naslovCar.setText("");
        telefonCar.setText("");
        emailCar.setText("");
        karticaC.setText("");
        CCV.setText("");
        znesekCar.setText("");
        casIzpit.setText("");
    }
}
