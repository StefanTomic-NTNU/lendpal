package lendpal.file;

import lendpal.model.LendPalItem;
import lendpal.model.LendPalModel;
import lendpal.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LendPalModelJsonTest {

    final String fileName = "src/test/resources/lendpal/file/test_model.json";

    LendPalModelFileHandler handler;
    LendPalModel model;
    LendPalItem item;
    User user;

    @BeforeEach
    void beforeEach() {
        model = new LendPalModel();
        user = new User("Test");
        item = new LendPalItem("Sirkelsag", "Grønn, slitt.");
        model.addUser(user);
        model.lendItem(user.getId(), item.getId());
        handler = new LendPalModelJson();

    }

    @Test
    void testSaveAndLoad() {
        handler.save(model, fileName);
        LendPalModel readModel = handler.load(fileName);
        Assertions.assertAll(
                () -> Assertions.assertTrue(readModel.containsUser(user)),
                () -> Assertions.assertTrue(readModel.isItemLent(item.getId()))
        );
        System.out.println(readModel.getUser(user.getId()));
        //System.out.println(readModel.get());
    }

}
