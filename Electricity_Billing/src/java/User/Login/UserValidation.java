/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Login;

import Connection.Manager.ConnectionManager;
import Connection.Manager.Tables.UserManager;
import Connection.Testing.Beans.User;

public class UserValidation {

    private final String name;
    private final String password;

    public UserValidation(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean isTrue() throws Exception {
        ConnectionManager.getInstance();
        User user = (User) UserManager.Checking(this.name, this.password);
        if (user != null) {
            if ((name.equals(user.getUserName()) == true) && (password.equals(user.getPassword()) == true)) {
                return true;
            } else {
                return false;
            }
        }
        else{
            return false;
        }
    }

}
