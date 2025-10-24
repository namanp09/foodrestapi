package in.bushansirgur.foodiesapi.io;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequest {
    private List<OrderItem>orderedItems;
    private String userAddress;
    private double amount;
    private String orderStatus;
    private String phoneNumber;
    private String email;

}
