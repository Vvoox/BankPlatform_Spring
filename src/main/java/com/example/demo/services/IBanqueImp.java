package com.example.demo.services;

import com.example.demo.Entities.*;
import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class IBanqueImp implements IBanqueService {
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Compte consulterCompte(String codeCpte) {
        Compte cp = compteRepository.getOne(codeCpte);
        if(cp==null) throw  new RuntimeException("Compte introuvable");
        return cp;
    }

    @Override
    public void verser(String codeCpte, double montant) {
        System.out.println(consulterCompte(codeCpte).getClient().toString());

        Compte cp =consulterCompte(codeCpte);
        cp.setSolde(cp.getSolde()+montant);
        Versement v = new Versement(new Date(), montant,cp);
        Operation operation = operationRepository.save(v);
        compteRepository.save(cp);


    }

    @Override
    public void retirer(String codeCpte, double montant) {
        Compte cp =consulterCompte(codeCpte);
        double facilitiesCaisse = 0;
        if(cp instanceof CompteCourant){
            facilitiesCaisse=((CompteCourant) cp).getDecouvert();
            if(montant+facilitiesCaisse<0) throw new RuntimeException("Solde insuffisant!");
        }
        Retrait r = new Retrait(new Date(), montant,cp);
        Operation operation = operationRepository.save(r);
        cp.setSolde(cp.getSolde()-montant);
        compteRepository.save(cp);

    }

    @Override
    public void virement(String codeCpte1, String codeCpte2, double montant) {
        retirer(codeCpte1,montant);
        verser(codeCpte2 ,montant);

    }

    @Override
    public Page<Operation> listOperation(String codeCpte, int page, int size) {

        return operationRepository.listOperations(codeCpte,PageRequest.of(page,size));
    }
}
