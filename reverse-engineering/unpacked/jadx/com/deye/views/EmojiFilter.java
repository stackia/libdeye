package com.deye.views;

import android.text.InputFilter;
import android.text.Spanned;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class EmojiFilter implements InputFilter {
    private boolean isEmojiCharacter(int i) {
        return (i >= 128512 && i <= 128591) || (i >= 127744 && i <= 128511) || ((i >= 128640 && i <= 128767) || ((i >= 9728 && i <= 9983) || ((i >= 9984 && i <= 10175) || ((i >= 65024 && i <= 65039) || ((i >= 129280 && i <= 129535) || (i >= 129648 && i <= 129791))))));
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (containsEmoji(charSequence)) {
            return "";
        }
        return null;
    }

    private boolean containsEmoji(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int iCodePointAt = Character.codePointAt(charSequence, i);
            if (Character.isSupplementaryCodePoint(iCodePointAt)) {
                i++;
            }
            if (isEmojiCharacter(iCodePointAt)) {
                return true;
            }
            i++;
        }
        return false;
    }
}
