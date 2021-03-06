package lendpal.file;

import lendpal.model.LendPalModel;

import java.nio.file.Path;

public interface LendPalModelFileHandler {

    void save(LendPalModel model, String fileName);

    LendPalModel load(String fileName);

}
