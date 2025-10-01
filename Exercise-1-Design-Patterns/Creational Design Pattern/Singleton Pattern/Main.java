import src.CacheManager;

public class Main {
    public static void main(String[] args) {
        CacheManager cache = CacheManager.getInstance();

        cache.put("user1", "Name Of the User1");
        cache.put("user2", "Name Of the User2");

        System.out.println(cache.get("user1")); // updating the cache memory
        System.out.println(cache.get("user2"));

        CacheManager cache2 = CacheManager.getInstance();
        
        System.out.println(cache2.get("user1"));

        if (cache == cache2) {
            System.out.println("Only one instance has been created.");
        }
    }
}
