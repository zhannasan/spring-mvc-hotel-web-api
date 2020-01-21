package dev.hotel.entite;

import java.util.UUID;

public class BaseEntite {

    private UUID uuid;

    public BaseEntite() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
