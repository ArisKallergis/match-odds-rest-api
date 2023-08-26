package gr.accepted.matchoddsrestapi.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {

    private String message;
    private String errorCode;
    private String requestPath;
}
