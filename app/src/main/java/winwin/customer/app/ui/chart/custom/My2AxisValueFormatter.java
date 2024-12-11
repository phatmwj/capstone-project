package winwin.customer.app.ui.chart.custom;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;

public class My2AxisValueFormatter extends ValueFormatter {

    private final DecimalFormat mFormat;

    public My2AxisValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0");
    }

    @Override
    public String getFormattedValue(float value) {
        return String.valueOf((int) value) + " đh";
    }
}

