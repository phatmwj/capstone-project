package winwin.customer.app.ui.chart.custom;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class CustomValueFormatter extends ValueFormatter {
    @Override
    public String getBarLabel(BarEntry barEntry) {
        return String.valueOf((int) barEntry.getY());
    }

    @Override
    public String getPointLabel(Entry entry) {
        return String.valueOf((int) entry.getY());
    }
}
