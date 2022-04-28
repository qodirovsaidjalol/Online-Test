package uz.qodirov.mappers;

public interface GenericMapper<E, D, CD, UD> extends GenericBaseMapper {
E fromDto(D dto);
E fromCreateDto(CD dto);
E fromUpdateDto(UD dto);
D toDto(E dto);
}
