package uz.qodirov.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericCriteria implements GenericBaseCriteria {
    private Integer size;
    private Integer page;
}
