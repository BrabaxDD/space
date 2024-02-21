package net.mortalsilence.olli.space.utility;

import java.util.Comparator;

class IntervalComparator implements Comparator<Interval> {
    @Override
    public int compare(Interval a, Interval b) {
        if (a.start < b.start)
            return -1;
        else if (a.start > b.start)
            return 1;
        else
            return 0;
    }
}