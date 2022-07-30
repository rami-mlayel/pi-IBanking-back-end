package esprit.pi.SoftIB.services;

import esprit.pi.SoftIB.dto.Tmm;
import esprit.pi.SoftIB.entities.*;
import esprit.pi.SoftIB.enumeration.LoanRequestStatus;
import esprit.pi.SoftIB.enumeration.LoanType;
import esprit.pi.SoftIB.filters.JWTAuthenticationFilter;
import esprit.pi.SoftIB.repository.AccountRepository;
import esprit.pi.SoftIB.repository.LoanRepository;
import esprit.pi.SoftIB.repository.LoanRequestRepository;
import esprit.pi.SoftIB.repository.UserRepository;
import esprit.pi.SoftIB.request.LoanCarRequest;
import esprit.pi.SoftIB.request.LoanTypeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class LoanRequestServiceImpl {

    @Autowired
    private LoanRequestRepository loanRequestRepository;
    @Autowired
    private JWTAuthenticationFilter jwt;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LoanServiceImpl loanService;
    @Autowired
    private LoanRepository loanRepository;

    public String addLoanRequest(LoanRequest loanRequest) {
        loanRequest.setLoanRequestStatus(LoanRequestStatus.WAITING);
        LoanRequest loanRequestObject = loanRequestRepository.save(loanRequest);
        return "Loan request submitted successfully with ID: " + loanRequestObject.getId();
    }

    public List<LoanRequest> getAllLoanRequestForCustomer() {
        User user = userRepository.findByUsername(jwt.getUserName());
        Account account = accountRepository.findByEmail(user.getEmail());
        return loanRequestRepository.findByAccount(account);
    }

    public List<LoanRequest> getAllLoanRequest() {
        return loanRequestRepository.findAll();
    }

    public String cancelLoanRequest(Long id) {
        LoanRequest loanRequest = loanRequestRepository.findById(id).get();
        loanRequest.setLoanRequestStatus(LoanRequestStatus.CANCELED);
        loanRequestRepository.save(loanRequest);
        return "Loan request has been canceled successfully";
    }

    public String refuseLoanRequestStatus(Long id) {
        LoanRequest loanRequest = loanRequestRepository.findById(id).get();
        loanRequest.setLoanRequestStatus(LoanRequestStatus.REFUSED);
        loanRequestRepository.save(loanRequest);
        return "Loan request has been refused successfully";
    }

    public String acceptLoanRequestStatus(Long id, LoanTypeRequest loanTypeRequest) throws IOException {
        LoanRequest loanRequest = loanRequestRepository.findById(id).get();
        loanRequest.setLoanRequestStatus(LoanRequestStatus.APPROVED);
        User user = userRepository.findByUsername(jwt.getUserName());
        Agent agent = user.getAgent();
        loanRequest.setAgent(agent);


        Loan loan = new Loan();
        Tmm tmm = new Tmm();

        if (LoanType.CAR.equals(loanRequest.getPurpose())) {
            LoanCarRequest loanCarRequest = (LoanCarRequest) loanTypeRequest;
            String monthlyPayment = loanService.autoLoan(
                    loanCarRequest.getCarPrice(),
                    loanCarRequest.getCarPower(),
                    loanCarRequest.getSum(),
                    loanCarRequest.getMonth())
                    .getAsString("monthlyPayment");

            loan.setActualSum(loanRequest.getSum());
            loan.setMonthlyPayment(new BigDecimal(monthlyPayment));
            loan.setInterestRate(tmm.retrieveTmmRate());

            BigDecimal returnedSum = new BigDecimal(Integer.parseInt(monthlyPayment) * Integer.parseInt(loanCarRequest.getMonth()));
            loan.setReturnedSum(returnedSum);

            loan.setLoanRequest(loanRequest);
        }

        Loan loan1 = loanRepository.save(loan);

        loanRequest.setLoan(loan1);
        loanRequestRepository.save(loanRequest);

        return "Loan request has been refused successfully";
    }
}
