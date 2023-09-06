package uz.greenwhite.shopping.error;

import lombok.Data;

@Data
public class ErrorResponse {
    String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public static ErrorResponse error(String message) {
        return new ErrorResponse(message);
    }
}
