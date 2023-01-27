package litvinov.alex.RESTfulAPP.repos;

import litvinov.alex.RESTfulAPP.domain.Laptop;
import litvinov.alex.RESTfulAPP.domain.Monitor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MonitorRepo extends CrudRepository<Monitor, Long> {

    @Query(value = "select * from monitors m where m.name = :name",
        nativeQuery = true)
    Optional<Monitor> findByName(@Param("name") String name);

    @Query(value = "select * from monitors " +
            "join laptops on monitors.producer = laptops.producer " +
            "join produces on laptops.producer = produces.name " +
            "where produces.name = :name",
            nativeQuery = true)
    Iterable<Monitor> findByProducer(@Param("name") String name);
    @Modifying
    @Transactional
    @Query("delete from monitors m where m.name = ?1")
    void deleteByName(String name);
}
