package litvinov.alex.RESTfulAPP.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Data
@Entity(name = "monitors")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Monitor{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "producer")
    private Producer producer;

    private String name;
    private int diag;
    private int price;

}
