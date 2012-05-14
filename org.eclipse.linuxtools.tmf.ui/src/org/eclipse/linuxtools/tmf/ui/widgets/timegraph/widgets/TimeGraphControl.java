/*****************************************************************************
 * Copyright (c) 2007, 2008 Intel Corporation, 2009, 2010, 2011, 2012 Ericsson.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Intel Corporation - Initial API and implementation
 *   Ruslan A. Scherbakov, Intel - Initial API and implementation
 *   Alvaro Sanchez-Leon - Updated for TMF
 *   Patrick Tasse - Refactoring
 *
 *****************************************************************************/

package org.eclipse.linuxtools.tmf.ui.widgets.timegraph.widgets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.linuxtools.tmf.ui.widgets.timegraph.ITimeGraphProvider;
import org.eclipse.linuxtools.tmf.ui.widgets.timegraph.ITimeGraphTreeListener;
import org.eclipse.linuxtools.tmf.ui.widgets.timegraph.TimeGraphTreeExpansionEvent;
import org.eclipse.linuxtools.tmf.ui.widgets.timegraph.model.ITimeEvent;
import org.eclipse.linuxtools.tmf.ui.widgets.timegraph.model.ITimeGraphEntry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;

public class TimeGraphControl extends TimeGraphBaseControl implements FocusListener, KeyListener, MouseMoveListener, MouseListener, MouseWheelListener, ControlListener, SelectionListener, MouseTrackListener, TraverseListener, ISelectionProvider {

    private static final int DRAG_NONE = 0;
    private static final int DRAG_TRACE_ITEM = 1;
    private static final int DRAG_SPLIT_LINE = 2;
    public static final boolean DEFAULT_DRAW_THREAD_JOIN = true;
    public static final boolean DEFAULT_DRAW_THREAD_WAIT = true;
    public static final boolean DEFAULT_DRAW_THREAD_RELEASE = true;
    public static final int H_SCROLLBAR_MAX = Integer.MAX_VALUE - 1;

    private static final double zoomCoeff = 1.5;

    private ITimeDataProvider _timeProvider;
    private boolean _isInFocus = false;
    private boolean _isDragCursor3 = false;
    private boolean _isWaitCursor = true;
    private boolean _mouseOverSplitLine = false;
    private int _itemHeightDefault = 19;
    private int _itemHeight = _itemHeightDefault;
    private int _minimumItemWidth = 0;
    private int _topIndex = 0;
    private int _dragState = DRAG_NONE;
    private int _dragX0 = 0;
    private int _dragX = 0;
    private int _idealNameSpace = 0;
    // private double _timeStep = 10000000;
    private long _time0bak;
    private long _time1bak;
    private ITimeGraphProvider fTimeGraphProvider = null;
    private ItemData _data = null;
    private List<SelectionListener> _selectionListeners;
    private List<ISelectionChangedListener> _selectionChangedListeners = new ArrayList<ISelectionChangedListener>();
    private List<ITimeGraphTreeListener> _treeListeners = new ArrayList<ITimeGraphTreeListener>();
    private Cursor _dragCursor3;
    private Cursor _WaitCursor;

    // Vertical formatting formatting for the state control view
    private boolean _visibleVerticalScroll = true;
    private int _borderWidth = 0;
    private int _headerHeight = 0;

    private Listener mouseScrollFilterListener;

    public TimeGraphControl(Composite parent, TimeGraphColorScheme colors) {

        super(parent, colors, SWT.NO_BACKGROUND | SWT.H_SCROLL | SWT.DOUBLE_BUFFERED);

        _data = new ItemData();

        addFocusListener(this);
        addMouseListener(this);
        addMouseMoveListener(this);
        addMouseTrackListener(this);
        addMouseWheelListener(this);
        addTraverseListener(this);
        addKeyListener(this);
        addControlListener(this);
        ScrollBar scrollHor = getHorizontalBar();

        if (scrollHor != null) {
            scrollHor.addSelectionListener(this);
        }

        _dragCursor3 = new Cursor(super.getDisplay(), SWT.CURSOR_SIZEWE);
        _WaitCursor = new Cursor(super.getDisplay(), SWT.CURSOR_WAIT);
    }

    @Override
    public void dispose() {
        super.dispose();
        _dragCursor3.dispose();
        _WaitCursor.dispose();
    }

    /**
     * Sets the timegraph provider used by this timegraph viewer.
     * 
     * @param timeGraphProvider the timegraph provider
     */
    public void setTimeGraphProvider(ITimeGraphProvider timeGraphProvider) {
        fTimeGraphProvider = timeGraphProvider;
        fTimeGraphProvider.setTimeGraphControl(this);
    }

    public void setTimeProvider(ITimeDataProvider timeProvider) {
        _timeProvider = timeProvider;
        adjustScrolls();
        redraw();
    }

    public void addSelectionListener(SelectionListener listener) {
        if (listener == null)
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
        if (null == _selectionListeners)
            _selectionListeners = new ArrayList<SelectionListener>();
        _selectionListeners.add(listener);
    }

    public void removeSelectionListener(SelectionListener listener) {
        if (null != _selectionListeners)
            _selectionListeners.remove(listener);
    }

    public void fireSelectionChanged() {
        if (null != _selectionListeners) {
            Iterator<SelectionListener> it = _selectionListeners.iterator();
            while (it.hasNext()) {
                SelectionListener listener = it.next();
                listener.widgetSelected(null);
            }
        }
    }

    public void fireDefaultSelection() {
        if (null != _selectionListeners) {
            Iterator<SelectionListener> it = _selectionListeners.iterator();
            while (it.hasNext()) {
                SelectionListener listener = it.next();
                listener.widgetDefaultSelected(null);
            }
        }
    }

    public Object[] getTraces() {
        return _data.getTraces();
    }

    public boolean[] getTraceFilter() {
        return _data.getTraceFilter();
    }

    public void refreshData() {
        _data.refreshData();
        adjustScrolls();
        redraw();
    }

    public void refreshData(ITimeGraphEntry traces[]) {
        _data.refreshData(traces);
        adjustScrolls();
        redraw();
    }

    public void adjustScrolls() {
        if (null == _timeProvider) {
            getHorizontalBar().setValues(0, 1, 1, 1, 1, 1);
            return;
        }

        // HORIZONTAL BAR
        // Visible window
        long time0 = _timeProvider.getTime0();
        long time1 = _timeProvider.getTime1();
        // Time boundaries
        long timeMin = _timeProvider.getMinTime();
        long timeMax = _timeProvider.getMaxTime();

        long delta = timeMax - timeMin;

        int timePos = 0;
        int thumb = H_SCROLLBAR_MAX;

        if (delta != 0) {
            // Thumb size (page size)
            thumb = Math.max(1, (int) (H_SCROLLBAR_MAX * ((double) (time1 - time0) / delta)));
            // At the beginning of visible window
            timePos = (int) (H_SCROLLBAR_MAX * ((double) (time0 - timeMin) / delta));
        }

        // position, minimum, maximum, thumb size, increment (half page)t, page
        // increment size (full page)
        getHorizontalBar().setValues(timePos, 0, H_SCROLLBAR_MAX, thumb, Math.max(1, thumb / 2), Math.max(2, thumb));
    }

