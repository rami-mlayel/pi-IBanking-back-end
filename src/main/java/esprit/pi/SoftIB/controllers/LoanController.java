package esprit.pi.SoftIB.controllers;

import esprit.pi.SoftIB.request.CreditAuto;
import esprit.pi.SoftIB.request.CreditHome;
import esprit.pi.SoftIB.services.LoanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("loan")
public class LoanController {

    @Autowired
    private LoanServiceImpl loanService;

    @GetMapping(value = "/simulate/residence")
    @ResponseBody
    public ResponseEntity getCreditResidence(@RequestBody CreditHome creditHome) throws IOException {
        return ResponseEntity.ok(loanService.homeLoan(
                creditHome.getType().type,
                creditHome.getSum(),
                creditHome.getYear()));
    }

    @GetMapping(value = "/simulate/auto")
    @ResponseBody
    public ResponseEntity getCreditAuto(@RequestBody CreditAuto creditAuto) throws IOException {
        return ResponseEntity.ok(loanService.autoLoan(
                creditAuto.getCarPrice(),
                creditAuto.getCarPower().type,
                creditAuto.getSum(),
                creditAuto.getMonths()));
    }
}
