public class Bank {
    private String név;
    private String id;
    private int pénz;

    public Bank(String név, String id, int pénz) {
        this.név = név;
        this.id = id;
        this.pénz = pénz;
    }

    public String getName() {
        return név;
    }

    public void setName(String név) {
        this.név = név;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPenz() {
        return pénz;
    }

    public void setPenz(int pénz) {
        this.pénz = pénz;
    }
}
