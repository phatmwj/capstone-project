package winwin.customer.app.ui.calendarview;

import static winwin.customer.app.ui.calendarview.WeekViewUtil.isSameDay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import lombok.Setter;

public class WeekView extends View {

    @Deprecated
    public static final int LENGTH_SHORT = 1;
    @Deprecated
    public static final int LENGTH_LONG = 2;
    private final Context mContext;
    int forward = 1080;
    private Paint shadowPaint;
    private boolean stop;

    private Paint mTimeTextPaint;
    private float mTimeTextWidth;
    private float mTimeTextHeight;
    private Paint mHeaderTextPaint;
    private float mHeaderTextHeight;
    @Setter
    private View shadow;
    private Paint jHeaderTextPaint;
    private Paint jtodayHeaderTextPaint;
    private float jHeaderTextHeight;
    private float mHeaderHeight;
    private GestureDetectorCompat mGestureDetector;
    private OverScroller mScroller;
    private PointF mCurrentOrigin = new PointF(0f, 0f);
//    private Direction mCurrentScrollDirection = Direction.NONE;
    private Paint mHeaderBackgroundPaint;
    private float mWidthPerDay;
    private Paint mDayBackgroundPaint;
    private Paint mHourSeparatorPaint;
    private float mHeaderMarginBottom;
    private Paint mTodayBackgroundPaint;
    private Paint jheaderEventTextpaint;
    private int jheaderEventheight;
    private Paint mFutureBackgroundPaint;
    private Paint mPastBackgroundPaint;
    private Paint mFutureWeekendBackgroundPaint;
    private Paint mPastWeekendBackgroundPaint;
    private Paint mNowLinePaint;
    private Paint jNowbackPaint;
    private Paint mTodayHeaderTextPaint;
    private Paint mEventBackgroundPaint;
    private float mHeaderColumnWidth;
    private List<EventRect> mEventRects;
    private List<? extends WeekViewEvent> mPreviousPeriodEvents;
    private List<? extends WeekViewEvent> mCurrentPeriodEvents;
    private List<? extends WeekViewEvent> mNextPeriodEvents;
    private TextPaint mEventTextPaint;
    private Paint mHeaderColumnBackgroundPaint;
    private int mFetchedPeriod = -1; // the middle period the calendar has fetched.
    private boolean mRefreshEvents = false;
//    private Direction mCurrentFlingDirection = Direction.NONE;
    private ScaleGestureDetector mScaleDetector;
    private boolean mIsZooming;
    private Calendar mFirstVisibleDay;
    private Calendar mLastVisibleDay;
    private boolean mShowFirstDayOfWeekFirst = false;
    private int mDefaultEventColor;
    private int mMinimumFlingVelocity = 0;
    private int mScaledTouchSlop = 0;
    // Attributes and their default values.
    private int mHourHeight = 50;
    private int mNewHourHeight = -1;
    private int mMinHourHeight = 0; //no minimum specified (will be dynamic, based on screen)
    private int mEffectiveMinHourHeight = mMinHourHeight; //compensates for the fact that you can't keep zooming out.
    private int mMaxHourHeight = 250;
    private int mColumnGap = 10;
    private int mFirstDayOfWeek = Calendar.MONDAY;
    private int mTextSize = 12;
    private int mHeaderColumnPadding = 10;
    private int mHeaderColumnTextColor = Color.BLACK;
    private int mNumberOfVisibleDays = 3;
    private int mHeaderRowPadding = 10;
    private int mHeaderRowBackgroundColor = Color.WHITE;
    private int mDayBackgroundColor = Color.rgb(245, 245, 245);
    private int mPastBackgroundColor = Color.rgb(227, 227, 227);
    private int mFutureBackgroundColor = Color.rgb(245, 245, 245);
    private int mPastWeekendBackgroundColor = 0;
    private int mFutureWeekendBackgroundColor = 0;
    private int mNowLineColor = Color.rgb(102, 102, 102);
    private int mNowLineThickness = 5;
    private int mHourSeparatorColor = Color.rgb(230, 230, 230);
    private int mTodayBackgroundColor = Color.rgb(239, 247, 254);
    private int mHourSeparatorHeight = 2;
    private int mTodayHeaderTextColor = Color.rgb(39, 137, 228);
    private int mEventTextSize = 12;
    private int mEventTextColor = Color.BLACK;
    private int mEventPadding = 8;
    private int mHeaderColumnBackgroundColor = Color.WHITE;
    private boolean mIsFirstDraw = true;
    private boolean mAreDimensionsInvalid = true;
    @Deprecated
    private int mDayNameLength = LENGTH_LONG;
    private int mOverlappingEventGap = 0;
    private int mEventMarginVertical = 0;
    private float mXScrollingSpeed = 1f;
    private Calendar mScrollToDay = null;
    private double mScrollToHour = -1;
    private int mEventCornerRadius = 0;
    private boolean mShowDistinctWeekendColor = false;
    private boolean mShowNowLine = false;
    private boolean mShowDistinctPastFutureColor = false;
    private boolean mHorizontalFlingEnabled = true;
    private boolean mVerticalFlingEnabled = true;
    private int mAllDayEventHeight = 100;
    private int mScrollDuration = 150;
    private float weekx;
    // Listeners.
//    private EventClickListener mEventClickListener;
//    private EventLongPressListener mEventLongPressListener;
//    private WeekViewLoader mWeekViewLoader;
//    private EmptyViewClickListener mEmptyViewClickListener;
//    private EmptyViewLongPressListener mEmptyViewLongPressListener;
    public Calendar getFirstVisibleDay() {
        return mFirstVisibleDay;
    }

