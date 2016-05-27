package com.mark.iosframe.UserInterface.NavBar;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.mark.iosframe.R;
import com.mark.iosframe.Utils.XScreenUtils;

import java.util.ArrayList;

/**
 * Class description : This class is used to draw the layout and this acts like a bridge between
 * library and app, all details can be modified via this class.
 *
 * @author ashokvarma
 * @version 1.0
 * @see FrameLayout
 * @see <a href="https://www.google.com/design/spec/components/bottom-navigation.html">Google Bottom Navigation Component</a>
 * @since 19 Mar 2016
 */

public class BottomNavigationBar extends FrameLayout {

    public static final int MODE_DEFAULT = 0;

    private int mMode = MODE_DEFAULT;
 
    private static final int MIN_SIZE = 3;
 
    ArrayList<BottomNavigationItem> mBottomNavigationItems = new ArrayList<>();
    ArrayList<BottomNavigationTab> mBottomNavigationTabs = new ArrayList<>();

    private static final int DEFAULT_SELECTED_POSITION = -1;
    private int mSelectedPosition = DEFAULT_SELECTED_POSITION;
    private int mFirstSelectedPosition = 0;
    private OnTabSelectedListener mTabSelectedListener;

    private int mActiveColor;
    private int mInActiveColor;
 
 
    private FrameLayout mContainer;
    private LinearLayout mTabContainer;

 
    private float mElevation;
    private int mBackgroundColor;
    private FrameLayout mBackgroundOverlay;

    ///////////////////////////////////////////////////////////////////////////
    // View Default Constructors and Methods
    ///////////////////////////////////////////////////////////////////////////

    public BottomNavigationBar(Context context) {
        this(context, null);
    }

    public BottomNavigationBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttrs(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomNavigationBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttrs(context, attrs);
        init();
    }

