package com.example.demo.web;

import com.example.demo.Entities.Compte;
import com.example.demo.services.IBanqueService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BankController {
    @Autowired
    private IBanqueService iBanqueService;

    @RequestMapping(value = "/operation")
    public String index(){

        return "comptes";
    }

    @RequestMapping(value = "/consulterCompte" , method = RequestMethod.GET)
    public String consulter(Model model , String codeCompte){
        try {
            Compte compte = iBanqueService.consulterCompte(codeCompte);
            model.addAttribute("compte", compte);
        }catch (Exception e){
            model.addAttribute("Exception",e);
        }
        return "comptes";
    }
    @RequestMapping(value = "/")
    public String home(){

        return "redirect:operation";
    }
}
