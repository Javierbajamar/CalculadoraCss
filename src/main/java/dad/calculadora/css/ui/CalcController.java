package dad.calculadora.css.ui;

import dad.calculadora.css.Calculadora;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class CalcController {

    // model

    private Calculadora calculadora = new Calculadora();

    // view

    @FXML
    private GridPane view;

    @FXML
    private TextField screenText;

    public CalcController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CalcView.fxml"));
        loader.setController(this);
        loader.load();
    }

    @FXML
    private void initialize() {

        screenText.textProperty().bind(calculadora.screenProperty());

        ComboBox<String> styleChooser = new ComboBox<>();
        styleChooser.getItems().addAll("Clásica", "Moderna");
        styleChooser.setValue("Clásica");

        styleChooser.valueProperty().addListener((obs, oldVal, newVal) -> {
            view.getStylesheets().clear();
            if ("Clásica".equals(newVal)) {
                view.getStylesheets().add("src/main/resources/css/Clasica.css");
                System.out.println("Clásica");
            } else if ("Moderna".equals(newVal)) {
                view.getStylesheets().add("resources/css/Moderna.css");
                System.out.println("Moderna");
            }
        });

        view.add(styleChooser, 0, 0);
    }


    @FXML
    private void onOperationButtonHandle(ActionEvent e) {
        String texto = ((Button) e.getSource()).getText();
        if (texto.equals("CE")) {
            calculadora.cleanEverything();
        } else if (texto.equals("C")) {
            calculadora.clean();
        } else if (texto.equals("+/-")) {
            calculadora.negate();
        } else {
            calculadora.operate(texto.charAt(0));
        }
    }

    @FXML
    private void onCommaButtonHandle(ActionEvent e) {
        calculadora.insertComma();
    }

    @FXML
    private void onNumberButtonHandle(ActionEvent e) {
        String texto = ((Button) e.getSource()).getText();
        calculadora.insert(texto.charAt(0));
    }

    public GridPane getView() {
        return view;
    }

}
