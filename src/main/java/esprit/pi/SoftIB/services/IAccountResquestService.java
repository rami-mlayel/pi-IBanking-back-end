package esprit.pi.SoftIB.services;

import esprit.pi.SoftIB.dto.CustomerAccount;
import esprit.pi.SoftIB.entities.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface IAccountResquestService {

    Long fillOutAccountReuqest(AccountRequest accountRequest);
    Timesheet getLeastBusyTimeSheetByDay(Date preferedDate);
    // changed to stream because of the enermous data size , the for was posing a performance issue
    List<LocalDate> checkAvailibleDates(LocalDate startDate, LocalDate endDate);

    void fixUpAMeeting(Meeting meeting) throws Exception;

    void approveAccountRequest(CustomerAccount customerAccount);

    List<AccountRequest> getAlLAccountRequest();

}
