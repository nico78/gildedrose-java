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
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            if (item.quality > 0) {
                item.quality = item.quality - 1;
            }
        }
    }

    private void updateForSulfuras(Item item) {

    }

    private void updateForBackstagePass(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

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

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private void updateForAgedBrie(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }
        }
    }

}
