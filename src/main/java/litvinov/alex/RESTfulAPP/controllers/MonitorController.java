package litvinov.alex.RESTfulAPP.controllers;

import litvinov.alex.RESTfulAPP.MyException;
import litvinov.alex.RESTfulAPP.domain.Monitor;
import litvinov.alex.RESTfulAPP.domain.Producer;
import litvinov.alex.RESTfulAPP.repos.MonitorRepo;
import litvinov.alex.RESTfulAPP.repos.ProducerRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path ="main/monitor")
public class MonitorController {

    private MonitorRepo monitorRepo;
    private ProducerRepo producerRepo;

    public MonitorController(MonitorRepo monitorRepo,
                             ProducerRepo producerRepo){
        this.monitorRepo = monitorRepo;
        this.producerRepo = producerRepo;
    }

    @PostMapping("{id}")
    public Monitor postMonitor(@PathVariable String id,
                               @RequestBody Monitor monitor){
        Producer producer = producerRepo
                .findById(id).orElseThrow();

        if(monitorRepo.findByName(monitor.getName()).isPresent())
            throw new MyException();
        monitor.setProducer(producer);
        monitorRepo.save(monitor);
        return monitor;

    }


    @PutMapping(path = "{id}")
    public Monitor putMonitor(@PathVariable String id,
                              @RequestBody Monitor monitor){
        Monitor monFromDb = monitorRepo
                .findByName(id).orElseThrow();

        if(monitor.getDiag() != 0)
            monFromDb.setDiag(monitor.getDiag());
        else throw new MyException();
        if(monitor.getName()!= null)
            monFromDb.setName(monitor.getName());
        else throw new MyException();
        if(monitor.getPrice() != 0)
            monFromDb.setPrice(monitor.getPrice());
        else throw new MyException();
        monitorRepo.save(monFromDb);
        return monFromDb;

    }

    @PatchMapping(path = "{id}")
    public Monitor patchMonitor(@PathVariable String id,
                              @RequestBody Monitor monitor){
        Monitor monFromDb = monitorRepo
                .findByName(id).orElseThrow();

        if(monitor.getDiag() != 0)
            monFromDb.setDiag(monitor.getDiag());
        if(monitor.getName()!= null)
            monFromDb.setName(monitor.getName());
        if(monitor.getPrice() != 0)
            monFromDb.setPrice(monitor.getPrice());

        monitorRepo.save(monFromDb);
        return monFromDb;

    }


    @DeleteMapping(path = "{id}")
    public void deleteMonitor(@PathVariable String id){
        monitorRepo.deleteByName(id);
    }

    @GetMapping("{id}")
    public Monitor getProducers(@PathVariable String id) {
        return monitorRepo
                .findByName(id).orElseThrow();
    }

}
