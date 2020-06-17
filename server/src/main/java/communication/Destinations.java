package communication;

public enum Destinations {
    SERVER_QUEUE_NAME {
        @Override
        public String toString() {
            return "server-queue";
        }
    },

    MATCH_TOPIC_NAME {
        @Override
        public String toString() {
            return "match-topic";
        }
    }
}