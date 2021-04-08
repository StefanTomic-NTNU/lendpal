package lendpal;

import lendpal.file.LendPalModelFileHandler;
import lendpal.file.LendPalModelJson;
import lendpal.model.LendPalItem;
import lendpal.model.LendPalModel;
import lendpal.model.User;
import lendpal.ui.App;

public class Main {
    public static void main(String[] args) {
        //App.main(args);'
        LendPalModel model;
        LendPalModelFileHandler handler;

        model = new LendPalModel();
        User user = new User("Test", "Test", "test@epost.com", "Tullepassord");
        LendPalItem item = new LendPalItem("Sirkelsag", "Grønn, slitt.");
        System.out.println(item);
        model.addNewUser(user);
        model.addItemToLendPalList(user, item);
        System.out.println(model);
        handler = new LendPalModelJson();
        handler.save(model, "model.json");
    }
}