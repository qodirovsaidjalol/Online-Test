package uz.qodirov.utils.validators;

import uz.qodirov.utils.BaseUtils;

import java.io.Serializable;

public abstract class AbstractValidator<K extends Serializable> {
    protected final BaseUtils utils;

    protected AbstractValidator(BaseUtils utils) {
        this.utils = utils;
    }

    protected abstract void validKey(K key) throws IllegalArgumentException;
}
