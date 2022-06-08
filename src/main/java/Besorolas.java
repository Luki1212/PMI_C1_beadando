public enum Besorolas {
        magánszemély("maganszemely"),
        kisvállalkozó("kisvallalkozo"),
        nagyvállalkozó("nagyvallalkozo");

        private Besorolas(String message) {
            this.message = message;
        }

        private final String message;

        public String getMessage() {
            return this.message;
        }

}
