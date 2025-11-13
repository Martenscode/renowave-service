package va.renowave_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Kov {

    ANTSLA_VALD("145", "Antsla vald"),
    ROUGE_VALD("698", "Rõuge vald"),
    SETOMAA_VALD("732", "Setomaa vald"),
    VORU_LINN("919", "Võru linn"),
    VORU_VALD("917", "Võru vald"),
    VORU_MAAKOND(null, "Võru maakond");

    private final String code;
    private final String name;

    public static Kov fromCode(String code) {
        for (Kov kov : values()) {
            if (kov.code.equals(code)) {
                return kov;
            }
        }
        throw new IllegalArgumentException("Unknown KOV code: " + code);
    }

    public static List<String> getAllCodes() {
        return Stream.of(values())
                .map(Kov::getCode)
                .collect(Collectors.toList());
    }

}
