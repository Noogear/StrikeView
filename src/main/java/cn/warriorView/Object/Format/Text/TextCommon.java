package cn.warriorView.Object.Format.Text;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class TextCommon implements IText {

    @Override
    public Component format(String text) {
        return MiniMessage.miniMessage().deserialize(text);
    }
}
