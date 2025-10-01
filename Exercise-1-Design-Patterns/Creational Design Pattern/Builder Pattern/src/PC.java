package src;

public class PC {
    private String cpu;
    private int ram;
    private String gpu;
    private int storage;
    private boolean cooling;
    private String caseType;
    
    private PC(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.gpu = builder.gpu;
        this.storage = builder.storage;
        this.cooling = builder.cooling;
        this.caseType = builder.caseType;
    }
    
    public static class Builder {
        private String cpu;
        private int ram = 8;
        private String gpu = "Integrated";
        private int storage = 256;
        private boolean cooling = false;
        private String caseType = "Standard";
        
        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }
        
        public Builder ram(int ram) {
            this.ram = ram;
            return this;
        }
        
        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }
        
        public Builder storage(int storage) {
            this.storage = storage;
            return this;
        }
        
        public Builder cooling(boolean cooling) {
            this.cooling = cooling;
            return this;
        }
        
        public Builder caseType(String caseType) {
            this.caseType = caseType;
            return this;
        }
        
        public PC build() {
            return new PC(this);
        }
    }
    
    @Override
    public String toString() {
        return "PC [cpu=" + cpu + ", ram=" + ram + "GB, gpu=" + gpu + ", storage=" + storage + "GB, cooling=" + cooling + ", caseType=" + caseType + "]";
    }
}
