package winwin.customer.app.ui.chart;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import winwin.customer.app.R;
import winwin.customer.app.databinding.ActivityChartBinding;
import winwin.customer.app.di.component.ActivityComponent;
import winwin.customer.app.ui.base.activity.BaseActivity;
import winwin.customer.app.ui.chart.custom.CustomValueFormatter;
import winwin.customer.app.ui.chart.custom.DayAxisValueFormatter;
import winwin.customer.app.ui.chart.custom.My2AxisValueFormatter;
import winwin.customer.app.ui.chart.custom.MyAxisValueFormatter;
import winwin.customer.app.ui.chart.custom.XYMarkerView;

public class ChartActivity extends BaseActivity<ActivityChartBinding, ChartViewModel> {
    BarChart chart;
    LineChart lineChart;
    PieChart pieChart;
    @Override
    public int getLayoutId() {
        return R.layout.activity_chart;
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chart = viewBinding.chart1;
        lineChart = viewBinding.linechart;
        pieChart = viewBinding.piechart;

        chart.setVisibility(View.VISIBLE);
        lineChart.setVisibility(View.GONE);
        pieChart.setVisibility(View.GONE);
        setChart();
        setData(12, 50);
//        setChart();
//        setData(12,50);
//        setLineChart();
//        setLineData(12,50);
//        setPieChart();
//        setPieData(12,50);
        PopupMenu popupMenu = new PopupMenu(this, viewBinding.btnOptions);
        getMenuInflater().inflate(R.menu.menu_chart, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.item_pie_chart:
                    chart.setVisibility(View.GONE);
                    lineChart.setVisibility(View.GONE);
                    pieChart.setVisibility(View.VISIBLE);
                    setPieChart();
                    setPieData(12, 50);
                    return true;

                case R.id.item_line_chart:
                    chart.setVisibility(View.GONE);
                    lineChart.setVisibility(View.VISIBLE);
                    pieChart.setVisibility(View.GONE);
                    setLineChart();
                    setLineData(12, 50);
                    return true;

                case R.id.item_bar_chart:
                    chart.setVisibility(View.VISIBLE);
                    lineChart.setVisibility(View.GONE);
                    pieChart.setVisibility(View.GONE);
                    setChart();
                    setData(12, 50);
                    // Tùy chỉnh khoảng cách giữa các cột
                    float groupSpace = 0.3f; // Khoảng cách giữa các nhóm
                    float barSpace = 0.05f; // Khoảng cách giữa các cột trong cùng nhóm
                    float barWidth = 0.3f; // Độ rộng mỗi cột

                    // Tổng groupWidth phải là groupSpace + barSpace * 2 + barWidth * số_cột = 1.0
                    int groupCount = 12;

                    // specify the width each bar should have
                    chart.getBarData().setBarWidth(barWidth);

                    // restrict the x-axis range
                    chart.getXAxis().setAxisMinimum(1);

                    // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
                    chart.getXAxis().setAxisMaximum(1 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
                    chart.groupBars(1, groupSpace, barSpace);
                    chart.invalidate();
                    return true;

                default:
                    return false;
            }
        });
        viewBinding.btnOptions.setOnClickListener(v -> {
            popupMenu.show();
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_chart, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.item_pie_chart: {
//                chart.setVisibility(View.GONE);
//                lineChart.setVisibility(View.GONE);
//                pieChart.setVisibility(View.VISIBLE);
//                setPieChart();
//                setPieData(12, 50);
//                return true;
//            }
//            case R.id.item_line_chart: {
//                chart.setVisibility(View.GONE);
//                lineChart.setVisibility(View.VISIBLE);
//                pieChart.setVisibility(View.GONE);
//                setLineChart();
//                setLineData(12, 50);
//                return true;
//            }
//            case R.id.item_bar_chart: {
//                chart.setVisibility(View.VISIBLE);
//                lineChart.setVisibility(View.GONE);
//                pieChart.setVisibility(View.GONE);
//                setChart();
//                setData(12, 50);
//                return true;
//            }
//        }
//        return true;
//    }

    private void setChart(){
//        chart.setOnChartValueSelectedListener(this);

        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        // chart.setDrawYLabels(false);

        ValueFormatter xAxisFormatter = new DayAxisValueFormatter(chart);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(Typeface.DEFAULT);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(12);
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(xAxisFormatter);

        ValueFormatter custom = new MyAxisValueFormatter();

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(Typeface.DEFAULT);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        ValueFormatter custom2 = new My2AxisValueFormatter();
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(Typeface.DEFAULT);
        rightAxis.setLabelCount(8, false);
        rightAxis.setValueFormatter(custom2);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv); // Set the marker to the chart

        // setting data
//        seekBarY.setProgress(50);
//        seekBarX.setProgress(12);

        // chart.setDrawLegend(false);
    }

    private void setData(int count, float range) {
        float start = 1f;

        ArrayList<BarEntry> values1 = new ArrayList<>();
        ArrayList<BarEntry> values2 = new ArrayList<>();

        for (int i = (int) start; i < start + count; i++) {
            float val1 = (float) (Math.random() * (range + 1));
            float val2 = (float) ((int) (Math.random() * 10 +3));

            values1.add(new BarEntry(i, val1));
            values2.add(new BarEntry(i, val2));
        }

        BarDataSet set1, set2;

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) chart.getData().getDataSetByIndex(1);
            set1.setValues(values1);
            set2.setValues(values2);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values1, "Thu Nhập");
            set2 = new BarDataSet(values2, "Số Lượng Đơn Hàng");

            set1.setColor(Color.rgb(104, 241, 175));
            set2.setColor(Color.rgb(164, 228, 251));

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set2.setValueFormatter(new CustomValueFormatter());

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            dataSets.add(set2);

            BarData data = new BarData(dataSets);
//            data.setValueFormatter(new CustomValueFormatter());
            data.setBarWidth(0.3f); // Set độ rộng của cột

            chart.setData(data);

            // Tùy chỉnh khoảng cách giữa các cột
            float groupSpace = 0.3f; // Khoảng cách giữa các nhóm
            float barSpace = 0.05f; // Khoảng cách giữa các cột trong cùng nhóm
            float barWidth = 0.3f; // Độ rộng mỗi cột

            // Tổng groupWidth phải là groupSpace + barSpace * 2 + barWidth * số_cột = 1.0
            int groupCount = count;

            // specify the width each bar should have
            chart.getBarData().setBarWidth(barWidth);

            // restrict the x-axis range
            chart.getXAxis().setAxisMinimum(start);

            // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
            chart.getXAxis().setAxisMaximum(start + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
            chart.groupBars(start, groupSpace, barSpace);
            chart.invalidate();
        }
    }


