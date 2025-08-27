// src/main/java/edu/portal/model/Fee.java
package edu.portal.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Fee {
    private int id;
    private String studentUsername;
    private String feeType; // TUITION, LIBRARY, LAB, HOSTEL, TRANSPORT, EXAM, MISC
    private String description;
    private double amount;
    private LocalDate dueDate;
    private String status; // PENDING, PAID, OVERDUE, PARTIAL, WAIVED
    private double paidAmount;
    private LocalDateTime paidAt;
    private String paymentMethod; // CASH, CARD, BANK_TRANSFER, ONLINE, CHEQUE
    private String transactionId;
    private String remarks;
    private LocalDateTime createdAt;

    public Fee() {
        this.createdAt = LocalDateTime.now();
        this.status = "PENDING";
        this.paidAmount = 0.0;
    }

    public Fee(String studentUsername, String feeType, String description, 
               double amount, LocalDate dueDate) {
        this();
        this.studentUsername = studentUsername;
        this.feeType = feeType;
        this.description = description;
        this.amount = amount;
        this.dueDate = dueDate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStudentUsername() { return studentUsername; }
    public void setStudentUsername(String studentUsername) { this.studentUsername = studentUsername; }

    public String getFeeType() { return feeType; }
    public void setFeeType(String feeType) { this.feeType = feeType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(double paidAmount) { this.paidAmount = paidAmount; }

    public LocalDateTime getPaidAt() { return paidAt; }
    public void setPaidAt(LocalDateTime paidAt) { this.paidAt = paidAt; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public double getBalanceAmount() {
        return amount - paidAmount;
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(dueDate) && !"PAID".equals(status);
    }

    public void makePayment(double paymentAmount, String method, String transactionId) {
        this.paidAmount += paymentAmount;
        this.paymentMethod = method;
        this.transactionId = transactionId;
        this.paidAt = LocalDateTime.now();
        
        if (paidAmount >= amount) {
            this.status = "PAID";
        } else if (paidAmount > 0) {
            this.status = "PARTIAL";
        }
    }

    @Override
    public String toString() {
        return feeType + " - " + description + ": $" + amount + " (" + status + ")";
    }
}