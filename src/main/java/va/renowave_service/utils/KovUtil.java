package va.renowave_service.utils;

import va.renowave_service.enums.Kov;

import java.util.List;

public class KovUtil {

    public Kov getKovFromCodes(List<String> kovs) {
        if (kovs.isEmpty()) {
            throw new IllegalArgumentException("Invalid KOVs size");
        }
        if (kovs.size() > 1) {
            return Kov.VORU_MAAKOND;
        }
        return Kov.fromCode(kovs.getFirst());
    }

}
