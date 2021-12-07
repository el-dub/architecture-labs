package com.kpi.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "orderDetails")
@ToString(exclude = "orderDetails")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue
    private Long orderId;

    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @Column(name = "processed", nullable = false)
    private Boolean processed = false;

    @Column(name = "paid", nullable = false)
    private Boolean paid = false;

    @Column(name = "delivered", nullable = false)
    private Boolean delivered = false;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public static class OrderBuilder {
        private Long orderId;
        private LocalDateTime orderTime;
        private User client;
        private Boolean processed;
        private Boolean paid;
        private Boolean delivered;
        private List<OrderDetail> orderDetails;

        OrderBuilder() {
        }

        public OrderBuilder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderBuilder orderTime(LocalDateTime orderTime) {
            this.orderTime = orderTime;
            return this;
        }

        public OrderBuilder client(User client) {
            this.client = client;
            return this;
        }

        public OrderBuilder processed(Boolean processed) {
            this.processed = processed;
            return this;
        }

        public OrderBuilder paid(Boolean paid) {
            this.paid = paid;
            return this;
        }

        public OrderBuilder delivered(Boolean delivered) {
            this.delivered = delivered;
            return this;
        }

        public OrderBuilder orderDetails(List<OrderDetail> orderDetails) {
            this.orderDetails = orderDetails;
            return this;
        }

        public Order build() {
            return new Order(orderId, orderTime, client, processed, paid, delivered, orderDetails);
        }

        public String toString() {
            return "Order.OrderBuilder(orderId=" + this.orderId + ", orderTime=" + this.orderTime + ", client=" + this.client + ", processed=" + this.processed + ", paid=" + this.paid + ", delivered=" + this.delivered + ", orderDetails=" + this.orderDetails + ")";
        }
    }
}
