package va.renowave_service.config;

import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EhrConfig {

    //Kasutamise otstarve
    //Kahe või mitme korteriga elamud
    public static final List<String> DEFAULT_KAOS = List.of(
            "11200", "11210", "11211", "11212", "11220", "11221", "11222"
    );

}
