package com.Harshit.expensify.Helper;

public class MessageData {

    public MessageData(String bankName, double amount, String date, String VPA_address, String senderName) {
        this.bankName = bankName;
        this.amount = amount;
        this.date = date;
        this.VPA_address = VPA_address;
        this.senderName = senderName;
    }

    private String bankName = "";
    private double amount = 0;
    private String date = "";
    private String VPA_address = "";
    private String senderName = "";

    public MessageData() {

    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVPA_address() {
        return VPA_address;
    }

    public void setVPA_address(String VPA_address) {
        this.VPA_address = VPA_address;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
