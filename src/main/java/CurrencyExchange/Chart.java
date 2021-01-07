package CurrencyExchange;
import java.util.ArrayList;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class Chart {

    public ChartPanel plotChart(ArrayList<String> dates, ArrayList<Float> values) {
        XYDataset dataset = createDataset(dates, values);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "", // Chart
                "Date", // X-Axis Label
                "PLN", // Y-Axis Label
                dataset);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,228,196));
        CustomChartPanel panel = new CustomChartPanel(chart);
        return panel;
    }

    private XYDataset createDataset(ArrayList<String> dates, ArrayList<Float> values) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries series = new TimeSeries("series");
        for (int counter = 0; counter < dates.size(); counter++) {
            int year=Integer.parseInt(dates.get(counter).substring(0,4));
            int month =Integer.parseInt(dates.get(counter).substring(5,7));
            int day =Integer.parseInt(dates.get(counter).substring(8,10));
            series.add(new Day(day, month, year), values.get(counter));
        }
        dataset.addSeries(series);
        return dataset;
    }
}