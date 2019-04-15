package com.cluntraru.service.datamanager;

import java.io.IOException;

public interface IDataManager {
    void saveData() throws IOException;
    void loadData() throws IOException;
}
