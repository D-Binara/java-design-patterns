public class SingletonTest {
    public static void main(String[] args) {
        Singleton singleObject = Singleton.getInstance();
        singleObject.showMessage();
    }
}

