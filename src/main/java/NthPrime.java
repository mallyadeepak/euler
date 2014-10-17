
public class NthPrime {

    public static void main(String args[]) {
        NthPrime nthPrime = new NthPrime();
        nthPrime.printNthPrime(10001);
    }

    public void printNthPrime(int n) {
        int countPrimes = 0;
        int startNumber = 2;
        while (true) {
            if (isPrime(startNumber)) {
                countPrimes++;
            }
            if (countPrimes == n) {
                break;
            }
            startNumber++;
        }
        System.out.println(n + "th prime number is = " + startNumber);
    }

    private boolean isPrime(int n) {
        for (int i = 2; i < (n - 1); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
