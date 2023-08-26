package gr.accepted.matchoddsrestapi.model.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {

    private String message;
    private String errorCode;
    private String requestPath;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> validationErrors;
}
