package communication;

public enum Hosts {
    LOCAL {
        @Override
        public String toString() {
            return "localhost";
        }
    },

    INTERNAL_RABBIT {
        @Override
        public String toString() {
            return "rabbit";
        }
    }
}
