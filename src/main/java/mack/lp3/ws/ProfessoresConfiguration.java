package mack.lp3.ws;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class ProfessoresConfiguration extends Configuration {
    @NotEmpty
    private String dateFormat;

    public String getDateFormat() {
        return dateFormat;
    }
}
