package dialog.hb.com.basedialog.iconfont;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by hanbin on 17/5/15.
 */
public class MDFontsUtils {
    public static Typeface OCTICONS;

    /**
     * 获取资源文件
     * 
     * @param context
     * @return
     */
    public static Typeface getOctIcons(final Context context) {
        if (OCTICONS == null)
            OCTICONS = getTypeface(context, "font/iconfont.ttf");
        return OCTICONS;
    }

    /**
     * 设置文字
     * 
     * @param textViews
     */
    public static void setOcticons(final TextView... textViews) {
        if (textViews == null || textViews.length == 0)
            return;

        Typeface typeface = getOctIcons(textViews[0].getContext());
        for (TextView textView : textViews)
            textView.setTypeface(typeface);
    }

    public static void setOctionsForRadioButton(final RadioButton... radioButtons){
        if(radioButtons == null || radioButtons.length == 0){
            return;
        }
        Typeface typeface = getOctIcons(radioButtons[0].getContext());
        for (RadioButton radioButton : radioButtons)
            radioButton.setTypeface(typeface);
    }

    /**
     * 获取资源文件
     * 
     * @param context
     * @param name
     * @return
     */
    public static Typeface getTypeface(final Context context, final String name) {
        return Typeface.createFromAsset(context.getAssets(), name);
    }
}
