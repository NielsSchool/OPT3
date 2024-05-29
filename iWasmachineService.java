public interface IWasmachineService {
        Wasmachine startWasmachine(Wasprogramma wasprogramma);
        void updateWasmachineStatus(boolean beschikbaar);
        
        void voorWas();
        void hoofdWas();
        void naWas();
}