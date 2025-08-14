package com.tfe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.tfe.dto.CheckoutRequestDTO;
import com.tfe.service.PaiementService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/paiement")
public class PaiementController {
    @Value("${stripe.api.secret}")
    private String stripeSecretKey;

    private final PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    @PostMapping("/create-checkout-session")
    @Operation(
            summary = "Connexion a Stripe pour effectuer le paiement",
            tags = {"Paiement"}

    )
    public ResponseEntity<Map<String, String>> createCheckoutSession(@RequestBody CheckoutRequestDTO request) throws StripeException {
        String YOUR_DOMAIN = "http://localhost:4200";

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(YOUR_DOMAIN + "/paiement/success?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(YOUR_DOMAIN + "/paiement/cancel")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("eur")
                                        .setUnitAmount((long) (request.getMontant() * 100)) // ! montant en centimes
                                        .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                .setName("Paiement KidsCamp - Panier")
                                                .build())
                                        .build())
                                .build())
                .build();

        Session session = Session.create(params);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("checkoutUrl", session.getUrl());

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/confirm")
    @Operation(
            summary = "Méthode qui recoit les information de Stripe et lance la gestion d'après paiement",
            tags = {"Paiement"}

    )
    public ResponseEntity<String> confirmPayment(@RequestParam String sessionId, @RequestParam int idParent, @RequestParam double montant) {
        try {
            Session session = Session.retrieve(sessionId);

            if ("complete".equals(session.getStatus()) || "paid".equals(session.getPaymentStatus())) {

                paiementService.gestionPaiementReussi(sessionId, idParent, montant);

                return ResponseEntity.ok("Paiement confirmé et inscription enregistrée");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paiement non validé");
            }
        } catch (StripeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur Stripe : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur serveur : " + e.getMessage());
        }
    }
}
