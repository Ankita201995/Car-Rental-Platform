package com.car_rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.car_rental.service.RazorpayService;
import com.razorpay.RazorpayException;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:5173") //allow react app
  

public class PaymentController {

    @Autowired
    private RazorpayService razorpayService;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> data) {
        try {
            // Validate input data
            if (!data.containsKey("amount") || !data.containsKey("currency")) {
                return ResponseEntity.badRequest().body("Amount and currency are required");
            }

            Object amountObj = data.get("amount");
            String currency = (String) data.get("currency");
            
            // Handle amount conversion (frontend sends amount in paise)
            int amount;
            if (amountObj instanceof Integer) {
                amount = (Integer) amountObj;
            } else if (amountObj instanceof Double) {
                amount = (int) Math.round((Double) amountObj);
            } else if (amountObj instanceof String) {
                try {
                    amount = Integer.parseInt((String) amountObj);
                } catch (NumberFormatException e) {
                    return ResponseEntity.badRequest().body("Invalid amount format");
                }
            } else {
                return ResponseEntity.badRequest().body("Invalid amount type");
            }

            // Validate amount
            if (amount <= 0) {
                return ResponseEntity.badRequest().body("Amount must be greater than 0");
            }

            // Validate currency
            if (!"INR".equalsIgnoreCase(currency)) {
                return ResponseEntity.badRequest().body("Only INR currency is supported");
            }

            // Create order
            Map<String, Object> orderData = razorpayService.createOrder(amount, currency, "receipt_" + System.currentTimeMillis());
            return ResponseEntity.ok(orderData);
            
        } catch (RazorpayException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to create payment order: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unexpected error: " + e.getMessage());
        }
    }

    @PostMapping("/verify-payment")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> paymentData) {
        try {
            String paymentId = paymentData.get("paymentId");
            String orderId = paymentData.get("orderId");
            String signature = paymentData.get("signature");
            
            if (paymentId == null || orderId == null || signature == null) {
                return ResponseEntity.badRequest().body("Payment verification data is incomplete");
            }
            
            // Verify payment signature (you can implement this in RazorpayService)
            boolean isValid = razorpayService.verifyPaymentSignature(paymentId, orderId, signature);
            
            if (isValid) {
                return ResponseEntity.ok("Payment verified successfully");
            } else {
                return ResponseEntity.badRequest().body("Payment verification failed");
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Payment verification error: " + e.getMessage());
        }
    }
}