    boolean ensureVisibleItem(int idx, boolean redraw) {
        boolean changed = false;
        if (idx < 0) {
            for (idx = 0; idx < _data._expandedItems.length; idx++) {
                if (((TimeGraphItem) _data._expandedItems[idx])._selected)
                    break;
            }
        }
        if (idx >= _data._expandedItems.length)
            return changed;
        if (idx < _topIndex) {
            _topIndex = idx;
            //FIXME:getVerticalBar().setSelection(_topItem);
            if (redraw)
                redraw();
            changed = true;
        } else {
            int page = countPerPage();
            if (idx >= _topIndex + page) {
                _topIndex = idx - page + 1;
                //FIXME:getVerticalBar().setSelection(_topItem);
                if (redraw)
                    redraw();
                changed = true;
            }
        }
        return changed;
    }

    public void setTopIndex(int idx) {
        idx = Math.min(idx, _data._expandedItems.length - countPerPage());
        idx = Math.max(0,  idx);
        _topIndex = idx;
        redraw();
    }

    public void setExpandedState(ITimeGraphEntry entry, boolean expanded) {
        TimeGraphItem item = _data.findItem(entry);
        if (item != null && item._expanded != expanded) {
            item._expanded = expanded;
            _data.updateExpandedItems();
            redraw();
        }
    }

    public void addTreeListener (ITimeGraphTreeListener listener) {
        if (!_treeListeners.contains(listener)) {
            _treeListeners.add(listener);
        }
    }

    public void removeTreeListener (ITimeGraphTreeListener listener) {
        if (_treeListeners.contains(listener)) {
            _treeListeners.remove(listener);
        }
    }

    public void fireTreeEvent(ITimeGraphEntry entry, boolean expanded) {
        TimeGraphTreeExpansionEvent event = new TimeGraphTreeExpansionEvent(this, entry);
        for (ITimeGraphTreeListener listener : _treeListeners) {
            if (expanded) {
                listener.treeExpanded(event);
            } else {
                listener.treeCollapsed(event);
            }
        }
    }

    @Override
    public ISelection getSelection() {
        TimeGraphSelection sel = new TimeGraphSelection();
        ITimeGraphEntry trace = getSelectedTrace();
        if (null != trace && null != _timeProvider) {
            long selectedTime = _timeProvider.getSelectedTime();
            ITimeEvent event = Utils.findEvent(trace, selectedTime, 0);
            if (event != null)
                sel.add(event);
            else
                sel.add(trace);
        }
        return sel;
    }

    public ISelection getSelectionTrace() {
        TimeGraphSelection sel = new TimeGraphSelection();
        ITimeGraphEntry trace = getSelectedTrace();
        if (null != trace) {
            sel.add(trace);
        }
        return sel;
    }

    public void selectTrace(int n) {
        if (n != 1 && n != -1)
            return;
        boolean changed = false;
        int lastSelection = -1;
        for (int i = 0; i < _data._expandedItems.length; i++) {
            TimeGraphItem item = (TimeGraphItem) _data._expandedItems[i];
            if (item._selected) {
                lastSelection = i;
                if (1 == n && i < _data._expandedItems.length - 1) {
                    item._selected = false;
                    if (item._hasChildren) {
                        _data.expandItem(i);
                        fireTreeEvent(item._trace, item._expanded);
                    }
                    item = (TimeGraphItem) _data._expandedItems[i + 1];
                    if (item._hasChildren) {
                        _data.expandItem(i + 1);
                        fireTreeEvent(item._trace, item._expanded);
                        item = (TimeGraphItem) _data._expandedItems[i + 2];
                    }
                    item._selected = true;
                    changed = true;
                } else if (-1 == n && i > 0) {
                    i--;
                    TimeGraphItem prevItem = (TimeGraphItem) _data._expandedItems[i];
                    if (prevItem._hasChildren) {
                        if (prevItem._expanded) {
                            if (i > 0) {
                                i--;
                                prevItem = (TimeGraphItem) _data._expandedItems[i];
                            }
                        }
                        if (!prevItem._expanded) {
                            _data.expandItem(i);
                            fireTreeEvent(prevItem._trace, prevItem._expanded);
                            prevItem = (TimeGraphItem) _data._expandedItems[i + prevItem.children.size()];
                            item._selected = false;
                            prevItem._selected = true;
                            changed = true;
                        }
                    } else {
                        item._selected = false;
                        prevItem._selected = true;
                        changed = true;
                    }
                }
                break;
            }
        }
        if (lastSelection < 0 && _data._expandedItems.length > 0) {
            TimeGraphItem item = (TimeGraphItem) _data._expandedItems[0];
            if (item._hasChildren) {
                _data.expandItem(0);
                fireTreeEvent(item._trace, item._expanded);
                item = (TimeGraphItem) _data._expandedItems[1];
                item._selected = true;
                changed = true;
            } else {
                item._selected = true;
                changed = true;
            }
        }
        if (changed) {
            ensureVisibleItem(-1, false);
            redraw();
            fireSelectionChanged();
        }
    }

    public void selectEvent(int n) {
        if (null == _timeProvider)
            return;
        ITimeGraphEntry trace = getSelectedTrace();
        if (trace == null)
            return;
        long selectedTime = _timeProvider.getSelectedTime();
        long endTime = _timeProvider.getEndTime();
        ITimeEvent nextEvent;
        if (-1 == n && selectedTime > endTime)
            nextEvent = Utils.findEvent(trace, selectedTime, 0);
        else
            nextEvent = Utils.findEvent(trace, selectedTime, n);
        if (null == nextEvent && -1 == n)
            nextEvent = Utils.getFirstEvent(trace);
        if (null != nextEvent) {
            long nextTime = nextEvent.getTime();
            // If last event detected e.g. going back or not moving to a next
            // event
            if (nextTime <= selectedTime && n == 1) {
                // Select to the end of this last event
                nextTime = nextEvent.getTime() + nextEvent.getDuration();
                // but not beyond the end of the trace
                if (nextTime > endTime) {
                    nextTime = endTime;
                }
            }
            _timeProvider.setSelectedTimeInt(nextTime, true);
            fireSelectionChanged();
        } else if (1 == n) {
            _timeProvider.setSelectedTimeInt(endTime, true);
            fireSelectionChanged();
        }
    }

