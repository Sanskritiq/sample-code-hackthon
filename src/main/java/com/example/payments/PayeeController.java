package com.example.payments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payees")
public class PayeeController {

    @Autowired
    private PayeeService payeeService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayee(@PathVariable Long id) {
        try {
            payeeService.deletePayee(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
