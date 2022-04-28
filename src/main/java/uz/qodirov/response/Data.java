package uz.qodirov.response;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @param <B> Response Body
 */


@Getter
@Setter
@ToString
public class Data<B> {
    private B body;
    private Integer total;

    public Data(B body) {
        this(body, 0);
    }

    public Data(B body, int total) {
        this.body = body;
        this.total = total;
    }


}
