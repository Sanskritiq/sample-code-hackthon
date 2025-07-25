package com.example.payments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayeeService {

    @Autowired
    private PayeeRepository payeeRepository;

    @Transactional
    public void deletePayee(Long id) {
        // Check if the payee exists before deleting
        if (!payeeRepository.existsById(id)) {
            throw new RuntimeException("Payee not found with ID: " + id);
        }
        payeeRepository.deleteById(id);
    }
}
