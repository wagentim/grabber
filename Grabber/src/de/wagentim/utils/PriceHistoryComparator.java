package de.wagentim.utils;

import java.util.Comparator;

import de.wagentim.grabber.db.PriceHistory;

public class PriceHistoryComparator implements Comparator<PriceHistory>
{

	@Override
	public int compare(PriceHistory ph1, PriceHistory ph2)
	{
		long time1 = ph1.getTime();
		long time2 = ph2.getTime();
		
		return time1 >= time2 ? 0 : 1;
	}

}