    private void setLineChart(){
        // no description text
        lineChart.getDescription().setEnabled(false);

        // enable touch gestures
        lineChart.setTouchEnabled(true);

        lineChart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);
        lineChart.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);

        // set an alternative background color
        lineChart.setBackgroundColor(Color.WHITE);

        lineChart.animateX(1500);

        // get the legend (only possible after setting data)
        Legend l = lineChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(Typeface.DEFAULT);
        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setYOffset(11f);

        ValueFormatter xAxisFormatter = new DayAxisValueFormatter(chart);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(Typeface.DEFAULT);
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setValueFormatter(xAxisFormatter);

        ValueFormatter custom = new MyAxisValueFormatter();
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTypeface(Typeface.DEFAULT);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaximum(60f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setValueFormatter(custom);

        ValueFormatter custom2 = new My2AxisValueFormatter();
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setTypeface(Typeface.DEFAULT);
        rightAxis.setTextColor(Color.RED);
        rightAxis.setAxisMaximum(20f);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);
        rightAxis.setValueFormatter(custom2);
    }

    private void setLineData(int count, float range) {

        ArrayList<Entry> values1 = new ArrayList<>();
        ArrayList<Entry> values2 = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            float val1 = (float) (Math.random() * (range + 1));
            float val2 = (float) ((int) (Math.random() * 10 +3));
            values1.add(new BarEntry(i, val1));
            values2.add(new BarEntry(i, val2));
        }

        LineDataSet set1, set2;

        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) lineChart.getData().getDataSetByIndex(1);
            set1.setValues(values1);
            set2.setValues(values2);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values1, "Thu Nhập");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(false);
            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);

            // create a dataset and give it a type
            set2 = new LineDataSet(values2, "SL Don Hang");
            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set2.setColor(Color.RED);
            set2.setCircleColor(Color.BLACK);
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
            set2.setHighLightColor(Color.rgb(244, 117, 117));
            set2.setValueFormatter(new CustomValueFormatter());
            //set2.setFillFormatter(new MyFillFormatter(900f));

            // create a data object with the data sets
            LineData data = new LineData(set1, set2);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(9f);

            // set data
            lineChart.setData(data);
        }
    }

    private void setPieChart(){
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setCenterTextTypeface(Typeface.DEFAULT);
//        pieChart.setCenterText(generateCenterSpannableText());

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
//        pieChart.setOnChartValueSelectedListener(this);

        pieChart.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTypeface(Typeface.DEFAULT);
        pieChart.setEntryLabelTextSize(12f);

    }

    private void setPieData(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 1; i <= count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                    i+".01.2016",
                    getDrawable(R.drawable.vector)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Thống Kê");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(Typeface.DEFAULT);
        pieChart.setData(data);

        // undo all highlights
        pieChart.highlightValues(null);

        pieChart.setUsePercentValues(true);

        pieChart.invalidate();
    }

}
