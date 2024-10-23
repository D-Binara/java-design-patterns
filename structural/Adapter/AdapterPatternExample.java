package structural.Adapter;

interface AmericanSocket {
        String provideElectricity();
}


class EuropeanPlug {
        public String plugIn() {
                return "European plug plugged in with 230V";
        }
}

class AdapterAmericanToEuropean implements AmericanSocket {
        private final EuropeanPlug europeanPlug;

        public AdapterAmericanToEuropean(EuropeanPlug europeanPlug) {
                this.europeanPlug = europeanPlug;
        }

        @Override
        public String provideElectricity() {
                return convertVoltage() + " - " + europeanPlug.plugIn();
        }

        private String convertVoltage() {
                return "Converting 120V from American Socket to 230V";
        }
}

public class AdapterPatternExample {
        public static void main(String[] args) {
                AmericanSocket americanSocket = new AdapterAmericanToEuropean(new EuropeanPlug());
                System.out.println(americanSocket.provideElectricity());
        }
}
