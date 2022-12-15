package com.kisaragi.app.OrderProduct;

import javax.persistence.Column;
import java.io.Serializable;

public class OrderProductKey implements Serializable {
    @Column(name = "orderId")
    Long orderId;

    @Column(name = "productId")
    Long productId;
}
