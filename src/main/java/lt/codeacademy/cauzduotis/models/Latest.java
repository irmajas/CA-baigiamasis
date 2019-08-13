package lt.codeacademy.cauzduotis.models;

import lombok.Data;

@Data
public class Latest {
    int latestid;

    public Latest(int latestid) {
        this.latestid=latestid;
    }
}
