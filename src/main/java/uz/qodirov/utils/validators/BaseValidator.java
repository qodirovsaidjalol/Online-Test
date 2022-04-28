package uz.qodirov.utils.validators;

import uz.qodirov.dto.GenericBaseDto;
import uz.qodirov.dto.GenericDto;
import uz.qodirov.utils.BaseUtils;

import java.io.Serializable;

public abstract class BaseValidator<
        CD extends GenericBaseDto,
        UD extends GenericDto,
        K extends Serializable> extends
        AbstractValidator<K> {

    protected BaseValidator(BaseUtils utils) {
        super(utils);
    }

    protected abstract void validOnCreate(CD dto) throws IllegalArgumentException;

    protected abstract void validOnUpdate(UD dto) throws IllegalArgumentException;
}
