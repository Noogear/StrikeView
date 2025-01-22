package cn.warriorView.View;


import cn.warriorView.Object.Animation.Animation;
import cn.warriorView.Object.Range;
import cn.warriorView.Object.Replacement;

public record ViewParams(
        String textFormat,
        Replacement replacement,
        Range scale,
        boolean shadow,
        float viewRange,
        byte viewMarge,
        int backgroundColor,
        boolean seeThrough,
        boolean onlyPlayer,
        Animation animation,
        String position
) {
    public ViewParams {
        if (viewRange < 0) throw new IllegalArgumentException("View range cannot be negative");
    }

}
