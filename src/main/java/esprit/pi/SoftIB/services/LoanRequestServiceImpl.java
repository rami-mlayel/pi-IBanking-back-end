package esprit.pi.SoftIB.services;

import esprit.pi.SoftIB.repository.LoanRequestRepository;
import lombok.extern.slf4j.Slf4j;
import esprit.pi.SoftIB.entities.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoanRequestServiceImpl {

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    public String addLoanRequest(LoanRequest loanRequest) throws Exception {
            LoanRequest loanRequestObject = loanRequestRepository.save(loanRequest);
            return "Loan request submitted successfully with ID: " + loanRequestObject.getId();
    }
}
