package com.example.demo.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_OP" , discriminatorType = DiscriminatorType.STRING , length = 1)
public abstract class Operation implements Serializable {

    @Id @GeneratedValue
    private long numero;
    private Date date;
    private double montant;
    @ManyToOne
    @JoinColumn(name = "CODE_CPTE")
    private Compte compte;

    public Operation(Date date, double montant, Compte compte) {
        this.date = date;
        this.montant = montant;
        this.compte = compte;
    }
    public Operation() {
        super();
    }

}
