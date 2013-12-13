package com.jwetherell.heart_rate_monitor;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * Created by andrew on 12/12/13.
 */
public class Done extends Activity {
    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    public static GraphicalView graphicalView;
    static XYSeries series = new XYSeries("heart rate");
    private XYMultipleSeriesRenderer getDemoRenderer() {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setAxisTitleTextSize(16);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setPointSize(5f);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        XYSeriesRenderer r = new XYSeriesRenderer();
        r.setColor(Color.BLUE);
        r.setPointStyle(PointStyle.SQUARE);
        r.setFillBelowLine(true);
        r.setFillBelowLineColor(Color.WHITE);
        r.setFillPoints(true);
        renderer.addSeriesRenderer(r);
        return renderer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.donelayout);
int i=0;
        TextView tv=(TextView)findViewById(R.id.tv);
        Bundle b=getIntent().getExtras().getBundle("rates");
        double[] rates=b.getDoubleArray("ratesbundle");
        for(double r: rates){
            tv.append(r+"\n");
            series.add(i,r);
            i++;
        }
        dataset.addSeries(series);
        graphicalView = ChartFactory.getScatterChartView(this, dataset, getDemoRenderer());
        LinearLayout layout = (LinearLayout) findViewById(R.id.lll);
        layout.addView(graphicalView);
    }
}
