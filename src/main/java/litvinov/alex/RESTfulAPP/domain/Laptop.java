package litvinov.alex.RESTfulAPP.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "laptops")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="producer")
    private Producer producer;

    private String name;
    private int ram;
    private int rom;
    private int price;
}
