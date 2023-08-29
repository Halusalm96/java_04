package service;

import dto.AccountDTO;
import dto.ClientDTO;
import repository.BankRepository;

import java.util.List;
import java.util.Scanner;

public class BankService {
    Scanner sc = new Scanner(System.in);
    BankRepository bankRepository = new BankRepository();
    public void save () {
        boolean run = true;
        System.out.print("이름 : ");
        String name = sc.next();
        String number = null;
        while (run) {
            System.out.print("계좌번호 : ");
            number = sc.next();
            boolean result = bankRepository.confirmationNumber(number);
            if (result == true) {
                System.out.println("사용중인 계좌번호 입니다. 다른 계좌번호를 입력하세요.");
            } else {
                run = false;
            }
        }
        System.out.print("비밀번호 : ");
        String pass = sc.next();
        System.out.print("잔액 : ");
        long balance = sc.nextInt();

        ClientDTO clientDTO = new ClientDTO(name, number, pass, balance);

        boolean result01 = bankRepository.save(clientDTO);

        if (result01 == true) {
            System.out.println("등록 성공");
        }else {
            System.out.println("등록 실패");
        }
    }

    public void list() {
        System.out.print("계좌번호 : ");
        String number = sc.next();
        boolean result = bankRepository.confirmationNumber(number);
        if (result != true) {
            System.out.println("등록된 계좌가 없습니다.");
        } else {
            List<ClientDTO> clientDTOList = bankRepository.list();
            for (ClientDTO clientDTO : clientDTOList) {
                if (number.equals(clientDTO.getAccountNumber())) {
                    System.out.println("잔액 : " + clientDTO.getBalance() + "원");
                }
            }
        }
    }

    public void deposit() {
        System.out.print("계좌번호 : ");
        String number = sc.next();
        boolean result = bankRepository.confirmationNumber(number);
        if (result != true) {
            System.out.println("등록된 계좌가 없습니다.");
        } else {
            System.out.print("입금 금액 : ");
            long deposit = sc.nextInt();
            boolean result01 = bankRepository.deposit(number, deposit);
            if (result01 == true) {
                long withdraw = 0;

                AccountDTO accountDTO = new AccountDTO(number,deposit,withdraw);

                bankRepository.deposit01(accountDTO);
                System.out.println("입금 성공");
            } else {
                System.out.println("입금 실패");
            }
        }
//        bankRepository.deposit01()
    }

    public void withdraw(){
        System.out.print("계좌번호 : ");
        String number = sc.next();
        boolean result = bankRepository.confirmationNumber(number);
        if (result != true) {
            System.out.println("등록된 계좌가 없습니다.");
        }else {
            System.out.print("비밀번호 : ");
            String pass = sc.next();
            boolean result01 = bankRepository.confirmationPass(number,pass);
            if (result01 != true) {
                System.out.println("비밀번호가 틀렸습니다.");
            }else {
                System.out.print("출금 잔액 : ");
                long withdraw = sc.nextInt();
                boolean result02 = bankRepository.confirmationWithdraw(number,pass,withdraw);
                if (result02 != true) {
                    System.out.println("잔액 부족입니다.");
                }else {
                    bankRepository.withdraw(number,pass,withdraw);
                    long deposit = 0;
                    AccountDTO accountDTO = new AccountDTO(number,deposit,withdraw);
                    bankRepository.deposit01(accountDTO);
                    System.out.println("출금 완료");
                }
            }
        }
    }

    public void account(){
        System.out.print("계좌번호 : ");
        String number = sc.next();
        boolean result = bankRepository.confirmationNumber(number);
        if (result != true) {
            System.out.println("계좌내역이 없습니다.");
        } else {
            boolean result01 = true;
            while (result01) {
                System.out.println("1.전체내역 2.입금내역 3.출금내역 0.뒤로가기");
                System.out.print("입력 : ");
                long menu = sc.nextInt();
                if (menu == 1) {
                    long num = 0;
                    List<AccountDTO> accountDTOList = bankRepository.bankingList();
                    for (AccountDTO accountDTO : accountDTOList) {
                        if (number.equals(accountDTO.getAccountNumber())) {
                            num++;
                            System.out.println(accountDTO);
                        }
                    }
                    if (num == 0) {
                        System.out.println("입출금 내역이 없습니다.");
                    }
                }else if (menu == 2) {
                    long num = 0;
                    List<AccountDTO> accountDTOList = bankRepository.bankingList();
                    for (AccountDTO accountDTO : accountDTOList) {
                        if (number.equals(accountDTO.getAccountNumber())) {
                            if (accountDTO.getWithdraw() == 0) {
                                num++;
                                System.out.println(accountDTO);
                            }
                        }
                    }
                    if (num == 0) {
                        System.out.println("입금 내역이 없습니다.");
                    }
                }else if (menu == 3) {
                    long num = 0;
                    List<AccountDTO> accountDTOList = bankRepository.bankingList();
                    for (AccountDTO accountDTO : accountDTOList) {
                        if (number.equals(accountDTO.getAccountNumber())) {
                            if (accountDTO.getDeposit() == 0) {
                                num++;
                                System.out.println(accountDTO);
                            }
                        }
                    }
                    if (num == 0) {
                        System.out.println("입출금 내역이 없습니다.");
                    }
                }else if (menu == 0){
                    result01 = false;
                }else {
                    System.out.println("0~3 중에 입력하세요.");
                }
            }

        }
    }
}
