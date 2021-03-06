package lendpal.ui;

import lendpal.model.LendPalItem;
import lendpal.model.LendPalItemContainer;
import lendpal.model.User;

import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Map;

public interface LendPalDataAccess {

    void addUser(User user);

    void addNewUser(String firstName, String lastName, String email, String password, String passwordConfirm);

    boolean containsUser(String userId);

    User getUser(String userId);

    User getUserByEmail(String email);

    User getItemHolder(String itemId);

    LendPalItem getItem(String itemId);

    Period getDefaultLendTime(String itemId);

    LendPalItemContainer getLentItems(String userId);

    void lendItem(String userId, LendPalItem item);

    boolean isItemLent(String itemId);

    void addItem(LendPalItem item);

    void removeItem(LendPalItem item);

    LendPalItemContainer getAllItems();

    void writeData();

    boolean checkUserCredentials(String email, String password);


}
