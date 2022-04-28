package uz.qodirov.utils;

import java.util.List;
import java.util.Objects;

public class BaseUtils {
    public static boolean isEmpty(Object obj) {
        return Objects.isNull(obj);
    }

    public static boolean isEmpty(List<Object> obj) {
        return Objects.isNull(obj) || obj.isEmpty();
    }
}
