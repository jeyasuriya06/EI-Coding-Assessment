import src.PC;

public class PCBuilder {
    public static void main(String[] args) {
        
        PC officePC = new PC.Builder()
                          .cpu("Intel i5")
                          .build();
        
        PC gamingPC = new PC.Builder()
                          .cpu("AMD Ryzen 9")
                          .ram(32)
                          .gpu("NVIDIA RTX 4090")
                          .storage(2000)
                          .cooling(true)
                          .caseType("Tower")
                          .build();
        
        PC miniPC = new PC.Builder()
                        .cpu("Intel i3")
                        .ram(16)
                        .build();
        
        System.out.println(officePC);

        System.out.println(gamingPC);
        
        System.out.println(miniPC);
    }
}

