package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ContactDialog {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField notesField;


    public Contact processResults() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        String notes = notesField.getText().trim();

        Contact newContact = new Contact(firstName, lastName, phoneNumber, notes);
        ContactData.getInstance().addContact(newContact);
        return newContact;
    }

    public void loadContactData(Contact contact) {
        firstNameField.setText(contact.getFirstName());
        lastNameField.setText(contact.getLastName());
        phoneNumberField.setText(contact.getPhoneNumber());
        notesField.setText(contact.getNotes());
    }

    public void updateContact(Contact contact) {
        contact.setFirstName(firstNameField.getText());
        contact.setLastName(lastNameField.getText());
        contact.setPhoneNumber(phoneNumberField.getText());
        contact.setNotes(notesField.getText());
    }
}
