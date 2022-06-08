public enum Besorolas {
        MAGANSZEMELY("maganszemely"), KISVALLALKOZO("kisvallalkozo"), NAGYVALLALKOZO("nagyvallalkozo");

        private final String message;

        private Besorolas(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }

}
