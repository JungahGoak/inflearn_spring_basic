package hello.core.singleton;

public class StatefulService {

    private int price; //상태를 유지하는 필트

    public  void order(String name, int price){
        System.out.println("name = " + name);
        System.out.println("price = " + price);
        this.price = price; // 여기가 문제임
    }

    public int getPrice() {
        return price;
    }
}
