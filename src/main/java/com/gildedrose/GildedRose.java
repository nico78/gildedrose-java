package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateQualityAndSellIn(item);
        }
    }

    private void updateQualityAndSellIn(Item item) {
        final boolean isAgedBrie = item.name.equals("Aged Brie");
        final boolean isBackstagePass = item.name.equals("Backstage passes to a TAFKAL80ETC concert");
        final boolean isSulfuras = item.name.equals("Sulfuras, Hand of Ragnaros");

        if(isAgedBrie)
            updateForAgedBrie(item);
        else if(isBackstagePass)
            updateForBackstagePass(item);
        else if(isSulfuras)
            updateForSulfuras(item);
        else
            updateDefault(item);
    }

    private void updateDefault(Item item) {
        updateQualityAndSellIn(item, false, false, false);
    }

    private void updateForSulfuras(Item item) {
        updateQualityAndSellIn(item, false, false, true);
    }

    private void updateForBackstagePass(Item item) {
        updateQualityAndSellIn(item, false, true, false);
    }

    private void updateForAgedBrie(Item item) {
        updateQualityAndSellIn(item, true, false, false);
    }

    private void updateQualityAndSellIn(Item item, boolean isAgedBrie, boolean isBackstagePass, boolean isSulfuras) {
        final boolean isOther = !(isAgedBrie || isBackstagePass || isSulfuras);
        if (!isAgedBrie && !isBackstagePass) {
            if (item.quality > 0) {
                if (!isSulfuras) {
                    item.quality = item.quality - 1;
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (isBackstagePass) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
            }
        }

        if (!isSulfuras) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (!isAgedBrie) {
                if (!isBackstagePass) {
                    if (item.quality > 0) {
                        if (!isSulfuras) {
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality;
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }
    }
}
