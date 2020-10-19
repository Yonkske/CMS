package org.dhbw;

import backend.usability.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class UserInfoController extends Controller implements Initializable {

    @FXML
    public TextField surnameTf;
    @FXML
    public TextField nameTf;
    @FXML
    public TextField usernameTf;

    private final User userToShow;


// Following are to show the correct selected-user

    /**
     * Constructor.
     *
     * @param user - this user info is showed
     */
    public UserInfoController(User user) {
        this.userToShow = user;
    }

    /**
     * this method shows your own user.
     *
     * @param resourceBundle - resource bundle
     * @param url - url
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        surnameTf.setText(userToShow.getSurName());
        nameTf.setText(userToShow.getName());
        usernameTf.setText(userToShow.getUserName());
    }
}
