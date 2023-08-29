package dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountDTO {
    private long id;
    private String accountNumber;
    private long deposit;
    private long withdraw;
    private String bankingAt;

    private static long num = 1L;
    LocalDateTime time = LocalDateTime.now();
    String create = time.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH시mm분ss초"));

    public AccountDTO() {
    }

    public AccountDTO(String accountNumber, long deposit, long withdraw) {
        this.id = num++;
        this.accountNumber = accountNumber;
        this.deposit = deposit;
        this.withdraw = withdraw;
        this.bankingAt = create;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getDeposit() {
        return deposit;
    }

    public void setDeposit(long deposit) {
        this.deposit = deposit;
    }

    public long getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(long withdraw) {
        this.withdraw = withdraw;
    }

    public String getBankingAt() {
        return bankingAt;
    }

    public void setBankingAt(String bankingAt) {
        this.bankingAt = bankingAt;
    }

    @Override
    public String toString() {
        return "내역" +
                "번호 : " + id +
                ", 계좌번호 : '" + accountNumber + '\'' +
                ", 입금액 : " + deposit +
                ", 출금액 : " + withdraw +
                ", 입출금 시간 : '" + bankingAt + '\'';
    }
}
