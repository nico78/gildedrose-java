package com.gildedrose;

import org.approvaltests.Approvals;
import org.approvaltests.reporters.QuietReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


//@UseReporter(QuietReporter.class)
public class GildedRoseApprovalTest {

    @Test
    public void runTest() throws Exception {

        GildedRose app = new GildedRose(new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
//                ,
//                // this conjured item does not work properly yet
//                new Item("Conjured Mana Cake", 3, 6)
        });
        int days = 31;

        final int numItems = app.items.length;

        final List< String[]> results = runResults(app, days);
        final String resultStr = stringResult(days, numItems, results);
        Approvals.verify(resultStr);
    }

    private String stringResult(int days, int numItems, List<String[]> results) {
        StringBuilder sb = new StringBuilder();
        sb.append("day|name|sellIn|quality" + "\n");
        for (int i = 0; i < numItems; i++) {
            final String[] result = results.get(i);
            for (int d = 0; d < days; d++) {
                sb.append(d + "|" + result[d]+"\n");
            }
        }

        return sb.toString();
    }

    private List<String[]> runResults(GildedRose app, int days) {
        Item[] items = app.items;
        List<String[]> results = new ArrayList< String[]>();
        for (Item item : items) results.add(new String[days]);
        for (int d = 0; d < days; d++) {
            for (int i = 0; i < items.length; i++) {
                results.get(i)[d] = items[i].toString();
            }
            app.updateQuality();
        }
        return results;
    }

}
