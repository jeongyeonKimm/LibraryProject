package codereview.library.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Embedded
    private Address address;

    protected Member() {
    }

    public Member(String name, String birth, String phone, String email, Address address) {
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public void changePhone(String phone) {
        this.phone = phone;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changeAddress(String zipcode, String main_address, String sub_address) {
        address.changeAddress(zipcode, main_address, sub_address);
    }

}
