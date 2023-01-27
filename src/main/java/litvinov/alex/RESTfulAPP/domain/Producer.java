package litvinov.alex.RESTfulAPP.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import litvinov.alex.RESTfulAPP.domain.Laptop;
import litvinov.alex.RESTfulAPP.domain.Monitor;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity(name = "produces")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Producer {
    @Id
    private String name;


}
