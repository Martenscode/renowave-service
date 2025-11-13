package va.renowave_service.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotBlank(message = "Kasutajanimi puudub")
    @Size(max = 255, message = "Kasutajanimi on liiga pikk")
    private String username;

    @NotBlank(message = "Parool puudub")
    @Size(max = 100, message = "Parool on liiga pikk")
    private String password;

}
