package com.ostd.labs.dto.account;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author pkiryukhin
 */
@XmlRootElement
public class AccountDTO implements Serializable{
    private Long id;
	private String iban;
	private String bic;

    public AccountDTO() {
    }  

    public AccountDTO(String iban, String bic){
        this.iban = iban;
        this.bic = bic;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

}