    /**
     * This method initiates the bottomNavigationBar properties,
     * Tries to get them form XML if not preset sets them to their default values.
     *
     * @param context context of the bottomNavigationBar
     * @param attrs   attributes mentioned in the layout XML by user
     */
    private void parseAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BottomNavigationBar, 0, 0);

            mActiveColor = typedArray.getColor(R.styleable.BottomNavigationBar_activeColor, XScreenUtils.fetchContextColor(context, R.attr.colorAccent));
            mInActiveColor = typedArray.getColor(R.styleable.BottomNavigationBar_inactiveColor, Color.LTGRAY);
            mBackgroundColor = typedArray.getColor(R.styleable.BottomNavigationBar_barBackgroundColor, Color.WHITE);
            mElevation = typedArray.getDimension(R.styleable.BottomNavigationBar_elevation, getResources().getDimension(R.dimen.bottom_navigation_elevation));



            switch (typedArray.getInt(R.styleable.BottomNavigationBar_mode, MODE_DEFAULT)) {

                case MODE_DEFAULT:
                    mMode = MODE_DEFAULT;
                    break;
                default:
                    mMode = MODE_DEFAULT;
                    break;
            }



            typedArray.recycle();
        } else {
            mActiveColor = XScreenUtils.fetchContextColor(context, R.attr.colorAccent);
            mInActiveColor = Color.LTGRAY;
            mBackgroundColor = Color.WHITE;
            mElevation = getResources().getDimension(R.dimen.bottom_navigation_elevation);
        }
    }

    /**
     * This method initiates the bottomNavigationBar and handles layout related values
     */
    private void init() {

        setLayoutParams(new ViewGroup.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)));

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View parentView = inflater.inflate(R.layout.bottom_navigation_bar_container, this, true);
        mBackgroundOverlay = (FrameLayout) parentView.findViewById(R.id.bottom_navigation_bar_overLay);
        mContainer = (FrameLayout) parentView.findViewById(R.id.bottom_navigation_bar_container);
        mTabContainer = (LinearLayout) parentView.findViewById(R.id.bottom_navigation_bar_item_container);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setOutlineProvider(ViewOutlineProvider.BOUNDS);
        } else {

        }

        ViewCompat.setElevation(this, mElevation);
        setClipToPadding(false);
    }

    /**
     * Used to add a new tab.
     *
     * @param item bottom navigation tab details
     * @return this, to allow builder pattern
     */
    public BottomNavigationBar addItem(BottomNavigationItem item) {
        mBottomNavigationItems.add(item);
        return this;
    }



    public BottomNavigationBar setActiveColor(@ColorRes int activeColor) {
        this.mActiveColor = getContext().getResources().getColor(activeColor);
        return this;
    }


    public BottomNavigationBar setActiveColor(String activeColorCode) {
        this.mActiveColor = Color.parseColor(activeColorCode);
        return this;
    }

    public BottomNavigationBar setInActiveColor(@ColorRes int inActiveColor) {
        this.mInActiveColor = getContext().getResources().getColor(inActiveColor);
        return this;
    }


    public BottomNavigationBar setInActiveColor(String inActiveColorCode) {
        this.mInActiveColor = Color.parseColor(inActiveColorCode);
        return this;
    }


    public BottomNavigationBar setBarBackgroundColor(@ColorRes int backgroundColor) {
        this.mBackgroundColor = getContext().getResources().getColor(backgroundColor);
        return this;
    }


    public BottomNavigationBar setBarBackgroundColor(String backgroundColorCode) {
        this.mBackgroundColor = Color.parseColor(backgroundColorCode);
        return this;
    }


    public BottomNavigationBar setFirstSelectedPosition(int firstSelectedPosition) {
        this.mFirstSelectedPosition = firstSelectedPosition;
        return this;
    }



    public void initialise() {
        if (!mBottomNavigationItems.isEmpty()) {
            mTabContainer.removeAllViews();

            mBackgroundOverlay.setBackgroundColor(mBackgroundColor);
            mContainer.setBackgroundColor(mBackgroundColor);
            int screenWidth = XScreenUtils.getScreenWidth(getContext());


        }
    }


    /**
     * @param tabSelectedListener callback listener for tabs
     * @return this, to allow builder pattern
     */
    public BottomNavigationBar setTabSelectedListener(OnTabSelectedListener tabSelectedListener) {
        this.mTabSelectedListener = tabSelectedListener;
        return this;
    }



    public void selectTab(int newPosition) {
        selectTab(newPosition, true);
    }

    /**
     * Should be called only after initialization of BottomBar(i.e after calling initialize method)
     *
     * @param newPosition  to select a tab after bottom navigation bar is initialised
     * @param callListener should this change call listener callbacks
     */
    public void selectTab(int newPosition, boolean callListener) {
        selectTabInternal(newPosition, false, callListener);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Internal Methods of the class
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Internal method to setup tabs
     *
     * @param bottomNavigationTab Tab item
     * @param currentItem         data structure for tab item
     * @param itemWidth           tab item in-active width
     * @param itemActiveWidth     tab item active width
     */
    private void setUpTab(BottomNavigationTab bottomNavigationTab, BottomNavigationItem currentItem, int itemWidth, int itemActiveWidth) {
        bottomNavigationTab.setInactiveWidth(itemWidth);
        bottomNavigationTab.setActiveWidth(itemActiveWidth);
        bottomNavigationTab.setPosition(mBottomNavigationItems.indexOf(currentItem));

        bottomNavigationTab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationTab bottomNavigationTabView = (BottomNavigationTab) v;
                selectTabInternal(bottomNavigationTabView.getPosition(), false, true);
            }
        });

        mBottomNavigationTabs.add(bottomNavigationTab);

        BottomNavigationHelper.bindTabWithData(currentItem, bottomNavigationTab, this);

        bottomNavigationTab.initialise(mBackgroundStyle == BACKGROUND_STYLE_STATIC);

        mTabContainer.addView(bottomNavigationTab);
    }

    /**
     * Internal Method to select a tab
     *
     * @param newPosition  to select a tab after bottom navigation bar is initialised
     * @param firstTab     if firstTab the no ripple animation will be done
     * @param callListener is listener callbacks enabled for this change
     */
    private void selectTabInternal(int newPosition, boolean firstTab, boolean callListener) {
        int oldPosition = mSelectedPosition;
        if (mSelectedPosition != newPosition) {
            if (mBackgroundStyle == BACKGROUND_STYLE_STATIC) {
                if (mSelectedPosition != -1)
                    mBottomNavigationTabs.get(mSelectedPosition).unSelect(true, mAnimationDuration);
                mBottomNavigationTabs.get(newPosition).select(true, mAnimationDuration);
            } else if (mBackgroundStyle == BACKGROUND_STYLE_RIPPLE) {
                if (mSelectedPosition != -1)
                    mBottomNavigationTabs.get(mSelectedPosition).unSelect(false, mAnimationDuration);
                mBottomNavigationTabs.get(newPosition).select(false, mAnimationDuration);

                final BottomNavigationTab clickedView = mBottomNavigationTabs.get(newPosition);
                if (firstTab) {
                    // Running a ripple on the opening app won't be good so on firstTab this won't run.
                    mContainer.setBackgroundColor(clickedView.getActiveColor());
                    mBackgroundOverlay.setVisibility(View.GONE);
                } else {
                    mBackgroundOverlay.post(new Runnable() {
                        @Override
                        public void run() {
//                            try {
                            BottomNavigationHelper.setBackgroundWithRipple(clickedView, mContainer, mBackgroundOverlay, clickedView.getActiveColor(), mRippleAnimationDuration);
//                            } catch (Exception e) {
//                                mContainer.setBackgroundColor(clickedView.getActiveColor());
//                                mBackgroundOverlay.setVisibility(View.GONE);
//                            }
                        }
                    });
                }
            }
            mSelectedPosition = newPosition;
        }

        if (callListener) {
            sendListenerCall(oldPosition, newPosition);
        }
    }

    /**
     * Internal method used to send callbacks to listener
     *
     * @param oldPosition old selected tab position, -1 if this is first call
     * @param newPosition newly selected tab position
     */
    private void sendListenerCall(int oldPosition, int newPosition) {
        if (mTabSelectedListener != null) {
//                && oldPosition != -1) {
            if (oldPosition == newPosition) {
                mTabSelectedListener.onTabReselected(newPosition);
            } else {
                mTabSelectedListener.onTabSelected(newPosition);
                if (oldPosition != -1) {
                    mTabSelectedListener.onTabUnselected(oldPosition);
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Animating methods
    ///////////////////////////////////////////////////////////////////////////

    /**
     * hide with animation
     */
    public void hide() {
        hide(true);
    }

    /**
     * @param animate is animation enabled for hide
     */
    public void hide(boolean animate) {
        setTranslationY(this.getHeight(), animate);
    }

    /**
     * unHide with animation
     */
    public void unHide() {
        unHide(true);
    }

    /**
     * @param animate is animation enabled for unHide
     */
    public void unHide(boolean animate) {
        setTranslationY(0, animate);
    }

    /**
     * @param offset  offset needs to be set
     * @param animate is animation enabled for translation
     */
    private void setTranslationY(int offset, boolean animate) {
        if (animate) {
            animateOffset(offset);
        } else {
            if (mTranslationAnimator != null) {
                mTranslationAnimator.cancel();
            }
            this.setTranslationY(offset);
        }
    }

    /**
     * Internal Method
     * <p/>
     * used to set animation and
     * takes care of cancelling current animation
     * and sets duration and interpolator for animation
     *
     * @param offset translation offset that needs to set with animation
     */
    private void animateOffset(final int offset) {
        if (mTranslationAnimator == null) {
            mTranslationAnimator = ViewCompat.animate(this);
            mTranslationAnimator.setDuration(mRippleAnimationDuration);
            mTranslationAnimator.setInterpolator(INTERPOLATOR);
        } else {
            mTranslationAnimator.cancel();
        }
        mTranslationAnimator.translationY(offset).start();
    }


    ///////////////////////////////////////////////////////////////////////////
    // Getters
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @return activeColor
     */
    public int getActiveColor() {
        return mActiveColor;
    }

    /**
     * @return in-active color
     */
    public int getInActiveColor() {
        return mInActiveColor;
    }

    /**
     * @return background color
     */
    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    /**
     * @return current selected position
     */
    public int getCurrentSelectedPosition() {
        return mSelectedPosition;
    }

    /**
     * @return animation duration
     */
    public int getAnimationDuration() {
        return mAnimationDuration;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Listener interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Callback interface invoked when a tab's selection state changes.
     */
    public interface OnTabSelectedListener {

        /**
         * Called when a tab enters the selected state.
         *
         * @param position The position of the tab that was selected
         */
        void onTabSelected(int position);

        /**
         * Called when a tab exits the selected state.
         *
         * @param position The position of the tab that was unselected
         */
        void onTabUnselected(int position);

        /**
         * Called when a tab that is already selected is chosen again by the user. Some applications
         * may use this action to return to the top level of a category.
         *
         * @param position The position of the tab that was reselected.
         */
        void onTabReselected(int position);
    }

    /**
     * Simple implementation of the OnTabSelectedListener interface with stub implementations of each method.
     * Extend this if you do not intend to override every method of OnTabSelectedListener.
     */
    public static class SimpleOnTabSelectedListener implements OnTabSelectedListener {
        @Override
        public void onTabSelected(int position) {
        }

        @Override
        public void onTabUnselected(int position) {
        }

        @Override
        public void onTabReselected(int position) {
        }
    }
}
