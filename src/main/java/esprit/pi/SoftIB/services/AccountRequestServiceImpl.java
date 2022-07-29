package esprit.pi.SoftIB.services;

import esprit.pi.SoftIB.dto.CustomerAccount;
import esprit.pi.SoftIB.entities.*;
import esprit.pi.SoftIB.enumeration.RoleEnum;
import esprit.pi.SoftIB.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountRequestServiceImpl implements IAccountResquestService {

    @Autowired
    AccountRequestRepository accountRequestRepository;
    @Autowired
    TimeSheetRepository timeSheetRepository;
    @Autowired
    MeetingRepository meetingRepository;
    @Autowired
    AgentRepository agentRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Long fillOutAccountReuqest(AccountRequest accountRequest) {
        accountRequestRepository.save(accountRequest);
        return accountRequest.getId();
    }




    private int getTimeLeftBySheet(Timesheet timeSheet ){
        List<TimeSlot> timeSlotList = timeSheet.getTimeSlots();
        return (8*60) - timeSlotList.stream().map(e-> e.getDuration())
                .reduce(0,(a, b) -> a + b);
    }

    @Override
    public Timesheet getLeastBusyTimeSheetByDay(Date preferedDate) {
        List<Timesheet> emptyTimesheet = timeSheetRepository.getTimesheetByDate(preferedDate);
        Comparator<Timesheet> comparator = Comparator.comparing(e->getTimeLeftBySheet(e) );
        Timesheet timesheet= emptyTimesheet.stream().min(comparator).get();
        if ( getTimeLeftBySheet(timesheet)>=60){
            return timesheet;
        }
        return null;
    }


    // changed to stream because of the enermous data size , the for was posing a performance issue
    @Override
    public List<LocalDate> checkAvailibleDates(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate).filter(d->getLeastBusyTimeSheetByDay(
                Date.from(d.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant())
        )!=null).collect(Collectors.toList());
    }
    @Override
    public void fixUpAMeeting(Meeting meeting) throws Exception {
        Agent availableAgent =  agentRepository.findAgentWithTimeSheetDate(meeting.getDate());

        if(availableAgent!=null) {
            Timesheet timesheet = new Timesheet();
            timesheet.setAgent(availableAgent);
            timesheet.setDate(meeting.getDate());
            TimeSlot timeSlot = new TimeSlot();
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY,9);
            cal.set(Calendar.MINUTE,0);
            timeSlot.setStartTime(cal.getTime());
            cal.set(Calendar.HOUR_OF_DAY,10);
            cal.set(Calendar.MINUTE,0);
            timeSlot.setStartTime(cal.getTime());
            timesheet.getTimeSlots().add(timeSlot);
            meeting.setAgent(availableAgent);
            meetingRepository.save(meeting);
            MailManagerImpl.sendEmail("Account Request",
                    " Dear " + meeting.getAccountRequest()!=null?meeting.getAccountRequest().getLastName():
                            meeting.getCustomer().getLastName()
                                    + "/n"
                                    +"You are invited to our Branch at "+ timeSlot.getStartTime()
                                    + "to continue your account procedure with our agent" +meeting.getAgent().getFirstName()+meeting.getAgent().getLastName(),
                    meeting.getAccountRequest()!=null?meeting.getAccountRequest().getEmail():
                            meeting.getCustomer().getAccountRequest().getEmail()
            );

        }else {
            //no agent with timesheet empty so we check less busy agent that day
            //find first available time slot
            Timesheet timesheet = getLeastBusyTimeSheetByDay(meeting.getDate());
            if(timesheet!=null) {
                for (TimeSlot timeSlot : timesheet.getTimeSlots()) {
                    Date endTime = DateUtils.addHours(timeSlot.getStartTime(), timeSlot.getDuration() / 60);
                    if (meeting.getTime().compareTo(DateUtils.addHours(endTime, 1)) > 0) {
                        TimeSlot timeSlot1 = new TimeSlot();
                        timeSlot1.setDuration(60);
                        timeSlot1.setStartTime(meeting.getTime());
                        timeSlot1.setTimesheet(timesheet);
                        timesheet.getTimeSlots().add(timeSlot1);
                        timeSheetRepository.save(timesheet);
                        meeting.setAgent(timesheet.getAgent());
                        meetingRepository.save(meeting);
                        MailManagerImpl.sendEmail("Account Request",
                                " Dear " + meeting.getAccountRequest()!=null?meeting.getAccountRequest().getLastName():
                                        meeting.getCustomer().getLastName()
                                                + "/n"
                                                +"You are invited to our Branch at "+ timeSlot1.getStartTime()
                                                + "to continue your account pocedure with the help of "
                                                +meeting.getAgent().getFirstName()+meeting.getAgent().getLastName(),
                                meeting.getAccountRequest()!=null?meeting.getAccountRequest().getEmail():
                                        meeting.getCustomer().getAccountRequest().getEmail()
                        );

                    }

                }

            }else{
                throw new Exception("this day is fully booked");
            }
        }



    }
    @Override
    public void approveAccountRequest(CustomerAccount customerAccount) {
        Account account = new Account();
        account.setEmail(customerAccount.getEmail());
        account.setRib(customerAccount.getRib());
        account.setAccountNumber(customerAccount.getAccountNumber());
        account.setBalance(customerAccount.getBalance());
        Customer customer = new Customer();
        customer.setPersonalAddress(customerAccount.getPersonalAddress());
        customer.setCin(customerAccount.getCin());
        customer.setBirthDate(customerAccount.getBirthDate());
        customer.setSalary(customerAccount.getSalary());
        customer.setPhoneNumber(customerAccount.getPhoneNumber());
        customer.setLastName(customerAccount.getLastName());
        customer.setFirstName(customerAccount.getLastName());
        AccountRequest accountRequest = accountRequestRepository.findById(customerAccount.getIdAccountRequest()).get();
        Agent agent = agentRepository.findById(customerAccount.getIdAgent()).get();
        //to pressure customer to come make an account
        User user = new User();
        user.setEmail(account.getEmail());
        user.setUsername(customer.getFirstName()+customer.getLastName());
        user.getRoles().add(roleRepository.findByName(RoleEnum.ROLE_CUSTOMER).get());
        user.setPassword(customer.getPhoneNumber());
        User savedUser = userRepository.save(user);
        customer.setUser(savedUser);
        customer.setAccountRequest(accountRequest);
        customer.setAgencyCustomer(agent.getAgencyAgent());
        Customer savedCustomer = customerRepository.save(customer);
        accountRequest.setCustomer(savedCustomer);
        accountRequest.setApproved(true);
        account.setUserAccount(savedUser);
        accountRepository.save(account);
        accountRequestRepository.save(accountRequest);
    }

    @Override
    public List<AccountRequest> getAlLAccountRequest() {
            return (List<AccountRequest>) accountRequestRepository.findAll();
    }



}
