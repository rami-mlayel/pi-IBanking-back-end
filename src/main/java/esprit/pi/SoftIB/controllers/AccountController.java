package esprit.pi.SoftIB.controllers;


import esprit.pi.SoftIB.entities.AccountRequest;
import esprit.pi.SoftIB.services.IAccountResquestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    IAccountResquestService iAccountRequestService;

    @PostMapping("/fillOutAccountReuqest")
    @ResponseStatus(HttpStatus.OK)
    public void fillOutAccountReuqest(@RequestBody AccountRequest accountRequest){
        iAccountRequestService.fillOutAccountReuqest(accountRequest);
    }



}
