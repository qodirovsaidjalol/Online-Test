package uz.qodirov.ui;

import uz.qodirov.services.GenericService;

public abstract class BaseUI <S extends GenericService> {

    protected final S service;

    public BaseUI(S service) {
        this.service = service;
    }

    public abstract void create();

    public abstract void delete();

    public abstract void update();

    public abstract void get();

    public abstract void list();
}
