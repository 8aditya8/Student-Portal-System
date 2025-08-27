// src/main/java/edu/portal/model/Invoice.java
package edu.portal.model;

import java.time.LocalDateTime;

public class Invoice {
    private int id;
    private int studentId;
    private String studentName;
    private String invoiceNumber;
    private double amount;
    private double paidAmount;
    private String status; // PENDING, PAID, OVERDUE, PARTIAL
    private String description;
    private LocalDateTime issueDate;
    private LocalDateTime dueDate;
    private LocalDateTime paidDate;
    private String paymentMethod;

    // Constructors
    public Invoice() {}

    public Invoice(int studentId, String invoiceNumber, double amount, String description, LocalDateTime dueDate) {
        this.studentId = studentId;
        this.invoiceNumber = invoiceNumber;
        this.amount = amount;
        this.description = description;
        this.dueDate = dueDate;
        this.issueDate = LocalDateTime.now();
        this.status = "PENDING";
        this.paidAmount = 0.0;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(double paidAmount) { this.paidAmount = paidAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDateTime issueDate) { this.issueDate = issueDate; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public LocalDateTime getPaidDate() { return paidDate; }
    public void setPaidDate(LocalDateTime paidDate) { this.paidDate = paidDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public double getBalanceAmount() {
        return amount - paidAmount;
    }

    public boolean isOverdue() {
        return LocalDateTime.now().isAfter(dueDate) && !"PAID".equals(status);
    }

    public void makePayment(double paymentAmount, String method) {
        this.paidAmount += paymentAmount;
        this.paymentMethod = method;
        this.paidDate = LocalDateTime.now();
        
        if (paidAmount >= amount) {
            this.status = "PAID";
        } else if (paidAmount > 0) {
            this.status = "PARTIAL";
        }
    }

    @Override
    public String toString() {
        return invoiceNumber + " - $" + amount + " (" + status + ")";
    }
}