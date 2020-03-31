package com.example.demo;

import com.example.demo.Entities.*;
import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.OperationRepository;
import com.example.demo.services.IBanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;

@SpringBootApplication
public class BankSpringApplication implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private IBanqueService iBanqueService;

    public static void main(String[] args) {

       SpringApplication.run(BankSpringApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        Client c1 = clientRepository.save(new Client("Alfa","alfa@gmail.com"));
        Client c2 = clientRepository.save(new Client("beta","beta@gmail.com"));
        Client c3 = clientRepository.save(new Client("teta","teta@gmail.com"));

        Compte compte1 = compteRepository.save(new CompteCourant("code1",new Date(),90000,c1,6000 ));
        Compte compte2 = compteRepository.save(new CompteCourant("code2",new Date(),8000,c1,2000 ));
        Compte compte3 = compteRepository.save(new CompteCourant("code3",new Date(),10000,c2,6000 ));
        Compte compte4 = compteRepository.save(new CompteCourant("code4",new Date(),30000,c2,6000 ));
        Compte compte5 = compteRepository.save(new CompteEpargne("code5",new Date(),30000,c1,1000 ));
        Compte compte6 = compteRepository.save(new CompteEpargne("code6",new Date(),30000,c2,1000 ));

        Operation op1 = operationRepository.save(new Versement(new Date() , 5000 , compte1));
        Operation op2 = operationRepository.save(new Versement(new Date() , 1000 , compte2));
        Operation op3 = operationRepository.save(new Retrait(new Date() , 5000 , compte1));

        iBanqueService.verser("code1",1111111);
        clientRepository.findAll().forEach(client -> System.out.println(client.getCode()+" "+client.getNom()+" "+client.getEmail()));
    }
}
