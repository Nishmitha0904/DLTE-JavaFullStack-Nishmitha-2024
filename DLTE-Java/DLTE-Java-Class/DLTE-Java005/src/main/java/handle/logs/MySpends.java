package handle.logs;

public class MySpends {
    protected String[] myBeneficiaries = {"John", "Alice", "Peter", "Sofia", "Jake"};

    public final void filter(String phrase) {
        for (String each : myBeneficiaries) {
            if (each.startsWith(phrase))
                System.out.println(each);
        }
    }
}