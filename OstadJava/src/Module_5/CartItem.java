package Module_5;

public class CartItem {
    private int productId;
    private String name;
    private double unitPrice;
    private int quantity;
    private boolean addedToCart;

    public CartItem(String name, double unitPrice) {
        this.productId = (int) (Math.random() * 9000 + 1000);
        this.name = name;
        this.unitPrice = unitPrice > 0 ? unitPrice : 1;
        this.quantity = 0;
        this.addedToCart = false;
    }

    public String getName() {
        return name;
    }

    public boolean isAddedToCart() {
        return addedToCart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addToCart() {
        if (!addedToCart) {
            addedToCart = true;
            quantity = 1;
            System.out.println(name + " added to cart");
        } else {
            System.out.println(name + " already in cart");
        }
    }

    public void removeFromCart() {
        if (addedToCart) {
            addedToCart = false;
            quantity = 0;
            System.out.println(name + " removed from cart");
        } else {
            System.out.println(name + " is not in cart");
        }
    }

    public void increaseQty() {
        if (addedToCart) {
            quantity++;
        }
    }

    public void decreaseQty() {
        if (addedToCart && quantity > 1) {
            quantity--;
        } else {
            System.out.println("Quantity cannot be less than 1");
        }
    }

    public double getTotalPrice() {
        return unitPrice * quantity;
    }

    public void printItem() {
        System.out.println(name + " | Price : " + unitPrice +
                " | Quality : " + quantity +
                " | Total : " + getTotalPrice());
    }

}
