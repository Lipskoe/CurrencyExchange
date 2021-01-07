package CurrencyExchange;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import java.awt.*;

public class CustomChartPanel extends ChartPanel {

    public CustomChartPanel(JFreeChart chart) {
        super(chart);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 210);
    }
}