    public void selectNextEvent() {
        selectEvent(1);
        // Notify if visible time window has been adjusted
        _timeProvider.setStartFinishTimeNotify(_timeProvider.getTime0(), _timeProvider.getTime1());
    }

    public void selectPrevEvent() {
        selectEvent(-1);
        // Notify if visible time window has been adjusted
        _timeProvider.setStartFinishTimeNotify(_timeProvider.getTime0(), _timeProvider.getTime1());
    }

    public void selectNextTrace() {
        selectTrace(1);
    }

    public void selectPrevTrace() {
        selectTrace(-1);
    }

    /**
     * Zooming based on mouse cursor location with mouse scrolling
     * 
     * @param zoomIn
     */
    public void zoom(boolean zoomIn) {
        int globalX = getDisplay().getCursorLocation().x;
        Point p = toControl(globalX, 0);
        int nameSpace = _timeProvider.getNameSpace();
        int timeSpace = _timeProvider.getTimeSpace();
        int xPos = Math.max(nameSpace, Math.min(nameSpace + timeSpace, p.x));
        long time0 = _timeProvider.getTime0();
        long time1 = _timeProvider.getTime1();
        long interval = time1 - time0;
        if (interval == 0) {
            interval = 1;
        } // to allow getting out of single point interval
        long newInterval;
        if (zoomIn) {
            newInterval = Math.max(Math.round((double) interval * 0.8), _timeProvider.getMinTimeInterval());
        } else {
            newInterval = (long) Math.ceil((double) interval * 1.25);
        }
        long center = time0 + Math.round(((double) (xPos - nameSpace) / timeSpace * interval));
        long newTime0 = center - Math.round((double) newInterval * (center - time0) / interval);
        long newTime1 = newTime0 + newInterval;
        _timeProvider.setStartFinishTime(newTime0, newTime1);
    }

    /**
     * zoom in using single click
     */
    public void zoomIn() {
        long _time0 = _timeProvider.getTime0();
        long _time1 = _timeProvider.getTime1();
        long _range = _time1 - _time0;
        long selTime = _timeProvider.getSelectedTime();
        if (selTime <= _time0 || selTime >= _time1) {
            selTime = (_time0 + _time1) / 2;
        }
        long time0 = selTime - (long) ((selTime - _time0) / zoomCoeff);
        long time1 = selTime + (long) ((_time1 - selTime) / zoomCoeff);

        long inaccuracy = (_timeProvider.getMaxTime() - _timeProvider.getMinTime()) - (time1 - time0);

        // Trace.debug("selTime:" + selTime + " time0:" + time0 + " time1:"
        // + time1 + " inaccuracy:" + inaccuracy);

        if (inaccuracy > 0 && inaccuracy < 100) {
            _timeProvider.setStartFinishTimeNotify(_timeProvider.getMinTime(), _timeProvider.getMaxTime());
            return;
        }

        long m = _timeProvider.getMinTimeInterval();
        if ((time1 - time0) < m) {
            time0 = selTime - (long) ((selTime - _time0) * m / _range);
            time1 = time0 + m;
        }

        _timeProvider.setStartFinishTimeNotify(time0, time1);
    }

    /**
     * zoom out using single click
     */
    public void zoomOut() {
        long _time0 = _timeProvider.getTime0();
        long _time1 = _timeProvider.getTime1();
        long selTime = _timeProvider.getSelectedTime();
        if (selTime <= _time0 || selTime >= _time1) {
            selTime = (_time0 + _time1) / 2;
        }
        long time0 = (long) (selTime - (selTime - _time0) * zoomCoeff);
        long time1 = (long) (selTime + (_time1 - selTime) * zoomCoeff);

        long inaccuracy = (_timeProvider.getMaxTime() - _timeProvider.getMinTime()) - (time1 - time0);
        if (inaccuracy > 0 && inaccuracy < 100) {
            _timeProvider.setStartFinishTimeNotify(_timeProvider.getMinTime(), _timeProvider.getMaxTime());
            return;
        }

        _timeProvider.setStartFinishTimeNotify(time0, time1);
    }

    public ITimeGraphEntry getSelectedTrace() {
        ITimeGraphEntry trace = null;
        int idx = getSelectedIndex();
        if (idx >= 0)
            trace = _data._expandedItems[idx]._trace;
        return trace;
    }

