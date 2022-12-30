package hillellit;

import hillellit.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

/**
 * @author Ludmila Litvinova on 30.12
 */
@SpringBootApplication
public class Main {
    @Autowired
    CartService cartService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void inited() {
        cartService.runCart();
    }
}

