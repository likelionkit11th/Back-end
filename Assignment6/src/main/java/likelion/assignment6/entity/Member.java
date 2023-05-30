package likelion.assignment6.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="member")
@NoArgsConstructor
public class Member {
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    Long memberId;

    String memberName;

    @Builder
    public Member(String memberName){
        this.memberName = memberName;

    }
    public void addOrder (Order order) {
        orders.add(order);
        order.setMember(this);
    }


}
