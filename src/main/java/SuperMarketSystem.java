import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SuperMarketSystem {

    static boolean validateFlag;
    static String selectedItems;
    static int selectionCount;
    static int countOfItem_A;
    static int countOfItem_B;
    static int countOfItem_C;
    static int countOfItem_D;
    static int priceOfItem_A;
    static int priceOfItem_B;
    static int priceOfItem_C;
    static int priceOfItem_D;
    static int totalPriceOfItems;
    static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) {

        System.out.println("*******WELCOME TO SUPER_MARKET_CHECKOUT_SYSTEM********");
        System.out.println("PLEASE FIND BELOW PRICING/DISCOUNT INFORMATION FOR AVAILABLE ITEM");
        System.out.println("ITEM\t\tPRICE\t\tOFFER");
        System.out.println("A\t\t\t50\t\t\t3 for 130");
        System.out.println("B\t\t\t30\t\t\t2 for 37");
        System.out.println("C\t\t\t20\t\t\tNo discount");
        System.out.println("D\t\t\t10\t\t\tNo discount");

        SuperMarketSystem superMarketSystem = new SuperMarketSystem();

        while (validateFlag == false && selectionCount < 5) {
            selectionCount=superMarketSystem.selectItems();
            validateFlag=superMarketSystem.validateSelectedItems(selectedItems);
        }
        if (validateFlag) {

            map = superMarketSystem.countingOfItems(selectedItems);
            totalPriceOfItems = superMarketSystem.calculatingPricingAndDiscountOfferOnAllItems(map);
            System.out.println("TOTAL PRICE OF ITEMS." + totalPriceOfItems);

        } else {

            System.out.println("YOU HAVE REACHED MAXIMUM LIMIT FOR INCORRECT SELECTION OF ITEMS.");
        }

    }

    /**
     * @return selectionCount after item selection
     */
    public int selectItems() {
        ++selectionCount;
        Scanner sc = new Scanner(System.in);
        System.out.print("PLEASE ENTER ITEMS :");
        selectedItems = sc.nextLine();
        return selectionCount;
    }

    /**
     * @param selectedItems- accept input string as item
     * @return validationFlag for selectedItems
     */
    public boolean validateSelectedItems(String selectedItems) {

        if (selectedItems != null && !selectedItems.isEmpty() && selectedItems.matches("[ABCD]+")) {
            return validateFlag = true;
        } else {
            System.out.println("ENTERED ITEMS ARE NOT VALID");
            return validateFlag;
        }
    }

    /**
     * @param selectedItems- accept input string as item
     * @return itemMap with each item count
     */
    public  Map<Character, Integer> countingOfItems(String selectedItems) {
        Map<Character, Integer> itemMap = new HashMap<>();
        char[] ch = selectedItems.toCharArray();
        for (Character item : ch) {
            if (itemMap.containsKey(item)) {
                itemMap.put(item, itemMap.get(item) + 1);
            } else {
                itemMap.put(item, 1);
            }
        }
        itemMap.forEach((key, value) -> System.out.println("Key:"+key + "---" +"Value:"+value));
        return itemMap;
    }

    /**
     * @param map- accept map having each item count
     * @return totalPriceOfItems
     */
    public int calculatingPricingAndDiscountOfferOnAllItems(Map<Character, Integer> map) {

        if (map.containsKey(SuperMarketConstant.ITEM_A)) {

            countOfItem_A = map.get(SuperMarketConstant.ITEM_A);
            priceOfItem_A = calculatingPricingAndDiscountOfferOnItemA_B(SuperMarketConstant.ITEM_A, countOfItem_A,
                    SuperMarketConstant.OFFER_AMOUNT_ON_ITEM_A, SuperMarketConstant.DIVISOR_OF_A,
                    SuperMarketConstant.FIXED_PRICE_ON_ITEM_A);
            System.out.println("PRICE OF A:" + priceOfItem_A);
        }
        if (map.containsKey(SuperMarketConstant.ITEM_B)) {

            countOfItem_B = map.get(SuperMarketConstant.ITEM_B);
            priceOfItem_B = calculatingPricingAndDiscountOfferOnItemA_B(SuperMarketConstant.ITEM_B, countOfItem_B,
                    SuperMarketConstant.OFFER_AMOUNT_ON_ITEM_B, SuperMarketConstant.DIVISOR_OF_B,
                    SuperMarketConstant.FIXED_PRICE_ON_ITEM_B);
            System.out.println("PRICE OF B:" + priceOfItem_B);

        }
        if (map.containsKey(SuperMarketConstant.ITEM_C)) {

            countOfItem_C = map.get(SuperMarketConstant.ITEM_C);
            priceOfItem_C = countOfItem_C * SuperMarketConstant.FIXED_PRICE_ON_ITEM_C;
            System.out.println("PRICE OF C:" + priceOfItem_C);
        }
        if (map.containsKey(SuperMarketConstant.ITEM_D)) {

            countOfItem_D = map.get(SuperMarketConstant.ITEM_D);
            priceOfItem_D = countOfItem_D * SuperMarketConstant.FIXED_PRICE_ON_ITEM_D;
            System.out.println("PRICE OF D:" + priceOfItem_D);
        }
        totalPriceOfItems = priceOfItem_A + priceOfItem_B + priceOfItem_C + priceOfItem_D;
        return totalPriceOfItems;
    }

    /**
     * @param item- item selection from A/B/C/D
     * @param countOfItem- count of item
     * @param offerAmountOnItem- offer amount on item
     * @param divisor- offer item count
     * @param fixedPriceOnItem- fixed price on item
     * @return priceOfItem
     */
    public static int calculatingPricingAndDiscountOfferOnItemA_B(char item, int countOfItem, int offerAmountOnItem, int divisor, int fixedPriceOnItem) {
        int offerCountOnItem = 0;
        int priceOfItem = 0;
        while (countOfItem >= divisor) {

            if (countOfItem % divisor == 0) {
                offerCountOnItem = countOfItem / divisor;
                priceOfItem = offerCountOnItem * offerAmountOnItem;
                break;
            } else {

                offerCountOnItem = countOfItem / divisor;
                priceOfItem = offerCountOnItem * offerAmountOnItem;
                countOfItem = countOfItem % divisor;
            }
        }
        if (countOfItem < divisor) {
            priceOfItem = priceOfItem + (countOfItem * fixedPriceOnItem);
        }
        return priceOfItem;
    }

}
