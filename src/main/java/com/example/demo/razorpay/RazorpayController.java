package com.example.demo.razorpay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class RazorpayController {

    @Autowired
    private RazorpayService razorpayService;

    @PostMapping(value = "/create-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createOrder(@RequestParam double amount, @RequestParam String currency) {
        try {
            String orderJson = razorpayService.createOrder(amount, currency, "txn_" + System.currentTimeMillis());
            return ResponseEntity.ok(orderJson);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error creating Razorpay order: " + e.getMessage()));
        }
    }

    @PostMapping("/verify-payment")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> data) {
        String orderId = data.get("razorpay_order_id");
        String paymentId = data.get("razorpay_payment_id");
        String signature = data.get("razorpay_signature");

        boolean isValid = razorpayService.verifySignature(orderId, paymentId, signature);

        if (isValid) {
            // TODO: Grant user access to the chosen plan in DB
            return ResponseEntity.ok(Map.of("success", true, "message", "Payment verified"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("success", false, "message", "Invalid signature"));
        }
    }
}