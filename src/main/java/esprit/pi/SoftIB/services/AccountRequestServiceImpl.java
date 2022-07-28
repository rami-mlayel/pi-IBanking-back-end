package esprit.pi.SoftIB.services;

import esprit.pi.SoftIB.entities.Account;
import esprit.pi.SoftIB.entities.AccountRequest;
import esprit.pi.SoftIB.entities.Meeting;
import esprit.pi.SoftIB.repository.AccountRequestRepository;
import esprit.pi.SoftIB.services.IAccountResquestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountRequestServiceImpl implements IAccountResquestService {

    @Autowired
    AccountRequestRepository accountRequestRepository;

    @Override
    public Long fillOutAccountReuqest(AccountRequest accountRequest) {
        accountRequestRepository.save(accountRequest);
        return accountRequest.getId();
    }


    @Override
    public List<Date> checkAvailibility(Date fromDate, Date toDate) {
        return null;
    }

    @Override
    public void fixUpAMeeting(Meeting meeting) {

    }

    @Override
    public void approveAccountRequest(Account account) {

    }

    @Override
    public List<AccountRequest> getAlLAccountRequest() {
        return null;
    }
}
