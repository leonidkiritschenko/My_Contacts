package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML private BorderPane mainBorderPane;
    @FXML private TableView<Contact> tableView;

    public void initialize() {
        tableView.setItems(ContactData.getInstance().getContacts());
    }

    @FXML
    public void handleAddButton() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add new Contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contactDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ContactDialog controller = fxmlLoader.getController();
            Contact contact = controller.processResults();
            tableView.getSelectionModel().select(contact);
        }

    }

    @FXML
    public void handleEditButton() {

    }

    @FXML
    public void handleDeleteButton() {
        Contact contact = tableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Contact");
        alert.setHeaderText(
                "Delete contact: "
                        + contact.getFirstName() + " "
                        + contact.getLastName());
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            System.out.println("Deleted!");
            ContactData.getInstance().deleteContact(contact);
        }
    }
}
