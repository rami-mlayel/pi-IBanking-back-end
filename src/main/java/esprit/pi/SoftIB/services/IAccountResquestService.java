package esprit.pi.SoftIB.services;

import esprit.pi.SoftIB.entities.Account;
import esprit.pi.SoftIB.entities.AccountRequest;
import esprit.pi.SoftIB.entities.Meeting;

import java.util.Date;
import java.util.List;

public interface IAccountResquestService {
    Long fillOutAccountReuqest(AccountRequest accountRequest);
    List<Date> checkAvailibility(Date fromDate , Date toDate);
    void fixUpAMeeting(Meeting meeting);
    void approveAccountRequest (Account account);// create a new account using account request
    List<AccountRequest> getAlLAccountRequest();
}
