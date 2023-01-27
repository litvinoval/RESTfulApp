package litvinov.alex.RESTfulAPP.repos;

import litvinov.alex.RESTfulAPP.domain.Laptop;
import litvinov.alex.RESTfulAPP.domain.Monitor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface LaptopRepo extends CrudRepository<Laptop, Long> {

    @Query(value = "select * from laptops l where l.name= :name ", nativeQuery = true)
    Optional<Laptop> findByName(@Param("name") String name);

    @Query(value = "select * from laptops " +
            "join monitors on laptops.producer = monitors.producer " +
            "join produces on monitors.producer = produces.name " +
            "where produces.name = :name",
            nativeQuery = true)
    Iterable<Laptop> findByProducer(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("delete from laptops l where l.name = ?1")
    void deleteByName(String name);
}