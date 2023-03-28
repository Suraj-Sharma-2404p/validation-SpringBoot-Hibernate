package validation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Table(name = "reservations")
@Entity
@Data
@Accessors(chain = true)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 20 , unique = true,nullable = false)
    private String code;
    @Column(nullable = false)
    private int bagsCount;
    @Column(nullable = false)
    private Date departureDate;
    @Column(nullable = false)
    private Date arrivalDate;
    @Column(nullable = false)
    private int roomNumber;
    @Column(nullable = false)
    private String[] extras;

    @ManyToOne(targetEntity = User.class , cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "id")
    @JsonBackReference(value = "user-reservations")
    private User user;

    @Lob
    @Column
    private String note;


}
