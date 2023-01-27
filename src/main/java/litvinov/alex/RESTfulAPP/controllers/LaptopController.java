package litvinov.alex.RESTfulAPP.controllers;

import litvinov.alex.RESTfulAPP.MyException;
import litvinov.alex.RESTfulAPP.domain.Laptop;
import litvinov.alex.RESTfulAPP.domain.Producer;
import litvinov.alex.RESTfulAPP.repos.LaptopRepo;
import litvinov.alex.RESTfulAPP.repos.ProducerRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path ="main/laptop")
public class LaptopController {

    private LaptopRepo laptopRepo;
    private ProducerRepo producerRepo;

    public LaptopController(LaptopRepo laptopRepo,
                             ProducerRepo producerRepo){
        this.laptopRepo = laptopRepo;
        this.producerRepo = producerRepo;
    }

    @PostMapping("{id}")
    public Laptop post(@PathVariable String id,
                               @RequestBody  Laptop laptop){
        Producer producer = producerRepo
                .findById(id).orElseThrow();

        if(laptopRepo
                .findByName(laptop.getName()).isPresent())
            throw new MyException();

        laptop.setProducer(producer);
        laptopRepo.save(laptop);
        return laptop;

    }


    @PutMapping(path = "{id}")
    public Laptop putLaptop(@PathVariable String id,
                              @RequestBody Laptop laptop){
        Laptop lapFromDb = laptopRepo
                .findByName(id).orElseThrow();

        if(laptop.getPrice() != 0)
            lapFromDb.setPrice(laptop.getPrice());
        else throw new MyException();

        if(laptop.getName()!= null)
            lapFromDb.setName(laptop.getName());
        else throw new MyException();

        if(laptop.getRam() != 0)
            lapFromDb.setRam(laptop.getRam());
        else throw new MyException();

        if(laptop.getRom() != 0)
            lapFromDb.setRom(laptop.getRom());
        else throw new MyException();

        laptopRepo.save(lapFromDb);
        return lapFromDb;

    }

    @PatchMapping(path = "{id}")
    public Laptop patchLaptop(@PathVariable String id,
                                @RequestBody Laptop laptop){
        Laptop lapFromDb = laptopRepo
                .findByName(id).orElseThrow();

        if(laptop.getPrice() != 0)
            lapFromDb.setPrice(laptop.getPrice());
        if(laptop.getName()!= null)
            lapFromDb.setName(laptop.getName());
        if(laptop.getRam() != 0)
            lapFromDb.setRam(laptop.getRam());
        if(laptop.getRom() != 0)
            lapFromDb.setRom(laptop.getRom());

        laptopRepo.save(lapFromDb);
        return lapFromDb;

    }

    @DeleteMapping(path = "{id}")
    public void deleteLaptop(@PathVariable String id){
        laptopRepo.findByName(id).orElseThrow();
        laptopRepo.deleteByName(id);
    }

    @GetMapping("{id}")
    public Laptop getProducers(@PathVariable String id) {
        return laptopRepo
                .findByName(id).orElseThrow();
    }

}
