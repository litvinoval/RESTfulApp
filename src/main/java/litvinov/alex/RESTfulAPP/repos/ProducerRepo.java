package litvinov.alex.RESTfulAPP.repos;

import litvinov.alex.RESTfulAPP.domain.Producer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface ProducerRepo extends CrudRepository<Producer, String> {


}
