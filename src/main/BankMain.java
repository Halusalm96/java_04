package main;

import service.BankService;

import java.util.Scanner;

public class BankMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService bankService = new BankService();
        boolean run = true;

        while (run) {
            System.out.println(" = = = = = = = = = = = 은 행 = = = = = = = = = = = ");
            System.out.println("1.등록 2.잔액 조회 3.입금 4.출금 5.입출금 내역 조회 0.종료");
            System.out.print("입력 : ");
            long menu = sc.nextInt();
            if(menu == 1) {
                bankService.save();
            }else if(menu == 2) {
                bankService.list();
            }else if(menu == 3) {
                bankService.deposit();
            }else if(menu == 4) {
                bankService.withdraw();
            }else if(menu == 5) {
                bankService.account();
            }else if(menu == 0){
                System.out.println("종료");
                run = false;
            }else {
                System.out.println("0~5 중에 입력하세요.");
            }
        }
    }
}
