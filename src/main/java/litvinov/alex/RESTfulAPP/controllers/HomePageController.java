package litvinov.alex.RESTfulAPP.controllers;

import litvinov.alex.RESTfulAPP.MyException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/home")
public class HomePageController {
    private int count = 3;
    private List<Map<String, String>> list = new ArrayList<>(){{
        add(new HashMap<>(){{put("id", "1"); put("text", "first");}});
        add(new HashMap<>(){{put("id", "2"); put("text", "second");}});
    }};

    private Map<String, String> findById(String id){
        return list.stream()
                .filter(x -> x.get("id").equals(id))
                .findFirst()
                .orElseThrow(MyException::new);
    }

    @GetMapping
    public List<Map<String, String>> get(){
        return list;

    }

    @GetMapping("{id}")
    public Map<String, String> getId(@PathVariable String id){
        return findById(id);
    }

    @PostMapping
    public Map<String, String> post(@RequestBody Map<String, String> map){
        map.put("id", String.valueOf(count++));
        list.add(map);
        return map;
    }

    @PutMapping("{id}")
    public Map<String, String> put(@PathVariable String id,
                                   @RequestBody Map<String, String> map){
        Map<String, String> mapFromDb = findById(id);
        mapFromDb.putAll(map);
        return mapFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        list.remove(findById(id));
    }
}
