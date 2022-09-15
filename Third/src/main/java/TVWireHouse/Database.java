package TVWireHouse;

import org.springframework.beans.factory.annotation.Value;

public class Database {
    private int countOfObjects = 0;
    private String dataBaseName;

    public Database(){

    }

    public int getCountOfObjects() {
        return countOfObjects;
    }

    public void setCountOfObjects(int countOfObjects) {
        this.countOfObjects = countOfObjects;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    @Value("${dataBase.dataBaseName}")
    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }
}
