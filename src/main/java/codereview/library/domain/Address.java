package codereview.library.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String zipcode;
    private String main_address;
    private String sub_address;

    protected Address(){
    }

    public Address(String zipcode, String main_address, String sub_address) {
        this.zipcode = zipcode;
        this.main_address = main_address;
        this.sub_address = sub_address;
    }
}
