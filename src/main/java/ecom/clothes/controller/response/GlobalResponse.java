package ecom.clothes.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GlobalResponse {
    private int page;
    private int size;
    private Long totalElements;
    private int totalPages;
}
