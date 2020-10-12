package org.dhbw;

import backend.database.DbCallerCir;
import backend.database.DbCallerCit;
import backend.database.DbCallerUser;

public class Controller {

    DbCallerCir dbCallerCir;
    DbCallerCit dbCallerCit;
    DbCallerUser dbCallerUser;

    public Controller () {

        dbCallerUser = new DbCallerUser();
        dbCallerCir = new DbCallerCir();
        dbCallerCit = new DbCallerCit();

    }
}
