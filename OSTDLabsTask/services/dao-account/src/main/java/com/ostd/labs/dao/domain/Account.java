package com.ostd.labs.dao.domain;
import javax.persistence.*;
import java.util.Set;
/**
 * Created by pkiryukhin
 */

@Entity
@Table(name = "ACCOUNT")
@NamedQueries({
        @NamedQuery(name = "Account.findByBic", query = "select a from Account a where a.bic = :bic"),
        @NamedQuery(name = "Account.findByIban", query = "select a from Account a where a.iban = :iban"),
        @NamedQuery(name = "Account.findByIbanAndBic", query = "select a from Account a where a.bic = :bic and a.iban = :iban")
})
public class Account {
    public static final String FIND_BY_BIC = "Account.findByBic";
    public static final String FIND_BY_IBAN = "Account.findByIban";
    public static final String FIND_BY_IBAN_AND_BIC = "Account.findByIbanAndBic";
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "iban")
    private String iban;

    @Column(name = "bic")
    private String bic;

    public Account(){}

    public Account(String iban, String bic){
        this.iban = iban;
        this.bic = bic;
    }
    public String getIban(){
        return iban;
    }
    public void setIban(String iban){
        this.iban = iban;
    }
    public String getBic(){
        return bic;
    }
    public void setBic(String bic){
        this.bic = bic;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

}
