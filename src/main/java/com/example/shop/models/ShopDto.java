package com.example.shop.models;

public class ShopDto {

    private Long shopId;

    private String shopName;

    private boolean shopPublic;

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopPublic(boolean shopPublic) {
        this.shopPublic = shopPublic;
    }

    public boolean isShopPublic() {
        return shopPublic;
    }

    public ShopDto(Long shopId, String shopName, boolean shopPublic) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopPublic = shopPublic;
    }
}
