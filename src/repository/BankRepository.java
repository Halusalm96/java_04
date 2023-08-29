package repository;

import dto.AccountDTO;
import dto.ClientDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankRepository {
    LocalDateTime time = LocalDateTime.now();
    String create = time.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH시mm분ss초"));

    List<ClientDTO> clientDTOList = new ArrayList<>();

    List<AccountDTO> accountDTOList = new ArrayList<>();

    public boolean confirmationNumber(String number) {
        for (ClientDTO clientDTO : clientDTOList) {
            if (number.equals(clientDTO.getAccountNumber())) {
                return true;
            }
        }
        return false;
    }

    public boolean confirmationPass(String number,String pass) {
        for (ClientDTO clientDTO : clientDTOList) {
            if (number.equals(clientDTO.getAccountNumber())) {
                if (pass.equals(clientDTO.getClientPass())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean confirmationWithdraw(String number,String pass,long withdraw) {
        for (ClientDTO clientDTO : clientDTOList) {
            if (number.equals(clientDTO.getAccountNumber())) {
                if (pass.equals(clientDTO.getClientPass())) {
                    if (withdraw > clientDTO.getBalance()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean save(ClientDTO clientDTO) {
        return clientDTOList.add(clientDTO);
    }

    public List<ClientDTO> list() {
        return clientDTOList;
    }

    public boolean deposit(String number,long deposit) {
        for(ClientDTO clientDTO : clientDTOList) {
            if (number.equals(clientDTO.getAccountNumber())) {
                clientDTO.setBalance(clientDTO.getBalance()+deposit);
                return true;
            }
        }
        return false;
    }

    public void withdraw(String number, String pass, long withdraw) {
        for (ClientDTO clientDTO : clientDTOList) {
            if (number.equals(clientDTO.getAccountNumber())) {
                if (pass.equals(clientDTO.getClientPass())) {
                    clientDTO.setBalance(clientDTO.getBalance()-withdraw);
                }
            }
        }
    }

    public void deposit01(AccountDTO accountDTO) {
        accountDTOList.add(accountDTO);
    }


    public List<AccountDTO> bankingList() {
        return accountDTOList;
    }
}
