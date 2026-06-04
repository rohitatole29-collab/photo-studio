package com.example.studio.service;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

@Service
public class PaymentService {
    @Value("${razorpay.key_id}") private String keyId;
    @Value("${razorpay.key_secret}") private String keySecret;

    public com.razorpay.Order createOrder(double amountInRupees) throws RazorpayException {
        RazorpayClient client = new RazorpayClient(keyId, keySecret);
        JSONObject req = new JSONObject();
        req.put("amount", (int)(amountInRupees * 100));
        req.put("currency", "INR");
        req.put("receipt", "txn_" + System.currentTimeMillis());
        return client.orders.create(req);
    }

    public boolean verifySignature(String orderId, String paymentId, String signature) {
        try {
            String payload = orderId + "|" + paymentId;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(keySecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            String expected = HexFormat.of().formatHex(mac.doFinal(payload.getBytes(StandardCharsets.UTF_8)));
            return expected.equals(signature);
        } catch (Exception e) {
            return false;
        }
    }
}
