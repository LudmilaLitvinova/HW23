package hillellit.service;

import hillellit.model.Cart;
import hillellit.model.Product;
import hillellit.model.RepositoryProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Scanner;

/**
 * @author Ludmila Litvinova on 30.12
 */
@Service
@RequiredArgsConstructor
public class CartService {

    private final Cart cart;
    private final RepositoryProduct products;

    public void runCart() {
        boolean isWorking = true;
        int resp;

        do {
            System.out.println("Привіт! Зроби вибір із наступних дій: ");
            System.out.println("""
                    1 - перевірити вміст корзини
                    2 - список доступних товарів
                    3 - додати товар у корзину
                    4 - видалити товар із корзини
                    0 - вийти із програми""");

            resp = checkOnInt();

            switch (resp) {
                case 0 -> isWorking = false;
                case 1 -> {
                    System.out.println(cart);
                    System.out.println("=======================================");
                }
                case 2 -> {
                    System.out.println(products.getAll());
                    System.out.println("=======================================");
                }
                case 3 -> {
                    addToCart(cart, products);
                    System.out.println("=======================================");
                }
                case 4 -> {
                    deleteFromCart(cart, products);
                    System.out.println("=======================================");
                }
                default -> {
                    System.out.println("Ви вибрали неіснуючу дію. Спробуйте ще раз.");
                    System.out.println("=======================================");
                }
            }
        } while (isWorking);
    }

    public static void addToCart(Cart cart, RepositoryProduct products) {
        boolean isExit;
        int id;

        do {
            System.out.println("Введіте id товару.");
            id = checkOnInt();

            isExit = checkProductList(id, products);
            if (!isExit)
                System.out.println("Ви ввели неіснуючий товар. Спробуйте ще раз. ");
        } while (!isExit);
        cart.addProduct(products.getById(id));
        System.out.println("Наступний товар " + products.getById(id) + " було додано у корзину.");
    }

    public static void deleteFromCart(Cart cart, RepositoryProduct products) {
        if (cart.getProducts().size() > 0) {
            boolean isExitProducts;
            boolean isExitCart;
            int id;

            do {
                System.out.println("Введіте id товара для видалення. ");

                isExitCart = false;

                id = checkOnInt();

                isExitProducts = checkProductList(id, products);
                for (Map.Entry<Integer, Product> pr : cart.getProducts().entrySet()) {
                    if (id == pr.getKey()) {
                        isExitCart = true;
                        break;
                    }
                }
                if (!isExitProducts) {
                    System.out.println("Ви ввели неіснуючий товар. Спробуйте ще раз. ");
                } else if (!isExitCart) {
                    System.out.println("Ви ввели неіснуючий у корзині товар. Спробуйте ще раз. ");
                }
            } while (!isExitProducts || !isExitCart);
            cart.deleteProduct(products.getById(id));
            System.out.println("Наступний товар " + products.getById(id) + " було видалено із корзини.");

        } else {
            System.out.println("Ваша корзина порожня.");
        }
    }

    public static boolean checkProductList(int id, RepositoryProduct products) {
        boolean isExit = false;
        for (Product pr : products.getAll()) {
            if (id == pr.getId()) {
                isExit = true;
                break;
            }
        }
        return isExit;
    }

    public static int checkOnInt() {
        boolean isInt;
        Scanner sc = new Scanner(System.in);
        String respL;
        do {
            respL = sc.nextLine();
            isInt = true;
            try {
                Integer.parseInt(respL);
            } catch (NumberFormatException e) {
                System.out.println("Ви ввели не число.");
                isInt = false;
            }
        } while (!isInt);
        return Integer.parseInt(respL);
    }


}
