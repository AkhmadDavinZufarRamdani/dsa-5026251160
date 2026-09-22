package lw01.unguided;

public class MotorcycleWash extends WashService {
    private static final int CHARGE_PER_DAY = 15000;

    public MotorcycleWash(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        return getDays() * CHARGE_PER_DAY + 5000;
    }

    @Override
    public String label() {
        return "Motorcycle";
    }

}
