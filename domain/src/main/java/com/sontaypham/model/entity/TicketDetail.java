package com.sontaypham.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "ticket_item")
public class TicketDetail {
    @Id
    Long id;
    String name;
    String description;
    int stockInitial;
    int stockAvailable;
    boolean isStockPrepared;
    Long priceOriginal;
    Long priceFlash;
    Date saleStartTime;
    Date saleEndTime;
    int status;
    Long activityId;
    Date updatedAt;
    Date createdAt;
}