    public int getSelectedIndex() {
        int idx = -1;
        for (int i = 0; i < _data._expandedItems.length; i++) {
            TimeGraphItem item = (TimeGraphItem) _data._expandedItems[i];
            if (item._selected) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    boolean toggle(int idx) {
        boolean toggled = false;
        if (idx >= 0 && idx < _data._expandedItems.length) {
            TimeGraphItem item = (TimeGraphItem) _data._expandedItems[idx];
            if (item._hasChildren) {
                item._expanded = !item._expanded;
                _data.updateExpandedItems();
                adjustScrolls();
                redraw();
                toggled = true;
                fireTreeEvent(item._trace, item._expanded);
            }
        }
        return toggled;
    }

    int getItemIndexAtY(int y) {
        if (y < 0) {
            return -1;
        }
        int idx = y / _itemHeight;
        idx += _topIndex;
        if (idx < _data._expandedItems.length) {
            return idx;
        }
        return -1;
    }

    boolean isOverSplitLine(int x) {
        if (x < 0 || null == _timeProvider)
            return false;
        int w = 4;
        int nameWidth = _timeProvider.getNameSpace();
        if (x > nameWidth - w && x < nameWidth + w) {
            return true;
        } else {
            return false;
        }
    }

    TimeGraphItem getItem(Point pt) {
        int idx = getItemIndexAtY(pt.y);
        return idx >= 0 ? (TimeGraphItem) _data._expandedItems[idx] : null;
    }

    long getTimeAtX(int x) {
        if (null == _timeProvider)
            return -1;
        long hitTime = -1;
        Point size = getCtrlSize();
        long time0 = _timeProvider.getTime0();
        long time1 = _timeProvider.getTime1();
        int nameWidth = _timeProvider.getNameSpace();
        x -= nameWidth;
        if (x >= 0 && size.x >= nameWidth) {
            if (time1 - time0 > size.x - nameWidth - RIGHT_MARGIN) {
                // get the last possible time represented by the pixel position
                // by taking the time of the next pixel position minus 1
                // nanosecond
                hitTime = time0 + (long) ((time1 - time0) * ((double) (x + 1) / (size.x - nameWidth - RIGHT_MARGIN))) - 1;
            } else {
                hitTime = time0 + (long) ((time1 - time0) * ((double) (x) / (size.x - nameWidth - RIGHT_MARGIN)));
            }
        }
        return hitTime;
    }

    void selectItem(int idx, boolean addSelection) {
        boolean changed = false;
        if (addSelection) {
            if (idx >= 0 && idx < _data._expandedItems.length) {
                TimeGraphItem item = (TimeGraphItem) _data._expandedItems[idx];
                changed = (item._selected == false);
                item._selected = true;
            }
        } else {
            for (int i = 0; i < _data._expandedItems.length; i++) {
                TimeGraphItem item = (TimeGraphItem) _data._expandedItems[i];
                if ((i == idx && !item._selected) || (idx == -1 && item._selected)) {
                    changed = true;
                }
                item._selected = i == idx;
            }
        }
        changed |= ensureVisibleItem(idx, true);
        if (changed)
            redraw();
    }

    public void selectItem(ITimeGraphEntry trace, boolean addSelection) {
        int idx = _data.findItemIndex(trace);
        selectItem(idx, addSelection);
    }

    public int countPerPage() {
        int height = getCtrlSize().y;
        int count = 0;
        if (height > 0)
            count = height / _itemHeight;
        return count;
    }

    public int getTopIndex() {
        return _topIndex;
    }

    public int getExpandedElementCount() {
        return _data._expandedItems.length;
    }

    Point getCtrlSize() {
        Point size = getSize();
        if (getHorizontalBar().isVisible()) {
            size.y -= getHorizontalBar().getSize().y;
        }
        return size;
    }

    Rectangle getNameRect(Rectangle bound, int idx, int nameWidth) {
        idx -= _topIndex;
        int x = bound.x;
        int y = bound.y + idx * _itemHeight;
        int width = nameWidth;
        int height = _itemHeight;
        return new Rectangle(x, y, width, height);
    }

    Rectangle getStatesRect(Rectangle bound, int idx, int nameWidth) {
        idx -= _topIndex;
        int x = bound.x + nameWidth;
        int y = bound.y + idx * _itemHeight;
        int width = bound.width - x;
        int height = _itemHeight;
        return new Rectangle(x, y, width, height);
    }

    public void drawTraceEvent(Rectangle bound, ITimeEvent e, int nItem, int color, GC gc) {
        int nameWidth = _timeProvider.getNameSpace();

        long time0 = _timeProvider.getTime0();
        long time1 = _timeProvider.getTime1();
        if (time0 == time1)
            return;

        int xr = bound.x + nameWidth;
        double pixelsPerNanoSec = (bound.width - xr <= RIGHT_MARGIN) ? 0 : (double) (bound.width - xr - RIGHT_MARGIN) / (time1 - time0);

        int x0 = xr + (int) ((e.getTime() - time0) * pixelsPerNanoSec);
        if (x0 < xr)
            return;

        int y0 = bound.y + (nItem - _topIndex) * _itemHeight + 3;

        gc.setBackground(_colors.getColor(color));
        int c[] = { x0 - 3, y0 - 3, x0, y0, x0 + 3, y0 - 3 };
        gc.fillPolygon(c);
    }

    @Override
    void paint(Rectangle bounds, PaintEvent e) {
        GC gc = e.gc;
        gc.setBackground(_colors.getColor(TimeGraphColorScheme.BACKGROUND));
        drawBackground(gc, bounds.x, bounds.y, bounds.width, bounds.height);

        if (bounds.width < 2 || bounds.height < 2 || null == _timeProvider)
            return;

        _idealNameSpace = 0;
        int nameSpace = _timeProvider.getNameSpace();

        // draw empty name space background
        int nbDrawn = _data._expandedItems.length - _topIndex;
        if (_itemHeight * nbDrawn < bounds.height) {
            gc.setBackground(_colors.getBkColor(false, false, true));
            drawBackground(gc, bounds.x, _itemHeight * nbDrawn, nameSpace, bounds.height - _itemHeight * nbDrawn);
        }

        fTimeGraphProvider.drawItems(bounds, _timeProvider, _data._expandedItems, _topIndex, nameSpace, gc);

        // draw selected time
        long time0 = _timeProvider.getTime0();
        long time1 = _timeProvider.getTime1();
        long selectedTime = _timeProvider.getSelectedTime();
        double pixelsPerNanoSec = (bounds.width - nameSpace <= RIGHT_MARGIN) ? 0 : (double) (bounds.width - nameSpace - RIGHT_MARGIN) / (time1 - time0);
        int x = bounds.x + nameSpace + (int) ((double) (selectedTime - time0) * pixelsPerNanoSec);
        if (x >= nameSpace && x < bounds.x + bounds.width) {
            gc.setForeground(_colors.getColor(TimeGraphColorScheme.SELECTED_TIME));
            gc.drawLine(x, bounds.y, x, bounds.y + bounds.height);
        }

        // draw drag line, no line if name space is 0.
        if (DRAG_SPLIT_LINE == _dragState) {
            gc.setForeground(_colors.getColor(TimeGraphColorScheme.BLACK));
            gc.drawLine(bounds.x + nameSpace, bounds.y, bounds.x + nameSpace, bounds.y + bounds.height - 1);
        } else if (DRAG_NONE == _dragState && _mouseOverSplitLine && _timeProvider.getNameSpace() > 0) {
            gc.setForeground(_colors.getColor(TimeGraphColorScheme.RED));
            gc.drawLine(bounds.x + nameSpace, bounds.y, bounds.x + nameSpace, bounds.y + bounds.height - 1);
        }
    }

    public void drawItems(Rectangle bounds, ITimeDataProvider timeProvider, TimeGraphItem[] items, int topIndex, int nameSpace, GC gc) {
        // draw trace states
        for (int i = topIndex; i < items.length; i++) {
            TimeGraphItem item = (TimeGraphItem) items[i];
            fTimeGraphProvider.drawItem(item, bounds, timeProvider, i, nameSpace, gc);
        }
    }

    /**
     * Draws the item
     * 
     * @param item the item to draw
     * @param bounds the container rectangle
     * @param i the item index
     * @param nameSpace the name space
     * @param gc
     */
    public void drawItem(TimeGraphItem item, Rectangle bounds, ITimeDataProvider timeProvider, int i, int nameSpace, GC gc) {
        long time0 = timeProvider.getTime0();
        long time1 = timeProvider.getTime1();
        long endTime = timeProvider.getEndTime();
        long selectedTime = timeProvider.getSelectedTime();

        Rectangle nameRect = getNameRect(bounds, i, nameSpace);
        if (nameRect.y >= bounds.y + bounds.height) {
            return;
        }

        if (item._trace.getTimeEventsIterator() == null) {
            Rectangle statesRect = getStatesRect(bounds, i, nameSpace);
            nameRect.width += statesRect.width;
            drawName(item, nameRect, gc);
        } else {
            drawName(item, nameRect, gc);
        }
        Rectangle rect = getStatesRect(bounds, i, nameSpace);
        if (rect.isEmpty())
            return;
        if (time1 <= time0) {
            gc.setBackground(_colors.getBkColor(false, false, false));
            gc.fillRectangle(rect);
            return;
        }

        // Initialize _rect1 to same values as enclosing rectangle rect
        Rectangle stateRect = Utils.clone(rect);
        boolean selected = item._selected;
        // K pixels per second
        double pixelsPerNanoSec = (rect.width <= RIGHT_MARGIN) ? 0 : (double) (rect.width - RIGHT_MARGIN) / (time1 - time0);

        boolean group = item._trace.getTimeEventsIterator() == null;

        if (group) {
            // gc.setBackground(_colors.getBkColorGroup(selected, _isInFocus));
            // gc.fillRectangle(rect);
        } else {
            ITimeGraphEntry trace = item._trace;

            double x0 = rect.x;
            long maxDuration = (timeProvider.getTimeSpace() == 0) ? Long.MAX_VALUE : 1 * (time1 - time0) / timeProvider.getTimeSpace();
            Iterator<ITimeEvent> iterator = trace.getTimeEventsIterator(time0, time1, maxDuration);
            // Drawing rectangle is smaller than reserved space
            stateRect.y += 3;
            stateRect.height -= 6;

            // Clean up to empty line to draw on top
            int xEnd = rect.x + rect.width;
            fillSpace(rect, gc, selected);
            if (iterator.hasNext()) {
                ITimeEvent currEvent = iterator.next();
                ITimeEvent nextEvent = null;
                long currEventTime = currEvent.getTime();
                long currEventDuration = currEvent.getDuration();
                // initial value
                long nextEventTime = currEventTime;
                // x0 - Points to the beginning of the event being drawn
                double step = (double) ((currEventTime - time0) * pixelsPerNanoSec);
                x0 = rect.x + step;
                // xEnd - Points to the end of the events rectangle
                double x1 = -1;
                double xNext = 0;

                // draw event states
                while (/* x0 <= xEnd && */null != currEvent) {
                    boolean stopped = false;// currEvent instanceof
                    // refresh current event duration as the loop moves
                    currEventDuration = currEvent.getDuration();
                    // TsfTmTraceDeadEvent;
                    if (iterator.hasNext()) {
                        nextEvent = iterator.next();
                        nextEventTime = nextEvent.getTime();
                    } else if (stopped) {
                        nextEvent = null;
                        nextEventTime = time1;
                    } else {
                        nextEvent = null;
                        nextEventTime = endTime;
                    }

                    // Calculate position to next event
                    xNext = rect.x + (double) ((nextEventTime - time0) * pixelsPerNanoSec);

                    // Calculate end position of current event
                    if (currEventDuration < 0) {
                        x1 = rect.x + (double) ((nextEventTime - time0) * pixelsPerNanoSec);
                    } else if (currEventDuration == 0) {
                        x1 = x0;
                    } else {
                        x1 = x0 + (double) ((currEventDuration) * pixelsPerNanoSec);
                    }

                    // If event end position x1 further away than start position
                    // of next event, cut width till next event
                    if (currEventDuration != 0) {
                        x1 = x1 > xNext ? xNext : x1;
                    }
                    // if event end boundary is within time range
                    if (x1 >= rect.x && x0 <= xEnd) {
                        if (currEventDuration != 0) {
                            x0 = (double) (x0 >= rect.x ? x0 : rect.x);
                            stateRect.width = (int) ((x1 <= xEnd ? x1 : xEnd) - x0);
                        } else {
                            stateRect.width = 1;
                        }
                        stateRect.width = Math.max(getMinimumItemWidth(), stateRect.width);
                        stateRect.x = (int) x0;
                        boolean timeSelected = currEventTime <= selectedTime && selectedTime < nextEventTime;
                        fTimeGraphProvider.drawState(_colors, currEvent, stateRect, gc, selected, false, timeSelected);
                        // Advance rectangle to next start position and Fill
                        // with space until next event
                        stateRect.x += stateRect.width;
                        x0 = stateRect.x;
                    }

                    // lastEvent = currEvent;
                    currEvent = nextEvent;
                    currEventTime = nextEventTime;
                    // Move x0 to the beginning of next event
                    x0 = rect.x + (double) ((nextEventTime - time0) * pixelsPerNanoSec);
                }
            }
        }
    }

    void drawName(TimeGraphItem item, Rectangle bounds, GC gc) {
        boolean group = item._trace.getTimeEventsIterator() == null;
        if (group) {
            gc.setBackground(_colors.getBkColorGroup(item._selected, _isInFocus));
            gc.fillRectangle(bounds);
            if (item._selected && _isInFocus) {
                gc.setForeground(_colors.getBkColor(item._selected, _isInFocus, false));
                gc.drawRectangle(bounds.x, bounds.y, bounds.width - 1, bounds.height - 1);
            }
        } else {
            gc.setBackground(_colors.getBkColor(item._selected, _isInFocus, true));
            gc.setForeground(_colors.getFgColor(item._selected, _isInFocus));
            gc.fillRectangle(bounds);
        }

        // No name to be drawn
        if (_timeProvider.getNameSpace() == 0) {
            return;
        }

        int leftMargin = MARGIN + item.level * EXPAND_SIZE;
        if (item._hasChildren) {
            gc.setForeground(_colors.getFgColorGroup(false, false));
            gc.setBackground(_colors.getBkColor(false, false, false));
            Rectangle rect = Utils.clone(bounds);
            rect.x += leftMargin;
            rect.y += (bounds.height - EXPAND_SIZE) / 2;
            rect.width = EXPAND_SIZE;
            rect.height = EXPAND_SIZE;
            gc.fillRectangle(rect);
            gc.drawRectangle(rect.x, rect.y, rect.width - 1, rect.height - 1);
            int midy = rect.y + rect.height / 2;
            gc.drawLine(rect.x + 2, midy, rect.x + rect.width - 3, midy);
            if (!item._expanded) {
                int midx = rect.x + rect.width / 2;
                gc.drawLine(midx, rect.y + 2, midx, rect.y + rect.height - 3);
            }
        }
        leftMargin += EXPAND_SIZE + MARGIN;

        Image img = fTimeGraphProvider.getItemImage(item._trace);
        if (img != null) {
            // draw icon
            int imgHeight = img.getImageData().height;
            int imgWidth = img.getImageData().width;
            int x = leftMargin;
            int y = bounds.y + (bounds.height - imgHeight) / 2;
            gc.drawImage(img, x, y);
            leftMargin += imgWidth + MARGIN;
        }
        String name = item._name;
        Point size = gc.stringExtent(name);
        if (_idealNameSpace < leftMargin + size.x + MARGIN) {
            _idealNameSpace = leftMargin + size.x + MARGIN;
        }
        if (!group) {
            // cut long string with "..."
            int width = bounds.width - leftMargin;
            int cuts = 0;
            while (size.x > width && name.length() > 1) {
                cuts++;
                name = name.substring(0, name.length() - 1);
                size = gc.stringExtent(name + "..."); //$NON-NLS-1$
            }
            if (cuts > 0) {
                name += "..."; //$NON-NLS-1$
            }
        }
        Rectangle rect = Utils.clone(bounds);
        rect.x += leftMargin;
        rect.width -= leftMargin;
        // draw text
        if (rect.width > 0) {
            rect.y += 2;
            int textWidth = Utils.drawText(gc, name, rect, true);
            leftMargin += textWidth + MARGIN;
            rect.y -= 2;

            if (!group) {
                // draw middle line
                int x = bounds.x + leftMargin;
                int width = bounds.width - x;
                int midy = bounds.y + bounds.height / 2;
                gc.setForeground(_colors.getColor(TimeGraphColorScheme.MID_LINE));
                gc.drawLine(x, midy, x + width, midy);
            }
        }
    }

    public void drawState(TimeGraphColorScheme colors, int colorIdx,
            Rectangle rect, GC gc, boolean selected, boolean rectBound,
            boolean timeSelected) {

        boolean visible = rect.width == 0 ? false : true;
        int colorIdx1 = colorIdx;

        timeSelected = timeSelected && selected;
        if (timeSelected) {
            colorIdx1 = colorIdx + TimeGraphColorScheme.STATES_SEL0 - TimeGraphColorScheme.STATES0;
        }

        if (visible) {
            // fill all rect area
            if (rect.isEmpty())
                return;

            gc.setBackground(colors.getColor(colorIdx1));
            gc.fillRectangle(rect);
            colorIdx1 = colorIdx + TimeGraphColorScheme.STATES_BORDER0 - TimeGraphColorScheme.STATES0;
            gc.setForeground(colors.getColor(colorIdx1));

            // draw bounds
            if (!timeSelected) {
                if (rectBound && rect.width >= 3) {
                    gc.drawRectangle(rect.x, rect.y, rect.width - 1, rect.height - 1);
                } else {
                    // Draw the top and bottom borders i.e. no side borders
                    // top
                    gc.drawLine(rect.x, rect.y, rect.x + rect.width - 1, rect.y);
                    // bottom
                    gc.drawLine(rect.x, rect.y + rect.height - 1, rect.x + rect.width - 1, rect.y + rect.height - 1);
                }
            }
        } else {
            // selected rectangle area is not visible but can be represented
            // with a broken vertical line of specified width.
            int width = 2;
            rect.width = width;
            // check if height is greater than zero.
            if (rect.isEmpty())
                return;
            // colorIdx1 = TraceColorScheme.BLACK;
            gc.setForeground(colors.getColor(colorIdx));
            int s = gc.getLineStyle();
            int w = gc.getLineWidth();
            gc.setLineStyle(SWT.LINE_DOT);
            gc.setLineWidth(width);
            // Trace.debug("Reactangle not visible, drawing vertical line with: "
            // + rect.x + "," + rect.y + "," + rect.x + "," + rect.y
            // + rect.height);
            gc.drawLine(rect.x, rect.y, rect.x, rect.y + rect.height);
            gc.setLineStyle(s);
            gc.setLineWidth(w);
        }
    }

    private void fillSpace(Rectangle rect, GC gc, boolean selected) {
        gc.setBackground(_colors.getBkColor(selected, _isInFocus, false));
        gc.fillRectangle(rect);
        // draw middle line
        gc.setForeground(_colors.getColor(TimeGraphColorScheme.MID_LINE));
        int midy = rect.y + rect.height / 2;
        gc.drawLine(rect.x, midy, rect.x + rect.width, midy);
    }

    @Override
    public void keyTraversed(TraverseEvent e) {
        if ((e.detail == SWT.TRAVERSE_TAB_NEXT) || (e.detail == SWT.TRAVERSE_TAB_PREVIOUS))
            e.doit = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int idx = -1;
        if (_data._expandedItems.length == 0) {
            return;
        }
        if (SWT.HOME == e.keyCode) {
            idx = 0;
        } else if (SWT.END == e.keyCode) {
            idx = _data._expandedItems.length - 1;
        } else if (SWT.ARROW_DOWN == e.keyCode) {
            idx = getSelectedIndex();
            if (idx < 0)
                idx = 0;
            else if (idx < _data._expandedItems.length - 1)
                idx++;
        } else if (SWT.ARROW_UP == e.keyCode) {
            idx = getSelectedIndex();
            if (idx < 0)
                idx = 0;
            else if (idx > 0)
                idx--;
        } else if (SWT.ARROW_LEFT == e.keyCode) {
            selectPrevEvent();
        } else if (SWT.ARROW_RIGHT == e.keyCode) {
            selectNextEvent();
        } else if (SWT.PAGE_DOWN == e.keyCode) {
            int page = countPerPage();
            idx = getSelectedIndex();
            if (idx < 0)
                idx = 0;
            idx += page;
            if (idx >= _data._expandedItems.length)
                idx = _data._expandedItems.length - 1;
        } else if (SWT.PAGE_UP == e.keyCode) {
            int page = countPerPage();
            idx = getSelectedIndex();
            if (idx < 0)
                idx = 0;
            idx -= page;
            if (idx < 0)
                idx = 0;
        } else if (SWT.CR == e.keyCode) {
            idx = getSelectedIndex();
            if (idx >= 0) {
                if (_data._expandedItems[idx]._hasChildren) {
                    toggle(idx);
                } else {
                    fireDefaultSelection();
                }
            }
            idx = -1;
        }
        if (idx >= 0) {
            selectItem(idx, false);
            fireSelectionChanged();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
        _isInFocus = true;
        redraw();
    }

    @Override
    public void focusLost(FocusEvent e) {
        _isInFocus = false;
        if (DRAG_NONE != _dragState) {
            setCapture(false);
            _dragState = DRAG_NONE;
        }
        redraw();
    }

    public boolean isInFocus() {
        return _isInFocus;
    }

    @Override
    public void mouseMove(MouseEvent e) {
        if (null == _timeProvider)
            return;
        Point size = getCtrlSize();
        if (DRAG_TRACE_ITEM == _dragState) {
            int nameWidth = _timeProvider.getNameSpace();
            int x = e.x - nameWidth;
            if (x > 0 && size.x > nameWidth && _dragX != x) {
                _dragX = x;
                double pixelsPerNanoSec = (size.x - nameWidth <= RIGHT_MARGIN) ? 0 : (double) (size.x - nameWidth - RIGHT_MARGIN) / (_time1bak - _time0bak);
                long timeDelta = (long) ((pixelsPerNanoSec == 0) ? 0 : ((_dragX - _dragX0) / pixelsPerNanoSec));
                long time1 = _time1bak - timeDelta;
                long maxTime = _timeProvider.getMaxTime();
                if (time1 > maxTime)
                    time1 = maxTime;
                long time0 = time1 - (_time1bak - _time0bak);
                if (time0 < _timeProvider.getMinTime()) {
                    time0 = _timeProvider.getMinTime();
                    time1 = time0 + (_time1bak - _time0bak);
                }
                _timeProvider.setStartFinishTime(time0, time1);
            }
        } else if (DRAG_SPLIT_LINE == _dragState) {
            _dragX = e.x;
            _timeProvider.setNameSpace(e.x);
        } else if (DRAG_NONE == _dragState) {
            boolean mouseOverSplitLine = isOverSplitLine(e.x);
            if (_mouseOverSplitLine != mouseOverSplitLine) {
                redraw();
            }
            _mouseOverSplitLine = mouseOverSplitLine;
            // Make sure any time changes are notified to the application e.g.
            // getting back from the horizontal scroll bar or zoomed using the
            // mouse wheel
            _timeProvider.notifyStartFinishTime();
        }
        updateCursor(e.x, e.y);
    }

    @Override
    public void mouseDoubleClick(MouseEvent e) {
        if (null == _timeProvider)
            return;
        if (1 == e.button) {
            if (isOverSplitLine(e.x) && _timeProvider.getNameSpace() != 0) {
                _timeProvider.setNameSpace(_idealNameSpace);
                boolean mouseOverSplitLine = isOverSplitLine(e.x);
                if (_mouseOverSplitLine != mouseOverSplitLine) {
                    redraw();
                }
                _mouseOverSplitLine = mouseOverSplitLine;
                return;
            }
            int idx = getItemIndexAtY(e.y);
            if (idx >= 0) {
                selectItem(idx, false);
                fireDefaultSelection();
            }
        }
    }

    /**
     * <p>
     * If the x, y position is over the vertical split line (name to time
     * ranges), then change the cursor to a drag cursor to indicate the user the
     * possibility of resizing
     * </p>
     * 
     * @param x
     * @param y
     */
    void updateCursor(int x, int y) {
        // if Wait cursor not active, check for the need to change to a drag
        // cursor
        if (_isWaitCursor == false) {
            boolean isSplitLine = isOverSplitLine(x);
            // No dragcursor is name space is fixed to zero
            if (isSplitLine && !_isDragCursor3 && _timeProvider.getNameSpace() > 0) {
                setCursor(_dragCursor3);
                _isDragCursor3 = true;
            } else if (!isSplitLine && _isDragCursor3) {
                setCursor(null);
                _isDragCursor3 = false;
            }
        }
    }

    /**
     * Provide the possibilty to control the wait cursor externally e.g. data
     * requests in progress
     * 
     * @param waitInd
     */
    public void waitCursor(boolean waitInd) {
        // Update cursor as indicated
        if (waitInd) {
            setCursor(_WaitCursor);
            _isWaitCursor = true;
        } else {
            setCursor(null);
            _isWaitCursor = false;
        }

        // Get ready for next mouse move
        _isDragCursor3 = false;
    }

    @Override
    public void mouseDown(MouseEvent e) {
        if (null == _timeProvider)
            return;
        int idx;
        if (1 == e.button) {
            int nameSpace = _timeProvider.getNameSpace();
            if (nameSpace != 0) {
                if (isOverSplitLine(e.x)) {
                    _dragState = DRAG_SPLIT_LINE;
                    _dragX = _dragX0 = e.x;
                    _time0bak = _timeProvider.getTime0();
                    _time1bak = _timeProvider.getTime1();
                    redraw();
                    return;
                }
            }

            idx = getItemIndexAtY(e.y);
            if (idx >= 0) {
                TimeGraphItem item = _data._expandedItems[idx];
                if (item._hasChildren && e.x < nameSpace && e.x < MARGIN + (item.level + 1) * EXPAND_SIZE) {
                    toggle(idx);
                } else {
                    long hitTime = getTimeAtX(e.x);
                    if (hitTime >= 0) {
                        // _timeProvider.setSelectedTimeInt(hitTime, false);
                        setCapture(true);
                        _dragState = DRAG_TRACE_ITEM;
                        _dragX = _dragX0 = e.x - nameSpace;
                        _time0bak = _timeProvider.getTime0();
                        _time1bak = _timeProvider.getTime1();
                    }
                }
                selectItem(idx, false);
                fireSelectionChanged();
            } else {
                selectItem(idx, false); // clear selection
                redraw();
                fireSelectionChanged();
            }
        }
    }

    @Override
    public void mouseUp(MouseEvent e) {
        if (DRAG_NONE != _dragState) {
            setCapture(false);
            if (DRAG_TRACE_ITEM == _dragState) {
                // Notify time provider to check the need for listener
                // notification
                _timeProvider.notifyStartFinishTime();
                if (_dragX == _dragX0) { // click without drag
                    long time = getTimeAtX(e.x);
                    _timeProvider.setSelectedTimeInt(time, false);
                }
            } else if (DRAG_SPLIT_LINE == _dragState) {
                redraw();
            }
            _dragState = DRAG_NONE;
        }
    }

    @Override
    public void controlMoved(ControlEvent e) {
    }

    @Override
    public void controlResized(ControlEvent e) {
        adjustScrolls();
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        if (e.widget == getVerticalBar()) {
            _topIndex = getVerticalBar().getSelection();
            if (_topIndex < 0)
                _topIndex = 0;
            redraw();
        } else if (e.widget == getHorizontalBar() && null != _timeProvider) {
            int start = getHorizontalBar().getSelection();
            long time0 = _timeProvider.getTime0();
            long time1 = _timeProvider.getTime1();
            long timeMin = _timeProvider.getMinTime();
            long timeMax = _timeProvider.getMaxTime();
            long delta = timeMax - timeMin;

            long range = time1 - time0;
            // _timeRangeFixed = true;
            time0 = timeMin + Math.round(delta * ((double) start / H_SCROLLBAR_MAX));
            time1 = time0 + range;

            // TODO: Follow-up with Bug 310310
            // In Linux SWT.DRAG is the only value received
            // https://bugs.eclipse.org/bugs/show_bug.cgi?id=310310
            if (e.detail == SWT.DRAG) {
                _timeProvider.setStartFinishTime(time0, time1);
            } else {
                _timeProvider.setStartFinishTimeNotify(time0, time1);
            }
        }
    }

    @Override
    public void mouseEnter(MouseEvent e) {
        if (mouseScrollFilterListener == null) {
            mouseScrollFilterListener = new Listener() {
                // This filter is used to prevent scrolling of the view when the
                // mouse wheel is used to zoom
                @Override
                public void handleEvent(Event event) {
                    event.doit = false;
                }
            };
            getDisplay().addFilter(SWT.MouseWheel, mouseScrollFilterListener);
        }
    }

    @Override
    public void mouseExit(MouseEvent e) {
        if (mouseScrollFilterListener != null) {
            getDisplay().removeFilter(SWT.MouseWheel, mouseScrollFilterListener);
            mouseScrollFilterListener = null;
        }
        if (_mouseOverSplitLine) {
            _mouseOverSplitLine = false;
            redraw();
        }
    }

    @Override
    public void mouseHover(MouseEvent e) {
    }

    @Override
    public void mouseScrolled(MouseEvent e) {
        if ((mouseScrollFilterListener == null) || _dragState != DRAG_NONE) {
            return;
        }
        if (e.x < _timeProvider.getNameSpace()) {
            setTopIndex(getTopIndex() - e.count);
        } else {
            if (e.count > 0) {
                zoom(true);
            } else if (e.count < 0) {
                zoom(false);
            }
        }
    }

    public boolean isVisibleVerticalScroll() {
        return _visibleVerticalScroll;
    }

    @Override
    public int getBorderWidth() {
        return _borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this._borderWidth = borderWidth;
    }

    public int getHeaderHeight() {
        return _headerHeight;
    }

    public void setHeaderHeight(int headerHeight) {
        this._headerHeight = headerHeight;
    }

    public int getItemHeight() {
        return _itemHeight;
    }

    public void setItemHeight(int rowHeight) {
        this._itemHeight = rowHeight;
    }

    public void setMinimumItemWidth(int width) {
        this._minimumItemWidth = width;
    }

    public int getMinimumItemWidth() {
        return _minimumItemWidth;
    }

    public Vector<ITimeGraphEntry> getFilteredOut() {
        return _data.getFilteredOut();
    }

    // @Override
    @Override
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        if (listener != null) {
            if (!_selectionChangedListeners.contains(listener)) {
                _selectionChangedListeners.add(listener);
            }
        }
    }

    // @Override
    @Override
    public void removeSelectionChangedListener(ISelectionChangedListener listener) {
        if (listener != null) {
            _selectionChangedListeners.remove(listener);
        }
    }

    // @Override
    @Override
    public void setSelection(ISelection selection) {
        if (selection instanceof TimeGraphSelection) {
            TimeGraphSelection sel = (TimeGraphSelection) selection;
            Object ob = sel.getFirstElement();
            if (ob instanceof ITimeGraphEntry) {
                ITimeGraphEntry trace = (ITimeGraphEntry) ob;
                selectItem(trace, false);
            }
        }

    }

}

class ItemData {
    public TimeGraphItem[] _expandedItems = new TimeGraphItem[0];
    public TimeGraphItem[] _items = new TimeGraphItem[0];
    private ITimeGraphEntry _traces[] = new ITimeGraphEntry[0];
    private boolean traceFilter[] = new boolean[0];
    private Vector<ITimeGraphEntry> filteredOut = new Vector<ITimeGraphEntry>();

    public ItemData() {
    }

    TimeGraphItem findItem(ITimeGraphEntry entry) {
        if (entry == null)
            return null;

        for (int i = 0; i < _items.length; i++) {
            TimeGraphItem item = _items[i];
            if (item._trace == entry) {
                return item;
            }
        }

        return null;
    }

    int findItemIndex(ITimeGraphEntry trace) {
        if (trace == null)
            return -1;

        for (int i = 0; i < _expandedItems.length; i++) {
            TimeGraphItem item = _expandedItems[i];
            if (item._trace == trace) {
                return i;
            }
        }

        return -1;
    }

    public void refreshData() {
        List<TimeGraphItem> itemList = new ArrayList<TimeGraphItem>();
        filteredOut.clear();
        for (int i = 0; i < _traces.length; i++) {
            ITimeGraphEntry entry = _traces[i];
            refreshData(itemList, null, 0, entry);
        }
        _items = itemList.toArray(new TimeGraphItem[0]);
        updateExpandedItems();
    }

    private void refreshData(List<TimeGraphItem> itemList, TimeGraphItem parent, int level, ITimeGraphEntry entry) {
        TimeGraphItem item = new TimeGraphItem(entry, entry.getName(), level);
        if (parent != null) {
            parent.children.add(item);
        }
        itemList.add(item);
        if (entry.hasChildren()) {
            item._expanded = true;
            item._hasChildren = true;
            for (ITimeGraphEntry child : entry.getChildren()) {
                refreshData(itemList, item, level + 1, child);
            }
        }
    }

    public void updateExpandedItems() {
        List<TimeGraphItem> expandedItemList = new ArrayList<TimeGraphItem>();
        for (int i = 0; i < _traces.length; i++) {
            ITimeGraphEntry entry = _traces[i];
            TimeGraphItem item = findItem(entry);
            refreshExpanded(expandedItemList, item);
        }
        _expandedItems = expandedItemList.toArray(new TimeGraphItem[0]);
    }

    private void refreshExpanded(List<TimeGraphItem> expandedItemList, TimeGraphItem item) {
        expandedItemList.add(item);
        if (item._hasChildren && item._expanded) {
            for (TimeGraphItem child : item.children) {
                refreshExpanded(expandedItemList, child);
            }
        }
    }

    public void expandItem(int idx) {
        if (idx < 0 || idx >= _expandedItems.length)
            return;
        TimeGraphItem item = (TimeGraphItem) _expandedItems[idx];
        if (item._hasChildren && !item._expanded) {
            item._expanded = true;
            updateExpandedItems();
        }
    }

    public void refreshData(ITimeGraphEntry traces[]) {
        if (traces == null || traces.length == 0) {
            traceFilter = null;
        } else if (traceFilter == null || traces.length != traceFilter.length) {
            traceFilter = new boolean[traces.length];
            java.util.Arrays.fill(traceFilter, true);
        }

        _traces = traces;
        refreshData();
    }

    public Object[] getTraces() {
        return _traces;
    }

    public boolean[] getTraceFilter() {
        return traceFilter;
    }

    public Vector<ITimeGraphEntry> getFilteredOut() {
        return filteredOut;
    }
}