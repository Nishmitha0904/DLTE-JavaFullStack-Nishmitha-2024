package store.oops;

public class MyBankBonds {

    public static void main(String[] args) {
        Bonds[] bonds = {
                new Bonds(50000.0, 3.2, true, "John", 3),
                new Bonds(80000.0, 4.1, false, "Alice", 8),
                new Bonds(100000.0, 2.5, true, "Bob", 10),
                new Bonds(20000.0, 5.5, false, "Peter", 4),
                new Bonds(25000.0, 4.8, true, "Lan", 9),
        };

        MyBankBonds bankBonds = new MyBankBonds();
        bankBonds.sort(bonds);
        bankBonds.viewBonds(bonds);
    }

    public void sort(Bonds[] bonds) {
        Bonds temp = null;
        for (int select=0;select<bonds.length;select++) {
            for (int next=0;next<bonds.length-select-1;next++) {
                if (bonds[next].getInterestRate() < bonds[next+1].getInterestRate()) {
                    temp = bonds[next];
                    bonds[next] = bonds[next+1];
                    bonds[next+1] = temp;
                }
            }
        }
    }

    public void viewBonds(Bonds[] bonds) {
        Double highInterest = Double.MIN_VALUE;
        String holder="";
        for (Bonds bond : bonds) {
            if(bond.getInterestRate() >= highInterest) {
                highInterest = bond.getInterestRate();
                holder = bond.getBondHolder();
            }
        }
        System.out.println("The bond holder with high rate of interest is "+holder+" and the interest is "+highInterest+".");
    }

}
