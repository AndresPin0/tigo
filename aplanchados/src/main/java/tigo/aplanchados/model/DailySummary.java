package tigo.aplanchados.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name="daily_summary")
public class DailySummary implements Serializable {
    @Id
    @Temporal(TemporalType.DATE)
    private Date date;

    private Integer initialBalance;
    private Integer totalSalesCash;
    private Integer totalSalesCredit;
    private Integer sumOfIncomes;
    private Integer sumOfExpenses;
    private Integer finalBalance;
    private Integer totalProfit;
}
