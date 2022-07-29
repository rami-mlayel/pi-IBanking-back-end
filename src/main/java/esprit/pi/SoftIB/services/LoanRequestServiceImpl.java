package esprit.pi.SoftIB.services;

import esprit.pi.SoftIB.entities.Account;
import esprit.pi.SoftIB.entities.User;
import esprit.pi.SoftIB.filters.JWTAuthenticationFilter;
import esprit.pi.SoftIB.repository.AccountRepository;
import esprit.pi.SoftIB.repository.LoanRequestRepository;
import esprit.pi.SoftIB.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import esprit.pi.SoftIB.entities.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String addLoanRequest(LoanRequest loanRequest) throws Exception {
            LoanRequest loanRequestObject = loanRequestRepository.save(loanRequest);
            return "Loan request submitted successfully with ID: " + loanRequestObject.getId();
    }

    public List<LoanRequest> getAllLoanRequest() {
        User user = userRepository.findByUsername(jwt.getUserName());
        Account account = accountRepository.findByEmail(user.getEmail());
        return loanRequestRepository.findByAccount(account);
    }
}
