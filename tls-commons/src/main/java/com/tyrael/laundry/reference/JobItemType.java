package com.tyrael.laundry.reference;

/**
 * @author mbmartinez
 */
public enum JobItemType {

    HANDKERCHIEF("Handkerchief", "singles/folded12.png"),
    UNDERWEAR("Underwear", "shopping/png/black278.png"),
    SOCKS("Socks", "singles/sock8.png"),
    SHORTS("Shorts", "singles/football24.png"),
    PAJAMA("Pajama", "shopping/png/long35.png"),
    BLOUSE("Blouse", "shopping/png/female203.png"),
    JACKET("Jacket", "shopping/png/black276.png"),
    DRESS("Dress", "shopping/png/dress7.png"),
    SHIRT_COLLAR("T-shirt w/ collar", "singles/tie8.png"),
    SHIRT_SS("Shirt, short sleeves", "shopping/png/tshirt18.png"),
    SHIRT_LS("Shirt, long sleeves", "singles/longshirts.png"),
    SLACKS("Slacks, pants, trousers", "singles/jeans.png"),
    SWIMSUIT("Swimwear", "singles/beach35.png"),
    SCARVES("Scarves & shawls", "singles/scarf1.png"),
    SKIRT("Skirts", "singles/skirt.png"),
    HOODIES("Hooded shirts", "singles/winter-clothes.png"),
    OTHERS("Others", "singles/question30.png");

    private String label;
    private String iconPath;
    private JobItemType(String label, String iconPath) {
        this.label = label;
        this.iconPath = iconPath;
    }
    public String getLabel() {
        return label;
    }
    public String getIconPath() {
        return iconPath;
    }
}