    private void initTextTimeWidth() {
        mTimeTextWidth = 0;
        for (int i = 0; i < 24; i++) {
            // Measure time string and get max width.
            String time = getDateTimeInterpreter().interpretTime(i);
            if (time == null)
                throw new IllegalStateException("A DateTimeInterpreter must not return null time");
            mTimeTextWidth = Math.max(mTimeTextWidth, mTimeTextPaint.measureText(time));
        }
    }

    private DateTimeInterpreter mDateTimeInterpreter;
    public DateTimeInterpreter getDateTimeInterpreter() {
        if (mDateTimeInterpreter == null) {
            mDateTimeInterpreter = new DateTimeInterpreter() {
                @Override
                public String interpretday(Calendar date) {
                    try {
                        int flags = DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NO_YEAR | DateUtils.FORMAT_NUMERIC_DATE;
                        String localizedDate = DateUtils.formatDateTime(getContext(), date.getTime().getTime(), flags);
                        SimpleDateFormat sdf = mDayNameLength == LENGTH_SHORT ? new SimpleDateFormat("EEEEE", Locale.getDefault()) : new SimpleDateFormat("EEE", Locale.getDefault());
                        return String.format("%s %s", sdf.format(date.getTime()).toUpperCase(), localizedDate);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "";
                    }
                }

                @Override
                public String interpretDate(Calendar date) {
                    int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);


                    return dayOfMonth + "";
                }

                @Override
                public String interpretTime(int hour) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, 0);

                    try {
                        SimpleDateFormat sdf = DateFormat.is24HourFormat(getContext()) ? new SimpleDateFormat("HH:mm", Locale.getDefault()) : new SimpleDateFormat("hh a", Locale.getDefault());
                        return sdf.format(calendar.getTime());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "";
                    }
                }
            };
        }
        return mDateTimeInterpreter;
    }

    private void init(){
        // Measure settings for time column.
        mTimeTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTimeTextPaint.setTextAlign(Paint.Align.RIGHT);
        mTimeTextPaint.setTextSize(mTextSize);
        mTimeTextPaint.setColor(mHeaderColumnTextColor);
        Rect rect = new Rect();
        mTimeTextPaint.getTextBounds("00 PM", 0, "00 PM".length(), rect);
        mTimeTextHeight = rect.height();
        mHeaderMarginBottom = mTimeTextHeight / 2;
        initTextTimeWidth();
    }

    public WeekView(Context context) {
        super(context);
        this.mContext = context;
    }

    public WeekView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public WeekView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public WeekView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawHeaderRowAndEvents(canvas);
    }

    private void drawHeaderRowAndEvents(Canvas canvas){
        // Calculate the available width for each day.
        mHeaderColumnWidth = mTimeTextWidth + mHeaderColumnPadding * 2;
        mWidthPerDay = getWidth() - mHeaderColumnWidth - mColumnGap * (mNumberOfVisibleDays - 1);
        if (mNumberOfVisibleDays == 1) mWidthPerDay = getWidth();//mWidthPerDay/mNumberOfVisibleDays
        else mWidthPerDay = mWidthPerDay / mNumberOfVisibleDays;

        calculateHeaderHeight();
    }

    public void calculateHeaderHeight() {
        //Make sure the header is the right size (depends on AllDay events)
        boolean containsAllDayEvent = false;
        int noofevent = 1;
        if (mEventRects != null && mEventRects.size() > 0) {
            for (int dayNumber = 0; dayNumber < mNumberOfVisibleDays; dayNumber++) {
                Calendar day = (Calendar) getFirstVisibleDay().clone();
                day.add(Calendar.DATE, dayNumber);
                for (int i = 0; i < mEventRects.size(); i++) {
                    if (isSameDay(mEventRects.get(i).event.getStartTime(), day) && mEventRects.get(i).event.isAllDay()) {
                        if (mEventRects.get(i).noofevent > noofevent)
                            noofevent = mEventRects.get(i).noofevent;
                        containsAllDayEvent = true;
                        break;
                    }
                }
//                if(containsAllDayEvent){
//                    break;
//                }
            }
        }
        //((noofevent-1)*5) where is 5 padding between event
        if (mNumberOfVisibleDays == 1) {
            if (containsAllDayEvent) {
                float without = mHeaderTextHeight + jHeaderTextHeight + mHeaderMarginBottom + 70 + mHeaderRowPadding / 3.0f;
                float with = (mAllDayEventHeight * noofevent) + ((noofevent - 1) * 5) + mHeaderMarginBottom;
                mHeaderHeight = Math.max(without, with);

                shadow.setY(mHeaderHeight - 70 + mHeaderRowPadding * 3);

            } else {
                float without = mHeaderTextHeight + jHeaderTextHeight + mHeaderMarginBottom + 70 + mHeaderRowPadding / 3.0f;

                mHeaderHeight = without;
                shadow.setY(mHeaderHeight - 70 + mHeaderRowPadding * 3);
            }


        } else {
            if (containsAllDayEvent) {
                mHeaderHeight = mHeaderTextHeight + jHeaderTextHeight + ((mAllDayEventHeight * noofevent) + ((noofevent - 1) * 5) + mHeaderMarginBottom) + 70;
                shadow.setY(mHeaderHeight - 70 + mHeaderRowPadding * 3);
            } else {
                mHeaderHeight = mHeaderTextHeight + jHeaderTextHeight + mHeaderMarginBottom + 70;
                shadow.setY(mHeaderHeight - 70 + mHeaderRowPadding * 3);
            }
        }


    }
}
