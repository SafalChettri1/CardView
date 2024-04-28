package net.com.gopal.cardview;

public enum CardTypes {
    VISA("Visa", "4"),
    AMERICAN_EXPRESS("American Express", "34", "37"),
    MASTERCARD("Mastercard", "51", "52", "53", "54", "55"),
    DISCOVER("Discover", "6011", "622126", "622127", "622128", "622129", "62213", "62214", "62215", "62216", "62217", "62218", "62219", "6222", "6223", "6224", "6225", "6226", "6227", "6228", "62290", "62291", "622920", "622921", "622922", "622923", "622924", "622925", "644", "645", "646", "647", "648", "649", "65"),
    UNKNOWN("Unknown");
    private final String displayName;
    private final String[] prefixes;

    CardTypes(String displayName, String... prefixes) {
        this.displayName = displayName;
        this.prefixes = prefixes;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static CardTypes detectCardType(String cardNumber) {
        for (CardTypes cardType : CardTypes.values()) {
            for (String prefix : cardType.prefixes) {
                if (cardNumber.startsWith(prefix)) {
                    return cardType;
                }
            }
        }
        return UNKNOWN;
    }
    private int getCardImageResource(CardTypes cardType) {
        int imageResource;
        switch (cardType) {
            case VISA:
                imageResource = R.drawable.visa;
                break;
            case MASTERCARD:
                imageResource = R.drawable.mastercard;
                break;
            case AMERICAN_EXPRESS:
                imageResource = R.drawable.amex;
                break;
            case DISCOVER:
                imageResource = R.drawable.discover;
                break;
            default:
                imageResource = R.drawable.unionpay;
                break;
        }
        return imageResource;
    }

}
