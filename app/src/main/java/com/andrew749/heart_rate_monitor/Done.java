package com.andrew749.heart_rate_monitor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
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

public class Done extends ActionBarActivity {
    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    public GraphicalView graphicalView;
    static XYSeries series = new XYSeries("heart rate");
    Button restart;
    double total=0;

    //sets all of the options for the renderer
    private XYMultipleSeriesRenderer getDemoRenderer() {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setAxisTitleTextSize(HeartRateMonitor.CHART_AXIS_TITLE_SIZE);
        renderer.setChartTitleTextSize(HeartRateMonitor.CHART_TITLE_SIZE);
        renderer.setLabelsTextSize(HeartRateMonitor.CHART_LABELS_TEXT_SIZE);
        renderer.setLegendTextSize(HeartRateMonitor.CHART_LEGEND_TEXT_SIZE);
        renderer.setPointSize(HeartRateMonitor.CHART_POINT_SIZE);
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
        int i = 0;
        restart = (Button) findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartTest();
            }
        });
        TextView tv = (TextView) findViewById(R.id.tv);
        Bundle b = getIntent().getExtras().getBundle("rates");
        double[] rates = b.getDoubleArray("ratesbundle");
        for (double r : rates) {
            tv.append(r + "\n");
            series.add(i, r);
            i++;
            total+=r;
        }
        tv.append("Average: "+ total/i );
        dataset.addSeries(series);
        graphicalView = ChartFactory.getScatterChartView(this, dataset, getDemoRenderer());
        LinearLayout layout = (LinearLayout) findViewById(R.id.lll);
        layout.addView(graphicalView);
    }

    private void restartTest() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), HeartRateMonitor.class);
        startActivity(intent);
        this.finish();
    }
}
