public class Bank {
    private String név;
    private String id;
    private int pénz;
    private Besorolas besorolas;

    public Bank(String név, String id, int pénz, Besorolas besorolas) {
        this.név = név;
        this.id = id;
        this.pénz = pénz;
        this.besorolas = besorolas;
    }
    public Besorolas Getbesorolas() {
        return besorolas;
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


//tudok commit and pusholni