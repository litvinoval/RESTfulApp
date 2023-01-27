package litvinov.alex.RESTfulAPP.controllers;

import litvinov.alex.RESTfulAPP.domain.Laptop;
import litvinov.alex.RESTfulAPP.domain.Monitor;
import litvinov.alex.RESTfulAPP.domain.Producer;
import litvinov.alex.RESTfulAPP.repos.LaptopRepo;
import litvinov.alex.RESTfulAPP.repos.MonitorRepo;
import litvinov.alex.RESTfulAPP.repos.ProducerRepo;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/main/produce")
public class ProducerController {
    private ProducerRepo producerRepo;
    private MonitorRepo monitorRepo;
    private LaptopRepo laptopRepo;

    public ProducerController(ProducerRepo producerRepo,
                              LaptopRepo laptopRepo,
                              MonitorRepo monitorRepo){
        this.producerRepo = producerRepo;
        this.laptopRepo = laptopRepo;
        this.monitorRepo = monitorRepo;
    }

    @PostMapping
    public Producer postProducer(@RequestBody Producer producer){

        producerRepo.save(producer);
        return producer;
    }
    
    @GetMapping(value = "/monitors/{id}", produces = "application/json")
    public Iterable<Monitor> getMonitors(@PathVariable String id){
        System.out.println(id);
        return monitorRepo.findByProducer(id);
    }
    @GetMapping(value = "/laptops/{id}", produces = "application/json")
    public Iterable<Laptop> getLaptops(@PathVariable String id){
        return laptopRepo.findByProducer(id);
    }

    @GetMapping
    public String getProducers(){
        return "index";
    }
}
