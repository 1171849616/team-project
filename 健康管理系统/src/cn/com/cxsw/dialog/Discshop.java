package cn.com.cxsw.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.Chart;
import org.swtchart.ISeries;
import org.swtchart.ISeries.SeriesType;

import cn.com.cxsw.util.DbUtil;


public class Discshop extends Dialog {

	protected Object result;
	protected Shell shell;
	private   double[] ySeries = null;//纵轴上的值
	private   String[] cagetorySeries = null;//横轴
	private DbUtil db=new DbUtil();

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Discshop(Shell parent, int style) {
		super(parent, style);
		setText("所在地区统计");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shell.setTouchEnabled(true);
		shell.setSize(450, 300);
		shell.setText(getText());
		
		//设置shell的布局方式
				shell.setLayout(new FillLayout());
				
				//查询数据
				
				List<Map<String,Object>> list=db.query("SELECT u_city,COUNT(*) AS num FROM user GROUP BY u_city");
				ySeries=new double[list.size()];
				cagetorySeries=new String[list.size()];
				for(int i=0;i<list.size();i++){
					ySeries[i] = Double.parseDouble(list.get(i).get("num").toString());
					cagetorySeries[i]=list.get(i).get("u_city").toString();
				}
				
				createChart(shell);

	}
	
	public Chart createChart(Composite parent) {

        // create a chart
        Chart chart = new Chart(parent, SWT.NONE);
        chart.getTitle().setText("所在地区统计");

        // set category
        chart.getAxisSet().getXAxis(0).enableCategory(true);
        chart.getAxisSet().getXAxis(0).setCategorySeries(cagetorySeries);
        chart.getAxisSet().getXAxis(0).getTick().setTickLabelAngle(90);
        chart.getAxisSet().getXAxis(0).getTitle().setText("所在地区");
        chart.getAxisSet().getYAxis(0).getTitle().setText("人数");
        // add bar series
        ISeries barSeries = chart.getSeriesSet().createSeries(SeriesType.BAR,
                "数量");
        barSeries.setYSeries(ySeries);
        chart.getAxisSet().adjustRange();
        chart.setOrientation(SWT.HORIZONTAL);
        return chart;
    }

}
