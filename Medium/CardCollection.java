import java.util.*;

class CardCollection {
    private Map<String, List<String>> cardMap;

    public CardCollection() {
        cardMap = new HashMap<>();
    }

    public void addCard(String symbol, String card) {
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(card);
    }

    public Collection<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, Collections.emptyList());
    }

    public void displayAllCards() {
        if (cardMap.isEmpty()) {
            System.out.println("No cards in the collection.");
            return;
        }
        for (Map.Entry<String, List<String>> entry : cardMap.entrySet()) {
            System.out.println("Symbol: " + entry.getKey() + " -> Cards: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        CardCollection collection = new CardCollection();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Store a new card");
            System.out.println("2. Search for cards by symbol");
            System.out.println("3. Display all stored cards");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter card symbol: ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter card number/face (e.g., 7, King, Queen, Ace): ");
                    String card = scanner.nextLine();
                    collection.addCard(symbol, card);
                    System.out.println("Card added successfully.");
                    break;
                case 2:
                    System.out.print("Enter the symbol to search: ");
                    String searchSymbol = scanner.nextLine();
                    Collection<String> cards = collection.getCardsBySymbol(searchSymbol);
                    if (cards.isEmpty()) {
                        System.out.println("No cards found for the symbol: " + searchSymbol);
                    } else {
                        System.out.println("Cards of " + searchSymbol + ": " + cards);
                    }
                    break;
                case 3:
                    collection.displayAllCards();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
