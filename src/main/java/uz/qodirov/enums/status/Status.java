package uz.qodirov.enums.status;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE,
    NO_ACTIVE,
    BLOCKED;

public static Status getStatus(String status){
    for (Status value : values()) {
        if (value.name().equalsIgnoreCase(status))
            return value;
    }
    return Status.NO_ACTIVE;
}
}
