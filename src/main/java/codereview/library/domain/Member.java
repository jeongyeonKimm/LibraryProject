package codereview.library.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservationList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Loan> loanList = new ArrayList<>();

}
