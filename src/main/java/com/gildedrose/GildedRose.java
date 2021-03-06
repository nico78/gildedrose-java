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
        advanceSellIn(item);
        QualityChange dec =
                pastSellBy(item)
                        ? incBy(-2)
                        : incBy(-1);
        changeQuality(item, dec);
    }



    private void updateForSulfuras(Item item) {
        
    }

    private void updateForBackstagePass(Item item) {
        advanceSellIn(item);
        QualityChange change = null;
        if (pastSellBy(item)) change = q -> 0;
        else if (dueIn(item, 5)) change = incBy(3);
        else if (dueIn(item, 10)) change = incBy(2);
        else change = incBy(1);
        changeQuality(item, change);
    }

    private void updateForAgedBrie(Item item) {
        advanceSellIn(item);
        QualityChange inc = null;
        if (pastSellBy(item)) inc = incBy(2);
        else inc = incBy(1);
        changeQuality(item, inc);
    }

    /////////////////////////////////////////////////////////////////////////

    @FunctionalInterface
    private static interface QualityChange{

        public int change(int quality);
    }
    private QualityChange incBy(int inc) {
        return n -> n + inc;
    }

    private boolean dueIn(Item item, int daysDue) {
        return item.sellIn < daysDue;
    }
    private boolean pastSellBy(Item item) {
        return dueIn(item, 0);
    }

    private void advanceSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private void changeQuality(Item item, QualityChange inc) {
        item.quality = Math.max(Math.min(inc.change(item.quality), maxQuality()), minQuality());
    }
    private int minQuality() {
        return 0;
    }
    private int maxQuality() {
        return 50;
    }

}
