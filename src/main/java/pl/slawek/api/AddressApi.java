package pl.slawek.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slawek.domain.address.Address;
import pl.slawek.domain.address.service.AddressService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/address")
public class AddressApi {

    private final AddressService service;

    @GetMapping
    public ResponseEntity<List<Address>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Address> getOne(@PathVariable long id) {
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> add(@Valid @RequestBody Address address) {
        return new ResponseEntity<>(service.add(address), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
