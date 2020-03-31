package com.example.demo.Entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {

    public Versement() {
        super();
    }

    public Versement(Date date, double montant, Compte compte) {
        super(date, montant, compte);
    }
}
