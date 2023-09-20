package com.challenge.challenge.controller;

import com.challenge.challenge.model.Movement;
import com.challenge.challenge.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movements")
public class MovementsController {

    @Autowired
    private MovementService movementService;

    @PostMapping
    public Movement createMovement(@RequestBody Movement movement) {
        return movementService.createMovement(movement);
    }

    @GetMapping("/{id}")
    public Movement getMovement(@PathVariable Integer id) {
        return movementService.getMovement(id);
    }

    @PutMapping("/{id}")
    public Movement updateMovement(@PathVariable Integer id, @RequestBody Movement movement) {
        return movementService.updateMovement(id, movement);
    }

    @DeleteMapping("/{id}")
    public void deleteMovement(@PathVariable Integer id) {
        movementService.deleteMovement(id);
    }
}
